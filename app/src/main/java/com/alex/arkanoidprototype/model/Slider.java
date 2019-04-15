package com.alex.arkanoidprototype.model;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

import static java.lang.Math.round;

public class Slider implements GameObject {

    public static int defautPointX = 500;
    public static int defautPointY = 1200;

    private Rect rectangle;
    private int color;
    private int startPosition;
    private Point sliderPoint;

    public Slider(){
        this.rectangle = new Rect(0,0,200,75);
        this.color = Color.rgb(255,0,0);
        this.sliderPoint = new Point(defautPointX, defautPointY );
    }

    public Slider(Rect rectangle, int color){
        this.rectangle = rectangle;
        this.color = color;
        this.sliderPoint = new Point(defautPointX, defautPointY );
    }

    public Rect getRect() {
        return rectangle;
    }

    public Point getpoint() {
        return sliderPoint;
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

    // Charger le default de la base de données
    private void loadNew() {

    }

    // Charger la valeur courante de la base de données
    private void LoadState() {

    }

    // Sauvegarder dans la base de données
    private void SaveState(){

    }
}
