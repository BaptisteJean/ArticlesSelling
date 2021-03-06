package com.thedevbrige.articleselling.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.thedevbrige.articleselling.domain.Ads;
import com.thedevbrige.articleselling.domain.Image;
import com.thedevbrige.articleselling.repository.ImageRepository;
import com.thedevbrige.articleselling.service.ImageService;
import com.thedevbrige.articleselling.service.AdsService;
import com.thedevbrige.articleselling.web.rest.util.HeaderUtil;
import com.thedevbrige.articleselling.web.rest.util.PaginationUtil;
import com.thedevbrige.articleselling.web.rest.dto.ImageDTO;
import com.thedevbrige.articleselling.web.rest.mapper.ImageMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.validation.Valid;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * REST controller for managing Image.
 */
@RestController
@RequestMapping("/api")
public class ImageResource {

    private final Logger log = LoggerFactory.getLogger(ImageResource.class);

    @Inject
    private ImageRepository imageRepository;
    
    @Inject
    private ImageMapper imageMapper;

    @Inject
    private AdsService adsService;

    private boolean ok = true;

    @Inject
    private ImageService imageService;


    /**
     * POST  /images -> Create a new image.
     */
    @RequestMapping(value = "/images",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<ImageDTO> createImage(@Valid @RequestBody ImageDTO imageDTO) throws URISyntaxException {
        log.debug("REST request to save Image : {}", imageDTO);
        if (imageDTO.getId() != null) {
            return ResponseEntity.badRequest().header("Failure", "A new image cannot already have an ID").body(null);
        }
        Image image = imageMapper.imageDTOToImage(imageDTO);
        Image result = new Image();
        while(ok == true){
        	if(adsService.getSemaphore() == true){
	        	image.setAds(adsService.getAds());
//	        	if(image.getMainImg() == null){
//	        		image.
//	        	}
	        	result = imageService.createImage(image);
	        	adsService.setSemaphore(false);
	        	ok = false;
        	}
        	else{
        		ok = true;
        	}
        }
        ok = true;

        return ResponseEntity.created(new URI("/api/images/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("image", result.getId().toString()))
            .body(imageMapper.imageToImageDTO(result));
    }

    /**
     * PUT  /images -> Updates an existing image.
     */
    @RequestMapping(value = "/images",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<ImageDTO> updateImage(@Valid @RequestBody ImageDTO imageDTO) throws URISyntaxException {
        log.debug("REST request to update Image : {}", imageDTO);
        if (imageDTO.getId() == null) {
            return createImage(imageDTO);
        }
        Image image = imageMapper.imageDTOToImage(imageDTO);
        Image result = imageRepository.save(image);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("image", imageDTO.getId().toString()))
            .body(imageMapper.imageToImageDTO(result));
    }

    /**
     * GET  /images -> get all the images.
     */
    @RequestMapping(value = "/images",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    @Transactional(readOnly = true)
    public ResponseEntity<List<ImageDTO>> getAllImages(Pageable pageable)
        throws URISyntaxException {
        Page<Image> page = imageRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/images");
        return new ResponseEntity<>(page.getContent().stream()
            .map(imageMapper::imageToImageDTO)
            .collect(Collectors.toCollection(LinkedList::new)), headers, HttpStatus.OK);
    }

    /**
     * GET  /images/:id -> get the "id" image.
     */
    @RequestMapping(value = "/images/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<ImageDTO> getImage(@PathVariable String id) {
        log.debug("REST request to get Image : {}", id);
        return Optional.ofNullable(imageRepository.findById(id))
            .map(imageMapper::imageToImageDTO)
            .map(imageDTO -> new ResponseEntity<>(
                imageDTO,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    
    
    
    //Top 1O des articles les plus vus
    @RequestMapping(value = "/top10Images",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
        @Timed
        public List <Image> top10Ads(){
          List<Image> top10 = new ArrayList();
          top10 = imageRepository.findFirst10ByOrderByAdsNbreVueDesc();
    	return top10;
        }
    
    

    /**
     * DELETE  /images/:id -> delete the "id" image.
     */
    @RequestMapping(value = "/images/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteImage(@PathVariable String id) {
        log.debug("REST request to delete Image : {}", id);
        imageRepository.delete(imageRepository.findById(id));
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("image", id.toString())).build();
    }
    /**
     * get  /images/:id -> get the "id" image.
     */
    @RequestMapping(value = "/imagesads/{adsId}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<byte[]> getImageAds(@PathVariable String adsId) throws IOException {
        log.debug("REST request to get Image ads : {}", adsId);

        Image imageads = imageRepository.findByAdsId(adsId);

        byte[] img = imageads.getImgThumbnail();

        final HttpHeaders headers = new HttpHeaders();

        String mime = imageService.checkMime(img);

        headers.setContentType(MediaType.parseMediaType(mime));

        return new ResponseEntity<byte[]>(img, headers, HttpStatus.CREATED);

    }
    /**
     * get  /images/:id -> get the "id" image.
     */
    @RequestMapping(value = "/allimagesads/{adsId}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public Image getAllImageAds(@PathVariable String adsId){

        return imageRepository.findByAdsId(adsId);
    }
}
