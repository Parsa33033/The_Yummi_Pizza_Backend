package com.yummipizza.api.web.rest;

import com.yummipizza.api.service.CustomerMessageService;
import com.yummipizza.api.web.rest.errors.BadRequestAlertException;
import com.yummipizza.api.service.dto.CustomerMessageDTO;

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
 * REST controller for managing {@link com.yummipizza.api.domain.CustomerMessage}.
 */
@RestController
@RequestMapping("/api")
public class CustomerMessageResource {

    private final Logger log = LoggerFactory.getLogger(CustomerMessageResource.class);

    private static final String ENTITY_NAME = "customerMessage";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CustomerMessageService customerMessageService;

    public CustomerMessageResource(CustomerMessageService customerMessageService) {
        this.customerMessageService = customerMessageService;
    }

    /**
     * {@code POST  /customer-messages} : Create a new customerMessage.
     *
     * @param customerMessageDTO the customerMessageDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new customerMessageDTO, or with status {@code 400 (Bad Request)} if the customerMessage has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/customer-messages")
    public ResponseEntity<CustomerMessageDTO> createCustomerMessage(@RequestBody CustomerMessageDTO customerMessageDTO) throws URISyntaxException {
        log.debug("REST request to save CustomerMessage : {}", customerMessageDTO);
        if (customerMessageDTO.getId() != null) {
            throw new BadRequestAlertException("A new customerMessage cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CustomerMessageDTO result = customerMessageService.save(customerMessageDTO);
        return ResponseEntity.created(new URI("/api/customer-messages/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /customer-messages} : Updates an existing customerMessage.
     *
     * @param customerMessageDTO the customerMessageDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated customerMessageDTO,
     * or with status {@code 400 (Bad Request)} if the customerMessageDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the customerMessageDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/customer-messages")
    public ResponseEntity<CustomerMessageDTO> updateCustomerMessage(@RequestBody CustomerMessageDTO customerMessageDTO) throws URISyntaxException {
        log.debug("REST request to update CustomerMessage : {}", customerMessageDTO);
        if (customerMessageDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CustomerMessageDTO result = customerMessageService.save(customerMessageDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, customerMessageDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /customer-messages} : get all the customerMessages.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of customerMessages in body.
     */
    @GetMapping("/customer-messages")
    public List<CustomerMessageDTO> getAllCustomerMessages() {
        log.debug("REST request to get all CustomerMessages");
        return customerMessageService.findAll();
    }

    /**
     * {@code GET  /customer-messages/:id} : get the "id" customerMessage.
     *
     * @param id the id of the customerMessageDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the customerMessageDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/customer-messages/{id}")
    public ResponseEntity<CustomerMessageDTO> getCustomerMessage(@PathVariable Long id) {
        log.debug("REST request to get CustomerMessage : {}", id);
        Optional<CustomerMessageDTO> customerMessageDTO = customerMessageService.findOne(id);
        return ResponseUtil.wrapOrNotFound(customerMessageDTO);
    }

    /**
     * {@code DELETE  /customer-messages/:id} : delete the "id" customerMessage.
     *
     * @param id the id of the customerMessageDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/customer-messages/{id}")
    public ResponseEntity<Void> deleteCustomerMessage(@PathVariable Long id) {
        log.debug("REST request to delete CustomerMessage : {}", id);
        customerMessageService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
