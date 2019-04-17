package com.alex.arkanoidprototype.database;

import com.orm.SugarRecord;

public class Utilisateur extends SugarRecord {
    private String codeUsager;
    private String nom;
    private String prenom;

    public Utilisateur() {
    }

    public Utilisateur(String codeUsager, String nom, String prenom) {
        this.codeUsager = codeUsager;
        this.nom = nom;
        this.prenom = prenom;
    }

    public String getCodeUsager() {
        return codeUsager;
    }

    public void setCodeUsager(String codeUsager) {
        this.codeUsager = codeUsager;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
}
