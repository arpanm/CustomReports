package com.report.customreport.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.report.customreport.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class JmdUserTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(JmdUser.class);
        JmdUser jmdUser1 = new JmdUser();
        jmdUser1.setId(1L);
        JmdUser jmdUser2 = new JmdUser();
        jmdUser2.setId(jmdUser1.getId());
        assertThat(jmdUser1).isEqualTo(jmdUser2);
        jmdUser2.setId(2L);
        assertThat(jmdUser1).isNotEqualTo(jmdUser2);
        jmdUser1.setId(null);
        assertThat(jmdUser1).isNotEqualTo(jmdUser2);
    }
}
