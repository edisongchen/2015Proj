package com.proj.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proj.dao.mybatis.sys.IUserDao;
import com.proj.entity.sys.User;

/**
 * 系统管理，安全相关实体类
 * @author ctg
 */
@Service
@Transactional(readOnly = true)
public class SystemService {

	@Autowired
	private IUserDao userDao;
	
	public User getUserById(String id){
		return userDao.findById(id);
	}
	
	public User getUserByLoginName (String loginName){
		return userDao.findByLoginName(loginName);
	}
}
