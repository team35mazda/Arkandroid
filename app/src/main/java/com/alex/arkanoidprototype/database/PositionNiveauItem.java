package com.alex.arkanoidprototype.database;

import com.orm.SugarRecord;

public class PositionNiveauItem extends SugarRecord {

    private Position position;
    private NiveauItem niveauItem;

    public PositionNiveauItem() {
    }

    public PositionNiveauItem(Position position, NiveauItem niveauItem) {
        this.position = position;
        this.niveauItem = niveauItem;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public NiveauItem getNiveauItem() {
        return niveauItem;
    }

    public void setNiveauItem(NiveauItem niveauItem) {
        this.niveauItem = niveauItem;
    }
}
