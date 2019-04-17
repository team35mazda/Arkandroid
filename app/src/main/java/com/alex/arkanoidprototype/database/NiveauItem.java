package com.alex.arkanoidprototype.database;

import com.orm.SugarRecord;

public class NiveauItem extends SugarRecord {

    private Niveau niveau;
    private Item item;
    private int isVisible;

    public NiveauItem() {
    }

    public NiveauItem(Niveau niveau, Item item, int isVisible) {
        this.niveau = niveau;
        this.item = item;
        this.isVisible = isVisible;
    }

    public Niveau getNiveau() {
        return niveau;
    }

    public void setNiveau(Niveau niveau) {
        this.niveau = niveau;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public int getIsVisible() {
        return isVisible;
    }

    public void setIsVisible(int isVisible) {
        this.isVisible = isVisible;
    }
}
