package com.saille.rxqq;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONObject;

public class RxqqInstance {
    private static final Logger LOGGER = Logger.getLogger(RxqqInstance.class);
    public static final String[] CARDLEVEL = {"??", "??", "橙卡", "元老", "紫卡", "蓝卡", "绿卡"};

    public static int DF_SFFS = 1200;
    public static int KF_SFFS = 1200;

    public static Map<String, String> verifyFile = new HashMap();
    public static Map<String, String> verifyCode = new HashMap();

    public static Map<String, String> pwd = new HashMap();
    public static Map<String, JSONObject> info = new HashMap();
    public static Map<String, JSONObject[]> arenaRank = new HashMap();
    public static Map<String, JSONObject[]> crossRank = new HashMap();
    public static Map<String, Object[]> id = new HashMap();

    public static List<String[]> yizhiqiuyuan = new ArrayList();
    public static Map<String, Integer[]> cprice = new HashMap();

    public static Map<String, Vector<String[]>> togetPlayMatch = new HashMap();

    public static Map<Integer, Integer> yizhiCount = new HashMap();
    public static List<String> blackYIZHI = new ArrayList();
    public static final String SERVER = "http://s3.qiuqiu.2010.sina.com.cn/";
    public static final String DFTT = "http://s3.qiuqiu.2010.sina.com.cn/Arena.do?action=attendladder";
    public static final String JJC = "http://s3.qiuqiu.2010.sina.com.cn/AthleticsCoin.do?action=Register";
    public static final String KFTT = "http://s3.qiuqiu.2010.sina.com.cn/CrossArena.do?action=join";
    public static final String DSS_NEXT = "http://s3.qiuqiu.2010.sina.com.cn/NPCMaster.do?action=ready";
    public static final String DSS_GO = "http://s3.qiuqiu.2010.sina.com.cn/NPCMaster.do?action=matchraise";
    public static final String MY_INFO = "http://s3.qiuqiu.2010.sina.com.cn/Manager.do?action=myinfo";
    public static final String KFXX = "http://s3.qiuqiu.2010.sina.com.cn/CrossArena.do?action=myinfo";
    public static final String DFXX = "http://s3.qiuqiu.2010.sina.com.cn/Arena.do?action=managerinfo";
    public static final String DF_PAIMING = "http://s3.qiuqiu.2010.sina.com.cn/Arena.do?action=rank";
    public static final String KF_PAIMING = "http://s3.qiuqiu.2010.sina.com.cn/CrossArena.do?domain=1&action=ranklist";
    public static final String CG_RACE_RY = "http://s3.qiuqiu.2010.sina.com.cn/Match.do?AwayManager=5a28732e%2Dcbe5%2D442b%2D9765%2D499b96106ba6&action=creatematchdaily";
    public static final String CG_RACE_JQ = "http://s3.qiuqiu.2010.sina.com.cn/Match.do?AwayManager=b790d339%2Dbe78%2D4cd2%2Da7c2%2D4da1fbba32d5&action=creatematchdaily";
    public static final String CG_RACE_MX = "http://s3.qiuqiu.2010.sina.com.cn/Match.do?AwayManager=5a28732e%2Dcbe5%2D442b%2D9765%2D499b96106ba6&action=creatematchdaily";
    public static final String CG_1_SHOWCARD = "http://s3.qiuqiu.2010.sina.com.cn/Index.aspx?isPlayBack=1&MatchType=1&MatchId=";
    public static final String CG_2_SHOWSTATUS = "http://s3.qiuqiu.2010.sina.com.cn/interface/Get-MatchStat.aspx?MatchType=1&MatchId=";
    public static final String CG_3_BEFORE_CHOUKA = "http://s3.qiuqiu.2010.sina.com.cn/Match.do?action=GetLottery&MatchId=";
    public static final String CG_4_CHOUKA = "http://s3.qiuqiu.2010.sina.com.cn/Match.do?action=SaveLottery&MatchId=";
    public static final String BB = "http://s3.qiuqiu.2010.sina.com.cn/Package.do?action=getpackage";
    public static final String BB_LS = "http://s3.qiuqiu.2010.sina.com.cn/Auction.do?action=CheckTempPackage";
    public static final String BB_LS_GET = "http://s3.qiuqiu.2010.sina.com.cn/Auction.do?action=GetItem&ItemId=";
    public static final String WP_MS = "http://s3.qiuqiu.2010.sina.com.cn/Package.do?ver=1.6&action=Description&ItemCode=";
    public static final String WP_FJ = "http://s3.qiuqiu.2010.sina.com.cn/Pandora.do?action=postDecompound&itemId=";
    public static final String WP_DQ = "http://s3.qiuqiu.2010.sina.com.cn/interface/Post-ItemOperate.aspx?Op=2&ItemId=";
    public static final String ZB_DH = "http://s3.qiuqiu.2010.sina.com.cn/Token.do?action=ExchangeToken&Type=4&Num=1&ItemId=%s&random=%s";
    public static final String WP_SY = "http://s3.qiuqiu.2010.sina.com.cn/MallItem.do?action=use&itemId=";
    public static final String MFS_1_BEGIN = "http://s3.qiuqiu.2010.sina.com.cn/CardPack.do?action=buy&payType=2";
    public static final String MFS_2_GETRESULT = "http://s3.qiuqiu.2010.sina.com.cn/CardPack.do?action=hit&pStr=";
    public static final String MFS_3 = "http://s3.qiuqiu.2010.sina.com.cn/CardPack.do?action=state";
    public static final String MFS_4 = "http://s3.qiuqiu.2010.sina.com.cn/CardPack.do?action=packs";
    public static final String SELL = "http://s3.qiuqiu.2010.sina.com.cn/Auction.do?action=StartAuction&CurrencyType=2&EndPrice=50&StartPrice=50&TimeType=2&ItemId=";
    public static final String SELL_CPLAYER = "http://s3.qiuqiu.2010.sina.com.cn/Auction.do?action=StartAuction&CurrencyType=2&StartPrice=%s&EndPrice=%s&TimeType=2&ItemId=%s";
    public static final String GH_BS = "http://s3.qiuqiu.2010.sina.com.cn/GuildCup.do?action=join";
    public static final String PM_MY = "http://s3.qiuqiu.2010.sina.com.cn/Auction.do?action=GetBySellerId";
    public static final String GH_PTFJK = "http://s3.qiuqiu.2010.sina.com.cn/Guild.do?actqty=1&action=shopAct&bdcode=GM101&actNo=1";
    public static final String GH_ZJFJK = "http://s3.qiuqiu.2010.sina.com.cn/Guild.do?actqty=1&action=shopAct&bdcode=GM102&actNo=1";
    public static final String GH_YCJ = "http://s3.qiuqiu.2010.sina.com.cn/Guild.do?actqty=1&action=shopAct&bdcode=GM301&actNo=1";
    public static final String GH_BHM = "http://s3.qiuqiu.2010.sina.com.cn/Guild.do?actqty=1&action=shopAct&bdcode=GM401&actNo=1";
    public static final String JC_MX = "http://s3.qiuqiu.2010.sina.com.cn/League.do?mid=3561ee2b%2D21a2%2D45f9%2Da305%2D2d7a5dbd98ff&Money=800&action=PostGambleLeague";
    public static final String JC_RY = "http://s3.qiuqiu.2010.sina.com.cn/League.do?mid=1759ef4e%2D4693%2D4d78%2Dbb93%2D3bfa8a4ce037&Money=800&action=PostGambleLeague";
    public static final String JC_JQ = "http://s3.qiuqiu.2010.sina.com.cn/League.do?mid=6f62d071%2Da7b4%2D413d%2Db865%2D00046f71781b&Money=800&action=PostGambleLeague";
    public static final String PM_SJB = "http://s3.qiuqiu.2010.sina.com.cn/Manager.do?action=ScoreRank";
    public static final String BS_CJ = "http://s3.qiuqiu.2010.sina.com.cn/interface/Post-JoinDailyCup.aspx";
    public static final int ItemCode_TX = 8500310;
    public static final int ItemCode_BD = 8500501;
    public static final int ItemCode_CJFJK = 8500101;
    public static final int ItemCode_ZJFJK = 8500102;
    public static final int ItemCode_GJFJK = 8500103;
    public static final int ItemCode_SFJK = 8500104;
    public static final int ItemCode_ZF = 8500111;
    public static final int ItemCode_YCJ = 8500113;
    public static final int ItemCode_QXBJ = 8500119;
    public static final int ItemCode_VIP7 = 8500151;
    public static final int ItemCode_CSKB = 8500208;
    public static final int ItemCode_JSKB = 8500209;
    public static final int ItemCode_GQLB = 8500211;
    public static final int ItemCode_ZCYS = 8500302;
    public static final int ItemCode_CKBHJ = 8500503;
    public static final int ItemCode_BHM = 8500502;
    public static final int ItemCode_ZHEN = 8500506;
    public static final int ItemCode_XLS = 8500590;
    public static final int ItemCode_XLS_PT = 8500593;
    public static final int ItemCode_XLS_CJ = 8500594;
    public static final int ItemCode_RHJ = 8500591;
    public static final int ItemCode_GONG = 8600201;
    public static final int ItemCode_SHENG = 8600101;
    public static final int ItemCode_DAN = 8600102;
    public static final int ItemCode_KUAI_S = 8600103;
    public static final int ItemCode_LE = 8600104;
    public static final int ItemCode_FA = 8600203;
    public static final int ItemCode_GUO = 8600401;
    public static final int ItemCode_QING = 8600402;
    public static final int ItemCode_KUAI = 8600403;
    public static int dfttJiou;

