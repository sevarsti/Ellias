package com.saille.bbs.yssy;

public class Board {
    private int id;
    private String boardName;
    private String chineseName;

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

    public String getChineseName() {
        return this.chineseName;
    }

    public void setChineseName(String chineseName) {
        this.chineseName = chineseName;
    }
}