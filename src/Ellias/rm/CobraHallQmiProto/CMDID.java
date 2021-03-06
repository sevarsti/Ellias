package CobraHallQmiProto;

import java.io.Serializable;

public final class CMDID
  implements Serializable
{
  public static final CMDID CMDID_ADDFEEDCOMMENT;
  public static final CMDID CMDID_ADDFRIEND;
  public static final CMDID CMDID_ADDFRIENDWITHMSG;
  public static final CMDID CMDID_ADDGAMETALK;
  public static final CMDID CMDID_ADDPOST;
  public static final CMDID CMDID_ADDVCOMMENT;
  public static final CMDID CMDID_APPLYUPLOADVIDEO;
  public static final CMDID CMDID_BIND3RDPARTYACCOUNT;
  public static final CMDID CMDID_BINDACCOUNTFORCE;
  public static final CMDID CMDID_CHANGEFRIENDGROUP;
  public static final CMDID CMDID_CHECKCREATEGROUP;
  public static final CMDID CMDID_CHECKFEEDUPDATE;
  public static final CMDID CMDID_CHECKINSTALLGAME;
  public static final CMDID CMDID_CHECKPERSONRELATED;
  public static final CMDID CMDID_CLEARLOCATIONRECORD;
  public static final CMDID CMDID_COMPLETEWORD;
  public static final CMDID CMDID_CONFIRMFRIEND;
  public static final CMDID CMDID_CREATEGROUP;
  public static final CMDID CMDID_CREATEPARTY;
  public static final CMDID CMDID_DAILYRECOMMEND;
  public static final CMDID CMDID_DAUCOUNT;
  public static final CMDID CMDID_DELETEFEED;
  public static final CMDID CMDID_DELETEFEEDCOMMENT;
  public static final CMDID CMDID_DELETEFRIEND;
  public static final CMDID CMDID_DELETEVIDEO;
  public static final CMDID CMDID_DELGROUPMEMBER;
  public static final CMDID CMDID_DELVCOMMENT;
  public static final CMDID CMDID_DISCERNGAMEPKG;
  public static final CMDID CMDID_DOFEEDPRAISE;
  public static final CMDID CMDID_FOLLOW;
  public static final CMDID CMDID_GAMESEARCH_V2;
  public static final CMDID CMDID_GETACCESSTOKEN;
  public static final CMDID CMDID_GETAD;
  public static final CMDID CMDID_GETEXPIDENT;
  public static final CMDID CMDID_GETFEEDCOMMENT;
  public static final CMDID CMDID_GETFEEDDETAIL;
  public static final CMDID CMDID_GETFEEDLIST;
  public static final CMDID CMDID_GETFEEDPRAISE;
  public static final CMDID CMDID_GETFRIENDCONFIRM;
  public static final CMDID CMDID_GETFRIENDINFO_V2;
  public static final CMDID CMDID_GETFRIENDLIST_V2;
  public static final CMDID CMDID_GETGAMEBASE;
  public static final CMDID CMDID_GETGAMEDETAIL;
  public static final CMDID CMDID_GETGAMEPAGELIST;
  public static final CMDID CMDID_GETGAMERECOMMEND;
  public static final CMDID CMDID_GETGAMEURLINFO;
  public static final CMDID CMDID_GETGIFTCONF;
  public static final CMDID CMDID_GETGROUP;
  public static final CMDID CMDID_GETHOTWORD;
  public static final CMDID CMDID_GETINFOPAGELIST;
  public static final CMDID CMDID_GETLBSGAMELIST;
  public static final CMDID CMDID_GETLOGINTYPE;
  public static final CMDID CMDID_GETMIME;
  public static final CMDID CMDID_GETMYFEEDNOTICE;
  public static final CMDID CMDID_GETMYGROUPLIST;
  public static final CMDID CMDID_GETNEARBYPARTYLIST;
  public static final CMDID CMDID_GETORDERLIST;
  public static final CMDID CMDID_GETPLUGINNOTICE;
  public static final CMDID CMDID_GETPOPVIDEOLIST;
  public static final CMDID CMDID_GETQQFRIENDBYGROUP;
  public static final CMDID CMDID_GETQQFRIENDGROUP;
  public static final CMDID CMDID_GETRECOMMENDGROUP;
  public static final CMDID CMDID_GETSELFDEFFRIENDLIST;
  public static final CMDID CMDID_GETSPLASH;
  public static final CMDID CMDID_GETSTATDATA;
  public static final CMDID CMDID_GETSYBACCESSTOKEN;
  public static final CMDID CMDID_GETSYSMSG;
  public static final CMDID CMDID_GETUSERFANSLIST;
  public static final CMDID CMDID_GETUSERFOLLOWLIST;
  public static final CMDID CMDID_GETUSERGIFT;
  public static final CMDID CMDID_GETUSERINFO_V2;
  public static final CMDID CMDID_GETUSERVIDEOLIST;
  public static final CMDID CMDID_GETVAR;
  public static final CMDID CMDID_GETVCOMMENT;
  public static final CMDID CMDID_GETVCOMMLIST;
  public static final CMDID CMDID_GETVERIFYCODE;
  public static final CMDID CMDID_GETVIDEOLIST;
  public static final CMDID CMDID_GETVIDEOPLAYURL;
  public static final CMDID CMDID_GETWECHATFRIENDLIST;
  public static final CMDID CMDID_HOTGAMELIST;
  public static final CMDID CMDID_INVITE;
  public static final CMDID CMDID_JOINGROUP;
  public static final CMDID CMDID_KEEPALIVE;
  public static final CMDID CMDID_LOCATIONRECORDEXIST;
  public static final CMDID CMDID_LOGINPHONEUSER;
  public static final CMDID CMDID_MAX;
  public static final CMDID CMDID_MODIFYGROUP;
  public static final CMDID CMDID_MODVCOMMENT;
  public static final CMDID CMDID_MYGAMES;
  public static final CMDID CMDID_NEARBYUSER;
  public static final CMDID CMDID_QMI_CHECKGAMES;
  public static final CMDID CMDID_QMI_GETCHATTERINFO;
  public static final CMDID CMDID_QMI_GETGAMEEXTENDINFO;
  public static final CMDID CMDID_QMI_GETGAMEJOYLDLINFO;
  public static final CMDID CMDID_QMI_GETGAMEJOYRECORDINGCONF;
  public static final CMDID CMDID_QMI_GETGAMEJOYRECORDINGWHITELIST;
  public static final CMDID CMDID_QMI_GETGAMEPAGELIST;
  public static final CMDID CMDID_QMI_GETSDKSYBACCESSTOKEN;
  public static final CMDID CMDID_QMI_START;
  public static final CMDID CMDID_QUERYGAMEUSERLIST;
  public static final CMDID CMDID_QUITGROUP;
  public static final CMDID CMDID_RECOMMENDFOLLOWLIST;
  public static final CMDID CMDID_RECOMMENDFRIENDLIST;
  public static final CMDID CMDID_RECVGIFT;
  public static final CMDID CMDID_REGLOC;
  public static final CMDID CMDID_REGPUSHINFO;
  public static final CMDID CMDID_REPORT;
  public static final CMDID CMDID_REPORTGAMEACTION_V2;
  public static final CMDID CMDID_REPORTGAMEACTION_V3;
  public static final CMDID CMDID_REPORTLBSPARTYINFO;
  public static final CMDID CMDID_REPORTUPLOADVIDEO;
  public static final CMDID CMDID_REPORTVTS;
  public static final CMDID CMDID_SAYHI;
  public static final CMDID CMDID_SEARCHGAME;
  public static final CMDID CMDID_SEARCHGROUP;
  public static final CMDID CMDID_SEARCHUSERINFO;
  public static final CMDID CMDID_SENDMSG;
  public static final CMDID CMDID_SETFRIENDALIAS;
  public static final CMDID CMDID_SETMOOD;
  public static final CMDID CMDID_SETPICPROFILE;
  public static final CMDID CMDID_SETPRIVACY;
  public static final CMDID CMDID_SETPROFILE;
  public static final CMDID CMDID_SETPWD;
  public static final CMDID CMDID_SETSYBACCOUNTNAME;
  public static final CMDID CMDID_SHAREGROUP;
  public static final CMDID CMDID_START;
  public static final CMDID CMDID_UNKNOWN;
  public static final CMDID CMDID_UPLOADGROUPPIC;
  public static final CMDID CMDID_USERPERIODGROUP;
  public static final CMDID CMDID_VERIFYMSGCODE;
  public static final CMDID CMDID_VIDEOPRAISE;
  public static final int _CMDID_ADDFEEDCOMMENT = 171;
  public static final int _CMDID_ADDFRIEND = 310;
  public static final int _CMDID_ADDFRIENDWITHMSG = 311;
  public static final int _CMDID_ADDGAMETALK = 180;
  public static final int _CMDID_ADDPOST = 113;
  public static final int _CMDID_ADDVCOMMENT = 150;
  public static final int _CMDID_APPLYUPLOADVIDEO = 130;
  public static final int _CMDID_BIND3RDPARTYACCOUNT = 324;
  public static final int _CMDID_BINDACCOUNTFORCE = 325;
  public static final int _CMDID_CHANGEFRIENDGROUP = 314;
  public static final int _CMDID_CHECKCREATEGROUP = 199;
  public static final int _CMDID_CHECKFEEDUPDATE = 179;
  public static final int _CMDID_CHECKINSTALLGAME = 6;
  public static final int _CMDID_CHECKPERSONRELATED = 112;
  public static final int _CMDID_CLEARLOCATIONRECORD = 335;
  public static final int _CMDID_COMPLETEWORD = 109;
  public static final int _CMDID_CONFIRMFRIEND = 312;
  public static final int _CMDID_CREATEGROUP = 190;
  public static final int _CMDID_CREATEPARTY = 120;
  public static final int _CMDID_DAILYRECOMMEND = 15;
  public static final int _CMDID_DAUCOUNT = 31;
  public static final int _CMDID_DELETEFEED = 173;
  public static final int _CMDID_DELETEFEEDCOMMENT = 172;
  public static final int _CMDID_DELETEFRIEND = 313;
  public static final int _CMDID_DELETEVIDEO = 132;
  public static final int _CMDID_DELGROUPMEMBER = 194;
  public static final int _CMDID_DELVCOMMENT = 152;
  public static final int _CMDID_DISCERNGAMEPKG = 125;
  public static final int _CMDID_DOFEEDPRAISE = 177;
  public static final int _CMDID_FOLLOW = 360;
  public static final int _CMDID_GAMESEARCH_V2 = 161;
  public static final int _CMDID_GETACCESSTOKEN = 103;
  public static final int _CMDID_GETAD = 9;
  public static final int _CMDID_GETEXPIDENT = 104;
  public static final int _CMDID_GETFEEDCOMMENT = 174;
  public static final int _CMDID_GETFEEDDETAIL = 176;
  public static final int _CMDID_GETFEEDLIST = 170;
  public static final int _CMDID_GETFEEDPRAISE = 175;
  public static final int _CMDID_GETFRIENDCONFIRM = 318;
  public static final int _CMDID_GETFRIENDINFO_V2 = 303;
  public static final int _CMDID_GETFRIENDLIST_V2 = 301;
  public static final int _CMDID_GETGAMEBASE = 2;
  public static final int _CMDID_GETGAMEDETAIL = 3;
  public static final int _CMDID_GETGAMEPAGELIST = 4;
  public static final int _CMDID_GETGAMERECOMMEND = 14;
  public static final int _CMDID_GETGAMEURLINFO = 124;
  public static final int _CMDID_GETGIFTCONF = 12;
  public static final int _CMDID_GETGROUP = 195;
  public static final int _CMDID_GETHOTWORD = 13;
  public static final int _CMDID_GETINFOPAGELIST = 126;
  public static final int _CMDID_GETLBSGAMELIST = 122;
  public static final int _CMDID_GETLOGINTYPE = 321;
  public static final int _CMDID_GETMIME = 10;
  public static final int _CMDID_GETMYFEEDNOTICE = 178;
  public static final int _CMDID_GETMYGROUPLIST = 196;
  public static final int _CMDID_GETNEARBYPARTYLIST = 121;
  public static final int _CMDID_GETORDERLIST = 5;
  public static final int _CMDID_GETPLUGINNOTICE = 115;
  public static final int _CMDID_GETPOPVIDEOLIST = 136;
  public static final int _CMDID_GETQQFRIENDBYGROUP = 320;
  public static final int _CMDID_GETQQFRIENDGROUP = 319;
  public static final int _CMDID_GETRECOMMENDGROUP = 197;
  public static final int _CMDID_GETSELFDEFFRIENDLIST = 315;
  public static final int _CMDID_GETSPLASH = 7;
  public static final int _CMDID_GETSTATDATA = 102;
  public static final int _CMDID_GETSYBACCESSTOKEN = 322;
  public static final int _CMDID_GETSYSMSG = 8;
  public static final int _CMDID_GETUSERFANSLIST = 363;
  public static final int _CMDID_GETUSERFOLLOWLIST = 361;
  public static final int _CMDID_GETUSERGIFT = 107;
  public static final int _CMDID_GETUSERINFO_V2 = 300;
  public static final int _CMDID_GETUSERVIDEOLIST = 133;
  public static final int _CMDID_GETVAR = 11;
  public static final int _CMDID_GETVCOMMENT = 153;
  public static final int _CMDID_GETVCOMMLIST = 154;
  public static final int _CMDID_GETVERIFYCODE = 330;
  public static final int _CMDID_GETVIDEOLIST = 134;
  public static final int _CMDID_GETVIDEOPLAYURL = 135;
  public static final int _CMDID_GETWECHATFRIENDLIST = 326;
  public static final int _CMDID_HOTGAMELIST = 160;
  public static final int _CMDID_INVITE = 329;
  public static final int _CMDID_JOINGROUP = 192;
  public static final int _CMDID_KEEPALIVE = 101;
  public static final int _CMDID_LOCATIONRECORDEXIST = 336;
  public static final int _CMDID_LOGINPHONEUSER = 333;
  public static final int _CMDID_MAX = 1010;
  public static final int _CMDID_MODIFYGROUP = 191;
  public static final int _CMDID_MODVCOMMENT = 151;
  public static final int _CMDID_MYGAMES = 30;
  public static final int _CMDID_NEARBYUSER = 334;
  public static final int _CMDID_QMI_CHECKGAMES = 1007;
  public static final int _CMDID_QMI_GETCHATTERINFO = 1003;
  public static final int _CMDID_QMI_GETGAMEEXTENDINFO = 1004;
  public static final int _CMDID_QMI_GETGAMEJOYLDLINFO = 1005;
  public static final int _CMDID_QMI_GETGAMEJOYRECORDINGCONF = 1006;
  public static final int _CMDID_QMI_GETGAMEJOYRECORDINGWHITELIST = 1008;
  public static final int _CMDID_QMI_GETGAMEPAGELIST = 1002;
  public static final int _CMDID_QMI_GETSDKSYBACCESSTOKEN = 1009;
  public static final int _CMDID_QMI_START = 1001;
  public static final int _CMDID_QUERYGAMEUSERLIST = 162;
  public static final int _CMDID_QUITGROUP = 193;
  public static final int _CMDID_RECOMMENDFOLLOWLIST = 362;
  public static final int _CMDID_RECOMMENDFRIENDLIST = 316;
  public static final int _CMDID_RECVGIFT = 106;
  public static final int _CMDID_REGLOC = 110;
  public static final int _CMDID_REGPUSHINFO = 105;
  public static final int _CMDID_REPORT = 100;
  public static final int _CMDID_REPORTGAMEACTION_V2 = 302;
  public static final int _CMDID_REPORTGAMEACTION_V3 = 114;
  public static final int _CMDID_REPORTLBSPARTYINFO = 123;
  public static final int _CMDID_REPORTUPLOADVIDEO = 131;
  public static final int _CMDID_REPORTVTS = 137;
  public static final int _CMDID_SAYHI = 327;
  public static final int _CMDID_SEARCHGAME = 108;
  public static final int _CMDID_SEARCHGROUP = 198;
  public static final int _CMDID_SEARCHUSERINFO = 317;
  public static final int _CMDID_SENDMSG = 111;
  public static final int _CMDID_SETFRIENDALIAS = 309;
  public static final int _CMDID_SETMOOD = 304;
  public static final int _CMDID_SETPICPROFILE = 305;
  public static final int _CMDID_SETPRIVACY = 308;
  public static final int _CMDID_SETPROFILE = 306;
  public static final int _CMDID_SETPWD = 332;
  public static final int _CMDID_SETSYBACCOUNTNAME = 323;
  public static final int _CMDID_SHAREGROUP = 201;
  public static final int _CMDID_START = 1;
  public static final int _CMDID_UNKNOWN = 0;
  public static final int _CMDID_UPLOADGROUPPIC = 200;
  public static final int _CMDID_USERPERIODGROUP = 328;
  public static final int _CMDID_VERIFYMSGCODE = 331;
  public static final int _CMDID_VIDEOPRAISE = 138;
  private static CMDID[] __values;
  private String __T = new String();
  private int __value;

  static
  {
    if (!CMDID.class.desiredAssertionStatus());
    for (boolean bool = true; ; bool = false)
    {
      $assertionsDisabled = bool;
      __values = new CMDID[''];
      CMDID_UNKNOWN = new CMDID(0, 0, "CMDID_UNKNOWN");
      CMDID_START = new CMDID(1, 1, "CMDID_START");
      CMDID_GETGAMEBASE = new CMDID(2, 2, "CMDID_GETGAMEBASE");
      CMDID_GETGAMEDETAIL = new CMDID(3, 3, "CMDID_GETGAMEDETAIL");
      CMDID_GETGAMEPAGELIST = new CMDID(4, 4, "CMDID_GETGAMEPAGELIST");
      CMDID_GETORDERLIST = new CMDID(5, 5, "CMDID_GETORDERLIST");
      CMDID_CHECKINSTALLGAME = new CMDID(6, 6, "CMDID_CHECKINSTALLGAME");
      CMDID_GETSPLASH = new CMDID(7, 7, "CMDID_GETSPLASH");
      CMDID_GETSYSMSG = new CMDID(8, 8, "CMDID_GETSYSMSG");
      CMDID_GETAD = new CMDID(9, 9, "CMDID_GETAD");
      CMDID_GETMIME = new CMDID(10, 10, "CMDID_GETMIME");
      CMDID_GETVAR = new CMDID(11, 11, "CMDID_GETVAR");
      CMDID_GETGIFTCONF = new CMDID(12, 12, "CMDID_GETGIFTCONF");
      CMDID_GETHOTWORD = new CMDID(13, 13, "CMDID_GETHOTWORD");
      CMDID_GETGAMERECOMMEND = new CMDID(14, 14, "CMDID_GETGAMERECOMMEND");
      CMDID_DAILYRECOMMEND = new CMDID(15, 15, "CMDID_DAILYRECOMMEND");
      CMDID_MYGAMES = new CMDID(16, 30, "CMDID_MYGAMES");
      CMDID_DAUCOUNT = new CMDID(17, 31, "CMDID_DAUCOUNT");
      CMDID_REPORT = new CMDID(18, 100, "CMDID_REPORT");
      CMDID_KEEPALIVE = new CMDID(19, 101, "CMDID_KEEPALIVE");
      CMDID_GETSTATDATA = new CMDID(20, 102, "CMDID_GETSTATDATA");
      CMDID_GETACCESSTOKEN = new CMDID(21, 103, "CMDID_GETACCESSTOKEN");
      CMDID_GETEXPIDENT = new CMDID(22, 104, "CMDID_GETEXPIDENT");
      CMDID_REGPUSHINFO = new CMDID(23, 105, "CMDID_REGPUSHINFO");
      CMDID_RECVGIFT = new CMDID(24, 106, "CMDID_RECVGIFT");
      CMDID_GETUSERGIFT = new CMDID(25, 107, "CMDID_GETUSERGIFT");
      CMDID_SEARCHGAME = new CMDID(26, 108, "CMDID_SEARCHGAME");
      CMDID_COMPLETEWORD = new CMDID(27, 109, "CMDID_COMPLETEWORD");
      CMDID_REGLOC = new CMDID(28, 110, "CMDID_REGLOC");
      CMDID_SENDMSG = new CMDID(29, 111, "CMDID_SENDMSG");
      CMDID_CHECKPERSONRELATED = new CMDID(30, 112, "CMDID_CHECKPERSONRELATED");
      CMDID_ADDPOST = new CMDID(31, 113, "CMDID_ADDPOST");
      CMDID_REPORTGAMEACTION_V3 = new CMDID(32, 114, "CMDID_REPORTGAMEACTION_V3");
      CMDID_GETPLUGINNOTICE = new CMDID(33, 115, "CMDID_GETPLUGINNOTICE");
      CMDID_CREATEPARTY = new CMDID(34, 120, "CMDID_CREATEPARTY");
      CMDID_GETNEARBYPARTYLIST = new CMDID(35, 121, "CMDID_GETNEARBYPARTYLIST");
      CMDID_GETLBSGAMELIST = new CMDID(36, 122, "CMDID_GETLBSGAMELIST");
      CMDID_REPORTLBSPARTYINFO = new CMDID(37, 123, "CMDID_REPORTLBSPARTYINFO");
      CMDID_GETGAMEURLINFO = new CMDID(38, 124, "CMDID_GETGAMEURLINFO");
      CMDID_DISCERNGAMEPKG = new CMDID(39, 125, "CMDID_DISCERNGAMEPKG");
      CMDID_GETINFOPAGELIST = new CMDID(40, 126, "CMDID_GETINFOPAGELIST");
      CMDID_APPLYUPLOADVIDEO = new CMDID(41, 130, "CMDID_APPLYUPLOADVIDEO");
      CMDID_REPORTUPLOADVIDEO = new CMDID(42, 131, "CMDID_REPORTUPLOADVIDEO");
      CMDID_DELETEVIDEO = new CMDID(43, 132, "CMDID_DELETEVIDEO");
      CMDID_GETUSERVIDEOLIST = new CMDID(44, 133, "CMDID_GETUSERVIDEOLIST");
      CMDID_GETVIDEOLIST = new CMDID(45, 134, "CMDID_GETVIDEOLIST");
      CMDID_GETVIDEOPLAYURL = new CMDID(46, 135, "CMDID_GETVIDEOPLAYURL");
      CMDID_GETPOPVIDEOLIST = new CMDID(47, 136, "CMDID_GETPOPVIDEOLIST");
      CMDID_REPORTVTS = new CMDID(48, 137, "CMDID_REPORTVTS");
      CMDID_VIDEOPRAISE = new CMDID(49, 138, "CMDID_VIDEOPRAISE");
      CMDID_ADDVCOMMENT = new CMDID(50, 150, "CMDID_ADDVCOMMENT");
      CMDID_MODVCOMMENT = new CMDID(51, 151, "CMDID_MODVCOMMENT");
      CMDID_DELVCOMMENT = new CMDID(52, 152, "CMDID_DELVCOMMENT");
      CMDID_GETVCOMMENT = new CMDID(53, 153, "CMDID_GETVCOMMENT");
      CMDID_GETVCOMMLIST = new CMDID(54, 154, "CMDID_GETVCOMMLIST");
      CMDID_HOTGAMELIST = new CMDID(55, 160, "CMDID_HOTGAMELIST");
      CMDID_GAMESEARCH_V2 = new CMDID(56, 161, "CMDID_GAMESEARCH_V2");
      CMDID_QUERYGAMEUSERLIST = new CMDID(57, 162, "CMDID_QUERYGAMEUSERLIST");
      CMDID_GETFEEDLIST = new CMDID(58, 170, "CMDID_GETFEEDLIST");
      CMDID_ADDFEEDCOMMENT = new CMDID(59, 171, "CMDID_ADDFEEDCOMMENT");
      CMDID_DELETEFEEDCOMMENT = new CMDID(60, 172, "CMDID_DELETEFEEDCOMMENT");
      CMDID_DELETEFEED = new CMDID(61, 173, "CMDID_DELETEFEED");
      CMDID_GETFEEDCOMMENT = new CMDID(62, 174, "CMDID_GETFEEDCOMMENT");
      CMDID_GETFEEDPRAISE = new CMDID(63, 175, "CMDID_GETFEEDPRAISE");
      CMDID_GETFEEDDETAIL = new CMDID(64, 176, "CMDID_GETFEEDDETAIL");
      CMDID_DOFEEDPRAISE = new CMDID(65, 177, "CMDID_DOFEEDPRAISE");
      CMDID_GETMYFEEDNOTICE = new CMDID(66, 178, "CMDID_GETMYFEEDNOTICE");
      CMDID_CHECKFEEDUPDATE = new CMDID(67, 179, "CMDID_CHECKFEEDUPDATE");
      CMDID_ADDGAMETALK = new CMDID(68, 180, "CMDID_ADDGAMETALK");
      CMDID_CREATEGROUP = new CMDID(69, 190, "CMDID_CREATEGROUP");
      CMDID_MODIFYGROUP = new CMDID(70, 191, "CMDID_MODIFYGROUP");
      CMDID_JOINGROUP = new CMDID(71, 192, "CMDID_JOINGROUP");
      CMDID_QUITGROUP = new CMDID(72, 193, "CMDID_QUITGROUP");
      CMDID_DELGROUPMEMBER = new CMDID(73, 194, "CMDID_DELGROUPMEMBER");
      CMDID_GETGROUP = new CMDID(74, 195, "CMDID_GETGROUP");
      CMDID_GETMYGROUPLIST = new CMDID(75, 196, "CMDID_GETMYGROUPLIST");
      CMDID_GETRECOMMENDGROUP = new CMDID(76, 197, "CMDID_GETRECOMMENDGROUP");
      CMDID_SEARCHGROUP = new CMDID(77, 198, "CMDID_SEARCHGROUP");
      CMDID_CHECKCREATEGROUP = new CMDID(78, 199, "CMDID_CHECKCREATEGROUP");
      CMDID_UPLOADGROUPPIC = new CMDID(79, 200, "CMDID_UPLOADGROUPPIC");
      CMDID_SHAREGROUP = new CMDID(80, 201, "CMDID_SHAREGROUP");
      CMDID_GETUSERINFO_V2 = new CMDID(81, 300, "CMDID_GETUSERINFO_V2");
      CMDID_GETFRIENDLIST_V2 = new CMDID(82, 301, "CMDID_GETFRIENDLIST_V2");
      CMDID_REPORTGAMEACTION_V2 = new CMDID(83, 302, "CMDID_REPORTGAMEACTION_V2");
      CMDID_GETFRIENDINFO_V2 = new CMDID(84, 303, "CMDID_GETFRIENDINFO_V2");
      CMDID_SETMOOD = new CMDID(85, 304, "CMDID_SETMOOD");
      CMDID_SETPICPROFILE = new CMDID(86, 305, "CMDID_SETPICPROFILE");
      CMDID_SETPROFILE = new CMDID(87, 306, "CMDID_SETPROFILE");
      CMDID_SETPRIVACY = new CMDID(88, 308, "CMDID_SETPRIVACY");
      CMDID_SETFRIENDALIAS = new CMDID(89, 309, "CMDID_SETFRIENDALIAS");
      CMDID_ADDFRIEND = new CMDID(90, 310, "CMDID_ADDFRIEND");
      CMDID_ADDFRIENDWITHMSG = new CMDID(91, 311, "CMDID_ADDFRIENDWITHMSG");
      CMDID_CONFIRMFRIEND = new CMDID(92, 312, "CMDID_CONFIRMFRIEND");
      CMDID_DELETEFRIEND = new CMDID(93, 313, "CMDID_DELETEFRIEND");
      CMDID_CHANGEFRIENDGROUP = new CMDID(94, 314, "CMDID_CHANGEFRIENDGROUP");
      CMDID_GETSELFDEFFRIENDLIST = new CMDID(95, 315, "CMDID_GETSELFDEFFRIENDLIST");
      CMDID_RECOMMENDFRIENDLIST = new CMDID(96, 316, "CMDID_RECOMMENDFRIENDLIST");
      CMDID_SEARCHUSERINFO = new CMDID(97, 317, "CMDID_SEARCHUSERINFO");
      CMDID_GETFRIENDCONFIRM = new CMDID(98, 318, "CMDID_GETFRIENDCONFIRM");
      CMDID_GETQQFRIENDGROUP = new CMDID(99, 319, "CMDID_GETQQFRIENDGROUP");
      CMDID_GETQQFRIENDBYGROUP = new CMDID(100, 320, "CMDID_GETQQFRIENDBYGROUP");
      CMDID_GETLOGINTYPE = new CMDID(101, 321, "CMDID_GETLOGINTYPE");
      CMDID_GETSYBACCESSTOKEN = new CMDID(102, 322, "CMDID_GETSYBACCESSTOKEN");
      CMDID_SETSYBACCOUNTNAME = new CMDID(103, 323, "CMDID_SETSYBACCOUNTNAME");
      CMDID_BIND3RDPARTYACCOUNT = new CMDID(104, 324, "CMDID_BIND3RDPARTYACCOUNT");
      CMDID_BINDACCOUNTFORCE = new CMDID(105, 325, "CMDID_BINDACCOUNTFORCE");
      CMDID_GETWECHATFRIENDLIST = new CMDID(106, 326, "CMDID_GETWECHATFRIENDLIST");
      CMDID_SAYHI = new CMDID(107, 327, "CMDID_SAYHI");
      CMDID_USERPERIODGROUP = new CMDID(108, 328, "CMDID_USERPERIODGROUP");
      CMDID_INVITE = new CMDID(109, 329, "CMDID_INVITE");
      CMDID_GETVERIFYCODE = new CMDID(110, 330, "CMDID_GETVERIFYCODE");
      CMDID_VERIFYMSGCODE = new CMDID(111, 331, "CMDID_VERIFYMSGCODE");
      CMDID_SETPWD = new CMDID(112, 332, "CMDID_SETPWD");
      CMDID_LOGINPHONEUSER = new CMDID(113, 333, "CMDID_LOGINPHONEUSER");
      CMDID_NEARBYUSER = new CMDID(114, 334, "CMDID_NEARBYUSER");
      CMDID_CLEARLOCATIONRECORD = new CMDID(115, 335, "CMDID_CLEARLOCATIONRECORD");
      CMDID_LOCATIONRECORDEXIST = new CMDID(116, 336, "CMDID_LOCATIONRECORDEXIST");
      CMDID_FOLLOW = new CMDID(117, 360, "CMDID_FOLLOW");
      CMDID_GETUSERFOLLOWLIST = new CMDID(118, 361, "CMDID_GETUSERFOLLOWLIST");
      CMDID_RECOMMENDFOLLOWLIST = new CMDID(119, 362, "CMDID_RECOMMENDFOLLOWLIST");
      CMDID_GETUSERFANSLIST = new CMDID(120, 363, "CMDID_GETUSERFANSLIST");
      CMDID_QMI_START = new CMDID(121, 1001, "CMDID_QMI_START");
      CMDID_QMI_GETGAMEPAGELIST = new CMDID(122, 1002, "CMDID_QMI_GETGAMEPAGELIST");
      CMDID_QMI_GETCHATTERINFO = new CMDID(123, 1003, "CMDID_QMI_GETCHATTERINFO");
      CMDID_QMI_GETGAMEEXTENDINFO = new CMDID(124, 1004, "CMDID_QMI_GETGAMEEXTENDINFO");
      CMDID_QMI_GETGAMEJOYLDLINFO = new CMDID(125, 1005, "CMDID_QMI_GETGAMEJOYLDLINFO");
      CMDID_QMI_GETGAMEJOYRECORDINGCONF = new CMDID(126, 1006, "CMDID_QMI_GETGAMEJOYRECORDINGCONF");
      CMDID_QMI_CHECKGAMES = new CMDID(127, 1007, "CMDID_QMI_CHECKGAMES");
      CMDID_QMI_GETGAMEJOYRECORDINGWHITELIST = new CMDID(128, 1008, "CMDID_QMI_GETGAMEJOYRECORDINGWHITELIST");
      CMDID_QMI_GETSDKSYBACCESSTOKEN = new CMDID(129, 1009, "CMDID_QMI_GETSDKSYBACCESSTOKEN");
      CMDID_MAX = new CMDID(130, 1010, "CMDID_MAX");
      return;
    }
  }

  private CMDID(int paramInt1, int paramInt2, String paramString)
  {
    this.__T = paramString;
    this.__value = paramInt2;
    __values[paramInt1] = this;
  }

  public static CMDID a(int paramInt)
  {
    for (int i = 0; i < __values.length; i++)
      if (__values[i].a() == paramInt)
        return __values[i];
    if (!$assertionsDisabled)
      throw new AssertionError();
    return null;
  }

  public static CMDID a(String paramString)
  {
    for (int i = 0; i < __values.length; i++)
      if (__values[i].toString().equals(paramString))
        return __values[i];
    if (!$assertionsDisabled)
      throw new AssertionError();
    return null;
  }

  public int a()
  {
    return this.__value;
  }

  public String toString()
  {
    return this.__T;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     CobraHallQmiProto.CMDID
 * JD-Core Version:    0.6.0
 */