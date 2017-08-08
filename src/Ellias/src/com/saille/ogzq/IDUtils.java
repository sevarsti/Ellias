package com.saille.ogzq;

import com.saille.util.CommonUtils;
import com.saille.util.UtilFunctions;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.log4j.Logger;

import java.util.*;
import java.io.*;

public class IDUtils {
    private static final Logger LOGGER = Logger.getLogger(IDUtils.class);
    public static Map<String, HttpClient> IDS = new Hashtable<String, HttpClient>();
    public static Map<String, String> IDPWDS = new Hashtable<String, String>();

    public static Map<String, String[]> IDObjIds = new Hashtable<String, String[]>();
    public static Map<String, Map<String, String>> IDInfos = new Hashtable<String, Map<String, String>>();
    public static Map<String, Map<String, String>> IDTasks = new Hashtable<String, Map<String, String>>();
    public static Map<String, List<Map<String, String>>> IDTaskInfos = new Hashtable<String, List<Map<String, String>>>();
    public static Map<String, Long> IDThreads = new Hashtable<String, Long>();
    public static Map<String, Boolean> JOINEDTEAMGAME = new Hashtable<String, Boolean>();
    public static Map<String, Boolean> WorldCupFinished = new Hashtable<String, Boolean>();
    public static Map<String, int[]> FubenStatus = new Hashtable<String, int[]>(); //每个id的几个副本剩余场次
    public static Map<String, String[]> JJCAgainst = new Hashtable<String, String[]>(); //每个号竞技场的对手

    public static Map<String, String> SigninDate = new Hashtable<String, String>(); //每个号日常签到日期，记录当天是否签到过

    /* 收编用到的 */
    public static Map<String, ShoubianThread> ShoubianThreads = new Hashtable<String, ShoubianThread>();
    public static Map<String, String> ShoubianTime = new Hashtable<String, String>();
    public static String XiaohaoShoubianTime = null;

    /* 联盟争夺战用到的 */
    public static Map<String, OGLMZDZThread> OGLMZDZThreads = new Hashtable<String, OGLMZDZThread>();
    public static Map<String, String> OGLMZDZTime = new Hashtable<String, String>();
    public static Map<String, OtherLMZDThread> OTHEROGLMZDZThreads = new Hashtable<String, OtherLMZDThread>();
    public static Map<String, String> OTHEROGLMZDZTime = new Hashtable<String, String>();

    public static FirstLoginTeamgameThread firstLoginTeamgameThread = null;
    public static String firstLoginTeamgameThreadTime = null;

    public static OtherWorldcup32Thread otherWorldcup32Thread = null;
    public static String otherWorldcup32ThreadTime = null;

    public static Map<String, int[]> AllPlayer = new Hashtable<String, int[]>();
    public static List<String> XiangQianPlayer = new ArrayList<String>();
    public static Map<String, Integer> AllXiangQianPlayer = new Hashtable<String, Integer>();
    public static Map<String, Integer> AllPlayerInitial = new Hashtable<String, Integer>();
    public static Date LastModify = new Date();

    public static List<String[]> GJXLS = new ArrayList<String[]>();
    static {
        try {
            File f = new File(ConfigUtils.class.getResource("../../../../../ogzq/gjxls.ini").getPath());
            if(f.exists()) {
                try {
                    FileReader fr = new FileReader(f);
                    BufferedReader br = new BufferedReader(fr);
                    String s;
                    while((s = br.readLine()) != null) {
                        GJXLS.add(s.split("[|]"));
                    }
                } catch(Exception ex) {
                    ex.printStackTrace();
                }
            }
        } catch(Exception ex) {}
    }

