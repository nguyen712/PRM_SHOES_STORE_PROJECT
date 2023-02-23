package com.buikhoinguyen.service;

import org.springframework.http.ResponseEntity;

import com.buikhoinguyen.dto.SizeShoesDTO;

public interface SizeShoesService {
	public ResponseEntity<String> createSizeShoes(SizeShoesDTO sizeShoesDTO);
}
