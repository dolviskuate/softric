package com.ric.service.impl;

import com.ric.service.LigneOperationService;
import com.ric.domain.LigneOperation;
import com.ric.repository.LigneOperationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link LigneOperation}.
 */
@Service
@Transactional
public class LigneOperationServiceImpl implements LigneOperationService {

    private final Logger log = LoggerFactory.getLogger(LigneOperationServiceImpl.class);

    private final LigneOperationRepository ligneOperationRepository;

    public LigneOperationServiceImpl(LigneOperationRepository ligneOperationRepository) {
        this.ligneOperationRepository = ligneOperationRepository;
    }

    /**
     * Save a ligneOperation.
     *
     * @param ligneOperation the entity to save.
     * @return the persisted entity.
     */
    @Override
    public LigneOperation save(LigneOperation ligneOperation) {
        log.debug("Request to save LigneOperation : {}", ligneOperation);
        return ligneOperationRepository.save(ligneOperation);
    }

    /**
     * Get all the ligneOperations.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<LigneOperation> findAll(Pageable pageable) {
        log.debug("Request to get all LigneOperations");
        return ligneOperationRepository.findAll(pageable);
    }

    /**
     * Get one ligneOperation by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<LigneOperation> findOne(Long id) {
        log.debug("Request to get LigneOperation : {}", id);
        return ligneOperationRepository.findById(id);
    }

    /**
     * Delete the ligneOperation by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete LigneOperation : {}", id);
        ligneOperationRepository.deleteById(id);
    }
}
