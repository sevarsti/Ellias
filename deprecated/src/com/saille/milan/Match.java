package com.saille.milan;

import com.sinitek.dao.domain.IdEntity;
import com.sinitek.dao.domain.ClassDescription;
import com.sinitek.dao.domain.PropertyDescription;

import java.sql.Types;

/**
 * Created by IntelliJ IDEA.
 * User: Ellias
 * Date: 2013-5-13
 * Time: 12:41:57
 * To change this template use File | Settings | File Templates.
 */
@ClassDescription(table = "MIL_MATCH")
public class Match extends IdEntity {
    public final static int TYPE_LEAGUE = 1; //联赛
    public final static int TYPE_CUP = 2; //杯赛
    public final static int TYPE_ECC = 3; //欧冠
    public final static int TYPE_ES = 4; //欧洲超级杯
    public final static int TYPE_IS = 5; //意大利超级杯

    public final static int PLACE_HOME = 1; //主场
    public final static int PLACE_AWAY = 2; //客场

    private int date; //比赛时间
    private int against; //对手
    private String place; //比赛地点
    private int placeType; //主客场

    private int goal; //米兰进球
    private int goalAgainst; //米兰丢球

    @PropertyDescription(persistant = true, sqlType = Types.INTEGER)
    public int getDate() {
        return date;
    }

    @PropertyDescription(persistant = true)
    public void setDate(int date) {
        this.date = date;
    }

    @PropertyDescription(persistant = true, sqlType = Types.INTEGER)
    public int getAgainst() {
        return against;
    }

    @PropertyDescription(persistant = true)
    public void setAgainst(int against) {
        this.against = against;
    }

    @PropertyDescription(persistant = true, sqlType = Types.VARCHAR)
    public String getPlace() {
        return place;
    }

    @PropertyDescription(persistant = true)
    public void setPlace(String place) {
        this.place = place;
    }

    @PropertyDescription(persistant = true, sqlType = Types.INTEGER)
    public int getPlaceType() {
        return placeType;
    }

    @PropertyDescription(persistant = true)
    public void setPlaceType(int placeType) {
        this.placeType = placeType;
    }

    @PropertyDescription(persistant = true, sqlType = Types.INTEGER)
    public int getGoal() {
        return goal;
    }

    @PropertyDescription(persistant = true)
    public void setGoal(int goal) {
        this.goal = goal;
    }

    @PropertyDescription(persistant = true, sqlType = Types.INTEGER)
    public int getGoalAgainst() {
        return goalAgainst;
    }

    @PropertyDescription(persistant = true)
    public void setGoalAgainst(int goalAgainst) {
        this.goalAgainst = goalAgainst;
    }
}
