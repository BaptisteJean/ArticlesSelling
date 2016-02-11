package com.thedevbrige.articleselling.domain;

import org.codehaus.jackson.annotate.JsonIgnore;
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
    
    @JsonIgnore
    @Lob
    @Column(name = "img")
    private byte[] img;

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
    
    @Lob
    @Column(name = "main_img")
    private byte[] mainImg;

    @Column(name = "main_img_content_type")
    private String mainImgContentType;
    
    @Lob
    @Column(name = "img_thumbnail1")
    private byte[] imgThumbnail1;

    @Column(name = "img_thumbnail_content_type1")
    private String imgThumbnailContentType1;
    
    @Lob
    @Column(name = "img_normal1")
    private byte[] imgNormal1;


    @Column(name = "img_normal_content_type1")
    private String imgNormalContentType1;

    @ManyToOne
    private Ads ads;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    public byte[] getImg() {
        return img;
    }

    public void setImg(byte[] img) {
        this.img = img;
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
    
    public byte[] getMainImg() {
        return mainImg;
    }

    public void setMainImg(byte[] mainImg) {
        this.mainImg = mainImg;
    }
    
    public String getMainImgContentType() {
        return mainImgContentType;
    }

    public void setMainImgContentType(String mainImgContentType) {
        this.mainImgContentType = mainImgContentType;
    }
    
    public byte[] getImgThumbnail1() {
        return imgThumbnail1;
    }

    public void setImgThumbnail1(byte[] imgThumbnail1) {
        this.imgThumbnail1 = imgThumbnail1;
    }

    public String getImgThumbnailContentType1() {
        return imgThumbnailContentType1;
    }

    public void setImgThumbnailContentType1(String imgThumbnailContentType1) {
        this.imgThumbnailContentType1 = imgThumbnailContentType1;
    }

    public byte[] getImgNormal1() {
        return imgNormal1;
    }

    public void setImgNormal1(byte[] imgNormal1) {
        this.imgNormal1 = imgNormal1;
    }

    public String getImgNormalContentType1() {
        return imgNormalContentType1;
    }

    public void setImgNormalContentType1(String imgNormalContentType1) {
        this.imgNormalContentType1 = imgNormalContentType1;
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
