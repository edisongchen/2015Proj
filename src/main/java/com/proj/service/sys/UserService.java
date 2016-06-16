package com.proj.service.sys;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proj.common.security.SysAuthorizaingRealm;
import com.proj.common.util.UserUtils;
import com.proj.dao.mybatis.sys.IUserDao;
import com.proj.entity.sys.User;

@Service
@Transactional(readOnly = true)
public class UserService {

	@Autowired
	private IUserDao userDao;
	
	@Autowired
	private SysAuthorizaingRealm sysAuthorizate;
	
	public User getUserById(String id){
		return userDao.findById(id);
	}
	
	@Transactional(readOnly = false)
	public void update(User user) throws Exception{
		userDao.update(user);
//		sysAuthorizate.clearCachedAuthorizationInfo(user.getLoginName());
		sysAuthorizate.clearAllCachedAuthorizationInfo();//TODO 断点进入后是没有清除，后期测试
	}
	
	@Transactional(readOnly = false)
	public void save(User user) throws Exception{
		userDao.save(user);
		sysAuthorizate.clearAllCachedAuthorizationInfo();
	}
}
