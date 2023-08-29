package com.report.customreport.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.report.customreport.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class JmdSalesDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(JmdSalesDTO.class);
        JmdSalesDTO jmdSalesDTO1 = new JmdSalesDTO();
        jmdSalesDTO1.setId(1L);
        JmdSalesDTO jmdSalesDTO2 = new JmdSalesDTO();
        assertThat(jmdSalesDTO1).isNotEqualTo(jmdSalesDTO2);
        jmdSalesDTO2.setId(jmdSalesDTO1.getId());
        assertThat(jmdSalesDTO1).isEqualTo(jmdSalesDTO2);
        jmdSalesDTO2.setId(2L);
        assertThat(jmdSalesDTO1).isNotEqualTo(jmdSalesDTO2);
        jmdSalesDTO1.setId(null);
        assertThat(jmdSalesDTO1).isNotEqualTo(jmdSalesDTO2);
    }
}
