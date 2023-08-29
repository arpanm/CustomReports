package com.report.customreport.web.rest;

import com.report.customreport.repository.JmdSalesRepository;
import com.report.customreport.service.JmdSalesService;
import com.report.customreport.service.dto.JmdSalesDTO;
import com.report.customreport.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.report.customreport.domain.JmdSales}.
 */
@RestController
@RequestMapping("/api")
public class JmdSalesResource {

    private final Logger log = LoggerFactory.getLogger(JmdSalesResource.class);

    private static final String ENTITY_NAME = "jmdSales";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final JmdSalesService jmdSalesService;

    private final JmdSalesRepository jmdSalesRepository;

    public JmdSalesResource(JmdSalesService jmdSalesService, JmdSalesRepository jmdSalesRepository) {
        this.jmdSalesService = jmdSalesService;
        this.jmdSalesRepository = jmdSalesRepository;
    }

    /**
     * {@code POST  /jmd-sales} : Create a new jmdSales.
     *
     * @param jmdSalesDTO the jmdSalesDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new jmdSalesDTO, or with status {@code 400 (Bad Request)} if the jmdSales has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/jmd-sales")
    public ResponseEntity<JmdSalesDTO> createJmdSales(@RequestBody JmdSalesDTO jmdSalesDTO) throws URISyntaxException {
        log.debug("REST request to save JmdSales : {}", jmdSalesDTO);
        if (jmdSalesDTO.getId() != null) {
            throw new BadRequestAlertException("A new jmdSales cannot already have an ID", ENTITY_NAME, "idexists");
        }
        JmdSalesDTO result = jmdSalesService.save(jmdSalesDTO);
        return ResponseEntity
            .created(new URI("/api/jmd-sales/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /jmd-sales/:id} : Updates an existing jmdSales.
     *
     * @param id the id of the jmdSalesDTO to save.
     * @param jmdSalesDTO the jmdSalesDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated jmdSalesDTO,
     * or with status {@code 400 (Bad Request)} if the jmdSalesDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the jmdSalesDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/jmd-sales/{id}")
    public ResponseEntity<JmdSalesDTO> updateJmdSales(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody JmdSalesDTO jmdSalesDTO
    ) throws URISyntaxException {
        log.debug("REST request to update JmdSales : {}, {}", id, jmdSalesDTO);
        if (jmdSalesDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, jmdSalesDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!jmdSalesRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        JmdSalesDTO result = jmdSalesService.update(jmdSalesDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, jmdSalesDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /jmd-sales/:id} : Partial updates given fields of an existing jmdSales, field will ignore if it is null
     *
     * @param id the id of the jmdSalesDTO to save.
     * @param jmdSalesDTO the jmdSalesDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated jmdSalesDTO,
     * or with status {@code 400 (Bad Request)} if the jmdSalesDTO is not valid,
     * or with status {@code 404 (Not Found)} if the jmdSalesDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the jmdSalesDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/jmd-sales/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<JmdSalesDTO> partialUpdateJmdSales(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody JmdSalesDTO jmdSalesDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update JmdSales partially : {}, {}", id, jmdSalesDTO);
        if (jmdSalesDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, jmdSalesDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!jmdSalesRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<JmdSalesDTO> result = jmdSalesService.partialUpdate(jmdSalesDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, jmdSalesDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /jmd-sales} : get all the jmdSales.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of jmdSales in body.
     */
    @GetMapping("/jmd-sales")
    public ResponseEntity<List<JmdSalesDTO>> getAllJmdSales(@org.springdoc.core.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of JmdSales");
        Page<JmdSalesDTO> page = jmdSalesService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /jmd-sales/:id} : get the "id" jmdSales.
     *
     * @param id the id of the jmdSalesDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the jmdSalesDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/jmd-sales/{id}")
    public ResponseEntity<JmdSalesDTO> getJmdSales(@PathVariable Long id) {
        log.debug("REST request to get JmdSales : {}", id);
        Optional<JmdSalesDTO> jmdSalesDTO = jmdSalesService.findOne(id);
        return ResponseUtil.wrapOrNotFound(jmdSalesDTO);
    }

    /**
     * {@code DELETE  /jmd-sales/:id} : delete the "id" jmdSales.
     *
     * @param id the id of the jmdSalesDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/jmd-sales/{id}")
    public ResponseEntity<Void> deleteJmdSales(@PathVariable Long id) {
        log.debug("REST request to delete JmdSales : {}", id);
        jmdSalesService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
