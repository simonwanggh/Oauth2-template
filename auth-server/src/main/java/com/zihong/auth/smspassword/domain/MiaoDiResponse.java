package com.zihong.auth.smspassword.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 
 * Sample : 1. {"respCode":"00000",
 *           "respDesc":"请求成功。",
 *            "failCount":"0",
 *            "failList":[],
 *            "smsId":"100bfa64ec174d2eb20442d3b317af72"}
 *            
 *          2. {"respCode":"00006",
 *               "respDesc":"sign错误 "}
 *               
 *          3. {"respCode":"00131",
 *               "respDesc":"content参数为空"}
 * 
 * @author wangzi
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MiaoDiResponse {
	
	private String respCode;
	private String respDesc;
	private String smsId;
	public String getRespCode() {
		return respCode;
	}
	public void setRespCode(String respCode) {
		this.respCode = respCode;
	}
	public String getRespDesc() {
		return respDesc;
	}
	public void setRespDesc(String respDesc) {
		this.respDesc = respDesc;
	}
	public String getSmsId() {
		return smsId;
	}
	public void setSmsId(String smsId) {
		this.smsId = smsId;
	}
	
	

}
