package com.yummipizza.api.service.mapper;


import com.yummipizza.api.domain.*;
import com.yummipizza.api.service.dto.CustomerMessageDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link CustomerMessage} and its DTO {@link CustomerMessageDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CustomerMessageMapper extends EntityMapper<CustomerMessageDTO, CustomerMessage> {



    default CustomerMessage fromId(Long id) {
        if (id == null) {
            return null;
        }
        CustomerMessage customerMessage = new CustomerMessage();
        customerMessage.setId(id);
        return customerMessage;
    }
}
