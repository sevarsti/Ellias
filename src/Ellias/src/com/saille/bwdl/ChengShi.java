package com.saille.bwdl;

import com.sinitek.dao.domain.ClassDescription;
import com.sinitek.dao.domain.PropertyDescription;

import java.util.Date;

@ClassDescription(table = "CHENGSHI")
public class ChengShi {
    private int id;
    private String name;
    private int jin;
    private int mi;
    private int bao;
    private int tongzhi;
    private int tudi;
    private int chanye;
    private int renkou;
    private int fangzai;
    private Date updateTime;
    private int version;
    private int shili;
    private boolean init;
    private int locationX;
    private int locationY;

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
    public int getJin() {
        return this.jin;
    }

    @PropertyDescription(persistant = true)
    public void setJin(int jin) {
        this.jin = jin;
    }

    @PropertyDescription(persistant = true, sqlType = 4)
    public int getMi() {
        return this.mi;
    }

    @PropertyDescription(persistant = true)
    public void setMi(int mi) {
        this.mi = mi;
    }

    @PropertyDescription(persistant = true, sqlType = 4)
    public int getBao() {
        return this.bao;
    }

    @PropertyDescription(persistant = true)
    public void setBao(int bao) {
        this.bao = bao;
    }

    @PropertyDescription(persistant = true, sqlType = 4)
    public int getTongzhi() {
        return this.tongzhi;
    }

    @PropertyDescription(persistant = true)
    public void setTongzhi(int tongzhi) {
        this.tongzhi = tongzhi;
    }

    @PropertyDescription(persistant = true, sqlType = 4)
    public int getTudi() {
        return this.tudi;
    }

    @PropertyDescription(persistant = true)
    public void setTudi(int tudi) {
        this.tudi = tudi;
    }

    @PropertyDescription(persistant = true, sqlType = 4)
    public int getChanye() {
        return this.chanye;
    }

    @PropertyDescription(persistant = true)
    public void setChanye(int chanye) {
        this.chanye = chanye;
    }

    @PropertyDescription(persistant = true, sqlType = 4)
    public int getRenkou() {
        return this.renkou;
    }

    @PropertyDescription(persistant = true)
    public void setRenkou(int renkou) {
        this.renkou = renkou;
    }

    @PropertyDescription(persistant = true, sqlType = 4)
    public int getFangzai() {
        return this.fangzai;
    }

    @PropertyDescription(persistant = true)
    public void setFangzai(int fangzai) {
        this.fangzai = fangzai;
    }

    @PropertyDescription(persistant = true, sqlType = 93)
    public Date getUpdateTime() {
        return this.updateTime;
    }

    @PropertyDescription(persistant = true)
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @PropertyDescription(persistant = true, sqlType = 4)
    public int getVersion() {
        return this.version;
    }

    @PropertyDescription(persistant = true)
    public void setVersion(int version) {
        this.version = version;
    }

    @PropertyDescription(persistant = true, sqlType = 4)
    public int getShili() {
        return this.shili;
    }

    @PropertyDescription(persistant = true)
    public void setShili(int shili) {
        this.shili = shili;
    }

    public boolean isInit() {
        return this.init;
    }

    public void setInit(boolean init) {
        this.init = init;
    }

    @PropertyDescription(persistant = true, sqlType = 4)
    public int getLocationX() {
        return this.locationX;
    }

    @PropertyDescription(persistant = true)
    public void setLocationX(int locationX) {
        this.locationX = locationX;
    }

    @PropertyDescription(persistant = true, sqlType = 4)
    public int getLocationY() {
        return this.locationY;
    }

    @PropertyDescription(persistant = true)
    public void setLocationY(int locationY) {
        this.locationY = locationY;
    }
}