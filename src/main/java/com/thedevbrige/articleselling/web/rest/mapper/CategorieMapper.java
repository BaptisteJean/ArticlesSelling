package com.thedevbrige.articleselling.web.rest.mapper;

import com.thedevbrige.articleselling.domain.*;
import com.thedevbrige.articleselling.web.rest.dto.CategorieDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Categorie and its DTO CategorieDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CategorieMapper {

    CategorieDTO categorieToCategorieDTO(Categorie categorie);

    @Mapping(target = "adss", ignore = true)
    Categorie categorieDTOToCategorie(CategorieDTO categorieDTO);
}
