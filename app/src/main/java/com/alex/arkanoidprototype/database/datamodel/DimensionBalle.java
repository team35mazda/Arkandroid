package com.alex.arkanoidprototype.database.datamodel;

public class DimensionBalle {
    private long id;
    private long rayon;

    public void setId(long id) { this.id = id; }
    public void setRayon(long rayon) { this.rayon = rayon; }

    public long getId() {
        return id;
    }
    public long getRayon() {
        return rayon;
    }

}
