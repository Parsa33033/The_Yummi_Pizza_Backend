package com.yummipizza.api.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.yummipizza.api.web.rest.TestUtil;

public class MenuItemDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MenuItemDTO.class);
        MenuItemDTO menuItemDTO1 = new MenuItemDTO();
        menuItemDTO1.setId(1L);
        MenuItemDTO menuItemDTO2 = new MenuItemDTO();
        assertThat(menuItemDTO1).isNotEqualTo(menuItemDTO2);
        menuItemDTO2.setId(menuItemDTO1.getId());
        assertThat(menuItemDTO1).isEqualTo(menuItemDTO2);
        menuItemDTO2.setId(2L);
        assertThat(menuItemDTO1).isNotEqualTo(menuItemDTO2);
        menuItemDTO1.setId(null);
        assertThat(menuItemDTO1).isNotEqualTo(menuItemDTO2);
    }
}
