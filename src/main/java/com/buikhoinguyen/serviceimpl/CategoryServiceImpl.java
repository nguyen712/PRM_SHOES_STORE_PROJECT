package com.buikhoinguyen.serviceimpl;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.buikhoinguyen.entity.Category;
import com.buikhoinguyen.repository.CategoryRepository;
import com.buikhoinguyen.service.CategoryService;
@Service
public class CategoryServiceImpl implements CategoryService{
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	
	@Override
	public ResponseEntity<String> createCategory(Category category) {
		category.setCreatedDate(LocalDateTime.now());
		category.setStatus(true);
		Optional<Category> categoryOptional = categoryRepository.findByName(category.getName());
		if(categoryOptional.isPresent()) {
			return new ResponseEntity<String>("Category is existed", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if(categoryRepository.save(category) != null) {
			return new ResponseEntity<String>("Create successfully", HttpStatus.CREATED);
		}
		return new ResponseEntity<String>("Create is not successfully", HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
