package com.thedevbrige.articleselling.web.rest.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;


/**
 * A DTO for the Pays entity.
 */
public class PaysDTO implements Serializable {

    private Long id;

    private String namePays;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNamePays() {
        return namePays;
    }

    public void setNamePays(String namePays) {
        this.namePays = namePays;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PaysDTO paysDTO = (PaysDTO) o;

        if ( ! Objects.equals(id, paysDTO.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "PaysDTO{" +
            "id=" + id +
            ", namePays='" + namePays + "'" +
            '}';
    }
}
