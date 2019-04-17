package com.alex.arkanoidprototype.database;

import com.orm.SugarRecord;

public class Pouvoir extends SugarRecord {
    private String code;
    private String description;

    public Pouvoir() {
    }

    public Pouvoir(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
