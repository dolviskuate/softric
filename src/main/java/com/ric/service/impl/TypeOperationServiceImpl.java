package com.ric.service.impl;

import com.ric.service.TypeOperationService;
import com.ric.domain.TypeOperation;
import com.ric.repository.TypeOperationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link TypeOperation}.
 */
@Service
@Transactional
public class TypeOperationServiceImpl implements TypeOperationService {

    private final Logger log = LoggerFactory.getLogger(TypeOperationServiceImpl.class);

    private final TypeOperationRepository typeOperationRepository;

    public TypeOperationServiceImpl(TypeOperationRepository typeOperationRepository) {
        this.typeOperationRepository = typeOperationRepository;
    }

    /**
     * Save a typeOperation.
     *
     * @param typeOperation the entity to save.
     * @return the persisted entity.
     */
    @Override
    public TypeOperation save(TypeOperation typeOperation) {
        log.debug("Request to save TypeOperation : {}", typeOperation);
        return typeOperationRepository.save(typeOperation);
    }

    /**
     * Get all the typeOperations.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<TypeOperation> findAll(Pageable pageable) {
        log.debug("Request to get all TypeOperations");
        return typeOperationRepository.findAll(pageable);
    }

    /**
     * Get one typeOperation by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<TypeOperation> findOne(Long id) {
        log.debug("Request to get TypeOperation : {}", id);
        return typeOperationRepository.findById(id);
    }

    /**
     * Delete the typeOperation by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete TypeOperation : {}", id);
        typeOperationRepository.deleteById(id);
    }
}
