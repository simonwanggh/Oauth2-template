package com.zihong.wx.to;

import java.io.Serializable;

import org.springframework.util.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

/**
 * 
 * @author Zihong Wang
 *<xml><ToUserName><![CDATA[gh_6cbbac877d70]]></ToUserName>
<FromUserName><![CDATA[oO2C91RaX-V8ohCKlkBs8lfc2Olg]]></FromUserName>
<CreateTime>1560691967</CreateTime>
<MsgType><![CDATA[event]]></MsgType>
<Event><![CDATA[SCAN]]></Event>
<EventKey><![CDATA[123]]></EventKey>
<Ticket><![CDATA[gQF07zwAAAAAAAAAAS5odHRwOi8vd2VpeGluLnFxLmNvbS9xLzAycHpTd2d0WHdlWmwxNEY1NjF0MXAAAgTtRAZdAwQ8AAAA]]></Ticket>
</xml>
 */

@JsonIgnoreProperties(ignoreUnknown = true)
@JacksonXmlRootElement(localName = "xml")
public class WxCurrentScanUser implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8855870292094256460L;

	private static final String SC_PREFIX = "qrscene_";
	
	@JacksonXmlCData
	@JacksonXmlProperty(localName = "FromUserName")
	private String openId;
	
	@JacksonXmlProperty(localName = "CreateTime")
	private int createTime;
	
	@JacksonXmlCData
	@JacksonXmlElementWrapper(localName = "EventKey")
	private String scene;
	
	
	private WxUser wxUser;

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String fromUserName) {
		this.openId = fromUserName;
	}

	public int getCreateTime() {
		return createTime;
	}

	public void setCreateTime(int createTime) {
		this.createTime = createTime;
	}

	public String getScene() {
		if(StringUtils.hasLength(this.scene) && this.scene.startsWith(SC_PREFIX)) {
			scene = scene.substring(8);
		}
		return scene;
	}

	public void setScene(String scene) {
		this.scene = scene;
	}

	public WxUser getWxUser() {
		return wxUser;
	}

	public void setWxUser(WxUser wxUser) {
		this.wxUser = wxUser;
	}
	
	
	

}
