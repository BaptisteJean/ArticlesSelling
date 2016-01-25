package com.thedevbrige.articleselling.web.rest.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;


/**
 * A DTO for the Ville entity.
 */
public class VilleDTO implements Serializable {

    private Long id;

    private String nameVille;

    private Long paysId;

    private String paysNamePays;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameVille() {
        return nameVille;
    }

    public void setNameVille(String nameVille) {
        this.nameVille = nameVille;
    }

    public Long getPaysId() {
        return paysId;
    }

    public void setPaysId(Long paysId) {
        this.paysId = paysId;
    }

    public String getPaysNamePays() {
        return paysNamePays;
    }

    public void setPaysNamePays(String paysNamePays) {
        this.paysNamePays = paysNamePays;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        VilleDTO villeDTO = (VilleDTO) o;

        if ( ! Objects.equals(id, villeDTO.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "VilleDTO{" +
            "id=" + id +
            ", nameVille='" + nameVille + "'" +
            '}';
    }
}
