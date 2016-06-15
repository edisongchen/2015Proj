package com.proj.controller.sys;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.zookeeper.Login;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.proj.common.security.FormAuthenticationFilter;
import com.proj.common.security.SysAuthorizaingRealm.Principal;

@Controller
public class LoginController {

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(){
		return "/sys/sysLogin";
	}
	
	@RequestMapping(value = "/login",method =RequestMethod.POST)
	public String login(@RequestParam(FormAuthenticationFilter.DEFAULT_USERNAME_PARAM) String username,
			HttpServletRequest request, HttpServletResponse response, Model model){
		 Subject subject = SecurityUtils.getSubject();
         Principal principal = (Principal) subject.getPrincipal();
         if (principal != null) {
        	 System.out.println(principal.getId());
			return "redirect:/index";
		}
        System.out.println("/////登陆失败");
        return "/sys/sysLogin";
	}
	
	@RequestMapping(value = "/index")
	public String toIndex(){
		//TODO 需要验证
		System.out.println("to index...");
		return "/sys/sysIndex";
	}
}
