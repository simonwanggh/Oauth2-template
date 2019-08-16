package com.zihong.auth.authentication;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;



public class WechatUser {
    public String openid;
    public String nickname;
    public Integer sex;
    public String language;
    public String country;
    public String province;
    public String city;
    public String headimgurl;
}
