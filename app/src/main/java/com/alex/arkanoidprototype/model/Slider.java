package com.alex.arkanoidprototype.model;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

import static java.lang.Math.round;

public class Slider implements GameObject {

    private Rect rectangle;
    private int color;
    private int startPosition = 1200;

    public Slider(Rect rectangle, int color){
        this.rectangle = rectangle;
        this.color = color;
    }

    public Rect getRect() {
        return rectangle;
    }

    @Override
    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(color);
        canvas.drawRect(rectangle, paint);

        startPosition = (int)round(canvas.getHeight()*0.9);
    }

    @Override
    public void update() {

    }

    public void update(Point point){

        rectangle.set(point.x - rectangle.width()/2, startPosition, point.x + rectangle.width()/2, startPosition + rectangle.width()/3);

        //rectangle.set(point.x - rectangle.width()/2, point.y - rectangle.height()/2, point.x + rectangle.width()/2, point.y + rectangle.height()/2);
    }
}
