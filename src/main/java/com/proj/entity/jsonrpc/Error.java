/**
 * 
 */
package com.proj.entity.jsonrpc;

/**
 * 
 * @author ctg
 * @date 2016年2月27日
 */
public class Error {

	private int code;
	private String message;
	private String data;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public Error() {
	}

	public Error(int code, String message, String data) {
		this.code = code;
		this.message = message;
		this.data = data;
	}

}
