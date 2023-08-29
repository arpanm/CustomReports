package com.report.customreport.web.rest;

import com.report.customreport.repository.JmdUserRepository;
import com.report.customreport.service.JmdUserService;
import com.report.customreport.service.dto.JmdUserDTO;
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
 * REST controller for managing {@link com.report.customreport.domain.JmdUser}.
 */
@RestController
@RequestMapping("/api")
public class JmdUserResource {

    private final Logger log = LoggerFactory.getLogger(JmdUserResource.class);

    private static final String ENTITY_NAME = "jmdUser";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final JmdUserService jmdUserService;

    private final JmdUserRepository jmdUserRepository;

    public JmdUserResource(JmdUserService jmdUserService, JmdUserRepository jmdUserRepository) {
        this.jmdUserService = jmdUserService;
        this.jmdUserRepository = jmdUserRepository;
    }

    /**
     * {@code POST  /jmd-users} : Create a new jmdUser.
     *
     * @param jmdUserDTO the jmdUserDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new jmdUserDTO, or with status {@code 400 (Bad Request)} if the jmdUser has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/jmd-users")
    public ResponseEntity<JmdUserDTO> createJmdUser(@RequestBody JmdUserDTO jmdUserDTO) throws URISyntaxException {
        log.debug("REST request to save JmdUser : {}", jmdUserDTO);
        if (jmdUserDTO.getId() != null) {
            throw new BadRequestAlertException("A new jmdUser cannot already have an ID", ENTITY_NAME, "idexists");
        }
        JmdUserDTO result = jmdUserService.save(jmdUserDTO);
        return ResponseEntity
            .created(new URI("/api/jmd-users/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /jmd-users/:id} : Updates an existing jmdUser.
     *
     * @param id the id of the jmdUserDTO to save.
     * @param jmdUserDTO the jmdUserDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated jmdUserDTO,
     * or with status {@code 400 (Bad Request)} if the jmdUserDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the jmdUserDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/jmd-users/{id}")
    public ResponseEntity<JmdUserDTO> updateJmdUser(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody JmdUserDTO jmdUserDTO
    ) throws URISyntaxException {
        log.debug("REST request to update JmdUser : {}, {}", id, jmdUserDTO);
        if (jmdUserDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, jmdUserDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!jmdUserRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        JmdUserDTO result = jmdUserService.update(jmdUserDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, jmdUserDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /jmd-users/:id} : Partial updates given fields of an existing jmdUser, field will ignore if it is null
     *
     * @param id the id of the jmdUserDTO to save.
     * @param jmdUserDTO the jmdUserDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated jmdUserDTO,
     * or with status {@code 400 (Bad Request)} if the jmdUserDTO is not valid,
     * or with status {@code 404 (Not Found)} if the jmdUserDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the jmdUserDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/jmd-users/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<JmdUserDTO> partialUpdateJmdUser(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody JmdUserDTO jmdUserDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update JmdUser partially : {}, {}", id, jmdUserDTO);
        if (jmdUserDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, jmdUserDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!jmdUserRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<JmdUserDTO> result = jmdUserService.partialUpdate(jmdUserDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, jmdUserDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /jmd-users} : get all the jmdUsers.
     *
     * @param pageable the pagination information.
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of jmdUsers in body.
     */
    @GetMapping("/jmd-users")
    public ResponseEntity<List<JmdUserDTO>> getAllJmdUsers(
        @org.springdoc.core.annotations.ParameterObject Pageable pageable,
        @RequestParam(required = false, defaultValue = "false") boolean eagerload
    ) {
        log.debug("REST request to get a page of JmdUsers");
        Page<JmdUserDTO> page;
        if (eagerload) {
            page = jmdUserService.findAllWithEagerRelationships(pageable);
        } else {
            page = jmdUserService.findAll(pageable);
        }
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /jmd-users/:id} : get the "id" jmdUser.
     *
     * @param id the id of the jmdUserDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the jmdUserDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/jmd-users/{id}")
    public ResponseEntity<JmdUserDTO> getJmdUser(@PathVariable Long id) {
        log.debug("REST request to get JmdUser : {}", id);
        Optional<JmdUserDTO> jmdUserDTO = jmdUserService.findOne(id);
        return ResponseUtil.wrapOrNotFound(jmdUserDTO);
    }

    /**
     * {@code DELETE  /jmd-users/:id} : delete the "id" jmdUser.
     *
     * @param id the id of the jmdUserDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/jmd-users/{id}")
    public ResponseEntity<Void> deleteJmdUser(@PathVariable Long id) {
        log.debug("REST request to delete JmdUser : {}", id);
        jmdUserService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
