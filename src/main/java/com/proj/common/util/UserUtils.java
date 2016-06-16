package com.proj.common.util;

import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.dom4j.util.UserDataAttribute;
import org.hibernate.internal.StaticFilterAliasGenerator;

import com.google.common.collect.Maps;
import com.proj.common.security.SysAuthorizaingRealm.Principal;
import com.proj.dao.mybatis.sys.IUserDao;
import com.proj.entity.sys.User;

/***
 * 
 * @author ctg
 * @date 2016年6月16日
 */
public class UserUtils {

	private static IUserDao userDao = ApplicationContextHelper.getBean(IUserDao.class);

	public static User getUser() {
		User user = (User) getCache(Constant.CACHE_USER);
		if (user == null) {
			try {
				Subject subject = SecurityUtils.getSubject();
				Principal principal = (Principal) subject.getPrincipal();
				if (principal != null) {
					// 缓存中没有user
					user = userDao.findById(principal.getId());
					if (user != null) {
						putCache(Constant.CACHE_USER, user);
					} else {
						SecurityUtils.getSubject().logout();
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return user;
	}

	// ===============User Cache Principal cacheMap
	public static Object getCache(String key) {
		return getCache(key, null);
	}

	public static Object getCache(String key, Object defaultValue) {
		Object obj = getCacheMap().get(key);
		return obj != null ? obj : defaultValue;
	}

	public static void removeCache(String key) {
		getCacheMap().remove(key);
	}

	public static void putCache(String key, Object value) {
		getCacheMap().put(key, value);
	}

	/**
	 * shiro will use cache by configed ecache
	 */
	public static Map<String, Object> getCacheMap() {

		try {
			Subject subject = SecurityUtils.getSubject();
			Principal principal = (Principal) subject.getPrincipal();
			if (principal != null) {
				return principal.getCacheMap();
			} else {
				Map<String, Object> map = Maps.newHashMap();
				return map;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Map<String, Object> map = Maps.newHashMap();
		return map;
	}
}
