package com.yummipizza.api.service;

import com.yummipizza.api.domain.Address;
import com.yummipizza.api.repository.AddressRepository;
import com.yummipizza.api.service.dto.AddressDTO;
import com.yummipizza.api.service.mapper.AddressMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Service Implementation for managing {@link Address}.
 */
@Service
@Transactional
public class AddressService {

    private final Logger log = LoggerFactory.getLogger(AddressService.class);

    private final AddressRepository addressRepository;

    private final AddressMapper addressMapper;

    public AddressService(AddressRepository addressRepository, AddressMapper addressMapper) {
        this.addressRepository = addressRepository;
        this.addressMapper = addressMapper;
    }

    /**
     * Save a address.
     *
     * @param addressDTO the entity to save.
     * @return the persisted entity.
     */
    public AddressDTO save(AddressDTO addressDTO) {
        log.debug("Request to save Address : {}", addressDTO);
        Address address = addressMapper.toEntity(addressDTO);
        address = addressRepository.save(address);
        return addressMapper.toDto(address);
    }

    /**
     * Get all the addresses.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<AddressDTO> findAll() {
        log.debug("Request to get all Addresses");
        return addressRepository.findAll().stream()
            .map(addressMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }



    /**
     *  Get all the addresses where Customer is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true) 
    public List<AddressDTO> findAllWhereCustomerIsNull() {
        log.debug("Request to get all addresses where Customer is null");
        return StreamSupport
            .stream(addressRepository.findAll().spliterator(), false)
            .filter(address -> address.getCustomer() == null)
            .map(addressMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     *  Get all the addresses where Order is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true) 
    public List<AddressDTO> findAllWhereOrderIsNull() {
        log.debug("Request to get all addresses where Order is null");
        return StreamSupport
            .stream(addressRepository.findAll().spliterator(), false)
            .filter(address -> address.getOrder() == null)
            .map(addressMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     *  Get all the addresses where Pizzaria is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true) 
    public List<AddressDTO> findAllWherePizzariaIsNull() {
        log.debug("Request to get all addresses where Pizzaria is null");
        return StreamSupport
            .stream(addressRepository.findAll().spliterator(), false)
            .filter(address -> address.getPizzaria() == null)
            .map(addressMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one address by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<AddressDTO> findOne(Long id) {
        log.debug("Request to get Address : {}", id);
        return addressRepository.findById(id)
            .map(addressMapper::toDto);
    }

    /**
     * Delete the address by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Address : {}", id);
        addressRepository.deleteById(id);
    }
}
