package com.alex.arkanoidprototype.database.datamodel;

public class NiveauItem {

    private long id;
    private long idNiveau;
    private long idItem;

    public void setId(long id) { this.id = id; }
    public void setIdNiveau(long idNiveau) { this.idNiveau = idNiveau; }
    public void setIdItem(long idItem) { this.idItem = idItem; }

    public long getId() { return id; }
    public long getIdNiveau() { return idNiveau; }
    public long getIdItem() { return idItem; }
}
