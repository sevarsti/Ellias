package com.saille.bwdl.web.form;

import com.saille.bwdl.FangJu;

import java.util.List;

import org.apache.struts.action.ActionForm;

public class FangJuForm extends ActionForm {
    private List<FangJu> fangjus;
    private int fangjuId;
    private String name;
    private int fangyu;
    private int weight;
    private int price;
    private int version;

    public List<FangJu> getFangjus() {
        return this.fangjus;
    }

    public void setFangjus(List<FangJu> fangjus) {
        this.fangjus = fangjus;
    }

    public int getFangjuId() {
        return this.fangjuId;
    }

    public void setFangjuId(int fangjuId) {
        this.fangjuId = fangjuId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFangyu() {
        return this.fangyu;
    }

    public void setFangyu(int fangyu) {
        this.fangyu = fangyu;
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