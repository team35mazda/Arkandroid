package com.alex.arkanoidprototype.database.datamodel;

public class Couleur {
    private long id;
    private String code;
    private long rouge;
    private long vert;
    private long bleu;

    public void setId(long id) { this.id = id; }
    public void setCode(String code) { this.code = code; }
    public void setRouge(long rouge) { this.rouge = rouge; }
    public void setVert(long vert) { this.vert = vert; }
    public void setBleu(long bleu) { this.bleu = bleu; }

    public long getId() {
        return id;
    }
    public String getCode() {
        return code;
    }
    public long getRouge() {
        return rouge;
    }
    public long getVert() {
        return vert;
    }
    public long getBleu() {
        return bleu;
    }

}
