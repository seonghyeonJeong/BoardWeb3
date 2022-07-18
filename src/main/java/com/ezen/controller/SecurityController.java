package com.ezen.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/system")
public class SecurityController {


	@GetMapping("/login")
	public void login( ) {
	}
	

	@PostMapping("/loginSuccess")
	public String loginSuccess() {
		return "redirect:/board/getBoardList";
	}
	
	
	@GetMapping("/accessDenied")
	public void accessDenied() {
	}
	
	@GetMapping("/logout")
	public String logout() {
		return "/";
	}
}
