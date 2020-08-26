package com.yummipizza.api.web.rest;

import com.yummipizza.api.TheYummiPizzaBackendApp;
import com.yummipizza.api.domain.MenuItem;
import com.yummipizza.api.repository.MenuItemRepository;

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

import com.yummipizza.api.domain.enumeration.FoodType;
/**
 * Integration tests for the {@link MenuItemResource} REST controller.
 */
@SpringBootTest(classes = TheYummiPizzaBackendApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class MenuItemResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_INGREDIENT = "AAAAAAAAAA";
    private static final String UPDATED_INGREDIENT = "BBBBBBBBBB";

    private static final Double DEFAULT_PRICE_DOLLOR = 1D;
    private static final Double UPDATED_PRICE_DOLLOR = 2D;

    private static final Double DEFAULT_PRICE_EURO = 1D;
    private static final Double UPDATED_PRICE_EURO = 2D;

    private static final FoodType DEFAULT_TYPE = FoodType.PIZZA;
    private static final FoodType UPDATED_TYPE = FoodType.BURGER;

    private static final byte[] DEFAULT_PIC_JPG = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_PIC_JPG = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_PIC_JPG_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_PIC_JPG_CONTENT_TYPE = "image/png";

    private static final byte[] DEFAULT_PIC_PNG = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_PIC_PNG = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_PIC_PNG_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_PIC_PNG_CONTENT_TYPE = "image/png";

    @Autowired
    private MenuItemRepository menuItemRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restMenuItemMockMvc;

    private MenuItem menuItem;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MenuItem createEntity(EntityManager em) {
        MenuItem menuItem = new MenuItem()
            .name(DEFAULT_NAME)
            .description(DEFAULT_DESCRIPTION)
            .ingredient(DEFAULT_INGREDIENT)
            .priceDollor(DEFAULT_PRICE_DOLLOR)
            .priceEuro(DEFAULT_PRICE_EURO)
            .type(DEFAULT_TYPE)
            .picJpg(DEFAULT_PIC_JPG)
            .picJpgContentType(DEFAULT_PIC_JPG_CONTENT_TYPE)
            .picPng(DEFAULT_PIC_PNG)
            .picPngContentType(DEFAULT_PIC_PNG_CONTENT_TYPE);
        return menuItem;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MenuItem createUpdatedEntity(EntityManager em) {
        MenuItem menuItem = new MenuItem()
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION)
            .ingredient(UPDATED_INGREDIENT)
            .priceDollor(UPDATED_PRICE_DOLLOR)
            .priceEuro(UPDATED_PRICE_EURO)
            .type(UPDATED_TYPE)
            .picJpg(UPDATED_PIC_JPG)
            .picJpgContentType(UPDATED_PIC_JPG_CONTENT_TYPE)
            .picPng(UPDATED_PIC_PNG)
            .picPngContentType(UPDATED_PIC_PNG_CONTENT_TYPE);
        return menuItem;
    }

    @BeforeEach
    public void initTest() {
        menuItem = createEntity(em);
    }

    @Test
    @Transactional
    public void createMenuItem() throws Exception {
        int databaseSizeBeforeCreate = menuItemRepository.findAll().size();
        // Create the MenuItem
        restMenuItemMockMvc.perform(post("/api/menu-items")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(menuItem)))
            .andExpect(status().isCreated());

        // Validate the MenuItem in the database
        List<MenuItem> menuItemList = menuItemRepository.findAll();
        assertThat(menuItemList).hasSize(databaseSizeBeforeCreate + 1);
        MenuItem testMenuItem = menuItemList.get(menuItemList.size() - 1);
        assertThat(testMenuItem.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testMenuItem.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testMenuItem.getIngredient()).isEqualTo(DEFAULT_INGREDIENT);
        assertThat(testMenuItem.getPriceDollor()).isEqualTo(DEFAULT_PRICE_DOLLOR);
        assertThat(testMenuItem.getPriceEuro()).isEqualTo(DEFAULT_PRICE_EURO);
        assertThat(testMenuItem.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testMenuItem.getPicJpg()).isEqualTo(DEFAULT_PIC_JPG);
        assertThat(testMenuItem.getPicJpgContentType()).isEqualTo(DEFAULT_PIC_JPG_CONTENT_TYPE);
        assertThat(testMenuItem.getPicPng()).isEqualTo(DEFAULT_PIC_PNG);
        assertThat(testMenuItem.getPicPngContentType()).isEqualTo(DEFAULT_PIC_PNG_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void createMenuItemWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = menuItemRepository.findAll().size();

        // Create the MenuItem with an existing ID
        menuItem.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMenuItemMockMvc.perform(post("/api/menu-items")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(menuItem)))
            .andExpect(status().isBadRequest());

        // Validate the MenuItem in the database
        List<MenuItem> menuItemList = menuItemRepository.findAll();
        assertThat(menuItemList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllMenuItems() throws Exception {
        // Initialize the database
        menuItemRepository.saveAndFlush(menuItem);

        // Get all the menuItemList
        restMenuItemMockMvc.perform(get("/api/menu-items?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(menuItem.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].ingredient").value(hasItem(DEFAULT_INGREDIENT.toString())))
            .andExpect(jsonPath("$.[*].priceDollor").value(hasItem(DEFAULT_PRICE_DOLLOR.doubleValue())))
            .andExpect(jsonPath("$.[*].priceEuro").value(hasItem(DEFAULT_PRICE_EURO.doubleValue())))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE.toString())))
            .andExpect(jsonPath("$.[*].picJpgContentType").value(hasItem(DEFAULT_PIC_JPG_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].picJpg").value(hasItem(Base64Utils.encodeToString(DEFAULT_PIC_JPG))))
            .andExpect(jsonPath("$.[*].picPngContentType").value(hasItem(DEFAULT_PIC_PNG_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].picPng").value(hasItem(Base64Utils.encodeToString(DEFAULT_PIC_PNG))));
    }
    
    @Test
    @Transactional
    public void getMenuItem() throws Exception {
        // Initialize the database
        menuItemRepository.saveAndFlush(menuItem);

        // Get the menuItem
        restMenuItemMockMvc.perform(get("/api/menu-items/{id}", menuItem.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(menuItem.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.ingredient").value(DEFAULT_INGREDIENT.toString()))
            .andExpect(jsonPath("$.priceDollor").value(DEFAULT_PRICE_DOLLOR.doubleValue()))
            .andExpect(jsonPath("$.priceEuro").value(DEFAULT_PRICE_EURO.doubleValue()))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE.toString()))
            .andExpect(jsonPath("$.picJpgContentType").value(DEFAULT_PIC_JPG_CONTENT_TYPE))
            .andExpect(jsonPath("$.picJpg").value(Base64Utils.encodeToString(DEFAULT_PIC_JPG)))
            .andExpect(jsonPath("$.picPngContentType").value(DEFAULT_PIC_PNG_CONTENT_TYPE))
            .andExpect(jsonPath("$.picPng").value(Base64Utils.encodeToString(DEFAULT_PIC_PNG)));
    }
    @Test
    @Transactional
    public void getNonExistingMenuItem() throws Exception {
        // Get the menuItem
        restMenuItemMockMvc.perform(get("/api/menu-items/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMenuItem() throws Exception {
        // Initialize the database
        menuItemRepository.saveAndFlush(menuItem);

        int databaseSizeBeforeUpdate = menuItemRepository.findAll().size();

        // Update the menuItem
        MenuItem updatedMenuItem = menuItemRepository.findById(menuItem.getId()).get();
        // Disconnect from session so that the updates on updatedMenuItem are not directly saved in db
        em.detach(updatedMenuItem);
        updatedMenuItem
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION)
            .ingredient(UPDATED_INGREDIENT)
            .priceDollor(UPDATED_PRICE_DOLLOR)
            .priceEuro(UPDATED_PRICE_EURO)
            .type(UPDATED_TYPE)
            .picJpg(UPDATED_PIC_JPG)
            .picJpgContentType(UPDATED_PIC_JPG_CONTENT_TYPE)
            .picPng(UPDATED_PIC_PNG)
            .picPngContentType(UPDATED_PIC_PNG_CONTENT_TYPE);

        restMenuItemMockMvc.perform(put("/api/menu-items")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedMenuItem)))
            .andExpect(status().isOk());

        // Validate the MenuItem in the database
        List<MenuItem> menuItemList = menuItemRepository.findAll();
        assertThat(menuItemList).hasSize(databaseSizeBeforeUpdate);
        MenuItem testMenuItem = menuItemList.get(menuItemList.size() - 1);
        assertThat(testMenuItem.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testMenuItem.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testMenuItem.getIngredient()).isEqualTo(UPDATED_INGREDIENT);
        assertThat(testMenuItem.getPriceDollor()).isEqualTo(UPDATED_PRICE_DOLLOR);
        assertThat(testMenuItem.getPriceEuro()).isEqualTo(UPDATED_PRICE_EURO);
        assertThat(testMenuItem.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testMenuItem.getPicJpg()).isEqualTo(UPDATED_PIC_JPG);
        assertThat(testMenuItem.getPicJpgContentType()).isEqualTo(UPDATED_PIC_JPG_CONTENT_TYPE);
        assertThat(testMenuItem.getPicPng()).isEqualTo(UPDATED_PIC_PNG);
        assertThat(testMenuItem.getPicPngContentType()).isEqualTo(UPDATED_PIC_PNG_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void updateNonExistingMenuItem() throws Exception {
        int databaseSizeBeforeUpdate = menuItemRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMenuItemMockMvc.perform(put("/api/menu-items")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(menuItem)))
            .andExpect(status().isBadRequest());

        // Validate the MenuItem in the database
        List<MenuItem> menuItemList = menuItemRepository.findAll();
        assertThat(menuItemList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMenuItem() throws Exception {
        // Initialize the database
        menuItemRepository.saveAndFlush(menuItem);

        int databaseSizeBeforeDelete = menuItemRepository.findAll().size();

        // Delete the menuItem
        restMenuItemMockMvc.perform(delete("/api/menu-items/{id}", menuItem.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<MenuItem> menuItemList = menuItemRepository.findAll();
        assertThat(menuItemList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
