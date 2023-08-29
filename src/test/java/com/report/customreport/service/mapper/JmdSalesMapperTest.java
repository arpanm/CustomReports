package com.report.customreport.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class JmdSalesMapperTest {

    private JmdSalesMapper jmdSalesMapper;

    @BeforeEach
    public void setUp() {
        jmdSalesMapper = new JmdSalesMapperImpl();
    }
}
