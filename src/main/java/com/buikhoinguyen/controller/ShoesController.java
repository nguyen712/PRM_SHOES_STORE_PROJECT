package com.buikhoinguyen.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.buikhoinguyen.dto.ShoesDTO;
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

	@PutMapping("/updateShoes/{shoesId}")
	public ResponseEntity<String> updateShoes(@PathVariable("shoesId") long shoesId, @RequestBody ShoesDTO shoesDTO) {
		return shoesService.updateShoes(shoesId, shoesDTO);
	}

	@DeleteMapping("/deleteShoes/{shoesId}")
	public ResponseEntity<String> deleteShoes(@PathVariable("shoesId") long shoesId) {
		boolean shoesIsDelete = shoesService.deleteShoes(shoesId);
		if (shoesIsDelete) {
			return ResponseEntity.status(HttpStatus.OK).body("Delete Shoes successfully!");
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Delete failed!!!");
		}
	}
}
