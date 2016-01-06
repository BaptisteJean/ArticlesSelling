package com.thedevbrige.articleselling.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.thedevbrige.articleselling.domain.Pays;
import com.thedevbrige.articleselling.repository.PaysRepository;
import com.thedevbrige.articleselling.web.rest.util.HeaderUtil;
import com.thedevbrige.articleselling.web.rest.util.PaginationUtil;
import com.thedevbrige.articleselling.web.rest.dto.PaysDTO;
import com.thedevbrige.articleselling.web.rest.mapper.PaysMapper;
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
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * REST controller for managing Pays.
 */
@RestController
@RequestMapping("/api")
public class PaysResource {

    private final Logger log = LoggerFactory.getLogger(PaysResource.class);

    @Inject
    private PaysRepository paysRepository;

    @Inject
    private PaysMapper paysMapper;

    /**
     * POST  /payss -> Create a new pays.
     */
    @RequestMapping(value = "/payss",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<PaysDTO> createPays(@RequestBody PaysDTO paysDTO) throws URISyntaxException {
        log.debug("REST request to save Pays : {}", paysDTO);
        if (paysDTO.getId() != null) {
            return ResponseEntity.badRequest().header("Failure", "A new pays cannot already have an ID").body(null);
        }
        Pays pays = paysMapper.paysDTOToPays(paysDTO);
        Pays result = paysRepository.save(pays);
        return ResponseEntity.created(new URI("/api/payss/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("pays", result.getId().toString()))
            .body(paysMapper.paysToPaysDTO(result));
    }

    /**
     * PUT  /payss -> Updates an existing pays.
     */
    @RequestMapping(value = "/payss",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<PaysDTO> updatePays(@RequestBody PaysDTO paysDTO) throws URISyntaxException {
        log.debug("REST request to update Pays : {}", paysDTO);
        if (paysDTO.getId() == null) {
            return createPays(paysDTO);
        }
        Pays pays = paysMapper.paysDTOToPays(paysDTO);
        Pays result = paysRepository.save(pays);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("pays", paysDTO.getId().toString()))
            .body(paysMapper.paysToPaysDTO(result));
    }

    /**
     * GET  /payss -> get all the payss.
     */
    @RequestMapping(value = "/payss",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    @Transactional(readOnly = true)
    public ResponseEntity<List<PaysDTO>> getAllPayss(Pageable pageable)
        throws URISyntaxException {
        Page<Pays> page = paysRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/payss");
        return new ResponseEntity<>(page.getContent().stream()
            .map(paysMapper::paysToPaysDTO)
            .collect(Collectors.toCollection(LinkedList::new)), headers, HttpStatus.OK);
    }

    /**
     * GET  /payss -> get all the payss.
     */
    @RequestMapping(value = "/pays/findAll",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<Pays> getAll() {
        List<Pays> page = paysRepository.findAll();
        return page;
    }

    /**
     * GET  /payss/:id -> get the "id" pays.
     */
    @RequestMapping(value = "/payss/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<PaysDTO> getPays(@PathVariable Long id) {
        log.debug("REST request to get Pays : {}", id);
        return Optional.ofNullable(paysRepository.findOne(id))
            .map(paysMapper::paysToPaysDTO)
            .map(paysDTO -> new ResponseEntity<>(
                paysDTO,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /payss/:id -> delete the "id" pays.
     */
    @RequestMapping(value = "/payss/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deletePays(@PathVariable Long id) {
        log.debug("REST request to delete Pays : {}", id);
        paysRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("pays", id.toString())).build();
    }
}
