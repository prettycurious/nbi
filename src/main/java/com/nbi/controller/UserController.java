package com.nbi.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.nbi.util.ResponseUtil;
import com.nbi.util.PswUtil;

import net.sf.json.JSONObject;

import com.nbi.entity.User;

import com.nbi.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		// setLenient方法是用来控制是否将正确格式的不正确时间转化成正确的时间.
		// 默认为true,设置为false时,如果时间不正确,不会自动转化,而是抛出java.text.ParseException异常.
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true)); // true:允许输入空值，false:不能为空值
	}

	@RequestMapping(value = "/deliverysession", method = RequestMethod.POST)
	public String login(@RequestParam(value = "id", required = false) Integer id, User user, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String name = user.getName();
		String psw = user.getPsw();
		System.out.println(name);
		System.out.println(psw);
		System.out.println(user);
		JSONObject result = new JSONObject();
		System.out.println("用户登陆权限验证");
		Subject currentUser = SecurityUtils.getSubject();
		if (!currentUser.isAuthenticated()) {
			// 把用户名和密码封装为 UsernamePasswordToken 对象
			UsernamePasswordToken token = new UsernamePasswordToken(name, psw);
			// rememberme
			token.setRememberMe(false);
			try {
				// 执行登录.
				currentUser.login(token);
				System.out.println("登陆成功");
				result.put("status", 100);
				result.put("message", "登陆成功");
				String url = request.getContextPath() + "/main.jsp";
				System.out.println(url);
				// 跳转地址
				result.put("back_url", url);
				// 设置session
				Map<String, Object> map = new HashMap<String, Object>();
				User resultUser = userService.loginShiro(name);
				// System.out.println(resultUser);

				// session的使用
				HttpSession session = request.getSession();
				session.setAttribute("loginUser", resultUser);
				session.setMaxInactiveInterval(10);//以秒为单位 

			}
			// 若没有指定的账户, 则 shiro 将会抛出 UnknownAccountException 异常.
			catch (UnknownAccountException uae) {
				System.out.println("登录失败: " + uae.getMessage());
				result.put("status", 200);
				result.put("message", uae.getMessage());
			}
			// 若账户存在, 但密码不匹配, 则 shiro 会抛出 IncorrectCredentialsException 异常。
			catch (IncorrectCredentialsException ice) {
				System.out.println("登录失败: 密码不正确！");
				result.put("status", 200);
				result.put("message", "密码不正确！");
			}
			// ... catch more exceptions here (maybe custom ones specific to
			// your application?
			// 所有认证时异常的父类.
			catch (AuthenticationException ae) {
				// unexpected condition? error?
				System.out.println("登录失败: " + ae.getMessage());
				result.put("status", 200);
				result.put("message", ae.getMessage());
			}
		} else {
			result.put("status", 100);
			result.put("message", "已经登陆");
			// 跳转地址
			String url = request.getContextPath() + "/main.jsp";
			System.out.println(url);
			result.put("back_url", url);
		}
		ResponseUtil.write(response, result);
		return null;
	}

}
