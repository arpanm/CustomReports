package com.report.customreport.service.impl;

import com.report.customreport.domain.JmdUser;
import com.report.customreport.repository.JmdUserRepository;
import com.report.customreport.service.JmdUserService;
import com.report.customreport.service.dto.JmdUserDTO;
import com.report.customreport.service.mapper.JmdUserMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link JmdUser}.
 */
@Service
@Transactional
public class JmdUserServiceImpl implements JmdUserService {

    private final Logger log = LoggerFactory.getLogger(JmdUserServiceImpl.class);

    private final JmdUserRepository jmdUserRepository;

    private final JmdUserMapper jmdUserMapper;

    public JmdUserServiceImpl(JmdUserRepository jmdUserRepository, JmdUserMapper jmdUserMapper) {
        this.jmdUserRepository = jmdUserRepository;
        this.jmdUserMapper = jmdUserMapper;
    }

    @Override
    public JmdUserDTO save(JmdUserDTO jmdUserDTO) {
        log.debug("Request to save JmdUser : {}", jmdUserDTO);
        JmdUser jmdUser = jmdUserMapper.toEntity(jmdUserDTO);
        jmdUser = jmdUserRepository.save(jmdUser);
        return jmdUserMapper.toDto(jmdUser);
    }

    @Override
    public JmdUserDTO update(JmdUserDTO jmdUserDTO) {
        log.debug("Request to update JmdUser : {}", jmdUserDTO);
        JmdUser jmdUser = jmdUserMapper.toEntity(jmdUserDTO);
        jmdUser = jmdUserRepository.save(jmdUser);
        return jmdUserMapper.toDto(jmdUser);
    }

    @Override
    public Optional<JmdUserDTO> partialUpdate(JmdUserDTO jmdUserDTO) {
        log.debug("Request to partially update JmdUser : {}", jmdUserDTO);

        return jmdUserRepository
            .findById(jmdUserDTO.getId())
            .map(existingJmdUser -> {
                jmdUserMapper.partialUpdate(existingJmdUser, jmdUserDTO);

                return existingJmdUser;
            })
            .map(jmdUserRepository::save)
            .map(jmdUserMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<JmdUserDTO> findAll(Pageable pageable) {
        log.debug("Request to get all JmdUsers");
        return jmdUserRepository.findAll(pageable).map(jmdUserMapper::toDto);
    }

    public Page<JmdUserDTO> findAllWithEagerRelationships(Pageable pageable) {
        return jmdUserRepository.findAllWithEagerRelationships(pageable).map(jmdUserMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<JmdUserDTO> findOne(Long id) {
        log.debug("Request to get JmdUser : {}", id);
        return jmdUserRepository.findOneWithEagerRelationships(id).map(jmdUserMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete JmdUser : {}", id);
        jmdUserRepository.deleteById(id);
    }
}
