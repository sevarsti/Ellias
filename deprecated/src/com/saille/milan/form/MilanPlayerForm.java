package com.saille.milan.form;

import com.saille.milan.MilanPlayer;
import org.apache.struts.action.ActionForm;
import java.util.List;

public class MilanPlayerForm extends ActionForm {
    private List<MilanPlayer> milanPlayers;
    private String msg;
    private int id;
    private String name;
    private String nationality;
    private int birth;

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<MilanPlayer> getMilanPlayers() {
        return this.milanPlayers;
    }

    public void setMilanPlayers(List<MilanPlayer> list) {
        this.milanPlayers = list;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNationality() {
        return this.nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public int getBirth() {
        return this.birth;
    }

    public void setBirth(int birth) {
        this.birth = birth;
    }
}
