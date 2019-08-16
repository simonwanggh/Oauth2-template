package com.zihong.auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import com.zihong.auth.config.props.SecurityProperties;
import com.zihong.auth.exception.PropertyNotBeSetException;

@Configuration
@EnableAuthorizationServer
public class AuthServerConfig extends AuthorizationServerConfigurerAdapter {
	@Autowired
	@Qualifier("authenticationManagerBean")
    private AuthenticationManager authenticationManager;
	
	@Autowired
	private SecurityProperties securityProperties;
	
	@Autowired
	@Qualifier("basicUserServiceBean")
	private UserDetailsService userDetailsService;
	
	
	@Value("${oauth2.redirecturis}")
	private String redirectUris;
	
	@Value("${jwt.secret.key}")
	private String jwtKey;

 
    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
        oauthServer.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
    }

 
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
    	
    	if(null == redirectUris || redirectUris.trim().length() == 0) {
    		throw new PropertyNotBeSetException("redirect URIs are not set!");
    	}
    	
    	String[] redUriArr = redirectUris.split(",");
   	
        clients.inMemory()
          .withClient(securityProperties.getClientCredential().getClientId())
          .secret(securityProperties.getClientCredential().getSecret()) //W@u&Jl2OPD
          .authorizedGrantTypes("authorization_code","password", "refresh_token")
          .scopes("user_info")
          .autoApprove(true)
          .redirectUris(redUriArr); 
    }
 
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(tokenStore()).accessTokenConverter(accessTokenConverter())
        .authenticationManager(authenticationManager).userDetailsService(userDetailsService);
    }
    
    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(accessTokenConverter());
    }
 
    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey(jwtKey);
        return converter;
    }
 
    @Bean
    @Primary
    public DefaultTokenServices tokenServices() {
        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setTokenStore(tokenStore());
        defaultTokenServices.setSupportRefreshToken(true);
        return defaultTokenServices;
    }
}
