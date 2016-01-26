package com.thedevbrige.articleselling.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.time.LocalDate;

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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name = "name_ads", nullable = false)
    private String nameAds;

    @NotNull
    @Column(name = "identif", nullable = false)
    private String identif;

    @Column(name = "date_ajout", nullable = false)
    private LocalDate dateAjout;

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

    @Column(name = "main_image")
    private String mainImage;

    @Column(name = "negociable")
    private Boolean negociable;

    @Column(name = "blocked")
    private boolean blocked = false;

    @ManyToOne
    private Categorie categorie;

    @OneToMany(mappedBy = "ads")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Image> images = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameAds() {
        return nameAds;
    }

    public void setNameAds(String nameAds) {
        this.nameAds = nameAds;
    }

    public String getIdentif() {
        return identif;
    }

    public void setIdentif(String identif) {
        this.identif = identif;
    }

    public LocalDate getDateAjout() {
        return dateAjout;
    }

    public void setDateAjout(LocalDate dateAjout) {
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

    public boolean isBlocked() {
        return blocked;
    }

    public String getMainImage() {
        return mainImage;
    }

    public void setMainImage(String mainImage) {
        this.mainImage = mainImage;
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

    public boolean getBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
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
            ", nameAds='" + nameAds + '\'' +
            ", identif='" + identif + '\'' +
            ", dateAjout=" + dateAjout +
            ", pays='" + pays + '\'' +
            ", ville='" + ville + '\'' +
            ", price=" + price +
            ", etat='" + etat + '\'' +
            ", description='" + description + '\'' +
            ", nbreImage=" + nbreImage +
            ", nbreVue=" + nbreVue +
            ", login='" + login + '\'' +
            ", mainImage='" + mainImage + '\'' +
            ", negociable=" + negociable +
            ", blocked=" + blocked +
            ", categorie=" + categorie +
            ", images=" + images +
            '}';
    }
}
