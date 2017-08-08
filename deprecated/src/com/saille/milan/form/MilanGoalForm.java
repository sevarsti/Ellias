package com.saille.milan.form;

import com.saille.milan.MilanGoal;
import org.apache.struts.action.ActionForm;
import java.util.List;

public class MilanGoalForm extends ActionForm {
    private List<MilanGoal> milanGoals;
    private String msg;
    private int id;
    private int matchId;
    private int minute;
    private int playerId;
    private String playerName;

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

    public List<MilanGoal> getMilanGoals() {
        return this.milanGoals;
    }

    public void setMilanGoals(List<MilanGoal> list) {
        this.milanGoals = list;
    }

    public int getMatchId() {
        return this.matchId;
    }

    public void setMatchId(int matchId) {
        this.matchId = matchId;
    }

    public int getMinute() {
        return this.minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public int getPlayerId() {
        return this.playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public String getPlayerName() {
        return this.playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }
}
