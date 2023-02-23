package com.buikhoinguyen.serviceimpl;


import java.time.LocalDateTime;
import java.util.Optional;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.buikhoinguyen.entity.Image;
import com.buikhoinguyen.entity.Shoes;
import com.buikhoinguyen.repository.ImageRepository;
import com.buikhoinguyen.repository.ShoesRepository;
import com.buikhoinguyen.service.ImageService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ImageServiceImpl implements ImageService{
	
	@Autowired
	private ShoesRepository shoesRepository;
	
	@Autowired
	private ImageRepository imageRepository;
	
	@Override
	public Image uploadImage(long shoesId, MultipartFile file) throws Exception {
		
		Optional<Shoes> shoesIsExisted = shoesRepository.findById(shoesId);
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		if(shoesIsExisted.isPresent()) {
			
			try {
				if(fileName.contains("..")) {
					throw new Exception("Filename contain invalid path sequece " + fileName);
				}
				
				Image image = new Image(file.getBytes() , fileName, shoesIsExisted.get());
				image.setCreatedDate(LocalDateTime.now());
				image.setName(fileName);
				image.setStatus(true);
				return imageRepository.save(image);
			} catch (Exception e) {
				throw new Exception("Could not save File " + fileName);
			}
			
		}
		log.warn("Shoes id is not existed: " + shoesId +". Can not Save Image");
		return null;
	}
}
