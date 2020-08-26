package com.yummipizza.api.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class ManagerMapperTest {

    private ManagerMapper managerMapper;

    @BeforeEach
    public void setUp() {
        managerMapper = new ManagerMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(managerMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(managerMapper.fromId(null)).isNull();
    }
}
