package com.buikhoinguyen.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.buikhoinguyen.entity.OrderDetails;
@Repository
public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Long>{

}
