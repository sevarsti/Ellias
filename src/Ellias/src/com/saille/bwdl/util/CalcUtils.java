package com.saille.bwdl.util;

import com.saille.bwdl.ChengShi;
import com.saille.bwdl.WuJiang;
import com.saille.bwdl.service.BwdlService;
import com.saille.bwdl.service.BwdlSettingService;
import com.saille.bwdl.service.ServiceHelper;

public class CalcUtils {
    public static int[] KAIFAMONEY = {10, 12, 14, 16, 18, 20, 22, 24, 26};
    public static String[] TUDIDESC = {"开垦荒芜之土地\n以便于耕作可否？\n需10两金", "砍伐森林\n以扩大农田可否？\n需12两金", "填埋沼泽\n以开发农田可否？\n需14两金", "假设水渠\n引河以灌溉可否？\n需16两金", "建造水坝\n蓄水以灌溉可否？\n需18两金", "重新种植田内\n受损之作物可否？\n需20两金", "施肥于作物\n使之长势更加可否？\n需22两金", "将更易用之农具\n授予农民可否？\n需24两金", "改良品种\n以培育丰产秧稻如何？\n需26两金"};
    public static String[] CHANYEDESC = {"将废弃之土地\n改建为工厂如何？\n需10两金", "为提升生产力\n雇佣劳动者如何？\n需12两金", "于城之中央\n建造市集如何？\n需14两金", "使用运货车\n输送商品怎样？\n需16两金", "挖掘矿山\n开采矿石如何？\n需18两金", "为开采更多矿石\n挖掘新矿山如何？\n需20两金", "邀请新工匠加入\n研习新技术如何？\n需22两金", "改良工匠使用\n之工具如何？\n需24两金", "召集工匠共同作业\n提升生产力如何？\n需26两金"};
    public static String[] RENKOUDESC = {"修整城中道路\n以便利交通如何？\n需10两金", "将城镇建得更为气派\n更适于居住如何？\n需12两金", "拓宽道路\n改善交通如何？\n需14两金", "为使城镇更适于居住\n建造广场如何？\n需16两金", "于城中空地\n建造房屋如何？\n需18两金", "为城里百姓\n建造集会场如何？\n需20两金", "为居住更多百姓\n扩建城镇如何？\n需22两金", "为维护城镇治安\n配置官员如何？\n需24两金", "削平各地之山岭\n建造房屋如何？\n需26两金"};

    public static int getRandom(int a, int b) {
        int min = Math.min(a, b);
        int max = Math.max(a, b);
        return (int) (Math.random() * (max - min + 1)) + min;
    }

    public static String kaifaTudi(int wujiangId, int chengshiId, int jin) {
        BwdlService service = ServiceHelper.getBwdlService();
        BwdlSettingService settingService = ServiceHelper.getSettingService();
        int maxTudi = settingService.getTudiMax(0);
        WuJiang wujiang = service.getWuJiang(wujiangId);
        ChengShi chengshi = service.getChengShi(chengshiId);
        if(chengshi.getTudi() >= maxTudi) {
            return "无需再为此花费银两";
        }
        if(chengshi.getJin() < jin) {
            return "如今此城内并无足够金钱\n请下达其他指令";
        }
        int rand = getRandom(10, 17);
        int value = jin * wujiang.getZhi() / 100 * rand / 10;
        int result = chengshi.getTudi() + value;
        if(result > maxTudi) {
            value = maxTudi - chengshi.getTudi();
        }
        chengshi.setJin(chengshi.getJin() - jin);
        if(value > 31) {
            chengshi.setTongzhi(chengshi.getTongzhi() + 2);
        } else {
            chengshi.setTongzhi(chengshi.getTongzhi() + 1);
        }
        int tongzhiMax = settingService.getTongzhiMax(0);
        if(chengshi.getTongzhi() > tongzhiMax) {
            chengshi.setTongzhi(tongzhiMax);
        }
        chengshi.setTudi(chengshi.getTudi() + value);
        service.saveChengShi(chengshi);
        return "土地值上升  " + value + "点";
    }

    public static int kaifaChanye(int wujiangId, int chengshiId, int jin) {
        BwdlService service = ServiceHelper.getBwdlService();
        BwdlSettingService settingService = ServiceHelper.getSettingService();
        int maxChanye = settingService.getChanyeMax(0);
        WuJiang wujiang = service.getWuJiang(wujiangId);
        ChengShi chengshi = service.getChengShi(chengshiId);
        if(chengshi.getChanye() >= maxChanye) {
            return 0;
        }
        int rand = getRandom(10, 17);
        int value = jin * wujiang.getZhi() * rand / 10;
        int result = chengshi.getChanye() + value;
        if(result > maxChanye) {
            value = maxChanye - chengshi.getChanye();
        }
        chengshi.setChanye(chengshi.getChanye() + value);
        service.saveChengShi(chengshi);
        return value;
    }

