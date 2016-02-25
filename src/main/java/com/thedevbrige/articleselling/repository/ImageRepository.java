package com.thedevbrige.articleselling.repository;

import com.thedevbrige.articleselling.domain.Ads;
import com.thedevbrige.articleselling.domain.Image;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Image entity.
 */
public interface ImageRepository extends JpaRepository<Image,Long> {

    Image findByAdsId(String adsId);
	public Image findById(String id);
	
	public List <Image> findFirst10ByOrderByAdsNbreVueDesc();

}
