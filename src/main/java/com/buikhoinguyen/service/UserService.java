package com.buikhoinguyen.service;

import org.springframework.http.ResponseEntity;

import com.buikhoinguyen.dto.CustomerDTO;
import com.buikhoinguyen.dto.LoginDTO;
import com.buikhoinguyen.entity.Customer;


public interface UserService {
	public ResponseEntity<String> registerUser(Customer customer);
	
	public ResponseEntity<CustomerDTO> getAccount(String email);
	
	public ResponseEntity<CustomerDTO> editProfile(Customer customer);
	
	public ResponseEntity<CustomerDTO> authenticateUser(LoginDTO loginDto);
	
	public ResponseEntity<String> inActiveAccount(String email);
	
	public ResponseEntity<String> reActiveAccount(String email);
	
	public ResponseEntity<String> createAdmin(Customer customer);
}
