package com.ric.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Employe.
 */
@Entity
@Table(name = "employe")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Employe implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "nom", nullable = false)
    private String nom;

    @Column(name = "prenom")
    private String prenom;

    @Column(name = "telephone")
    private Long telephone;

    @Column(name = "num_cni")
    private Long numCni;

    @OneToOne
    @JoinColumn(unique = true)
    private User user;

    @ManyToOne
    @JsonIgnoreProperties("employes")
    private Agence agence;

    @ManyToOne
    @JsonIgnoreProperties("employes")
    private Poste poste;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public Employe nom(String nom) {
        this.nom = nom;
        return this;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public Employe prenom(String prenom) {
        this.prenom = prenom;
        return this;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Long getTelephone() {
        return telephone;
    }

    public Employe telephone(Long telephone) {
        this.telephone = telephone;
        return this;
    }

    public void setTelephone(Long telephone) {
        this.telephone = telephone;
    }

    public Long getNumCni() {
        return numCni;
    }

    public Employe numCni(Long numCni) {
        this.numCni = numCni;
        return this;
    }

    public void setNumCni(Long numCni) {
        this.numCni = numCni;
    }

    public User getUser() {
        return user;
    }

    public Employe user(User user) {
        this.user = user;
        return this;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Agence getAgence() {
        return agence;
    }

    public Employe agence(Agence agence) {
        this.agence = agence;
        return this;
    }

    public void setAgence(Agence agence) {
        this.agence = agence;
    }

    public Poste getPoste() {
        return poste;
    }

    public Employe poste(Poste poste) {
        this.poste = poste;
        return this;
    }

    public void setPoste(Poste poste) {
        this.poste = poste;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Employe)) {
            return false;
        }
        return id != null && id.equals(((Employe) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Employe{" +
            "id=" + getId() +
            ", nom='" + getNom() + "'" +
            ", prenom='" + getPrenom() + "'" +
            ", telephone=" + getTelephone() +
            ", numCni=" + getNumCni() +
            "}";
    }
}
