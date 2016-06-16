package com.proj.common.security;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.springframework.stereotype.Service;

import com.proj.common.util.ApplicationContextHelper;
import com.proj.entity.sys.User;
import com.proj.service.SystemService;

/**
 *	系统授权类
 * @author ctg
 */
@Service
public class SysAuthorizaingRealm extends AuthorizingRealm{

	private SystemService sysService;
	
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		return null;
	}

	/**
	 * 认证回调函数,登陆时调用
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken pToken) throws AuthenticationException {
		UsernamePasswordToken token=(UsernamePasswordToken)pToken;
		String username = token.getUsername();
		User user = getSystemService().getUserByLoginName(username);
		/**
		 * 返回SimpleAuthenticationInfo认证实例，与formAuthentication构造的token
		 * 进行默认策略 如果认证成功会返回successUrl(shiroFilter中配置)否则getPrincipal为空
		 */
		return new SimpleAuthenticationInfo(new Principal(user), user.getPassword(), getName());
	}
	
	
	/**
	 * 清空用户关联权限认证，待下次使用时重新加载
	 */
	public void clearCachedAuthorizationInfo(String principal) {
		SimplePrincipalCollection principals = new SimplePrincipalCollection(principal, getName());
		clearCachedAuthorizationInfo(principals);
	}

	/**
	 * 清空所有关联认证
	 */
	public void clearAllCachedAuthorizationInfo() {
		Cache<Object, AuthorizationInfo> cache = getAuthorizationCache();
		if (cache != null) {
			for (Object key : cache.keys()) {
				cache.remove(key);
			}
		}
	}
	
	private SystemService getSystemService(){
		if (sysService == null) {
			sysService = ApplicationContextHelper.getBean(SystemService.class);
		}
		return sysService;
	}

	/**
	 * 授权用户信息,一个principal是subject的标识
	 */
	public static class Principal implements Serializable {
		private static final long serialVersionUID = 1L;
		
		private String id;
		private String loginName;
		private String name;
		private Map<String, Object> cacheMap;

		public Principal(User user) {
			this.id = user.getId();
			this.loginName = user.getLoginName();
			this.name = user.getName();
		}

		public String getId() {
			return id;
		}

		public String getLoginName() {
			return loginName;
		}

		public String getName() {
			return name;
		}

		public Map<String, Object> getCacheMap() {
			if (cacheMap==null){
				cacheMap = new HashMap<String, Object>();
			}
			return cacheMap;
		}
	}
}
