package com.alex.arkanoidprototype.model;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

import com.alex.arkanoidprototype.database.Couleur;
import com.alex.arkanoidprototype.database.Game;
import com.alex.arkanoidprototype.database.Item;
import com.alex.arkanoidprototype.database.ItemType;
import com.alex.arkanoidprototype.database.Niveau;
import com.alex.arkanoidprototype.database.NiveauItem;
import com.alex.arkanoidprototype.database.Position;
import com.alex.arkanoidprototype.database.SavedGame;
import com.alex.arkanoidprototype.database.SavedGameNiveauItem;
import com.alex.arkanoidprototype.database.Utilisateur;

import java.util.List;


public class Block implements GameObject {

    private Rect rectangle;
    private int color;
    private boolean visible;
    // Variable privé pour conserver la position du block dans le jeux
    private int positionX;
    private int positionY;


    public Block(Rect rectangle, int color,int positionX,int positionY){
        this.rectangle = rectangle;
        this.color = color;
        this.visible = true;
        this.positionX = positionX;
        this.positionY = positionY;
    }

    public Rect getRect(){
        return this.rectangle;
    }

    public void ballHit(BlockHit bhit, Point ballCoord, boolean directionX, boolean directionY, int MouvementX, int MouvementY, int rayon){

        //Todo: pour une meilleure détection, il faudrait calculer tous les points de la circonférences et valider par rapport aux coordonnée du rectangle

        DetectHIT:
        {
            //Si le block est déja tappé... donc invisible.. on ne cherche pas s'il y a un hit
            if (!visible) break DetectHIT;//return bhit;

            if ((ballCoord.x + rayon/2 >= rectangle.left) && (ballCoord.x - rayon/2 <= rectangle.right)) {
                if ((ballCoord.y - rayon <= rectangle.bottom) && (ballCoord.y > rectangle.bottom)) {
                    bhit.setHitByBottom(true);
                }
                else {
                    if ((ballCoord.y + rayon >= rectangle.top) && (ballCoord.y  < rectangle.top)) {
                        bhit.setHitByTop(true);
                    }
                }
            }
            else{
                if ((ballCoord.y + rayon/2 >= rectangle.top) && (ballCoord.y - rayon/2 <= rectangle.bottom)) {
                    if ((ballCoord.x + rayon >= rectangle.left) && (ballCoord.x < rectangle.left)) {
                        bhit.setHitByLeft(true);
                    }
                    else {
                        if ((ballCoord.x - rayon <= rectangle.right) && (ballCoord.x > rectangle.right)) {
                            bhit.setHitByRigth(true);
                        }
                    }
                }
            }

            if (bhit.getHit()) this.visible = false;
        }
    }

    @Override
    public void draw(Canvas canvas){

        if (visible){

            Paint paint = new Paint();
            //paint.setColor(color);
            //canvas.drawRect(rectangle,paint);

            paint.setStyle(Paint.Style.FILL);
            paint.setColor(color);
            canvas.drawRect(rectangle, paint);

            // border
            paint.setStyle(Paint.Style.STROKE);
            paint.setColor(Color.BLACK);
            canvas.drawRect(rectangle, paint);
        }
    }

    @Override
    public void update(){

    }

    public boolean isVisible() {
        return visible;
    }

    public void update(Point point){

        //rectangle.set(point.x - rectangle.width()/2, 1200, point.x + rectangle.width()/2, 1250);
        rectangle.set(point.x - rectangle.width()/2, point.y - rectangle.height()/2, point.x + rectangle.width()/2, point.y + rectangle.height()/2);
    }

