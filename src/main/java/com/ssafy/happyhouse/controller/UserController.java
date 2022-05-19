package com.ssafy.happyhouse.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ssafy.happyhouse.model.dto.User;
import com.ssafy.happyhouse.model.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/user")
@Slf4j
public class UserController {
	
	@Autowired
	UserService uService;
	
	@PostMapping("/login")
	public String login(@ModelAttribute User user,HttpSession session,RedirectAttributes redir,Model model) {
		User loginUser = uService.login(user.getId(), user.getPw());
		if(loginUser!=null) {
			session.setAttribute("loginUser", loginUser);
			redir.addFlashAttribute("msg","로그인 성공");
			return "redirect:/";
		}else {
			model.addAttribute("msg","id/pass 확인!!");
			return "index";
		}
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	
	@GetMapping("/registerForm")
	public String joinForm() {
		return "regist/registerForm";
	}
	
	@PostMapping("/regist")
	public String join(@ModelAttribute User user,RedirectAttributes redir) {
		int result= uService.insert(user);
		redir.addFlashAttribute("msg","회원 가입 완료!! 로그인 후 사용");
		return "redirect:/";
	}
	
	@GetMapping("/info")
	public String select(HttpSession session){
		User user= (User) session.getAttribute("loginUser");
		User loginUser = uService.select(user.getId());
	
		log.debug("loginUser: {}", loginUser);
		session.setAttribute("user", loginUser);
		return "/user/info";
	}
	
	@GetMapping("/infoupdate")
	public String updateinfo2(HttpSession session) {
		return "/user/infoupdate";
	}
	
	@PostMapping("/info")
	public String updateinfo(HttpSession session) {
		return "redirect:/user/infoupdate";
	}
	
	
	@PostMapping("/update")
	public String update(@ModelAttribute User user,HttpSession session) {
		User loginUser=(User) session.getAttribute("loginUser");
		user.setId(loginUser.getId());
		log.debug("user: {}", user);
		
		int result= uService.update(user);
		
		return "redirect:/user/info";
	}
	
	@PostMapping("/resign")
	public String delete(@ModelAttribute User user,HttpSession session,RedirectAttributes redir) {
		User loginUser=(User) session.getAttribute("loginUser");
		user.setId(loginUser.getId());
		log.debug("user: {}", user);
		int result= uService.delete(user.getId());
		redir.addFlashAttribute("msg","탈퇴하셨습니다.");
		session.invalidate();
		return "redirect:/";
	}
	
}
