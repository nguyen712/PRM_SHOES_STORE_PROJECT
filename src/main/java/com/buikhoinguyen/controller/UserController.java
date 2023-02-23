package com.buikhoinguyen.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.buikhoinguyen.dto.CustomerDTO;
import com.buikhoinguyen.dto.LoginDTO;
import com.buikhoinguyen.entity.Customer;
import com.buikhoinguyen.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;
	
	@PostMapping("/register")
	public ResponseEntity<String> registerUser(@RequestBody Customer customer){
		ResponseEntity<String> response = userService.registerUser(customer);
		return response;
	}
	
	@PostMapping("/createAdmin")
	public ResponseEntity<String> createAdmin(@RequestBody Customer customer){
		ResponseEntity<String> response = userService.createAdmin(customer);
		return response;
	}
	
	@GetMapping("/myInformation")
	public ResponseEntity<CustomerDTO> getAccount(@RequestParam String email) {
		ResponseEntity<CustomerDTO> response = userService.getAccount(email);
		return response;
	}
	
	
	@PutMapping("/editProfile")
	public ResponseEntity<CustomerDTO> editProfile(@RequestBody Customer customer){
		return userService.editProfile(customer);
	}
	
	@PostMapping("/signin")
	public ResponseEntity<CustomerDTO> authenticateUser(@RequestBody LoginDTO loginDto){
		return userService.authenticateUser(loginDto);
    } 
	
	@PostMapping("/inActiveAccount")
	public ResponseEntity<String> inActiveAccount(@RequestParam String email){
		return userService.inActiveAccount(email);
	}
	
	@PostMapping("/reActiveAccount")
	public ResponseEntity<String> reActiveAccount(@RequestParam String email){
		return userService.reActiveAccount(email);
	}
}
