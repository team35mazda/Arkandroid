package com.alex.arkanoidprototype.model;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.alex.arkanoidprototype.R;
import com.alex.arkanoidprototype.database.Game;
import com.alex.arkanoidprototype.database.Niveau;
import com.alex.arkanoidprototype.database.NiveauItem;
import com.alex.arkanoidprototype.database.SavedGame;
import com.alex.arkanoidprototype.database.SavedGameNiveauItem;
import com.alex.arkanoidprototype.database.Utilisateur;

import java.util.List;

public class UserProfile {

    private int actualLevel;
    private int maxLife;
    private int life;

    public UserProfile(){
        this.load();
    }

    public int getActualLevel() {
        return actualLevel;
    }

    public boolean lostLife(){
        life--;

        return (life==0);
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public void setActualLevel(int actualLevel) {
        this.actualLevel = actualLevel;
    }

    public void draw(View view){

        TextView lifeView = view.findViewById(R.id.life);

        TextView levelView = view.findViewById(R.id.level);

        lifeView.setText(Integer.toString(this.life));
        levelView.setText(Integer.toString(this.actualLevel));
    }

    public void levelUp(){
        actualLevel++;
    }

    public void resetLife(){
        this.life = this.maxLife;
    }


    // Charger le default de la base de données
    private void load() {
        Game game = Game.listAll(Game.class).get(0);
        Utilisateur utilisateur = Utilisateur.listAll(Utilisateur.class).get(0);
        maxLife = game.getNombreVie();
        life = maxLife;
        actualLevel = 1;

        List<SavedGame> savedGames = SavedGame.find(SavedGame.class,"game = ? and utilisateur = ?",
                String.valueOf(game.getId()),String.valueOf(utilisateur.getId()));

        if (savedGames.size() != 0)
        {
            SavedGame savedGame = savedGames.get(0);
            maxLife = game.getNombreVie();
            life = savedGame.getNombreVieRestante();
            List<SavedGameNiveauItem> savedGameNiveauItems = SavedGameNiveauItem.listAll(SavedGameNiveauItem.class);
            if(savedGameNiveauItems.size() != 0) {
                SavedGameNiveauItem savedGameNiveauItem = savedGameNiveauItems.get(0);
                NiveauItem niveauItem = NiveauItem.findById(NiveauItem.class, savedGameNiveauItem.getNiveauItem().getId());
                Niveau niveau = Niveau.findById(Niveau.class, niveauItem.getNiveau().getId());
                actualLevel = Integer.parseInt(niveau.getCode());
            }
        }
    }

    // Charger la valeur courante de la base de données
    public void save() {
        Game game = Game.listAll(Game.class).get(0);
        Utilisateur utilisateur = Utilisateur.listAll(Utilisateur.class).get(0);
        List<SavedGame> savedGames = SavedGame.find(SavedGame.class,"game = ? and utilisateur = ?",
                String.valueOf(game.getId()),String.valueOf(utilisateur.getId()));
        if (savedGames.size() != 0) {
            SavedGame savedGame = savedGames.get(0);
            savedGame.setNombreVieRestante(life);
            savedGame.update();
        }
        else
        {
            SavedGame savedGame = new SavedGame(game,utilisateur,life);
            savedGame.save();
        }

    }


}
