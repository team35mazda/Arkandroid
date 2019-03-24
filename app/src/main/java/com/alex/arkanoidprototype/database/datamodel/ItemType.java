package com.alex.arkanoidprototype.database.datamodel;

public class ItemType {

    private long id;
    private String code;

    public void setId(long id) { this.id = id; }
    public void setCode(String code) { this.code = code; }

    public long getId() {
        return id;
    }
    public String getCode() {
        return code;
    }
}
