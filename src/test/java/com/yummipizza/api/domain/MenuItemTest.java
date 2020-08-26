package com.yummipizza.api.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.yummipizza.api.web.rest.TestUtil;

public class MenuItemTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MenuItem.class);
        MenuItem menuItem1 = new MenuItem();
        menuItem1.setId(1L);
        MenuItem menuItem2 = new MenuItem();
        menuItem2.setId(menuItem1.getId());
        assertThat(menuItem1).isEqualTo(menuItem2);
        menuItem2.setId(2L);
        assertThat(menuItem1).isNotEqualTo(menuItem2);
        menuItem1.setId(null);
        assertThat(menuItem1).isNotEqualTo(menuItem2);
    }
}
