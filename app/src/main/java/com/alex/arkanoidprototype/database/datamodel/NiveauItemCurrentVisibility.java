package com.alex.arkanoidprototype.database.datamodel;

public class NiveauItemCurrentVisibility {
    private long id;
    private short isVisible;

    public void setId(long id) { this.id = id; }
    public void setIsVisible(short isVisible) { this.isVisible = isVisible; }

    public long getId() {
        return id;
    }
    public short getIsVisible() { return isVisible;}
}
