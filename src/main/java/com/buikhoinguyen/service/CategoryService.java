package com.buikhoinguyen.service;

import org.springframework.http.ResponseEntity;

import com.buikhoinguyen.entity.Category;

public interface CategoryService {
	public ResponseEntity<String> createCategory(Category category);
}
