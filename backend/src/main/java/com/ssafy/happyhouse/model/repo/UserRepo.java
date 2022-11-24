package com.ssafy.happyhouse.model.repo;

import com.ssafy.happyhouse.model.dto.User;

public interface UserRepo {
	int insert(User user);
	
	User select(String id);
	int update(User user);
	int delete(String id);
}
