package com.alex.arkanoidprototype.controler;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.SurfaceHolder;
import android.view.WindowManager;

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
    private SoundController soundController;
    private ControlListener controlListener;

    private static int LevelStartPosY = 100;
    private static int SliderStartPosX = 500;
    private static int SliderStartPosY = 1200;

    private MainActivity mainactivity;
    private static int xOffset = 10;    // For the slider speed.
    public static int pointX = 500;
    public static int pointY = 1200;
    public static int screenWidth;


    public GamePanel(Context context){
        super(context);

        getHolder().addCallback(this);

        thread = new MainThread(getHolder(), this);

        slider = new Slider(new Rect(0,0,200,75), Color.rgb(255,0,0));
        sliderPoint = new Point(SliderStartPosX,SliderStartPosY);
        //level = new Level(1);

        WindowManager wm = (WindowManager) context.getSystemService(context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        screenWidth = size.x;

//        slider = new Slider(new Rect(50,50,200,100), Color.rgb(255,0,0));
        mainactivity = new MainActivity();
        sliderPoint = new Point(pointX, pointY);
        level = new Level(3, size);

        int sliderCenter;
        //TODO: mettre le rayon comme variable syst�me
        int ballrayon = 30;
        sliderCenter = slider.getRect().right - slider.getRect().left; //Size du slider
        sliderCenter = round(sliderCenter / 2) - ballrayon;

        ballPoint = new Point(sliderCenter, SliderStartPosY-ballrayon);
        ball = new Ball(ballPoint, Color.rgb(102,135,255)); //

        setFocusable(true);

        //Création du contrôleur de sons du jeu
        soundController = new SoundController(context);

        controlListener = new ControlListener(context,this);

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
                // ONTOUCH EVENT OR WITH SENSOR #################################################
                sliderPoint.set((int)event.getX(),(int)event.getY());
        }
        //return super.onTouchEvent(event);
        return true;
    }

    public void update(){
        slider.update(sliderPoint);
        //sliderPoint.set((int)this.getX(),(int)this.getY());
		ball.update(ballPoint);
        //sliderUpdate();
    }

    private int Get_New_Mouvement(){
        int ret = 0;
        int sign = 0;
        if (ball.getdirectionX()) {sign = -1;}
        int SeptiemeDePlaque = round((slider.getRect().right - slider.getRect().left) / 7);
        int Position = ballPoint.x - slider.getRect().left;

        int plaquePos = (int)(Position / SeptiemeDePlaque);
        int oldMouvement = ball.getMouvementX();

        // Correction du mouvement selon l'arriver sur la plaque, le rebond sur le Slider, on doit changer la direction de la balle
        //    7 Emplacements    La base : pareil au rebond sur un mur, on rebondit en changeant d'axe seulement
        //    -------------     1 et 7 : 2 x la correction du MouvementX (soit du c�t� gauche, sinon droit)
        //    |1|2|345|6|7|     2 et 6 : 1 x la correction du MouvementX (soit du c�t� gauche, sinon droit)
        //    -------------     3 et 5 : 0.5 x la correction du MouvementX | 4 : on rebondit en changeant d'axe
        switch (plaquePos + 1) {
            case 1: // A de gauche
                ret = oldMouvement - (sign * 2 * ball.getdef_MouvementX()); // A de gauche
                break;
            case 2:
                ret = oldMouvement - (sign * ball.getdef_MouvementX()); // B de gauche
                break;
            case 3:
                ret = oldMouvement - (int)(sign * 0.5 * ball.getdef_MouvementX()); // C de gauche
                break;
            case 4:
                ret = oldMouvement; // C... le centre Aucun changement de MouvementX
                break;
            case 5:
                ret = oldMouvement + (int)(sign * 0.5 * ball.getdef_MouvementX()); // C de droite
                break;
            case 6:
                ret = oldMouvement + (sign * ball.getdef_MouvementX()); // B de droite
                break;
            case 7:
                ret = oldMouvement + (sign * 2 * ball.getdef_MouvementX()); // A de droite
                break;
            default:
                break;
        }

        Log.v("Arkanoid - GamePanel", "Rebond sur plaque Direction ("+ball.getdirectionX()+")   Old Mouvement : " + oldMouvement + ", New Mouvement : " + ret + " |   plaquePos  : " + plaquePos + "    Balle : " + Position + "    SeptiemeDePlaque  : " + SeptiemeDePlaque);

        return ret;
    }

    private void detect_collision(Canvas canvas){
        Point ballpoint;
        ballpoint = ball.getpoint();

        //------ Tappe � gauche ou a droite de l'�cran ------
        if ((ballpoint.x<ball.getrayon()) || (ballpoint.x+ball.getrayon() > canvas.getWidth())) ball.setdirectionX(!ball.getdirectionX());

        //------ Tappe en haut ou en bas de l'�cran ------
        //TODO:enlever le riccochet avec le bas pour faire perdre une vie
        if ((ballpoint.y<ball.getrayon()) || (ballpoint.y + ball.getrayon() > canvas.getHeight())) ball.setdirectionY(!ball.getdirectionY());

        //------ Tappe sur le slider ------
        //On est a la hauteur du slider ?
        if ((ballpoint.y+ball.getrayon() >= slider.getRect().top) && (ballpoint.y-ball.getrayon() < slider.getRect().top)) {
            //On est sur le slider ?
            if ((ballpoint.x-ball.getrayon()/2 >= slider.getRect().left) && (ballpoint.x+ball.getrayon()/2 <= slider.getRect().right)) {

                this.soundController.playSliderHitSound(); //Son de la balle sur le slider

                ball.setdirectionY(true);
                // Correction du mouvement selon l'arriver sur la plaque, le rebond sur le Slider, on doit changer la direction de la balle
                //    7 Emplacements    La base : pareil au rebond sur un mur, on rebondit en changeant d'axe seulement
                //    -------------     A : 2 x la correction du MouvementX (soit du c�t� gauche, sinon droit)
                //    |A|B| C |B|A|     B : 1 x la correction du MouvementX (soit du c�t� gauche, sinon droit)
                //    -------------     C : , on rebondit en changeant d'axe
                int SeptiemeDePlaque = round((slider.getRect().right - slider.getRect().left) / 7);
                int Position = ballpoint.x-ball.getrayon()/2 - slider.getRect().left;
                ball.setMouvementX(Get_New_Mouvement());
            }
        }

        //------Tappe sur un block ------
        BlockHit bhit = new BlockHit();
        level.DetectBlockHit(bhit, ballPoint, ball.getdirectionX(), ball.getdirectionY(), ball.getMouvementX(), ball.getMouvementY(), ball.getrayon());
        if (bhit.getHitByY()) {

            this.soundController.playBlockHitSound(); //son de la balle sur un block

            ball.setdirectionY(!ball.getdirectionY());
//            if (bhit.HitByTop ) ball.setdirectionY(true);
//            if (bhit.HitByBottom ) ball.setdirectionY(false);
        }
        if (bhit.getHitByX()){

            this.soundController.playBlockHitSound();

            ball.setdirectionX(!ball.getdirectionX());
//            if (bhit.HitByLeft ) ball.setdirectionX(true);
//            if (bhit.HitByRigth ) ball.setdirectionX(false);
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

    // This function is in the GamePanel class because I think it has easy access to objects *?*
    public void sliderPointUpdate(int tourner){

        if ((sliderPoint.x < 0) || (sliderPoint.x > screenWidth)) {
            if (sliderPoint.x < 0) {
                sliderPoint.offset(xOffset, 0);
            } else {
                if (sliderPoint.x > screenWidth) {
                    sliderPoint.offset(xOffset*(-1), 0);
                }
            }
        } else {
            if (tourner == 0) {
                sliderPoint.offset(0, 0);
            } else {
                if (tourner == 1) {
                    sliderPoint.offset(xOffset, 0);
                } else {
                    if (tourner == -1) {
                        sliderPoint.offset(xOffset*(-1), 0);
                    } else {
                        sliderPoint.offset(0, 0);   // Si erreur, ne rien faire.
                    }
                }
            }
        }
        //slider.update(sliderPoint);
    }

    public ControlListener getControlListener(){
        return controlListener;
    }

}
