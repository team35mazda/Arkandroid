package com.alex.arkanoidprototype.database;

import com.orm.SugarRecord;

public class Game extends SugarRecord {
    private int nombreVie;

    public Game() {
    }

    public Game(int nombreVie) {
        this.nombreVie = nombreVie;
    }

    public int getNombreVie() {
        return nombreVie;
    }

    public void setNombreVie(int nombreVie) {
        this.nombreVie = nombreVie;
    }
}
