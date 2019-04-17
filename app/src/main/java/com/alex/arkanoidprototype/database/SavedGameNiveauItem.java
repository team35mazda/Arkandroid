package com.alex.arkanoidprototype.database;

import com.orm.SugarRecord;

public class SavedGameNiveauItem extends SugarRecord {

    private SavedGame savedGame;
    private NiveauItem niveauItem;
    private long vitesseX;
    private long vitesseY;
    private long directionX;
    private long directionY;
    private short isVisible;
    private long coordonneX;
    private long coordonneY;

    public SavedGameNiveauItem(){};

    public SavedGameNiveauItem(SavedGame savedGame, NiveauItem niveauItem, long vitesseX, long vitesseY, long directionX, long directionY, short isVisible, long coordonneX, long coordonneY) {
        this.savedGame = savedGame;
        this.niveauItem = niveauItem;
        this.vitesseX = vitesseX;
        this.vitesseY = vitesseY;
        this.directionX = directionX;
        this.directionY = directionY;
        this.isVisible = isVisible;
        this.coordonneX = coordonneX;
        this.coordonneY = coordonneY;
    }

    public SavedGameNiveauItem(SavedGame savedGame, NiveauItem niveauItem, short isVisible) {
        this.savedGame = savedGame;
        this.niveauItem = niveauItem;
        this.isVisible = isVisible;
    }

    public SavedGame getSavedGame() {
        return savedGame;
    }

    public void setSavedGame(SavedGame savedGame) {
        this.savedGame = savedGame;
    }

    public NiveauItem getNiveauItem() {
        return niveauItem;
    }

    public void setNiveauItem(NiveauItem niveauItem) {
        this.niveauItem = niveauItem;
    }

    public long getVitesseX() {
        return vitesseX;
    }

    public void setVitesseX(long vitesseX) {
        this.vitesseX = vitesseX;
    }

    public long getVitesseY() {
        return vitesseY;
    }

    public void setVitesseY(long vitesseY) {
        this.vitesseY = vitesseY;
    }

    public long getDirectionX() {
        return directionX;
    }

    public void setDirectionX(long directionX) {
        this.directionX = directionX;
    }

    public long getDirectionY() {
        return directionY;
    }

    public void setDirectionY(long directionY) {
        this.directionY = directionY;
    }

    public short getIsVisible() {
        return isVisible;
    }

    public void setIsVisible(short isVisible) {
        this.isVisible = isVisible;
    }

    public long getCoordonneX() {
        return coordonneX;
    }

    public void setCoordonneX(long coordonneX) {
        this.coordonneX = coordonneX;
    }

    public long getCoordonneY() {
        return coordonneY;
    }

    public void setCoordonneY(long coordonneY) {
        this.coordonneY = coordonneY;
    }
}
