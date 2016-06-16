package com.proj.controller.sys;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.proj.common.util.StringUtils;
import com.proj.common.util.UserUtils;
import com.proj.entity.sys.User;
import com.proj.service.SystemService;
import com.proj.service.sys.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@ModelAttribute
	public User get(@RequestParam(required = false) String id) {
		if (StringUtils.isNotBlank(id)) {
			return userService.getUserById(id);
		} else {
			return new User();
		}
	}

	@RequestMapping("/update")
	public String update() {
		return "";
	}

	@RequestMapping("/save")
	public String save(User user,String newLoginName,String newPassword) {
		try {
			
			//user.setPassword(SystemService.entryptPassword(newPassword));
			//userService.update(user);
			 // 清除当前用户缓存
			UserUtils.getCacheMap().clear();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/index";
	}
}
