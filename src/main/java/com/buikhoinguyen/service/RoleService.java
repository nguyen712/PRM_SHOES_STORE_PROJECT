package com.buikhoinguyen.service;

import com.buikhoinguyen.model.ResponseRoleData;
import org.springframework.http.ResponseEntity;

import com.buikhoinguyen.entity.Role;

import java.util.List;


public interface RoleService {
	
	
	public ResponseEntity<String> addRole(Role role);
	public List<ResponseRoleData> getRole();
	public ResponseEntity<String> updateRole(Long roleId, Role roleRequest);
	public boolean deleteRole(Long roleId);
}
