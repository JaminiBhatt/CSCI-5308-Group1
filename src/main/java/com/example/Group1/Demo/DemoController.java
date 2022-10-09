package com.example.Group1.Demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DemoController {
	
	@GetMapping("/")
	public String index() {
		return "hello";
	}
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
 	
}
