package com.ssafy.happyhouse.model.service;

import com.ssafy.happyhouse.model.dto.User;


public interface UserService {
	int insert(User user);
	User login(String id,String pass);
	boolean existUser(String id);
	User select(String id);
	int update(User user);
	int delete(String id);
}
