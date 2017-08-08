package com.saille.hhcq;

import com.sinitek.dao.domain.PropertyDescription;

public class Jiage {
    private int id;
    private int gangkouId;
    private int shangpinId;
    private int jiage;
    private int weixian;

    @PropertyDescription(primaryKey = true, persistant = true)
    public int getId() {
        return this.id;
    }

    @PropertyDescription(primaryKey = true, persistant = true)
    public void setId(int id) {
        this.id = id;
    }

    @PropertyDescription(persistant = true, sqlType = 4)
    public int getGangkouId() {
        return this.gangkouId;
    }

    @PropertyDescription(persistant = true)
    public void setGangkouId(int gangkouId) {
        this.gangkouId = gangkouId;
    }

    @PropertyDescription(persistant = true, sqlType = 4)
    public int getShangpinId() {
        return this.shangpinId;
    }

    @PropertyDescription(persistant = true)
    public void setShangpinId(int shangpinId) {
        this.shangpinId = shangpinId;
    }

    @PropertyDescription(persistant = true, sqlType = 4)
    public int getJiage() {
        return this.jiage;
    }

    @PropertyDescription(persistant = true)
    public void setJiage(int jiage) {
        this.jiage = jiage;
    }

    @PropertyDescription(persistant = true, sqlType = 4)
    public int getWeixian() {
        return this.weixian;
    }

    @PropertyDescription(persistant = true)
    public void setWeixian(int weixian) {
        this.weixian = weixian;
    }
}