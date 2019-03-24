package com.alex.arkanoidprototype.database.datamodel;

public class NiveauItemCurrentMouvement {
    private long id;
    private long vitesseX;
    private long vitesseY;
    private long directionX;
    private long directionY;

    public void setId(long id) { this.id = id; }
    public void setVitesseX(long vitesseX) { this.vitesseX = vitesseX; }
    public void setVitesseY(long vitesseY) { this.vitesseY = vitesseY; }
    public void setDirectionX(long directionX) { this.directionX = directionX; }
    public void setDirectionY(long directionY) { this.directionY = directionY; }

    public long getId() {
        return id;
    }
    public long getVitesseX() {
        return vitesseX;
    }
    public long getVitesseY() {
        return vitesseY;
    }
    public long getDirectionX() { return directionX; }
    public long getDirectionY() {
        return directionY;
    }
}
