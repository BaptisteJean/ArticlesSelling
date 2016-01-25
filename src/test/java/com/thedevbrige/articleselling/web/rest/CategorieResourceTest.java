package com.thedevbrige.articleselling.web.rest;

import com.thedevbrige.articleselling.Application;
import com.thedevbrige.articleselling.domain.Categorie;
import com.thedevbrige.articleselling.repository.CategorieRepository;
import com.thedevbrige.articleselling.web.rest.dto.CategorieDTO;
import com.thedevbrige.articleselling.web.rest.mapper.CategorieMapper;

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
 * Test class for the CategorieResource REST controller.
 *
 * @see CategorieResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class CategorieResourceTest {

    private static final String DEFAULT_NAME_CATEGORIE = "AAAAA";
    private static final String UPDATED_NAME_CATEGORIE = "BBBBB";
    private static final String DEFAULT_PARENT = "AAAAA";
    private static final String UPDATED_PARENT = "BBBBB";
    private static final String DEFAULT_DESCRIPTION = "AAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBB";

    private static final Long DEFAULT_NBRE_ADS = 1L;
    private static final Long UPDATED_NBRE_ADS = 2L;

    @Inject
    private CategorieRepository categorieRepository;

    @Inject
    private CategorieMapper categorieMapper;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restCategorieMockMvc;

    private Categorie categorie;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        CategorieResource categorieResource = new CategorieResource();
        ReflectionTestUtils.setField(categorieResource, "categorieRepository", categorieRepository);
        ReflectionTestUtils.setField(categorieResource, "categorieMapper", categorieMapper);
        this.restCategorieMockMvc = MockMvcBuilders.standaloneSetup(categorieResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        categorie = new Categorie();
        categorie.setNameCategorie(DEFAULT_NAME_CATEGORIE);
        categorie.setParent(DEFAULT_PARENT);
        categorie.setDescription(DEFAULT_DESCRIPTION);
        categorie.setNbreAds(DEFAULT_NBRE_ADS);
    }

    @Test
    @Transactional
    public void createCategorie() throws Exception {
        int databaseSizeBeforeCreate = categorieRepository.findAll().size();

        // Create the Categorie
        CategorieDTO categorieDTO = categorieMapper.categorieToCategorieDTO(categorie);

        restCategorieMockMvc.perform(post("/api/categories")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(categorieDTO)))
                .andExpect(status().isCreated());

        // Validate the Categorie in the database
        List<Categorie> categories = categorieRepository.findAll();
        assertThat(categories).hasSize(databaseSizeBeforeCreate + 1);
        Categorie testCategorie = categories.get(categories.size() - 1);
        assertThat(testCategorie.getNameCategorie()).isEqualTo(DEFAULT_NAME_CATEGORIE);
        assertThat(testCategorie.getParent()).isEqualTo(DEFAULT_PARENT);
        assertThat(testCategorie.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testCategorie.getNbreAds()).isEqualTo(DEFAULT_NBRE_ADS);
    }

    @Test
    @Transactional
    public void checkNameCategorieIsRequired() throws Exception {
        int databaseSizeBeforeTest = categorieRepository.findAll().size();
        // set the field null
        categorie.setNameCategorie(null);

        // Create the Categorie, which fails.
        CategorieDTO categorieDTO = categorieMapper.categorieToCategorieDTO(categorie);

        restCategorieMockMvc.perform(post("/api/categories")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(categorieDTO)))
                .andExpect(status().isBadRequest());

        List<Categorie> categories = categorieRepository.findAll();
        assertThat(categories).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllCategories() throws Exception {
        // Initialize the database
        categorieRepository.saveAndFlush(categorie);

        // Get all the categories
        restCategorieMockMvc.perform(get("/api/categories"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(categorie.getId().intValue())))
                .andExpect(jsonPath("$.[*].nameCategorie").value(hasItem(DEFAULT_NAME_CATEGORIE.toString())))
                .andExpect(jsonPath("$.[*].parent").value(hasItem(DEFAULT_PARENT.toString())))
                .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
                .andExpect(jsonPath("$.[*].nbreAds").value(hasItem(DEFAULT_NBRE_ADS.intValue())));
    }

    @Test
    @Transactional
    public void getCategorie() throws Exception {
        // Initialize the database
        categorieRepository.saveAndFlush(categorie);

        // Get the categorie
        restCategorieMockMvc.perform(get("/api/categories/{id}", categorie.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(categorie.getId().intValue()))
            .andExpect(jsonPath("$.nameCategorie").value(DEFAULT_NAME_CATEGORIE.toString()))
            .andExpect(jsonPath("$.parent").value(DEFAULT_PARENT.toString()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.nbreAds").value(DEFAULT_NBRE_ADS.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingCategorie() throws Exception {
        // Get the categorie
        restCategorieMockMvc.perform(get("/api/categories/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCategorie() throws Exception {
        // Initialize the database
        categorieRepository.saveAndFlush(categorie);

		int databaseSizeBeforeUpdate = categorieRepository.findAll().size();

        // Update the categorie
        categorie.setNameCategorie(UPDATED_NAME_CATEGORIE);
        categorie.setParent(UPDATED_PARENT);
        categorie.setDescription(UPDATED_DESCRIPTION);
        categorie.setNbreAds(UPDATED_NBRE_ADS);
        CategorieDTO categorieDTO = categorieMapper.categorieToCategorieDTO(categorie);

        restCategorieMockMvc.perform(put("/api/categories")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(categorieDTO)))
                .andExpect(status().isOk());

        // Validate the Categorie in the database
        List<Categorie> categories = categorieRepository.findAll();
        assertThat(categories).hasSize(databaseSizeBeforeUpdate);
        Categorie testCategorie = categories.get(categories.size() - 1);
        assertThat(testCategorie.getNameCategorie()).isEqualTo(UPDATED_NAME_CATEGORIE);
        assertThat(testCategorie.getParent()).isEqualTo(UPDATED_PARENT);
        assertThat(testCategorie.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testCategorie.getNbreAds()).isEqualTo(UPDATED_NBRE_ADS);
    }

    @Test
    @Transactional
    public void deleteCategorie() throws Exception {
        // Initialize the database
        categorieRepository.saveAndFlush(categorie);

		int databaseSizeBeforeDelete = categorieRepository.findAll().size();

        // Get the categorie
        restCategorieMockMvc.perform(delete("/api/categories/{id}", categorie.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<Categorie> categories = categorieRepository.findAll();
        assertThat(categories).hasSize(databaseSizeBeforeDelete - 1);
    }
}