    public static List<String[]> JIONGIDS = new ArrayList<String[]>();
    public static List<String[]> GIDS = new ArrayList<String[]>();
    public static List<String[]> WEIIDS = new ArrayList<String[]>();
    public static List<String[]> XYIDS = new ArrayList<String[]>();
    public static List<String[]> NBIDS = new ArrayList<String[]>();
    public static List<String[]> SIDS = new ArrayList<String[]>();
    public static List<String[]> OTHERIDS = new ArrayList<String[]>();
    static {
        JIONGIDS.add(new String[]{"leonis11@e7wan*HDFC","123789","HDFC"});
        JIONGIDS.add(new String[]{"751541263@qq.com*井冈山","3239092","井冈山"});
        JIONGIDS.add(new String[]{"yuliang0526@163.com*不及阁大学士","yyx121101","不及阁大学士"});
        JIONGIDS.add(new String[]{"sw197297@163.com*华夏龙梦之队","smy19780405","华夏龙梦之队"});
        JIONGIDS.add(new String[]{"270738347@qq.com*ZM渝菲联","zmi198678","ZM渝菲联"});
        JIONGIDS.add(new String[]{"345777992@qq.com*发如雪123","55885588","发如雪123"});
        JIONGIDS.add(new String[]{"super88man66@126.com*完美时空","072022021","完美时空"});
        JIONGIDS.add(new String[]{"meijianbai@hotmail.com*酷爱皓皓","meiweilin","酷爱皓皓"});
        JIONGIDS.add(new String[]{"hujiekaka@163.com*艾斯丶爱慕","593162040","艾斯丶爱慕"});
        JIONGIDS.add(new String[]{"SKY@163.COM*新英格兰红魔","030978","新英格兰红魔"});
        JIONGIDS.add(new String[]{"690496636@qq.com*Carlos特维斯","852456..","Carlos特维斯"});
//        JIONGIDS.add(new String[]{"sevarsti@sina.com*缥缈过客","pmgkpmgk","缥缈过客"});
        JIONGIDS.add(new String[]{"rinoawing@qq.com*诺惟翼","840907","诺惟翼"});
        JIONGIDS.add(new String[]{"284230685@qq.com*C罗吕布","5858xjf5858","C罗吕布"});
        JIONGIDS.add(new String[]{"michael110110@hupu1*气煞","zengyu520","气煞"});
        JIONGIDS.add(new String[]{"Gino_88@hupu1*黑池","vanny6620013","黑池"});
        JIONGIDS.add(new String[]{"jiangshulei1988@vip.qq.com*紫云灬绝恋","jsl88970746","紫云灬绝恋"});
        JIONGIDS.add(new String[]{"cxz289@hupu1*CXZ","Cxz289","cxz"});
        JIONGIDS.add(new String[]{"no9_bj@sohu.com*欧冠战车","hanwen93","欧冠战车"});
        JIONGIDS.add(new String[]{"freewxlcg@163.com*小小极品","79667777","小小极品"});
        JIONGIDS.add(new String[]{"79792581@qq.com*小布衣","2112560715wH","小布衣"});
        JIONGIDS.add(new String[]{"ahfyyjq@sina.com*MilanJunior","yjq19751006","MilanJunior"});
        JIONGIDS.add(new String[]{"Gino_88@hupu1*GloryUnited","vanny6620013","GloryUnited"});
        JIONGIDS.add(new String[]{"vealzhf@hotmail.com*9869","222222","9869"});

        GIDS.add(new String[]{"88232241@qq.com*天真遥远","hanwen93","天真遥远"});
        GIDS.add(new String[]{"王者迈帝@hupu1*海布里包","58817039","海布里包"});
        GIDS.add(new String[]{"zxj13321667675@126.com*佳佳俱乐部","112918","佳佳俱乐部"});
        GIDS.add(new String[]{"wen52014@126.com*数字王国","hanwen","数字王国"});
        GIDS.add(new String[]{"181001147@qq.com*缘聚风情","87765192","缘聚风情"});
        GIDS.add(new String[]{"manking93@163.com*科悦","hanwen93","科悦"});
        GIDS.add(new String[]{"rentao@vip.sina.com*皇家GFG","meiweilin","皇家GFG"});
        GIDS.add(new String[]{"song11@163.com*荷兰建业FC","leewei917129","荷兰建业FC"});
        GIDS.add(new String[]{"lixiang2160@126.com*griggs","j23w3a15n41","griggs"});
        GIDS.add(new String[]{"764832681@qq.com*娘子还要","15896205678","娘子还要"});
        GIDS.add(new String[]{"smy1978@163.com*自由飞翔1","smy19780405","自由飞翔1"});
        GIDS.add(new String[]{"jx327645683*hamasi","871111","hamasi"});
        GIDS.add(new String[]{"anjou22@139.com*国家队冯潇霆","liwei781012","国家队冯潇霆"});
        GIDS.add(new String[]{"2359178129@qq.com*飓风熊猫队","mimi20080731","飓风熊猫队"});
        GIDS.add(new String[]{"fly123450@hupu1*生詹姆士","www123","生詹姆士"});
        GIDS.add(new String[]{"9253448@qq.com*国家队马德里","19840711","国家队马德里"});
        GIDS.add(new String[]{"12403303@qq.com*美丽小丫丫","821015tt","美丽小丫丫"});
        GIDS.add(new String[]{"andy9@9.com*叫我红领巾","a5258048","叫我红领巾"});
        GIDS.add(new String[]{"qilili19841212@126.com*YI兰帕德","www123","YI兰帕德"});
        GIDS.add(new String[]{"81430914@qq.com*赛卡卡","14789632","赛卡卡"});
        GIDS.add(new String[]{"broad@hupu5*绿茵messi", "cao123", "绿茵messi"});
        GIDS.add(new String[]{"104722123@qq.com*NB吕布","www123","NB吕布"});
        GIDS.add(new String[]{"104722123@qq.com*国家队C罗","www123","国家队C罗"});
        GIDS.add(new String[]{"masu2@qq.com*闲","84786056","闲"});

        WEIIDS.add(new String[]{"kkoanbfm@vip.qq.com*Sky爆射","yuliang83012","Sky爆射"});
        WEIIDS.add(new String[]{"hahako@yeah.net*SD狼耶","593425kk","SD狼耶"});
        WEIIDS.add(new String[]{"544397212@qq.com*熬特蛮","hcqsy0809","熬特蛮"});
        WEIIDS.add(new String[]{"国脚涛爷@hupu1*FCB涛爷","huangjiaqi520","FCB涛爷"});
        WEIIDS.add(new String[]{"leizhensd82@163.com*哦也baba","8425792","哦也baba"});
        WEIIDS.add(new String[]{"617566442@qq.com*神父队","qq251314","神父队"});
        WEIIDS.add(new String[]{"曾妮可@hupu1*气煞II","zengyu520","气煞II"});
        WEIIDS.add(new String[]{"313724849@qq.com*W本泽马w","8751107","W本泽马w"});
        WEIIDS.add(new String[]{"63208995@qq.com*C罗加油","hb6620013","C罗加油"});
        WEIIDS.add(new String[]{"tim1983831@e7wan*TIMI","1983831","TIMI"});
        WEIIDS.add(new String[]{"602888616@qq.com*lianni","123456","lianni"});
        WEIIDS.add(new String[]{"156451865@qq.com*拉玛利亚青训","lspmgk","拉玛利亚青训"});
        WEIIDS.add(new String[]{"chulang@163.com*楚天云浪","lspmgk","楚天云浪"});
        WEIIDS.add(new String[]{"fanmingsuo@163.com*囧之mrfan","880612","囧之mrfan"});
        WEIIDS.add(new String[]{"82382037111@qq.com*坪石2012","cllbwcnm","坪石2012"});
        WEIIDS.add(new String[]{"272271809@qq.com*甩甩小叽叽","19920217","甩甩小叽叽"});
        WEIIDS.add(new String[]{"823820371@qq.com*佛山1976","lspmgk","佛山1976"});
        WEIIDS.add(new String[]{"Italyone2003@yahoo.com.cn*尤文复兴帝国","lspmgk","尤文复兴帝国"});
        WEIIDS.add(new String[]{"707535504@qq.com*BF许磊","xulei521","BF许磊"});
        WEIIDS.add(new String[]{"278224476@qq.com@qq*argetina","123q456w789e","argetina"});
        WEIIDS.add(new String[]{"wzb0817@163.com*立否","123123","立否"});

        XYIDS.add(new String[]{"fanmingsuo2@163.com*MFAN","880612","MFAN"});
        XYIDS.add(new String[]{"jiangshulei1988@163.com*犹豫爱神","jsl88970746","犹豫爱神"});
        XYIDS.add(new String[]{"killer160@126.com*潇洒俊俊男","123456","潇洒俊俊男"});
        XYIDS.add(new String[]{"7125608@163.com*我叫不会玩","fhx520723","我叫不会玩"});
        XYIDS.add(new String[]{"403114076@qq.com*国家队范志毅","hb6620013","国家队范志毅"});
        XYIDS.add(new String[]{"270373371@qq.com*磊love","shilei001","磊love"});
        XYIDS.add(new String[]{"yz_jzq@163.com*巴塞罗纳","lspmgk","巴塞罗纳"});
        XYIDS.add(new String[]{"wjq801109@163.com*yao博涵","lspmgk","yao博涵"});
        XYIDS.add(new String[]{"karen525@tom.com*我是骗子","25313923","我是骗子"});
        XYIDS.add(new String[]{"fanmingsuo1@163.com*fan7","840907","fan7"});
        XYIDS.add(new String[]{"421623479@qq.com*滁州元老队","fhx520723","滁州元老队"});
        XYIDS.add(new String[]{"zhao6527@163.com*不耐烦丶","lspmgk","不耐烦丶"});
        XYIDS.add(new String[]{"101977723@qq.com*国米魔力鸟","lspmgk","国米魔力鸟"});
        XYIDS.add(new String[]{"1412913604@qq.com*A弑天","www123","A弑天"});
        XYIDS.add(new String[]{"472545875@qq.com*Angelababy","454870","Angelababy"});
        XYIDS.add(new String[]{"xieqigan@qq.com*小儿郎","lspmgk","小儿郎"});
        XYIDS.add(new String[]{"115271540@qq.com*Magic爆破","lspmgk","Magic爆破"});
        XYIDS.add(new String[]{"279524695@qq.com*利州小罗","www123","利州小罗"});
        XYIDS.add(new String[]{"zhouzhu998@sina.com*欧冠无敌手","yuliang83012","欧冠无敌手"});
        XYIDS.add(new String[]{"279512194@qq.com*厦门椰风寨","lspmgk","厦门椰风寨"});
        XYIDS.add(new String[]{"kouling.859@163.com*蝶舞亂","www123","蝶舞亂"});
        XYIDS.add(new String[]{"fanmingsuo3@163.com*囧fman","880612","囧fman"});

        NBIDS.add(new String[]{"freeseas*沙茶酱","11335577","沙茶酱"});
        NBIDS.add(new String[]{"3897021733@qq.com*后来居上","lspmgk","后来居上"});
        NBIDS.add(new String[]{"1422485013@qq.com*B弑天","www123","B弑天"});
        NBIDS.add(new String[]{"www.569323373@qq.163.com*思念灬娜","lspmgk","思念灬娜"});
        NBIDS.add(new String[]{"fanmingsuo4@163.com*mrf","880612","mrf"});
        NBIDS.add(new String[]{"woyaofabiaola@163.com*受到等不及","lspmgk","受到等不及"});
        NBIDS.add(new String[]{"www.273919473@qq.com*幸隆","lspmgk","幸隆"});
        NBIDS.add(new String[]{"355537@qq.com*辽原保南湖","123456","辽原保南湖"});
        NBIDS.add(new String[]{"189153298@qq.com*火玄","xy721207","火玄"});
        NBIDS.add(new String[]{"justin801022@163.com*蓝丨风","lspmgk","蓝丨风"});
        NBIDS.add(new String[]{"444825566@qq.com*孤单羽神8服","lspmgk","孤单羽神8服"});
        NBIDS.add(new String[]{"530528694@qq.com*小U","www123","小U"});
        NBIDS.add(new String[]{"zjzhaoxiaojun03@126.com*霞山之光","lspmgk","霞山之光"});
        NBIDS.add(new String[]{"stevending1@163.com*EV普约尔","lspmgk","EV普约尔"});
        NBIDS.add(new String[]{"mmzhaoxiaojun@126.com*RedBull8","lspmgk","RedBull8"});
        NBIDS.add(new String[]{"273734644@qq.com*斯巴达的爹","lspmgk","斯巴达的爹"});
        NBIDS.add(new String[]{"zjzhaoxiaojun02@126.com*湛江无敌","lspmgk","湛江无敌"});
        NBIDS.add(new String[]{"mrshanyao@163.com*挺着大肚子","lspmgk","挺着大肚子"});
        NBIDS.add(new String[]{"zjzhaoxiaojun@126.com*RedBull1","lspmgk","RedBull1"});
        NBIDS.add(new String[]{"124906932@qq.com*XD孔帕尼","lspmgk","XD孔帕尼"});
        NBIDS.add(new String[]{"313025470@qq.com*囧之因扎吉","lspmgk","囧之因扎吉"});

        SIDS.add(new String[]{"020637@163.com*梅心梅肺","lspmgk","梅心梅肺"});
        SIDS.add(new String[]{"shmilyfc@vip.qq.com*崛起吧灬落尘","lspmgk","崛起吧灬落尘"});
        SIDS.add(new String[]{"366440852@qq.com*超AC米兰","lspmgk","超AC米兰"});
        SIDS.add(new String[]{"757210292@qq.com*永远kk","lspmgk","永远kk"});
        SIDS.add(new String[]{"550267853@qq.com*中国女足","lspmgk","中国女足"});
        SIDS.add(new String[]{"zjzhaoxiaojun01@126.com*广东红牛队","lspmgk","广东红牛队"});
        SIDS.add(new String[]{"110932097@qq.com*丫丫足球","821015tt","丫丫足球"});
        SIDS.add(new String[]{"921851791@qq.com*可爱小丫丫","821015tt","可爱小丫丫"});
        SIDS.add(new String[]{"316639404@qq.com*o罗尼o","lspmgk","o罗尼o"});
        SIDS.add(new String[]{"zjzhaoxi7aojun@126.com*RED","lspmgk","RED"});
        SIDS.add(new String[]{"liaoqing262@163.com*岳阳神话","lspmgk","岳阳神话"});
        SIDS.add(new String[]{"183444340@qq.com*滁州业余队","lspmgk","滁州业余队"});
        SIDS.add(new String[]{"707000142@qq.com*虐菜灬魏","lspmgk","虐菜灬魏"});
        SIDS.add(new String[]{"348794240@qq.com*小鬼绅士","lspmgk","小鬼绅士"});
        SIDS.add(new String[]{"276300227@qq.com*精神威武","lspmgk","精神威武"});
        SIDS.add(new String[]{"zjzhaoxiaojun04@126.com*赤坎之辉","lspmgk","赤坎之辉"});
        SIDS.add(new String[]{"wqs1613@163.com*辽宁宏运","lspmgk","辽宁宏运"});
        SIDS.add(new String[]{"zhlzxd@163.com*采蘑菇","lspmgk","采蘑菇"});
        SIDS.add(new String[]{"LI5JIN9@SINA.COM*I因扎吉SK","lspmgk","I因扎吉SK"});
        SIDS.add(new String[]{"haibo414@126.com*什么乱七八糟","lspmgk","什么乱七八糟"});

//        NBIDS.add(new String[]{"xy721207@qq.com*温州鹿城","xy721207","温州鹿城"});
        OTHERIDS.add(new String[]{"245381501@qq.com*QQCC","123456","QQCC"});
    }

