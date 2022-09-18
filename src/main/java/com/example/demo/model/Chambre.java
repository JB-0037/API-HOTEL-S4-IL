package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Chambre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String type;
    private int prix;
    private int nbrePersonnes;
    private Statuts statut;

    public Chambre() {

    }

    public Chambre(String type, int prix, int nbrePersonnes) {
        this.type = type;
        this.prix = prix;
        this.nbrePersonnes = nbrePersonnes;
        this.statut = Statuts.DISPONIBLE;

    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public int getNbrePersonnes() {
        return nbrePersonnes;
    }

    public void setNbrePersonnes(int nbrePersonnes) {
        this.nbrePersonnes = nbrePersonnes;
    }

    public Long getId() {
        return id;
    }

    public Statuts getStatut() {
        return statut;
    }

    public void setStatut(Statuts statut) {
        this.statut = statut;
    }
}
