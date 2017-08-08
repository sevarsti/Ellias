package com.saille.bwdl.util;

import com.saille.bwdl.BwdlConstant;
import com.saille.bwdl.ChengShi;
import com.saille.bwdl.FangJu;
import com.saille.bwdl.ShiLi;
import com.saille.bwdl.WuJiang;
import com.saille.bwdl.WuQi;
import com.saille.bwdl.service.BwdlService;
import com.saille.bwdl.service.BwdlSettingService;
import com.saille.bwdl.service.ServiceHelper;

import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;

public class BwdlUtils {
    private static final Logger LOGGER = Logger.getLogger(BwdlUtils.class);

    public static ChengShi getChengShi(int id) {
        BwdlService service = ServiceHelper.getBwdlService();
        return service.getChengShi(id);
    }

    public static String getBingZhong(int id) {
        String[] types = BwdlConstant.TYPE_BINGZHONG;
        if((types == null) || (types.length == 0)) {
            return "??";
        }
        if(id < 0) {
            return "??";
        }
        if(id > types.length) {
            return "??";
        }
        return types[id];
    }

    public static String getWuQiType(int id) {
        String[] types = BwdlConstant.TYPE_WUQI;
        if((types == null) || (types.length == 0)) {
            return "??";
        }
        if(id < 0) {
            return "??";
        }
        if(id > types.length) {
            return "??";
        }
        return types[id];
    }

    public static WuQi getWuQi(int id) {
        BwdlService service = ServiceHelper.getBwdlService();
        return service.getWuQi(id);
    }

    public static FangJu getFangJu(int id) {
        BwdlService service = ServiceHelper.getBwdlService();
        return service.getFangJu(id);
    }

    public static List getAllFangJus(int version) {
        BwdlService service = ServiceHelper.getBwdlService();
        return service.findAllFangJus(version);
    }

    public static List getAllWuQis(int version) {
        BwdlService service = ServiceHelper.getBwdlService();
        return service.findAllWuQis(version);
    }

    public static List getAllBingZhongs() {
        return Arrays.asList(BwdlConstant.TYPE_BINGZHONG);
    }

    public static List getAllChengShis(int versioin) {
        BwdlService service = ServiceHelper.getBwdlService();
        return service.findAllChengShis(versioin);
    }

    public static List<WuJiang> loadWuJiangsByChengshi(int chengshiId) {
        BwdlService service = ServiceHelper.getBwdlService();
        return service.findWuJiangsByChengShi(chengshiId);
    }

    public static List getAllWuJiangsInit(int version) {
        BwdlService service = ServiceHelper.getBwdlService();
        return service.findAllWuJiangsInit(version);
    }

    public static List getAllShiLisInit(int version) {
        BwdlService service = ServiceHelper.getBwdlService();
        return service.findAllShilisInit(version);
    }

    public static ShiLi getShiLi(int shiliId) {
        BwdlService service = ServiceHelper.getBwdlService();
        return service.getShiLiInit(shiliId);
    }

    public static Object[] preAction(String action, int wujiangId, int chengshiId) {
        BwdlService service = ServiceHelper.getBwdlService();
        BwdlSettingService settingService = ServiceHelper.getSettingService();
        if(BwdlConstant.ACTION[0].equals(action)) {
            WuJiang wujiang = service.getWuJiang(wujiangId);
            ChengShi chengshi = service.getChengShi(chengshiId);
            if(chengshi.getTudi() >= settingService.getTudiMax(1)) {
                return new Object[]{Integer.valueOf(-1), "无需再为此花费银两"};
            }
            int rand = 0;
            if(wujiang.getZhi() < 51) {
                rand = CalcUtils.getRandom(0, 4) * 2 + 10;
            } else if(wujiang.getZhi() < 81) {
                rand = CalcUtils.getRandom(0, 4) * 2 + 14;
            } else {
                rand = CalcUtils.getRandom(0, 4) * 2 + 18;
            }
            for(int i = 0; i < CalcUtils.KAIFAMONEY.length; i++) {
                if(CalcUtils.KAIFAMONEY[i] == rand) {
                    return new Object[]{Integer.valueOf(rand), CalcUtils.TUDIDESC[i]};
                }
            }
        } else
        if((BwdlConstant.ACTION[1].equals(action)) || (BwdlConstant.ACTION[2].equals(action)) || (BwdlConstant.ACTION[3].equals(action)) || (BwdlConstant.ACTION[4].equals(action)) || (BwdlConstant.ACTION[5].equals(action)) || (BwdlConstant.ACTION[6].equals(action)) || (BwdlConstant.ACTION[7].equals(action)) || (BwdlConstant.ACTION[8].equals(action)) || (BwdlConstant.ACTION[9].equals(action)) || (BwdlConstant.ACTION[10].equals(action)) || (!BwdlConstant.ACTION[11].equals(action))) {
            ;
        }
        return null;
    }

    public static Object[] doAction(String action, int wujiangId, int chengshiId, int jin) {
        BwdlService service = ServiceHelper.getBwdlService();
        BwdlSettingService settingService = ServiceHelper.getSettingService();
        if(BwdlConstant.ACTION[0].equals(action)) {
            String desc = CalcUtils.kaifaTudi(wujiangId, chengshiId, jin);
            ChengShi chengshi = service.getChengShi(chengshiId);
            LOGGER.info(desc);
            return new Object[]{desc, chengshi};
        }
        return null;
    }
}