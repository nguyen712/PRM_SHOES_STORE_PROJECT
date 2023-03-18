package com.buikhoinguyen.serviceimpl;

import com.buikhoinguyen.config.*;
import com.buikhoinguyen.entity.Customer;
import com.buikhoinguyen.entity.Shoes;
import com.buikhoinguyen.entity.ShoppingDetail;
import com.buikhoinguyen.entity.ShoppingOrder;
import com.buikhoinguyen.repository.CustomerRepository;
import com.buikhoinguyen.repository.ShoesRepository;
import com.buikhoinguyen.repository.ShoppingDetailRepository;
import com.buikhoinguyen.repository.ShoppingOrderRepository;
import com.buikhoinguyen.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Autowired
    private ShoppingOrderRepository shoppingOrderRepository;

    @Autowired
    private ShoppingDetailRepository shoppingDetailRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ShoesRepository shoeRepository;

    @Override
    public ShoppingDetail addToCart(Long customerId, Long shoeId, Integer quantity) {
        Optional<Customer> customer = customerRepository.findById(customerId);
        if (!customer.isPresent()) {
            throw new CustomerNotFoundException("Customer not found");
        }

        Optional<Shoes> shoe = shoeRepository.findById(shoeId);
        if (!shoe.isPresent()) {
            throw new ShoesNotFoundException("Shoe not found");
        }

        ShoppingOrder shoppingOrder = shoppingOrderRepository.findByCustomer(customer.get())
                .orElseGet(() -> {
                    ShoppingOrder newShoppingOrder = new ShoppingOrder(customer.get());
                    return shoppingOrderRepository.save(newShoppingOrder);
                });

        ShoppingDetail shoppingDetail = shoppingDetailRepository.findByShoppingOrderAndShoe(shoppingOrder, shoe.get())
                .orElseGet(() -> {
                    ShoppingDetail newShoppingDetail = new ShoppingDetail(shoe.get(), quantity);
                    newShoppingDetail.setShoppingOrder(shoppingOrder);
                    return shoppingDetailRepository.save(newShoppingDetail);
                });

        shoppingDetail.setQuantity(shoppingDetail.getQuantity() + quantity);
        shoppingDetailRepository.save(shoppingDetail);

        return shoppingDetail;
    }

    private ShoppingDetail findShoppingDetail(Long customerId, Long shoeId) {
        Optional<Customer> customer = customerRepository.findById(customerId);
        if (!customer.isPresent()) {
            throw new CustomerNotFoundException("Customer not found");
        }

        Optional<Shoes> shoe = shoeRepository.findById(shoeId);
        if (!shoe.isPresent()) {
            throw new ShoesNotFoundException("Shoe not found");
        }

        ShoppingOrder shoppingOrder = shoppingOrderRepository.findByCustomer(customer.get())
                .orElseThrow(() -> new ShoppingOrderNotFoundException("Shopping order not found"));

        ShoppingDetail shoppingDetail = shoppingDetailRepository.findByShoppingOrderAndShoe(shoppingOrder, shoe.get())
                .orElseThrow(() -> new ShoppingDetailNotFoundException("Shopping detail not found"));

        return shoppingDetail;
    }

    @Override
    public ShoppingOrder removeFromCart(Long customerId, Long shoeId) {
        ShoppingDetail shoppingDetail = findShoppingDetail(customerId, shoeId);

        ShoppingOrder shoppingOrder = shoppingDetail.getShoppingOrder();
        shoppingOrder.getShoppingDetails().remove(shoppingDetail);
        shoppingDetailRepository.delete(shoppingDetail);

        return shoppingOrder;
    }

    @Override
    public ShoppingOrder updateCartItemQuantity(Long customerId, Long shoeId, Integer quantity) {
        ShoppingDetail shoppingDetail = findShoppingDetail(customerId, shoeId);

        if (quantity <= 0) {
            shoppingDetailRepository.delete(shoppingDetail);
        } else {
            shoppingDetail.setQuantity(quantity);
            shoppingDetailRepository.save(shoppingDetail);
        }

        return shoppingDetail.getShoppingOrder();
    }

    @Override
    public void checkoutCart(Long customerId) {
        Optional<Customer> customer = customerRepository.findById(customerId);
        if (!customer.isPresent()) {
            throw new CustomerNotFoundException("Customer not found");
        }

        ShoppingOrder shoppingOrder = shoppingOrderRepository.findByCustomer(customer.get())
                .orElseThrow(() -> new ShoppingOrderNotFoundException("Shopping order not found"));

        for (ShoppingDetail shoppingDetail : shoppingOrder.getShoppingDetails()) {
            Shoes shoe = shoppingDetail.getShoe();
            int newStockCount = (int) (shoe.getQuanity() - shoppingDetail.getQuantity());
            if (newStockCount < 0) {
                throw new InsufficientStockException("Insufficient stock for shoe " + shoe.getId());
            }
            shoe.setQuanity((long) newStockCount);
            shoeRepository.save(shoe);
        }

        shoppingOrder.setCheckedOut(true);
        shoppingOrderRepository.save(shoppingOrder);
    }

}
