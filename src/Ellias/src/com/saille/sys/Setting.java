package com.saille.sys;

import java.util.Date;
import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: ELLIAS
 * Date: 2015-1-13
 * Time: 23:15:36
 * To change this template use File | Settings | File Templates.
 */
public class Setting implements Serializable {
    public final static int TYPE_INT = 1;
    public final static int TYPE_STRING = 2;
    public final static int TYPE_NUMBER = 3;
    public final static int TYPE_DATE = 4;
    
    private String setting;
    private String group;
    private String name;
    private String memo;
    private int type;
    private Integer intValue;
    private Double numberValue;
    private String strValue;
    private Date dateValue;
    private String pattern;

    public String getSetting() {
        return setting;
    }

    public void setSetting(String setting) {
        this.setting = setting;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Integer getIntValue() {
        return intValue;
    }

    public void setIntValue(Integer intValue) {
        this.intValue = intValue;
    }

    public Double getNumberValue() {
        return numberValue;
    }

    public void setNumberValue(Double numberValue) {
        this.numberValue = numberValue;
    }

    public String getStrValue() {
        return strValue;
    }

    public void setStrValue(String strValue) {
        this.strValue = strValue;
    }

    public Date getDateValue() {
        return dateValue;
    }

    public void setDateValue(Date dateValue) {
        this.dateValue = dateValue;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }
}
