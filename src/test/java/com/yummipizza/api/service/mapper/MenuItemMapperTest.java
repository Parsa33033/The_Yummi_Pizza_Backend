package com.yummipizza.api.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class MenuItemMapperTest {

    private MenuItemMapper menuItemMapper;

    @BeforeEach
    public void setUp() {
        menuItemMapper = new MenuItemMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(menuItemMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(menuItemMapper.fromId(null)).isNull();
    }
}
