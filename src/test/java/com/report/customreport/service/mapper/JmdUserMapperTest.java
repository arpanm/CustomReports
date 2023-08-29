package com.report.customreport.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class JmdUserMapperTest {

    private JmdUserMapper jmdUserMapper;

    @BeforeEach
    public void setUp() {
        jmdUserMapper = new JmdUserMapperImpl();
    }
}
