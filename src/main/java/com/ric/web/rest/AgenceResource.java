package com.ric.web.rest;

import com.ric.domain.Agence;
import com.ric.service.AgenceService;
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

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.ric.domain.Agence}.
 */
@RestController
@RequestMapping("/api")
public class AgenceResource {

    private final Logger log = LoggerFactory.getLogger(AgenceResource.class);

    private static final String ENTITY_NAME = "agence";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AgenceService agenceService;

    public AgenceResource(AgenceService agenceService) {
        this.agenceService = agenceService;
    }

    /**
     * {@code POST  /agences} : Create a new agence.
     *
     * @param agence the agence to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new agence, or with status {@code 400 (Bad Request)} if the agence has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/agences")
    public ResponseEntity<Agence> createAgence(@Valid @RequestBody Agence agence) throws URISyntaxException {
        log.debug("REST request to save Agence : {}", agence);
        if (agence.getId() != null) {
            throw new BadRequestAlertException("A new agence cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Agence result = agenceService.save(agence);
        return ResponseEntity.created(new URI("/api/agences/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /agences} : Updates an existing agence.
     *
     * @param agence the agence to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated agence,
     * or with status {@code 400 (Bad Request)} if the agence is not valid,
     * or with status {@code 500 (Internal Server Error)} if the agence couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/agences")
    public ResponseEntity<Agence> updateAgence(@Valid @RequestBody Agence agence) throws URISyntaxException {
        log.debug("REST request to update Agence : {}", agence);
        if (agence.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Agence result = agenceService.save(agence);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, agence.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /agences} : get all the agences.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of agences in body.
     */
    @GetMapping("/agences")
    public ResponseEntity<List<Agence>> getAllAgences(Pageable pageable) {
        log.debug("REST request to get a page of Agences");
        Page<Agence> page = agenceService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /agences/:id} : get the "id" agence.
     *
     * @param id the id of the agence to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the agence, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/agences/{id}")
    public ResponseEntity<Agence> getAgence(@PathVariable Long id) {
        log.debug("REST request to get Agence : {}", id);
        Optional<Agence> agence = agenceService.findOne(id);
        return ResponseUtil.wrapOrNotFound(agence);
    }

    /**
     * {@code DELETE  /agences/:id} : delete the "id" agence.
     *
     * @param id the id of the agence to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/agences/{id}")
    public ResponseEntity<Void> deleteAgence(@PathVariable Long id) {
        log.debug("REST request to delete Agence : {}", id);
        agenceService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
