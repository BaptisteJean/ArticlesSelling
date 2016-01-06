package com.thedevbrige.articleselling.web.rest;

import com.thedevbrige.articleselling.Application;
import com.thedevbrige.articleselling.domain.Ville;
import com.thedevbrige.articleselling.repository.VilleRepository;
import com.thedevbrige.articleselling.web.rest.dto.VilleDTO;
import com.thedevbrige.articleselling.web.rest.mapper.VilleMapper;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.hamcrest.Matchers.hasItem;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


/**
 * Test class for the VilleResource REST controller.
 *
 * @see VilleResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class VilleResourceTest {

    private static final String DEFAULT_NAME_VILLE = "AAAAA";
    private static final String UPDATED_NAME_VILLE = "BBBBB";

    @Inject
    private VilleRepository villeRepository;

    @Inject
    private VilleMapper villeMapper;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restVilleMockMvc;

    private Ville ville;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        VilleResource villeResource = new VilleResource();
        ReflectionTestUtils.setField(villeResource, "villeRepository", villeRepository);
        ReflectionTestUtils.setField(villeResource, "villeMapper", villeMapper);
        this.restVilleMockMvc = MockMvcBuilders.standaloneSetup(villeResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        ville = new Ville();
        ville.setNameVille(DEFAULT_NAME_VILLE);
    }

    @Test
    @Transactional
    public void createVille() throws Exception {
        int databaseSizeBeforeCreate = villeRepository.findAll().size();

        // Create the Ville
        VilleDTO villeDTO = villeMapper.villeToVilleDTO(ville);

        restVilleMockMvc.perform(post("/api/villes")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(villeDTO)))
                .andExpect(status().isCreated());

        // Validate the Ville in the database
        List<Ville> villes = villeRepository.findAll();
        assertThat(villes).hasSize(databaseSizeBeforeCreate + 1);
        Ville testVille = villes.get(villes.size() - 1);
        assertThat(testVille.getNameVille()).isEqualTo(DEFAULT_NAME_VILLE);
    }

    @Test
    @Transactional
    public void getAllVilles() throws Exception {
        // Initialize the database
        villeRepository.saveAndFlush(ville);

        // Get all the villes
        restVilleMockMvc.perform(get("/api/villes"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(ville.getId().intValue())))
                .andExpect(jsonPath("$.[*].nameVille").value(hasItem(DEFAULT_NAME_VILLE.toString())));
    }

    @Test
    @Transactional
    public void getVille() throws Exception {
        // Initialize the database
        villeRepository.saveAndFlush(ville);

        // Get the ville
        restVilleMockMvc.perform(get("/api/villes/{id}", ville.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(ville.getId().intValue()))
            .andExpect(jsonPath("$.nameVille").value(DEFAULT_NAME_VILLE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingVille() throws Exception {
        // Get the ville
        restVilleMockMvc.perform(get("/api/villes/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateVille() throws Exception {
        // Initialize the database
        villeRepository.saveAndFlush(ville);

		int databaseSizeBeforeUpdate = villeRepository.findAll().size();

        // Update the ville
        ville.setNameVille(UPDATED_NAME_VILLE);
        VilleDTO villeDTO = villeMapper.villeToVilleDTO(ville);

        restVilleMockMvc.perform(put("/api/villes")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(villeDTO)))
                .andExpect(status().isOk());

        // Validate the Ville in the database
        List<Ville> villes = villeRepository.findAll();
        assertThat(villes).hasSize(databaseSizeBeforeUpdate);
        Ville testVille = villes.get(villes.size() - 1);
        assertThat(testVille.getNameVille()).isEqualTo(UPDATED_NAME_VILLE);
    }

    @Test
    @Transactional
    public void deleteVille() throws Exception {
        // Initialize the database
        villeRepository.saveAndFlush(ville);

		int databaseSizeBeforeDelete = villeRepository.findAll().size();

        // Get the ville
        restVilleMockMvc.perform(delete("/api/villes/{id}", ville.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<Ville> villes = villeRepository.findAll();
        assertThat(villes).hasSize(databaseSizeBeforeDelete - 1);
    }
}
