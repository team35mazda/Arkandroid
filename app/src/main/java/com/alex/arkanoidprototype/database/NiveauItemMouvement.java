package com.alex.arkanoidprototype.database;

import com.orm.SugarRecord;

public class NiveauItemMouvement extends SugarRecord {

    private NiveauItem niveauItem;
    private Mouvement mouvement;

    public NiveauItemMouvement() {
    }

    public NiveauItemMouvement(NiveauItem niveauItem, Mouvement mouvement) {
        this.niveauItem = niveauItem;
        this.mouvement = mouvement;
    }

    public NiveauItem getNiveauItem() {
        return niveauItem;
    }

    public void setNiveauItem(NiveauItem niveauItem) {
        this.niveauItem = niveauItem;
    }

    public Mouvement getMouvement() {
        return mouvement;
    }

    public void setMouvement(Mouvement mouvement) {
        this.mouvement = mouvement;
    }
}
