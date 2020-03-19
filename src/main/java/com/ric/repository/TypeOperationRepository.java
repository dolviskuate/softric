package com.ric.repository;

import com.ric.domain.TypeOperation;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the TypeOperation entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TypeOperationRepository extends JpaRepository<TypeOperation, Long> {
}
