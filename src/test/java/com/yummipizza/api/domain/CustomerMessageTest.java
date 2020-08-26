package com.yummipizza.api.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.yummipizza.api.web.rest.TestUtil;

public class CustomerMessageTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CustomerMessage.class);
        CustomerMessage customerMessage1 = new CustomerMessage();
        customerMessage1.setId(1L);
        CustomerMessage customerMessage2 = new CustomerMessage();
        customerMessage2.setId(customerMessage1.getId());
        assertThat(customerMessage1).isEqualTo(customerMessage2);
        customerMessage2.setId(2L);
        assertThat(customerMessage1).isNotEqualTo(customerMessage2);
        customerMessage1.setId(null);
        assertThat(customerMessage1).isNotEqualTo(customerMessage2);
    }
}
