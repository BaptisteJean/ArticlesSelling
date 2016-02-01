package com.thedevbrige.articleselling.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Image.
 */
@Entity
@Table(name = "image")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Image implements Serializable {

    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    @Lob
    @Column(name = "img_thumbnail")
    private byte[] imgThumbnail;


    @Column(name = "img_thumbnail_content_type")
    private String imgThumbnailContentType;
    @Lob
    @Column(name = "img_normal")
    private byte[] imgNormal;


    @Column(name = "img_normal_content_type")
    private String imgNormalContentType;

    @ManyToOne
    private Ads ads;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public byte[] getImgThumbnail() {
        return imgThumbnail;
    }

    public void setImgThumbnail(byte[] imgThumbnail) {
        this.imgThumbnail = imgThumbnail;
    }

    public String getImgThumbnailContentType() {
        return imgThumbnailContentType;
    }

    public void setImgThumbnailContentType(String imgThumbnailContentType) {
        this.imgThumbnailContentType = imgThumbnailContentType;
    }

    public byte[] getImgNormal() {
        return imgNormal;
    }

    public void setImgNormal(byte[] imgNormal) {
        this.imgNormal = imgNormal;
    }

    public String getImgNormalContentType() {
        return imgNormalContentType;
    }

    public void setImgNormalContentType(String imgNormalContentType) {
        this.imgNormalContentType = imgNormalContentType;
    }

    public Ads getAds() {
        return ads;
    }

    public void setAds(Ads ads) {
        this.ads = ads;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Image image = (Image) o;

        if ( ! Objects.equals(id, image.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Image{" +
            "id=" + id +
            ", imgThumbnail='" + imgThumbnail + "'" +
            ", imgThumbnailContentType='" + imgThumbnailContentType + "'" +
            ", imgNormal='" + imgNormal + "'" +
            ", imgNormalContentType='" + imgNormalContentType + "'" +
            '}';
    }
}
