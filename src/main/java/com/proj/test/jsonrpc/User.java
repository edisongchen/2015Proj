/**
 * 
 */
package com.proj.test.jsonrpc;

/**
 * 
 * @author ctg
 * @date 2016年2月25日
 */
public class User {

	String userName;
	String firstName;
	String password;

	
	
	public User() {
		super();
	}

	public User(String userName, String firstName, String password) {
		super();
		this.userName = userName;
		this.firstName = firstName;
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [userName=" + userName + ", firstName=" + firstName
				+ ", password=" + password + "]";
	}

}
