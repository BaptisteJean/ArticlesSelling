package com.thedevbrige.articleselling.service;


import com.thedevbrige.articleselling.domain.Categorie;
import com.thedevbrige.articleselling.repository.CategorieRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

@Service
@Transactional
public class DataInitializeService {

@Inject
CategorieRepository categorieRepository;

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
        }

	}

    public Categorie createCateg(String nameCategorie, String parent){
        Categorie categImg = new Categorie(nameCategorie, parent);
        return categorieRepository.saveAndFlush(categImg);
    }

}
