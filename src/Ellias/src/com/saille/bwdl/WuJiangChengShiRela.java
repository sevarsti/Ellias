package com.saille.bwdl;

import com.sinitek.dao.domain.ClassDescription;
import com.sinitek.dao.domain.PropertyDescription;

import java.util.Date;

@ClassDescription(table = "WUJIANGCHENGSHIRELA")
public class WuJiangChengShiRela {
    private int id;
    private int chengshiId;
    private int wujiangId;
    private int version;
    private Date updateTime;
    private int index;
    private boolean init;

    @PropertyDescription(primaryKey = true, persistant = true)
    public int getId() {
        return this.id;
    }

    @PropertyDescription(primaryKey = true, persistant = true)
    public void setId(int id) {
        this.id = id;
    }

    @PropertyDescription(persistant = true, sqlType = 4)
    public int getChengshiId() {
        return this.chengshiId;
    }

    @PropertyDescription(persistant = true)
    public void setChengshiId(int chengshiId) {
        this.chengshiId = chengshiId;
    }

    @PropertyDescription(persistant = true, sqlType = 4)
    public int getWujiangId() {
        return this.wujiangId;
    }

    @PropertyDescription(persistant = true)
    public void setWujiangId(int wujiangId) {
        this.wujiangId = wujiangId;
    }

    @PropertyDescription(persistant = true, sqlType = 4)
    public int getVersion() {
        return this.version;
    }

    @PropertyDescription(persistant = true)
    public void setVersion(int version) {
        this.version = version;
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
    public int getIndex() {
        return this.index;
    }

    @PropertyDescription(persistant = true)
    public void setIndex(int index) {
        this.index = index;
    }

    public boolean isInit() {
        return this.init;
    }

    public void setInit(boolean init) {
        this.init = init;
    }
}