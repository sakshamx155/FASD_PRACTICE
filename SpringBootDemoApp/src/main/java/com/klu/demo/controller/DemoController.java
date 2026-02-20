package com.klu.demo.controller;
import org.springframework.web.bind.annotation.*;

@RestController
public class DemoController {
	@GetMapping("/hello")
	public String sayHello() {
		return "Ashu Kumar";
	}
}

