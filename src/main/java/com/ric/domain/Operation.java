package com.ric.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;
import java.time.LocalDate;

/**
 * A Operation.
 */
@Entity
@Table(name = "operation")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Operation implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "date_operation", nullable = false)
    private LocalDate dateOperation;

    @Column(name = "montant_total")
    private Long montantTotal;

    @ManyToOne
    @JsonIgnoreProperties("operations")
    private Client client;

    @ManyToOne
    @JsonIgnoreProperties("operations")
    private TypeOperation typeOperation;

    @ManyToOne
    @JsonIgnoreProperties("operations")
    private Employe employe;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDateOperation() {
        return dateOperation;
    }

    public Operation dateOperation(LocalDate dateOperation) {
        this.dateOperation = dateOperation;
        return this;
    }

    public void setDateOperation(LocalDate dateOperation) {
        this.dateOperation = dateOperation;
    }

    public Long getMontantTotal() {
        return montantTotal;
    }

    public Operation montantTotal(Long montantTotal) {
        this.montantTotal = montantTotal;
        return this;
    }

    public void setMontantTotal(Long montantTotal) {
        this.montantTotal = montantTotal;
    }

    public Client getClient() {
        return client;
    }

    public Operation client(Client client) {
        this.client = client;
        return this;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public TypeOperation getTypeOperation() {
        return typeOperation;
    }

    public Operation typeOperation(TypeOperation typeOperation) {
        this.typeOperation = typeOperation;
        return this;
    }

    public void setTypeOperation(TypeOperation typeOperation) {
        this.typeOperation = typeOperation;
    }

    public Employe getEmploye() {
        return employe;
    }

    public Operation employe(Employe employe) {
        this.employe = employe;
        return this;
    }

    public void setEmploye(Employe employe) {
        this.employe = employe;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Operation)) {
            return false;
        }
        return id != null && id.equals(((Operation) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Operation{" +
            "id=" + getId() +
            ", dateOperation='" + getDateOperation() + "'" +
            ", montantTotal=" + getMontantTotal() +
            "}";
    }
}
