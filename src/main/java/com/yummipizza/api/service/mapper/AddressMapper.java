package com.yummipizza.api.service.mapper;


import com.yummipizza.api.domain.*;
import com.yummipizza.api.service.dto.AddressDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Address} and its DTO {@link AddressDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface AddressMapper extends EntityMapper<AddressDTO, Address> {


    @Mapping(target = "customer", ignore = true)
    @Mapping(target = "order", ignore = true)
    @Mapping(target = "pizzaria", ignore = true)
    Address toEntity(AddressDTO addressDTO);

    default Address fromId(Long id) {
        if (id == null) {
            return null;
        }
        Address address = new Address();
        address.setId(id);
        return address;
    }
}
