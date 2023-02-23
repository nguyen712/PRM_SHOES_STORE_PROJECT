package com.buikhoinguyen.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseData {
	private String fileName;
	private String fileType;
	private long filesIZE;
}
