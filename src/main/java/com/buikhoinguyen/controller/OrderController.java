package com.buikhoinguyen.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class OrderController {
	
	@GetMapping("/myOrder")
	public String getOrder() {
		return "My Order";
	}
}
