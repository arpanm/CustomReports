package com.report.customreport.service;

import com.report.customreport.service.dto.JmdUserDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.report.customreport.domain.JmdUser}.
 */
public interface JmdUserService {
    /**
     * Save a jmdUser.
     *
     * @param jmdUserDTO the entity to save.
     * @return the persisted entity.
     */
    JmdUserDTO save(JmdUserDTO jmdUserDTO);

    /**
     * Updates a jmdUser.
     *
     * @param jmdUserDTO the entity to update.
     * @return the persisted entity.
     */
    JmdUserDTO update(JmdUserDTO jmdUserDTO);

    /**
     * Partially updates a jmdUser.
     *
     * @param jmdUserDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<JmdUserDTO> partialUpdate(JmdUserDTO jmdUserDTO);

    /**
     * Get all the jmdUsers.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<JmdUserDTO> findAll(Pageable pageable);

    /**
     * Get all the jmdUsers with eager load of many-to-many relationships.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<JmdUserDTO> findAllWithEagerRelationships(Pageable pageable);

    /**
     * Get the "id" jmdUser.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<JmdUserDTO> findOne(Long id);

    /**
     * Delete the "id" jmdUser.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
