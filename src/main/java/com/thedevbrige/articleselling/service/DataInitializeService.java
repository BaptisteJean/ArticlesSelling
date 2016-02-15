package com.thedevbrige.articleselling.service;


import com.thedevbrige.articleselling.domain.Ads;
import com.thedevbrige.articleselling.domain.Categorie;
import com.thedevbrige.articleselling.domain.Image;
import com.thedevbrige.articleselling.repository.AdsRepository;
import com.thedevbrige.articleselling.repository.CategorieRepository;
import com.thedevbrige.articleselling.repository.ImageRepository;

import org.apache.commons.imaging.ImageInfo;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.UUID;

@Service
@Transactional
public class DataInitializeService {

@Inject
private CategorieRepository categorieRepository;

@Inject
private ImageService imageService;

@Inject
private AdsRepository adsRepository;

@Inject
private ImageRepository imageRepository;

private static String REPERTOIRE = "images";

	@PostConstruct
	public void iniatilisation() throws FileNotFoundException, IOException{
		List<Categorie> findAll = categorieRepository.findAll();
		if(findAll.isEmpty()){
			//initialize categorie
			List<Categorie> setCatImg = new ArrayList<Categorie>();
			setCatImg.add(createCateg("Car Parts & Accessories", "Automobiles"));
			setCatImg.add(createCateg("Campervans & Caravans", "Automobiles"));
			setCatImg.add(createCateg("Motorbikes & Scooters", "Automobiles"));
			setCatImg.add(createCateg("Motorbike Parts & Accessories", "Automobiles"));
			setCatImg.add(createCateg("Vans, Trucks & Plant", "Automobiles"));
			setCatImg.add(createCateg("Wanted", "Automobiles"));
			setCatImg.add(createCateg("House for Rent", "Property"));
			setCatImg.add(createCateg("House for Sale", "Property"));
			setCatImg.add(createCateg("Apartments For Sale", "Property"));
			setCatImg.add(createCateg("Apartments for Rent", "Property"));
			setCatImg.add(createCateg("Farms-Ranches", "Property"));
			setCatImg.add(createCateg("Land", "Property"));
            setCatImg.add(createCateg("Vacation Rentals", "Property"));
			setCatImg.add(createCateg("Commercial Building", "Property"));
            setCatImg.add(createCateg("Full Time Jobs", "Jobs"));
			setCatImg.add(createCateg("Internet Jobs", "Jobs"));
			setCatImg.add(createCateg("Learn & Earn jobs", "Jobs"));
			setCatImg.add(createCateg("Manual Labor Jobs", "Jobs"));
            setCatImg.add(createCateg("Other Jobs", "Jobs"));
			setCatImg.add(createCateg("OwnBusiness", "Jobs"));
            setCatImg.add(createCateg("Building, Home & Removals",	"Services"));
            setCatImg.add(createCateg("Entertainment",	"Services"));
            setCatImg.add(createCateg("Health & Beauty",	"Services"));
            setCatImg.add(createCateg("Miscellaneous",	"Services"));
            setCatImg.add(createCateg("Property & Shipping",	"Services"));
            setCatImg.add(createCateg("Tax, Money & Visas",	"Services"));
            setCatImg.add(createCateg("Telecoms & Computers",	"Services"));
            setCatImg.add(createCateg("Travel Services & Tours",	"Services"));
            setCatImg.add(createCateg("Tuition & Lessons",	"Services"));
            setCatImg.add(createCateg("Automotive Classes",	"Learning"));
            setCatImg.add(createCateg("Computer Multimedia Classes",	"Learning"));
            setCatImg.add(createCateg("Sports Classes",	"Learning"));
            setCatImg.add(createCateg("Home Improvement Classes",	"Learning"));
            setCatImg.add(createCateg("Language Classes",	"Learning"));
            setCatImg.add(createCateg("Music Classes",	"Learning"));
            setCatImg.add(createCateg("Personal Fitness","Learning"));
            setCatImg.add(createCateg("Other Classes",	"Learning"));
            setCatImg.add(createCateg("Pets for Sale",	"Pets"));
            setCatImg.add(createCateg("Petsitters & Dogwalkers"	,"Pets"));
            setCatImg.add(createCateg("Equipment & Accessories",	"Pets"));
            setCatImg.add(createCateg("Missing, Lost & Found",	"Pets"));
            setCatImg.add(createCateg("Audio & Stereo",	"For Sale"));
            setCatImg.add(createCateg("Baby & Kids Stuff",	"For Sale"));
            setCatImg.add(createCateg("CDs, DVDs, Games & Books",	"For Sale"));
            setCatImg.add(createCateg("Clothes, Footwear & Accessories","For Sale"));
            setCatImg.add(createCateg("Computers & Software",	"For Sale"));
            setCatImg.add(createCateg("Home & Garden",	"For Sale"));
            setCatImg.add(createCateg("Music & Instruments",	"For Sale"));
            setCatImg.add(createCateg("Office Furniture & Equipment",	"For Sale"));
            setCatImg.add(createCateg("Phones, Mobile Phones & Telecoms",	"For Sale"));
            setCatImg.add(createCateg("Sports, Leisure & Travel",	"For Sale"));
            setCatImg.add(createCateg("Tickets",	"For Sale"));
            setCatImg.add(createCateg("TV, DVD & Cameras",	"For Sale"));
            setCatImg.add(createCateg("Video Games & Consoles",	"For Sale"));
            setCatImg.add(createCateg("Freebies",	"For Sale"));
            setCatImg.add(createCateg("Miscellaneous Goods",	"For Sale"));
            setCatImg.add(createCateg("Stuff Wanted",	"For Sale"));
            setCatImg.add(createCateg("Swap Shop",	"For Sale"));
            setCatImg.add(createCateg("Announcements",	"Community"));
            setCatImg.add(createCateg("Car Pool - Bike Ride",	"Community"));
            setCatImg.add(createCateg("Charity - Donate - NGO",	"Community"));
            setCatImg.add(createCateg("Lost - Found",	"Community"));
            setCatImg.add(createCateg("Tender Notices",	"Community"));
            

            Categorie categImg1 = categorieRepository.findByNameCategorie("Car Parts & Accessories");
            Categorie categImg2 = categorieRepository.findByNameCategorie("Phones, Mobile Phones & Telecoms");
            
          //initialize some ads and image
			for(int i=1;i<26;i++){
				createAdsAndImage("Ads", (long)150000, "Beautiful Car Parts & Accessories", categImg1, i);
			}
			
			for(int i=26;i<51;i++){
				createAdsAndImage("Ads", (long)85000, "Beautiful Phones, Mobile Phones & Telecoms", categImg2, i);
			}
        }

	}

