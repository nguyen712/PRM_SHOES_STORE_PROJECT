package com.buikhoinguyen.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

@Entity
public class Category extends BasedEntity{
	@OneToMany(mappedBy = "category")
	@JsonIgnore
	private List<Shoes> shoes = new ArrayList<>();
}
