package com.buikhoinguyen.serviceimpl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.buikhoinguyen.dto.SizeShoesDTO;
import com.buikhoinguyen.entity.SizeShoes;
import com.buikhoinguyen.repository.SizeRepository;
import com.buikhoinguyen.service.SizeShoesService;

@Service
public class SizeShoesServiceImpl implements SizeShoesService{
	@Autowired
	private SizeRepository sizeRepository;

	@Override
	public ResponseEntity<String> createSizeShoes(SizeShoesDTO sizeShoesDTO) {
		SizeShoes sizeShoes = new SizeShoes();
		sizeShoes.setCreatedDate(LocalDateTime.now());
		sizeShoes.setName(sizeShoesDTO.getName());
		sizeShoes.setStatus(true);
		sizeShoes.setFormatSize(sizeShoesDTO.getFormatSize());
		sizeShoes.setSizeShoes(sizeShoesDTO.getSizeShoes());
		if(sizeRepository.save(sizeShoes) != null) {
			return new ResponseEntity<String>("Size of shoes is created ",HttpStatus.CREATED);
		}
		return new ResponseEntity<String>("Could not create size shoes",HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
}
