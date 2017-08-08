package com.saille.milan.form;

import com.saille.milan.MatchPlayer;
import org.apache.struts.action.ActionForm;
import java.util.List;

public class MatchPlayerForm extends ActionForm {
    private List<MatchPlayer> matchPlayers;
    private String msg;
    private int id;
    private int matchId;
    private int playerId;
    private int substitude;
    private int onTime;
    private int offTime;
    private int goal;
    private int yellowCard;
    private int redCard;
    private int ownGoal;

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

    public List<MatchPlayer> getMatchPlayers() {
        return this.matchPlayers;
    }

    public void setMatchPlayers(List<MatchPlayer> list) {
        this.matchPlayers = list;
    }

    public int getMatchId() {
        return this.matchId;
    }

    public void setMatchId(int matchId) {
        this.matchId = matchId;
    }

    public int getPlayerId() {
        return this.playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public int getSubstitude() {
        return this.substitude;
    }

    public void setSubstitude(int substitude) {
        this.substitude = substitude;
    }

    public int getOnTime() {
        return this.onTime;
    }

    public void setOnTime(int onTime) {
        this.onTime = onTime;
    }

    public int getOffTime() {
        return this.offTime;
    }

    public void setOffTime(int offTime) {
        this.offTime = offTime;
    }

    public int getGoal() {
        return this.goal;
    }

    public void setGoal(int goal) {
        this.goal = goal;
    }

    public int getYellowCard() {
        return this.yellowCard;
    }

    public void setYellowCard(int yellowCard) {
        this.yellowCard = yellowCard;
    }

    public int getRedCard() {
        return this.redCard;
    }

    public void setRedCard(int redCard) {
        this.redCard = redCard;
    }

    public int getOwnGoal() {
        return this.ownGoal;
    }

    public void setOwnGoal(int ownGoal) {
        this.ownGoal = ownGoal;
    }
}
