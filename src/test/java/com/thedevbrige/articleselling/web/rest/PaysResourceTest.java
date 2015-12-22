package com.thedevbrige.articleselling.web.rest;

import com.thedevbrige.articleselling.Application;
import com.thedevbrige.articleselling.domain.Pays;
import com.thedevbrige.articleselling.repository.PaysRepository;
import com.thedevbrige.articleselling.web.rest.dto.PaysDTO;
import com.thedevbrige.articleselling.web.rest.mapper.PaysMapper;

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
 * Test class for the PaysResource REST controller.
 *
 * @see PaysResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class PaysResourceTest {

    private static final String DEFAULT_NAME_PAYS = "AAAAA";
    private static final String UPDATED_NAME_PAYS = "BBBBB";

    @Inject
    private PaysRepository paysRepository;

    @Inject
    private PaysMapper paysMapper;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restPaysMockMvc;

    private Pays pays;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        PaysResource paysResource = new PaysResource();
        ReflectionTestUtils.setField(paysResource, "paysRepository", paysRepository);
        ReflectionTestUtils.setField(paysResource, "paysMapper", paysMapper);
        this.restPaysMockMvc = MockMvcBuilders.standaloneSetup(paysResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        pays = new Pays();
        pays.setNamePays(DEFAULT_NAME_PAYS);
    }

    @Test
    @Transactional
    public void createPays() throws Exception {
        int databaseSizeBeforeCreate = paysRepository.findAll().size();

        // Create the Pays
        PaysDTO paysDTO = paysMapper.paysToPaysDTO(pays);

        restPaysMockMvc.perform(post("/api/payss")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(paysDTO)))
                .andExpect(status().isCreated());

        // Validate the Pays in the database
        List<Pays> payss = paysRepository.findAll();
        assertThat(payss).hasSize(databaseSizeBeforeCreate + 1);
        Pays testPays = payss.get(payss.size() - 1);
        assertThat(testPays.getNamePays()).isEqualTo(DEFAULT_NAME_PAYS);
    }

    @Test
    @Transactional
    public void getAllPayss() throws Exception {
        // Initialize the database
        paysRepository.saveAndFlush(pays);

        // Get all the payss
        restPaysMockMvc.perform(get("/api/payss"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(pays.getId().intValue())))
                .andExpect(jsonPath("$.[*].namePays").value(hasItem(DEFAULT_NAME_PAYS.toString())));
    }

    @Test
    @Transactional
    public void getPays() throws Exception {
        // Initialize the database
        paysRepository.saveAndFlush(pays);

        // Get the pays
        restPaysMockMvc.perform(get("/api/payss/{id}", pays.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(pays.getId().intValue()))
            .andExpect(jsonPath("$.namePays").value(DEFAULT_NAME_PAYS.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingPays() throws Exception {
        // Get the pays
        restPaysMockMvc.perform(get("/api/payss/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePays() throws Exception {
        // Initialize the database
        paysRepository.saveAndFlush(pays);

		int databaseSizeBeforeUpdate = paysRepository.findAll().size();

        // Update the pays
        pays.setNamePays(UPDATED_NAME_PAYS);
        PaysDTO paysDTO = paysMapper.paysToPaysDTO(pays);

        restPaysMockMvc.perform(put("/api/payss")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(paysDTO)))
                .andExpect(status().isOk());

        // Validate the Pays in the database
        List<Pays> payss = paysRepository.findAll();
        assertThat(payss).hasSize(databaseSizeBeforeUpdate);
        Pays testPays = payss.get(payss.size() - 1);
        assertThat(testPays.getNamePays()).isEqualTo(UPDATED_NAME_PAYS);
    }

    @Test
    @Transactional
    public void deletePays() throws Exception {
        // Initialize the database
        paysRepository.saveAndFlush(pays);

		int databaseSizeBeforeDelete = paysRepository.findAll().size();

        // Get the pays
        restPaysMockMvc.perform(delete("/api/payss/{id}", pays.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<Pays> payss = paysRepository.findAll();
        assertThat(payss).hasSize(databaseSizeBeforeDelete - 1);
    }
}
