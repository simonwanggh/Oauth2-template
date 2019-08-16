package com.zihong.auth.config;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoTokenServices;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.filter.OAuth2ClientAuthenticationProcessingFilter;
import org.springframework.security.oauth2.client.filter.OAuth2ClientContextFilter;
import org.springframework.security.oauth2.client.token.RequestEnhancer;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeAccessTokenProvider;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.CompositeFilter;

import com.zihong.auth.config.filter.CustomAuthenticationFilter;
import com.zihong.auth.config.filter.CustomOAuth2ClientAuthenticationProcessingFilter;

@Configuration
@EnableOAuth2Client
@Order(2)
public class OAuth2SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	OAuth2ClientContext oauth2ClientContext;

	@Autowired
	@Qualifier("phoneAuthProvider")
	private AuthenticationProvider phoneAuthProvider;
	
	@Autowired
	@Qualifier("dbAuthProvider")
	private AuthenticationProvider dbAuthProvider;
	
	@Autowired
	@Qualifier("wxAuthProvider")
	private AuthenticationProvider wxAuthProvider;
	
	@Value("${wechat.fake.username}")
	private String fakeUserName;

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	@Qualifier("basicUserServiceBean")
	@Override
	public UserDetailsService userDetailsServiceBean() throws Exception {
		return super.userDetailsServiceBean();
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/webjars/**", "/resources/**","/js/**","/css/**","/fonts/**","/app.js","/favicon.ico");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.requestMatchers().antMatchers("/login", "/login.do","/oauth/authorize","/logout.do", "/error")
		.and().authorizeRequests()
        .antMatchers("/login","/logout.do", "/error").permitAll()
        .antMatchers("/**").authenticated()
        .and()
        .authorizeRequests()
        .anyRequest()
        .authenticated()
        .and()
        .formLogin()
        .loginPage("/login")
        .and().addFilterBefore(ssoCompositeFilter(), BasicAuthenticationFilter.class)
        .addFilterBefore(authenticationFilter(), UsernamePasswordAuthenticationFilter.class)
        .logout()
        .invalidateHttpSession(true)
        .deleteCookies("JSESSIONID");
	
        //http.csrf().disable();
	
	}
	

	

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(wxAuthProvider);
		auth.authenticationProvider(phoneAuthProvider);
		auth.authenticationProvider(dbAuthProvider);
		
		auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder()).withUser("john")
				.password("$2a$10$Qz/O/oQmbmnSlQ5xcj.YzObZST/e4wMDCRjiiG5vQxlkDpEtNJr2y") // 123
				.roles("USER");
	}
	
	@Bean
    public CustomAuthenticationFilter authenticationFilter() throws Exception {
		CustomAuthenticationFilter authenticationFilter
            = new CustomAuthenticationFilter(fakeUserName);
        authenticationFilter.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/login.do", "POST"));
        authenticationFilter.setAuthenticationManager(authenticationManagerBean());
        return authenticationFilter;
    }
	
	@Bean
	public FilterRegistrationBean<OAuth2ClientContextFilter> oauth2ClientFilterRegistration(OAuth2ClientContextFilter filter) {
		FilterRegistrationBean<OAuth2ClientContextFilter> registration = new FilterRegistrationBean<OAuth2ClientContextFilter>();
		registration.setFilter(filter);
		registration.setOrder(-100);
		return registration;
	}
	
	
	@Bean
	@ConfigurationProperties("github")
	public ClientResources github() {
		return new ClientResources();
	}
	
	@Bean
	@ConfigurationProperties("wechat")
	public ClientResources wechat() {
		return new ClientResources();
	}
	
	
	private Filter ssoCompositeFilter() {
		CompositeFilter filter = new CompositeFilter();
		List<Filter> filters = new ArrayList<>();
		filters.add(ssoStandardFilter(github(), "/login/github"));
		filters.add(ssoWechatFilter(wechat(),"/login/wechat"));
		filter.setFilters(filters);
		return filter;
	}
	
	private Filter ssoWechatFilter(ClientResources client,String processingURL) {
        OAuth2ClientAuthenticationProcessingFilter wechatFilter 
        	= new CustomOAuth2ClientAuthenticationProcessingFilter(processingURL);
        OAuth2RestTemplate wechatTemplate = new OAuth2RestTemplate(client.getClient(), oauth2ClientContext) {
            @Override
            protected URI appendQueryParameter(URI uri, OAuth2AccessToken accessToken) {
                try {
                    String query = uri.getRawQuery(); // Don't decode anything here
                    String tokenQueryFragment = this.getResource().getTokenName() + "=" + URLEncoder.encode
                            (accessToken.getValue(),
                                    "UTF-8");
                    if (query == null) {
                        query = tokenQueryFragment;
                    } else {
                        query = query + "&" + tokenQueryFragment;
                    }

                    String openid = (String) accessToken
                            .getAdditionalInformation().get("openid");

                    String openIdQueryFragment = "openid=" + URLEncoder.encode(openid, "UTF-8");
                    query = query + "&" + openIdQueryFragment;

                    // first form the URI without query and fragment parts, so that it doesn't re-encode some query
                    // string chars
                    // (SECOAUTH-90)
                    URI update = new URI(uri.getScheme(), uri.getUserInfo(), uri.getHost(), uri.getPort(), uri
                            .getPath(), null,
                            null);
                    // now add the encoded query string and the then fragment
                    StringBuffer sb = new StringBuffer(update.toString());
                    sb.append("?");
                    sb.append(query);
                    if (uri.getFragment() != null) {
                        sb.append("#");
                        sb.append(uri.getFragment());
                    }

                    return new URI(sb.toString());

                } catch (Exception e) {
                    throw new IllegalArgumentException("Could not parse URI", e);
                } 
            }
        };


        AuthorizationCodeAccessTokenProvider accessTokenProvider = new AuthorizationCodeAccessTokenProvider();
        RequestEnhancer requestEnhancer = (request, resource, form, headers) -> {

            headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

            List<String> clientId = form.get("client_id");
            if (clientId != null) {
                form.remove("client_id");
                form.put("appid", clientId);
            }

            List<String> clientSecret = form.get("client_secret");

            if (clientSecret != null) {
                form.remove("client_secret");
                form.put("secret", clientSecret);
            }
        };

        accessTokenProvider.setAuthorizationRequestEnhancer(requestEnhancer);
        accessTokenProvider.setTokenRequestEnhancer(requestEnhancer);

        MappingJackson2HttpMessageConverter customJsonMessageConverter = new
                MappingJackson2HttpMessageConverter();
        customJsonMessageConverter.setSupportedMediaTypes(Arrays.asList(MediaType.TEXT_PLAIN));

        accessTokenProvider.setMessageConverters(Arrays.asList(customJsonMessageConverter));

        wechatTemplate.setAccessTokenProvider(accessTokenProvider);
        wechatTemplate.setMessageConverters(Arrays.asList(customJsonMessageConverter));


        wechatFilter.setRestTemplate(wechatTemplate);
        UserInfoTokenServices tokenServices = new UserInfoTokenServices(client.getResource().getUserInfoUri(), client.getClient().getClientId());

        tokenServices.setRestTemplate(wechatTemplate);
        wechatFilter.setTokenServices(tokenServices);
        return wechatFilter;
    }
	
	

	private Filter ssoStandardFilter(ClientResources client, String path) {
		OAuth2ClientAuthenticationProcessingFilter filter = new CustomOAuth2ClientAuthenticationProcessingFilter(path);
		OAuth2RestTemplate template = new OAuth2RestTemplate(client.getClient(), oauth2ClientContext);
		filter.setRestTemplate(template);
		UserInfoTokenServices tokenServices = new CustomSocialUserInfoService(
				client.getResource().getUserInfoUri(), client.getClient().getClientId());
		tokenServices.setRestTemplate(template);
		filter.setTokenServices(tokenServices);
		return filter;
	}
}


class ClientResources {

	@NestedConfigurationProperty
	private AuthorizationCodeResourceDetails client = new AuthorizationCodeResourceDetails();

	@NestedConfigurationProperty
	private ResourceServerProperties resource = new ResourceServerProperties();

	public AuthorizationCodeResourceDetails getClient() {
		return client;
	}

	public ResourceServerProperties getResource() {
		return resource;
	}
}
