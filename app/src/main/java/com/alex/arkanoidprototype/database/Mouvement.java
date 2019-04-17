package com.alex.arkanoidprototype.database;

import com.orm.SugarRecord;

public class Mouvement extends SugarRecord {
    private long vitesseX;
    private long vitesseY;
    private long directionX;
    private long directionY;

    public Mouvement() {
    }

    public Mouvement(long vitesseX, long vitesseY, long directionX, long directionY) {
        this.vitesseX = vitesseX;
        this.vitesseY = vitesseY;
        this.directionX = directionX;
        this.directionY = directionY;
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
}
