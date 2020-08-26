package com.yummipizza.api.service.mapper;


import com.yummipizza.api.domain.*;
import com.yummipizza.api.service.dto.OrderDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Order} and its DTO {@link OrderDTO}.
 */
@Mapper(componentModel = "spring", uses = {AddressMapper.class, CustomerMapper.class, PizzariaMapper.class})
public interface OrderMapper extends EntityMapper<OrderDTO, Order> {

    @Mapping(source = "address.id", target = "addressId")
    @Mapping(source = "customer.id", target = "customerId")
    @Mapping(source = "pizzaria.id", target = "pizzariaId")
    OrderDTO toDto(Order order);

    @Mapping(source = "addressId", target = "address")
    @Mapping(target = "items", ignore = true)
    @Mapping(target = "removeItems", ignore = true)
    @Mapping(source = "customerId", target = "customer")
    @Mapping(source = "pizzariaId", target = "pizzaria")
    Order toEntity(OrderDTO orderDTO);

    default Order fromId(Long id) {
        if (id == null) {
            return null;
        }
        Order order = new Order();
        order.setId(id);
        return order;
    }
}
