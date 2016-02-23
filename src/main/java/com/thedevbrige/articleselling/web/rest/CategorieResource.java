package com.thedevbrige.articleselling.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.thedevbrige.articleselling.domain.Categorie;
import com.thedevbrige.articleselling.repository.CategorieRepository;
import com.thedevbrige.articleselling.web.rest.util.HeaderUtil;
import com.thedevbrige.articleselling.web.rest.util.PaginationUtil;
import com.thedevbrige.articleselling.web.rest.dto.CategorieDTO;
import com.thedevbrige.articleselling.web.rest.mapper.CategorieMapper;









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
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * REST controller for managing Categorie.
 */
@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/api")
public class CategorieResource {

    private final Logger log = LoggerFactory.getLogger(CategorieResource.class);

    @Inject
    private CategorieRepository categorieRepository;

    @Inject
    private CategorieMapper categorieMapper;

    /**
     * POST  /categories -> Create a new categorie.
     */
    @RequestMapping(value = "/categories",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<CategorieDTO> createCategorie(@Valid @RequestBody CategorieDTO categorieDTO) throws URISyntaxException {
        log.debug("REST request to save Categorie : {}", categorieDTO);
        if (categorieDTO.getId() != null) {
            return ResponseEntity.badRequest().header("Failure", "A new categorie cannot already have an ID").body(null);
        }
        Categorie categorie = categorieMapper.categorieDTOToCategorie(categorieDTO);
        Categorie result = categorieRepository.save(categorie);
        return ResponseEntity.created(new URI("/api/categories/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("categorie", result.getId().toString()))
            .body(categorieMapper.categorieToCategorieDTO(result));
    }

    /**
     * PUT  /categories -> Updates an existing categorie.
     */
    @RequestMapping(value = "/categories",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<CategorieDTO> updateCategorie(@Valid @RequestBody CategorieDTO categorieDTO) throws URISyntaxException {
        log.debug("REST request to update Categorie : {}", categorieDTO);
        if (categorieDTO.getId() == null) {
            return createCategorie(categorieDTO);
        }
        Categorie categorie = categorieMapper.categorieDTOToCategorie(categorieDTO);
        Categorie result = categorieRepository.save(categorie);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("categorie", categorieDTO.getId().toString()))
            .body(categorieMapper.categorieToCategorieDTO(result));
    }

    /**
     * GET  /categories -> get all the categories.
     */
    @RequestMapping(value = "/categories",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    @Transactional(readOnly = true)
    public ResponseEntity<List<CategorieDTO>> getAllCategories(Pageable pageable)
        throws URISyntaxException {
        Page<Categorie> page = categorieRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/categories");
        return new ResponseEntity<>(page.getContent().stream()
            .map(categorieMapper::categorieToCategorieDTO)
            .collect(Collectors.toCollection(LinkedList::new)), headers, HttpStatus.OK);
    }

    /**
     * GET  /categories/:id -> get the "id" categorie.
     */
    @RequestMapping(value = "/categories/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<CategorieDTO> getCategorie(@PathVariable Long id) {
        log.debug("REST request to get Categorie : {}", id);

        return Optional.ofNullable(categorieRepository.findOne(id))
            .map(categorieMapper::categorieToCategorieDTO)
            .map(categorieDTO -> new ResponseEntity<>(
                categorieDTO,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /categories/:id -> delete the "id" categorie.
     */
    @RequestMapping(value = "/categories/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteCategorie(@PathVariable Long id) {
        log.debug("REST request to delete Categorie : {}", id);
        categorieRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("categorie", id.toString())).build();
    }

    //////////////////////////////
    /**
     * put  /categories/:id -> get the "id" categorie.
     */
    @RequestMapping(value = "/categories/addvue/{id}",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void getCategorieAddnumberOfvu(@PathVariable Long id) {
        log.debug("REST request to get Categorie : {}", id);
         Categorie cat= categorieRepository.findById(id);
         Long nuvue = cat.getNbre_vu();
         if(nuvue!=null){
         nuvue+=1;
         cat.setNbre_vu(nuvue);
         }else {
        	 nuvue=(long) 1;
        	 cat.setNbre_vu(nuvue);
         }

         categorieRepository.save(cat);
    }

    /*
     * get amount of click on parent category
     * */
    @RequestMapping(value = "/categories/parentVu/{parent}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
       @Timed
       public String getCountForAllparent(@PathVariable String parent){
    	List<Categorie> list= categorieRepository.findAll();
    	ArrayList<String> listOfparent = new ArrayList<String> ();
    	String jesonobject=null;


    	for(int i=0; i<list.size(); i++){
    		if(!listOfparent.contains(list.get(i).getParent()) )
    		listOfparent.add(list.get(i).getParent());
    	}
    	String[][] tab=catparentByVu( listOfparent);
    	int j=0,i = 0,find=0;
    	do{
    		if(parent.equals(tab[0][i])){
    			j=i;find=1;
    		}
    		i++;
    	}while (i<listOfparent.size()&& find==0);

    	return tab[1][j];
    	}


    public long getCountForparent( String parent){
    	List<Categorie> list= categorieRepository.findByParent(parent);
    	long amtvu=0;
    	for(int i=0; i<list.size(); i++){
    		if(list.get(i).getNbre_vu()!=null)
    		amtvu+=list.get(i).getNbre_vu();
    	}
    	//return categorieRepository.countByParent(parent);
    	return amtvu;
    }

    public String[][] catparentByVu(ArrayList<String> listOfparent){
    	String[][] tab= new String[listOfparent.size()][listOfparent.size()];
    	for(int i=0; i<listOfparent.size(); i++){
    		tab[0][i]=listOfparent.get(i);
    		tab[1][i]=getCountForparent(listOfparent.get(i))+"";
    	}
		return tab;

    }
}
