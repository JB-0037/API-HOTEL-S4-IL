package com.example.demo.model;

public class ReservationRequestBody {

    private String prenom;
    private String nom;
    private int dureeSejour;

    public ReservationRequestBody() {

    }

    public ReservationRequestBody(String prenom, String nom, int dureeSejour) {
        this.prenom = prenom;
        this.nom = nom;
        this.dureeSejour = dureeSejour;
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

    public int getDureeSejour() {
        return dureeSejour;
    }

    public void setDureeSejour(int dureeSejour) {
        this.dureeSejour = dureeSejour;
    }
}