    public Categorie createCateg(String nameCategorie, String parent){
        Categorie categImg = new Categorie(nameCategorie, parent);
        return categorieRepository.saveAndFlush(categImg);
    }
    
    public void createAdsAndImage(String name, Long price, String desc, Categorie categImg, int nbre){
    	String id = UUID.randomUUID().toString();
    	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        
    	Ads ads = new Ads();
    	Image image = new Image();
    	ads.setId(id);
    	ads.setNameAds(name + nbre);
    	ads.setDescription(desc);
    	ads.setPrice(price);
        ads.setDateAjout(dateFormat.format(date));
        ads.setLogin("admin");
        ads.setNbreImage((long)5);
        ads.setBlocked(false);
        ads.setCategorie(categImg);
        ads.setPays("Cameroon");
        ads.setVille("Douala");
        ads.setEtat("New");
        ads.setNegociable(true);
        
        image.setId(id);
        String filePathImg = REPERTOIRE+"/"+nbre+".jpg";
        try(InputStream inputStream = new FileInputStream(filePathImg)) {
			byte[] data  = IOUtils.toByteArray(inputStream);
			image.setImg(data);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        //image.setImgThumbnail(imageService.resize(image.getImg(), 300));
        
        ImageInfo imageInfo = imageService.imgTechDescription(image.getImg());
        
        image.setImgNormal(imageService.resizeWeigth(image.getImg(), 450, imageInfo.getHeight()));
        byte[] addImageWatermark = imageService.addImageWatermark(image.getImgNormal());
        //byte[] addImageWatermark1 = imageService.addImageWatermark(image.getImgThumbnail());
		image.setImgNormal(addImageWatermark);
		image.setImgNormal1(addImageWatermark);
		image.setImgThumbnail(addImageWatermark);
		image.setImgThumbnail1(addImageWatermark);
		image.setMainImg(addImageWatermark);
		
		Ads result = adsRepository.save(ads);
        image.setAds(result);
		imageRepository.save(image);
    	
    }

}
