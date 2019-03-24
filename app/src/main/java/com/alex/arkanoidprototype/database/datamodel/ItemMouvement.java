package com.alex.arkanoidprototype.database.datamodel;

public class ItemMouvement {
    private long id;
    private long idItem;
    private long idMouvement;

    public void setId(long id) { this.id = id; }
    public void setIdItem(long idItem) { this.idItem = idItem; }
    public void setIdMouvement(long idMouvement) { this.idMouvement = idMouvement; }

    public long getId() {
        return id;
    }
    public long getIdItem() {
        return idItem;
    }
    public long getIdMouvement() { return idMouvement;}
}
