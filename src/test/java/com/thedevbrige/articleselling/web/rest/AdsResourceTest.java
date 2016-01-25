package com.thedevbrige.articleselling.web.rest;

import com.thedevbrige.articleselling.Application;
import com.thedevbrige.articleselling.domain.Ads;
import com.thedevbrige.articleselling.repository.AdsRepository;
import com.thedevbrige.articleselling.web.rest.dto.AdsDTO;
import com.thedevbrige.articleselling.web.rest.mapper.AdsMapper;

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
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


/**
 * Test class for the AdsResource REST controller.
 *
 * @see AdsResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class AdsResourceTest {

    private static final String DEFAULT_NAME_ADS = "AAAAA";
    private static final String UPDATED_NAME_ADS = "BBBBB";
    private static final String DEFAULT_IDENTIF = "AAAAA";
    private static final String UPDATED_IDENTIF = "BBBBB";

    private static final LocalDate DEFAULT_DATE_AJOUT = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_AJOUT = LocalDate.now(ZoneId.systemDefault());
    private static final String DEFAULT_PAYS = "AAAAA";
    private static final String UPDATED_PAYS = "BBBBB";
    private static final String DEFAULT_VILLE = "AAAAA";
    private static final String UPDATED_VILLE = "BBBBB";

    private static final Long DEFAULT_PRICE = 1L;
    private static final Long UPDATED_PRICE = 2L;
    private static final String DEFAULT_ETAT = "AAAAA";
    private static final String UPDATED_ETAT = "BBBBB";
    private static final String DEFAULT_DESCRIPTION = "AAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBB";

    private static final Long DEFAULT_NBRE_IMAGE = 1L;
    private static final Long UPDATED_NBRE_IMAGE = 2L;

    private static final Long DEFAULT_NBRE_VUE = 1L;
    private static final Long UPDATED_NBRE_VUE = 2L;
    private static final String DEFAULT_MAIN_IMAGE = "AAAAA";
    private static final String UPDATED_MAIN_IMAGE = "BBBBB";

    private static final Boolean DEFAULT_NEGOCIABLE = false;
    private static final Boolean UPDATED_NEGOCIABLE = true;

    @Inject
    private AdsRepository adsRepository;

    @Inject
    private AdsMapper adsMapper;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restAdsMockMvc;

    private Ads ads;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        AdsResource adsResource = new AdsResource();
        ReflectionTestUtils.setField(adsResource, "adsRepository", adsRepository);
        ReflectionTestUtils.setField(adsResource, "adsMapper", adsMapper);
        this.restAdsMockMvc = MockMvcBuilders.standaloneSetup(adsResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        ads = new Ads();
        ads.setNameAds(DEFAULT_NAME_ADS);
        ads.setIdentif(DEFAULT_IDENTIF);
        ads.setDateAjout(DEFAULT_DATE_AJOUT);
        ads.setPays(DEFAULT_PAYS);
        ads.setVille(DEFAULT_VILLE);
        ads.setPrice(DEFAULT_PRICE);
        ads.setEtat(DEFAULT_ETAT);
        ads.setDescription(DEFAULT_DESCRIPTION);
        ads.setNbreImage(DEFAULT_NBRE_IMAGE);
        ads.setNbreVue(DEFAULT_NBRE_VUE);
        ads.setMainImage(DEFAULT_MAIN_IMAGE);
        ads.setNegociable(DEFAULT_NEGOCIABLE);
    }

    @Test
    @Transactional
    public void createAds() throws Exception {
        int databaseSizeBeforeCreate = adsRepository.findAll().size();

        // Create the Ads
        AdsDTO adsDTO = adsMapper.adsToAdsDTO(ads);

        restAdsMockMvc.perform(post("/api/adss")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(adsDTO)))
                .andExpect(status().isCreated());

        // Validate the Ads in the database
        List<Ads> adss = adsRepository.findAll();
        assertThat(adss).hasSize(databaseSizeBeforeCreate + 1);
        Ads testAds = adss.get(adss.size() - 1);
        assertThat(testAds.getNameAds()).isEqualTo(DEFAULT_NAME_ADS);
        assertThat(testAds.getIdentif()).isEqualTo(DEFAULT_IDENTIF);
        assertThat(testAds.getDateAjout()).isEqualTo(DEFAULT_DATE_AJOUT);
        assertThat(testAds.getPays()).isEqualTo(DEFAULT_PAYS);
        assertThat(testAds.getVille()).isEqualTo(DEFAULT_VILLE);
        assertThat(testAds.getPrice()).isEqualTo(DEFAULT_PRICE);
        assertThat(testAds.getEtat()).isEqualTo(DEFAULT_ETAT);
        assertThat(testAds.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testAds.getNbreImage()).isEqualTo(DEFAULT_NBRE_IMAGE);
        assertThat(testAds.getNbreVue()).isEqualTo(DEFAULT_NBRE_VUE);
        assertThat(testAds.getMainImage()).isEqualTo(DEFAULT_MAIN_IMAGE);
        assertThat(testAds.getNegociable()).isEqualTo(DEFAULT_NEGOCIABLE);
    }

    @Test
    @Transactional
    public void checkNameAdsIsRequired() throws Exception {
        int databaseSizeBeforeTest = adsRepository.findAll().size();
        // set the field null
        ads.setNameAds(null);

        // Create the Ads, which fails.
        AdsDTO adsDTO = adsMapper.adsToAdsDTO(ads);

        restAdsMockMvc.perform(post("/api/adss")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(adsDTO)))
                .andExpect(status().isBadRequest());

        List<Ads> adss = adsRepository.findAll();
        assertThat(adss).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIdentifIsRequired() throws Exception {
        int databaseSizeBeforeTest = adsRepository.findAll().size();
        // set the field null
        ads.setIdentif(null);

        // Create the Ads, which fails.
        AdsDTO adsDTO = adsMapper.adsToAdsDTO(ads);

        restAdsMockMvc.perform(post("/api/adss")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(adsDTO)))
                .andExpect(status().isBadRequest());

        List<Ads> adss = adsRepository.findAll();
        assertThat(adss).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPriceIsRequired() throws Exception {
        int databaseSizeBeforeTest = adsRepository.findAll().size();
        // set the field null
        ads.setPrice(null);

        // Create the Ads, which fails.
        AdsDTO adsDTO = adsMapper.adsToAdsDTO(ads);

        restAdsMockMvc.perform(post("/api/adss")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(adsDTO)))
                .andExpect(status().isBadRequest());

        List<Ads> adss = adsRepository.findAll();
        assertThat(adss).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllAdss() throws Exception {
        // Initialize the database
        adsRepository.saveAndFlush(ads);

        // Get all the adss
        restAdsMockMvc.perform(get("/api/adss"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(ads.getId().intValue())))
                .andExpect(jsonPath("$.[*].nameAds").value(hasItem(DEFAULT_NAME_ADS.toString())))
                .andExpect(jsonPath("$.[*].identif").value(hasItem(DEFAULT_IDENTIF.toString())))
                .andExpect(jsonPath("$.[*].dateAjout").value(hasItem(DEFAULT_DATE_AJOUT.toString())))
                .andExpect(jsonPath("$.[*].pays").value(hasItem(DEFAULT_PAYS.toString())))
                .andExpect(jsonPath("$.[*].ville").value(hasItem(DEFAULT_VILLE.toString())))
                .andExpect(jsonPath("$.[*].price").value(hasItem(DEFAULT_PRICE.intValue())))
                .andExpect(jsonPath("$.[*].etat").value(hasItem(DEFAULT_ETAT.toString())))
                .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
                .andExpect(jsonPath("$.[*].nbreImage").value(hasItem(DEFAULT_NBRE_IMAGE.intValue())))
                .andExpect(jsonPath("$.[*].nbreVue").value(hasItem(DEFAULT_NBRE_VUE.intValue())))
                .andExpect(jsonPath("$.[*].mainImage").value(hasItem(DEFAULT_MAIN_IMAGE.toString())))
                .andExpect(jsonPath("$.[*].negociable").value(hasItem(DEFAULT_NEGOCIABLE.booleanValue())));
    }

    @Test
    @Transactional
    public void getAds() throws Exception {
        // Initialize the database
        adsRepository.saveAndFlush(ads);

        // Get the ads
        restAdsMockMvc.perform(get("/api/adss/{id}", ads.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(ads.getId().intValue()))
            .andExpect(jsonPath("$.nameAds").value(DEFAULT_NAME_ADS.toString()))
            .andExpect(jsonPath("$.identif").value(DEFAULT_IDENTIF.toString()))
            .andExpect(jsonPath("$.dateAjout").value(DEFAULT_DATE_AJOUT.toString()))
            .andExpect(jsonPath("$.pays").value(DEFAULT_PAYS.toString()))
            .andExpect(jsonPath("$.ville").value(DEFAULT_VILLE.toString()))
            .andExpect(jsonPath("$.price").value(DEFAULT_PRICE.intValue()))
            .andExpect(jsonPath("$.etat").value(DEFAULT_ETAT.toString()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.nbreImage").value(DEFAULT_NBRE_IMAGE.intValue()))
            .andExpect(jsonPath("$.nbreVue").value(DEFAULT_NBRE_VUE.intValue()))
            .andExpect(jsonPath("$.mainImage").value(DEFAULT_MAIN_IMAGE.toString()))
            .andExpect(jsonPath("$.negociable").value(DEFAULT_NEGOCIABLE.booleanValue()));
    }

    @Test
    @Transactional
    public void getNonExistingAds() throws Exception {
        // Get the ads
        restAdsMockMvc.perform(get("/api/adss/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAds() throws Exception {
        // Initialize the database
        adsRepository.saveAndFlush(ads);

		int databaseSizeBeforeUpdate = adsRepository.findAll().size();

        // Update the ads
        ads.setNameAds(UPDATED_NAME_ADS);
        ads.setIdentif(UPDATED_IDENTIF);
        ads.setDateAjout(UPDATED_DATE_AJOUT);
        ads.setPays(UPDATED_PAYS);
        ads.setVille(UPDATED_VILLE);
        ads.setPrice(UPDATED_PRICE);
        ads.setEtat(UPDATED_ETAT);
        ads.setDescription(UPDATED_DESCRIPTION);
        ads.setNbreImage(UPDATED_NBRE_IMAGE);
        ads.setNbreVue(UPDATED_NBRE_VUE);
        ads.setMainImage(UPDATED_MAIN_IMAGE);
        ads.setNegociable(UPDATED_NEGOCIABLE);
        AdsDTO adsDTO = adsMapper.adsToAdsDTO(ads);

        restAdsMockMvc.perform(put("/api/adss")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(adsDTO)))
                .andExpect(status().isOk());

        // Validate the Ads in the database
        List<Ads> adss = adsRepository.findAll();
        assertThat(adss).hasSize(databaseSizeBeforeUpdate);
        Ads testAds = adss.get(adss.size() - 1);
        assertThat(testAds.getNameAds()).isEqualTo(UPDATED_NAME_ADS);
        assertThat(testAds.getIdentif()).isEqualTo(UPDATED_IDENTIF);
        assertThat(testAds.getDateAjout()).isEqualTo(UPDATED_DATE_AJOUT);
        assertThat(testAds.getPays()).isEqualTo(UPDATED_PAYS);
        assertThat(testAds.getVille()).isEqualTo(UPDATED_VILLE);
        assertThat(testAds.getPrice()).isEqualTo(UPDATED_PRICE);
        assertThat(testAds.getEtat()).isEqualTo(UPDATED_ETAT);
        assertThat(testAds.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testAds.getNbreImage()).isEqualTo(UPDATED_NBRE_IMAGE);
        assertThat(testAds.getNbreVue()).isEqualTo(UPDATED_NBRE_VUE);
        assertThat(testAds.getMainImage()).isEqualTo(UPDATED_MAIN_IMAGE);
        assertThat(testAds.getNegociable()).isEqualTo(UPDATED_NEGOCIABLE);
    }

    @Test
    @Transactional
    public void deleteAds() throws Exception {
        // Initialize the database
        adsRepository.saveAndFlush(ads);

		int databaseSizeBeforeDelete = adsRepository.findAll().size();

        // Get the ads
        restAdsMockMvc.perform(delete("/api/adss/{id}", ads.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<Ads> adss = adsRepository.findAll();
        assertThat(adss).hasSize(databaseSizeBeforeDelete - 1);
    }
}
