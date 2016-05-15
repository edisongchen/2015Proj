package com.proj.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.proj.common.mapper.JsonMapper;
import com.proj.entity.tree.ZTree;

@Controller
@RequestMapping("/springmvc")
public class SpringMVCControllerTest {
	private Logger logger = LoggerFactory.getLogger(SpringMVCControllerTest.class);
	//
	@RequestMapping("/test")
	public String test(Model model){
		logger.info("success enter test method");
		model.addAttribute("info", "request springmvc success");
		return "test/hello";
	}
	
	@RequestMapping("/ajaxTest")
	public String toAjaxTest(){
		return "ajaxTest/ajax";
	}
	
	@RequestMapping("/ajax/test")
	@ResponseBody
	public String generateAjax(String id,String data){
		Map<String,String> map = Maps.newHashMap();
		map.put("id", id);
		map.put("data", data);
		System.out.println("////");
		return JsonMapper.getInstance().toJson(map);
	}
	
	@RequestMapping(value="/ajax/tree/next",method=RequestMethod.POST)
	@ResponseBody
	public String getTreeNode(String id){
		List<ZTree> lists = Lists.newArrayList();
		if (id==null) {
			id="3";
		}
		int id_=Integer.valueOf(id);
		for(int i=0;i<3;i++){
			ZTree tree = new ZTree();
			String thisId=""+id_++;
			tree.setId(thisId);
			tree.setName("nb"+thisId);
			tree.setParent(id);
			lists.add(tree);
		}
		return JsonMapper.getInstance().toJson(lists);
	}
}
