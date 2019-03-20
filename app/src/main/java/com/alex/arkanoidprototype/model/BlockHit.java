package com.alex.arkanoidprototype.model;

public class BlockHit {
    private boolean HitByX = false;
    private boolean HitByY = false;

    private boolean HitByTop = false;
    private boolean HitByBottom = false;
    private boolean HitByLeft = false;
    private boolean HitByRigth = false;

    public BlockHit(){    }

    public boolean getHit() {
        return getHitByX() || getHitByY();
    }

    public boolean getHitByX() {
        return HitByX;
    }
    public boolean getHitByY() {
        return HitByY;
    }

    public boolean getHitByTop() {
        return HitByTop;
    }
    public boolean getHitByBottom() {
        return HitByBottom;
    }
    public boolean getHitByLeft() {
        return HitByLeft;
    }
    public boolean getHitByRigth() {
        return HitByRigth;
    }

    public void setHitByTop(boolean val) {
        HitByTop = val;
        HitByY = val;
    }
    public void setHitByBottom(boolean val) {
        HitByBottom = val;
        HitByY = val;
    }
    public void setHitByLeft(boolean val) {
        HitByLeft = val;
        HitByX = val;
    }
    public void setHitByRigth(boolean val) {
        HitByRigth = val;
        HitByX = val;
    }

}
