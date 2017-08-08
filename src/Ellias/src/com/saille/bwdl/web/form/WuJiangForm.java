package com.saille.bwdl.web.form;

import com.saille.bwdl.WuJiang;

import java.util.List;

import org.apache.struts.action.ActionForm;

public class WuJiangForm extends ActionForm {
    private List<WuJiang> wujiangs;
    private int wujiangId;
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
    private int version;

    public List<WuJiang> getWujiangs() {
        return this.wujiangs;
    }

    public void setWujiangs(List<WuJiang> wujiangs) {
        this.wujiangs = wujiangs;
    }

    public int getWujiangId() {
        return this.wujiangId;
    }

    public void setWujiangId(int wujiangId) {
        this.wujiangId = wujiangId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTi() {
        return this.ti;
    }

    public void setTi(int ti) {
        this.ti = ti;
    }

    public int getWu() {
        return this.wu;
    }

    public void setWu(int wu) {
        this.wu = wu;
    }

    public int getZhi() {
        return this.zhi;
    }

    public void setZhi(int zhi) {
        this.zhi = zhi;
    }

    public int getZhong() {
        return this.zhong;
    }

    public void setZhong(int zhong) {
        this.zhong = zhong;
    }

    public int getDe() {
        return this.de;
    }

    public void setDe(int de) {
        this.de = de;
    }

    public int getJing() {
        return this.jing;
    }

    public void setJing(int jing) {
        this.jing = jing;
    }

    public int getBingzhong() {
        return this.bingzhong;
    }

    public void setBingzhong(int bingzhong) {
        this.bingzhong = bingzhong;
    }

    public int getWuqi() {
        return this.wuqi;
    }

    public void setWuqi(int wuqi) {
        this.wuqi = wuqi;
    }

    public int getFangju() {
        return this.fangju;
    }

    public void setFangju(int fangju) {
        this.fangju = fangju;
    }

    public int getShibing() {
        return this.shibing;
    }

    public void setShibing(int shibing) {
        this.shibing = shibing;
    }

    public int getVersion() {
        return this.version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}