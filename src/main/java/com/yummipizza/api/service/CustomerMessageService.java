package com.yummipizza.api.service;

import com.yummipizza.api.domain.CustomerMessage;
import com.yummipizza.api.repository.CustomerMessageRepository;
import com.yummipizza.api.service.dto.CustomerMessageDTO;
import com.yummipizza.api.service.mapper.CustomerMessageMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link CustomerMessage}.
 */
@Service
@Transactional
public class CustomerMessageService {

    private final Logger log = LoggerFactory.getLogger(CustomerMessageService.class);

    private final CustomerMessageRepository customerMessageRepository;

    private final CustomerMessageMapper customerMessageMapper;

    public CustomerMessageService(CustomerMessageRepository customerMessageRepository, CustomerMessageMapper customerMessageMapper) {
        this.customerMessageRepository = customerMessageRepository;
        this.customerMessageMapper = customerMessageMapper;
    }

    /**
     * Save a customerMessage.
     *
     * @param customerMessageDTO the entity to save.
     * @return the persisted entity.
     */
    public CustomerMessageDTO save(CustomerMessageDTO customerMessageDTO) {
        log.debug("Request to save CustomerMessage : {}", customerMessageDTO);
        CustomerMessage customerMessage = customerMessageMapper.toEntity(customerMessageDTO);
        customerMessage = customerMessageRepository.save(customerMessage);
        return customerMessageMapper.toDto(customerMessage);
    }

    /**
     * Get all the customerMessages.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<CustomerMessageDTO> findAll() {
        log.debug("Request to get all CustomerMessages");
        return customerMessageRepository.findAll().stream()
            .map(customerMessageMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one customerMessage by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<CustomerMessageDTO> findOne(Long id) {
        log.debug("Request to get CustomerMessage : {}", id);
        return customerMessageRepository.findById(id)
            .map(customerMessageMapper::toDto);
    }

    /**
     * Delete the customerMessage by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete CustomerMessage : {}", id);
        customerMessageRepository.deleteById(id);
    }
}
