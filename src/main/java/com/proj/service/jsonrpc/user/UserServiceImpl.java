/**
 * 
 */
package com.proj.service.jsonrpc.user;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;
import com.proj.common.mapper.JsonMapper;
import com.proj.entity.jsonrpc.JsonRpc;

/**
 * 
 * @author ctg
 * @date 2016年2月27日
 */
@Service
public class UserServiceImpl implements UserService{

	private static JsonMapper mapper = JsonMapper.getInstance();
	
	@Override
	public String getUserById(JsonRpc bean) throws Exception {
		Map<String,Object> response = Maps.newHashMap();
		response.put("jsonrpc", "2.0");
		response.put("id", bean.getId()+"1");
		
		String beanStr = mapper.toJsonString(bean);
		System.out.println("-->"+beanStr);
		Map<String, Object> params = bean.getParams();
		System.out.println("params:"+params);
		if (params == null ||params.isEmpty()) {
			params.put("data", "param null");
			params.put("code", "-327");
//			response.put("error", value)
			//throw new Exception("messag is override");
		}else {
			params.put("name", "ttt1");
			params.put("password", "123456");
		}
		response.put("result", params);
		System.out.println(mapper.toJson(response));
		return mapper.toJson(response);
	}

	
}
