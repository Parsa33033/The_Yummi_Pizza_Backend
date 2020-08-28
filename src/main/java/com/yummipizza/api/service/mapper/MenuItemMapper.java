package com.yummipizza.api.service.mapper;


import com.yummipizza.api.domain.*;
import com.yummipizza.api.service.dto.MenuItemDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MenuItem} and its DTO {@link MenuItemDTO}.
 */
@Mapper(componentModel = "spring", uses = {PizzariaMapper.class})
public interface MenuItemMapper extends EntityMapper<MenuItemDTO, MenuItem> {

    @Mapping(source = "pizzaria.id", target = "pizzariaId")
    MenuItemDTO toDto(MenuItem menuItem);

    @Mapping(source = "pizzariaId", target = "pizzaria")
    MenuItem toEntity(MenuItemDTO menuItemDTO);

    default MenuItem fromId(Long id) {
        if (id == null) {
            return null;
        }
        MenuItem menuItem = new MenuItem();
        menuItem.setId(id);
        return menuItem;
    }
}
