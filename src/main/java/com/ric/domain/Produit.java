package com.ric.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Produit.
 */
@Entity
@Table(name = "produit")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Produit implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "designation", nullable = false)
    private String designation;

    @Column(name = "quantite_stock")
    private Long quantiteStock;

    @Column(name = "quantite_minimale")
    private Long quantiteMinimale;

    @ManyToOne
    @JsonIgnoreProperties("produits")
    private Categorie categorie;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDesignation() {
        return designation;
    }

    public Produit designation(String designation) {
        this.designation = designation;
        return this;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public Long getQuantiteStock() {
        return quantiteStock;
    }

    public Produit quantiteStock(Long quantiteStock) {
        this.quantiteStock = quantiteStock;
        return this;
    }

    public void setQuantiteStock(Long quantiteStock) {
        this.quantiteStock = quantiteStock;
    }

    public Long getQuantiteMinimale() {
        return quantiteMinimale;
    }

    public Produit quantiteMinimale(Long quantiteMinimale) {
        this.quantiteMinimale = quantiteMinimale;
        return this;
    }

    public void setQuantiteMinimale(Long quantiteMinimale) {
        this.quantiteMinimale = quantiteMinimale;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public Produit categorie(Categorie categorie) {
        this.categorie = categorie;
        return this;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Produit)) {
            return false;
        }
        return id != null && id.equals(((Produit) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Produit{" +
            "id=" + getId() +
            ", designation='" + getDesignation() + "'" +
            ", quantiteStock=" + getQuantiteStock() +
            ", quantiteMinimale=" + getQuantiteMinimale() +
            "}";
    }
}
