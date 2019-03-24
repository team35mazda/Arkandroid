package com.alex.arkanoidprototype.database.datamodel;

public class NiveauItem {

    private long id;
    private long idNiveau;
    private long idItem;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIdNiveau() {
        return idNiveau;
    }

    public long getIdItem() {
        return idNiveau;
    }

    public void setNiveauItem(long idNiveau, long idItem) {
        this.idNiveau = idNiveau;
        this.idItem = idItem;
    }
}
