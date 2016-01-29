package com.thedevbrige.articleselling.repository;

import com.thedevbrige.articleselling.domain.Ads;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Ads entity.
 */
public interface AdsRepository extends JpaRepository<Ads,Long> {

	public Ads findById(String id);

}
