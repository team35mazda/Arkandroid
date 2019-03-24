package com.alex.arkanoidprototype.database.datamodel;

public class PositionItem {

    private long id;
    private long idPosition;
    private long idItem;

    public void setId(long id) { this.id = id; }
    public void setIdPosition(long idPosition) { this.idPosition = idPosition; }
    public void setIdItem(long idItem) { this.idItem = idItem; }

    public long getId() { return id; }
    public long getIdPosition() { return idPosition; }
    public long getIdItem() { return idItem; }
}
