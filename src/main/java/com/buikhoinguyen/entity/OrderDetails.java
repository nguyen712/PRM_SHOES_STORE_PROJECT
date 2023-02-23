package com.buikhoinguyen.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
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
