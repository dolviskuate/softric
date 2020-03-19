package com.ric.web.rest;

import com.ric.SoftricApp;
import com.ric.domain.LigneOperation;
import com.ric.repository.LigneOperationRepository;
import com.ric.service.LigneOperationService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link LigneOperationResource} REST controller.
 */
@SpringBootTest(classes = SoftricApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class LigneOperationResourceIT {

    private static final String DEFAULT_QUANTITE = "AAAAAAAAAA";
    private static final String UPDATED_QUANTITE = "BBBBBBBBBB";

    @Autowired
    private LigneOperationRepository ligneOperationRepository;

    @Autowired
    private LigneOperationService ligneOperationService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restLigneOperationMockMvc;

    private LigneOperation ligneOperation;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static LigneOperation createEntity(EntityManager em) {
        LigneOperation ligneOperation = new LigneOperation()
            .quantite(DEFAULT_QUANTITE);
        return ligneOperation;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static LigneOperation createUpdatedEntity(EntityManager em) {
        LigneOperation ligneOperation = new LigneOperation()
            .quantite(UPDATED_QUANTITE);
        return ligneOperation;
    }

    @BeforeEach
    public void initTest() {
        ligneOperation = createEntity(em);
    }

    @Test
    @Transactional
    public void createLigneOperation() throws Exception {
        int databaseSizeBeforeCreate = ligneOperationRepository.findAll().size();

        // Create the LigneOperation
        restLigneOperationMockMvc.perform(post("/api/ligne-operations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ligneOperation)))
            .andExpect(status().isCreated());

        // Validate the LigneOperation in the database
        List<LigneOperation> ligneOperationList = ligneOperationRepository.findAll();
        assertThat(ligneOperationList).hasSize(databaseSizeBeforeCreate + 1);
        LigneOperation testLigneOperation = ligneOperationList.get(ligneOperationList.size() - 1);
        assertThat(testLigneOperation.getQuantite()).isEqualTo(DEFAULT_QUANTITE);
    }

    @Test
    @Transactional
    public void createLigneOperationWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = ligneOperationRepository.findAll().size();

        // Create the LigneOperation with an existing ID
        ligneOperation.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restLigneOperationMockMvc.perform(post("/api/ligne-operations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ligneOperation)))
            .andExpect(status().isBadRequest());

        // Validate the LigneOperation in the database
        List<LigneOperation> ligneOperationList = ligneOperationRepository.findAll();
        assertThat(ligneOperationList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllLigneOperations() throws Exception {
        // Initialize the database
        ligneOperationRepository.saveAndFlush(ligneOperation);

        // Get all the ligneOperationList
        restLigneOperationMockMvc.perform(get("/api/ligne-operations?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(ligneOperation.getId().intValue())))
            .andExpect(jsonPath("$.[*].quantite").value(hasItem(DEFAULT_QUANTITE)));
    }
    
    @Test
    @Transactional
    public void getLigneOperation() throws Exception {
        // Initialize the database
        ligneOperationRepository.saveAndFlush(ligneOperation);

        // Get the ligneOperation
        restLigneOperationMockMvc.perform(get("/api/ligne-operations/{id}", ligneOperation.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(ligneOperation.getId().intValue()))
            .andExpect(jsonPath("$.quantite").value(DEFAULT_QUANTITE));
    }

    @Test
    @Transactional
    public void getNonExistingLigneOperation() throws Exception {
        // Get the ligneOperation
        restLigneOperationMockMvc.perform(get("/api/ligne-operations/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateLigneOperation() throws Exception {
        // Initialize the database
        ligneOperationService.save(ligneOperation);

        int databaseSizeBeforeUpdate = ligneOperationRepository.findAll().size();

        // Update the ligneOperation
        LigneOperation updatedLigneOperation = ligneOperationRepository.findById(ligneOperation.getId()).get();
        // Disconnect from session so that the updates on updatedLigneOperation are not directly saved in db
        em.detach(updatedLigneOperation);
        updatedLigneOperation
            .quantite(UPDATED_QUANTITE);

        restLigneOperationMockMvc.perform(put("/api/ligne-operations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedLigneOperation)))
            .andExpect(status().isOk());

        // Validate the LigneOperation in the database
        List<LigneOperation> ligneOperationList = ligneOperationRepository.findAll();
        assertThat(ligneOperationList).hasSize(databaseSizeBeforeUpdate);
        LigneOperation testLigneOperation = ligneOperationList.get(ligneOperationList.size() - 1);
        assertThat(testLigneOperation.getQuantite()).isEqualTo(UPDATED_QUANTITE);
    }

    @Test
    @Transactional
    public void updateNonExistingLigneOperation() throws Exception {
        int databaseSizeBeforeUpdate = ligneOperationRepository.findAll().size();

        // Create the LigneOperation

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restLigneOperationMockMvc.perform(put("/api/ligne-operations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ligneOperation)))
            .andExpect(status().isBadRequest());

        // Validate the LigneOperation in the database
        List<LigneOperation> ligneOperationList = ligneOperationRepository.findAll();
        assertThat(ligneOperationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteLigneOperation() throws Exception {
        // Initialize the database
        ligneOperationService.save(ligneOperation);

        int databaseSizeBeforeDelete = ligneOperationRepository.findAll().size();

        // Delete the ligneOperation
        restLigneOperationMockMvc.perform(delete("/api/ligne-operations/{id}", ligneOperation.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<LigneOperation> ligneOperationList = ligneOperationRepository.findAll();
        assertThat(ligneOperationList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
