package com.buikhoinguyen.repository;

import com.buikhoinguyen.entity.Customer;
import com.buikhoinguyen.entity.ShoppingOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShoppingOrderRepository extends JpaRepository<ShoppingOrder, Long> {

    Optional<ShoppingOrder> findByCustomer(Customer customer);
}