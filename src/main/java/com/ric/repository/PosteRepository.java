package com.ric.repository;

import com.ric.domain.Poste;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Poste entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PosteRepository extends JpaRepository<Poste, Long> {
}
