package com.saille.milan;

import com.saille.core.BaseEntity;
import com.sinitek.dao.domain.PropertyDescription;
import com.sinitek.dao.domain.ClassDescription;
import java.sql.Types;
@ClassDescription(table = "MATCHPLAYER")
public class MatchPlayer extends BaseEntity {
    private int matchId;
    private int playerId;
    private int substitude;
    private int onTime;
    private int offTime;
    private int goal;
    private int yellowCard;
    private int redCard;
    private int ownGoal;

    @PropertyDescription(persistant = true, sqlType = Types.INTEGER)
    public int getMatchId() {
        return this.matchId;
    }

    @PropertyDescription(persistant = true)
    public void setMatchId(int matchId) {
        this.matchId = matchId;
    }
    @PropertyDescription(persistant = true, sqlType = Types.INTEGER)
    public int getPlayerId() {
        return this.playerId;
    }

    @PropertyDescription(persistant = true)
    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }
    @PropertyDescription(persistant = true, sqlType = Types.INTEGER)
    public int getSubstitude() {
        return this.substitude;
    }

    @PropertyDescription(persistant = true)
    public void setSubstitude(int substitude) {
        this.substitude = substitude;
    }
    @PropertyDescription(persistant = true, sqlType = Types.INTEGER)
    public int getOnTime() {
        return this.onTime;
    }

    @PropertyDescription(persistant = true)
    public void setOnTime(int onTime) {
        this.onTime = onTime;
    }
    @PropertyDescription(persistant = true, sqlType = Types.INTEGER)
    public int getOffTime() {
        return this.offTime;
    }

    @PropertyDescription(persistant = true)
    public void setOffTime(int offTime) {
        this.offTime = offTime;
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
    public int getYellowCard() {
        return this.yellowCard;
    }

    @PropertyDescription(persistant = true)
    public void setYellowCard(int yellowCard) {
        this.yellowCard = yellowCard;
    }
    @PropertyDescription(persistant = true, sqlType = Types.INTEGER)
    public int getRedCard() {
        return this.redCard;
    }

    @PropertyDescription(persistant = true)
    public void setRedCard(int redCard) {
        this.redCard = redCard;
    }
    @PropertyDescription(persistant = true, sqlType = Types.INTEGER)
    public int getOwnGoal() {
        return this.ownGoal;
    }

    @PropertyDescription(persistant = true)
    public void setOwnGoal(int ownGoal) {
        this.ownGoal = ownGoal;
    }
}
