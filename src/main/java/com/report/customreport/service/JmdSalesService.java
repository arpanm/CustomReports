package com.report.customreport.service;

import com.report.customreport.service.dto.JmdSalesDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.report.customreport.domain.JmdSales}.
 */
public interface JmdSalesService {
    /**
     * Save a jmdSales.
     *
     * @param jmdSalesDTO the entity to save.
     * @return the persisted entity.
     */
    JmdSalesDTO save(JmdSalesDTO jmdSalesDTO);

    /**
     * Updates a jmdSales.
     *
     * @param jmdSalesDTO the entity to update.
     * @return the persisted entity.
     */
    JmdSalesDTO update(JmdSalesDTO jmdSalesDTO);

    /**
     * Partially updates a jmdSales.
     *
     * @param jmdSalesDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<JmdSalesDTO> partialUpdate(JmdSalesDTO jmdSalesDTO);

    /**
     * Get all the jmdSales.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<JmdSalesDTO> findAll(Pageable pageable);

    /**
     * Get the "id" jmdSales.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<JmdSalesDTO> findOne(Long id);

    /**
     * Delete the "id" jmdSales.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
