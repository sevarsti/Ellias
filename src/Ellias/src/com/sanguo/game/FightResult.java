package com.sanguo.game;

public class FightResult {
    private int id;
    private int fightType;
    private int person1;
    private int person2;
    private int result;

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFightType() {
        return this.fightType;
    }

    public void setFightType(int fightType) {
        this.fightType = fightType;
    }

    public int getPerson1() {
        return this.person1;
    }

    public void setPerson1(int person1) {
        this.person1 = person1;
    }

    public int getPerson2() {
        return this.person2;
    }

    public void setPerson2(int person2) {
        this.person2 = person2;
    }

    public int getResult() {
        return this.result;
    }

    public void setResult(int result) {
        this.result = result;
    }
}