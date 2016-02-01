package com.thedevbrige.articleselling.web.rest.mapper;

import com.thedevbrige.articleselling.domain.*;
import com.thedevbrige.articleselling.web.rest.dto.ImageDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Image and its DTO ImageDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ImageMapper {

    @Mapping(source = "ads.id", target = "adsId")
    @Mapping(source = "ads.nameAds", target = "adsNameAds")
    ImageDTO imageToImageDTO(Image image);

    @Mapping(source = "adsId", target = "ads")
    Image imageDTOToImage(ImageDTO imageDTO);

    default Ads adsFromId(String id) {
        if (id == null) {
            return null;
        }
        Ads ads = new Ads();
        ads.setId(id);
        return ads;
    }
}
