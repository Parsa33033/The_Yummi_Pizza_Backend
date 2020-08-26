package com.yummipizza.api.service.mapper;


import com.yummipizza.api.domain.*;
import com.yummipizza.api.service.dto.RatingDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Rating} and its DTO {@link RatingDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface RatingMapper extends EntityMapper<RatingDTO, Rating> {



    default Rating fromId(Long id) {
        if (id == null) {
            return null;
        }
        Rating rating = new Rating();
        rating.setId(id);
        return rating;
    }
}
