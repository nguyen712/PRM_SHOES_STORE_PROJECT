package com.buikhoinguyen.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@SuppressWarnings("serial")
@Embeddable
public class ShoppingOrderDetails implements Serializable{
	
	@Column(name = "shopping_id")
	private Long orderId;
	
	@Column(name = "shoes_id")
	private Long shoesid;
}
