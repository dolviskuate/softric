package com.ric.web.rest;

import com.ric.SoftricApp;
import com.ric.domain.TypeOperation;
import com.ric.repository.TypeOperationRepository;
import com.ric.service.TypeOperationService;

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
 * Integration tests for the {@link TypeOperationResource} REST controller.
 */
@SpringBootTest(classes = SoftricApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class TypeOperationResourceIT {

    private static final Long DEFAULT_LIBELLE = 1L;
    private static final Long UPDATED_LIBELLE = 2L;

    @Autowired
    private TypeOperationRepository typeOperationRepository;

    @Autowired
    private TypeOperationService typeOperationService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTypeOperationMockMvc;

    private TypeOperation typeOperation;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TypeOperation createEntity(EntityManager em) {
        TypeOperation typeOperation = new TypeOperation()
            .libelle(DEFAULT_LIBELLE);
        return typeOperation;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TypeOperation createUpdatedEntity(EntityManager em) {
        TypeOperation typeOperation = new TypeOperation()
            .libelle(UPDATED_LIBELLE);
        return typeOperation;
    }

    @BeforeEach
    public void initTest() {
        typeOperation = createEntity(em);
    }

    @Test
    @Transactional
    public void createTypeOperation() throws Exception {
        int databaseSizeBeforeCreate = typeOperationRepository.findAll().size();

        // Create the TypeOperation
        restTypeOperationMockMvc.perform(post("/api/type-operations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(typeOperation)))
            .andExpect(status().isCreated());

        // Validate the TypeOperation in the database
        List<TypeOperation> typeOperationList = typeOperationRepository.findAll();
        assertThat(typeOperationList).hasSize(databaseSizeBeforeCreate + 1);
        TypeOperation testTypeOperation = typeOperationList.get(typeOperationList.size() - 1);
        assertThat(testTypeOperation.getLibelle()).isEqualTo(DEFAULT_LIBELLE);
    }

    @Test
    @Transactional
    public void createTypeOperationWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = typeOperationRepository.findAll().size();

        // Create the TypeOperation with an existing ID
        typeOperation.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTypeOperationMockMvc.perform(post("/api/type-operations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(typeOperation)))
            .andExpect(status().isBadRequest());

        // Validate the TypeOperation in the database
        List<TypeOperation> typeOperationList = typeOperationRepository.findAll();
        assertThat(typeOperationList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllTypeOperations() throws Exception {
        // Initialize the database
        typeOperationRepository.saveAndFlush(typeOperation);

        // Get all the typeOperationList
        restTypeOperationMockMvc.perform(get("/api/type-operations?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(typeOperation.getId().intValue())))
            .andExpect(jsonPath("$.[*].libelle").value(hasItem(DEFAULT_LIBELLE.intValue())));
    }
    
    @Test
    @Transactional
    public void getTypeOperation() throws Exception {
        // Initialize the database
        typeOperationRepository.saveAndFlush(typeOperation);

        // Get the typeOperation
        restTypeOperationMockMvc.perform(get("/api/type-operations/{id}", typeOperation.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(typeOperation.getId().intValue()))
            .andExpect(jsonPath("$.libelle").value(DEFAULT_LIBELLE.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingTypeOperation() throws Exception {
        // Get the typeOperation
        restTypeOperationMockMvc.perform(get("/api/type-operations/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTypeOperation() throws Exception {
        // Initialize the database
        typeOperationService.save(typeOperation);

        int databaseSizeBeforeUpdate = typeOperationRepository.findAll().size();

        // Update the typeOperation
        TypeOperation updatedTypeOperation = typeOperationRepository.findById(typeOperation.getId()).get();
        // Disconnect from session so that the updates on updatedTypeOperation are not directly saved in db
        em.detach(updatedTypeOperation);
        updatedTypeOperation
            .libelle(UPDATED_LIBELLE);

        restTypeOperationMockMvc.perform(put("/api/type-operations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedTypeOperation)))
            .andExpect(status().isOk());

        // Validate the TypeOperation in the database
        List<TypeOperation> typeOperationList = typeOperationRepository.findAll();
        assertThat(typeOperationList).hasSize(databaseSizeBeforeUpdate);
        TypeOperation testTypeOperation = typeOperationList.get(typeOperationList.size() - 1);
        assertThat(testTypeOperation.getLibelle()).isEqualTo(UPDATED_LIBELLE);
    }

    @Test
    @Transactional
    public void updateNonExistingTypeOperation() throws Exception {
        int databaseSizeBeforeUpdate = typeOperationRepository.findAll().size();

        // Create the TypeOperation

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTypeOperationMockMvc.perform(put("/api/type-operations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(typeOperation)))
            .andExpect(status().isBadRequest());

        // Validate the TypeOperation in the database
        List<TypeOperation> typeOperationList = typeOperationRepository.findAll();
        assertThat(typeOperationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTypeOperation() throws Exception {
        // Initialize the database
        typeOperationService.save(typeOperation);

        int databaseSizeBeforeDelete = typeOperationRepository.findAll().size();

        // Delete the typeOperation
        restTypeOperationMockMvc.perform(delete("/api/type-operations/{id}", typeOperation.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TypeOperation> typeOperationList = typeOperationRepository.findAll();
        assertThat(typeOperationList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
