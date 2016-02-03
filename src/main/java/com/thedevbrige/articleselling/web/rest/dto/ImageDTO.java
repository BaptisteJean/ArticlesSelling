package com.thedevbrige.articleselling.web.rest.dto;

import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

import javax.persistence.Lob;


/**
 * A DTO for the Image entity.
 */
public class ImageDTO implements Serializable {

    private String id;

    @Lob
    private byte[] imgThumbnail;

    private String imgThumbnailContentType;

    @Lob
    private byte[] imgNormal;

    private String imgNormalContentType;
    
    @Lob
    private byte[] mainImg;
    
    @Lob
    private byte[] imgThumbnail1;

    private String imgThumbnailContentType1;

    @Lob
    private byte[] imgNormal1;

    private String imgNormalContentType1;
    
    private String mainImgContentType;

    private String adsId;

    private String adsNameAds;

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

    public String getAdsId() {
        return adsId;
    }

    public void setAdsId(String adsId) {
        this.adsId = adsId;
    }

    public String getAdsNameAds() {
        return adsNameAds;
    }

    public void setAdsNameAds(String adsNameAds) {
        this.adsNameAds = adsNameAds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ImageDTO imageDTO = (ImageDTO) o;

        if ( ! Objects.equals(id, imageDTO.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "ImageDTO{" +
            "id=" + id +
            ", imgThumbnail='" + imgThumbnail + "'" +
            ", imgNormal='" + imgNormal + "'" +
            '}';
    }
}
