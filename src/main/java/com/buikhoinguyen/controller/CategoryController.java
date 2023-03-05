package com.buikhoinguyen.controller;

import com.buikhoinguyen.dto.CategoryDTO;
import com.buikhoinguyen.entity.Role;
import com.buikhoinguyen.model.ResponseCategoryData;
import com.buikhoinguyen.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.buikhoinguyen.entity.Category;
import com.buikhoinguyen.service.CategoryService;

import java.util.List;

@RestController
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	
	@PostMapping("/createCategory")
	public ResponseEntity<String> createCategory(@RequestBody Category category){
		return categoryService.createCategory(category);
	}

	@GetMapping("/categories")
	public List<ResponseCategoryData> getCategories() {
		List<ResponseCategoryData> categoriesResponse = categoryService.getCategories();
		return categoriesResponse;
	}

	@PutMapping("/updateCategory/{cateId}")
	public ResponseEntity<String> updateCategory(@PathVariable("cateId") long cateId, @RequestBody CategoryDTO categoryDTO){
		return categoryService.updateCategory(cateId, categoryDTO);
	}

	@DeleteMapping("/deleteCategory/{cateId}")
	public ResponseEntity<String> deleteCategory(@PathVariable("cateId") long cateId) {
		boolean categoryIsDelete = categoryService.deleteCategory(cateId);
		if (categoryIsDelete) {
			return ResponseEntity.status(HttpStatus.OK).body("Delete successfully !");
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Delete failed!");
		}
	}
}
