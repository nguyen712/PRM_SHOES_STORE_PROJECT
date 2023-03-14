package com.buikhoinguyen.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "size")
@Getter
@Setter
public class SizeShoes extends BasedEntity{
	
	@Column
	private String formatSize;
	
	@Column
	private float sizeShoes;
	
	@OneToMany(mappedBy = "sizeShoes")
	private List<Shoes> shoes = new ArrayList<>();
}
