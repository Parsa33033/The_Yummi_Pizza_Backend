package com.yummipizza.api.service.mapper;


import com.yummipizza.api.domain.*;
import com.yummipizza.api.service.dto.ManagerDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Manager} and its DTO {@link ManagerDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ManagerMapper extends EntityMapper<ManagerDTO, Manager> {


    @Mapping(target = "pizzaria", ignore = true)
    Manager toEntity(ManagerDTO managerDTO);

    default Manager fromId(Long id) {
        if (id == null) {
            return null;
        }
        Manager manager = new Manager();
        manager.setId(id);
        return manager;
    }
}
