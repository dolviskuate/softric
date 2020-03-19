package com.ric.service;

import com.ric.domain.Categorie;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link Categorie}.
 */
public interface CategorieService {

    /**
     * Save a categorie.
     *
     * @param categorie the entity to save.
     * @return the persisted entity.
     */
    Categorie save(Categorie categorie);

    /**
     * Get all the categories.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<Categorie> findAll(Pageable pageable);

    /**
     * Get the "id" categorie.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Categorie> findOne(Long id);

    /**
     * Delete the "id" categorie.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
