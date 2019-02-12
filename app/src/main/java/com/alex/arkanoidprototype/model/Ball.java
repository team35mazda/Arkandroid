package com.alex.arkanoidprototype.model;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Color;
import com.alex.arkanoidprototype.model.Slider;


public class Ball implements GameObject {
    public static int rayon = 30;
    private static int MouvementX = 3;
    private static int MouvementY = 3;

    private Point cercle;
    private Paint paint;
    private boolean directionX;
    private boolean directionY;

    public Ball (Point p , int color){
        cercle = new Point(p.x, p.y);
        paint = new Paint();
        paint.setColor(color);
        directionX = true;
        directionY = true;
    }

    public Point getcercle() {
        return cercle;
    }

    public int getrayon() {
        return rayon;
    }

    public boolean getdirectionX() {
        return directionX;
    }
    public void setdirectionX(boolean val) { directionX = val; }
    public boolean getdirectionY() { return directionY; }
    public void setdirectionY(boolean val) { directionY = val; }


    @Override
    public void draw(Canvas canvas) {
        canvas.drawCircle(cercle.x, cercle.y, rayon, paint);
    }

    @Override
    public void update() {

    }

    private void movetheball(Point point){
        int x;
        int y;

        if (directionX) {
            x = point.x - MouvementX;
        }else{
            x = point.x + MouvementX;
        };
        if (directionY) {
            y = point.y - MouvementY;
        }else{
            y = point.y + MouvementY;
        };

        point.set(x,y);
    }

    public void update(Point point){
        movetheball(point);
        cercle.set(point.x, point.y);
    }

}
