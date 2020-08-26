package com.yummipizza.api.service;

import com.yummipizza.api.domain.Pizzaria;
import com.yummipizza.api.repository.PizzariaRepository;
import com.yummipizza.api.service.dto.PizzariaDTO;
import com.yummipizza.api.service.mapper.PizzariaMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Pizzaria}.
 */
@Service
@Transactional
public class PizzariaService {

    private final Logger log = LoggerFactory.getLogger(PizzariaService.class);

    private final PizzariaRepository pizzariaRepository;

    private final PizzariaMapper pizzariaMapper;

    public PizzariaService(PizzariaRepository pizzariaRepository, PizzariaMapper pizzariaMapper) {
        this.pizzariaRepository = pizzariaRepository;
        this.pizzariaMapper = pizzariaMapper;
    }

    /**
     * Save a pizzaria.
     *
     * @param pizzariaDTO the entity to save.
     * @return the persisted entity.
     */
    public PizzariaDTO save(PizzariaDTO pizzariaDTO) {
        log.debug("Request to save Pizzaria : {}", pizzariaDTO);
        Pizzaria pizzaria = pizzariaMapper.toEntity(pizzariaDTO);
        pizzaria = pizzariaRepository.save(pizzaria);
        return pizzariaMapper.toDto(pizzaria);
    }

    /**
     * Get all the pizzarias.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<PizzariaDTO> findAll() {
        log.debug("Request to get all Pizzarias");
        return pizzariaRepository.findAll().stream()
            .map(pizzariaMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one pizzaria by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<PizzariaDTO> findOne(Long id) {
        log.debug("Request to get Pizzaria : {}", id);
        return pizzariaRepository.findById(id)
            .map(pizzariaMapper::toDto);
    }

    /**
     * Delete the pizzaria by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Pizzaria : {}", id);
        pizzariaRepository.deleteById(id);
    }
}
