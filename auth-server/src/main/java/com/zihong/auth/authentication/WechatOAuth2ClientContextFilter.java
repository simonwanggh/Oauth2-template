package com.zihong.auth.authentication;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.oauth2.client.filter.OAuth2ClientContextFilter;
import org.springframework.security.oauth2.client.resource.UserRedirectRequiredException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.util.Assert;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.Filter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;


public class WechatOAuth2ClientContextFilter extends OAuth2ClientContextFilter implements Filter, InitializingBean {

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.notNull(redirectStrategy,
                "Redirect strategy for wechat need be provided");
    }
    
    @Override
    public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
        this.redirectStrategy = redirectStrategy;
    }


    @Override
    protected void redirectUser(UserRedirectRequiredException e,
                                HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        String redirectUri = e.getRedirectUri();
        UriComponentsBuilder builder = UriComponentsBuilder
                .fromHttpUrl(redirectUri);
        Map<String, String> requestParams = e.getRequestParams();

        if (requestParams.containsKey("client_id")) {
            requestParams.put("appid", requestParams.remove("client_id"));
        }

        for (Map.Entry<String, String> param : requestParams.entrySet()) {
            builder.queryParam(param.getKey(), param.getValue());
        }

        if (e.getStateKey() != null) {
            builder.queryParam("state", e.getStateKey());
        }

        builder.fragment("wechat_redirect");

        this.redirectStrategy.sendRedirect(request, response, builder.build()
                .encode().toUriString());
    }

    

}
