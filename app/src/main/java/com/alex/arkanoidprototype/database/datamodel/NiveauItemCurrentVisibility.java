package com.alex.arkanoidprototype.database.datamodel;

public class NiveauItemCurrentVisibility {
    private long id;
    private boolean isVisible;

    public void setId(long id) { this.id = id; }
    public void setIsVisible(boolean isVisible) { this.isVisible = isVisible; }

    public long getId() {
        return id;
    }
    public boolean getIsVisible() { return isVisible;}
}
