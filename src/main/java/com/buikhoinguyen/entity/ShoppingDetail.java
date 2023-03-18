package com.buikhoinguyen.entity;

import javax.persistence.*;

@Entity
@Table(name = "shopping_detail")
public class ShoppingDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shopping_order_id")
    private ShoppingOrder shoppingOrder;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shoe_id")
    private Shoes shoe;

    private Integer quantity;

    // Constructors
    public ShoppingDetail() {}

    public ShoppingDetail(ShoppingOrder shoppingOrder, Shoes shoe, Integer quantity) {
        this.shoppingOrder = shoppingOrder;
        this.shoe = shoe;
        this.quantity = quantity;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ShoppingOrder getShoppingOrder() {
        return shoppingOrder;
    }

    public void setShoppingOrder(ShoppingOrder shoppingOrder) {
        this.shoppingOrder = shoppingOrder;
    }

    public Shoes getShoe() {
        return shoe;
    }

    public void setShoe(Shoes shoe) {
        this.shoe = shoe;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public ShoppingDetail(Shoes shoe, int quantity) {
        this.shoe = shoe;
        this.quantity = quantity;
    }

}

