package com.buikhoinguyen.service;

import com.buikhoinguyen.entity.ShoppingDetail;
import com.buikhoinguyen.entity.ShoppingOrder;

public interface ShoppingCartService {

    ShoppingDetail addToCart(Long customerId, Long shoeId, Integer quantity);

    ShoppingOrder removeFromCart(Long customerId, Long shoeId);

    ShoppingOrder updateCartItemQuantity(Long customerId, Long shoeId, Integer quantity);

    void checkoutCart(Long customerId);

}

