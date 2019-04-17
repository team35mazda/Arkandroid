package com.alex.arkanoidprototype.database;

import com.orm.SugarRecord;

public class ItemType extends SugarRecord {

    private String code;

    public ItemType() {
    }

    public ItemType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
