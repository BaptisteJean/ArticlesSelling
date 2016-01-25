package com.thedevbrige.articleselling.web.rest.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;


/**
 * A DTO for the Categorie entity.
 */
public class CategorieDTO implements Serializable {

    private Long id;

    @NotNull
    private String nameCategorie;

    private String parent;

    private String description;

    private Long nbreAds;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameCategorie() {
        return nameCategorie;
    }

    public void setNameCategorie(String nameCategorie) {
        this.nameCategorie = nameCategorie;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getNbreAds() {
        return nbreAds;
    }

    public void setNbreAds(Long nbreAds) {
        this.nbreAds = nbreAds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CategorieDTO categorieDTO = (CategorieDTO) o;

        if ( ! Objects.equals(id, categorieDTO.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "CategorieDTO{" +
            "id=" + id +
            ", nameCategorie='" + nameCategorie + "'" +
            ", parent='" + parent + "'" +
            ", description='" + description + "'" +
            ", nbreAds='" + nbreAds + "'" +
            '}';
    }
}
