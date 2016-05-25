package com.proj.vo;

/**
 * 简单的接口调用Result wrapper
 * @author ctg
 */
public class Result<T> {
	
	/**
	 * 状态码
	 */
	private String code;
	
	/**
	 * 状态信息,额外信息
	 */
	private String message;
	
	private T content;
	
	
	public static  Result buildErrorResult(String message) {
		return buildResult(Status.ERROR, message);
	}
	
	public static  <T>Result<T> buildErrorResult(T t) {
		return buildResult(Status.ERROR, t);
	}

	public static  Result buildErrorResult() {
		return buildResult(Status.ERROR);
	}
	
	public static  Result buildSuccessResult() {
		return buildResult(Status.OK);
	}
	
	public static  Result buildSuccessResult(String message) {
		return buildResult(Status.OK, message);
	}

	public static  <T>Result<T> buildSuccessResult(T t) {
		return buildResult(Status.OK, t);
	}
	
	
	public static Result buildResult(Status status, String message) {
		return new Result(status.getCode(),message);
	}

	public static Result buildResult(Status status){
		return new Result(status.getCode(),status.getReason());
	}
	
	public static <T>Result<T> buildResult(Status status ,T t){
		return new Result<T>(status.getCode(),status.getReason(),t);
	}
	
	
	//=============get/setter
	public T getContent() {
		return content;
	}

	public void setContent(T content) {
		this.content = content;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Result() {
		super();
	}
	

	public Result(String code, String reason) {
		this.code = code;
		this.message = reason;
	}



	public Result(String code, String message, T t) {
		this.code = code;
		this.message = message;
		this.content = t;
	}



	public enum Status {
		/**
		 * 错误状态
		 */
		ERROR("100", "ERROR"),
		
		/**
		 * 成功状态,创建成功结果的时候自动设置.
		 */
		OK("200", "OK"),
		
		/**
		 * 错误的请求,参数不正确,如果没有更精确的状态表示,使用此状态.
		 */
		BAD_REQUEST("400", "Bad Request"),
		
		/**
		 * 未授权,未登录或登录失效.
		 */
		UNAUTHORIZED("401", "Unauthorized"),
		
		/**
		 * 请求方法错误
		 */
		METHOD_NOT_ALLOWED("405", "Method Not Allowed"),
		
		/**
		 * 服务器内部错误,如果没有更精确的状态表示,使用此状态.
		 */
		INTERNAL_SERVER_ERROR("500", "Internal Server Error"),

		UNKOWN_ERROR("600", "未知错误"),

		NOT_EXIST_ERROR("700", "数据不存在"),
		
		EXIST_ERROR("800","数据已经存在");
		
		/**
		 * 状态码,长度固定为6位的字符串
		 */
		private String code;
		/**
		 * 错误信息
		 */
		private String reason;
		
		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		public String getReason() {
			return reason;
		}

		public void setReason(String reason) {
			this.reason = reason;
		}

		private Status(String code, String reason) {
			this.code = code;
			this.reason = reason;
		}
		
	}//end enum Status
	
}
