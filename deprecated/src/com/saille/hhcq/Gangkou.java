package com.saille.hhcq;

import com.sinitek.dao.domain.ClassDescription;
import com.sinitek.dao.domain.PropertyDescription;

@ClassDescription(table = "GANGKOU")
public class Gangkou {
    private int id;
    private String name;
    private int guojia;
    private String pinyin;

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
    public int getGuojia() {
        return this.guojia;
    }

    @PropertyDescription(persistant = true)
    public void setGuojia(int guojia) {
        this.guojia = guojia;
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