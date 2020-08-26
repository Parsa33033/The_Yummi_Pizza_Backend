package com.yummipizza.api.service;

import com.yummipizza.api.domain.Manager;
import com.yummipizza.api.repository.ManagerRepository;
import com.yummipizza.api.service.dto.ManagerDTO;
import com.yummipizza.api.service.mapper.ManagerMapper;
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
 * Service Implementation for managing {@link Manager}.
 */
@Service
@Transactional
public class ManagerService {

    private final Logger log = LoggerFactory.getLogger(ManagerService.class);

    private final ManagerRepository managerRepository;

    private final ManagerMapper managerMapper;

    public ManagerService(ManagerRepository managerRepository, ManagerMapper managerMapper) {
        this.managerRepository = managerRepository;
        this.managerMapper = managerMapper;
    }

    /**
     * Save a manager.
     *
     * @param managerDTO the entity to save.
     * @return the persisted entity.
     */
    public ManagerDTO save(ManagerDTO managerDTO) {
        log.debug("Request to save Manager : {}", managerDTO);
        Manager manager = managerMapper.toEntity(managerDTO);
        manager = managerRepository.save(manager);
        return managerMapper.toDto(manager);
    }

    /**
     * Get all the managers.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<ManagerDTO> findAll() {
        log.debug("Request to get all Managers");
        return managerRepository.findAll().stream()
            .map(managerMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }



    /**
     *  Get all the managers where Pizzaria is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true) 
    public List<ManagerDTO> findAllWherePizzariaIsNull() {
        log.debug("Request to get all managers where Pizzaria is null");
        return StreamSupport
            .stream(managerRepository.findAll().spliterator(), false)
            .filter(manager -> manager.getPizzaria() == null)
            .map(managerMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one manager by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ManagerDTO> findOne(Long id) {
        log.debug("Request to get Manager : {}", id);
        return managerRepository.findById(id)
            .map(managerMapper::toDto);
    }

    /**
     * Delete the manager by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Manager : {}", id);
        managerRepository.deleteById(id);
    }
}
