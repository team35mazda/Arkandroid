package com.alex.arkanoidprototype.database.datamodel;

public class Item {
    private long id;
    private long idItemType;
    private long idPosition;
    private long idCouleur;
    private boolean isVisible;

    public void setId(long id) { this.id = id; }
    public void setIdItemType(long idItemType) { this.idItemType = idItemType; }
    public void setIdPosition(long idPosition) { this.idPosition = idPosition; }
    public void setIdCouleur(long idCouleur) { this.idCouleur = idCouleur; }
    public void setIsVisible(boolean isVisible) { this.isVisible = isVisible; }

    public long getId() {
        return id;
    }
    public long getIdItemType() {
        return idItemType;
    }
    public long getIdPosition() { return idPosition;}
    public long getIdCouleur() { return idCouleur;}
    public boolean getIsVisible() { return isVisible;}
}
