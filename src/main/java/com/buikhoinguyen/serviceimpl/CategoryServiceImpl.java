package com.buikhoinguyen.serviceimpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.buikhoinguyen.dto.CategoryDTO;
import com.buikhoinguyen.model.ResponseCategoryData;
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

	@Override
	public List<ResponseCategoryData> getCategories() {
		List<Category> categoryData = categoryRepository.findAll();
		List<ResponseCategoryData> responseCategory = new ArrayList<>();
		for (Category category : categoryData) {
			ResponseCategoryData rspCate = new ResponseCategoryData();
			rspCate.setCateId(category.getId());
			rspCate.setCateName(category.getName());
			rspCate.setCateStatus(category.isStatus());
			responseCategory.add(rspCate);
		}
		return responseCategory;
	}

	@Override
	public ResponseEntity<String> updateCategory(long cateId, CategoryDTO categoryDTO) {
		Optional<Category> categoryData = categoryRepository.findById(cateId);
		if (categoryData.isPresent()) {
			Category cate = categoryData.get();
			cate.setName(categoryDTO.getCateName());
			cate.setStatus(categoryDTO.isStatus());
			categoryRepository.save(cate);
			return ResponseEntity.status(HttpStatus.OK).body("Update is successfully !");
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Can not find ID !!");
		}
	}

	@Override
	public boolean deleteCategory(long cateId) {
		Optional<Category> cateData = categoryRepository.findById(cateId);
		if (cateData.isPresent()) {
			Category cate = cateData.get();
			categoryRepository.delete(cate);
			return true;
		} else {
			return false;
		}

	}

}
