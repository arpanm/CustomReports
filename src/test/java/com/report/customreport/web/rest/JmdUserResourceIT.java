package com.report.customreport.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.report.customreport.IntegrationTest;
import com.report.customreport.domain.JmdUser;
import com.report.customreport.domain.enumeration.JmdClass;
import com.report.customreport.domain.enumeration.JmdRole;
import com.report.customreport.repository.JmdUserRepository;
import com.report.customreport.service.JmdUserService;
import com.report.customreport.service.dto.JmdUserDTO;
import com.report.customreport.service.mapper.JmdUserMapper;
import jakarta.persistence.EntityManager;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link JmdUserResource} REST controller.
 */
@IntegrationTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
class JmdUserResourceIT {

    private static final Long DEFAULT_PRMID = 1L;
    private static final Long UPDATED_PRMID = 2L;

    private static final JmdRole DEFAULT_JMD_ROLE = JmdRole.Jmdo;
    private static final JmdRole UPDATED_JMD_ROLE = JmdRole.Retailer;

    private static final JmdClass DEFAULT_JMD_CLASS = JmdClass.AClass;
    private static final JmdClass UPDATED_JMD_CLASS = JmdClass.APlusClass;

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final Long DEFAULT_PHONE = 1L;
    private static final Long UPDATED_PHONE = 2L;

    private static final Boolean DEFAULT_IS_ACTIVE = false;
    private static final Boolean UPDATED_IS_ACTIVE = true;

    private static final Long DEFAULT_CREATED_BY = 1L;
    private static final Long UPDATED_CREATED_BY = 2L;

    private static final LocalDate DEFAULT_CREATED_ON = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CREATED_ON = LocalDate.now(ZoneId.systemDefault());

    private static final Long DEFAULT_UPDATED_BY = 1L;
    private static final Long UPDATED_UPDATED_BY = 2L;

    private static final LocalDate DEFAULT_UPDATED_ON = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_UPDATED_ON = LocalDate.now(ZoneId.systemDefault());

    private static final String ENTITY_API_URL = "/api/jmd-users";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private JmdUserRepository jmdUserRepository;

    @Mock
    private JmdUserRepository jmdUserRepositoryMock;

    @Autowired
    private JmdUserMapper jmdUserMapper;

    @Mock
    private JmdUserService jmdUserServiceMock;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restJmdUserMockMvc;

    private JmdUser jmdUser;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static JmdUser createEntity(EntityManager em) {
        JmdUser jmdUser = new JmdUser()
            .prmid(DEFAULT_PRMID)
            .jmdRole(DEFAULT_JMD_ROLE)
            .jmdClass(DEFAULT_JMD_CLASS)
            .name(DEFAULT_NAME)
            .phone(DEFAULT_PHONE)
            .isActive(DEFAULT_IS_ACTIVE)
            .createdBy(DEFAULT_CREATED_BY)
            .createdOn(DEFAULT_CREATED_ON)
            .updatedBy(DEFAULT_UPDATED_BY)
            .updatedOn(DEFAULT_UPDATED_ON);
        return jmdUser;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static JmdUser createUpdatedEntity(EntityManager em) {
        JmdUser jmdUser = new JmdUser()
            .prmid(UPDATED_PRMID)
            .jmdRole(UPDATED_JMD_ROLE)
            .jmdClass(UPDATED_JMD_CLASS)
            .name(UPDATED_NAME)
            .phone(UPDATED_PHONE)
            .isActive(UPDATED_IS_ACTIVE)
            .createdBy(UPDATED_CREATED_BY)
            .createdOn(UPDATED_CREATED_ON)
            .updatedBy(UPDATED_UPDATED_BY)
            .updatedOn(UPDATED_UPDATED_ON);
        return jmdUser;
    }

    @BeforeEach
    public void initTest() {
        jmdUser = createEntity(em);
    }

    @Test
    @Transactional
    void createJmdUser() throws Exception {
        int databaseSizeBeforeCreate = jmdUserRepository.findAll().size();
        // Create the JmdUser
        JmdUserDTO jmdUserDTO = jmdUserMapper.toDto(jmdUser);
        restJmdUserMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(jmdUserDTO)))
            .andExpect(status().isCreated());

