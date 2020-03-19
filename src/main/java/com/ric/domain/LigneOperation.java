package com.ric.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A LigneOperation.
 */
@Entity
@Table(name = "ligne_operation")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class LigneOperation implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "quantite")
    private String quantite;

    @ManyToOne
    @JsonIgnoreProperties("ligneOperations")
    private Operation operation;

    @ManyToOne
    @JsonIgnoreProperties("ligneOperations")
    private Produit produit;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuantite() {
        return quantite;
    }

    public LigneOperation quantite(String quantite) {
        this.quantite = quantite;
        return this;
    }

    public void setQuantite(String quantite) {
        this.quantite = quantite;
    }

    public Operation getOperation() {
        return operation;
    }

    public LigneOperation operation(Operation operation) {
        this.operation = operation;
        return this;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    public Produit getProduit() {
        return produit;
    }

    public LigneOperation produit(Produit produit) {
        this.produit = produit;
        return this;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof LigneOperation)) {
            return false;
        }
        return id != null && id.equals(((LigneOperation) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "LigneOperation{" +
            "id=" + getId() +
            ", quantite='" + getQuantite() + "'" +
            "}";
    }
}
