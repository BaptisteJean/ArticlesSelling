package com.thedevbrige.articleselling.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.thedevbrige.articleselling.domain.Pays;
import com.thedevbrige.articleselling.domain.Ville;
import com.thedevbrige.articleselling.repository.PaysRepository;
import com.thedevbrige.articleselling.repository.VilleRepository;
import com.thedevbrige.articleselling.web.rest.util.HeaderUtil;
import com.thedevbrige.articleselling.web.rest.util.PaginationUtil;
import com.thedevbrige.articleselling.web.rest.dto.VilleDTO;
import com.thedevbrige.articleselling.web.rest.mapper.VilleMapper;
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
import java.net.URI;
import java.net.URISyntaxException;
import java.util.LinkedList;
import java.util.*;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * REST controller for managing Ville.
 */
@RestController
@RequestMapping("/api")
public class VilleResource {

    private final Logger log = LoggerFactory.getLogger(VilleResource.class);

    @Inject
    private VilleRepository villeRepository;

    @Inject
    private PaysRepository paysRepository;

    @Inject
    private VilleMapper villeMapper;

    /**
     * POST  /villes -> Create a new ville.
     */
    @RequestMapping(value = "/villes",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<VilleDTO> createVille(@RequestBody VilleDTO villeDTO) throws URISyntaxException {
        log.debug("REST request to save Ville : {}", villeDTO);
        if (villeDTO.getId() != null) {
            return ResponseEntity.badRequest().header("Failure", "A new ville cannot already have an ID").body(null);
        }
        Ville ville = villeMapper.villeDTOToVille(villeDTO);
        Ville result = villeRepository.save(ville);
        return ResponseEntity.created(new URI("/api/villes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("ville", result.getId().toString()))
            .body(villeMapper.villeToVilleDTO(result));
    }

    /**
     * PUT  /villes -> Updates an existing ville.
     */
    @RequestMapping(value = "/villes",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<VilleDTO> updateVille(@RequestBody VilleDTO villeDTO) throws URISyntaxException {
        log.debug("REST request to update Ville : {}", villeDTO);
        if (villeDTO.getId() == null) {
            return createVille(villeDTO);
        }
        Ville ville = villeMapper.villeDTOToVille(villeDTO);
        Ville result = villeRepository.save(ville);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("ville", villeDTO.getId().toString()))
            .body(villeMapper.villeToVilleDTO(result));
    }

    /**
     * GET  /villes -> get all the villes.
     */
    @RequestMapping(value = "/villes",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    @Transactional(readOnly = true)
    public ResponseEntity<List<VilleDTO>> getAllVilles(Pageable pageable)
        throws URISyntaxException {
        Page<Ville> page = villeRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/villes");
        return new ResponseEntity<>(page.getContent().stream()
            .map(villeMapper::villeToVilleDTO)
            .collect(Collectors.toCollection(LinkedList::new)), headers, HttpStatus.OK);
    }

    /**
     * GET  /villes -> get all the villes.
     */
    @RequestMapping(value = "/villes/findAll",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<Ville> getAll(){
        List<Ville> page = villeRepository.findAll();
        return page;
    }

    /**
     * GET  /villes -> get all the villes.
     */
    @RequestMapping(value = "/listvillesforcountry/{pays}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<Ville> getVillesForPays(@PathVariable String pays){
        List<Ville> list = new ArrayList<Ville>();
       Pays pays1 = paysRepository.findByNamePaysEquals(pays);
        list = villeRepository.findByPays(pays1);
        return list;
    }


    /**
     * GET  /villes/:id -> get the "id" ville.
     */
    @RequestMapping(value = "/villes/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<VilleDTO> getVille(@PathVariable Long id) {
        log.debug("REST request to get Ville : {}", id);
        return Optional.ofNullable(villeRepository.findOne(id))
            .map(villeMapper::villeToVilleDTO)
            .map(villeDTO -> new ResponseEntity<>(
                villeDTO,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /villes/:id -> delete the "id" ville.
     */
    @RequestMapping(value = "/villes/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteVille(@PathVariable Long id) {
        log.debug("REST request to delete Ville : {}", id);
        villeRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("ville", id.toString())).build();
    }
}
