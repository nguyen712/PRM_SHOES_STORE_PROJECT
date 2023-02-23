package com.buikhoinguyen.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.buikhoinguyen.entity.Shoes;

@Repository
public interface ShoesRepository extends JpaRepository<Shoes, Long>{

}
