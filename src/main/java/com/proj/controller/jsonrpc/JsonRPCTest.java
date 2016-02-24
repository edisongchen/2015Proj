/**
 * 
 */
package com.proj.controller.jsonrpc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.proj.common.mapper.JsonMapper;
import com.proj.vo.JsonRpc;


/**
 * 
 * @author ctg
 * @date 2016年2月24日
 */
@Controller
@RequestMapping(value = "/jsonrpc")
@SuppressWarnings("rawtypes")
public class JsonRPCTest {

 	@RequestMapping("/test/request")
    @ResponseBody
    public Object request(JsonRpc request) {
 		System.out.println("get...");
 		String ret = JsonMapper.getInstance().toJson(request);
        return ret;//jsonRpcService.request(request);
    }
}
