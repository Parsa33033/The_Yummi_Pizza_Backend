package com.yummipizza.api.service.mapper;


import com.yummipizza.api.domain.*;
import com.yummipizza.api.service.dto.CustomerDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Customer} and its DTO {@link CustomerDTO}.
 */
@Mapper(componentModel = "spring", uses = {AddressMapper.class})
public interface CustomerMapper extends EntityMapper<CustomerDTO, Customer> {

    @Mapping(source = "address.id", target = "addressId")
    CustomerDTO toDto(Customer customer);

    @Mapping(source = "addressId", target = "address")
    @Mapping(target = "orders", ignore = true)
    @Mapping(target = "removeOrders", ignore = true)
    Customer toEntity(CustomerDTO customerDTO);

    default Customer fromId(Long id) {
        if (id == null) {
            return null;
        }
        Customer customer = new Customer();
        customer.setId(id);
        return customer;
    }
}
