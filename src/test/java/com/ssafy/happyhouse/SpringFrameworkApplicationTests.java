package com.ssafy.happyhouse;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ssafy.happyhouse.model.repo.UserRepo;

@SpringBootTest
class SpringFrameworkApplicationTests {

	@Autowired
	UserRepo repo;
	
	@Test
	void contextLoads() {
		
		
	}

}
