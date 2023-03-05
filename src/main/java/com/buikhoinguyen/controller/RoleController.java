package com.buikhoinguyen.controller;

import com.buikhoinguyen.model.ResponseRoleData;
import com.buikhoinguyen.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.buikhoinguyen.entity.Role;
import com.buikhoinguyen.service.RoleService;

import java.util.List;

@RestController
public class RoleController {
	@Autowired
	private RoleService roleService;

	@GetMapping("/roles")
	public List<ResponseRoleData> getRole() {
		List<ResponseRoleData> allRoleData = roleService.getRole();
		return allRoleData;
	}

	@PutMapping("/updateRole/{roleId}")
	public ResponseEntity<String> updateRole(@PathVariable("roleId") long roleId, @RequestBody Role roleRequest){
		return roleService.updateRole(roleId, roleRequest);
	}

	@PostMapping("/role")
	public ResponseEntity<String> addRole(@RequestBody Role role){
		return roleService.addRole(role);
	}

	@DeleteMapping("/deleteRole/{roleId}")
	public ResponseEntity<String> deleteRole(@PathVariable("roleId") long roleId){
		boolean isDelete = roleService.deleteRole(roleId);
		if (isDelete) {
			return ResponseEntity.status(HttpStatus.OK).body("Delete successfully !");
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Delete failed !!!");
		}
	}
}
