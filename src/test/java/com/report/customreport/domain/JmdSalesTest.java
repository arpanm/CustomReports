package com.report.customreport.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.report.customreport.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class JmdSalesTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(JmdSales.class);
        JmdSales jmdSales1 = new JmdSales();
        jmdSales1.setId(1L);
        JmdSales jmdSales2 = new JmdSales();
        jmdSales2.setId(jmdSales1.getId());
        assertThat(jmdSales1).isEqualTo(jmdSales2);
        jmdSales2.setId(2L);
        assertThat(jmdSales1).isNotEqualTo(jmdSales2);
        jmdSales1.setId(null);
        assertThat(jmdSales1).isNotEqualTo(jmdSales2);
    }
}
