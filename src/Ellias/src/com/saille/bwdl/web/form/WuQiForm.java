package com.saille.bwdl.web.form;

import com.saille.bwdl.WuQi;

import java.util.List;

import org.apache.struts.action.ActionForm;

public class WuQiForm extends ActionForm {
    private List<WuQi> wuqis;
    private int wuqiId;
    private String name;
    private int gongji;
    private int type;
    private int weight;
    private int price;
    private int version;

    public List<WuQi> getWuqis() {
        return this.wuqis;
    }

    public void setWuqis(List<WuQi> wuqis) {
        this.wuqis = wuqis;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGongji() {
        return this.gongji;
    }

    public void setGongji(int gongji) {
        this.gongji = gongji;
    }

    public int getWuqiId() {
        return this.wuqiId;
    }

    public void setWuqiId(int wuqiId) {
        this.wuqiId = wuqiId;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getWeight() {
        return this.weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getPrice() {
        return this.price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getVersion() {
        return this.version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}