package com.alex.arkanoidprototype.database.datamodel;

public class DimensionBase {
    private long id;
    private long largeur;
    private long longeur;

    public void setId(long id) { this.id = id; }
    public void setLargeur(long largeur) { this.largeur = largeur; }
    public void setLongeur(long longeur) { this.longeur = longeur; }

    public long getId() {
        return id;
    }
    public long setLargeur() { return largeur; }
    public long setLongeur() { return longeur;}
}
