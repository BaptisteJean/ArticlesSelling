package com.thedevbrige.articleselling.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.thedevbrige.articleselling.domain.Ads;

@Service
public class AdsService {

	private String id = null;
	
	private boolean semaphore = false; 
	
	private Ads ads;
	
	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    public boolean getSemaphore(){
    	return semaphore;
    }
    
    public void setSemaphore(boolean semaphore){
    	this.semaphore = semaphore;
    }
    
    public void generateId(){
    	id = UUID.randomUUID().toString();
    }
    
    public Ads getAds() {
        return ads;
    }

    public void setAds(Ads ads) {
        this.ads = ads;
    }
}
