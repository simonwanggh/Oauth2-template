package com.zihong.auth.provider;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryContext;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import com.zihong.auth.cache.repository.WxLoginUserStore;
import com.zihong.auth.config.token.CustomAuthenticationToken;
import com.zihong.auth.provider.exception.NoWxUserLoginException;
import com.zihong.wx.to.WxCurrentScanUser;

@Service
public class WxUserService {
	
	@Autowired
	private RetryTemplate retryTemplate;
	
	@Autowired
	private WxLoginUserStore wxUserStore;

	public Authentication authenticate(Authentication authentication) {
		
		SimpleRetryPolicy policy = new SimpleRetryPolicy();
		policy.setMaxAttempts(120);
		

		retryTemplate.setRetryPolicy(policy);
		

		try {
			CustomAuthenticationToken customAuth = (CustomAuthenticationToken)authentication;
			WxCurrentScanUser result = retryTemplate.execute(new RetryCallback<WxCurrentScanUser, NoWxUserLoginException>() {

			    public WxCurrentScanUser doWithRetry(RetryContext context) throws NoWxUserLoginException {
			    	WxCurrentScanUser user = null;
			    	try {
			    		user = wxUserStore.getLoginUser(customAuth.getScene());
			    	}catch(Exception e) {
			    		//please add log
			    	}
			    	if(user == null) {
			    		try {
							Thread.currentThread().sleep(500l);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
			    		
			    		throw new NoWxUserLoginException();
			    	}
			    	wxUserStore.evictSingleCacheValue(customAuth.getScene());
			        return user;
			    }

			});
			
			if(result != null) {				
				return generateAuthentication(result,customAuth.getTenantID());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	private Authentication generateAuthentication(WxCurrentScanUser result, String tenant) {
		return new CustomAuthenticationToken(result.getWxUser().getNickname(), "",tenant,
				Collections.singletonList(new SimpleGrantedAuthority(CustomAuthenticationProvider.COMMON_USER)));
	}
	
	

}
