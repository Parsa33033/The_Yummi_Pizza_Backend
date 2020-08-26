package com.yummipizza.api.web.rest;

import com.yummipizza.api.service.RatingService;
import com.yummipizza.api.web.rest.errors.BadRequestAlertException;
import com.yummipizza.api.service.dto.RatingDTO;

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
 * REST controller for managing {@link com.yummipizza.api.domain.Rating}.
 */
@RestController
@RequestMapping("/api")
public class RatingResource {

    private final Logger log = LoggerFactory.getLogger(RatingResource.class);

    private static final String ENTITY_NAME = "rating";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final RatingService ratingService;

    public RatingResource(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    /**
     * {@code POST  /ratings} : Create a new rating.
     *
     * @param ratingDTO the ratingDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new ratingDTO, or with status {@code 400 (Bad Request)} if the rating has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/ratings")
    public ResponseEntity<RatingDTO> createRating(@RequestBody RatingDTO ratingDTO) throws URISyntaxException {
        log.debug("REST request to save Rating : {}", ratingDTO);
        if (ratingDTO.getId() != null) {
            throw new BadRequestAlertException("A new rating cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RatingDTO result = ratingService.save(ratingDTO);
        return ResponseEntity.created(new URI("/api/ratings/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /ratings} : Updates an existing rating.
     *
     * @param ratingDTO the ratingDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated ratingDTO,
     * or with status {@code 400 (Bad Request)} if the ratingDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the ratingDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/ratings")
    public ResponseEntity<RatingDTO> updateRating(@RequestBody RatingDTO ratingDTO) throws URISyntaxException {
        log.debug("REST request to update Rating : {}", ratingDTO);
        if (ratingDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        RatingDTO result = ratingService.save(ratingDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, ratingDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /ratings} : get all the ratings.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of ratings in body.
     */
    @GetMapping("/ratings")
    public List<RatingDTO> getAllRatings() {
        log.debug("REST request to get all Ratings");
        return ratingService.findAll();
    }

    /**
     * {@code GET  /ratings/:id} : get the "id" rating.
     *
     * @param id the id of the ratingDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the ratingDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/ratings/{id}")
    public ResponseEntity<RatingDTO> getRating(@PathVariable Long id) {
        log.debug("REST request to get Rating : {}", id);
        Optional<RatingDTO> ratingDTO = ratingService.findOne(id);
        return ResponseUtil.wrapOrNotFound(ratingDTO);
    }

    /**
     * {@code DELETE  /ratings/:id} : delete the "id" rating.
     *
     * @param id the id of the ratingDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/ratings/{id}")
    public ResponseEntity<Void> deleteRating(@PathVariable Long id) {
        log.debug("REST request to delete Rating : {}", id);
        ratingService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
