package com.buikhoinguyen.entity;


import java.sql.Blob;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Image extends BasedEntity{
	@Lob
	@Column( length = Integer.MAX_VALUE)
	private byte[] profPic;
	
	private String fileType;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "shoes_id")
	private Shoes shoes;
}
