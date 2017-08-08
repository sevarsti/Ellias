package com.saille.milan;

import com.saille.core.BaseEntity;
import com.sinitek.dao.domain.PropertyDescription;
import com.sinitek.dao.domain.ClassDescription;
import java.sql.Types;
@ClassDescription(table = "MILANMATCH")
public class MilanMatch extends BaseEntity {
    private String against;
    private String statium;
    private String city;
    private String type;
    private String round;
    private int goal;
    private int goaled;
    private int year;
    private int date;
    private int homeawy;
    private int time;

    @PropertyDescription(persistant = true, sqlType = Types.VARCHAR)
    public String getAgainst() {
        return this.against;
    }

    @PropertyDescription(persistant = true)
    public void setAgainst(String against) {
        this.against = against;
    }
    @PropertyDescription(persistant = true, sqlType = Types.VARCHAR)
    public String getStatium() {
        return this.statium;
    }

    @PropertyDescription(persistant = true)
    public void setStatium(String statium) {
        this.statium = statium;
    }
    @PropertyDescription(persistant = true, sqlType = Types.VARCHAR)
    public String getCity() {
        return this.city;
    }

    @PropertyDescription(persistant = true)
    public void setCity(String city) {
        this.city = city;
    }
    @PropertyDescription(persistant = true, sqlType = Types.VARCHAR)
    public String getType() {
        return this.type;
    }

    @PropertyDescription(persistant = true)
    public void setType(String type) {
        this.type = type;
    }
    @PropertyDescription(persistant = true, sqlType = Types.VARCHAR)
    public String getRound() {
        return this.round;
    }

    @PropertyDescription(persistant = true)
    public void setRound(String round) {
        this.round = round;
    }
    @PropertyDescription(persistant = true, sqlType = Types.INTEGER)
    public int getGoal() {
        return this.goal;
    }

    @PropertyDescription(persistant = true)
    public void setGoal(int goal) {
        this.goal = goal;
    }
    @PropertyDescription(persistant = true, sqlType = Types.INTEGER)
    public int getGoaled() {
        return this.goaled;
    }

    @PropertyDescription(persistant = true)
    public void setGoaled(int goaled) {
        this.goaled = goaled;
    }
    @PropertyDescription(persistant = true, sqlType = Types.INTEGER)
    public int getYear() {
        return this.year;
    }

    @PropertyDescription(persistant = true)
    public void setYear(int year) {
        this.year = year;
    }
    @PropertyDescription(persistant = true, sqlType = Types.INTEGER)
    public int getDate() {
        return this.date;
    }

    @PropertyDescription(persistant = true)
    public void setDate(int date) {
        this.date = date;
    }

    @PropertyDescription(persistant = true, sqlType = Types.INTEGER)
    public int getHomeawy() {
        return this.homeawy;
    }

    @PropertyDescription(persistant = true)
    public void setHomeawy(int homeawy) {
        this.homeawy = homeawy;
    }

    @PropertyDescription(persistant = true, sqlType = Types.INTEGER)
    public int getTime() {
        return time;
    }

    @PropertyDescription(persistant = true)
    public void setTime(int time) {
        this.time = time;
    }
}
