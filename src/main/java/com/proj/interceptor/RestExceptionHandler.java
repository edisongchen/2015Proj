package com.proj.interceptor;

import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import com.proj.common.mapper.JsonMapper;
import com.proj.vo.Result;

/**
 * REST 统一异常处理器
 * @author ctg
 */
public class RestExceptionHandler implements ExceptionMapper<Exception>{

	@Override
	public Response toResponse(Exception ex) {
		System.out.println("interceptor has catch a exception");
		Result result = null;
		if (ex instanceof NotFoundException) {
			result = Result.buildResult(Result.Status.METHOD_NOT_ALLOWED);
		} else if (ex instanceof InternalServerErrorException) {
			result = Result.buildResult(Result.Status.INTERNAL_SERVER_ERROR);
		} else {
			result = Result.buildErrorResult();
		}
		String json = JsonMapper.getInstance().toJson(result);
		
        return Response.status(Response.Status.BAD_REQUEST).entity(json).build();
	}

}
