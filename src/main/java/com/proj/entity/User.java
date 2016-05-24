package com.proj.entity;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonProperty;

//@Alias("user2")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class User {
	private String id;
	@JsonProperty("nickName")
	@XmlElement(name = "nickName")
	@NotNull
	@Size(min = 6, max = 50)
	private String nickName;

	@JsonProperty("loginName")
	@XmlElement(name = "loginName")
	@NotNull
	@Size(min = 6, max = 50)
	private String loginName;

	private String password;

	public String getId() {
		return id;
	}

	public void setId(String pId) {
		id = pId;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String pNickName) {
		nickName = pNickName;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String pLoginName) {
		loginName = pLoginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String pPassword) {
		password = pPassword;
	}

	
	public User() {
	}

	public User(String id) {
		super();
		this.id = id;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", nickName=" + nickName + ", loginName=" + loginName + "]";
	}

}
