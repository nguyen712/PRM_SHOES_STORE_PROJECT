package com.buikhoinguyen.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class ProjectSecurityConfig {
	
	@SuppressWarnings("unused")
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Bean
	SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
		http.csrf().disable()
		.authorizeHttpRequests()
			.antMatchers("/inActiveAccount","/myInformation/**", "/editProfile", "/inActiveAccount/**").hasAnyAuthority("User", "Admin")
			.antMatchers("/createShoes/**", "/createCategory", "/upload/**", "/createSize").hasAuthority("Admin")
			.antMatchers( "/role","/roles","/updateRole/{roleId}", "/deleteRole/{roleId}").hasAuthority("Admin")
			.antMatchers("/categories", "/updateCategory/{cateId}", "/deleteCategory/{cateId}").hasAuthority("Admin")
			.antMatchers("/updateShoes/{shoesId}", "/deleteShoes/{shoesId}").hasAuthority("Admin")
			.antMatchers("/shoes", "/notices", "/contacts", "/register", "/signin", "/reActiveAccount/**", "/createAdmin").permitAll()
			.and().formLogin().loginProcessingUrl("/signin")
			.and().httpBasic();
		
		return http.build();
	}
	
	@Bean
    public AuthenticationManager authenticationManager(
                                 AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
