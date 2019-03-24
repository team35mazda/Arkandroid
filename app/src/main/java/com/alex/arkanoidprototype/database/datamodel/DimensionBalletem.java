package com.alex.arkanoidprototype.database.datamodel;

public class DimensionBalletem {
    private long id;
    private long idItem;
    private long idDimensionBalle;

    public void setId(long id) { this.id = id; }
    public void setIdItem(long idItem) { this.idItem = idItem; }
    public void setIdDimensionBalle(long idDimensionBalle) { this.idDimensionBalle = idDimensionBalle; }

    public long getId() {
        return id;
    }
    public long getIdItem() {
        return idItem;
    }
    public long getIdDimensionBalle() { return idDimensionBalle;}
}
