package com.yummipizza.api.web.rest;

import com.yummipizza.api.service.PizzariaService;
import com.yummipizza.api.web.rest.errors.BadRequestAlertException;
import com.yummipizza.api.service.dto.PizzariaDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.yummipizza.api.domain.Pizzaria}.
 */
@RestController
@RequestMapping("/api")
public class PizzariaResource {

    private final Logger log = LoggerFactory.getLogger(PizzariaResource.class);

    private static final String ENTITY_NAME = "pizzaria";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PizzariaService pizzariaService;

    public PizzariaResource(PizzariaService pizzariaService) {
        this.pizzariaService = pizzariaService;
    }

    /**
     * {@code POST  /pizzarias} : Create a new pizzaria.
     *
     * @param pizzariaDTO the pizzariaDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new pizzariaDTO, or with status {@code 400 (Bad Request)} if the pizzaria has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/pizzarias")
    public ResponseEntity<PizzariaDTO> createPizzaria(@RequestBody PizzariaDTO pizzariaDTO) throws URISyntaxException {
        log.debug("REST request to save Pizzaria : {}", pizzariaDTO);
        if (pizzariaDTO.getId() != null) {
            throw new BadRequestAlertException("A new pizzaria cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PizzariaDTO result = pizzariaService.save(pizzariaDTO);
        return ResponseEntity.created(new URI("/api/pizzarias/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /pizzarias} : Updates an existing pizzaria.
     *
     * @param pizzariaDTO the pizzariaDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated pizzariaDTO,
     * or with status {@code 400 (Bad Request)} if the pizzariaDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the pizzariaDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/pizzarias")
    public ResponseEntity<PizzariaDTO> updatePizzaria(@RequestBody PizzariaDTO pizzariaDTO) throws URISyntaxException {
        log.debug("REST request to update Pizzaria : {}", pizzariaDTO);
        if (pizzariaDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PizzariaDTO result = pizzariaService.save(pizzariaDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, pizzariaDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /pizzarias} : get all the pizzarias.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of pizzarias in body.
     */
    @GetMapping("/pizzarias")
    public List<PizzariaDTO> getAllPizzarias() {
        log.debug("REST request to get all Pizzarias");
        return pizzariaService.findAll();
    }

    /**
     * {@code GET  /pizzarias/:id} : get the "id" pizzaria.
     *
     * @param id the id of the pizzariaDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the pizzariaDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/pizzarias/{id}")
    public ResponseEntity<PizzariaDTO> getPizzaria(@PathVariable Long id) {
        log.debug("REST request to get Pizzaria : {}", id);
        Optional<PizzariaDTO> pizzariaDTO = pizzariaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(pizzariaDTO);
    }

    /**
     * {@code DELETE  /pizzarias/:id} : delete the "id" pizzaria.
     *
     * @param id the id of the pizzariaDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/pizzarias/{id}")
    public ResponseEntity<Void> deletePizzaria(@PathVariable Long id) {
        log.debug("REST request to delete Pizzaria : {}", id);
        pizzariaService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
