package com.yummipizza.api.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class RatingMapperTest {

    private RatingMapper ratingMapper;

    @BeforeEach
    public void setUp() {
        ratingMapper = new RatingMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(ratingMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(ratingMapper.fromId(null)).isNull();
    }
}
