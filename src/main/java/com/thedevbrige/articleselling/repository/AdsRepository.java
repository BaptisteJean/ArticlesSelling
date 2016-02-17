package com.thedevbrige.articleselling.repository;

import com.thedevbrige.articleselling.domain.Ads;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Ads entity.
 */
public interface AdsRepository extends JpaRepository<Ads,Long> {



    public List<Ads> findByLoginAndBlocked(String login, boolean blocked);

    List<Ads> findByCategorieIdAndBlocked(Long categorieId, boolean blocked);

    public Ads findById(String id);


}
