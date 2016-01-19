package com.proj.controller.report;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/report")
public class EchartsController {
	private Logger logger = LoggerFactory.getLogger(EchartsController.class);
	
	@RequestMapping("/echarts")
	public String toEcharts(Model model){
		return "report/echarts";
	}
}
