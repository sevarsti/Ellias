package com.saille.rm.form;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

/**
 * Created by IntelliJ IDEA.
 * User: Ellias
 * Date: 2015-4-15
 * Time: 13:57:43
 * To change this template use File | Settings | File Templates.
 */
public class ChangeBpmForm extends ActionForm {
    private String msg;

    private String song;
    private double ratio;

    private FormFile cutmp3;
    private FormFile cutimd;
    private int begin;
    private int end;

    public String getSong() {
        return song;
    }

    public void setSong(String song) {
        this.song = song;
    }

    public double getRatio() {
        return ratio;
    }

    public void setRatio(double ratio) {
        this.ratio = ratio;
    }

    public FormFile getCutmp3() {
        return cutmp3;
    }

    public void setCutmp3(FormFile cutmp3) {
        this.cutmp3 = cutmp3;
    }

    public FormFile getCutimd() {
        return cutimd;
    }

    public void setCutimd(FormFile cutimd) {
        this.cutimd = cutimd;
    }

    public int getBegin() {
        return begin;
    }

    public void setBegin(int begin) {
        this.begin = begin;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
