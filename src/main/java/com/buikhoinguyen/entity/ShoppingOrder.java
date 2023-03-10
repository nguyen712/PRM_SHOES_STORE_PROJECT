package com.buikhoinguyen.entity;

import java.time.LocalDate;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class ShoppingOrder {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	private Long id;
	
	@Column(nullable = true)
	private String name;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;
	
	@Column(nullable = true)
    @CreatedDate
    private LocalDate createdDate;

    @Column(nullable = true)
    @LastModifiedBy
    private String modifiedBy;

    @Column(nullable = true)
    @LastModifiedDate
    private LocalDate modifiedDate;
    
    @Column
	private boolean status;
    
    
}
