package com.buikhoinguyen.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.buikhoinguyen.entity.Role;
import com.buikhoinguyen.service.RoleService;

@RestController
public class RoleController {
	@Autowired
	private RoleService roleService;
	
	@PostMapping("/role")
	public ResponseEntity<String> addRole(@RequestBody Role role){
		return roleService.addRole(role);
	}
}
