package com.nbi.shiro.realms;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.nbi.entity.User;
import com.nbi.service.UserService;

public class ShiroRealm extends AuthorizingRealm {

	@Autowired
	private UserService userService;

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		System.out.println("-->doGetAuthenticationInfo");

		// 1. 把 AuthenticationToken 转换为 UsernamePasswordToken
		UsernamePasswordToken upToken = (UsernamePasswordToken) token;

		// 2. 从 UsernamePasswordToken 中来获取 username
		String username = upToken.getUsername();
		String password = new String(upToken.getPassword());
		System.out.println("用户输入密码：" + password);

		// 3. 调用数据库的方法, 从数据库中查询 username 对应的用户记录
		User user = userService.loginShiro(username);
		System.out.println("从数据库中获取 username: " + username + " 所对应的用户信息.");

		// 4. 若用户不存在, 则可以抛出 UnknownAccountException 异常
		if (user != null) {
			// 5. 根据用户信息的情况, 决定是否需要抛出其他的 AuthenticationException 异常.
			// 6. 根据用户的情况, 来构建 AuthenticationInfo 对象并返回. 通常使用的实现类为:
			// SimpleAuthenticationInfo
			// 以下信息是从数据库中获取的.
			// 1). principal: 认证的实体信息. 可以是 username, 也可以是数据表对应的用户的实体类对象.
			Object principal = user;
			// 2). credentials: 密码.
			Object credentials = user.getPsw();
			// 3). realmName: 当前 realm 对象的 name. 调用父类的 getName() 方法即可
			String realmName = getName();
			// 4). 盐值.
			ByteSource credentialsSalt = ByteSource.Util.bytes(username);

			return new SimpleAuthenticationInfo(principal, credentials, credentialsSalt, realmName);
		}
		throw new UnknownAccountException("用户不存在");
	}

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// TODO Auto-generated method stub
		return null;
	}

}
