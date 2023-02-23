package com.buikhoinguyen.dto;





import lombok.Data;

@Data
public class ShoesDTO {
	private String description;
	private double price;
	private long categoryId;
	private long sizeId;
	private long quanity;
	private double discount;
	private String[] colors;
	private String name;
}
