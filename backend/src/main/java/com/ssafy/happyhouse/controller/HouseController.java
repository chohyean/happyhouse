package com.ssafy.happyhouse.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class HouseController {
	@GetMapping("/")
	public String hello() {
		log.debug("index 호출됨");
		return "index";
	}
	
	@GetMapping("/house")
	public String house() {
		return "/home/deal";
	}
	

}
