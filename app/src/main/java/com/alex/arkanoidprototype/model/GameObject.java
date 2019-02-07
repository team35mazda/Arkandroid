package com.alex.arkanoidprototype.model;

import android.graphics.Canvas;

public interface GameObject {
    public void draw(Canvas canvas);
    public void update();
}
