package com.saille.milan.form;

import com.saille.milan.MilanMatch;
import org.apache.struts.action.ActionForm;
import java.util.List;

public class MilanMatchForm extends ActionForm {
    private List<MilanMatch> milanMatchs;
    private String msg;
    private int id;
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

    private int[] playerId;
    private int[] substitude;
    private int[] onTime;
    private int[] offTime;
    private int[] goals;
    private int[] ownGoal;
    private int[] yellowCard;
    private int[] redCard;

    private int[] goalMinute;
    private int[] goalPlayerId;
    private String[] goalPlayerName;

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

    public List<MilanMatch> getMilanMatchs() {
        return this.milanMatchs;
    }

    public void setMilanMatchs(List<MilanMatch> list) {
        this.milanMatchs = list;
    }

    public String getAgainst() {
        return this.against;
    }

    public void setAgainst(String against) {
        this.against = against;
    }

    public String getStatium() {
        return this.statium;
    }

    public void setStatium(String statium) {
        this.statium = statium;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRound() {
        return this.round;
    }

    public void setRound(String round) {
        this.round = round;
    }

    public int getGoal() {
        return this.goal;
    }

    public void setGoal(int goal) {
        this.goal = goal;
    }

    public int getGoaled() {
        return this.goaled;
    }

    public void setGoaled(int goaled) {
        this.goaled = goaled;
    }

    public int getYear() {
        return this.year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getDate() {
        return this.date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public int getHomeawy() {
        return this.homeawy;
    }

    public void setHomeawy(int homeawy) {
        this.homeawy = homeawy;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int[] getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int[] playerId) {
        this.playerId = playerId;
    }

    public int[] getSubstitude() {
        return substitude;
    }

    public void setSubstitude(int[] substitude) {
        this.substitude = substitude;
    }

    public int[] getOnTime() {
        return onTime;
    }

    public void setOnTime(int[] onTime) {
        this.onTime = onTime;
    }

    public int[] getOffTime() {
        return offTime;
    }

    public void setOffTime(int[] offTime) {
        this.offTime = offTime;
    }

    public int[] getOwnGoal() {
        return ownGoal;
    }

    public void setOwnGoal(int[] ownGoal) {
        this.ownGoal = ownGoal;
    }

    public int[] getYellowCard() {
        return yellowCard;
    }

    public void setYellowCard(int[] yellowCard) {
        this.yellowCard = yellowCard;
    }

    public int[] getRedCard() {
        return redCard;
    }

    public void setRedCard(int[] redCard) {
        this.redCard = redCard;
    }

    public int[] getGoals() {
        return goals;
    }

    public void setGoals(int[] goals) {
        this.goals = goals;
    }

    public int[] getGoalMinute() {
        return goalMinute;
    }

    public void setGoalMinute(int[] goalMinute) {
        this.goalMinute = goalMinute;
    }

    public int[] getGoalPlayerId() {
        return goalPlayerId;
    }

    public void setGoalPlayerId(int[] goalPlayerId) {
        this.goalPlayerId = goalPlayerId;
    }

    public String[] getGoalPlayerName() {
        return goalPlayerName;
    }

    public void setGoalPlayerName(String[] goalPlayerName) {
        this.goalPlayerName = goalPlayerName;
    }
}
