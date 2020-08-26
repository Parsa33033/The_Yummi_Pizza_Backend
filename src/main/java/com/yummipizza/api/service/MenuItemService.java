package com.yummipizza.api.service;

import com.yummipizza.api.domain.MenuItem;
import com.yummipizza.api.repository.MenuItemRepository;
import com.yummipizza.api.service.dto.MenuItemDTO;
import com.yummipizza.api.service.mapper.MenuItemMapper;
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
 * Service Implementation for managing {@link MenuItem}.
 */
@Service
@Transactional
public class MenuItemService {

    private final Logger log = LoggerFactory.getLogger(MenuItemService.class);

    private final MenuItemRepository menuItemRepository;

    private final MenuItemMapper menuItemMapper;

    public MenuItemService(MenuItemRepository menuItemRepository, MenuItemMapper menuItemMapper) {
        this.menuItemRepository = menuItemRepository;
        this.menuItemMapper = menuItemMapper;
    }

    /**
     * Save a menuItem.
     *
     * @param menuItemDTO the entity to save.
     * @return the persisted entity.
     */
    public MenuItemDTO save(MenuItemDTO menuItemDTO) {
        log.debug("Request to save MenuItem : {}", menuItemDTO);
        MenuItem menuItem = menuItemMapper.toEntity(menuItemDTO);
        menuItem = menuItemRepository.save(menuItem);
        return menuItemMapper.toDto(menuItem);
    }

    /**
     * Get all the menuItems.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<MenuItemDTO> findAll() {
        log.debug("Request to get all MenuItems");
        return menuItemRepository.findAll().stream()
            .map(menuItemMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }



    /**
     *  Get all the menuItems where OrderItem is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true) 
    public List<MenuItemDTO> findAllWhereOrderItemIsNull() {
        log.debug("Request to get all menuItems where OrderItem is null");
        return StreamSupport
            .stream(menuItemRepository.findAll().spliterator(), false)
            .filter(menuItem -> menuItem.getOrderItem() == null)
            .map(menuItemMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one menuItem by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MenuItemDTO> findOne(Long id) {
        log.debug("Request to get MenuItem : {}", id);
        return menuItemRepository.findById(id)
            .map(menuItemMapper::toDto);
    }

    /**
     * Delete the menuItem by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MenuItem : {}", id);
        menuItemRepository.deleteById(id);
    }
}
