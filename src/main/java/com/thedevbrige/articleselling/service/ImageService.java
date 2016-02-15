package com.thedevbrige.articleselling.service;


import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.inject.Inject;

import net.sf.jmimemagic.Magic;
import net.sf.jmimemagic.MagicException;
import net.sf.jmimemagic.MagicMatch;
import net.sf.jmimemagic.MagicMatchNotFoundException;
import net.sf.jmimemagic.MagicParseException;

import org.apache.commons.imaging.ImageInfo;
import org.apache.commons.imaging.ImageReadException;
import org.apache.commons.imaging.Imaging;
import org.imgscalr.Scalr;
import org.imgscalr.Scalr.Method;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thedevbrige.articleselling.domain.Image;
import com.thedevbrige.articleselling.repository.ImageRepository;

@Service
public class ImageService {
	
	@Inject
	private ImageRepository imageRepository;

	public String checkMime(byte[] img){
		Magic parser = new Magic() ;
		MagicMatch match;
		try {
			match = parser.getMagicMatch(img);
		} catch (MagicParseException e) {
			e.printStackTrace();
			return "application/jpg";
		} catch (MagicMatchNotFoundException e) {
			e.printStackTrace();
			return "application/jpg";
		} catch (MagicException e) {
			e.printStackTrace();
			return "application/jpg";
		}
		return match.getMimeType();
	}

	public byte[] resize(byte[] img, int taille){	
		try {
			InputStream inputStream = new ByteArrayInputStream(img);
			BufferedImage bufImg = ImageIO.read(inputStream);
			BufferedImage thumbnail = Scalr.resize(bufImg, Method.ULTRA_QUALITY, taille, Scalr.OP_ANTIALIAS);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(thumbnail, "jpg", baos);
			baos.flush();
			return baos.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public ImageInfo imgTechDescription(byte[] img){
		try {			
			 ImageInfo imageInfo = Imaging.getImageInfo(img);
			 return imageInfo;
			 //return strInfo(imageInfo.getFormatName(), imageInfo.getHeight(), imageInfo.getWidth());
		} catch (ImageReadException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
                
	}
	
	public byte[] resizeWeigth(byte[] img, int largeur, int hauteur){		
		try {
			InputStream inputStream = new ByteArrayInputStream(img);
			BufferedImage bufImg = ImageIO.read(inputStream);
			BufferedImage thumbnail = Scalr.resize(bufImg, Method.ULTRA_QUALITY, largeur, hauteur, Scalr.OP_ANTIALIAS);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(thumbnail, "jpg", baos);
			baos.flush();
			return baos.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public byte[] addImageWatermark(byte[] img) {
	    try {
	    	File watermarkImageFile = new File("images/logo_app.png");
	    	
	    	InputStream inputStream = new ByteArrayInputStream(img);
			BufferedImage sourceImage = ImageIO.read(inputStream);
	        BufferedImage watermarkImage = ImageIO.read(watermarkImageFile);
	 
	        // initializes necessary graphic properties
	        Graphics2D g2d = (Graphics2D) sourceImage.getGraphics();
	        AlphaComposite alphaChannel = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f);
	        g2d.setComposite(alphaChannel);
	 
	        // calculates the coordinate where the image is painted
	        int topLeftX = (sourceImage.getWidth() - watermarkImage.getWidth()) / 2;
	        int topLeftY = (sourceImage.getHeight() - watermarkImage.getHeight()) / 2;
	 
	        // paints the image watermark
	        g2d.drawImage(watermarkImage, topLeftX, topLeftY, null);
	 
	        ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(sourceImage, "jpg", baos);
			baos.flush();
	        g2d.dispose();	        
			return baos.toByteArray();
			
	    } catch (IOException ex) {
	        ex.printStackTrace();
	        return null;
	    }
	}
	
	@Transactional
	public Image createImage(Image image){
		
        image.setId(UUID.randomUUID().toString());
        
        if(image.getMainImg() != null){
			ImageInfo mainImageInfo = imgTechDescription(image.getMainImg());
			byte[] mainImg = resizeWeigth(image.getMainImg(), 450, mainImageInfo.getHeight());
			image.setMainImg(mainImg);
			byte[] addMainImageWatermark = addImageWatermark(image.getMainImg());
	        image.setMainImg(addMainImageWatermark);
        }
        
        if(image.getImgNormal() != null){
	        ImageInfo normalImageInfo = imgTechDescription(image.getImgNormal());
			byte[] normalImg = resizeWeigth(image.getImgNormal(), 450, normalImageInfo.getHeight());
			image.setImgNormal(normalImg);
			byte[] addNormalImageWatermark = addImageWatermark(image.getImgNormal());
	        image.setImgNormal(addNormalImageWatermark);
        }
        
        if(image.getImgNormal1() != null){
	        ImageInfo normal1ImageInfo = imgTechDescription(image.getImgNormal1());
			byte[] normal1Img = resizeWeigth(image.getImgNormal1(), 450, normal1ImageInfo.getHeight());
			image.setImgNormal1(normal1Img);
			byte[] addNormal1ImageWatermark = addImageWatermark(image.getImgNormal1());
	        image.setImgNormal1(addNormal1ImageWatermark);
        }
        
        if(image.getImgThumbnail() != null){
	        ImageInfo thumbnailImageInfo = imgTechDescription(image.getImgThumbnail());
			byte[] thumbnailImg = resizeWeigth(image.getImgThumbnail(), 450, thumbnailImageInfo.getHeight());
			image.setImgThumbnail(thumbnailImg);
			byte[] addThumbnailImageWatermark = addImageWatermark(image.getImgThumbnail());
	        image.setImgThumbnail(addThumbnailImageWatermark);
        }
        
        if(image.getImgThumbnail1() != null){
	        ImageInfo thumbnail1ImageInfo = imgTechDescription(image.getImgThumbnail1());
			byte[] thumbnail1Img = resizeWeigth(image.getImgThumbnail1(), 450, thumbnail1ImageInfo.getHeight());
			image.setImgThumbnail1(thumbnail1Img);
			byte[] addThumbnail1ImageWatermark = addImageWatermark(image.getImgThumbnail1());
	        image.setImgThumbnail1(addThumbnail1ImageWatermark);
        }
		
        Image result = imageRepository.save(image);
		
		return result;
	}

}
