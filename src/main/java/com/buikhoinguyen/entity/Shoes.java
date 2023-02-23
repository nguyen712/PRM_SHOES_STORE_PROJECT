package com.buikhoinguyen.entity;



import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Shoes extends BasedEntity{
	
	@Column
	private String description;
	
	@Column
	private double price;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "size_id")
	private SizeShoes sizeShoes;
	
	@Column
	private Long quanity;
	
	@Column
	private double discount;
	
	@Column
	private String[] colors;
	
	@OneToMany(mappedBy = "shoes")
	@JsonIgnore
	private List<Image> images = new ArrayList<>();
}