    // Charger le default de la base de données
    public void load(int niveau) {

        NiveauItem niveauItem = NiveauItem.findWithQuery(NiveauItem.class,
                    "select Niveau_Item.* from Niveau_Item " +
                        "join Niveau on Niveau_Item.Niveau = Niveau.Id " +
                        "join Item on Niveau_Item.Item = Item.Id " +
                        "join Item_type on Item_type.id = Item.Item_type " +
                        "join Position_Niveau_Item on Niveau_Item.ID = Position_Niveau_Item.NIVEAU_ITEM "+
                        "join Position on POSITION.ID = POSITION_NIVEAU_ITEM.POSITION "+
                        "where Niveau.code = ? and Item_type.code = ? and POSITION.POSITION_X = ? and POSITION.POSITION_Y = ? ",
                String.valueOf(niveau),"BLOCK",String.valueOf(positionX),String.valueOf(positionY)).get(0);

        visible = niveauItem.getIsVisible() == 1;

        Item item = Item.findById(Item.class,niveauItem.getItem().getId());
        Couleur couleur = Couleur.findById(Couleur.class,item.getCouleur().getId());
        color = Color.rgb(couleur.getRouge(),couleur.getVert(),couleur.getBleu());

        // LoadSavedGame
        Game game = Game.listAll(Game.class).get(0);
        Utilisateur utilisateur = Utilisateur.listAll(Utilisateur.class).get(0);
        List<SavedGame> savedGames = SavedGame.find(SavedGame.class,"game = ? and utilisateur = ?",
                String.valueOf(game.getId()),String.valueOf(utilisateur.getId()));

        if (savedGames.size() != 0)
        {
            SavedGame savedGame = savedGames.get(0);
            String test = String.valueOf(niveauItem.getId());
            List<SavedGameNiveauItem> savedGameNiveauItems =
                    SavedGameNiveauItem.findWithQuery(SavedGameNiveauItem.class,
                            "select * from Saved_Game_Niveau_Item WHERE NIVEAU_ITEM = " + String.valueOf(niveauItem.getId()));
            if(savedGameNiveauItems.size() != 0) {
                SavedGameNiveauItem savedGameNiveauItem = savedGameNiveauItems.get(0);
                visible = savedGameNiveauItem.getIsVisible() == 1;
            }
        }
    }

    // Sauvegarder dans la base de données
    public void save(int niveau){

        NiveauItem niveauItem = NiveauItem.findWithQuery(NiveauItem.class,
                "select Niveau_Item.* from Niveau_Item " +
                        "join Niveau on Niveau_Item.Niveau = Niveau.Id " +
                        "join Item on Niveau_Item.Item = Item.Id " +
                        "join Item_type on Item_type.id = Item.Item_type " +
                        "join Position_Niveau_Item on Niveau_Item.ID = Position_Niveau_Item.NIVEAU_ITEM "+
                        "join Position on POSITION.ID = POSITION_NIVEAU_ITEM.POSITION "+
                        "where Niveau.code = ? and Item_type.code = ? and POSITION.POSITION_X = ? and POSITION.POSITION_Y = ? ",
                String.valueOf(niveau),"BLOCK",String.valueOf(positionX),String.valueOf(positionY)).get(0);

        Game game = Game.listAll(Game.class).get(0);
        Utilisateur utilisateur = Utilisateur.listAll(Utilisateur.class).get(0);
        List<SavedGame> savedGames = SavedGame.find(SavedGame.class,"game = ? and utilisateur = ?",
                String.valueOf(game.getId()),String.valueOf(utilisateur.getId()));
        if (savedGames.size() != 0) {
            SavedGame savedGame = savedGames.get(0);
            short IsVisibleSave = 0;
            if(visible) IsVisibleSave = 1;

            List<SavedGameNiveauItem> savedGameNiveauItems =
                    SavedGameNiveauItem.findWithQuery(SavedGameNiveauItem.class,
                            "select * from Saved_Game_Niveau_Item WHERE NIVEAU_ITEM = " + String.valueOf(niveauItem.getId()));
            if(savedGameNiveauItems.size() != 0) {
                SavedGameNiveauItem savedGameNiveauItem = savedGameNiveauItems.get(0);
                savedGameNiveauItem.setIsVisible(IsVisibleSave);
                savedGameNiveauItem.update();
            }
            else
            {
                SavedGameNiveauItem savedGameNiveauItem = new SavedGameNiveauItem(savedGame,niveauItem,IsVisibleSave);
                savedGameNiveauItem.save();
            }
        }
    }
}
