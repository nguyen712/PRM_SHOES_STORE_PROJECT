package com.buikhoinguyen.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.buikhoinguyen.entity.Image;
import com.buikhoinguyen.model.ResponseData;
import com.buikhoinguyen.service.ImageService;

@RestController
public class ImageController {
	
	@Autowired
	private ImageService imageService;
	
	@PostMapping("/upload")
	private ResponseEntity<ResponseData> uploadImage(@RequestParam long shoesId, @RequestParam("file") MultipartFile file) throws Exception{
		Image image = null;
		image = imageService.uploadImage(shoesId, file);
		if(image != null) {
			return new ResponseEntity<ResponseData>(new ResponseData(image.getFileType(), image.getName(), file.getSize()), HttpStatus.CREATED);
		}
		return null;
	}
	
	
}
