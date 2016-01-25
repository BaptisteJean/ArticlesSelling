package com.thedevbrige.articleselling.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Categorie.
 */
@Entity
@Table(name = "categorie")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Categorie implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name = "name_categorie", nullable = false)
    private String nameCategorie;

    @Column(name = "parent")
    private String parent;

    @Column(name = "description")
    private String description;

    @Column(name = "nbre_ads")
    private Long nbreAds;
    
    @Column(name = "nbre_vu")
    private Long nbre_vu;
    

    @OneToMany(mappedBy = "categorie")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Ads> adss = new HashSet<>();

    
    public Long getNbre_vu() {
		return nbre_vu;
	}

	public void setNbre_vu(Long nbre_vu) {
		this.nbre_vu = nbre_vu;
	}
	public void addNbre_vu(){
		this.nbre_vu++;
	}

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

    public Set<Ads> getAdss() {
        return adss;
    }

    public void setAdss(Set<Ads> adss) {
        this.adss = adss;
    }

    public Categorie(String nameCategorie, String parent) {
        super();
        this.nameCategorie = nameCategorie;
        this.parent = parent;
    }
    public Categorie() {
        super();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Categorie categorie = (Categorie) o;

        if ( ! Objects.equals(id, categorie.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Categorie{" +
            "id=" + id +
            ", nameCategorie='" + nameCategorie + "'" +
            ", parent='" + parent + "'" +
            ", description='" + description + "'" +
            ", nbreAds='" + nbreAds + "'" +
            '}';
    }
}
