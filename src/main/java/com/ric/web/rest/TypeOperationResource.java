package com.ric.web.rest;

import com.ric.domain.TypeOperation;
import com.ric.service.TypeOperationService;
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
 * REST controller for managing {@link com.ric.domain.TypeOperation}.
 */
@RestController
@RequestMapping("/api")
public class TypeOperationResource {

    private final Logger log = LoggerFactory.getLogger(TypeOperationResource.class);

    private static final String ENTITY_NAME = "typeOperation";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TypeOperationService typeOperationService;

    public TypeOperationResource(TypeOperationService typeOperationService) {
        this.typeOperationService = typeOperationService;
    }

    /**
     * {@code POST  /type-operations} : Create a new typeOperation.
     *
     * @param typeOperation the typeOperation to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new typeOperation, or with status {@code 400 (Bad Request)} if the typeOperation has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/type-operations")
    public ResponseEntity<TypeOperation> createTypeOperation(@RequestBody TypeOperation typeOperation) throws URISyntaxException {
        log.debug("REST request to save TypeOperation : {}", typeOperation);
        if (typeOperation.getId() != null) {
            throw new BadRequestAlertException("A new typeOperation cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TypeOperation result = typeOperationService.save(typeOperation);
        return ResponseEntity.created(new URI("/api/type-operations/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /type-operations} : Updates an existing typeOperation.
     *
     * @param typeOperation the typeOperation to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated typeOperation,
     * or with status {@code 400 (Bad Request)} if the typeOperation is not valid,
     * or with status {@code 500 (Internal Server Error)} if the typeOperation couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/type-operations")
    public ResponseEntity<TypeOperation> updateTypeOperation(@RequestBody TypeOperation typeOperation) throws URISyntaxException {
        log.debug("REST request to update TypeOperation : {}", typeOperation);
        if (typeOperation.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TypeOperation result = typeOperationService.save(typeOperation);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, typeOperation.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /type-operations} : get all the typeOperations.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of typeOperations in body.
     */
    @GetMapping("/type-operations")
    public ResponseEntity<List<TypeOperation>> getAllTypeOperations(Pageable pageable) {
        log.debug("REST request to get a page of TypeOperations");
        Page<TypeOperation> page = typeOperationService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /type-operations/:id} : get the "id" typeOperation.
     *
     * @param id the id of the typeOperation to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the typeOperation, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/type-operations/{id}")
    public ResponseEntity<TypeOperation> getTypeOperation(@PathVariable Long id) {
        log.debug("REST request to get TypeOperation : {}", id);
        Optional<TypeOperation> typeOperation = typeOperationService.findOne(id);
        return ResponseUtil.wrapOrNotFound(typeOperation);
    }

    /**
     * {@code DELETE  /type-operations/:id} : delete the "id" typeOperation.
     *
     * @param id the id of the typeOperation to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/type-operations/{id}")
    public ResponseEntity<Void> deleteTypeOperation(@PathVariable Long id) {
        log.debug("REST request to delete TypeOperation : {}", id);
        typeOperationService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
