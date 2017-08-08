package com.saille.html.travian;

import java.util.ArrayList;
import java.util.List;

public class TravianMain {
    private static TravianMain instance;
    private List<TravianUserMain> users;

    public static TravianMain getInstance() {
        if(instance == null) {
            instance = new TravianMain();
            instance.users = new ArrayList();
        }

        return instance;
    }

    public void addUser(String userid, String pwd) {
        if(this.users == null) {
            this.users = new ArrayList();
        }
        this.users.add(new TravianUserMain(userid, pwd));
    }

    public void deleteUser(String userid) {
        if(this.users == null) {
            return;
        }
        for(TravianUserMain user : this.users) {
            if(user.getUserid().equals(userid)) {
                this.users.remove(user);
                break;
            }
        }
    }

    public TravianUserMain getUser(String userid) {
        if(this.users == null) {
            return null;
        }
        for(TravianUserMain user : this.users) {
            if(user.getUserid().equals(userid)) {
                return user;
            }
        }
        return null;
    }

    public List<TravianUserMain> getUsers() {
        return this.users;
    }

    public void setUsers(List<TravianUserMain> users) {
        this.users = users;
    }
}