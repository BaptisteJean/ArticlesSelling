package com.thedevbrige.articleselling.web.rest;

import com.thedevbrige.articleselling.Application;
import com.thedevbrige.articleselling.domain.Image;
import com.thedevbrige.articleselling.repository.ImageRepository;
import com.thedevbrige.articleselling.web.rest.dto.ImageDTO;
import com.thedevbrige.articleselling.web.rest.mapper.ImageMapper;

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
import org.springframework.util.Base64Utils;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


/**
 * Test class for the ImageResource REST controller.
 *
 * @see ImageResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class ImageResourceTest {


    private static final byte[] DEFAULT_IMG_THUMBNAIL = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_IMG_THUMBNAIL = TestUtil.createByteArray(2, "1");
    private static final String DEFAULT_IMG_THUMBNAIL_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_IMG_THUMBNAIL_CONTENT_TYPE = "image/png";

    private static final byte[] DEFAULT_IMG_NORMAL = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_IMG_NORMAL = TestUtil.createByteArray(2, "1");
    private static final String DEFAULT_IMG_NORMAL_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_IMG_NORMAL_CONTENT_TYPE = "image/png";
    private static final String DEFAULT_IDENTIF = "AAAAA";
    private static final String UPDATED_IDENTIF = "BBBBB";

    @Inject
    private ImageRepository imageRepository;

    @Inject
    private ImageMapper imageMapper;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restImageMockMvc;

    private Image image;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ImageResource imageResource = new ImageResource();
        ReflectionTestUtils.setField(imageResource, "imageRepository", imageRepository);
        ReflectionTestUtils.setField(imageResource, "imageMapper", imageMapper);
        this.restImageMockMvc = MockMvcBuilders.standaloneSetup(imageResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        image = new Image();
        image.setImgThumbnail(DEFAULT_IMG_THUMBNAIL);
        image.setImgThumbnailContentType(DEFAULT_IMG_THUMBNAIL_CONTENT_TYPE);
        image.setImgNormal(DEFAULT_IMG_NORMAL);
        image.setImgNormalContentType(DEFAULT_IMG_NORMAL_CONTENT_TYPE);
        //image.setIdentif(DEFAULT_IDENTIF);
    }

    @Test
    @Transactional
    public void createImage() throws Exception {
        int databaseSizeBeforeCreate = imageRepository.findAll().size();

        // Create the Image
        ImageDTO imageDTO = imageMapper.imageToImageDTO(image);

        restImageMockMvc.perform(post("/api/images")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(imageDTO)))
                .andExpect(status().isCreated());

        // Validate the Image in the database
        List<Image> images = imageRepository.findAll();
        assertThat(images).hasSize(databaseSizeBeforeCreate + 1);
        Image testImage = images.get(images.size() - 1);
        assertThat(testImage.getImgThumbnail()).isEqualTo(DEFAULT_IMG_THUMBNAIL);
        assertThat(testImage.getImgThumbnailContentType()).isEqualTo(DEFAULT_IMG_THUMBNAIL_CONTENT_TYPE);
        assertThat(testImage.getImgNormal()).isEqualTo(DEFAULT_IMG_NORMAL);
        assertThat(testImage.getImgNormalContentType()).isEqualTo(DEFAULT_IMG_NORMAL_CONTENT_TYPE);
        //assertThat(testImage.getIdentif()).isEqualTo(DEFAULT_IDENTIF);
    }

    @Test
    @Transactional
    public void checkIdentifIsRequired() throws Exception {
        int databaseSizeBeforeTest = imageRepository.findAll().size();
        // set the field null
        //image.setIdentif(null);

        // Create the Image, which fails.
        ImageDTO imageDTO = imageMapper.imageToImageDTO(image);

        restImageMockMvc.perform(post("/api/images")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(imageDTO)))
                .andExpect(status().isBadRequest());

        List<Image> images = imageRepository.findAll();
        assertThat(images).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllImages() throws Exception {
        // Initialize the database
        imageRepository.saveAndFlush(image);

        // Get all the images
        restImageMockMvc.perform(get("/api/images"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(image.getId().toString())))//intValue()
                .andExpect(jsonPath("$.[*].imgThumbnailContentType").value(hasItem(DEFAULT_IMG_THUMBNAIL_CONTENT_TYPE)))
                .andExpect(jsonPath("$.[*].imgThumbnail").value(hasItem(Base64Utils.encodeToString(DEFAULT_IMG_THUMBNAIL))))
                .andExpect(jsonPath("$.[*].imgNormalContentType").value(hasItem(DEFAULT_IMG_NORMAL_CONTENT_TYPE)))
                .andExpect(jsonPath("$.[*].imgNormal").value(hasItem(Base64Utils.encodeToString(DEFAULT_IMG_NORMAL))))
                .andExpect(jsonPath("$.[*].identif").value(hasItem(DEFAULT_IDENTIF.toString())));
    }

    @Test
    @Transactional
    public void getImage() throws Exception {
        // Initialize the database
        imageRepository.saveAndFlush(image);

        // Get the image
        restImageMockMvc.perform(get("/api/images/{id}", image.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(image.getId().toString()))//intValue()
            .andExpect(jsonPath("$.imgThumbnailContentType").value(DEFAULT_IMG_THUMBNAIL_CONTENT_TYPE))
            .andExpect(jsonPath("$.imgThumbnail").value(Base64Utils.encodeToString(DEFAULT_IMG_THUMBNAIL)))
            .andExpect(jsonPath("$.imgNormalContentType").value(DEFAULT_IMG_NORMAL_CONTENT_TYPE))
            .andExpect(jsonPath("$.imgNormal").value(Base64Utils.encodeToString(DEFAULT_IMG_NORMAL)))
            .andExpect(jsonPath("$.identif").value(DEFAULT_IDENTIF.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingImage() throws Exception {
        // Get the image
        restImageMockMvc.perform(get("/api/images/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateImage() throws Exception {
        // Initialize the database
        imageRepository.saveAndFlush(image);

		int databaseSizeBeforeUpdate = imageRepository.findAll().size();

        // Update the image
        image.setImgThumbnail(UPDATED_IMG_THUMBNAIL);
        image.setImgThumbnailContentType(UPDATED_IMG_THUMBNAIL_CONTENT_TYPE);
        image.setImgNormal(UPDATED_IMG_NORMAL);
        image.setImgNormalContentType(UPDATED_IMG_NORMAL_CONTENT_TYPE);
        //image.setIdentif(UPDATED_IDENTIF);
        ImageDTO imageDTO = imageMapper.imageToImageDTO(image);

        restImageMockMvc.perform(put("/api/images")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(imageDTO)))
                .andExpect(status().isOk());

        // Validate the Image in the database
        List<Image> images = imageRepository.findAll();
        assertThat(images).hasSize(databaseSizeBeforeUpdate);
        Image testImage = images.get(images.size() - 1);
        assertThat(testImage.getImgThumbnail()).isEqualTo(UPDATED_IMG_THUMBNAIL);
        assertThat(testImage.getImgThumbnailContentType()).isEqualTo(UPDATED_IMG_THUMBNAIL_CONTENT_TYPE);
        assertThat(testImage.getImgNormal()).isEqualTo(UPDATED_IMG_NORMAL);
        assertThat(testImage.getImgNormalContentType()).isEqualTo(UPDATED_IMG_NORMAL_CONTENT_TYPE);
        //assertThat(testImage.getIdentif()).isEqualTo(UPDATED_IDENTIF);
    }

    @Test
    @Transactional
    public void deleteImage() throws Exception {
        // Initialize the database
        imageRepository.saveAndFlush(image);

		int databaseSizeBeforeDelete = imageRepository.findAll().size();

        // Get the image
        restImageMockMvc.perform(delete("/api/images/{id}", image.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<Image> images = imageRepository.findAll();
        assertThat(images).hasSize(databaseSizeBeforeDelete - 1);
    }
}
