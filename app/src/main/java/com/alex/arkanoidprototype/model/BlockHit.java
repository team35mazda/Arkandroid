package com.alex.arkanoidprototype.model;

public class BlockHit {
    private boolean HitByX = false;
    private boolean HitByY = false;

    public boolean HitByTop = false;
    public boolean HitByBottom = false;
    public boolean HitByLeft = false;
    public boolean HitByRigth = false;

    public BlockHit(){

    }

    public boolean getHit() {
        return getHitByX() || getHitByY();
    }

    public boolean getHitByX() {
        return HitByX;
    }

    public boolean getHitByY() {
        return HitByY;
    }

    public void setHitByX(boolean val) {
        HitByX = val;
    }

    public void setHitByY(boolean val) {
        HitByY = val;
    }
}
