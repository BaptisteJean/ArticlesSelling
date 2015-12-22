package com.thedevbrige.articleselling.repository;

import com.thedevbrige.articleselling.domain.Categorie;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Categorie entity.
 */
public interface CategorieRepository extends JpaRepository<Categorie,Long> {

}
