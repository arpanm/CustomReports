package com.report.customreport.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.report.customreport.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class JmdUserDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(JmdUserDTO.class);
        JmdUserDTO jmdUserDTO1 = new JmdUserDTO();
        jmdUserDTO1.setId(1L);
        JmdUserDTO jmdUserDTO2 = new JmdUserDTO();
        assertThat(jmdUserDTO1).isNotEqualTo(jmdUserDTO2);
        jmdUserDTO2.setId(jmdUserDTO1.getId());
        assertThat(jmdUserDTO1).isEqualTo(jmdUserDTO2);
        jmdUserDTO2.setId(2L);
        assertThat(jmdUserDTO1).isNotEqualTo(jmdUserDTO2);
        jmdUserDTO1.setId(null);
        assertThat(jmdUserDTO1).isNotEqualTo(jmdUserDTO2);
    }
}
