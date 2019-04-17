package com.alex.arkanoidprototype.database;

import com.orm.SugarRecord;

public class SavedGame extends SugarRecord {
    private Game game; //reférence sur un game setting de base
    private Utilisateur utilisateur;
    private int nombreVieRestante;

    public SavedGame() {
    }

    public SavedGame(Game game, Utilisateur utilisateur, int nombreVieRestante) {
        this.game = game;
        this.utilisateur = utilisateur;
        this.nombreVieRestante = nombreVieRestante;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public int getNombreVieRestante() {
        return nombreVieRestante;
    }

    public void setNombreVieRestante(int nombreVieRestante) {
        this.nombreVieRestante = nombreVieRestante;
    }
}
