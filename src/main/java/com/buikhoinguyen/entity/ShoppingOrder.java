package com.buikhoinguyen.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "shopping_order")
public class ShoppingOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(mappedBy = "shoppingOrder", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ShoppingDetail> shoppingDetails = new ArrayList<>();

    @Column(name = "checked_out")
    private boolean checkedOut;


    public ShoppingOrder(Customer customer) {
        this.customer = customer;
    }
    public ShoppingOrder() {
    }
    public boolean isCheckedOut() {
        return checkedOut;
    }

    public void setCheckedOut(boolean checkedOut) {
        this.checkedOut = checkedOut;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<ShoppingDetail> getShoppingDetails() {
        return shoppingDetails;
    }

    public void setShoppingDetails(List<ShoppingDetail> shoppingDetails) {
        this.shoppingDetails = shoppingDetails;
    }
}



