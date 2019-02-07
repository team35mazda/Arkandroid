package com.alex.arkanoidprototype.model;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;

public class Ball implements GameObject {

    private RectF oval;
    private int color;

    public Ball (Rect r , int color){
        oval = new RectF(r);
        this.color = color;

    }

    public RectF getOval() {
        return oval;
    }

    @Override
    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(color);

        canvas.drawOval(oval,paint);
    }

    @Override
    public void update() {

    }

    public void update(Point point){

        oval.set(point.x - oval.width()/2, point.y + oval.height()/2, point.x + oval.width()/2, point.y + oval.height()/2);
    }
}
