/**
 * 
 */
package com.proj.test.jsonrpc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * @author ctg
 * @date 2016年2月24日
 */
public class ReceivChannel  {
	private static final long serialVersionUID = 1L;

	public List<HashMap<String,Object>> publicQuery(String key1,String key2,String sql) throws Exception{
	    //在进行业务逻辑处理前，可以对传递过来的参数进行合法性验证。
	    //有必要的情况下可以对参数进行加密，并在这里解密。
	    List<HashMap<String,Object>> list = new ArrayList<HashMap<String,Object>>();
	    HashMap<String,Object> hm = new LinkedHashMap<String,Object>();
	    hm.put("网站名称","轰隆隆小站");
	    hm.put("网站地址","http://www.honglonglong.com");
	    list.add(hm);
	 return list;
	}
}
