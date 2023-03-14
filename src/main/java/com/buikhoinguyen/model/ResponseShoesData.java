package com.buikhoinguyen.model;

import java.util.List;

import com.buikhoinguyen.entity.Image;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ResponseShoesData {
	private Long id;
	private String description;
	private double price;
	private String categoryName;
	private String formatSize;
	private float size;
	private Long quanity;
	private double discount;
	private String[] colors;
	private String name;
//	private String fileType;
//	//private List<Image> profic;
//	private List<byte[]> profic;
	private List<Image> profic;
}
