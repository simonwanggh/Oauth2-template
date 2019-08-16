package com.zihong.auth.cache.repository;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import com.zihong.wx.to.WxCurrentScanUser;

@Repository
@CacheConfig(cacheNames = "WeixinCurrentScanUser")
public class WxLoginUserStore {
	
	@Cacheable(key = "#scene")
	public WxCurrentScanUser getLoginUser(String scene) {		
		return null;
	}
	
	@CacheEvict(key = "#scene")
	public void evictSingleCacheValue(String scene) {}

	
	@CachePut(key = "#user.scene")
	public WxCurrentScanUser store(WxCurrentScanUser user) {
		return user;
	}

}
