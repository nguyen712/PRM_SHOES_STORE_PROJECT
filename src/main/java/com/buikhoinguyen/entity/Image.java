package com.buikhoinguyen.entity;



import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
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
