package com.proj.test.jqgrid;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.proj.common.mapper.JsonMapper;

import jersey.repackaged.com.google.common.collect.Maps;

@Controller
@RequestMapping("/jqgrid")
public class JqgridController {

	@RequestMapping(value = "/getJson", method = RequestMethod.GET)
	public void getJsonData(HttpServletRequest request, HttpServletResponse response) {
		 String jsondata = "{\"page\":\"1\",\"total\":2,"+
				 "\"records\":\"13\","+
				 "\"rows\":["+
					 "{\"id\":\"13\",\"cell\":[\"13\",\"2007-10-06\",\"Client 3\",\"1000.00\",\"0.00\",\"1000.00\",null]}"+
					 "{\"id\":\"12\",\"cell\":[\"12\",\"2007-10-06\",\"Client 3\",\"1000.00\",\"0.00\",\"1000.00\",null]}"+
					 "{\"id\":\"11\",\"cell\":[\"11\",\"2007-10-06\",\"Client 3\",\"1000.00\",\"0.00\",\"1000.00\",null]}"+
					 "{\"id\":\"10\",\"cell\":[\"10\",\"2007-10-06\",\"Client 3\",\"1000.00\",\"0.00\",\"1000.00\",null]}"+
					 "{\"id\":\"9\",\"cell\":[\"9\",\"2007-10-06\",\"Client 3\",\"1000.00\",\"0.00\",\"1000.00\",null]}"+
					 "{\"id\":\"8\",\"cell\":[\"8\",\"2007-10-06\",\"Client 3\",\"1000.00\",\"0.00\",\"1000.00\",null]}"+
					 "{\"id\":\"7\",\"cell\":[\"7\",\"2007-10-06\",\"Client 3\",\"1000.00\",\"0.00\",\"1000.00\",null]}"+
					 "{\"id\":\"6\",\"cell\":[\"6\",\"2007-10-06\",\"Client 3\",\"1000.00\",\"0.00\",\"1000.00\",null]}"+
					 "{\"id\":\"5\",\"cell\":[\"5\",\"2007-10-06\",\"Client 3\",\"1000.00\",\"0.00\",\"1000.00\",null]}"+
					 "{\"id\":\"4\",\"cell\":[\"4\",\"2007-10-06\",\"Client 3\",\"1000.00\",\"0.00\",\"1000.00\",null]}"+
				 "]"+
				"\"userdata\":{\"amount\":3200,\"tax\":342,\"total\":3564,\"name\":\"Total:\"}"+
				"}";
		try {
			response.getWriter().write(JsonMapper.getInstance().toJson(jsondata));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
