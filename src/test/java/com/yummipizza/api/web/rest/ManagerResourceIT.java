package com.yummipizza.api.web.rest;

import com.yummipizza.api.TheYummiPizzaBackendApp;
import com.yummipizza.api.domain.Manager;
import com.yummipizza.api.repository.ManagerRepository;
import com.yummipizza.api.service.ManagerService;
import com.yummipizza.api.service.dto.ManagerDTO;
import com.yummipizza.api.service.mapper.ManagerMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Base64Utils;
import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.yummipizza.api.domain.enumeration.Gender;
/**
 * Integration tests for the {@link ManagerResource} REST controller.
 */
@SpringBootTest(classes = TheYummiPizzaBackendApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class ManagerResourceIT {

    private static final String DEFAULT_USERNAME = "AAAAAAAAAA";
    private static final String UPDATED_USERNAME = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_FIRST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FIRST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_LAST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_LAST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_MOBILE_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_MOBILE_NUMBER = "BBBBBBBBBB";

    private static final Gender DEFAULT_GENDER = Gender.MALE;
    private static final Gender UPDATED_GENDER = Gender.FEMALE;

    private static final byte[] DEFAULT_IMAGE = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_IMAGE = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_IMAGE_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_IMAGE_CONTENT_TYPE = "image/png";

    @Autowired
    private ManagerRepository managerRepository;

    @Autowired
    private ManagerMapper managerMapper;

    @Autowired
    private ManagerService managerService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restManagerMockMvc;

    private Manager manager;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Manager createEntity(EntityManager em) {
        Manager manager = new Manager()
            .username(DEFAULT_USERNAME)
            .email(DEFAULT_EMAIL)
            .firstName(DEFAULT_FIRST_NAME)
            .lastName(DEFAULT_LAST_NAME)
            .mobileNumber(DEFAULT_MOBILE_NUMBER)
            .gender(DEFAULT_GENDER)
            .image(DEFAULT_IMAGE)
            .imageContentType(DEFAULT_IMAGE_CONTENT_TYPE);
        return manager;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Manager createUpdatedEntity(EntityManager em) {
        Manager manager = new Manager()
            .username(UPDATED_USERNAME)
            .email(UPDATED_EMAIL)
            .firstName(UPDATED_FIRST_NAME)
            .lastName(UPDATED_LAST_NAME)
            .mobileNumber(UPDATED_MOBILE_NUMBER)
            .gender(UPDATED_GENDER)
            .image(UPDATED_IMAGE)
            .imageContentType(UPDATED_IMAGE_CONTENT_TYPE);
        return manager;
    }

    @BeforeEach
    public void initTest() {
        manager = createEntity(em);
    }

    @Test
    @Transactional
    public void createManager() throws Exception {
        int databaseSizeBeforeCreate = managerRepository.findAll().size();
        // Create the Manager
        ManagerDTO managerDTO = managerMapper.toDto(manager);
        restManagerMockMvc.perform(post("/api/managers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(managerDTO)))
            .andExpect(status().isCreated());

        // Validate the Manager in the database
        List<Manager> managerList = managerRepository.findAll();
        assertThat(managerList).hasSize(databaseSizeBeforeCreate + 1);
        Manager testManager = managerList.get(managerList.size() - 1);
        assertThat(testManager.getUsername()).isEqualTo(DEFAULT_USERNAME);
        assertThat(testManager.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testManager.getFirstName()).isEqualTo(DEFAULT_FIRST_NAME);
        assertThat(testManager.getLastName()).isEqualTo(DEFAULT_LAST_NAME);
        assertThat(testManager.getMobileNumber()).isEqualTo(DEFAULT_MOBILE_NUMBER);
        assertThat(testManager.getGender()).isEqualTo(DEFAULT_GENDER);
        assertThat(testManager.getImage()).isEqualTo(DEFAULT_IMAGE);
        assertThat(testManager.getImageContentType()).isEqualTo(DEFAULT_IMAGE_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void createManagerWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = managerRepository.findAll().size();

        // Create the Manager with an existing ID
        manager.setId(1L);
        ManagerDTO managerDTO = managerMapper.toDto(manager);

        // An entity with an existing ID cannot be created, so this API call must fail
        restManagerMockMvc.perform(post("/api/managers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(managerDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Manager in the database
        List<Manager> managerList = managerRepository.findAll();
        assertThat(managerList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllManagers() throws Exception {
        // Initialize the database
        managerRepository.saveAndFlush(manager);

        // Get all the managerList
        restManagerMockMvc.perform(get("/api/managers?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(manager.getId().intValue())))
            .andExpect(jsonPath("$.[*].username").value(hasItem(DEFAULT_USERNAME)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
            .andExpect(jsonPath("$.[*].firstName").value(hasItem(DEFAULT_FIRST_NAME)))
            .andExpect(jsonPath("$.[*].lastName").value(hasItem(DEFAULT_LAST_NAME)))
            .andExpect(jsonPath("$.[*].mobileNumber").value(hasItem(DEFAULT_MOBILE_NUMBER)))
            .andExpect(jsonPath("$.[*].gender").value(hasItem(DEFAULT_GENDER.toString())))
            .andExpect(jsonPath("$.[*].imageContentType").value(hasItem(DEFAULT_IMAGE_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].image").value(hasItem(Base64Utils.encodeToString(DEFAULT_IMAGE))));
    }
    
    @Test
    @Transactional
    public void getManager() throws Exception {
        // Initialize the database
        managerRepository.saveAndFlush(manager);

        // Get the manager
        restManagerMockMvc.perform(get("/api/managers/{id}", manager.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(manager.getId().intValue()))
            .andExpect(jsonPath("$.username").value(DEFAULT_USERNAME))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL))
            .andExpect(jsonPath("$.firstName").value(DEFAULT_FIRST_NAME))
            .andExpect(jsonPath("$.lastName").value(DEFAULT_LAST_NAME))
            .andExpect(jsonPath("$.mobileNumber").value(DEFAULT_MOBILE_NUMBER))
            .andExpect(jsonPath("$.gender").value(DEFAULT_GENDER.toString()))
            .andExpect(jsonPath("$.imageContentType").value(DEFAULT_IMAGE_CONTENT_TYPE))
            .andExpect(jsonPath("$.image").value(Base64Utils.encodeToString(DEFAULT_IMAGE)));
    }
    @Test
    @Transactional
    public void getNonExistingManager() throws Exception {
        // Get the manager
        restManagerMockMvc.perform(get("/api/managers/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateManager() throws Exception {
        // Initialize the database
        managerRepository.saveAndFlush(manager);

        int databaseSizeBeforeUpdate = managerRepository.findAll().size();

        // Update the manager
        Manager updatedManager = managerRepository.findById(manager.getId()).get();
        // Disconnect from session so that the updates on updatedManager are not directly saved in db
        em.detach(updatedManager);
        updatedManager
            .username(UPDATED_USERNAME)
            .email(UPDATED_EMAIL)
            .firstName(UPDATED_FIRST_NAME)
            .lastName(UPDATED_LAST_NAME)
            .mobileNumber(UPDATED_MOBILE_NUMBER)
            .gender(UPDATED_GENDER)
            .image(UPDATED_IMAGE)
            .imageContentType(UPDATED_IMAGE_CONTENT_TYPE);
        ManagerDTO managerDTO = managerMapper.toDto(updatedManager);

        restManagerMockMvc.perform(put("/api/managers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(managerDTO)))
            .andExpect(status().isOk());

        // Validate the Manager in the database
        List<Manager> managerList = managerRepository.findAll();
        assertThat(managerList).hasSize(databaseSizeBeforeUpdate);
        Manager testManager = managerList.get(managerList.size() - 1);
        assertThat(testManager.getUsername()).isEqualTo(UPDATED_USERNAME);
        assertThat(testManager.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testManager.getFirstName()).isEqualTo(UPDATED_FIRST_NAME);
        assertThat(testManager.getLastName()).isEqualTo(UPDATED_LAST_NAME);
        assertThat(testManager.getMobileNumber()).isEqualTo(UPDATED_MOBILE_NUMBER);
        assertThat(testManager.getGender()).isEqualTo(UPDATED_GENDER);
        assertThat(testManager.getImage()).isEqualTo(UPDATED_IMAGE);
        assertThat(testManager.getImageContentType()).isEqualTo(UPDATED_IMAGE_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void updateNonExistingManager() throws Exception {
        int databaseSizeBeforeUpdate = managerRepository.findAll().size();

        // Create the Manager
        ManagerDTO managerDTO = managerMapper.toDto(manager);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restManagerMockMvc.perform(put("/api/managers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(managerDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Manager in the database
        List<Manager> managerList = managerRepository.findAll();
        assertThat(managerList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteManager() throws Exception {
        // Initialize the database
        managerRepository.saveAndFlush(manager);

        int databaseSizeBeforeDelete = managerRepository.findAll().size();

        // Delete the manager
        restManagerMockMvc.perform(delete("/api/managers/{id}", manager.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Manager> managerList = managerRepository.findAll();
        assertThat(managerList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
