package com.proj.test.org.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.Ini;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;

public class Tutorial {
	public static void main(String[] args) {
		System.out.println("ffffff");
		Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
		SecurityManager securityManager = factory.getInstance();
		//make the SecurityManager instance availabe to the
		//entire application via static memory;
		SecurityUtils.setSecurityManager(securityManager);
		// get the currently executing user:
		Subject currentUser = SecurityUtils.getSubject();
		// do some stuff with a Session(no need for a web or EJB container!)
		Session session = currentUser.getSession();
		session.setAttribute("someKey", "aValue");
		String value = (String) session.getAttribute("someKey");
		if (value.equals("aValue")) {
			System.out.println("recived the correct value{" + value + "}");
		}
		// let's login the current user so we can check against roles and
		// permissions
		if (!currentUser.isAuthenticated()) {
			UsernamePasswordToken token = new UsernamePasswordToken("guest", "guest");
			token.setRememberMe(true);
			try {
				currentUser.login(token);
			} catch (UnknownAccountException uae) {
				System.out.println("There is no user with username of " + token.getPrincipal());
			} catch (IncorrectCredentialsException ice) {
				System.out.println("Password for account " + token.getPrincipal() + " was incorrect!");
			} catch (LockedAccountException lae) {
				System.out.println("The account for username" + token.getPrincipal() + "is locked."
						+ "Please contact your administrator to unlock it.");
			} catch (AuthenticationException e) {
				System.out.println(e.getMessage());
			}
		}

		// test a role
		if (currentUser.hasRole("guestRole")) {
			System.out.println("May be guestRole be with you.");
		} else {
			System.out.println("Hello, mere mortal");
		}
		// test a typed permission (not instance-level)
		if (currentUser.isPermitted("guest:weild")) {
			System.out.println("You may use a lightsaber ring. Use it wisely.");
		} else {
			System.out.println("Sorry, lightsaber rings are for schwartz masters only.");

		}
		// a (very powerful) Instance Level permission:
		if (currentUser.isPermitted("winnebago:drive:eagle5")) {
			System.out.println("You are permitted to 'drive' the winnebago with license plate (id) 'eagle5' . "
					+ "Here are the keys - have fun!");
		} else {
			System.out.println("Sorry, you aren't allowed to drive the 'eagle5' winnebago!");
		}
		// all done - log out!
		currentUser.logout();
		System.exit(0);
	}
}
