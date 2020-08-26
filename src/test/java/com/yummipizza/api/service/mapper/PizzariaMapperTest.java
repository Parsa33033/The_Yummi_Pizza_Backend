package com.yummipizza.api.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class PizzariaMapperTest {

    private PizzariaMapper pizzariaMapper;

    @BeforeEach
    public void setUp() {
        pizzariaMapper = new PizzariaMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(pizzariaMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(pizzariaMapper.fromId(null)).isNull();
    }
}
