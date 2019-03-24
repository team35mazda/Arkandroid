package com.alex.arkanoidprototype.database.datamodel;

public class DimensionBaseItem {
    private long id;
    private long idItem;
    private long idDimensionBase;

    public void setId(long id) { this.id = id; }
    public void setIdItem(long idItem) { this.idItem = idItem; }
    public void setIdDimensionBase(long idDimensionBase) { this.idDimensionBase = idDimensionBase; }

    public long getId() {
        return id;
    }
    public long getIdItem() {
        return idItem;
    }
    public long getIdDimensionBase() { return idDimensionBase;}
}
