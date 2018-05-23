package com.nbi.dao;

import java.util.Map;

import com.nbi.entity.User;

public interface UserDao {

	public User loginShiro(Map<String, Object> map);

}
