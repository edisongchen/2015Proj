/**
 * 
 */
package com.proj.test.jsonrpc;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.proj.common.mapper.JsonMapper;

/**
 * 
 * @author ctg
 * @date 2016年2月25日
 */
@Component
public class UserServiceImpl implements UserService{

	public User createUser(String userName, String firstName, String password) {
        User user = new User();
        user.setUserName(userName);
        user.setFirstName(firstName);
        user.setPassword(password);
//        database.saveUser(user)
        return user;
    }

    public String createUser() {
    	System.out.println("/////               .............");
    	Map<String, Object> ret = Maps.newHashMap();
    	ret.put("id", "1");
    	ret.put("result", "success");
    	ret.put("jsonrpc","1.1");
        return JsonMapper.getInstance().toJson(new User());
    }

    public User findUserByUserName(String userName) {
//        return database.findUserByUserName(userName);
    	return new User(userName, "random2", "random2");
    }

    public int getUserCount() {
//        return database.getUserCount();
    	return 2;
    }
}
