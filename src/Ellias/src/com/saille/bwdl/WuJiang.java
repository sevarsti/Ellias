package com.saille.bwdl;

import com.sinitek.dao.domain.ClassDescription;
import com.sinitek.dao.domain.PropertyDescription;

import java.util.Date;

@ClassDescription(table = "WUJIANG")
public class WuJiang {
    private int id;
    private String name;
    private int ti;
    private int wu;
    private int zhi;
    private int zhong;
    private int de;
    private int jing;
    private int bingzhong;
    private int wuqi;
    private int fangju;
    private int shibing;
    private int chuchang;
    private Date updateTime;
    private int version;
    private boolean init;

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
    public int getTi() {
        return this.ti;
    }

    @PropertyDescription(persistant = true)
    public void setTi(int ti) {
        this.ti = ti;
    }

    @PropertyDescription(persistant = true, sqlType = 4)
    public int getWu() {
        return this.wu;
    }

    @PropertyDescription(persistant = true)
    public void setWu(int wu) {
        this.wu = wu;
    }

    @PropertyDescription(persistant = true, sqlType = 4)
    public int getZhi() {
        return this.zhi;
    }

    @PropertyDescription(persistant = true)
    public void setZhi(int zhi) {
        this.zhi = zhi;
    }

    @PropertyDescription(persistant = true, sqlType = 4)
    public int getZhong() {
        return this.zhong;
    }

    @PropertyDescription(persistant = true)
    public void setZhong(int zhong) {
        this.zhong = zhong;
    }

    @PropertyDescription(persistant = true, sqlType = 4)
    public int getDe() {
        return this.de;
    }

    @PropertyDescription(persistant = true)
    public void setDe(int de) {
        this.de = de;
    }

    @PropertyDescription(persistant = true, sqlType = 4)
    public int getJing() {
        return this.jing;
    }

    @PropertyDescription(persistant = true)
    public void setJing(int jing) {
        this.jing = jing;
    }

    @PropertyDescription(persistant = true, sqlType = 4)
    public int getBingzhong() {
        return this.bingzhong;
    }

    @PropertyDescription(persistant = true)
    public void setBingzhong(int bingzhong) {
        this.bingzhong = bingzhong;
    }

    @PropertyDescription(persistant = true, sqlType = 4)
    public int getWuqi() {
        return this.wuqi;
    }

    @PropertyDescription(persistant = true)
    public void setWuqi(int wuqi) {
        this.wuqi = wuqi;
    }

    @PropertyDescription(persistant = true, sqlType = 4)
    public int getFangju() {
        return this.fangju;
    }

    @PropertyDescription(persistant = true)
    public void setFangju(int fangju) {
        this.fangju = fangju;
    }

    @PropertyDescription(persistant = true, sqlType = 4)
    public int getShibing() {
        return this.shibing;
    }

    @PropertyDescription(persistant = true)
    public void setShibing(int shibing) {
        this.shibing = shibing;
    }

    @PropertyDescription(persistant = true, sqlType = 4)
    public int getChuchang() {
        return this.chuchang;
    }

    @PropertyDescription(persistant = true)
    public void setChuchang(int chuchang) {
        this.chuchang = chuchang;
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

    public boolean isInit() {
        return this.init;
    }

    public void setInit(boolean init) {
        this.init = init;
    }
}