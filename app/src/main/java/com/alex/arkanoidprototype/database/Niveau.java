package com.alex.arkanoidprototype.database;

import com.orm.SugarRecord;

public class Niveau extends SugarRecord {

    private String code;

    public Niveau() {
    }

    public Niveau(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
