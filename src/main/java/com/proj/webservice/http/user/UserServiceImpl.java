package com.proj.webservice.http.user;

import org.springframework.stereotype.Service;

import com.ctg.dubbo_hello_api.User;
import com.ctg.dubbo_hello_api.UserService;

@Service("userHttpService")
public class UserServiceImpl implements UserService{

	@Override
	public User getUser(Long id) {
		System.out.println("invoke userserviceImpl..");
		return new User(id, "name"+id);
	}

}
