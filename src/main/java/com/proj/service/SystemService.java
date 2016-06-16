package com.proj.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proj.common.security.util.Digests;
import com.proj.common.util.Constant;
import com.proj.common.util.Encodes;
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
	
	public static String entryptPassword(String clearText){
		byte[] salt = Digests.generateSalt(Constant.SALT_SIZE);
		byte[] hashPassword = Digests.sha1(clearText.getBytes(),salt,Constant.HASH_INTERATIONS);
		//前16为密钥加后hash码
		return Encodes.encodeHex(salt)+Encodes.encodeHex(hashPassword);
	}
	
	public static boolean validatePassword(String clearText,String password) {
		byte[] salt = Encodes.decodeHex(password.substring(0, 16));
        byte[] hashPassword = Digests.sha1(clearText.getBytes(), salt, Constant.HASH_INTERATIONS);
        System.out.println(salt);
        System.out.println(hashPassword);
        System.out.println("///");
        return password.equals(Encodes.encodeHex(salt) + Encodes.encodeHex(hashPassword));
	}
}
