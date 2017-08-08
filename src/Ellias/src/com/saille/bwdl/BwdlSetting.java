package com.saille.bwdl;

import com.sinitek.dao.domain.ClassDescription;
import com.sinitek.dao.domain.PropertyDescription;

import java.util.Date;

@ClassDescription(table = "SETTING")
public class BwdlSetting {
    private String setting;
    private Integer intValue;
    private String strValue;
    private Double numberValue;
    private Date dateValue;
    private int version;
    public static String TUDI_MAX = "TUDI_MAX";
    public static String CHANYE_MAX = "CHANYE_MAX";
    public static String RENKOU_MAX = "RENKOU_MAX";
    public static String DUSHU_MAX = "DUSHU_MAX";
    public static String DUSHU_MID = "DUSHU_MID";
    public static String DUSHU_MIN = "DUSHU_MIN";
    public static String JIN_MAX = "JIN_MAX";
    public static String MI_MAX = "MI_MAX";
    public static String BAO_MAX = "BAO_MAX";
    public static String TONGZHI_MAX = "TONGZHI_MAX";
    public static String WUJIANG_IN_CHENGSHI_MAX = "WUJIANG_IN_CHENGSHI_MAX";

    @PropertyDescription(persistant = true, sqlType = 12, primaryKey = true)
    public String getSetting() {
        return this.setting;
    }

    @PropertyDescription(persistant = true, primaryKey = true)
    public void setSetting(String setting) {
        this.setting = setting;
    }

    @PropertyDescription(persistant = true, sqlType = 4)
    public Integer getIntValue() {
        return this.intValue;
    }

    @PropertyDescription(persistant = true)
    public void setIntValue(Integer intValue) {
        this.intValue = intValue;
    }

    @PropertyDescription(persistant = true, sqlType = 12)
    public String getStrValue() {
        return this.strValue;
    }

    @PropertyDescription(persistant = true)
    public void setStrValue(String strValue) {
        this.strValue = strValue;
    }

    @PropertyDescription(persistant = true, sqlType = 6)
    public Double getNumberValue() {
        return this.numberValue;
    }

    @PropertyDescription(persistant = true)
    public void setNumberValue(Double numberValue) {
        this.numberValue = numberValue;
    }

    @PropertyDescription(persistant = true, sqlType = 93)
    public Date getDateValue() {
        return this.dateValue;
    }

    @PropertyDescription(persistant = true)
    public void setDateValue(Date dateValue) {
        this.dateValue = dateValue;
    }

    @PropertyDescription(persistant = true, sqlType = 4)
    public int getVersion() {
        return this.version;
    }

    @PropertyDescription(persistant = true)
    public void setVersion(int version) {
        this.version = version;
    }
}