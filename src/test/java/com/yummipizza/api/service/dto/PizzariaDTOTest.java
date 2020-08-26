package com.yummipizza.api.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.yummipizza.api.web.rest.TestUtil;

public class PizzariaDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PizzariaDTO.class);
        PizzariaDTO pizzariaDTO1 = new PizzariaDTO();
        pizzariaDTO1.setId(1L);
        PizzariaDTO pizzariaDTO2 = new PizzariaDTO();
        assertThat(pizzariaDTO1).isNotEqualTo(pizzariaDTO2);
        pizzariaDTO2.setId(pizzariaDTO1.getId());
        assertThat(pizzariaDTO1).isEqualTo(pizzariaDTO2);
        pizzariaDTO2.setId(2L);
        assertThat(pizzariaDTO1).isNotEqualTo(pizzariaDTO2);
        pizzariaDTO1.setId(null);
        assertThat(pizzariaDTO1).isNotEqualTo(pizzariaDTO2);
    }
}
