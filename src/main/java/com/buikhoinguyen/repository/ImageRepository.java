package com.buikhoinguyen.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.buikhoinguyen.entity.Image;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long>{
	
	Optional<Image> findByShoesId(long id);

	List<Image> findAllByShoesId(long id);
}
