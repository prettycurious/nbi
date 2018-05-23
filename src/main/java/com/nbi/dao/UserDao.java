package com.nbi.dao;

import java.util.List;
import java.util.Map;

import com.nbi.entity.User;

public interface UserDao {

	public int add(User user);

	public int delete(Integer id);

	public int update(User user);

	public List<User> find(Map<String, Object> map);

	public User loginShiro(Map<String, Object> map);

}
