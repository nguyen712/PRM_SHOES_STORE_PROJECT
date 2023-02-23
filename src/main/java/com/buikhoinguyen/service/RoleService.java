package com.buikhoinguyen.service;

import org.springframework.http.ResponseEntity;

import com.buikhoinguyen.entity.Role;


public interface RoleService {
	
	
	public ResponseEntity<String> addRole(Role role);
}
