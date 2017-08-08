package com.saille.bwdl.web.form;

import com.saille.bwdl.ChengShi;

import java.util.List;

import org.apache.struts.action.ActionForm;

public class ChengShiForm extends ActionForm {
    private List<ChengShi> chengshis;
    private int chengshiId;
    private String name;
    private int jin;
    private int mi;
    private int bao;
    private int tongzhi;
    private int tudi;
    private int chanye;
    private int renkou;
    private int fangzai;
    private int version;
    private int shili;
    private int locationX;
    private int locationY;
    private int[] wujiangIds;

    public List<ChengShi> getChengshis() {
        return this.chengshis;
    }

    public void setChengshis(List<ChengShi> chengshis) {
        this.chengshis = chengshis;
    }

    public int getChengshiId() {
        return this.chengshiId;
    }

    public void setChengshiId(int chengshiId) {
        this.chengshiId = chengshiId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getJin() {
        return this.jin;
    }

    public void setJin(int jin) {
        this.jin = jin;
    }

    public int getMi() {
        return this.mi;
    }

    public void setMi(int mi) {
        this.mi = mi;
    }

    public int getBao() {
        return this.bao;
    }

    public void setBao(int bao) {
        this.bao = bao;
    }

    public int getTongzhi() {
        return this.tongzhi;
    }

    public void setTongzhi(int tongzhi) {
        this.tongzhi = tongzhi;
    }

    public int getTudi() {
        return this.tudi;
    }

    public void setTudi(int tudi) {
        this.tudi = tudi;
    }

    public int getChanye() {
        return this.chanye;
    }

    public void setChanye(int chanye) {
        this.chanye = chanye;
    }

    public int getRenkou() {
        return this.renkou;
    }

    public void setRenkou(int renkou) {
        this.renkou = renkou;
    }

    public int getFangzai() {
        return this.fangzai;
    }

    public void setFangzai(int fangzai) {
        this.fangzai = fangzai;
    }

    public int getVersion() {
        return this.version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public int[] getWujiangIds() {
        return this.wujiangIds;
    }

    public void setWujiangIds(int[] wujiangIds) {
        this.wujiangIds = wujiangIds;
    }

    public int getShili() {
        return this.shili;
    }

    public void setShili(int shili) {
        this.shili = shili;
    }

    public int getLocationX() {
        return this.locationX;
    }

    public void setLocationX(int locationX) {
        this.locationX = locationX;
    }

    public int getLocationY() {
        return this.locationY;
    }

    public void setLocationY(int locationY) {
        this.locationY = locationY;
    }
}