package com.yummipizza.api.web.rest;

import com.yummipizza.api.domain.MenuItem;
import com.yummipizza.api.repository.MenuItemRepository;
import com.yummipizza.api.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * REST controller for managing {@link com.yummipizza.api.domain.MenuItem}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class MenuItemResource {

    private final Logger log = LoggerFactory.getLogger(MenuItemResource.class);

    private static final String ENTITY_NAME = "menuItem";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MenuItemRepository menuItemRepository;

    public MenuItemResource(MenuItemRepository menuItemRepository) {
        this.menuItemRepository = menuItemRepository;
    }

    /**
     * {@code POST  /menu-items} : Create a new menuItem.
     *
     * @param menuItem the menuItem to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new menuItem, or with status {@code 400 (Bad Request)} if the menuItem has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/menu-items")
    public ResponseEntity<MenuItem> createMenuItem(@RequestBody MenuItem menuItem) throws URISyntaxException {
        log.debug("REST request to save MenuItem : {}", menuItem);
        if (menuItem.getId() != null) {
            throw new BadRequestAlertException("A new menuItem cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MenuItem result = menuItemRepository.save(menuItem);
        return ResponseEntity.created(new URI("/api/menu-items/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /menu-items} : Updates an existing menuItem.
     *
     * @param menuItem the menuItem to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated menuItem,
     * or with status {@code 400 (Bad Request)} if the menuItem is not valid,
     * or with status {@code 500 (Internal Server Error)} if the menuItem couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/menu-items")
    public ResponseEntity<MenuItem> updateMenuItem(@RequestBody MenuItem menuItem) throws URISyntaxException {
        log.debug("REST request to update MenuItem : {}", menuItem);
        if (menuItem.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MenuItem result = menuItemRepository.save(menuItem);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, menuItem.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /menu-items} : get all the menuItems.
     *
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of menuItems in body.
     */
    @GetMapping("/menu-items")
    public List<MenuItem> getAllMenuItems(@RequestParam(required = false) String filter) {
        if ("orderitem-is-null".equals(filter)) {
            log.debug("REST request to get all MenuItems where orderItem is null");
            return StreamSupport
                .stream(menuItemRepository.findAll().spliterator(), false)
                .filter(menuItem -> menuItem.getOrderItem() == null)
                .collect(Collectors.toList());
        }
        log.debug("REST request to get all MenuItems");
        return menuItemRepository.findAll();
    }

    /**
     * {@code GET  /menu-items/:id} : get the "id" menuItem.
     *
     * @param id the id of the menuItem to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the menuItem, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/menu-items/{id}")
    public ResponseEntity<MenuItem> getMenuItem(@PathVariable Long id) {
        log.debug("REST request to get MenuItem : {}", id);
        Optional<MenuItem> menuItem = menuItemRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(menuItem);
    }

    /**
     * {@code DELETE  /menu-items/:id} : delete the "id" menuItem.
     *
     * @param id the id of the menuItem to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/menu-items/{id}")
    public ResponseEntity<Void> deleteMenuItem(@PathVariable Long id) {
        log.debug("REST request to delete MenuItem : {}", id);
        menuItemRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
