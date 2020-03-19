package com.ric.service;

import com.ric.domain.LigneOperation;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link LigneOperation}.
 */
public interface LigneOperationService {

    /**
     * Save a ligneOperation.
     *
     * @param ligneOperation the entity to save.
     * @return the persisted entity.
     */
    LigneOperation save(LigneOperation ligneOperation);

    /**
     * Get all the ligneOperations.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<LigneOperation> findAll(Pageable pageable);

    /**
     * Get the "id" ligneOperation.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<LigneOperation> findOne(Long id);

    /**
     * Delete the "id" ligneOperation.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
