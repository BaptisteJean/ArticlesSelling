package com.thedevbrige.articleselling.web.rest.mapper;

import com.thedevbrige.articleselling.domain.*;
import com.thedevbrige.articleselling.web.rest.dto.VilleDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Ville and its DTO VilleDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface VilleMapper {

    @Mapping(source = "pays.id", target = "paysId")
    @Mapping(source = "pays.namePays", target = "paysNamePays")
    VilleDTO villeToVilleDTO(Ville ville);

    @Mapping(source = "paysId", target = "pays")
    Ville villeDTOToVille(VilleDTO villeDTO);

    default Pays paysFromId(Long id) {
        if (id == null) {
            return null;
        }
        Pays pays = new Pays();
        pays.setId(id);
        return pays;
    }
}
