package com.saille.newBBS.telnet;

public class Screenline {
    private int oldlen;
    private int len;
    private int mode;
    private int smod;
    private int emod;
    private char sso;
    private int eso;
    private char[] data = new char[256];

    public int getOldlen() {
        return this.oldlen;
    }

    public void setOldlen(int oldlen) {
        this.oldlen = oldlen;
    }

    public int getLen() {
        return this.len;
    }

    public void setLen(int len) {
        this.len = len;
    }

    public int getMode() {
        return this.mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    public int getSmod() {
        return this.smod;
    }

    public void setSmod(int smod) {
        this.smod = smod;
    }

    public int getEmod() {
        return this.emod;
    }

    public void setEmod(int emod) {
        this.emod = emod;
    }

    public char getSso() {
        return this.sso;
    }

    public void setSso(char sso) {
        this.sso = sso;
    }

    public int getEso() {
        return this.eso;
    }

    public void setEso(int eso) {
        this.eso = eso;
    }

    public char[] getData() {
        return this.data;
    }

    public void setData(char[] data) {
        this.data = data;
    }
}