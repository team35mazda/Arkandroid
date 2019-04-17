package com.alex.arkanoidprototype.controler;

import android.content.Context;

import com.alex.arkanoidprototype.database.Couleur;
import com.alex.arkanoidprototype.database.Game;
import com.alex.arkanoidprototype.database.Item;
import com.alex.arkanoidprototype.database.ItemType;
import com.alex.arkanoidprototype.database.Mouvement;
import com.alex.arkanoidprototype.database.Niveau;
import com.alex.arkanoidprototype.database.NiveauItem;
import com.alex.arkanoidprototype.database.NiveauItemMouvement;
import com.alex.arkanoidprototype.database.Position;
import com.alex.arkanoidprototype.database.PositionNiveauItem;
import com.alex.arkanoidprototype.database.Pouvoir;
import com.alex.arkanoidprototype.database.PouvoirNiveauItem;
import com.alex.arkanoidprototype.database.SavedGame;
import com.alex.arkanoidprototype.database.Utilisateur;
import com.alex.arkanoidprototype.model.Block;
import com.alex.arkanoidprototype.model.Level;
import com.alex.arkanoidprototype.model.UserProfile;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public class DatabaseController {

   // private Context context;
   // private GamePanel gamepanel;

    public DatabaseController() {

        if(!IsDataBaseHaveDefault())
            SetDefaultDataBase();
    }

    // sauvegarder le statut des objet de jeux en court
    public void saveState(GamePanel gamepanel) {
        gamepanel.getUserProfile().save();
        gamepanel.getLevel().save();
    }

    private boolean IsDataBaseHaveDefault() {
        List<Niveau> niveaux = Niveau.listAll(Niveau.class);
        return niveaux.size() != 0;
    }

    private void SetDefaultDataBase() {

        // Game
        long idGame = new Game(3).save();

        // Utilisateur
        long idUtilisateur = new Utilisateur("AUCUN","Noid","Arkan").save();

        // Pouvoir
        long idPouvoirDurabilite = new Pouvoir("DURABILITE","Durabilité").save();

        // Couleur
        long idRouge = new Couleur("ROUGE",255,0,0).save();
        long idGris = new Couleur("GRIS",102,135,255).save();
        long idJaune = new Couleur("JAUNE",255,255,0).save();
        long idVert = new Couleur("VERT",0,255,0).save();
        long idBleu = new Couleur("BLEU",0,0,255).save();

        // ItemType
        long idBall = new ItemType("BALL").save();
        long idSlider = new ItemType("SLIDER").save();
        long idBlock = new ItemType("BLOCK").save();

        // Mouvement
        long idMouvement = new Mouvement(3,5,1,1).save();

        //Item
        long idItemBall = new Item(ItemType.findById(ItemType.class,idBall),Couleur.findById(Couleur.class,idGris)).save();
        long idItemSlider = new Item(ItemType.findById(ItemType.class,idSlider),Couleur.findById(Couleur.class,idRouge)).save();
        long idItemBlockRouge = new Item(ItemType.findById(ItemType.class,idBlock),Couleur.findById(Couleur.class,idRouge)).save();
        long idItemBlockJaune = new Item(ItemType.findById(ItemType.class,idBlock),Couleur.findById(Couleur.class,idJaune)).save();
        long idItemBlockVert = new Item(ItemType.findById(ItemType.class,idBlock),Couleur.findById(Couleur.class,idVert)).save();
        long idItemBlockBleu = new Item(ItemType.findById(ItemType.class,idBlock),Couleur.findById(Couleur.class,idBleu)).save();

        long idNiveau;
        long idNiveauItemBall;
        long idNiveauItemSlider;
        long idNiveauItemBlock;
        long idNiveauItemMouvement;
        long idNiveauItemPosition;
        long idNiveauItemPouvoir;
        long idPosition;

        // Niveau
        idNiveau = new Niveau("ALL").save();
        // NiveauItem (balle et slider)
        idNiveauItemSlider = new NiveauItem(Niveau.findById(Niveau.class,idNiveau),Item.findById(Item.class,idItemSlider),1).save();
        idNiveauItemBall = new NiveauItem(Niveau.findById(Niveau.class,idNiveau),Item.findById(Item.class,idItemBall),1).save();
        // NiveauItemMouvement (balle)
        idNiveauItemMouvement = new NiveauItemMouvement(NiveauItem.findById(NiveauItem.class,idNiveauItemBall),Mouvement.findById(Mouvement.class,idMouvement)).save();

        for (int niveau = 1; niveau <= 3; niveau++)
        {
            // Niveau
            idNiveau = new Niveau(Integer.toString(niveau)).save();

            for (int x = 1; x <= 10; x++)
            {
                for(int y=1; y <= 7;y++)
                {
                    //Position
                    if(niveau == 1)
                        idPosition = new Position(x,y).save();
                    else
                        idPosition = Position.find(Position.class,"position_x = ? and position_y = ?", String.valueOf(x),String.valueOf(y)).get(0).getId();

                    // Block

                    if(niveau == 1)
                    {
                        if((x <= 6 && y >= 5) || (x >= 7 && y <= 4))
                            idNiveauItemBlock = new NiveauItem(Niveau.findById(Niveau.class,idNiveau),Item.findById(Item.class,idItemBlockRouge),1).save();
                        else
                            idNiveauItemBlock = new NiveauItem(Niveau.findById(Niveau.class,idNiveau),Item.findById(Item.class,idItemBlockJaune),1).save();

                        idNiveauItemPosition = new PositionNiveauItem(Position.findById(Position.class,idPosition),NiveauItem.findById(NiveauItem.class,idNiveauItemBlock)).save();

                        if(y >= 7)
                            idNiveauItemPouvoir = new PouvoirNiveauItem(Pouvoir.findById(Pouvoir.class,idPouvoirDurabilite),NiveauItem.findById(NiveauItem.class,idNiveauItemBlock), 2).save();
                        else
                            idNiveauItemPouvoir = new PouvoirNiveauItem(Pouvoir.findById(Pouvoir.class,idPouvoirDurabilite),NiveauItem.findById(NiveauItem.class,idNiveauItemBlock), 1).save();

                    }

                    if(niveau == 2) {
                        if ((y % 2 == 0))
                            idNiveauItemBlock = new NiveauItem(Niveau.findById(Niveau.class, idNiveau), Item.findById(Item.class, idItemBlockJaune), 0).save();
                        else if (x % 2 == 0)
                            idNiveauItemBlock = new NiveauItem(Niveau.findById(Niveau.class, idNiveau), Item.findById(Item.class, idItemBlockVert), 1).save();
                        else
                            idNiveauItemBlock = new NiveauItem(Niveau.findById(Niveau.class, idNiveau), Item.findById(Item.class, idItemBlockJaune), 1).save();
                        idNiveauItemPosition = new PositionNiveauItem(Position.findById(Position.class, idPosition), NiveauItem.findById(NiveauItem.class, idNiveauItemBlock)).save();
                        if (y >= 7)
                            idNiveauItemPouvoir = new PouvoirNiveauItem(Pouvoir.findById(Pouvoir.class, idPouvoirDurabilite), NiveauItem.findById(NiveauItem.class, idNiveauItemBlock), 2).save();
                        else
                            idNiveauItemPouvoir = new PouvoirNiveauItem(Pouvoir.findById(Pouvoir.class, idPouvoirDurabilite), NiveauItem.findById(NiveauItem.class, idNiveauItemBlock), 1).save();
                    }

                    if(niveau == 3)
                    {
                        if ((x == 1 && y == 1) || (x == 3 && y == 1) || (x == 5 && y == 1) || (x == 6 && y == 1) || (x == 8 && y == 1) ||
                            (x == 10 && y == 1) || (x == 1 && y == 2) || (x == 3 && y == 2) || (x == 5 && y == 2) || (x == 8 && y == 2) ||
                            (x == 10 && y == 2) || (x == 1 && y == 3) || (x == 2 && y == 3) || (x == 3 && y == 3) || (x == 5 && y == 3) ||
                            (x == 6 && y == 3) || (x == 9 && y == 3) || (x == 1 && y == 4) || (x == 3 && y == 4) || (x == 5 && y == 4) ||
                            (x == 9 && y == 4) || (x == 1 && y == 5) || (x == 3 && y == 5) || (x == 5 && y == 5) || (x == 6 && y == 5) ||
                            (x == 9 && y == 5)
                            )
                            idNiveauItemBlock = new NiveauItem(Niveau.findById(Niveau.class, idNiveau), Item.findById(Item.class, idItemBlockBleu), 1).save();
                        else
                            idNiveauItemBlock = new NiveauItem(Niveau.findById(Niveau.class, idNiveau), Item.findById(Item.class, idItemBlockJaune), 0).save();

                        idNiveauItemPosition = new PositionNiveauItem(Position.findById(Position.class, idPosition), NiveauItem.findById(NiveauItem.class, idNiveauItemBlock)).save();
                        idNiveauItemPouvoir = new PouvoirNiveauItem(Pouvoir.findById(Pouvoir.class, idPouvoirDurabilite), NiveauItem.findById(NiveauItem.class, idNiveauItemBlock), 1).save();

                    }
                }
            }
        }
    }

}
