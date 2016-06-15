package com.proj.entity.sys;

import java.util.Date;

import javax.persistence.Entity;

import com.proj.common.persistence.IdEntity;

@Entity
public class User extends IdEntity<User> {
	private static final long serialVersionUID = 1L;
	private String loginName;// 登录名
	private String password;// 密码
	private String name; // 姓名
	private String email; // 邮箱
	private String phone; // 电话

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}
