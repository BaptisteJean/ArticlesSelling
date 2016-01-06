package com.thedevbrige.articleselling.web.rest.mapper;

import com.thedevbrige.articleselling.domain.*;
import com.thedevbrige.articleselling.web.rest.dto.PaysDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Pays and its DTO PaysDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface PaysMapper {

    PaysDTO paysToPaysDTO(Pays pays);

    @Mapping(target = "villes", ignore = true)
    Pays paysDTOToPays(PaysDTO paysDTO);
}
