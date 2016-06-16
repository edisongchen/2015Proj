package com.proj.controller.sys;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.proj.common.security.FormAuthenticationFilter;
import com.proj.common.util.StringUtils;
import com.proj.common.util.UserUtils;
import com.proj.entity.sys.User;

@Controller
public class LoginController {

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(){
		User user = UserUtils.getUser();
		if (user != null && user.getId() !=null) {
			return "redirect:/index";
		}
		return "/sys/sysLogin";
	}
	
	@RequestMapping(value = "/login",method =RequestMethod.POST)
	public String login(@RequestParam(FormAuthenticationFilter.DEFAULT_USERNAME_PARAM) String username,
			HttpServletRequest request, HttpServletResponse response, Model model){
		User user = UserUtils.getUser();
		if (user != null && user.getId() !=null) {
			return "redirect:/index";
		}
		model.addAttribute(FormAuthenticationFilter.DEFAULT_USERNAME_PARAM, username);
        return "/sys/sysLogin";
	}
	
	@RequestMapping(value = "/index")
	public String toIndex(HttpServletRequest request,HttpServletResponse response,Model model){
		User user = UserUtils.getUser();
		if (user == null) {
			return "redirect:/login";
		}
		//登陆成功后
		if (user !=null && StringUtils.isNotBlank(user.getId())) {
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			model.addAttribute("user", user);
		}
		return "/sys/sysIndex";
	}
}
