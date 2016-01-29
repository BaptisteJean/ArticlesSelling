package com.thedevbrige.articleselling.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;

import java.time.LocalDate;
//import org.joda.time.LocalDate;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Ads.
 */
@Entity
@Table(name = "ads")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Ads implements Serializable {

    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    @NotNull
    @Column(name = "name_ads", nullable = false)
    private String nameAds;

    @Column(name = "date_ajout", nullable = false)
    private String dateAjout;

    @Column(name = "pays")
    private String pays;

    @Column(name = "ville")
    private String ville;

    @NotNull
    @Column(name = "price", nullable = false)
    private Long price;

    @Column(name = "etat")
    private String etat;

    @Column(name = "description")
    private String description;

    @Column(name = "nbre_image")
    private Long nbreImage;

    @Column(name = "nbre_vue")
    private Long nbreVue;
    
    @Column(name = "login")
    private String login;

    @Column(name = "negociable")
    private Boolean negociable;

    @ManyToOne
    private Categorie categorie;

    @OneToMany(mappedBy = "ads")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Image> images = new HashSet<>();

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

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public Set<Image> getImages() {
        return images;
    }

    public void setImages(Set<Image> images) {
        this.images = images;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Ads ads = (Ads) o;

        if ( ! Objects.equals(id, ads.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Ads{" +
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
