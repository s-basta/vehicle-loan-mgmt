package com.vehicleloan.dao.user;

import java.util.List;

public interface UserDAO {
	Integer create(User user);
	User get(Integer userId);
	User get(String username , String password);
	List<User> getAll();
	boolean update(User user);
	boolean deleteById(Integer userId);
}
