package com.saille.hhcq;

import com.sinitek.dao.domain.PropertyDescription;

public class Shangpin {
    private int id;
    private String name;
    private String pinyin;
    private int leibie;

    @PropertyDescription(primaryKey = true, persistant = true)
    public int getId() {
        return this.id;
    }

    @PropertyDescription(primaryKey = true, persistant = true)
    public void setId(int id) {
        this.id = id;
    }

    @PropertyDescription(persistant = true, sqlType = 12)
    public String getName() {
        return this.name;
    }

    @PropertyDescription(persistant = true)
    public void setName(String name) {
        this.name = name;
    }

    @PropertyDescription(persistant = true, sqlType = 4)
    public int getLeibie() {
        return this.leibie;
    }

    @PropertyDescription(persistant = true)
    public void setLeibie(int leibie) {
        this.leibie = leibie;
    }

    @PropertyDescription(persistant = true, sqlType = 12)
    public String getPinyin() {
        return this.pinyin;
    }

    @PropertyDescription(persistant = true)
    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }
}