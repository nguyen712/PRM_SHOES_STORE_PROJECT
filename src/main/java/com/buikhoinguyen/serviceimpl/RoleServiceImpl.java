package com.buikhoinguyen.serviceimpl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.buikhoinguyen.entity.Role;
import com.buikhoinguyen.repository.RoleRepository;
import com.buikhoinguyen.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService{
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Override
	public ResponseEntity<String> addRole(Role role) {
		Role roleExisted = roleRepository.findByName(role.getName());
		role.setCreatedDate(LocalDateTime.now());
		if(roleExisted != null) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Role can not be duplicated");
		}
		roleRepository.save(role);
		return ResponseEntity.status(HttpStatus.CREATED).body("Role is register.");
	}

}