    public static String getItemDesc(int code) {
        switch(code) {
            case 8500310:
                return "头像更换";
            case 8500501:
                return "百搭";
            case 8500101:
                return "初级分解卡";
            case 8500102:
                return "中级分解卡";
            case 8500103:
                return "高级分解卡";
            case 8500111:
                return "祝福";
            case 8500113:
                return "印钞机";
            case 8500302:
                return "主场优势";
            case 8500502:
                return "卡牌保护膜";
            case 8500506:
                return "强化保护剂(针)";
            case 8500590:
                return "洗炼石";
            case 8500591:
                return "洗炼润滑剂，改变属性";
            case 8600201:
                return "\"恭\"";
            case 8600203:
                return "\"发\"";
        }
        return "未知";
    }

    public static int getDfttJiou() {
        dfttJiou = 1 - dfttJiou;
        return dfttJiou;
    }

    public static void initCPrice() {
        if((cprice != null) && (cprice.size() > 0)) {
            return;
        }
        cprice = new HashMap();
        try {
            File f = new File("D:\\work\\Ellias\\exploded\\rxqq\\cplayer.txt");
            FileInputStream fis = new FileInputStream(f);
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));
            String tmp = null;
            while((tmp = br.readLine()) != null) {
                String[] players = tmp.split("\t");
                if(players[0].equals("#")) {
                    continue;
                }
                if(players[0].startsWith("!")) {
                    String name = players[0];
                    for(int i = 1; i <= 10; i++) {
                        if(players.length > i) {
                            String[] prices = players[i].split("-");
                            cprice.put(name + "_" + i, new Integer[]{Integer.valueOf(Integer.parseInt(prices[0])), Integer.valueOf(Integer.parseInt(prices.length > 1 ? prices[1] : prices[0]))});
                        }
                    }
                    LOGGER.info("增加球员价格：" + name);
                } else {
                    String name = players[0];
                    String l5 = players[1];
                    String l6 = players[2];
                    String l7 = players[3];
                    String l8 = players[4];
                    String l9 = players[5];
                    String[] ll5 = l5.split("-");
                    String[] ll6 = l6.split("-");
                    String[] ll7 = l7.split("-");
                    String[] ll8 = l8.split("-");
                    String[] ll9 = l9.split("-");
                    cprice.put(name + "_5", new Integer[]{Integer.valueOf(Integer.parseInt(ll5[0])), Integer.valueOf(Integer.parseInt(ll5.length > 1 ? ll5[1] : ll5[0]))});
                    cprice.put(name + "_6", new Integer[]{Integer.valueOf(Integer.parseInt(ll6[0])), Integer.valueOf(Integer.parseInt(ll6.length > 1 ? ll6[1] : ll6[0]))});
                    cprice.put(name + "_7", new Integer[]{Integer.valueOf(Integer.parseInt(ll7[0])), Integer.valueOf(Integer.parseInt(ll7.length > 1 ? ll7[1] : ll7[0]))});
                    cprice.put(name + "_8", new Integer[]{Integer.valueOf(Integer.parseInt(ll8[0])), Integer.valueOf(Integer.parseInt(ll8.length > 1 ? ll8[1] : ll8[0]))});
                    cprice.put(name + "_9", new Integer[]{Integer.valueOf(Integer.parseInt(ll9[0])), Integer.valueOf(Integer.parseInt(ll9.length > 1 ? ll9[1] : ll9[0]))});
                    LOGGER.info("增加球员价格：" + name + "/" + l5 + "/" + l6 + "/" + l7 + "/" + l8 + "/" + l9);
                }
            }
        } catch(Exception ex) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            ex.printStackTrace(pw);
            LOGGER.error("初始化C卡价格出错：" + sw.toString());
        }
    }

    public static void initYizhi() {
        try {
            yizhiqiuyuan = new ArrayList();
            File f = new File("D:\\work\\Ellias\\exploded\\ROOT\\rxqq\\yizhi.txt");
            FileInputStream fis = new FileInputStream(f);
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));
            String tmp = null;
            while((tmp = br.readLine()) != null) {
                String[] y = new String[2];
                y[0] = tmp;
                y[1] = tmp;
                yizhiqiuyuan.add(y);
            }
        } catch(Exception ex) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            ex.printStackTrace(pw);
            LOGGER.error("初始化意志球员名单出错：" + sw.toString());
        }
    }

    public static synchronized String updateMatch(String id, String matchId, int type) {
        Vector matches = (Vector) togetPlayMatch.get(id);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
        Date now = new Date();
        if(type == 1) {
            if(matches == null) {
                matches = new Vector();
            }
            matches.add(new String[]{sdf.format(now), matchId});
            togetPlayMatch.put(id, matches);
            return matchId;
        }
        if(type == 2) {
            if(matches == null) {
                return null;
            }
            for(int i = 0; i < matches.size(); i++) {
                try {
                    if(now.getTime() - sdf.parse(((String[]) matches.get(i))[0]).getTime() > 300000L) {
                        String ret = ((String[]) matches.get(i))[1];
                        matches.remove(i);
                        return ret;
                    }
                } catch(Exception ex) {
                }
            }
            return null;
        }

        return null;
    }

    static {
        blackYIZHI.add("巴拉克");
        blackYIZHI.add("卡恩");
        blackYIZHI.add("罗西");
        blackYIZHI.add("鲁伊科斯塔");
        blackYIZHI.add("卡尼吉亚");
        blackYIZHI.add("博涅克");
        blackYIZHI.add("卡恩");
        blackYIZHI.add("卡恩");
        blackYIZHI.add("卡恩");
        blackYIZHI.add("卡恩");
        blackYIZHI.add("卡恩");
        blackYIZHI.add("卡恩");

        dfttJiou = 1;
    }
}