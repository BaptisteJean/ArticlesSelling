package com.thedevbrige.articleselling.repository;

import com.thedevbrige.articleselling.domain.Ads;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Ads entity.
 */
public interface AdsRepository extends JpaRepository<Ads,Long> {
	
	

    public List<Ads> findByLoginAndBlocked(String login, boolean blocked);
	
    public Ads findById(String id);

    @Query("select top 10  nbre_vue  from ads")
    public List <Ads> findAll();
  
}
