package com.buikhoinguyen.serviceimpl;


import java.time.LocalDateTime;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.buikhoinguyen.dto.CustomerDTO;
import com.buikhoinguyen.dto.LoginDTO;
import com.buikhoinguyen.entity.Customer;
import com.buikhoinguyen.entity.Role;
import com.buikhoinguyen.repository.CustomerRepository;
import com.buikhoinguyen.repository.RoleRepository;
import com.buikhoinguyen.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public ResponseEntity<String> registerUser(Customer customer) {
		Customer savedCustomer = null;
		ResponseEntity<String> response = null;
		try {
			List<Customer> customerIsExisted = customerRepository.findByEmail(customer.getEmail());
			if (customerIsExisted.size() > 0) {
				return response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
						.body("An exception occured due to email is existed");
			}
			Role userRole = roleRepository.findByName("User");
			if(userRole != null) {
				String hashPwd = passwordEncoder.encode(customer.getPwd());
				customer.setPwd(hashPwd);
				customer.setRole(userRole);
				customer.setCreatedDate(LocalDateTime.now());
				customer.setCreatedBy(customer.getName());
				customer.setStatus(true);
				savedCustomer = customerRepository.save(customer);
				if (savedCustomer != null) {
					response = ResponseEntity.status(HttpStatus.CREATED)
							.body("Given user details are successfullty registered");
				}
			}
			return response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("An exception occured due to user role is not existed");
		} catch (Exception ex) {
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("An exception occured due to " + ex.getMessage());
		}
		return response;
	}

	@Override
	public ResponseEntity<CustomerDTO> getAccount(String email) {
		List<Customer> customer = customerRepository.findByEmailAndStatus(email, true);
		CustomerDTO res = modelMapper.map(customer.get(0), CustomerDTO.class);
		if(res != null && customer.size() > 0) {
			return ResponseEntity.status(HttpStatus.OK).body(res);
		}
		
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	}

	@Override
	public ResponseEntity<CustomerDTO> editProfile(Customer customer) {
		List<Customer> customerExisted = customerRepository.findByEmail(customer.getEmail());
		if(customerExisted.size() > 0) {
			customerExisted.get(0).setName(customer.getName());
			String hashPwd = passwordEncoder.encode(customer.getPwd());
			customerExisted.get(0).setPwd(hashPwd);
			customerExisted.get(0).setGender(customer.getGender());
			customerExisted.get(0).setDateOfBirth(customer.getDateOfBirth());
			customerExisted.get(0).setIdentifyNumber(customer.getIdentifyNumber());
			customerExisted.get(0).setPhone(customer.getPhone());
			customerExisted.get(0).setEmail(customer.getEmail());
			customerExisted.get(0).setAddress(customer.getAddress());
			customerExisted.get(0).setModifiedBy(customer.getName());
			customerExisted.get(0).setModifiedDate(LocalDateTime.now());
			Customer customerUpdate = customerRepository.save(customerExisted.get(0));
			CustomerDTO res = modelMapper.map(customerUpdate, CustomerDTO.class);
			return ResponseEntity.status(HttpStatus.OK).body(res);
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	}

	@Override
	public ResponseEntity<CustomerDTO> authenticateUser(LoginDTO loginDto) {
		
		List<Customer> customer = customerRepository.findByEmailAndStatus(loginDto.getUsernameOrEmail(), true);
		if(customer.size() > 0) {
			Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
	                loginDto.getUsernameOrEmail(), loginDto.getPassword()));

	        SecurityContextHolder.getContext().setAuthentication(authentication);
	        CustomerDTO res = modelMapper.map(customer, CustomerDTO.class);
	        return new ResponseEntity<CustomerDTO>(res, HttpStatus.OK);
		}
		
		return new ResponseEntity<CustomerDTO>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<String> inActiveAccount(String email) {
		List<Customer> customer = customerRepository.findByEmail(email);
		if(customer.size() > 0) {
			customer.get(0).setStatus(false);
			customerRepository.save(customer.get(0));
			return new ResponseEntity<String>("User is inactive account", HttpStatus.OK);
		}
		return new ResponseEntity<String>("User is not existed", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<String> reActiveAccount(String email) {
		List<Customer> customer = customerRepository.findByEmail(email);
		if(customer.size() > 0) {
			customer.get(0).setStatus(true);
			customerRepository.save(customer.get(0));
			return new ResponseEntity<String>("User is active account", HttpStatus.OK);
		}
		return new ResponseEntity<String>("User is not existed", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<String> createAdmin(Customer customer) {
		Customer savedCustomer = null;
		ResponseEntity<String> response = null;
		try {
			List<Customer> customerIsExisted = customerRepository.findByEmail(customer.getEmail());
			if (customerIsExisted.size() > 0) {
				return response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
						.body("An exception occured due to email is existed");
			}
			Role userRole = roleRepository.findByName("Admin");
			String hashPwd = passwordEncoder.encode(customer.getPwd());
			customer.setPwd(hashPwd);
			customer.setRole(userRole);
			customer.setCreatedDate(LocalDateTime.now());
			customer.setCreatedBy(customer.getName());
			customer.setStatus(true);
			savedCustomer = customerRepository.save(customer);
			if (savedCustomer != null) {
				response = ResponseEntity.status(HttpStatus.CREATED)
						.body("Given Admin details are successfullty registered");
			}
		} catch (Exception ex) {
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("An exception occured due to " + ex.getMessage());
		}
		return response;
	}
	
	
	
}
