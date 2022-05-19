package com.ssafy.happyhouse.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.happyhouse.model.dto.User;
import com.ssafy.happyhouse.model.repo.UserRepo;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepo uRepo;

	@Override
	@Transactional
	public int insert(User user) {
		return uRepo.insert(user);
	}

	@Override
	public boolean existUser(String id) {
		return uRepo.select(id)!=null;
	}

	@Override
	public User login(String id, String pass) {
		User user =uRepo.select(id);
		if(user!=null && user.getPw().equals(pass)) {
			return user;
		}else {
			return null;
		}
	}

	@Override
	@Transactional
	public User select(String id) {
		return uRepo.select(id);
	}

	@Override
	@Transactional
	public int update(User user) {
		return uRepo.update(user);
	}

	@Override
	@Transactional
	public int delete(String id) {
		return uRepo.delete(id);
	}
	
	
}
