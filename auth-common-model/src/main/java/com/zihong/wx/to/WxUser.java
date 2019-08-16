package com.zihong.wx.to;

import java.io.Serializable;

/**
 * 
 * @author Zihong Wang
 *{
    "subscribe": 1, 
    "openid": "o6_bmjrPTlm6_2sgVt7hMZOPfL2M", 
    "nickname": "Band", 
    "sex": 1, 
    "language": "zh_CN", 
    "city": "广州", 
    "province": "广东", 
    "country": "中国", 
    "headimgurl":"http://thirdwx.qlogo.cn/mmopen/g3MonUZtNHkdmzicIlibx6iaFqAc56vxLSUfpb6n5WKSYVY0ChQKkiaJSgQ1dZuTOgvLLrhJbERQQ4eMsv84eavHiaiceqxibJxCfHe/0",
    "subscribe_time": 1382694957,
    "unionid": " o6_bmasdasdsad6_2sgVt7hMZOPfL"
    "remark": "",
    "groupid": 0,
    "tagid_list":[128,2],
    "subscribe_scene": "ADD_SCENE_QR_CODE",
    "qr_scene": 98765,
    "qr_scene_str": ""
}
 */
public class WxUser implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 614647705179146887L;
	private String nickname;
	private String sex;
	private String language;
	private String city;
	private String province;

	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	
	
}
