package com.thedevbrige.articleselling.repository;

import com.thedevbrige.articleselling.domain.Pays;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Pays entity.
 */
public interface PaysRepository extends JpaRepository<Pays,Long> {

    Pays findByNamePaysEquals(String namepays);
    Pays findByNamePays(String namepays);

}
