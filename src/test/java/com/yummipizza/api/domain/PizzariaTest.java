package com.yummipizza.api.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.yummipizza.api.web.rest.TestUtil;

public class PizzariaTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Pizzaria.class);
        Pizzaria pizzaria1 = new Pizzaria();
        pizzaria1.setId(1L);
        Pizzaria pizzaria2 = new Pizzaria();
        pizzaria2.setId(pizzaria1.getId());
        assertThat(pizzaria1).isEqualTo(pizzaria2);
        pizzaria2.setId(2L);
        assertThat(pizzaria1).isNotEqualTo(pizzaria2);
        pizzaria1.setId(null);
        assertThat(pizzaria1).isNotEqualTo(pizzaria2);
    }
}
