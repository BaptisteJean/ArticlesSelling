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
