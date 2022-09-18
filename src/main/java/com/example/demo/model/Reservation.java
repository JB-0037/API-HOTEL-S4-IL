package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long idChambreReservee;
    private String prenom;
    private String nom;
    private Statuts statut;
    private int dureeSejour;
    private int prix;

    public Reservation() {

    }

    public Reservation(String prenom, String nom, int dureeSejour, Long idChambreReservee, int prix) {
        this.prenom = prenom;
        this.nom = nom;
        this.dureeSejour = dureeSejour;
        this.statut = Statuts.EN_ATTENTE_DE_FINALISATION;
        this.idChambreReservee = idChambreReservee;
        this.prix = prix;
    }

    public Long getId() {
        return id;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Statuts getStatut() {
        return statut;
    }

    public void setStatut(Statuts statut) {
        this.statut = statut;
    }

    public int getDureeSejour() {
        return dureeSejour;
    }

    public void setDureeSejour(int dureeSejour) {
        this.dureeSejour = dureeSejour;
    }

    public Long getIdChambreReservee() {
        return idChambreReservee;
    }

    public void setIdChambreReservee(Long idChambreReservee) {
        this.idChambreReservee = idChambreReservee;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public int getPrix() {
        return prix;
    }
}
