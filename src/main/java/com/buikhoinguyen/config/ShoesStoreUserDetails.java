package com.buikhoinguyen.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.buikhoinguyen.entity.Customer;
import com.buikhoinguyen.repository.CustomerRepository;



@Service
public class ShoesStoreUserDetails implements UserDetailsService{
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		String userName , password = null;
		List<GrantedAuthority> authorities = null;
		List<Customer> user = customerRepository.findByEmail(username);
		if(user.size() == 0) {
			throw new UsernameNotFoundException("User details not found for the user " + user);
		}else {
			userName = user.get(0).getEmail();
			password = user.get(0).getPwd();
			authorities = new ArrayList<>();
			authorities.add(new SimpleGrantedAuthority(user.get(0).getRole().getName()));
			
		}
		return new User(userName, password, authorities);
	}

}
