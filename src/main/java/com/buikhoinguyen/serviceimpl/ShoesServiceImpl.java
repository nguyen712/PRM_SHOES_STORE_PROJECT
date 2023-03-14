package com.buikhoinguyen.serviceimpl;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.imageio.ImageIO;
import javax.swing.text.html.Option;

import com.buikhoinguyen.dto.SizeShoesDTO;

import org.hibernate.cache.spi.support.AbstractReadWriteAccess.Item;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.buikhoinguyen.dto.ShoesDTO;
import com.buikhoinguyen.entity.Category;
import com.buikhoinguyen.entity.Image;
import com.buikhoinguyen.entity.Shoes;
import com.buikhoinguyen.entity.SizeShoes;
import com.buikhoinguyen.model.ResponseShoesData;
import com.buikhoinguyen.repository.CategoryRepository;
import com.buikhoinguyen.repository.ImageRepository;
import com.buikhoinguyen.repository.ShoesRepository;
import com.buikhoinguyen.repository.SizeRepository;
import com.buikhoinguyen.service.ShoesService;
@Service
public class ShoesServiceImpl implements ShoesService{
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private ShoesRepository shoesRepository;
	
	@Autowired
	private SizeRepository sizeRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ImageRepository imageRepository;
	
	@Override
	public ResponseEntity<String> createShoes(ShoesDTO shoesDTO) {
		Optional<SizeShoes> sizeIsExisted = sizeRepository.findById(shoesDTO.getSizeId());
		Optional<Category> categoryIsExisted = categoryRepository.findById(shoesDTO.getCategoryId());
		if(sizeIsExisted == null) {
			return new ResponseEntity<String>("shoes size id is not found " + shoesDTO.getSizeId(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(categoryIsExisted == null) {
			return new ResponseEntity<String>("category id is not found " + shoesDTO.getCategoryId(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		Shoes shoes = new Shoes();
		shoes.setQuanity(shoesDTO.getQuanity());
		shoes.setPrice(shoesDTO.getPrice());
		shoes.setName(shoesDTO.getName());
		shoes.setDiscount(shoesDTO.getDiscount());
		shoes.setDescription(shoesDTO.getDescription());
		shoes.setColors(shoesDTO.getColors());
		shoes.setCreatedDate(LocalDateTime.now());
		shoes.setSizeShoes(sizeIsExisted.get());
		shoes.setCategory(categoryIsExisted.get());
		
		if(shoesRepository.save(shoes) != null) {
			return new ResponseEntity<String>("Shoes is created " + shoes.getId(),HttpStatus.CREATED);
		}
		return new ResponseEntity<String>("Fail to creat shoes ",HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public List<ResponseShoesData> getAllShoes() {
		List<Shoes> shoesList = shoesRepository.findAll();
		List<ResponseShoesData> shoesRespond = new ArrayList<>();
		for (Shoes shoes : shoesList) {
			shoesRespond.add(new ResponseShoesData(shoes.getId(),
					shoes.getDescription(),
					shoes.getPrice(),
					shoes.getCategory().getName(),
					shoes.getSizeShoes().getFormatSize(),
					shoes.getSizeShoes().getSizeShoes(),
					shoes.getQuanity(),
					shoes.getDiscount(),
					shoes.getColors(),
					shoes.getName(),
					shoes.getImages()
					));
		}
		return shoesRespond;
	}

	@Override
	public ResponseEntity<String> updateShoes(long shoesId, ShoesDTO shoesDTO) {
		Optional<Shoes> shoesData = shoesRepository.findById(shoesId);
		Optional<Category> categoryData = categoryRepository.findById(shoesDTO.getCategoryId());
		Optional<SizeShoes> sizeShoesData = sizeRepository.findById(shoesDTO.getSizeId());
		List<String> shoesColor = new ArrayList<>();
		SizeShoes sizeShoes = new SizeShoes();
		Category category = new Category();
		if (sizeShoesData.isPresent()){
			sizeShoes = sizeShoesData.get();
		}else {
			sizeShoes = null;
		}

		if (categoryData.isPresent()){
			category = categoryData.get();
		}else {
			category = null;
		}
		if (shoesData.isPresent()) {
			if (category == null){
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Can not found category");
			}

			if (sizeShoes == null){
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Can not found size of shoes");
			}
			Shoes shoes = shoesData.get();
			shoes.setName(shoesDTO.getName());
			shoes.setPrice(shoesDTO.getPrice());
			shoes.setCategory(category);
			shoes.setSizeShoes(sizeShoes);
			shoes.setQuanity(shoesDTO.getQuanity());
			shoes.setDescription(shoesDTO.getDescription());
			shoes.setDiscount(shoesDTO.getDiscount());
			shoes.setStatus(shoesDTO.isStatus());
			for (String color : shoesDTO.getColors()) {
				shoesColor.add(color);
			}
			shoesRepository.save(shoes);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Update shoes successfully");
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Can not found shoes");
		}
	}

	@Override
	public boolean deleteShoes(long shoesId) {
		Optional<Shoes> shoesData = shoesRepository.findById(shoesId);
		if (shoesData.isPresent()) {
			Shoes shoes = shoesData.get();
			shoesRepository.delete(shoes);
			return true;
		}
			return false;
	}

}
