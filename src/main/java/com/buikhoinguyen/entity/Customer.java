package com.buikhoinguyen.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Customer extends BasedEntity{

	@Column
	private String email;
	@Column
	private String pwd;
	
	@Column(nullable = true)
	private String address;
	
	@Column(length = 20, nullable = true)
	private String phone;
	
	@Column(length = 10, nullable = true)
	private String gender;
	
	@Column(length = 20, nullable = true)
	private LocalDate dateOfBirth;
	
	@Column(length = 20, nullable = true)
	private String identifyNumber;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "role_id")
	private Role role;
	
	
	@OneToMany(mappedBy = "customer")
	@JsonIgnore
	private List<ShoppingOrder> order = new ArrayList<>();
	
	
	
}
