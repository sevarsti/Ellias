package com.saille.bwdl;

import com.sinitek.dao.domain.ClassDescription;
import com.sinitek.dao.domain.PropertyDescription;

import java.util.Date;

@ClassDescription(table = "FANGJU")
public class FangJu {
    private int id;
    private String name;
    private int fangyu;
    private int weight;
    private int price;
    private Date updateTime;
    private int version;

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
    public int getFangyu() {
        return this.fangyu;
    }

    @PropertyDescription(persistant = true)
    public void setFangyu(int fangyu) {
        this.fangyu = fangyu;
    }

    @PropertyDescription(persistant = true, sqlType = 4)
    public int getWeight() {
        return this.weight;
    }

    @PropertyDescription(persistant = true)
    public void setWeight(int weight) {
        this.weight = weight;
    }

    @PropertyDescription(persistant = true, sqlType = 4)
    public int getPrice() {
        return this.price;
    }

    @PropertyDescription(persistant = true)
    public void setPrice(int price) {
        this.price = price;
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
}