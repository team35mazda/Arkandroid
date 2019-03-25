package com.alex.arkanoidprototype.database.datamodel;

public class NiveauItemCurrentVisibility {
    private long id;
    private long idNiveauItem;
    private short isVisible;

    public void setId(long id) { this.id = id; }
    public void setIdNiveauItem(long idNiveauItem) { this.idNiveauItem = idNiveauItem; }
    public void setIsVisible(short isVisible) { this.isVisible = isVisible; }

    public long getId() {
        return id;
    }
    public long getIdNiveauItem() {
        return idNiveauItem;
    }
    public short getIsVisible() { return isVisible;}

}
