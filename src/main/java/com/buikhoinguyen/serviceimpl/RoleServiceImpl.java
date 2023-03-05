package com.buikhoinguyen.serviceimpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.buikhoinguyen.model.ResponseRoleData;
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

	@Override
	public List<ResponseRoleData>getRole(){
		List<Role> roles = roleRepository.findAll();
		List<ResponseRoleData> responseRoleDatas = new ArrayList<>();
		for (Role role : roles ){
			ResponseRoleData responseRoleData = new ResponseRoleData();
			responseRoleData.setRoleId(role.getId());
			responseRoleData.setRoleName(role.getName());
			responseRoleData.setStatus(role.isStatus());
			responseRoleDatas.add(responseRoleData);
		}
			return responseRoleDatas;
	}

	@Override
	public ResponseEntity<String> updateRole(Long roleId, Role roleRequest) {
		Optional<Role> roleData = roleRepository.findById(roleId);
		if (roleData.isPresent()){
			Role role = roleData.get();
			role.setName(roleRequest.getName());
			role.setStatus(roleRequest.isStatus());
			roleRepository.save(role);
			return  ResponseEntity.status(HttpStatus.OK).body("Update role successfully");
		}else {
			return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("Can not found role ID");
		}


	}

	@Override
	public boolean deleteRole(Long roleId) {
		Optional<Role> roleData = roleRepository.findById(roleId);
		if (roleData.isPresent()){
			Role role = roleData.get();
			roleRepository.delete(role);
			return true;
		}else {
			return false;
		}

	}

}
