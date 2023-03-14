package com.buikhoinguyen.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
@Entity
@Table(name = "shopping_details")
public class OrderDetails {
	@EmbeddedId
    private ShoppingOrderDetails shoppingDetailsId;
    
    @ManyToOne
    @MapsId("shopping_id")
    @JoinColumn(name = "shopping_id")
    private ShoppingOrder student;

    @ManyToOne
    @MapsId("shoes_id")
    @JoinColumn(name = "shoes_id")
    private Shoes shoes;
    
    @Column
    private Long quantity;
    
    @Column
    private double unitPrice;
    
    @Column
    private double discount;
}
