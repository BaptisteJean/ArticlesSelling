package com.thedevbrige.articleselling.web.rest.dto;

import java.time.LocalDate;
//import org.joda.time.LocalDate;

import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;


/**
 * A DTO for the Ads entity.
 */
public class AdsDTO implements Serializable {

    private String id;

    @NotNull
    private String nameAds;

    private String dateAjout;

    private String pays;

    private String ville;

    @NotNull
    private Long price;

    private String etat;

    private String description;

    private Long nbreImage;

    private Long nbreVue;
    
    private String login;

    private Boolean negociable;

    private Long categorieId;

    private String categorieNameCategorie;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNameAds() {
        return nameAds;
    }

    public void setNameAds(String nameAds) {
        this.nameAds = nameAds;
    }

    public String getDateAjout() {
        return dateAjout;
    }

    public void setDateAjout(String dateAjout) {
        this.dateAjout = dateAjout;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getNbreImage() {
        return nbreImage;
    }

    public void setNbreImage(Long nbreImage) {
        this.nbreImage = nbreImage;
    }

    public Long getNbreVue() {
        return nbreVue;
    }

    public void setNbreVue(Long nbreVue) {
        this.nbreVue = nbreVue;
    }
    
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Boolean getNegociable() {
        return negociable;
    }

    public void setNegociable(Boolean negociable) {
        this.negociable = negociable;
    }

    public Long getCategorieId() {
        return categorieId;
    }

    public void setCategorieId(Long categorieId) {
        this.categorieId = categorieId;
    }

    public String getCategorieNameCategorie() {
        return categorieNameCategorie;
    }

    public void setCategorieNameCategorie(String categorieNameCategorie) {
        this.categorieNameCategorie = categorieNameCategorie;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AdsDTO adsDTO = (AdsDTO) o;

        if ( ! Objects.equals(id, adsDTO.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "AdsDTO{" +
            "id=" + id +
            ", nameAds='" + nameAds + "'" +
            ", dateAjout='" + dateAjout + "'" +
            ", pays='" + pays + "'" +
            ", ville='" + ville + "'" +
            ", price='" + price + "'" +
            ", etat='" + etat + "'" +
            ", description='" + description + "'" +
            ", nbreImage='" + nbreImage + "'" +
            ", nbreVue='" + nbreVue + "'" +
            ", negociable='" + negociable + "'" +
            '}';
    }
}
