package com.alex.arkanoidprototype.model;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Color;
import com.alex.arkanoidprototype.model.Slider;
import android.util.Log;


public class Ball implements GameObject {

    private static int def_rayon = 30;
    private static int def_MouvementX = 3;
    private static int def_MouvementY = 5;

    private int rayon = def_rayon;
    private int MouvementX = def_MouvementX;
    private int MouvementY = def_MouvementY;

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

//    ----------
    public Rect getcercle() { return new Rect( cercle.x-rayon, cercle.y-rayon, cercle.x+rayon, cercle.y+rayon);  }
    public Point getpoint() { return cercle; }

    public Rect getRect() { return new Rect( cercle.x-rayon, cercle.y-rayon, cercle.x+rayon, cercle.y+rayon);  }
    public int getrayon() { return rayon; }

    public int getMouvementX() {
        return MouvementX;
    }
    public void setMouvementX(int val) {
        int value = Math.min(15, Math.abs(val));
        if (val < 0 ) value = value * -1;
        MouvementX = value;
        Log.v("Arkanoid - Ball", "MouvementX : " + String.valueOf(value));

    }

    public int getMouvementY() {
        return MouvementY;
    }

    public int getdef_MouvementX() {
        return def_MouvementX;
    }

    public boolean getdirectionX() {
        return directionX;
    }
    public void setdirectionX(boolean val) {
        this.directionX = val;
        Log.v("Arkanoid - Ball", "setdirectionX to " + String.valueOf(val));
    }
    public boolean getdirectionY() {
        return directionY;
    }
    public void setdirectionY(boolean val) {
        this.directionY = val;
        Log.v("Arkanoid - Ball", "setdirectionY to " + String.valueOf(val));
    }

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
        String txt="";

        if (this.directionX == true) {
            x = point.x - MouvementX;
        }else{
            x = point.x + MouvementX;
        };
        if (this.directionY == true) {
            y = point.y - MouvementY;
        }else{
            y = point.y + MouvementY;
        };

        txt = "   Ball move  FROM (" + String.valueOf(point.x) + "," + String.valueOf(point.y) + ")  TO (";
        point.set(x,y);
        txt = txt  + String.valueOf(x) + "," + String.valueOf(y) + ")";
        //Log.v("Arkanoid Ball", txt);
    }

    public void update(Point point){
        movetheball(point);
        cercle.set(point.x, point.y);
        //Log.v("Michel", "                               Vraiement ? ( "+ String.valueOf(point.x) + "," + String.valueOf(point.y) + ")");
    }

}
