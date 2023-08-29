package com.report.customreport.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.report.customreport.IntegrationTest;
import com.report.customreport.domain.JmdSales;
import com.report.customreport.repository.JmdSalesRepository;
import com.report.customreport.service.dto.JmdSalesDTO;
import com.report.customreport.service.mapper.JmdSalesMapper;
import jakarta.persistence.EntityManager;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link JmdSalesResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class JmdSalesResourceIT {

    private static final Long DEFAULT_PRMID = 1L;
    private static final Long UPDATED_PRMID = 2L;

    private static final Long DEFAULT_UNIS_ORDER = 1L;
    private static final Long UPDATED_UNIS_ORDER = 2L;

    private static final Long DEFAULT_UNITS_DELIVERED = 1L;
    private static final Long UPDATED_UNITS_DELIVERED = 2L;

    private static final Long DEFAULT_UNIS_ACTIVATED = 1L;
    private static final Long UPDATED_UNIS_ACTIVATED = 2L;

    private static final Long DEFAULT_JMD_MONTH = 1L;
    private static final Long UPDATED_JMD_MONTH = 2L;

    private static final Long DEFAULT_JMD_YEAR = 1L;
    private static final Long UPDATED_JMD_YEAR = 2L;

    private static final Long DEFAULT_JMDDATE = 1L;
    private static final Long UPDATED_JMDDATE = 2L;

    private static final Long DEFAULT_CREATED_BY = 1L;
    private static final Long UPDATED_CREATED_BY = 2L;

    private static final LocalDate DEFAULT_CREATED_ON = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CREATED_ON = LocalDate.now(ZoneId.systemDefault());

    private static final Long DEFAULT_UPDATED_BY = 1L;
    private static final Long UPDATED_UPDATED_BY = 2L;

    private static final LocalDate DEFAULT_UPDATED_ON = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_UPDATED_ON = LocalDate.now(ZoneId.systemDefault());

    private static final String ENTITY_API_URL = "/api/jmd-sales";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private JmdSalesRepository jmdSalesRepository;

    @Autowired
    private JmdSalesMapper jmdSalesMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restJmdSalesMockMvc;

    private JmdSales jmdSales;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static JmdSales createEntity(EntityManager em) {
        JmdSales jmdSales = new JmdSales()
            .prmid(DEFAULT_PRMID)
            .unisOrder(DEFAULT_UNIS_ORDER)
            .unitsDelivered(DEFAULT_UNITS_DELIVERED)
            .unisActivated(DEFAULT_UNIS_ACTIVATED)
            .jmdMonth(DEFAULT_JMD_MONTH)
            .jmdYear(DEFAULT_JMD_YEAR)
            .jmddate(DEFAULT_JMDDATE)
            .createdBy(DEFAULT_CREATED_BY)
            .createdOn(DEFAULT_CREATED_ON)
            .updatedBy(DEFAULT_UPDATED_BY)
            .updatedOn(DEFAULT_UPDATED_ON);
        return jmdSales;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static JmdSales createUpdatedEntity(EntityManager em) {
        JmdSales jmdSales = new JmdSales()
            .prmid(UPDATED_PRMID)
            .unisOrder(UPDATED_UNIS_ORDER)
            .unitsDelivered(UPDATED_UNITS_DELIVERED)
            .unisActivated(UPDATED_UNIS_ACTIVATED)
            .jmdMonth(UPDATED_JMD_MONTH)
            .jmdYear(UPDATED_JMD_YEAR)
            .jmddate(UPDATED_JMDDATE)
            .createdBy(UPDATED_CREATED_BY)
            .createdOn(UPDATED_CREATED_ON)
            .updatedBy(UPDATED_UPDATED_BY)
            .updatedOn(UPDATED_UPDATED_ON);
        return jmdSales;
    }

    @BeforeEach
    public void initTest() {
        jmdSales = createEntity(em);
    }

    @Test
    @Transactional
    void createJmdSales() throws Exception {
        int databaseSizeBeforeCreate = jmdSalesRepository.findAll().size();
        // Create the JmdSales
        JmdSalesDTO jmdSalesDTO = jmdSalesMapper.toDto(jmdSales);
        restJmdSalesMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(jmdSalesDTO)))
            .andExpect(status().isCreated());

        // Validate the JmdSales in the database
        List<JmdSales> jmdSalesList = jmdSalesRepository.findAll();
        assertThat(jmdSalesList).hasSize(databaseSizeBeforeCreate + 1);
        JmdSales testJmdSales = jmdSalesList.get(jmdSalesList.size() - 1);
        assertThat(testJmdSales.getPrmid()).isEqualTo(DEFAULT_PRMID);
        assertThat(testJmdSales.getUnisOrder()).isEqualTo(DEFAULT_UNIS_ORDER);
        assertThat(testJmdSales.getUnitsDelivered()).isEqualTo(DEFAULT_UNITS_DELIVERED);
        assertThat(testJmdSales.getUnisActivated()).isEqualTo(DEFAULT_UNIS_ACTIVATED);
        assertThat(testJmdSales.getJmdMonth()).isEqualTo(DEFAULT_JMD_MONTH);
        assertThat(testJmdSales.getJmdYear()).isEqualTo(DEFAULT_JMD_YEAR);
        assertThat(testJmdSales.getJmddate()).isEqualTo(DEFAULT_JMDDATE);
        assertThat(testJmdSales.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testJmdSales.getCreatedOn()).isEqualTo(DEFAULT_CREATED_ON);
        assertThat(testJmdSales.getUpdatedBy()).isEqualTo(DEFAULT_UPDATED_BY);
        assertThat(testJmdSales.getUpdatedOn()).isEqualTo(DEFAULT_UPDATED_ON);
    }

    @Test
    @Transactional
    void createJmdSalesWithExistingId() throws Exception {
        // Create the JmdSales with an existing ID
        jmdSales.setId(1L);
        JmdSalesDTO jmdSalesDTO = jmdSalesMapper.toDto(jmdSales);

        int databaseSizeBeforeCreate = jmdSalesRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restJmdSalesMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(jmdSalesDTO)))
            .andExpect(status().isBadRequest());

        // Validate the JmdSales in the database
        List<JmdSales> jmdSalesList = jmdSalesRepository.findAll();
        assertThat(jmdSalesList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllJmdSales() throws Exception {
        // Initialize the database
        jmdSalesRepository.saveAndFlush(jmdSales);

        // Get all the jmdSalesList
        restJmdSalesMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(jmdSales.getId().intValue())))
            .andExpect(jsonPath("$.[*].prmid").value(hasItem(DEFAULT_PRMID.intValue())))
            .andExpect(jsonPath("$.[*].unisOrder").value(hasItem(DEFAULT_UNIS_ORDER.intValue())))
            .andExpect(jsonPath("$.[*].unitsDelivered").value(hasItem(DEFAULT_UNITS_DELIVERED.intValue())))
            .andExpect(jsonPath("$.[*].unisActivated").value(hasItem(DEFAULT_UNIS_ACTIVATED.intValue())))
            .andExpect(jsonPath("$.[*].jmdMonth").value(hasItem(DEFAULT_JMD_MONTH.intValue())))
            .andExpect(jsonPath("$.[*].jmdYear").value(hasItem(DEFAULT_JMD_YEAR.intValue())))
            .andExpect(jsonPath("$.[*].jmddate").value(hasItem(DEFAULT_JMDDATE.intValue())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY.intValue())))
            .andExpect(jsonPath("$.[*].createdOn").value(hasItem(DEFAULT_CREATED_ON.toString())))
            .andExpect(jsonPath("$.[*].updatedBy").value(hasItem(DEFAULT_UPDATED_BY.intValue())))
            .andExpect(jsonPath("$.[*].updatedOn").value(hasItem(DEFAULT_UPDATED_ON.toString())));
    }

    @Test
    @Transactional
    void getJmdSales() throws Exception {
        // Initialize the database
        jmdSalesRepository.saveAndFlush(jmdSales);

        // Get the jmdSales
        restJmdSalesMockMvc
            .perform(get(ENTITY_API_URL_ID, jmdSales.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(jmdSales.getId().intValue()))
            .andExpect(jsonPath("$.prmid").value(DEFAULT_PRMID.intValue()))
            .andExpect(jsonPath("$.unisOrder").value(DEFAULT_UNIS_ORDER.intValue()))
            .andExpect(jsonPath("$.unitsDelivered").value(DEFAULT_UNITS_DELIVERED.intValue()))
            .andExpect(jsonPath("$.unisActivated").value(DEFAULT_UNIS_ACTIVATED.intValue()))
            .andExpect(jsonPath("$.jmdMonth").value(DEFAULT_JMD_MONTH.intValue()))
            .andExpect(jsonPath("$.jmdYear").value(DEFAULT_JMD_YEAR.intValue()))
            .andExpect(jsonPath("$.jmddate").value(DEFAULT_JMDDATE.intValue()))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY.intValue()))
            .andExpect(jsonPath("$.createdOn").value(DEFAULT_CREATED_ON.toString()))
            .andExpect(jsonPath("$.updatedBy").value(DEFAULT_UPDATED_BY.intValue()))
            .andExpect(jsonPath("$.updatedOn").value(DEFAULT_UPDATED_ON.toString()));
    }

    @Test
    @Transactional
    void getNonExistingJmdSales() throws Exception {
        // Get the jmdSales
        restJmdSalesMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingJmdSales() throws Exception {
        // Initialize the database
        jmdSalesRepository.saveAndFlush(jmdSales);

        int databaseSizeBeforeUpdate = jmdSalesRepository.findAll().size();

        // Update the jmdSales
        JmdSales updatedJmdSales = jmdSalesRepository.findById(jmdSales.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedJmdSales are not directly saved in db
        em.detach(updatedJmdSales);
        updatedJmdSales
            .prmid(UPDATED_PRMID)
            .unisOrder(UPDATED_UNIS_ORDER)
            .unitsDelivered(UPDATED_UNITS_DELIVERED)
            .unisActivated(UPDATED_UNIS_ACTIVATED)
            .jmdMonth(UPDATED_JMD_MONTH)
            .jmdYear(UPDATED_JMD_YEAR)
            .jmddate(UPDATED_JMDDATE)
            .createdBy(UPDATED_CREATED_BY)
            .createdOn(UPDATED_CREATED_ON)
            .updatedBy(UPDATED_UPDATED_BY)
            .updatedOn(UPDATED_UPDATED_ON);
        JmdSalesDTO jmdSalesDTO = jmdSalesMapper.toDto(updatedJmdSales);

        restJmdSalesMockMvc
            .perform(
                put(ENTITY_API_URL_ID, jmdSalesDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(jmdSalesDTO))
            )
            .andExpect(status().isOk());

        // Validate the JmdSales in the database
        List<JmdSales> jmdSalesList = jmdSalesRepository.findAll();
        assertThat(jmdSalesList).hasSize(databaseSizeBeforeUpdate);
        JmdSales testJmdSales = jmdSalesList.get(jmdSalesList.size() - 1);
        assertThat(testJmdSales.getPrmid()).isEqualTo(UPDATED_PRMID);
        assertThat(testJmdSales.getUnisOrder()).isEqualTo(UPDATED_UNIS_ORDER);
        assertThat(testJmdSales.getUnitsDelivered()).isEqualTo(UPDATED_UNITS_DELIVERED);
        assertThat(testJmdSales.getUnisActivated()).isEqualTo(UPDATED_UNIS_ACTIVATED);
        assertThat(testJmdSales.getJmdMonth()).isEqualTo(UPDATED_JMD_MONTH);
        assertThat(testJmdSales.getJmdYear()).isEqualTo(UPDATED_JMD_YEAR);
        assertThat(testJmdSales.getJmddate()).isEqualTo(UPDATED_JMDDATE);
        assertThat(testJmdSales.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testJmdSales.getCreatedOn()).isEqualTo(UPDATED_CREATED_ON);
        assertThat(testJmdSales.getUpdatedBy()).isEqualTo(UPDATED_UPDATED_BY);
        assertThat(testJmdSales.getUpdatedOn()).isEqualTo(UPDATED_UPDATED_ON);
    }

    @Test
    @Transactional
    void putNonExistingJmdSales() throws Exception {
        int databaseSizeBeforeUpdate = jmdSalesRepository.findAll().size();
        jmdSales.setId(count.incrementAndGet());

        // Create the JmdSales
        JmdSalesDTO jmdSalesDTO = jmdSalesMapper.toDto(jmdSales);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restJmdSalesMockMvc
            .perform(
                put(ENTITY_API_URL_ID, jmdSalesDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(jmdSalesDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the JmdSales in the database
        List<JmdSales> jmdSalesList = jmdSalesRepository.findAll();
        assertThat(jmdSalesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchJmdSales() throws Exception {
        int databaseSizeBeforeUpdate = jmdSalesRepository.findAll().size();
        jmdSales.setId(count.incrementAndGet());

        // Create the JmdSales
        JmdSalesDTO jmdSalesDTO = jmdSalesMapper.toDto(jmdSales);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restJmdSalesMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(jmdSalesDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the JmdSales in the database
        List<JmdSales> jmdSalesList = jmdSalesRepository.findAll();
        assertThat(jmdSalesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamJmdSales() throws Exception {
        int databaseSizeBeforeUpdate = jmdSalesRepository.findAll().size();
        jmdSales.setId(count.incrementAndGet());

        // Create the JmdSales
        JmdSalesDTO jmdSalesDTO = jmdSalesMapper.toDto(jmdSales);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restJmdSalesMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(jmdSalesDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the JmdSales in the database
        List<JmdSales> jmdSalesList = jmdSalesRepository.findAll();
        assertThat(jmdSalesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateJmdSalesWithPatch() throws Exception {
        // Initialize the database
        jmdSalesRepository.saveAndFlush(jmdSales);

        int databaseSizeBeforeUpdate = jmdSalesRepository.findAll().size();

        // Update the jmdSales using partial update
        JmdSales partialUpdatedJmdSales = new JmdSales();
        partialUpdatedJmdSales.setId(jmdSales.getId());

        partialUpdatedJmdSales
            .prmid(UPDATED_PRMID)
            .unitsDelivered(UPDATED_UNITS_DELIVERED)
            .jmdMonth(UPDATED_JMD_MONTH)
            .jmddate(UPDATED_JMDDATE)
            .updatedBy(UPDATED_UPDATED_BY)
            .updatedOn(UPDATED_UPDATED_ON);

        restJmdSalesMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedJmdSales.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedJmdSales))
            )
            .andExpect(status().isOk());

        // Validate the JmdSales in the database
        List<JmdSales> jmdSalesList = jmdSalesRepository.findAll();
        assertThat(jmdSalesList).hasSize(databaseSizeBeforeUpdate);
        JmdSales testJmdSales = jmdSalesList.get(jmdSalesList.size() - 1);
        assertThat(testJmdSales.getPrmid()).isEqualTo(UPDATED_PRMID);
        assertThat(testJmdSales.getUnisOrder()).isEqualTo(DEFAULT_UNIS_ORDER);
        assertThat(testJmdSales.getUnitsDelivered()).isEqualTo(UPDATED_UNITS_DELIVERED);
        assertThat(testJmdSales.getUnisActivated()).isEqualTo(DEFAULT_UNIS_ACTIVATED);
        assertThat(testJmdSales.getJmdMonth()).isEqualTo(UPDATED_JMD_MONTH);
        assertThat(testJmdSales.getJmdYear()).isEqualTo(DEFAULT_JMD_YEAR);
        assertThat(testJmdSales.getJmddate()).isEqualTo(UPDATED_JMDDATE);
        assertThat(testJmdSales.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testJmdSales.getCreatedOn()).isEqualTo(DEFAULT_CREATED_ON);
        assertThat(testJmdSales.getUpdatedBy()).isEqualTo(UPDATED_UPDATED_BY);
        assertThat(testJmdSales.getUpdatedOn()).isEqualTo(UPDATED_UPDATED_ON);
    }

    @Test
    @Transactional
    void fullUpdateJmdSalesWithPatch() throws Exception {
        // Initialize the database
        jmdSalesRepository.saveAndFlush(jmdSales);

        int databaseSizeBeforeUpdate = jmdSalesRepository.findAll().size();

        // Update the jmdSales using partial update
        JmdSales partialUpdatedJmdSales = new JmdSales();
        partialUpdatedJmdSales.setId(jmdSales.getId());

        partialUpdatedJmdSales
            .prmid(UPDATED_PRMID)
            .unisOrder(UPDATED_UNIS_ORDER)
            .unitsDelivered(UPDATED_UNITS_DELIVERED)
            .unisActivated(UPDATED_UNIS_ACTIVATED)
            .jmdMonth(UPDATED_JMD_MONTH)
            .jmdYear(UPDATED_JMD_YEAR)
            .jmddate(UPDATED_JMDDATE)
            .createdBy(UPDATED_CREATED_BY)
            .createdOn(UPDATED_CREATED_ON)
            .updatedBy(UPDATED_UPDATED_BY)
            .updatedOn(UPDATED_UPDATED_ON);

        restJmdSalesMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedJmdSales.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedJmdSales))
            )
            .andExpect(status().isOk());

        // Validate the JmdSales in the database
        List<JmdSales> jmdSalesList = jmdSalesRepository.findAll();
        assertThat(jmdSalesList).hasSize(databaseSizeBeforeUpdate);
        JmdSales testJmdSales = jmdSalesList.get(jmdSalesList.size() - 1);
        assertThat(testJmdSales.getPrmid()).isEqualTo(UPDATED_PRMID);
        assertThat(testJmdSales.getUnisOrder()).isEqualTo(UPDATED_UNIS_ORDER);
        assertThat(testJmdSales.getUnitsDelivered()).isEqualTo(UPDATED_UNITS_DELIVERED);
        assertThat(testJmdSales.getUnisActivated()).isEqualTo(UPDATED_UNIS_ACTIVATED);
        assertThat(testJmdSales.getJmdMonth()).isEqualTo(UPDATED_JMD_MONTH);
        assertThat(testJmdSales.getJmdYear()).isEqualTo(UPDATED_JMD_YEAR);
        assertThat(testJmdSales.getJmddate()).isEqualTo(UPDATED_JMDDATE);
        assertThat(testJmdSales.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testJmdSales.getCreatedOn()).isEqualTo(UPDATED_CREATED_ON);
        assertThat(testJmdSales.getUpdatedBy()).isEqualTo(UPDATED_UPDATED_BY);
        assertThat(testJmdSales.getUpdatedOn()).isEqualTo(UPDATED_UPDATED_ON);
    }

    @Test
    @Transactional
    void patchNonExistingJmdSales() throws Exception {
        int databaseSizeBeforeUpdate = jmdSalesRepository.findAll().size();
        jmdSales.setId(count.incrementAndGet());

        // Create the JmdSales
        JmdSalesDTO jmdSalesDTO = jmdSalesMapper.toDto(jmdSales);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restJmdSalesMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, jmdSalesDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(jmdSalesDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the JmdSales in the database
        List<JmdSales> jmdSalesList = jmdSalesRepository.findAll();
        assertThat(jmdSalesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchJmdSales() throws Exception {
        int databaseSizeBeforeUpdate = jmdSalesRepository.findAll().size();
        jmdSales.setId(count.incrementAndGet());

        // Create the JmdSales
        JmdSalesDTO jmdSalesDTO = jmdSalesMapper.toDto(jmdSales);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restJmdSalesMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(jmdSalesDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the JmdSales in the database
        List<JmdSales> jmdSalesList = jmdSalesRepository.findAll();
        assertThat(jmdSalesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamJmdSales() throws Exception {
        int databaseSizeBeforeUpdate = jmdSalesRepository.findAll().size();
        jmdSales.setId(count.incrementAndGet());

        // Create the JmdSales
        JmdSalesDTO jmdSalesDTO = jmdSalesMapper.toDto(jmdSales);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restJmdSalesMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(jmdSalesDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the JmdSales in the database
        List<JmdSales> jmdSalesList = jmdSalesRepository.findAll();
        assertThat(jmdSalesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteJmdSales() throws Exception {
        // Initialize the database
        jmdSalesRepository.saveAndFlush(jmdSales);

        int databaseSizeBeforeDelete = jmdSalesRepository.findAll().size();

        // Delete the jmdSales
        restJmdSalesMockMvc
            .perform(delete(ENTITY_API_URL_ID, jmdSales.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<JmdSales> jmdSalesList = jmdSalesRepository.findAll();
        assertThat(jmdSalesList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
