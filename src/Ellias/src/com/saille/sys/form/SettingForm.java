package com.saille.sys.form;

import org.apache.struts.action.ActionForm;
import com.saille.sys.Setting;

import java.util.List;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: ELLIAS
 * Date: 2015-1-24
 * Time: 18:00:15
 * To change this template use File | Settings | File Templates.
 */
public class SettingForm extends ActionForm {
    private String msg;

    private List<Setting> settings;
    private String settingName;
    private String group;
    private String name;
    private String memo;
    private int type;
    private Integer intValue;
    private Double numberValue;
    private String strValue;
    private Date dateValue;
    private String pattern;
    private String settingValue;

    public List<Setting> getSettings() {
        return settings;
    }

    public void setSettings(List<Setting> settings) {
        this.settings = settings;
    }

    public String getSettingName() {
        return settingName;
    }

    public void setSettingName(String settingName) {
        this.settingName = settingName;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
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

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getSettingValue() {
        return settingValue;
    }

    public void setSettingValue(String settingValue) {
        this.settingValue = settingValue;
    }
}
