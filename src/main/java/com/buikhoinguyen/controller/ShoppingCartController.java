package com.buikhoinguyen.controller;

import com.buikhoinguyen.entity.ShoppingDetail;
import com.buikhoinguyen.entity.ShoppingOrder;
import com.buikhoinguyen.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/cart")
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    @PostMapping("/add")
    public ResponseEntity<ShoppingDetail> addToCart(@RequestBody Map<String, Object> requestParams) {
        Long customerId = Long.parseLong(requestParams.get("customerId").toString());
        Long shoeId = Long.parseLong(requestParams.get("shoeId").toString());
        Integer quantity = Integer.parseInt(requestParams.get("quantity").toString());
        ShoppingDetail shoppingOrder = shoppingCartService.addToCart(customerId, shoeId, quantity);
        return ResponseEntity.ok().body(shoppingOrder);
    }

    @PostMapping("/remove")
    public ResponseEntity<ShoppingOrder> removeFromCart(@RequestBody Map<String, Object> requestParams) {
        Long customerId = Long.parseLong(requestParams.get("customerId").toString());
        Long shoeId = Long.parseLong(requestParams.get("shoeId").toString());
        ShoppingOrder shoppingOrder = shoppingCartService.removeFromCart(customerId, shoeId);
        return ResponseEntity.ok().body(shoppingOrder);
    }

    @PostMapping("/update")
    public ResponseEntity<ShoppingOrder> updateCartItemQuantity(@RequestBody Map<String, Object> requestParams) {
        Long customerId = Long.parseLong(requestParams.get("customerId").toString());
        Long shoeId = Long.parseLong(requestParams.get("shoeId").toString());
        Integer quantity = Integer.parseInt(requestParams.get("quantity").toString());
        ShoppingOrder shoppingOrder = shoppingCartService.updateCartItemQuantity(customerId, shoeId, quantity);
        return ResponseEntity.ok().body(shoppingOrder);
    }

    @PostMapping("/checkout")
    public ResponseEntity<Void> checkoutCart(@RequestBody Map<String, Object> requestParams) {
        Long customerId = Long.parseLong(requestParams.get("customerId").toString());
        shoppingCartService.checkoutCart(customerId);
        return ResponseEntity.ok().build();
    }

}

