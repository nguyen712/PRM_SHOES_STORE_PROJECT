package com.buikhoinguyen.entity;

public class CartItem {
    private Shoes shoe;
    private int quantity;

    public CartItem(Shoes shoe, int quantity) {
        this.shoe = shoe;
        this.quantity = quantity;
    }

    public Shoes getShoe() {
        return shoe;
    }

    public void setShoe(Shoes shoe) {
        this.shoe = shoe;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void increaseQuantity(int quantity) {
        this.quantity += quantity;
    }

    public void decreaseQuantity(int quantity) {
        this.quantity -= quantity;
    }

    public double getTotalPrice() {
        return this.shoe.getPrice() * this.quantity;
    }
}

