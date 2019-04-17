package com.alex.arkanoidprototype.database;

import com.orm.SugarRecord;

public class Couleur extends SugarRecord {

    private String code;
    private int rouge;
    private int vert;
    private int bleu;

    public Couleur(){
    }

    public Couleur(String code,int rouge,int vert, int bleu ){
        this.code = code;
        this.rouge = rouge;
        this.vert = vert;
        this.bleu = bleu;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getRouge() {
        return rouge;
    }

    public void setRouge(int rouge) {
        this.rouge = rouge;
    }

    public int getVert() {
        return vert;
    }

    public void setVert(int vert) {
        this.vert = vert;
    }

    public int getBleu() {
        return bleu;
    }

    public void setBleu(int bleu) {
        this.bleu = bleu;
    }
}
