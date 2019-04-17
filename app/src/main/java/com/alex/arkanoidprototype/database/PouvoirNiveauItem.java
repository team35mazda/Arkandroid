package com.alex.arkanoidprototype.database;

import com.orm.SugarRecord;

public class PouvoirNiveauItem extends SugarRecord {
    private Pouvoir pouvoir;
    private NiveauItem niveauItem;
    private int puissance;

    public PouvoirNiveauItem() {
    }

    public PouvoirNiveauItem(Pouvoir pouvoir, NiveauItem niveauItem, int puissance) {
        this.pouvoir = pouvoir;
        this.niveauItem = niveauItem;
        this.puissance = puissance;
    }

    public Pouvoir getPouvoir() {
        return pouvoir;
    }

    public void setPouvoir(Pouvoir pouvoir) {
        this.pouvoir = pouvoir;
    }

    public NiveauItem getNiveauItem() {
        return niveauItem;
    }

    public void setNiveauItem(NiveauItem niveauItem) {
        this.niveauItem = niveauItem;
    }

    public int getPuissance() {
        return puissance;
    }

    public void setPuissance(int puissance) {
        this.puissance = puissance;
    }
}
