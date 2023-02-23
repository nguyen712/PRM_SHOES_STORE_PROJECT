package com.buikhoinguyen.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.buikhoinguyen.dto.ShoesDTO;
import com.buikhoinguyen.entity.Shoes;
import com.buikhoinguyen.model.ResponseShoesData;

public interface ShoesService {
	public ResponseEntity<String> createShoes(ShoesDTO shoesDTO);

	public List<ResponseShoesData> getAllShoes();
}
