package com.report.customreport.service.impl;

import com.report.customreport.domain.JmdSales;
import com.report.customreport.repository.JmdSalesRepository;
import com.report.customreport.service.JmdSalesService;
import com.report.customreport.service.dto.JmdSalesDTO;
import com.report.customreport.service.mapper.JmdSalesMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link JmdSales}.
 */
@Service
@Transactional
public class JmdSalesServiceImpl implements JmdSalesService {

    private final Logger log = LoggerFactory.getLogger(JmdSalesServiceImpl.class);

    private final JmdSalesRepository jmdSalesRepository;

    private final JmdSalesMapper jmdSalesMapper;

    public JmdSalesServiceImpl(JmdSalesRepository jmdSalesRepository, JmdSalesMapper jmdSalesMapper) {
        this.jmdSalesRepository = jmdSalesRepository;
        this.jmdSalesMapper = jmdSalesMapper;
    }

    @Override
    public JmdSalesDTO save(JmdSalesDTO jmdSalesDTO) {
        log.debug("Request to save JmdSales : {}", jmdSalesDTO);
        JmdSales jmdSales = jmdSalesMapper.toEntity(jmdSalesDTO);
        jmdSales = jmdSalesRepository.save(jmdSales);
        return jmdSalesMapper.toDto(jmdSales);
    }

    @Override
    public JmdSalesDTO update(JmdSalesDTO jmdSalesDTO) {
        log.debug("Request to update JmdSales : {}", jmdSalesDTO);
        JmdSales jmdSales = jmdSalesMapper.toEntity(jmdSalesDTO);
        jmdSales = jmdSalesRepository.save(jmdSales);
        return jmdSalesMapper.toDto(jmdSales);
    }

    @Override
    public Optional<JmdSalesDTO> partialUpdate(JmdSalesDTO jmdSalesDTO) {
        log.debug("Request to partially update JmdSales : {}", jmdSalesDTO);

        return jmdSalesRepository
            .findById(jmdSalesDTO.getId())
            .map(existingJmdSales -> {
                jmdSalesMapper.partialUpdate(existingJmdSales, jmdSalesDTO);

                return existingJmdSales;
            })
            .map(jmdSalesRepository::save)
            .map(jmdSalesMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<JmdSalesDTO> findAll(Pageable pageable) {
        log.debug("Request to get all JmdSales");
        return jmdSalesRepository.findAll(pageable).map(jmdSalesMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<JmdSalesDTO> findOne(Long id) {
        log.debug("Request to get JmdSales : {}", id);
        return jmdSalesRepository.findById(id).map(jmdSalesMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete JmdSales : {}", id);
        jmdSalesRepository.deleteById(id);
    }
}
