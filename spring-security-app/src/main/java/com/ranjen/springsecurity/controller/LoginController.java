package com.ranjen.springsecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

	// mapping for custom login form as based on DemoSecurityConfig
	@GetMapping("/showMyLoginPage")
	public String showMyLoginPage() {

		// for normal login page without bootstrap css
		// return "plain-login";

		return "fancy-login";
	}

	// add request mapping for custom page access denied
	// add request mapping for /access-denied

	@GetMapping("/access-denied")
	public String showAccessDenied() {

		return "access-denied";

	}

}
