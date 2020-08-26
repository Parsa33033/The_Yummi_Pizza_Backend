package com.yummipizza.api.web.rest;

import com.yummipizza.api.TheYummiPizzaBackendApp;
import com.yummipizza.api.domain.OrderItem;
import com.yummipizza.api.repository.OrderItemRepository;
import com.yummipizza.api.service.OrderItemService;
import com.yummipizza.api.service.dto.OrderItemDTO;
import com.yummipizza.api.service.mapper.OrderItemMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link OrderItemResource} REST controller.
 */
@SpringBootTest(classes = TheYummiPizzaBackendApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class OrderItemResourceIT {

    private static final Integer DEFAULT_NUMBER = 1;
    private static final Integer UPDATED_NUMBER = 2;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private OrderItemMapper orderItemMapper;

    @Autowired
    private OrderItemService orderItemService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restOrderItemMockMvc;

    private OrderItem orderItem;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OrderItem createEntity(EntityManager em) {
        OrderItem orderItem = new OrderItem()
            .number(DEFAULT_NUMBER);
        return orderItem;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OrderItem createUpdatedEntity(EntityManager em) {
        OrderItem orderItem = new OrderItem()
            .number(UPDATED_NUMBER);
        return orderItem;
    }

    @BeforeEach
    public void initTest() {
        orderItem = createEntity(em);
    }

    @Test
    @Transactional
    public void createOrderItem() throws Exception {
        int databaseSizeBeforeCreate = orderItemRepository.findAll().size();
        // Create the OrderItem
        OrderItemDTO orderItemDTO = orderItemMapper.toDto(orderItem);
        restOrderItemMockMvc.perform(post("/api/order-items")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(orderItemDTO)))
            .andExpect(status().isCreated());

        // Validate the OrderItem in the database
        List<OrderItem> orderItemList = orderItemRepository.findAll();
        assertThat(orderItemList).hasSize(databaseSizeBeforeCreate + 1);
        OrderItem testOrderItem = orderItemList.get(orderItemList.size() - 1);
        assertThat(testOrderItem.getNumber()).isEqualTo(DEFAULT_NUMBER);
    }

    @Test
    @Transactional
    public void createOrderItemWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = orderItemRepository.findAll().size();

        // Create the OrderItem with an existing ID
        orderItem.setId(1L);
        OrderItemDTO orderItemDTO = orderItemMapper.toDto(orderItem);

        // An entity with an existing ID cannot be created, so this API call must fail
        restOrderItemMockMvc.perform(post("/api/order-items")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(orderItemDTO)))
            .andExpect(status().isBadRequest());

        // Validate the OrderItem in the database
        List<OrderItem> orderItemList = orderItemRepository.findAll();
        assertThat(orderItemList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllOrderItems() throws Exception {
        // Initialize the database
        orderItemRepository.saveAndFlush(orderItem);

        // Get all the orderItemList
        restOrderItemMockMvc.perform(get("/api/order-items?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(orderItem.getId().intValue())))
            .andExpect(jsonPath("$.[*].number").value(hasItem(DEFAULT_NUMBER)));
    }
    
    @Test
    @Transactional
    public void getOrderItem() throws Exception {
        // Initialize the database
        orderItemRepository.saveAndFlush(orderItem);

        // Get the orderItem
        restOrderItemMockMvc.perform(get("/api/order-items/{id}", orderItem.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(orderItem.getId().intValue()))
            .andExpect(jsonPath("$.number").value(DEFAULT_NUMBER));
    }
    @Test
    @Transactional
    public void getNonExistingOrderItem() throws Exception {
        // Get the orderItem
        restOrderItemMockMvc.perform(get("/api/order-items/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateOrderItem() throws Exception {
        // Initialize the database
        orderItemRepository.saveAndFlush(orderItem);

        int databaseSizeBeforeUpdate = orderItemRepository.findAll().size();

        // Update the orderItem
        OrderItem updatedOrderItem = orderItemRepository.findById(orderItem.getId()).get();
        // Disconnect from session so that the updates on updatedOrderItem are not directly saved in db
        em.detach(updatedOrderItem);
        updatedOrderItem
            .number(UPDATED_NUMBER);
        OrderItemDTO orderItemDTO = orderItemMapper.toDto(updatedOrderItem);

        restOrderItemMockMvc.perform(put("/api/order-items")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(orderItemDTO)))
            .andExpect(status().isOk());

        // Validate the OrderItem in the database
        List<OrderItem> orderItemList = orderItemRepository.findAll();
        assertThat(orderItemList).hasSize(databaseSizeBeforeUpdate);
        OrderItem testOrderItem = orderItemList.get(orderItemList.size() - 1);
        assertThat(testOrderItem.getNumber()).isEqualTo(UPDATED_NUMBER);
    }

    @Test
    @Transactional
    public void updateNonExistingOrderItem() throws Exception {
        int databaseSizeBeforeUpdate = orderItemRepository.findAll().size();

        // Create the OrderItem
        OrderItemDTO orderItemDTO = orderItemMapper.toDto(orderItem);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOrderItemMockMvc.perform(put("/api/order-items")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(orderItemDTO)))
            .andExpect(status().isBadRequest());

        // Validate the OrderItem in the database
        List<OrderItem> orderItemList = orderItemRepository.findAll();
        assertThat(orderItemList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteOrderItem() throws Exception {
        // Initialize the database
        orderItemRepository.saveAndFlush(orderItem);

        int databaseSizeBeforeDelete = orderItemRepository.findAll().size();

        // Delete the orderItem
        restOrderItemMockMvc.perform(delete("/api/order-items/{id}", orderItem.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<OrderItem> orderItemList = orderItemRepository.findAll();
        assertThat(orderItemList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
