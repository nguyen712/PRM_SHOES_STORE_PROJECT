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
		List<Shoes> shoes = shoesRepository.findAll();
		List<ResponseShoesData> shoesResponse = new ArrayList<>();
		List<byte[]> shoesImageResponse = new ArrayList<>();
		ByteArrayOutputStream outStreamObj = new ByteArrayOutputStream();
		BufferedImage image ;
		
		for (Shoes item : shoes) {
			if(item.getImages().size() > 0) {
				for (Image itemImg : item.getImages()) {
					//image =  ImageIO.read(itemImg.getProfPic());
					shoesImageResponse.add(itemImg.getProfPic());
					shoesResponse.add(
							new ResponseShoesData(item.getDescription(),
									item.getPrice(),
									item.getCategory().getName(),
									item.getSizeShoes().getFormatSize(),
									item.getSizeShoes().getSizeShoes(),
									item.getQuanity(),
									item.getDiscount(),
									item.getColors(),
									item.getName(),
									itemImg.getFileType(),
									shoesImageResponse
									)
							);
				}
			}
		}
		return shoesResponse;
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


//	@Override
//	public ResponseEntity<String> createShoes(MultipartFile file, ShoesDTO shoes) throws IOException {
//		String filename = StringUtils.cleanPath(file.getOriginalFilename());
//		
//		Shoes shoesDB = new Shoes();
//		shoesDB.setCreatedDate(LocalDateTime.now());
//		shoesDB.setName(shoes.getName() + filename);
//		shoesDB.setColors(shoes.getColors());
//		shoesDB.setDescription(shoes.getDescription());
//		shoesDB.setDiscount(shoes.getDiscount());
//		shoesDB.setQuanity(shoes.getQuanity());
//		shoesDB.setStatus(true);
//		Optional<Category> category = categoryRepository.findById(shoes.getCategoryId());
//		if(category.isPresent()) {
//			shoesDB.setCategory(category.get());
//		}else {
//			return new ResponseEntity<>("Category Id does not Exist", HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//		
//		
//		Optional<SizeShoes> size = sizeRepository.findById(shoes.getCategoryId());
//		if(size.isPresent()) {
//			shoesDB.setSizeShoes(size.get());
//		}else {
//			return new ResponseEntity<>("Size Id does not Exist", HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//		
//		Shoes res = shoesRepository.save(shoesDB);
//		
//		
//		Image image = new Image(file.getBytes(), shoesDB);
//		
//		image.setCreatedDate(LocalDateTime.now());
//		image.setName(filename);
//		image.setStatus(true);
//		Image imgRes = imageRepository.save(image);
//		if(res != null && imgRes != null) {
//			return new ResponseEntity<String>("Upload shoes successfully", HttpStatus.CREATED);
//		}
//		return new ResponseEntity<String>("Upload shoes is not accepted", HttpStatus.INTERNAL_SERVER_ERROR);
//	}

}
