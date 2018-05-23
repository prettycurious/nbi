package com.nbi.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nbi.dao.UserDao;
import com.nbi.entity.User;
import com.nbi.service.UserService;

@Service("UserService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Override
	public User loginShiro(String username) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", username);
		User user = userDao.loginShiro(map);
		return user;
	}

}
