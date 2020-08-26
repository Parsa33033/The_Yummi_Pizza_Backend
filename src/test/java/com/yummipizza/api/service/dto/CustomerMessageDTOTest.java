package com.yummipizza.api.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.yummipizza.api.web.rest.TestUtil;

public class CustomerMessageDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CustomerMessageDTO.class);
        CustomerMessageDTO customerMessageDTO1 = new CustomerMessageDTO();
        customerMessageDTO1.setId(1L);
        CustomerMessageDTO customerMessageDTO2 = new CustomerMessageDTO();
        assertThat(customerMessageDTO1).isNotEqualTo(customerMessageDTO2);
        customerMessageDTO2.setId(customerMessageDTO1.getId());
        assertThat(customerMessageDTO1).isEqualTo(customerMessageDTO2);
        customerMessageDTO2.setId(2L);
        assertThat(customerMessageDTO1).isNotEqualTo(customerMessageDTO2);
        customerMessageDTO1.setId(null);
        assertThat(customerMessageDTO1).isNotEqualTo(customerMessageDTO2);
    }
}
