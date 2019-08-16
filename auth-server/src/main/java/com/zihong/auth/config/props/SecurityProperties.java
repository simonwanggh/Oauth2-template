package com.zihong.auth.config.props;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Validated
@Component
@ConfigurationProperties("credential")
public class SecurityProperties {
	
	@Valid
    @NotNull
	private ClientCredential clientCredential;
	
	@Valid
    @NotNull
	private ServiceAccount serviceAccount;
	
	public static class ClientCredential{
		private String clientId;
		private String secret;
		public String getClientId() {
			return clientId;
		}
		public void setClientId(String clientId) {
			this.clientId = clientId;
		}
		public String getSecret() {
			return secret;
		}
		public void setSecret(String secret) {
			this.secret = secret;
		}
		
		
	}
	
	public static class ServiceAccount{
		private String userName;
		private String password;
		public String getUserName() {
			return userName;
		}
		public void setUserName(String userName) {
			this.userName = userName;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		
	}

	public ClientCredential getClientCredential() {
		return clientCredential;
	}

	public void setClientCredential(ClientCredential clientCredential) {
		this.clientCredential = clientCredential;
	}

	public ServiceAccount getServiceAccount() {
		return serviceAccount;
	}

	public void setServiceAccount(ServiceAccount serviceAccount) {
		this.serviceAccount = serviceAccount;
	}
	
	

}