        // Validate the JmdUser in the database
        List<JmdUser> jmdUserList = jmdUserRepository.findAll();
        assertThat(jmdUserList).hasSize(databaseSizeBeforeCreate + 1);
        JmdUser testJmdUser = jmdUserList.get(jmdUserList.size() - 1);
        assertThat(testJmdUser.getPrmid()).isEqualTo(DEFAULT_PRMID);
        assertThat(testJmdUser.getJmdRole()).isEqualTo(DEFAULT_JMD_ROLE);
        assertThat(testJmdUser.getJmdClass()).isEqualTo(DEFAULT_JMD_CLASS);
        assertThat(testJmdUser.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testJmdUser.getPhone()).isEqualTo(DEFAULT_PHONE);
        assertThat(testJmdUser.getIsActive()).isEqualTo(DEFAULT_IS_ACTIVE);
        assertThat(testJmdUser.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testJmdUser.getCreatedOn()).isEqualTo(DEFAULT_CREATED_ON);
        assertThat(testJmdUser.getUpdatedBy()).isEqualTo(DEFAULT_UPDATED_BY);
        assertThat(testJmdUser.getUpdatedOn()).isEqualTo(DEFAULT_UPDATED_ON);
    }

    @Test
    @Transactional
    void createJmdUserWithExistingId() throws Exception {
        // Create the JmdUser with an existing ID
        jmdUser.setId(1L);
        JmdUserDTO jmdUserDTO = jmdUserMapper.toDto(jmdUser);

        int databaseSizeBeforeCreate = jmdUserRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restJmdUserMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(jmdUserDTO)))
            .andExpect(status().isBadRequest());

        // Validate the JmdUser in the database
        List<JmdUser> jmdUserList = jmdUserRepository.findAll();
        assertThat(jmdUserList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllJmdUsers() throws Exception {
        // Initialize the database
        jmdUserRepository.saveAndFlush(jmdUser);

        // Get all the jmdUserList
        restJmdUserMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(jmdUser.getId().intValue())))
            .andExpect(jsonPath("$.[*].prmid").value(hasItem(DEFAULT_PRMID.intValue())))
            .andExpect(jsonPath("$.[*].jmdRole").value(hasItem(DEFAULT_JMD_ROLE.toString())))
            .andExpect(jsonPath("$.[*].jmdClass").value(hasItem(DEFAULT_JMD_CLASS.toString())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].phone").value(hasItem(DEFAULT_PHONE.intValue())))
            .andExpect(jsonPath("$.[*].isActive").value(hasItem(DEFAULT_IS_ACTIVE.booleanValue())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY.intValue())))
            .andExpect(jsonPath("$.[*].createdOn").value(hasItem(DEFAULT_CREATED_ON.toString())))
            .andExpect(jsonPath("$.[*].updatedBy").value(hasItem(DEFAULT_UPDATED_BY.intValue())))
            .andExpect(jsonPath("$.[*].updatedOn").value(hasItem(DEFAULT_UPDATED_ON.toString())));
    }

    @SuppressWarnings({ "unchecked" })
    void getAllJmdUsersWithEagerRelationshipsIsEnabled() throws Exception {
        when(jmdUserServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restJmdUserMockMvc.perform(get(ENTITY_API_URL + "?eagerload=true")).andExpect(status().isOk());

        verify(jmdUserServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({ "unchecked" })
    void getAllJmdUsersWithEagerRelationshipsIsNotEnabled() throws Exception {
        when(jmdUserServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restJmdUserMockMvc.perform(get(ENTITY_API_URL + "?eagerload=false")).andExpect(status().isOk());
        verify(jmdUserRepositoryMock, times(1)).findAll(any(Pageable.class));
    }

    @Test
    @Transactional
    void getJmdUser() throws Exception {
        // Initialize the database
        jmdUserRepository.saveAndFlush(jmdUser);

        // Get the jmdUser
        restJmdUserMockMvc
            .perform(get(ENTITY_API_URL_ID, jmdUser.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(jmdUser.getId().intValue()))
            .andExpect(jsonPath("$.prmid").value(DEFAULT_PRMID.intValue()))
            .andExpect(jsonPath("$.jmdRole").value(DEFAULT_JMD_ROLE.toString()))
            .andExpect(jsonPath("$.jmdClass").value(DEFAULT_JMD_CLASS.toString()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.phone").value(DEFAULT_PHONE.intValue()))
            .andExpect(jsonPath("$.isActive").value(DEFAULT_IS_ACTIVE.booleanValue()))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY.intValue()))
            .andExpect(jsonPath("$.createdOn").value(DEFAULT_CREATED_ON.toString()))
            .andExpect(jsonPath("$.updatedBy").value(DEFAULT_UPDATED_BY.intValue()))
            .andExpect(jsonPath("$.updatedOn").value(DEFAULT_UPDATED_ON.toString()));
    }

    @Test
    @Transactional
    void getNonExistingJmdUser() throws Exception {
        // Get the jmdUser
        restJmdUserMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingJmdUser() throws Exception {
        // Initialize the database
        jmdUserRepository.saveAndFlush(jmdUser);

        int databaseSizeBeforeUpdate = jmdUserRepository.findAll().size();

        // Update the jmdUser
        JmdUser updatedJmdUser = jmdUserRepository.findById(jmdUser.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedJmdUser are not directly saved in db
        em.detach(updatedJmdUser);
        updatedJmdUser
            .prmid(UPDATED_PRMID)
            .jmdRole(UPDATED_JMD_ROLE)
            .jmdClass(UPDATED_JMD_CLASS)
            .name(UPDATED_NAME)
            .phone(UPDATED_PHONE)
            .isActive(UPDATED_IS_ACTIVE)
            .createdBy(UPDATED_CREATED_BY)
            .createdOn(UPDATED_CREATED_ON)
            .updatedBy(UPDATED_UPDATED_BY)
            .updatedOn(UPDATED_UPDATED_ON);
        JmdUserDTO jmdUserDTO = jmdUserMapper.toDto(updatedJmdUser);

        restJmdUserMockMvc
            .perform(
                put(ENTITY_API_URL_ID, jmdUserDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(jmdUserDTO))
            )
            .andExpect(status().isOk());

        // Validate the JmdUser in the database
        List<JmdUser> jmdUserList = jmdUserRepository.findAll();
        assertThat(jmdUserList).hasSize(databaseSizeBeforeUpdate);
        JmdUser testJmdUser = jmdUserList.get(jmdUserList.size() - 1);
        assertThat(testJmdUser.getPrmid()).isEqualTo(UPDATED_PRMID);
        assertThat(testJmdUser.getJmdRole()).isEqualTo(UPDATED_JMD_ROLE);
        assertThat(testJmdUser.getJmdClass()).isEqualTo(UPDATED_JMD_CLASS);
        assertThat(testJmdUser.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testJmdUser.getPhone()).isEqualTo(UPDATED_PHONE);
        assertThat(testJmdUser.getIsActive()).isEqualTo(UPDATED_IS_ACTIVE);
        assertThat(testJmdUser.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testJmdUser.getCreatedOn()).isEqualTo(UPDATED_CREATED_ON);
        assertThat(testJmdUser.getUpdatedBy()).isEqualTo(UPDATED_UPDATED_BY);
        assertThat(testJmdUser.getUpdatedOn()).isEqualTo(UPDATED_UPDATED_ON);
    }

    @Test
    @Transactional
    void putNonExistingJmdUser() throws Exception {
        int databaseSizeBeforeUpdate = jmdUserRepository.findAll().size();
        jmdUser.setId(count.incrementAndGet());

        // Create the JmdUser
        JmdUserDTO jmdUserDTO = jmdUserMapper.toDto(jmdUser);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restJmdUserMockMvc
            .perform(
                put(ENTITY_API_URL_ID, jmdUserDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(jmdUserDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the JmdUser in the database
        List<JmdUser> jmdUserList = jmdUserRepository.findAll();
        assertThat(jmdUserList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchJmdUser() throws Exception {
        int databaseSizeBeforeUpdate = jmdUserRepository.findAll().size();
        jmdUser.setId(count.incrementAndGet());

        // Create the JmdUser
        JmdUserDTO jmdUserDTO = jmdUserMapper.toDto(jmdUser);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restJmdUserMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(jmdUserDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the JmdUser in the database
        List<JmdUser> jmdUserList = jmdUserRepository.findAll();
        assertThat(jmdUserList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamJmdUser() throws Exception {
        int databaseSizeBeforeUpdate = jmdUserRepository.findAll().size();
        jmdUser.setId(count.incrementAndGet());

        // Create the JmdUser
        JmdUserDTO jmdUserDTO = jmdUserMapper.toDto(jmdUser);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restJmdUserMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(jmdUserDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the JmdUser in the database
        List<JmdUser> jmdUserList = jmdUserRepository.findAll();
        assertThat(jmdUserList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateJmdUserWithPatch() throws Exception {
        // Initialize the database
        jmdUserRepository.saveAndFlush(jmdUser);

        int databaseSizeBeforeUpdate = jmdUserRepository.findAll().size();

        // Update the jmdUser using partial update
        JmdUser partialUpdatedJmdUser = new JmdUser();
        partialUpdatedJmdUser.setId(jmdUser.getId());

        partialUpdatedJmdUser.jmdRole(UPDATED_JMD_ROLE).name(UPDATED_NAME).phone(UPDATED_PHONE).isActive(UPDATED_IS_ACTIVE);

        restJmdUserMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedJmdUser.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedJmdUser))
            )
            .andExpect(status().isOk());

        // Validate the JmdUser in the database
        List<JmdUser> jmdUserList = jmdUserRepository.findAll();
        assertThat(jmdUserList).hasSize(databaseSizeBeforeUpdate);
        JmdUser testJmdUser = jmdUserList.get(jmdUserList.size() - 1);
        assertThat(testJmdUser.getPrmid()).isEqualTo(DEFAULT_PRMID);
        assertThat(testJmdUser.getJmdRole()).isEqualTo(UPDATED_JMD_ROLE);
        assertThat(testJmdUser.getJmdClass()).isEqualTo(DEFAULT_JMD_CLASS);
        assertThat(testJmdUser.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testJmdUser.getPhone()).isEqualTo(UPDATED_PHONE);
        assertThat(testJmdUser.getIsActive()).isEqualTo(UPDATED_IS_ACTIVE);
        assertThat(testJmdUser.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testJmdUser.getCreatedOn()).isEqualTo(DEFAULT_CREATED_ON);
        assertThat(testJmdUser.getUpdatedBy()).isEqualTo(DEFAULT_UPDATED_BY);
        assertThat(testJmdUser.getUpdatedOn()).isEqualTo(DEFAULT_UPDATED_ON);
    }

    @Test
    @Transactional
    void fullUpdateJmdUserWithPatch() throws Exception {
        // Initialize the database
        jmdUserRepository.saveAndFlush(jmdUser);

        int databaseSizeBeforeUpdate = jmdUserRepository.findAll().size();

        // Update the jmdUser using partial update
        JmdUser partialUpdatedJmdUser = new JmdUser();
        partialUpdatedJmdUser.setId(jmdUser.getId());

        partialUpdatedJmdUser
            .prmid(UPDATED_PRMID)
            .jmdRole(UPDATED_JMD_ROLE)
            .jmdClass(UPDATED_JMD_CLASS)
            .name(UPDATED_NAME)
            .phone(UPDATED_PHONE)
            .isActive(UPDATED_IS_ACTIVE)
            .createdBy(UPDATED_CREATED_BY)
            .createdOn(UPDATED_CREATED_ON)
            .updatedBy(UPDATED_UPDATED_BY)
            .updatedOn(UPDATED_UPDATED_ON);

        restJmdUserMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedJmdUser.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedJmdUser))
            )
            .andExpect(status().isOk());

        // Validate the JmdUser in the database
        List<JmdUser> jmdUserList = jmdUserRepository.findAll();
        assertThat(jmdUserList).hasSize(databaseSizeBeforeUpdate);
        JmdUser testJmdUser = jmdUserList.get(jmdUserList.size() - 1);
        assertThat(testJmdUser.getPrmid()).isEqualTo(UPDATED_PRMID);
        assertThat(testJmdUser.getJmdRole()).isEqualTo(UPDATED_JMD_ROLE);
        assertThat(testJmdUser.getJmdClass()).isEqualTo(UPDATED_JMD_CLASS);
        assertThat(testJmdUser.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testJmdUser.getPhone()).isEqualTo(UPDATED_PHONE);
        assertThat(testJmdUser.getIsActive()).isEqualTo(UPDATED_IS_ACTIVE);
        assertThat(testJmdUser.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testJmdUser.getCreatedOn()).isEqualTo(UPDATED_CREATED_ON);
        assertThat(testJmdUser.getUpdatedBy()).isEqualTo(UPDATED_UPDATED_BY);
        assertThat(testJmdUser.getUpdatedOn()).isEqualTo(UPDATED_UPDATED_ON);
    }

    @Test
    @Transactional
    void patchNonExistingJmdUser() throws Exception {
        int databaseSizeBeforeUpdate = jmdUserRepository.findAll().size();
        jmdUser.setId(count.incrementAndGet());

        // Create the JmdUser
        JmdUserDTO jmdUserDTO = jmdUserMapper.toDto(jmdUser);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restJmdUserMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, jmdUserDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(jmdUserDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the JmdUser in the database
        List<JmdUser> jmdUserList = jmdUserRepository.findAll();
        assertThat(jmdUserList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchJmdUser() throws Exception {
        int databaseSizeBeforeUpdate = jmdUserRepository.findAll().size();
        jmdUser.setId(count.incrementAndGet());

        // Create the JmdUser
        JmdUserDTO jmdUserDTO = jmdUserMapper.toDto(jmdUser);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restJmdUserMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(jmdUserDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the JmdUser in the database
        List<JmdUser> jmdUserList = jmdUserRepository.findAll();
        assertThat(jmdUserList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamJmdUser() throws Exception {
        int databaseSizeBeforeUpdate = jmdUserRepository.findAll().size();
        jmdUser.setId(count.incrementAndGet());

        // Create the JmdUser
        JmdUserDTO jmdUserDTO = jmdUserMapper.toDto(jmdUser);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restJmdUserMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(jmdUserDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the JmdUser in the database
        List<JmdUser> jmdUserList = jmdUserRepository.findAll();
        assertThat(jmdUserList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteJmdUser() throws Exception {
        // Initialize the database
        jmdUserRepository.saveAndFlush(jmdUser);

        int databaseSizeBeforeDelete = jmdUserRepository.findAll().size();

        // Delete the jmdUser
        restJmdUserMockMvc
            .perform(delete(ENTITY_API_URL_ID, jmdUser.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<JmdUser> jmdUserList = jmdUserRepository.findAll();
        assertThat(jmdUserList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
