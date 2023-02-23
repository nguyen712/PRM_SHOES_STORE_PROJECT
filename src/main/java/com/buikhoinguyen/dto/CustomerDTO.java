package com.buikhoinguyen.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class CustomerDTO {
	private String email;
	
	private String name;
	
	private String address;
	
	private String phone;
	
	private String gender;
	
	private LocalDate dateOfBirth;
	
	private String identifyNumber;
}
