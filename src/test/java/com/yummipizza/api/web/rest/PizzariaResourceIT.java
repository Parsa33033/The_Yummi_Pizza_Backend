package com.yummipizza.api.web.rest;

import com.yummipizza.api.TheYummiPizzaBackendApp;
import com.yummipizza.api.domain.Pizzaria;
import com.yummipizza.api.repository.PizzariaRepository;
import com.yummipizza.api.service.PizzariaService;
import com.yummipizza.api.service.dto.PizzariaDTO;
import com.yummipizza.api.service.mapper.PizzariaMapper;

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

/**
 * Integration tests for the {@link PizzariaResource} REST controller.
 */
@SpringBootTest(classes = TheYummiPizzaBackendApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class PizzariaResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_ABOUTUS = "AAAAAAAAAA";
    private static final String UPDATED_ABOUTUS = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_OPEN_HOURS = "AAAAAAAAAA";
    private static final String UPDATED_OPEN_HOURS = "BBBBBBBBBB";

    private static final String DEFAULT_OPEN_DAYS = "AAAAAAAAAA";
    private static final String UPDATED_OPEN_DAYS = "BBBBBBBBBB";

    private static final Double DEFAULT_DELIVERY_PRICE_IN_DOLLOR = 1D;
    private static final Double UPDATED_DELIVERY_PRICE_IN_DOLLOR = 2D;

    private static final Double DEFAULT_DELIVERY_PRICE_IN_EURO = 1D;
    private static final Double UPDATED_DELIVERY_PRICE_IN_EURO = 2D;

    private static final Integer DEFAULT_STAFF = 1;
    private static final Integer UPDATED_STAFF = 2;

    private static final Integer DEFAULT_CUSTOMERS = 1;
    private static final Integer UPDATED_CUSTOMERS = 2;

    private static final Integer DEFAULT_NUMBER_OF_AWARDS = 1;
    private static final Integer UPDATED_NUMBER_OF_AWARDS = 2;

    private static final Integer DEFAULT_PIZZA_BRANCHES = 1;
    private static final Integer UPDATED_PIZZA_BRANCHES = 2;

    @Autowired
    private PizzariaRepository pizzariaRepository;

    @Autowired
    private PizzariaMapper pizzariaMapper;

    @Autowired
    private PizzariaService pizzariaService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPizzariaMockMvc;

    private Pizzaria pizzaria;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Pizzaria createEntity(EntityManager em) {
        Pizzaria pizzaria = new Pizzaria()
            .name(DEFAULT_NAME)
            .description(DEFAULT_DESCRIPTION)
            .aboutus(DEFAULT_ABOUTUS)
            .email(DEFAULT_EMAIL)
            .openHours(DEFAULT_OPEN_HOURS)
            .openDays(DEFAULT_OPEN_DAYS)
            .deliveryPriceInDollor(DEFAULT_DELIVERY_PRICE_IN_DOLLOR)
            .deliveryPriceInEuro(DEFAULT_DELIVERY_PRICE_IN_EURO)
            .staff(DEFAULT_STAFF)
            .customers(DEFAULT_CUSTOMERS)
            .numberOfAwards(DEFAULT_NUMBER_OF_AWARDS)
            .pizzaBranches(DEFAULT_PIZZA_BRANCHES);
        return pizzaria;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Pizzaria createUpdatedEntity(EntityManager em) {
        Pizzaria pizzaria = new Pizzaria()
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION)
            .aboutus(UPDATED_ABOUTUS)
            .email(UPDATED_EMAIL)
            .openHours(UPDATED_OPEN_HOURS)
            .openDays(UPDATED_OPEN_DAYS)
            .deliveryPriceInDollor(UPDATED_DELIVERY_PRICE_IN_DOLLOR)
            .deliveryPriceInEuro(UPDATED_DELIVERY_PRICE_IN_EURO)
            .staff(UPDATED_STAFF)
            .customers(UPDATED_CUSTOMERS)
            .numberOfAwards(UPDATED_NUMBER_OF_AWARDS)
            .pizzaBranches(UPDATED_PIZZA_BRANCHES);
        return pizzaria;
    }

    @BeforeEach
    public void initTest() {
        pizzaria = createEntity(em);
    }

    @Test
    @Transactional
    public void createPizzaria() throws Exception {
        int databaseSizeBeforeCreate = pizzariaRepository.findAll().size();
        // Create the Pizzaria
        PizzariaDTO pizzariaDTO = pizzariaMapper.toDto(pizzaria);
        restPizzariaMockMvc.perform(post("/api/pizzarias")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(pizzariaDTO)))
            .andExpect(status().isCreated());

        // Validate the Pizzaria in the database
        List<Pizzaria> pizzariaList = pizzariaRepository.findAll();
        assertThat(pizzariaList).hasSize(databaseSizeBeforeCreate + 1);
        Pizzaria testPizzaria = pizzariaList.get(pizzariaList.size() - 1);
        assertThat(testPizzaria.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testPizzaria.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testPizzaria.getAboutus()).isEqualTo(DEFAULT_ABOUTUS);
        assertThat(testPizzaria.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testPizzaria.getOpenHours()).isEqualTo(DEFAULT_OPEN_HOURS);
        assertThat(testPizzaria.getOpenDays()).isEqualTo(DEFAULT_OPEN_DAYS);
        assertThat(testPizzaria.getDeliveryPriceInDollor()).isEqualTo(DEFAULT_DELIVERY_PRICE_IN_DOLLOR);
        assertThat(testPizzaria.getDeliveryPriceInEuro()).isEqualTo(DEFAULT_DELIVERY_PRICE_IN_EURO);
        assertThat(testPizzaria.getStaff()).isEqualTo(DEFAULT_STAFF);
        assertThat(testPizzaria.getCustomers()).isEqualTo(DEFAULT_CUSTOMERS);
        assertThat(testPizzaria.getNumberOfAwards()).isEqualTo(DEFAULT_NUMBER_OF_AWARDS);
        assertThat(testPizzaria.getPizzaBranches()).isEqualTo(DEFAULT_PIZZA_BRANCHES);
    }

    @Test
    @Transactional
    public void createPizzariaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = pizzariaRepository.findAll().size();

        // Create the Pizzaria with an existing ID
        pizzaria.setId(1L);
        PizzariaDTO pizzariaDTO = pizzariaMapper.toDto(pizzaria);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPizzariaMockMvc.perform(post("/api/pizzarias")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(pizzariaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Pizzaria in the database
        List<Pizzaria> pizzariaList = pizzariaRepository.findAll();
        assertThat(pizzariaList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllPizzarias() throws Exception {
        // Initialize the database
        pizzariaRepository.saveAndFlush(pizzaria);

        // Get all the pizzariaList
        restPizzariaMockMvc.perform(get("/api/pizzarias?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(pizzaria.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].aboutus").value(hasItem(DEFAULT_ABOUTUS.toString())))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
            .andExpect(jsonPath("$.[*].openHours").value(hasItem(DEFAULT_OPEN_HOURS)))
            .andExpect(jsonPath("$.[*].openDays").value(hasItem(DEFAULT_OPEN_DAYS)))
            .andExpect(jsonPath("$.[*].deliveryPriceInDollor").value(hasItem(DEFAULT_DELIVERY_PRICE_IN_DOLLOR.doubleValue())))
            .andExpect(jsonPath("$.[*].deliveryPriceInEuro").value(hasItem(DEFAULT_DELIVERY_PRICE_IN_EURO.doubleValue())))
            .andExpect(jsonPath("$.[*].staff").value(hasItem(DEFAULT_STAFF)))
            .andExpect(jsonPath("$.[*].customers").value(hasItem(DEFAULT_CUSTOMERS)))
            .andExpect(jsonPath("$.[*].numberOfAwards").value(hasItem(DEFAULT_NUMBER_OF_AWARDS)))
            .andExpect(jsonPath("$.[*].pizzaBranches").value(hasItem(DEFAULT_PIZZA_BRANCHES)));
    }
    
    @Test
    @Transactional
    public void getPizzaria() throws Exception {
        // Initialize the database
        pizzariaRepository.saveAndFlush(pizzaria);

        // Get the pizzaria
        restPizzariaMockMvc.perform(get("/api/pizzarias/{id}", pizzaria.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(pizzaria.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.aboutus").value(DEFAULT_ABOUTUS.toString()))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL))
            .andExpect(jsonPath("$.openHours").value(DEFAULT_OPEN_HOURS))
            .andExpect(jsonPath("$.openDays").value(DEFAULT_OPEN_DAYS))
            .andExpect(jsonPath("$.deliveryPriceInDollor").value(DEFAULT_DELIVERY_PRICE_IN_DOLLOR.doubleValue()))
            .andExpect(jsonPath("$.deliveryPriceInEuro").value(DEFAULT_DELIVERY_PRICE_IN_EURO.doubleValue()))
            .andExpect(jsonPath("$.staff").value(DEFAULT_STAFF))
            .andExpect(jsonPath("$.customers").value(DEFAULT_CUSTOMERS))
            .andExpect(jsonPath("$.numberOfAwards").value(DEFAULT_NUMBER_OF_AWARDS))
            .andExpect(jsonPath("$.pizzaBranches").value(DEFAULT_PIZZA_BRANCHES));
    }
    @Test
    @Transactional
    public void getNonExistingPizzaria() throws Exception {
        // Get the pizzaria
        restPizzariaMockMvc.perform(get("/api/pizzarias/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePizzaria() throws Exception {
        // Initialize the database
        pizzariaRepository.saveAndFlush(pizzaria);

        int databaseSizeBeforeUpdate = pizzariaRepository.findAll().size();

        // Update the pizzaria
        Pizzaria updatedPizzaria = pizzariaRepository.findById(pizzaria.getId()).get();
        // Disconnect from session so that the updates on updatedPizzaria are not directly saved in db
        em.detach(updatedPizzaria);
        updatedPizzaria
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION)
            .aboutus(UPDATED_ABOUTUS)
            .email(UPDATED_EMAIL)
            .openHours(UPDATED_OPEN_HOURS)
            .openDays(UPDATED_OPEN_DAYS)
            .deliveryPriceInDollor(UPDATED_DELIVERY_PRICE_IN_DOLLOR)
            .deliveryPriceInEuro(UPDATED_DELIVERY_PRICE_IN_EURO)
            .staff(UPDATED_STAFF)
            .customers(UPDATED_CUSTOMERS)
            .numberOfAwards(UPDATED_NUMBER_OF_AWARDS)
            .pizzaBranches(UPDATED_PIZZA_BRANCHES);
        PizzariaDTO pizzariaDTO = pizzariaMapper.toDto(updatedPizzaria);

        restPizzariaMockMvc.perform(put("/api/pizzarias")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(pizzariaDTO)))
            .andExpect(status().isOk());

        // Validate the Pizzaria in the database
        List<Pizzaria> pizzariaList = pizzariaRepository.findAll();
        assertThat(pizzariaList).hasSize(databaseSizeBeforeUpdate);
        Pizzaria testPizzaria = pizzariaList.get(pizzariaList.size() - 1);
        assertThat(testPizzaria.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testPizzaria.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testPizzaria.getAboutus()).isEqualTo(UPDATED_ABOUTUS);
        assertThat(testPizzaria.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testPizzaria.getOpenHours()).isEqualTo(UPDATED_OPEN_HOURS);
        assertThat(testPizzaria.getOpenDays()).isEqualTo(UPDATED_OPEN_DAYS);
        assertThat(testPizzaria.getDeliveryPriceInDollor()).isEqualTo(UPDATED_DELIVERY_PRICE_IN_DOLLOR);
        assertThat(testPizzaria.getDeliveryPriceInEuro()).isEqualTo(UPDATED_DELIVERY_PRICE_IN_EURO);
        assertThat(testPizzaria.getStaff()).isEqualTo(UPDATED_STAFF);
        assertThat(testPizzaria.getCustomers()).isEqualTo(UPDATED_CUSTOMERS);
        assertThat(testPizzaria.getNumberOfAwards()).isEqualTo(UPDATED_NUMBER_OF_AWARDS);
        assertThat(testPizzaria.getPizzaBranches()).isEqualTo(UPDATED_PIZZA_BRANCHES);
    }

    @Test
    @Transactional
    public void updateNonExistingPizzaria() throws Exception {
        int databaseSizeBeforeUpdate = pizzariaRepository.findAll().size();

        // Create the Pizzaria
        PizzariaDTO pizzariaDTO = pizzariaMapper.toDto(pizzaria);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPizzariaMockMvc.perform(put("/api/pizzarias")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(pizzariaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Pizzaria in the database
        List<Pizzaria> pizzariaList = pizzariaRepository.findAll();
        assertThat(pizzariaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePizzaria() throws Exception {
        // Initialize the database
        pizzariaRepository.saveAndFlush(pizzaria);

        int databaseSizeBeforeDelete = pizzariaRepository.findAll().size();

        // Delete the pizzaria
        restPizzariaMockMvc.perform(delete("/api/pizzarias/{id}", pizzaria.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Pizzaria> pizzariaList = pizzariaRepository.findAll();
        assertThat(pizzariaList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
