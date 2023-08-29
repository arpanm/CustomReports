package com.report.customreport.repository;

import com.report.customreport.domain.JmdSales;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the JmdSales entity.
 */
@SuppressWarnings("unused")
@Repository
public interface JmdSalesRepository extends JpaRepository<JmdSales, Long> {}
