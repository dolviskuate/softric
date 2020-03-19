package com.ric.web.rest;

import com.ric.domain.LigneOperation;
import com.ric.service.LigneOperationService;
import com.ric.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.ric.domain.LigneOperation}.
 */
@RestController
@RequestMapping("/api")
public class LigneOperationResource {

    private final Logger log = LoggerFactory.getLogger(LigneOperationResource.class);

    private static final String ENTITY_NAME = "ligneOperation";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final LigneOperationService ligneOperationService;

    public LigneOperationResource(LigneOperationService ligneOperationService) {
        this.ligneOperationService = ligneOperationService;
    }

    /**
     * {@code POST  /ligne-operations} : Create a new ligneOperation.
     *
     * @param ligneOperation the ligneOperation to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new ligneOperation, or with status {@code 400 (Bad Request)} if the ligneOperation has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/ligne-operations")
    public ResponseEntity<LigneOperation> createLigneOperation(@RequestBody LigneOperation ligneOperation) throws URISyntaxException {
        log.debug("REST request to save LigneOperation : {}", ligneOperation);
        if (ligneOperation.getId() != null) {
            throw new BadRequestAlertException("A new ligneOperation cannot already have an ID", ENTITY_NAME, "idexists");
        }
        LigneOperation result = ligneOperationService.save(ligneOperation);
        return ResponseEntity.created(new URI("/api/ligne-operations/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /ligne-operations} : Updates an existing ligneOperation.
     *
     * @param ligneOperation the ligneOperation to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated ligneOperation,
     * or with status {@code 400 (Bad Request)} if the ligneOperation is not valid,
     * or with status {@code 500 (Internal Server Error)} if the ligneOperation couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/ligne-operations")
    public ResponseEntity<LigneOperation> updateLigneOperation(@RequestBody LigneOperation ligneOperation) throws URISyntaxException {
        log.debug("REST request to update LigneOperation : {}", ligneOperation);
        if (ligneOperation.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        LigneOperation result = ligneOperationService.save(ligneOperation);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, ligneOperation.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /ligne-operations} : get all the ligneOperations.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of ligneOperations in body.
     */
    @GetMapping("/ligne-operations")
    public ResponseEntity<List<LigneOperation>> getAllLigneOperations(Pageable pageable) {
        log.debug("REST request to get a page of LigneOperations");
        Page<LigneOperation> page = ligneOperationService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /ligne-operations/:id} : get the "id" ligneOperation.
     *
     * @param id the id of the ligneOperation to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the ligneOperation, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/ligne-operations/{id}")
    public ResponseEntity<LigneOperation> getLigneOperation(@PathVariable Long id) {
        log.debug("REST request to get LigneOperation : {}", id);
        Optional<LigneOperation> ligneOperation = ligneOperationService.findOne(id);
        return ResponseUtil.wrapOrNotFound(ligneOperation);
    }

    /**
     * {@code DELETE  /ligne-operations/:id} : delete the "id" ligneOperation.
     *
     * @param id the id of the ligneOperation to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/ligne-operations/{id}")
    public ResponseEntity<Void> deleteLigneOperation(@PathVariable Long id) {
        log.debug("REST request to delete LigneOperation : {}", id);
        ligneOperationService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
