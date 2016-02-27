/**
 * 
 */
package com.proj.controller.jsonrpc;

import java.lang.reflect.Method;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.googlecode.jsonrpc4j.ErrorResolver;

/**
 * 
 * @author ctg
 * @date 2016年2月27日
 */
public class ExceptionResolver implements ErrorResolver{

	@Override
	public JsonError resolveError(Throwable t, Method method,
			List<JsonNode> arguments) {
		
		return null;
	}

}
