package com.thedevbrige.articleselling.repository;

import com.thedevbrige.articleselling.domain.Ville;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Ville entity.
 */
public interface VilleRepository extends JpaRepository<Ville,Long> {

    Ville findByNameVille(String nameville);

}