    public static int kaifaRenkou(int wujiangId, int chengshiId, int jin) {
        BwdlService service = ServiceHelper.getBwdlService();
        BwdlSettingService settingService = ServiceHelper.getSettingService();
        int maxRenkou = settingService.getRenkouMax(0);
        WuJiang wujiang = service.getWuJiang(wujiangId);
        ChengShi chengshi = service.getChengShi(chengshiId);
        if(chengshi.getRenkou() >= maxRenkou) {
            return 0;
        }
        int rand = getRandom(10, 17);
        int value = jin * wujiang.getZhi() * rand / 10 / 2;
        int result = chengshi.getRenkou() + value;
        if(result > maxRenkou) {
            value = maxRenkou - chengshi.getRenkou();
        }
        chengshi.setRenkou(chengshi.getRenkou() + value);
        service.saveChengShi(chengshi);
        return value;
    }

    public static int dushu(int wujiangId) {
        BwdlService service = ServiceHelper.getBwdlService();
        BwdlSettingService settingService = ServiceHelper.getSettingService();
        WuJiang wujiang = service.getWuJiang(wujiangId);
        int dushuMax = settingService.getDushuMax(0);
        int dushuMid = settingService.getDushuMid(0);
        int dushuMin = settingService.getDushuMin(0);
        if(wujiang.getZhi() >= dushuMax) {
            return 0;
        }
        int value = 0;
        if(wujiang.getZhi() > dushuMid) {
            value = getRandom(6, 10);
        } else if(wujiang.getZhi() > dushuMin) {
            value = getRandom(8, 12);
        } else {
            value = getRandom(5, 9);
        }
        wujiang.setZhi(wujiang.getZhi() + value);
        service.saveWuJiang(wujiang);
        return value;
    }

    public static String souji(int wujiangId, int chengShiId) {
        BwdlService service = ServiceHelper.getBwdlService();
        BwdlSettingService settingService = ServiceHelper.getSettingService();
        WuJiang wujiang = service.getWuJiang(wujiangId);
        ChengShi chengshi = service.getChengShi(chengShiId);

        String ret = null;

        int rand = getRandom(1, 8);
        int result = 0;
        if(wujiang.getDe() <= 40) {
            if(wujiang.getZhi() <= 40) {
                if(rand < 3) {
                    result = 1;
                } else {
                    result = 8;
                }
            } else if(wujiang.getZhi() <= 80) {
                if(rand < 3) {
                    result = 1;
                } else if(rand < 5) {
                    result = 2;
                } else {
                    result = 8;
                }
            } else if(rand < 2) {
                result = 2;
            } else if(rand < 4) {
                result = 3;
            } else if(rand < 7) {
                result = 4;
            } else {
                result = 8;
            }
        } else if(wujiang.getDe() <= 80) {
            if(wujiang.getZhi() <= 40) {
                if(rand < 4) {
                    result = 1;
                } else if(rand < 4) {
                    result = 2;
                } else if(rand < 6) {
                    result = 6;
                } else {
                    result = 8;
                }
            } else if(wujiang.getZhi() <= 80) {
                if(rand < 3) {
                    result = 2;
                } else if(rand < 6) {
                    result = 6;
                } else {
                    result = 8;
                }
            } else if(rand < 2) {
                result = 3;
            } else if(rand < 4) {
                result = 4;
            } else if(rand < 7) {
                result = 6;
            } else {
                result = 8;
            }

        } else if(wujiang.getZhi() <= 40) {
            if(rand < 4) {
                result = 1;
            } else if(rand < 5) {
                result = 5;
            } else if(rand < 7) {
                result = 6;
            } else {
                result = 8;
            }
        } else if(wujiang.getZhi() <= 80) {
            if(rand < 3) {
                result = 3;
            } else if(rand < 5) {
                result = 5;
            } else if(rand < 8) {
                result = 6;
            } else {
                result = 8;
            }
        } else if(rand < 3) {
            result = 3;
        } else if(rand < 5) {
            result = 4;
        } else if(rand < 8) {
            result = 5;
        } else {
            result = 8;
        }

        int value = 0;
        int maxJin = settingService.getJinMax(0);
        int maxMi = settingService.getMiMax(0);
        int maxBao = settingService.getBaoMax(0);
        switch(result) {
            case 1:
                value = getRandom(30, 70);
                chengshi.setJin(chengshi.getJin() + value);
                if(chengshi.getJin() > maxJin) {
                    chengshi.setJin(maxJin);
                }
                service.saveChengShi(chengshi);
                ret = "于城外收集情报之时\n遭遇并消灭盗贼\n没收 ?两金";
                break;
            case 2:
                ret = "城邑中偶遇富豪\n获得捐赠 ?石米";
                break;
            case 3:
                value = getRandom(80, 120);
                chengshi.setJin(chengshi.getJin() + value);
                if(chengshi.getJin() > maxJin) {
                    chengshi.setJin(maxJin);
                }
                service.saveChengShi(chengshi);
                ret = "城邑中偶遇富豪\n获得捐赠 ?两金";
                break;
            case 4:
                value = getRandom(1, 3);
                chengshi.setBao(chengshi.getBao() + value);
                if(chengshi.getJin() > maxBao) {
                    chengshi.setBao(maxBao);
                }
                service.saveChengShi(chengshi);
                ret = "发现 ?件宝物\n已置入仓库内";
                break;
            case 5:
                ret = "遇见一位前途无量之武将\n是否收为部下？";
                break;
            case 6:
                ret = "遇见一位前途无量之武将\n该武将索要 ?两金\n是否给予？";
                break;
            case 8:
                ret = "非常可惜\n并无值得一提之事";
            case 7:
        }
        return ret;
    }
}