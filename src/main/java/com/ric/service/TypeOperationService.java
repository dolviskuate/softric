package com.ric.service;

import com.ric.domain.TypeOperation;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link TypeOperation}.
 */
public interface TypeOperationService {

    /**
     * Save a typeOperation.
     *
     * @param typeOperation the entity to save.
     * @return the persisted entity.
     */
    TypeOperation save(TypeOperation typeOperation);

    /**
     * Get all the typeOperations.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<TypeOperation> findAll(Pageable pageable);

    /**
     * Get the "id" typeOperation.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<TypeOperation> findOne(Long id);

    /**
     * Delete the "id" typeOperation.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
