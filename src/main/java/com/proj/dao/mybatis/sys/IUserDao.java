package com.proj.dao.mybatis.sys;

import com.proj.common.persistence.BaseMybatisDao;
import com.proj.common.persistence.annotation.MyBatisRepository;
import com.proj.entity.sys.User;

@MyBatisRepository
public interface IUserDao extends BaseMybatisDao<User>{

	public User findByLoginName(String loginName);

}
