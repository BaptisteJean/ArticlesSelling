package com.thedevbrige.articleselling.web.rest.mapper;

import com.thedevbrige.articleselling.domain.*;
import com.thedevbrige.articleselling.web.rest.dto.AdsDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Ads and its DTO AdsDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface AdsMapper {

    @Mapping(source = "categorie.id", target = "categorieId")
    @Mapping(source = "categorie.nameCategorie", target = "categorieNameCategorie")
    AdsDTO adsToAdsDTO(Ads ads);

    @Mapping(source = "categorieId", target = "categorie")
    @Mapping(target = "images", ignore = true)
    Ads adsDTOToAds(AdsDTO adsDTO);

    default Categorie categorieFromId(Long id) {
        if (id == null) {
            return null;
        }
        Categorie categorie = new Categorie();
        categorie.setId(id);
        return categorie;
    }
}
