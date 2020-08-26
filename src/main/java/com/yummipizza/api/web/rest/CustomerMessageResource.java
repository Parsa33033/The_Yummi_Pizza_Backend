package com.yummipizza.api.web.rest;

import com.yummipizza.api.domain.CustomerMessage;
import com.yummipizza.api.repository.CustomerMessageRepository;
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

/**
 * REST controller for managing {@link com.yummipizza.api.domain.CustomerMessage}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class CustomerMessageResource {

    private final Logger log = LoggerFactory.getLogger(CustomerMessageResource.class);

    private static final String ENTITY_NAME = "customerMessage";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CustomerMessageRepository customerMessageRepository;

    public CustomerMessageResource(CustomerMessageRepository customerMessageRepository) {
        this.customerMessageRepository = customerMessageRepository;
    }

    /**
     * {@code POST  /customer-messages} : Create a new customerMessage.
     *
     * @param customerMessage the customerMessage to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new customerMessage, or with status {@code 400 (Bad Request)} if the customerMessage has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/customer-messages")
    public ResponseEntity<CustomerMessage> createCustomerMessage(@RequestBody CustomerMessage customerMessage) throws URISyntaxException {
        log.debug("REST request to save CustomerMessage : {}", customerMessage);
        if (customerMessage.getId() != null) {
            throw new BadRequestAlertException("A new customerMessage cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CustomerMessage result = customerMessageRepository.save(customerMessage);
        return ResponseEntity.created(new URI("/api/customer-messages/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /customer-messages} : Updates an existing customerMessage.
     *
     * @param customerMessage the customerMessage to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated customerMessage,
     * or with status {@code 400 (Bad Request)} if the customerMessage is not valid,
     * or with status {@code 500 (Internal Server Error)} if the customerMessage couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/customer-messages")
    public ResponseEntity<CustomerMessage> updateCustomerMessage(@RequestBody CustomerMessage customerMessage) throws URISyntaxException {
        log.debug("REST request to update CustomerMessage : {}", customerMessage);
        if (customerMessage.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CustomerMessage result = customerMessageRepository.save(customerMessage);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, customerMessage.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /customer-messages} : get all the customerMessages.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of customerMessages in body.
     */
    @GetMapping("/customer-messages")
    public List<CustomerMessage> getAllCustomerMessages() {
        log.debug("REST request to get all CustomerMessages");
        return customerMessageRepository.findAll();
    }

    /**
     * {@code GET  /customer-messages/:id} : get the "id" customerMessage.
     *
     * @param id the id of the customerMessage to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the customerMessage, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/customer-messages/{id}")
    public ResponseEntity<CustomerMessage> getCustomerMessage(@PathVariable Long id) {
        log.debug("REST request to get CustomerMessage : {}", id);
        Optional<CustomerMessage> customerMessage = customerMessageRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(customerMessage);
    }

    /**
     * {@code DELETE  /customer-messages/:id} : delete the "id" customerMessage.
     *
     * @param id the id of the customerMessage to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/customer-messages/{id}")
    public ResponseEntity<Void> deleteCustomerMessage(@PathVariable Long id) {
        log.debug("REST request to delete CustomerMessage : {}", id);
        customerMessageRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
