package com.alex.arkanoidprototype.controler;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.StringDef;
import android.support.annotation.StringRes;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.SurfaceHolder;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.alex.arkanoidprototype.R;
import com.alex.arkanoidprototype.database.Couleur;
import com.alex.arkanoidprototype.database.Niveau;
import com.alex.arkanoidprototype.database.NiveauItem;
import com.alex.arkanoidprototype.database.Position;
import com.alex.arkanoidprototype.model.Block;
import com.alex.arkanoidprototype.model.BlockHit;
import com.alex.arkanoidprototype.model.Level;
import com.alex.arkanoidprototype.model.Slider;
import com.alex.arkanoidprototype.model.Ball;
import com.alex.arkanoidprototype.model.UserProfile;

import org.w3c.dom.Text;

import java.util.List;

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
    private UserProfile userProfile;
    private boolean runningState;
    private Point size;

    private static int xOffset = 10;    // For the slider speed.
    public static int screenWidth;
    public static int screenHeight;
    private Paint paint;
    private Bitmap levelBackground;

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }

    public Slider getSlider() {
        return slider;
    }

    public void setSlider(Slider slider) {
        this.slider = slider;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public Ball getBall() {
        return ball;
    }

    public void setBall(Ball ball) {
        this.ball = ball;
    }

    public GamePanel(Context context){
        super(context);
        getHolder().addCallback(this);
        this.initGamePanel(context);
    }

    public GamePanel(Context context, AttributeSet attrs){
        super(context, attrs);
        getHolder().addCallback(this);
        this.initGamePanel(context);
    }

    public GamePanel(Context context, AttributeSet attrs, int defStyle){
        super(context, attrs, defStyle);
        getHolder().addCallback(this);
        this.initGamePanel(context);
    }

    private void initGamePanel(Context context){
        thread = new MainThread(getHolder(), this);
        runningState = true;

        //Définition du profil usager initial
        userProfile = new UserProfile();

        WindowManager wm = (WindowManager) context.getSystemService(context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        this.size = new Point();
        display.getSize(size);
        screenWidth = size.x;
        screenHeight = size.y;

        this.setObjectsInCanevas();

        setFocusable(true);

        //Création du contrôleur de sons du jeu
        soundController = new SoundController(context);
        //Création de l'ecouteur d'evenements de senseurs
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
        ball.update(ballPoint);
    }

    private int getNewMouvement(){
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

         return ret;
    }

    private void detectCollision(Canvas canvas){
        Point ballpoint;
        ballpoint = ball.getpoint();

        //------ Tappe à gauche ou a droite de l'écran ------
        if ((ballpoint.x < ball.getrayon()) || (ballpoint.x+ball.getrayon() > canvas.getWidth())) ball.setdirectionX(!ball.getdirectionX());

        //------ Tappe en haut de l'écran________________

        if (ballpoint.y<ball.getrayon()) ball.setdirectionY(!ball.getdirectionY());
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
                ball.setMouvementX(getNewMouvement());
            }
        }

        //------Tappe sur un block ------
        BlockHit bhit = new BlockHit();
        level.DetectBlockHit(bhit, ballPoint, ball.getdirectionX(), ball.getdirectionY(), ball.getMouvementX(), ball.getMouvementY(), ball.getrayon());
        if (bhit.getHitByY()) {

            this.soundController.playBlockHitSound(); //son de la balle sur un block

            ball.setdirectionY(!ball.getdirectionY());

        }
        if (bhit.getHitByX()){

            this.soundController.playBlockHitSound();

            ball.setdirectionX(!ball.getdirectionX());

        }
        //-------------- Tappe en bas de l'écran-------------------
        if ((ballpoint.y + ball.getrayon() > canvas.getHeight())){
            ball.setdirectionY(!ball.getdirectionY());
            this.ball = new Ball();
            this.ball.resetDirection();
            ballPoint = ball.getpoint();
            this.update();

            if (userProfile.lostLife()){

                gameOver();
            }
        }

    }

    @Override
    public void draw(Canvas canvas){
        super.draw(canvas);

        //canvas.drawColor(Color.TRANSPARENT);
        canvas.drawBitmap(levelBackground, 0, 0, paint);

        if (level.allBlocksHit()){
            this.userProfile.levelUp();
            this.setObjectsInCanevas();
        }

        slider.draw(canvas);
        level.draw(canvas);
        ball.draw(canvas);

        detectCollision(canvas);

        View userProfileView = ((Activity) this.getContext()).findViewById(R.id.userProfilLayout);

        TextView lifeView = userProfileView.findViewById(R.id.life);
        TextView levelView = userProfileView.findViewById(R.id.level);

        if(lifeView.getText() != Integer.toString(userProfile.getLife()) || levelView.getText() != Integer.toString(userProfile.getActualLevel())) {

            lifeView.setText("");
            levelView.setText("");
            userProfile.draw(userProfileView);
        }
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

    public void setBackground(int level){

        int x = screenWidth;
        int y = screenHeight;

        String name = "background" + String.valueOf(level);
        Resources resources = getContext().getResources();
        int resourceId = resources.getIdentifier(name, "drawable", getContext().getPackageName());

        paint = new Paint();
        //Bitmap myBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.background3);
        Bitmap myBitmap = BitmapFactory.decodeResource(getResources(), resourceId);

        int outWidth;
        int outHeight;
        int inWidth = myBitmap.getWidth();
        int inHeight = myBitmap.getHeight();
        if(inWidth > inHeight){
            outWidth = x;
            outHeight = (inHeight * x) / inWidth;
        } else {
            outHeight = y;
            outWidth = (inWidth * y) / inHeight;
        }
        levelBackground = Bitmap.createScaledBitmap(myBitmap, outWidth, outHeight, false);
    }
  
    public void gameOver(){

        userProfile.resetLife();
        View userProfileView = ((Activity) this.getContext()).findViewById(R.id.userProfilLayout);

        TextView msg = userProfileView.findViewById(R.id.message);

        msg.setText(getContext().getString(R.string.gameover));

        try {
            thread.runningState(false);
            Thread.sleep(3000);

        }catch (Exception e){
            Log.i("Alex", "gameOver:"+e.getMessage());
        }

        this.setObjectsInCanevas();
        msg.setText(getContext().getString(R.string.letsBattle));
        thread.runningState(true);

    }

    public void setObjectsInCanevas(){

        // Création des éléments
        setBackground(userProfile.getActualLevel());
        slider = new Slider();
        ball = new Ball();
        level = new Level(userProfile.getActualLevel(), size);

        sliderPoint = slider.getpoint();
        ballPoint = ball.getpoint();

    }


    public void GamePanelRunningState(boolean runningState)
    {
        thread.runningState(runningState);
    }




}
