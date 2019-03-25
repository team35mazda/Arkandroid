package com.alex.arkanoidprototype.database.datamodel;

public class NiveauItemCurrentPosition {
    private long id;
    private long idNiveauItem;
    private long positionX;
    private long positionY;

    public void setId(long id) { this.id = id; }
    public void setIdNiveauItem(long idNiveauItem) { this.idNiveauItem = idNiveauItem; }
    public void setPositionX(long positionX) { this.positionX = positionX; }
    public void setPositionY(long positionY) { this.positionY = positionY; }

    public long getId() {
        return id;
    }
    public long getIdNiveauItem() {
        return idNiveauItem;
    }
    public long getPositionX() {
        return positionX;
    }
    public long getPositionY() {
        return positionY;
    }
 }
