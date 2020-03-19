package com.ric.repository;

import com.ric.domain.LigneOperation;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the LigneOperation entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LigneOperationRepository extends JpaRepository<LigneOperation, Long> {
}
