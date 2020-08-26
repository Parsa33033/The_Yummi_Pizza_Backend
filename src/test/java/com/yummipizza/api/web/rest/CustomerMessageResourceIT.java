package com.yummipizza.api.web.rest;

import com.yummipizza.api.TheYummiPizzaBackendApp;
import com.yummipizza.api.domain.CustomerMessage;
import com.yummipizza.api.repository.CustomerMessageRepository;
import com.yummipizza.api.service.CustomerMessageService;
import com.yummipizza.api.service.dto.CustomerMessageDTO;
import com.yummipizza.api.service.mapper.CustomerMessageMapper;

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
 * Integration tests for the {@link CustomerMessageResource} REST controller.
 */
@SpringBootTest(classes = TheYummiPizzaBackendApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class CustomerMessageResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_SUBJECT = "AAAAAAAAAA";
    private static final String UPDATED_SUBJECT = "BBBBBBBBBB";

    private static final String DEFAULT_MESSAGE = "AAAAAAAAAA";
    private static final String UPDATED_MESSAGE = "BBBBBBBBBB";

    @Autowired
    private CustomerMessageRepository customerMessageRepository;

    @Autowired
    private CustomerMessageMapper customerMessageMapper;

    @Autowired
    private CustomerMessageService customerMessageService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCustomerMessageMockMvc;

    private CustomerMessage customerMessage;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CustomerMessage createEntity(EntityManager em) {
        CustomerMessage customerMessage = new CustomerMessage()
            .name(DEFAULT_NAME)
            .email(DEFAULT_EMAIL)
            .subject(DEFAULT_SUBJECT)
            .message(DEFAULT_MESSAGE);
        return customerMessage;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CustomerMessage createUpdatedEntity(EntityManager em) {
        CustomerMessage customerMessage = new CustomerMessage()
            .name(UPDATED_NAME)
            .email(UPDATED_EMAIL)
            .subject(UPDATED_SUBJECT)
            .message(UPDATED_MESSAGE);
        return customerMessage;
    }

    @BeforeEach
    public void initTest() {
        customerMessage = createEntity(em);
    }

    @Test
    @Transactional
    public void createCustomerMessage() throws Exception {
        int databaseSizeBeforeCreate = customerMessageRepository.findAll().size();
        // Create the CustomerMessage
        CustomerMessageDTO customerMessageDTO = customerMessageMapper.toDto(customerMessage);
        restCustomerMessageMockMvc.perform(post("/api/customer-messages")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(customerMessageDTO)))
            .andExpect(status().isCreated());

        // Validate the CustomerMessage in the database
        List<CustomerMessage> customerMessageList = customerMessageRepository.findAll();
        assertThat(customerMessageList).hasSize(databaseSizeBeforeCreate + 1);
        CustomerMessage testCustomerMessage = customerMessageList.get(customerMessageList.size() - 1);
        assertThat(testCustomerMessage.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testCustomerMessage.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testCustomerMessage.getSubject()).isEqualTo(DEFAULT_SUBJECT);
        assertThat(testCustomerMessage.getMessage()).isEqualTo(DEFAULT_MESSAGE);
    }

    @Test
    @Transactional
    public void createCustomerMessageWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = customerMessageRepository.findAll().size();

        // Create the CustomerMessage with an existing ID
        customerMessage.setId(1L);
        CustomerMessageDTO customerMessageDTO = customerMessageMapper.toDto(customerMessage);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCustomerMessageMockMvc.perform(post("/api/customer-messages")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(customerMessageDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CustomerMessage in the database
        List<CustomerMessage> customerMessageList = customerMessageRepository.findAll();
        assertThat(customerMessageList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllCustomerMessages() throws Exception {
        // Initialize the database
        customerMessageRepository.saveAndFlush(customerMessage);

        // Get all the customerMessageList
        restCustomerMessageMockMvc.perform(get("/api/customer-messages?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(customerMessage.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
            .andExpect(jsonPath("$.[*].subject").value(hasItem(DEFAULT_SUBJECT)))
            .andExpect(jsonPath("$.[*].message").value(hasItem(DEFAULT_MESSAGE.toString())));
    }
    
    @Test
    @Transactional
    public void getCustomerMessage() throws Exception {
        // Initialize the database
        customerMessageRepository.saveAndFlush(customerMessage);

        // Get the customerMessage
        restCustomerMessageMockMvc.perform(get("/api/customer-messages/{id}", customerMessage.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(customerMessage.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL))
            .andExpect(jsonPath("$.subject").value(DEFAULT_SUBJECT))
            .andExpect(jsonPath("$.message").value(DEFAULT_MESSAGE.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingCustomerMessage() throws Exception {
        // Get the customerMessage
        restCustomerMessageMockMvc.perform(get("/api/customer-messages/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCustomerMessage() throws Exception {
        // Initialize the database
        customerMessageRepository.saveAndFlush(customerMessage);

        int databaseSizeBeforeUpdate = customerMessageRepository.findAll().size();

        // Update the customerMessage
        CustomerMessage updatedCustomerMessage = customerMessageRepository.findById(customerMessage.getId()).get();
        // Disconnect from session so that the updates on updatedCustomerMessage are not directly saved in db
        em.detach(updatedCustomerMessage);
        updatedCustomerMessage
            .name(UPDATED_NAME)
            .email(UPDATED_EMAIL)
            .subject(UPDATED_SUBJECT)
            .message(UPDATED_MESSAGE);
        CustomerMessageDTO customerMessageDTO = customerMessageMapper.toDto(updatedCustomerMessage);

        restCustomerMessageMockMvc.perform(put("/api/customer-messages")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(customerMessageDTO)))
            .andExpect(status().isOk());

        // Validate the CustomerMessage in the database
        List<CustomerMessage> customerMessageList = customerMessageRepository.findAll();
        assertThat(customerMessageList).hasSize(databaseSizeBeforeUpdate);
        CustomerMessage testCustomerMessage = customerMessageList.get(customerMessageList.size() - 1);
        assertThat(testCustomerMessage.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testCustomerMessage.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testCustomerMessage.getSubject()).isEqualTo(UPDATED_SUBJECT);
        assertThat(testCustomerMessage.getMessage()).isEqualTo(UPDATED_MESSAGE);
    }

    @Test
    @Transactional
    public void updateNonExistingCustomerMessage() throws Exception {
        int databaseSizeBeforeUpdate = customerMessageRepository.findAll().size();

        // Create the CustomerMessage
        CustomerMessageDTO customerMessageDTO = customerMessageMapper.toDto(customerMessage);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCustomerMessageMockMvc.perform(put("/api/customer-messages")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(customerMessageDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CustomerMessage in the database
        List<CustomerMessage> customerMessageList = customerMessageRepository.findAll();
        assertThat(customerMessageList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCustomerMessage() throws Exception {
        // Initialize the database
        customerMessageRepository.saveAndFlush(customerMessage);

        int databaseSizeBeforeDelete = customerMessageRepository.findAll().size();

        // Delete the customerMessage
        restCustomerMessageMockMvc.perform(delete("/api/customer-messages/{id}", customerMessage.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<CustomerMessage> customerMessageList = customerMessageRepository.findAll();
        assertThat(customerMessageList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
