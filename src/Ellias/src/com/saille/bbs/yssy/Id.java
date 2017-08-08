package com.saille.bbs.yssy;

public class Id {
    private String id;
    private String nick;
    private int loginTimes;
    private int age;
    private String lastLoginTime;
    private String ip;
    private int life;
    private int post;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNick() {
        return this.nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public int getLoginTimes() {
        return this.loginTimes;
    }

    public void setLoginTimes(int loginTimes) {
        this.loginTimes = loginTimes;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getLastLoginTime() {
        return this.lastLoginTime;
    }

    public void setLastLoginTime(String lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getIp() {
        return this.ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getLife() {
        return this.life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public int getPost() {
        return this.post;
    }

    public void setPost(int post) {
        this.post = post;
    }
}