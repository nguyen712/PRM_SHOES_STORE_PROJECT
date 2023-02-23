package com.buikhoinguyen.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.buikhoinguyen.dto.SizeShoesDTO;
import com.buikhoinguyen.service.SizeShoesService;

@RestController
public class SizeShoesController {
	
	@Autowired
	private SizeShoesService sizeShoesService;
	
	@PostMapping("/createSize")
	public ResponseEntity<String> createSizeShoes(@RequestBody SizeShoesDTO sizeShoesDTO){
		return sizeShoesService.createSizeShoes(sizeShoesDTO);
	}
}
