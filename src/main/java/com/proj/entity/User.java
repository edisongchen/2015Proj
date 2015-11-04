package com.proj.entity;

import org.apache.ibatis.type.Alias;
//@Alias("user2")
public class User {
	private String id;
	private String nickName;
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
	@Override
	public String toString() {
		return "User [id=" + id + ", nickName=" + nickName + ", loginName=" + loginName + ", password=" + password
				+ "]";
	}
	
	
	
}
