package com.yummipizza.api.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class CustomerMessageMapperTest {

    private CustomerMessageMapper customerMessageMapper;

    @BeforeEach
    public void setUp() {
        customerMessageMapper = new CustomerMessageMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(customerMessageMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(customerMessageMapper.fromId(null)).isNull();
    }
}
