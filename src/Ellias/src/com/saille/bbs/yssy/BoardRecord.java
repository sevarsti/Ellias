package com.saille.bbs.yssy;

public class BoardRecord {
    private int id;
    private String boardName;
    private int avgTime;
    private int times;
    private double totalTime;
    private int date;
    private int index;

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBoardName() {
        return this.boardName;
    }

    public void setBoardName(String boardName) {
        this.boardName = boardName;
    }

    public int getAvgTime() {
        return this.avgTime;
    }

    public void setAvgTime(int avgTime) {
        this.avgTime = avgTime;
    }

    public int getTimes() {
        return this.times;
    }

    public void setTimes(int times) {
        this.times = times;
    }

    public double getTotalTime() {
        return this.totalTime;
    }

    public void setTotalTime(double totalTime) {
        this.totalTime = totalTime;
    }

    public int getDate() {
        return this.date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public int getIndex() {
        return this.index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}