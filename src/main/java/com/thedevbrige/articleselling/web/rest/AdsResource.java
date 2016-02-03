package com.thedevbrige.articleselling.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.thedevbrige.articleselling.domain.Ads;
import com.thedevbrige.articleselling.repository.AdsRepository;
import com.thedevbrige.articleselling.security.SecurityUtils;
import com.thedevbrige.articleselling.service.AdsService;
import com.thedevbrige.articleselling.web.rest.util.HeaderUtil;
import com.thedevbrige.articleselling.web.rest.util.PaginationUtil;
import com.thedevbrige.articleselling.web.rest.dto.AdsDTO;
import com.thedevbrige.articleselling.web.rest.mapper.AdsMapper;






//import org.joda.time.LocalDate;
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

import java.net.URI;
import java.net.URISyntaxException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * REST controller for managing Ads.
 */
@RestController
@RequestMapping("/api")
public class AdsResource {

    private final Logger log = LoggerFactory.getLogger(AdsResource.class);

    @Inject
    private AdsRepository adsRepository;

    @Inject
    private AdsMapper adsMapper;

    @Inject
    private AdsService adsService;

    /**
     * POST  /adss -> Create a new ads.
     */
    @RequestMapping(value = "/adss",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<AdsDTO> createAds(@Valid @RequestBody AdsDTO adsDTO) throws URISyntaxException {
        log.debug("REST request to save Ads : {}", adsDTO);
        if (adsDTO.getId() != null) {
            return ResponseEntity.badRequest().header("Failure", "Thi image already exist").body(null);
        }
        Ads ads = adsMapper.adsDTOToAds(adsDTO);
        ads.setLogin(SecurityUtils.getCurrentUserLogin());
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        ads.setDateAjout(dateFormat.format(date));
        adsService.generateId();
        ads.setId(adsService.getId());
        Ads result = adsRepository.save(ads);
        adsService.setAds(result);
        adsService.setSemaphore(true);
        return ResponseEntity.created(new URI("/api/adss/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("ads", result.getId().toString()))
            .body(adsMapper.adsToAdsDTO(result));
    }

    /**
     * PUT  /adss -> Updates an existing ads.
     */
    @RequestMapping(value = "/adss",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<AdsDTO> updateAds(@Valid @RequestBody AdsDTO adsDTO) throws URISyntaxException {
        log.debug("REST request to update Ads : {}", adsDTO);
        if (adsDTO.getId() == null) {
            return createAds(adsDTO);
        }
        Ads ads = adsMapper.adsDTOToAds(adsDTO);
        Ads result = adsRepository.save(ads);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("ads", adsDTO.getId().toString()))
            .body(adsMapper.adsToAdsDTO(result));
    }


    //incrémentation et sauvegarde du nbre de vues à partir de l'id venant du front
    @RequestMapping(value = "/nbreVue/ads/{id}",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
        @Timed
        public void nbreVue(@PathVariable String id){
        	Ads ads = new Ads();
        	ads = adsRepository.findById(id);
        	Long nbreVue = ads.getNbreVue();
        	if(nbreVue != null){
        	nbreVue = nbreVue + 1;
        	ads.setNbreVue(nbreVue);
        	}
        	else{
        		nbreVue = (long)1;
        		ads.setNbreVue(nbreVue);
        	}
        	adsRepository.save(ads);
        }




    /**
     * GET  /adss -> get all the adss.
     */
    @RequestMapping(value = "/adss",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    @Transactional(readOnly = true)
    public ResponseEntity<List<AdsDTO>> getAllAdss(Pageable pageable)
        throws URISyntaxException {
        Page<Ads> page = adsRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/adss");
        return new ResponseEntity<>(page.getContent().stream()
            .map(adsMapper::adsToAdsDTO)
            .collect(Collectors.toCollection(LinkedList::new)), headers, HttpStatus.OK);
    }



    /**
     * GET  /adss/:id -> get the "id" ads.
     */
    @RequestMapping(value = "/adss/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<AdsDTO> getAds(@PathVariable String id) {
        log.debug("REST request to get Ads : {}", id);
        return Optional.ofNullable(adsRepository.findById(id))
            .map(adsMapper::adsToAdsDTO)
            .map(adsDTO -> new ResponseEntity<>(
                adsDTO,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /adss/:id -> delete the "id" ads.
     */
    @RequestMapping(value = "/adss/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteAds(@PathVariable String id) {
        log.debug("REST request to delete Ads : {}", id);
        adsRepository.delete(adsRepository.findById(id));
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("ads", id.toString())).build();
    }

    /**
     * DELETE  /delete/:id -> delete the "id" ads.
     */
    @RequestMapping(value = "/delete/{id}",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void delete(@PathVariable String id) {
        log.debug("REST request to delete : {}", id);
        Ads ads = adsRepository.findById(id);
        adsRepository.delete(ads);
    }

    /**
     * PUT  /ads/blockedOrDeblocked/:id -> get the "id" user.
     */
    @RequestMapping(value = "/ads/blockedOrDeblocked/{id}",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void blockedOrDeblocked(@PathVariable String id) {
        log.debug("REST request to blocked Or Deblocked ads : {}", id);

        Ads ads = adsRepository.findById(id);

        if(ads.getBlocked() == true) {

            ads.setBlocked(false);

        }else {

            ads.setBlocked(true);
        }

        adsRepository.save(ads);

    }

    /**
     * GET  /myads
     */
    @RequestMapping(value = "/myads",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<Ads> getMyAds() {
        log.debug("REST request to get all my ads : {}");
        boolean blocked = false;
        List<Ads> myads = adsRepository.findByLoginAndBlocked(SecurityUtils.getCurrentUserLogin(),blocked);
        return myads;
    }

}
