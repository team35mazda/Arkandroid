package com.alex.arkanoidprototype.database;

import com.orm.SugarRecord;

public class SavedGameNiveauItemPouvoir extends SugarRecord {
    private SavedGameNiveauItem savedGameNiveauItem;
    private Pouvoir pouvoir;
    private Niveau niveauPouvoir;

    public SavedGameNiveauItemPouvoir() {
    }

    public SavedGameNiveauItemPouvoir(SavedGameNiveauItem savedGameNiveauItem, Pouvoir pouvoir, Niveau niveauPouvoir) {
        this.savedGameNiveauItem = savedGameNiveauItem;
        this.pouvoir = pouvoir;
        this.niveauPouvoir = niveauPouvoir;
    }

    public SavedGameNiveauItem getSavedGameNiveauItem() {
        return savedGameNiveauItem;
    }

    public void setSavedGameNiveauItem(SavedGameNiveauItem savedGameNiveauItem) {
        this.savedGameNiveauItem = savedGameNiveauItem;
    }

    public Pouvoir getPouvoir() {
        return pouvoir;
    }

    public void setPouvoir(Pouvoir pouvoir) {
        this.pouvoir = pouvoir;
    }

    public Niveau getNiveauPouvoir() {
        return niveauPouvoir;
    }

    public void setNiveauPouvoir(Niveau niveauPouvoir) {
        this.niveauPouvoir = niveauPouvoir;
    }
}
