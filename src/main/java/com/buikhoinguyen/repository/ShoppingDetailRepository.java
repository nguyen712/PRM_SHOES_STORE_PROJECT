package com.buikhoinguyen.repository;

import com.buikhoinguyen.entity.Shoes;
import com.buikhoinguyen.entity.ShoppingDetail;
import com.buikhoinguyen.entity.ShoppingOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShoppingDetailRepository extends JpaRepository<ShoppingDetail, Long> {

    Optional<ShoppingDetail> findByShoppingOrderAndShoe(ShoppingOrder shoppingOrder, Shoes shoes);
}