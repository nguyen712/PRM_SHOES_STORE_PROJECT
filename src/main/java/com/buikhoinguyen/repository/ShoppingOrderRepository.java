package com.buikhoinguyen.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.buikhoinguyen.entity.ShoppingOrder;

@Repository
public interface ShoppingOrderRepository extends JpaRepository<ShoppingOrder, Long>{

}
