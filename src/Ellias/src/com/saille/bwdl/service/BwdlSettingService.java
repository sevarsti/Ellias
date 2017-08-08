package com.saille.bwdl.service;

import com.saille.bwdl.BwdlSetting;
import com.saille.bwdl.dao.BwdlSettingDao;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.apache.log4j.Logger;

public class BwdlSettingService {
    private final Logger LOGGER = Logger.getLogger(getClass());
    private BwdlSettingDao dao;

    public int getTudiMax(int version) {
        return getIntValue(BwdlSetting.TUDI_MAX, 999, version);
    }

    public int getChanyeMax(int version) {
        return getIntValue(BwdlSetting.CHANYE_MAX, 999, version);
    }

    public int getRenkouMax(int version) {
        return getIntValue(BwdlSetting.RENKOU_MAX, 9999, version);
    }

    public int getDushuMax(int version) {
        return getIntValue(BwdlSetting.DUSHU_MAX, 80, version);
    }

    public int getDushuMid(int version) {
        return getIntValue(BwdlSetting.DUSHU_MID, 60, version);
    }

    public int getDushuMin(int version) {
        return getIntValue(BwdlSetting.DUSHU_MIN, 30, version);
    }

    public int getJinMax(int version) {
        return getIntValue(BwdlSetting.JIN_MAX, 999, version);
    }

    public int getMiMax(int version) {
        return getIntValue(BwdlSetting.MI_MAX, 999, version);
    }

    public int getBaoMax(int version) {
        return getIntValue(BwdlSetting.BAO_MAX, 99, version);
    }

    public int getTongzhiMax(int version) {
        return getIntValue(BwdlSetting.TONGZHI_MAX, 100, version);
    }

    public int getWuJiangInChengShiMax(int version) {
        return getIntValue(BwdlSetting.WUJIANG_IN_CHENGSHI_MAX, 99, version);
    }

    private int getIntValue(String set, int defaultValue, int version) {
        try {
            BwdlSetting setting = this.dao.getSetting(set, version);
            if(intValueExist(setting)) {
                return setting.getIntValue().intValue();
            }
        } catch(Exception ex) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            ex.printStackTrace(pw);
            this.LOGGER.error(sw.toString());
        }
        return defaultValue;
    }

    private boolean intValueExist(BwdlSetting setting) {
        return (setting != null) && (setting.getIntValue() != null);
    }

    public void setDao(BwdlSettingDao dao) {
        this.dao = dao;
    }
}