package com.subrutin.notification.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "app.mail.smtp")
public class MailSmtpProperties {

	private Boolean auth;
	
	private Boolean startTlsEnable;
	
	private String host;
	
	private Integer port;
	
	private String sslTrust;
	
	private String username;
	
	private String password;

	public Boolean getAuth() {
		return auth;
	}

	public void setAuth(Boolean auth) {
		this.auth = auth;
	}

	public Boolean getStartTlsEnable() {
		return startTlsEnable;
	}

	public void setStartTlsEnable(Boolean startTlsEnable) {
		this.startTlsEnable = startTlsEnable;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

	public String getSslTrust() {
		return sslTrust;
	}

	public void setSslTrust(String sslTrust) {
		this.sslTrust = sslTrust;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
}
