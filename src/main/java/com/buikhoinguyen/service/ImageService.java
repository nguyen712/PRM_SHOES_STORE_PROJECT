package com.buikhoinguyen.service;

import java.io.IOException;
import org.springframework.web.multipart.MultipartFile;

import com.buikhoinguyen.entity.Image;

public interface ImageService {
	public Image uploadImage(long shoesId ,MultipartFile file) throws Exception;
}
