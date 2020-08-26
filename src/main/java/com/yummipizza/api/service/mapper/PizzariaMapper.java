package com.yummipizza.api.service.mapper;


import com.yummipizza.api.domain.*;
import com.yummipizza.api.service.dto.PizzariaDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Pizzaria} and its DTO {@link PizzariaDTO}.
 */
@Mapper(componentModel = "spring", uses = {ManagerMapper.class, AddressMapper.class})
public interface PizzariaMapper extends EntityMapper<PizzariaDTO, Pizzaria> {

    @Mapping(source = "manager.id", target = "managerId")
    @Mapping(source = "address.id", target = "addressId")
    PizzariaDTO toDto(Pizzaria pizzaria);

    @Mapping(source = "managerId", target = "manager")
    @Mapping(source = "addressId", target = "address")
    @Mapping(target = "orders", ignore = true)
    @Mapping(target = "removeOrders", ignore = true)
    @Mapping(target = "items", ignore = true)
    @Mapping(target = "removeItems", ignore = true)
    Pizzaria toEntity(PizzariaDTO pizzariaDTO);

    default Pizzaria fromId(Long id) {
        if (id == null) {
            return null;
        }
        Pizzaria pizzaria = new Pizzaria();
        pizzaria.setId(id);
        return pizzaria;
    }
}
