package com.proj.dao.mybatis;

import java.util.List;

import com.proj.common.persistence.annotation.MyBatisRepository;
import com.proj.entity.User;

@MyBatisRepository
public interface IUserDao {
	public List<User> findAll(User user);
}
