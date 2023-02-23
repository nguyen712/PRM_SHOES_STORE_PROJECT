package com.buikhoinguyen.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.buikhoinguyen.entity.SizeShoes;

@Repository
public interface SizeRepository extends JpaRepository<SizeShoes, Long>{
	
}
