package com.buikhoinguyen.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.buikhoinguyen.dto.ShoesDTO;
import com.buikhoinguyen.entity.Shoes;
import com.buikhoinguyen.model.ResponseShoesData;
import com.buikhoinguyen.service.ShoesService;

@RestController
public class ShoesController {

	@Autowired
	private ShoesService shoesService;
	
	@GetMapping("/shoes")
	public List<ResponseShoesData> getShoes() {
		List<ResponseShoesData> responseData = shoesService.getAllShoes();
		return responseData;
	}
	
	@PostMapping("/createShoes")
	public ResponseEntity<String> createShoes(@RequestBody ShoesDTO shoes) {
		return shoesService.createShoes(shoes);
	}
}