    public static List<String[]> GETJIONGIDS() {
        List<String[]> ret = new ArrayList<String[]>();
        for(String[] s : JIONGIDS) {
            ret.add(s);
        }
        return ret;
    }

    public static String getNick(String id) {
        return IDInfos.get(id).get("nick");
    }

    public static List<String> GETIDS() {
        Set<String> ss = IDS.keySet();
        List<String> ret = new ArrayList<String>();
        for(String s : ss) {
            ret.add(s);
        }
        Collections.sort(ret);
        return ret;
    }

    private static void loadXiangQianPlayer() {
        try {
            File f = new File("D:\\excel\\1.xls");
            if(!f.exists()) {
                f = new File("C:\\Documents and Settings\\Ellias\\桌面\\1.xls");
            }
            FileInputStream fis = new FileInputStream(f);
            HSSFWorkbook workbook = new HSSFWorkbook(fis);
            HSSFSheet sheet = workbook.getSheet("球员");
            AllXiangQianPlayer.clear();
            for(int i = 1; i < 551; i++) {
                HSSFRow row = sheet.getRow(i);
                if(row == null) {
                    continue;
                }
                String name = row.getCell(3).getRichStringCellValue().getString();
                String level = row.getCell(4).getRichStringCellValue().getString();
                String pos = row.getCell(5).getRichStringCellValue().getString();
                HSSFCell cell = row.getCell(16);
                String key = name + "_" + level.charAt(0) + "_" + pos.charAt(0);
                AllXiangQianPlayer.put(key, cell == null ? 0 : 1);
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void loadPlayerInitial() {
        try {
            File f = new File("D:\\excel\\1.xls");
            if(!f.exists()) {
                f = new File("C:\\Documents and Settings\\Ellias\\桌面\\1.xls");
            }

            FileInputStream fis = new FileInputStream(f);
            HSSFWorkbook workbook = new HSSFWorkbook(fis);
            HSSFSheet sheet = workbook.getSheet("初始能力");
            AllPlayerInitial.clear();
            for(int i = 2; i <= sheet.getLastRowNum(); i++) {
                HSSFRow row = sheet.getRow(i);
                if(row == null) {
                    continue;
                }
                String name = row.getCell(0).getRichStringCellValue().getString();
                String pos = row.getCell(1).getRichStringCellValue().getString();
                String level = row.getCell(2).getRichStringCellValue().getString();

                String key = name + "_" + level.charAt(0) + "_" + pos.charAt(0);

                AllPlayerInitial.put(key, 1);
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    private synchronized static void loadPlayerDetail() {
        try {
            File f = new File("D:\\excel\\1.xls");
            if(!f.exists()) {
                f = new File("C:\\Documents and Settings\\Ellias\\桌面\\1.xls");
            }

            LastModify.setTime(f.lastModified());
            FileInputStream fis = new FileInputStream(f);
            HSSFWorkbook workbook = new HSSFWorkbook(fis);
            HSSFSheet sheet = workbook.getSheet("球员明细");
            AllPlayer.clear();
            for(int i = 2; i <= sheet.getLastRowNum(); i++) {
                HSSFRow row = sheet.getRow(i);
                if(row == null) {
                    continue;
                }
                String name = row.getCell(1).getRichStringCellValue().getString();
                String level = row.getCell(2).getRichStringCellValue().getString();
                String pos = row.getCell(3).getRichStringCellValue().getString();
                int ability = (int)row.getCell(4).getNumericCellValue();
                String key = name + "_" + level.charAt(0) + "_" + pos.charAt(0);
                int full = StringUtils.isEmpty(row.getCell(14).getRichStringCellValue().getString()) ? 0 : 1;
                if(AllPlayer.containsKey(key)) {
                    throw new Exception("出现重复球员！");
                } else {
                    AllPlayer.put(key, new int[]{ability, full});
                }
            }

            sheet = workbook.getSheet("球员");
            XiangQianPlayer.clear();
            for(int i = 1; i <= sheet.getLastRowNum(); i++) {
                HSSFRow row = sheet.getRow(i);
                HSSFCell cell = row.getCell(8);
                if(cell == null || cell.getCellType() != HSSFCell.CELL_TYPE_NUMERIC) {
                    continue;
                }
                if(((int) cell.getNumericCellValue()) != 1) {
                    continue;
                }
                String name = row.getCell(3).getRichStringCellValue().getString();
                String level = row.getCell(4).getRichStringCellValue().getString();
                String pos = row.getCell(5).getRichStringCellValue().getString();
                String key = name + "_" + level.charAt(0) + "_" + pos.charAt(0);
                XiangQianPlayer.add(key);
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    public static synchronized void loadPlayer() {
        loadPlayerDetail();
        loadXiangQianPlayer();
        loadPlayerInitial();
    }

    /* 检查是否有该球员的初始能力数据，没有则在txt中增加 */
    public static boolean checkPlayerInitial(String name, String position, String level) {
        String key = name + "_" + level + "_" + position;
        if(AllPlayerInitial == null || AllPlayerInitial.size() == 0) {
            loadPlayer();
        }
        Boolean has = false;
        Integer i = AllPlayerInitial.get(key);
        has = i != null && i == 1;
        return has;
    }

    public static synchronized void savePlayerInitial(String name, String pos, String level, String[] atts) {
        Map<String, String> l = new Hashtable<String, String>();
        l.put("1", "1普通");
        l.put("2", "2优秀");
        l.put("3", "3精英");
        l.put("4", "4杰出");
        l.put("5", "5大牌");
        l.put("6", "6巨星");
        Map<String, String> p = new Hashtable<String, String>();
        p.put("1", "1门将");
        p.put("2", "2后卫");
        p.put("3", "3中场");
        p.put("4", "4前锋");
        try {
            File f = new File("D:\\newPlayerInitial.txt");
            if(!f.exists()) {
                f.createNewFile();
            }
            FileOutputStream fos = new FileOutputStream(f, true);
            StringBuffer sb = new StringBuffer("\r\n" + name + "\t" + p.get(pos) + "\t" + l.get(level));
            for(String att : atts) {
                sb.append("\t").append(att);
            }
            fos.write(sb.toString().getBytes());
            fos.close();
        } catch(Exception ex) {
            UtilFunctions.LogError(LOGGER, ex);
        }
    }

    public synchronized static int[] checkPlayer(String name, String position, String level) {
        String key = name + "_" + level + "_" + position;
        if(AllPlayer == null || AllPlayer.size() == 0) {
            loadPlayer();
        }
        if(AllPlayer.containsKey(key)) {
            return AllPlayer.get(key);
        } else {
            return new int[]{0, 0};
        }
    }

    public synchronized static boolean hasXiangQianInfo(String name, String position, String level) {
        String key = name + "_" + level + "_" + position;
        if(AllXiangQianPlayer == null || AllXiangQianPlayer.size() == 0) {
            loadXiangQianPlayer();
        }
        Integer i = AllXiangQianPlayer.get(key);
        return i == null || i == 1;
    }

    public synchronized static boolean isXiangQianPlayer(String name, String position, String level) {
        String key = name + "_" + level + "_" + position;
        if(XiangQianPlayer == null || XiangQianPlayer.size() == 0) {
            loadPlayer();
        }
        return XiangQianPlayer.contains(key);
    }

    public static String execute(HttpClient client, String email, HttpPost pm) {
        int count = 0;
        int retry = 0;
        while(count < 3) {
            CloseableHttpResponse response = null;
            InputStream is = null;
            try {
                if(client == null) {
                    return "";
                }
                String ret = "";
                synchronized(client) {
                    pm.getParams().setParameter("Connection", "keep-alive");
                    response = (CloseableHttpResponse) client.execute(pm);
                    ret = CommonUtils.getString(response.getEntity().getContent(), "utf-8");
                    is = response.getEntity().getContent();
                    is.close();
                    response.close();
//                    pm.abort();
                    pm.releaseConnection();
                    return ret;
//                    if(ret.indexOf("ASP.NET") != -1 || ret.indexOf("未将对象引用设置到对象的实例") != -1) {
//                        if(email.indexOf("sevarsti") != -1) {
//                            client = LoginUtils.Login(email, "pmgkpmgk");
//                        } else {
//                            client = LoginUtils.Login(email, "lspmgk");
//                        }
//                        IDUtils.IDS.put(email, client);
//                        OperationUtils.defaults(email, 7);
//                        IDUtils.WorldCupFinished.put(email, false);
//
//                        AutoLoopThread thread = new AutoLoopThread(email);
//                        thread.start();
//                    }
                }
//                return ret;
            } catch(Exception ex) {
                System.out.println(email + "/" + pm.getURI().getPath() + " occurs error: " + ex.getMessage() + ": " + retry);
                if("Timeout waiting for connection from pool".equals(ex.getMessage())) {
                } else {
                    count++;
                }
                retry++;
//                ex.printStackTrace();
                continue;
            } finally{
                if(is != null) {
                    try {is.close();} catch(Exception ex) {ex.printStackTrace();}
                }
                if(response != null) {
                    try {response.close();} catch(Exception ex) {ex.printStackTrace();}
                }
                pm.releaseConnection();
            }
        }
        return null;
    }

    public static String execute(String email, HttpPost pm) {
        int count = 0;
        try {
            HttpClient client = IDS.get(email);
            if(client == null) {
                return "";
            }
            String ret = execute(client, email, pm);
            if(ret.indexOf("ASP.NET") != -1 || ret.indexOf("未将对象引用设置到对象的实例") != -1 ||
                    ret.indexOf("action=\"MiddleMan.aspx\"") >= 0) {
                    client = LoginUtils.Login(email, IDPWDS.get(email));
                IDUtils.IDS.put(email, client);
                OperationUtils.defaults(email, 7);
                IDUtils.WorldCupFinished.put(email, false);
//                IDUtils.FubenStatus.put(email, new int[]{5,5,5,5,5});

                AutoLoopThread thread = new AutoLoopThread(email);
                thread.start();
            }
            return ret;
        } catch(Exception ex) {
            System.out.println(email + "/" + pm.getURI().getPath() + " occurs error: " + ex.getMessage());
            pm.releaseConnection();
        }
        return null;
    }

    public synchronized static void insertXiangqianPlayer(String email, int level, String[] playerDetail) {
        List<String[]> currPlayers = getXiangqianPlayer(email, level);
        if(currPlayers == null) {
            currPlayers = new ArrayList<String[]>();
        }
        currPlayers.add(playerDetail);
        TeamXiangqianCacheThread.saveCache(email, level, currPlayers);
    }

    public synchronized static void deleteXiangqianPlayer(String email, int level, String playerId) {
        List<String[]> currPlayers = getXiangqianPlayer(email, level);
        if(currPlayers == null || currPlayers.size() == 0) {
            return;
        }
        for(int i = 0; i < currPlayers.size(); i++) {
            if(currPlayers.get(i)[0].equals(playerId)) {
                currPlayers.remove(i);
                TeamXiangqianCacheThread.saveCache(email, level, currPlayers);
                return;
            }
        }
    }

    public static List<String[]> getXiangqianPlayer(String email, int level) {
        FileReader fr = null;
        BufferedReader br = null;
        try {
            List<String[]> ret = new ArrayList<String[]>();
            for(int i = (level == 0 ? 1 : level); i <= (level == 0 ? 6 : level); i++) {
                String filepath = TeamXiangqianCacheThread.class.getResource("../../../../../ogzq/cache/xiangqian/").getPath() + email + "_" + i + ".txt";
                filepath = filepath.replaceAll("[*]", "~");
                File f = new File(filepath);
                if(f.exists()) {
                    fr = new FileReader(f);
                    br = new BufferedReader(fr);
                    String s;
                    while((s = br.readLine()) != null) {
                        if(!"".equals(s)) {
                            String[] ss = s.split("[|]");
                            ret.add(ss);
                        }
                    }
                }
            }
            return ret;
        } catch(Exception ex) {
            ex.printStackTrace();
            return null;
        } finally{
            if(br != null) {
                try {
                    br.close();
                } catch(Exception ex) {}
            }
            if(fr != null) {
                try {
                    fr.close();
                } catch(Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    public static boolean isZijinPlayer(String playerId) {
        String[] zijins = new String[]{"671158","593008","620890","262936","821915","823685","849215","509416","629122","668440","618592","253864","377230","782633","335795","624935","632467","552736","618514","244450","768971","782627","342503","821603","622066","257068","549850","622864","848999","692656","330460","771919"};
        for(String s : zijins) {
            if(s.equals(playerId)) {
                return true;
            } else {
//                return false;
            }
        }
        return false;
    }
    /*
    * 跳过比赛ImaMatchCategory
    * 5: 竞技场, KFC=0
    * 38: 球会巅峰
    * */
}