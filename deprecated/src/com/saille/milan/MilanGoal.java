package com.saille.milan;

import com.saille.core.BaseEntity;
import com.sinitek.dao.domain.PropertyDescription;
import com.sinitek.dao.domain.ClassDescription;
import java.sql.Types;
@ClassDescription(table = "MILANGOAL")
public class MilanGoal extends BaseEntity {
    private int matchId;
    private int minute;
    private int playerId;
    private String playerName;

    @PropertyDescription(persistant = true, sqlType = Types.INTEGER)
    public int getMatchId() {
        return this.matchId;
    }

    @PropertyDescription(persistant = true)
    public void setMatchId(int matchId) {
        this.matchId = matchId;
    }
    @PropertyDescription(persistant = true, sqlType = Types.INTEGER)
    public int getMinute() {
        return this.minute;
    }

    @PropertyDescription(persistant = true)
    public void setMinute(int minute) {
        this.minute = minute;
    }
    @PropertyDescription(persistant = true, sqlType = Types.INTEGER)
    public int getPlayerId() {
        return this.playerId;
    }

    @PropertyDescription(persistant = true)
    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }
    @PropertyDescription(persistant = true, sqlType = Types.VARCHAR)
    public String getPlayerName() {
        return this.playerName;
    }

    @PropertyDescription(persistant = true)
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }
}
