package com.buikhoinguyen.service;

import com.buikhoinguyen.dto.CategoryDTO;
import com.buikhoinguyen.model.ResponseCategoryData;
import org.springframework.http.ResponseEntity;

import com.buikhoinguyen.entity.Category;

import java.util.List;

public interface CategoryService {
	public ResponseEntity<String> createCategory(Category category);
	public List<ResponseCategoryData> getCategories();
	public ResponseEntity<String> updateCategory(long cateId, CategoryDTO categoryDTO);
	public boolean deleteCategory(long cateId);
}
