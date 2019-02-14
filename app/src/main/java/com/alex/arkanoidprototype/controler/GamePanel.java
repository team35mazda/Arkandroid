package com.alex.arkanoidprototype.controler;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.SurfaceHolder;

import com.alex.arkanoidprototype.model.BlockHit;
import com.alex.arkanoidprototype.model.Level;
import com.alex.arkanoidprototype.model.Slider;
import com.alex.arkanoidprototype.model.Ball;

import static java.lang.Math.round;

public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {
    private MainThread thread;

    private Slider slider;
    private Point sliderPoint;
    private Level level;
    private Ball ball;
    private Point ballPoint;

    private static int SliderStartPosX = 500;
    private static int SliderStartPosY = 1200;

    public GamePanel(Context context){
        super(context);

        getHolder().addCallback(this);

        thread = new MainThread(getHolder(), this);

        slider = new Slider(new Rect(50,50,200,100), Color.rgb(255,0,0));
        sliderPoint = new Point(SliderStartPosX,SliderStartPosY);

        level = new Level(1);
        int sliderCenter;
        //TODO: mettre le rayon comme variable système
        int ballrayon = 30;
        sliderCenter = slider.getRect().right - slider.getRect().left; //Size du slider
        sliderCenter = round(sliderCenter / 2) - ballrayon;

        ballPoint = new Point(sliderCenter, SliderStartPosY-ballrayon);
        ball = new Ball(ballPoint, Color.rgb(102,135,255)); //

        setFocusable(true);

        Log.v("Michel", "----- NEW START -----");

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height){

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder){
        thread = new MainThread(getHolder(), this);

        thread.setRunning(true);
        thread.start();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder){
        boolean retry = true;

        while(retry){
            try{
                thread.setRunning(false);
                thread.join();
                retry = false;
            }catch(Exception e){e.printStackTrace();}
         }
    }

    @Override
    public boolean onTouchEvent (MotionEvent event){

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                sliderPoint.set((int)event.getX(),(int)event.getY());

                // ballPoint.set((int)event.getX(),(int)event.getY());  // pour débug, je positionne la balle au touchpoint...
        }
        //return super.onTouchEvent(event);
        return true;
    }

    public void update(){
        slider.update(sliderPoint);
        ball.update(ballPoint);
    }

    private void detect_collision(Canvas canvas){
        Point ballpoint;
        ballpoint = ball.getpoint();

        //------ Tappe à gauche ou a droite de l'écran ------
        if ((ballpoint.x<ball.getrayon()) || (ballpoint.x+ball.getrayon() > canvas.getWidth())) ball.setdirectionX(!ball.getdirectionX());

        //------ Tappe en haut ou en bas de l'écran ------
        //TODO:enlever le riccochet avec le bas pour faire perdre une vie
        if ((ballpoint.y<ball.getrayon()) || (ballpoint.y + ball.getrayon() > canvas.getHeight())) ball.setdirectionY(!ball.getdirectionY());

        //------ Tappe sur le slider ------
        //On est a la hauteur du slider ?
        if (ballpoint.y+ball.getrayon() >= slider.getRect().top) {
            //On est sur le slider ?
            if (ballpoint.x >= slider.getRect().left && ballpoint.x <= slider.getRect().right)
                ball.setdirectionY(true);
            // Correction du mouvement selon l'arriver sur la plaque, le rebond sur le Slider, on doit changer la direction de la balle
            //    7 Emplacements    La base : pareil au rebond sur un mur, on rebondit en changeant d'axe seulement
            //    -------------     A : 2 x la correction du MouvementX (soit du côté gauche, sinon droit)
            //    |A|B| C |B|A|     B : 1 x la correction du MouvementX (soit du côté gauche, sinon droit)
            //    -------------     C : , on rebondit en changeant d'axe
            int SeptiemeDePlaque = round((slider.getRect().right - slider.getRect().left) / 7);
            int Position = ballpoint.x - slider.getRect().left;
            if (Position <= SeptiemeDePlaque) {
                ball.setMouvementX(ball.getMouvementX() - 2 * ball.getdef_MouvementX()); // A de gauche
            }
            else {
                if (Position <= SeptiemeDePlaque * 2) {
                    ball.setMouvementX(ball.getMouvementX() - ball.getdef_MouvementX()); // B de gauche
                }
                else {
                    if (Position <= SeptiemeDePlaque * 5) {
                        ball.setMouvementX(ball.getMouvementX()); // C... le centre Aucun changement de MouvementX
                    }
                    else {
                        if (Position <= SeptiemeDePlaque * 7) {
                            ball.setMouvementX(ball.getMouvementX() + ball.getdef_MouvementX()); // B de droite
                        }
                        else {
                                ball.setMouvementX(ball.getMouvementX() + 2 * ball.getdef_MouvementX()); // A de droite
                        }
                    }
                }
            }
        }

        //------Tappe sur un block ------
        BlockHit bhit = level.DetectBlockHit(ballPoint, ball.getdirectionX(), ball.getdirectionY(), ball.getMouvementX(), ball.getMouvementY(), ball.getrayon());
        if (bhit.getHitByY()) {
            if (bhit.HitByTop ) ball.setdirectionY(true);
            if (bhit.HitByBottom ) ball.setdirectionY(false);
        }
        if (bhit.getHitByX()){
            if (bhit.HitByLeft ) ball.setdirectionX(true);
            if (bhit.HitByRigth ) ball.setdirectionX(false);
        }

    }

    @Override
    public void draw(Canvas canvas){
        super.draw(canvas);

        canvas.drawColor(Color.WHITE);

        slider.draw(canvas);
        level.draw(canvas);
        ball.draw(canvas);

        detect_collision(canvas);
    }

}
