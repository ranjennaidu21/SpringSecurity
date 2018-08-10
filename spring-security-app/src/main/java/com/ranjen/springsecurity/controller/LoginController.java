package com.ranjen.springsecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
	
	//mapping for custom login form as based on DemoSecurityConfig
	@GetMapping("/showMyLoginPage")
	public String showMyLoginPage() {
		
		return "plain-login";
		
	}
}
