package CobraHallQmiProto;

import java.io.Serializable;

public final class COBRAUSERINFOKEY
  implements Serializable
{
  public static final COBRAUSERINFOKEY CUIK_AGE;
  public static final COBRAUSERINFOKEY CUIK_BACKGROUNDPIC;
  public static final COBRAUSERINFOKEY CUIK_BIRTHDAY;
  public static final COBRAUSERINFOKEY CUIK_CITY;
  public static final COBRAUSERINFOKEY CUIK_CONSTELLATION;
  public static final COBRAUSERINFOKEY CUIK_CONTINUELOGINDAYS;
  public static final COBRAUSERINFOKEY CUIK_COUNTRY;
  public static final COBRAUSERINFOKEY CUIK_DESC1;
  public static final COBRAUSERINFOKEY CUIK_FACE;
  public static final COBRAUSERINFOKEY CUIK_FACE_BIG;
  public static final COBRAUSERINFOKEY CUIK_FACE_MID;
  public static final COBRAUSERINFOKEY CUIK_FACE_SMALL;
  public static final COBRAUSERINFOKEY CUIK_GAMEVIP;
  public static final COBRAUSERINFOKEY CUIK_GENDER;
  public static final COBRAUSERINFOKEY CUIK_GETSCORETHISLEVEL;
  public static final COBRAUSERINFOKEY CUIK_ISHALLUSER;
  public static final COBRAUSERINFOKEY CUIK_LASTLOGINTIME;
  public static final COBRAUSERINFOKEY CUIK_LEVEL;
  public static final COBRAUSERINFOKEY CUIK_LOGINTIMEPERDAY;
  public static final COBRAUSERINFOKEY CUIK_MOOD;
  public static final COBRAUSERINFOKEY CUIK_MOSTFAVGAME;
  public static final COBRAUSERINFOKEY CUIK_NICKNAME;
  public static final COBRAUSERINFOKEY CUIK_ONLINESTATE;
  public static final COBRAUSERINFOKEY CUIK_PROVICE;
  public static final COBRAUSERINFOKEY CUIK_QQGAMELEVEL;
  public static final COBRAUSERINFOKEY CUIK_RANKINFRIENDS;
  public static final COBRAUSERINFOKEY CUIK_SCOREOFLEVEL;
  public static final COBRAUSERINFOKEY CUIK_SCOREREWARDTODAY;
  public static final COBRAUSERINFOKEY CUIK_TOTALLOGINDAYS;
  public static final COBRAUSERINFOKEY CUIK_TOTALLOGINTIME;
  public static final COBRAUSERINFOKEY CUIK_TOTALSCORE;
  public static final COBRAUSERINFOKEY CUIK_TOTOALPLAYGAMENUM;
  public static final COBRAUSERINFOKEY CUIK_UIN;
  public static final int _CUIK_AGE = 3;
  public static final int _CUIK_BACKGROUNDPIC = 212;
  public static final int _CUIK_BIRTHDAY = 10;
  public static final int _CUIK_CITY = 6;
  public static final int _CUIK_CONSTELLATION = 213;
  public static final int _CUIK_CONTINUELOGINDAYS = 202;
  public static final int _CUIK_COUNTRY = 4;
  public static final int _CUIK_DESC1 = 9;
  public static final int _CUIK_FACE = 7;
  public static final int _CUIK_FACE_BIG = 13;
  public static final int _CUIK_FACE_MID = 12;
  public static final int _CUIK_FACE_SMALL = 11;
  public static final int _CUIK_GAMEVIP = 300;
  public static final int _CUIK_GENDER = 2;
  public static final int _CUIK_GETSCORETHISLEVEL = 101;
  public static final int _CUIK_ISHALLUSER = 203;
  public static final int _CUIK_LASTLOGINTIME = 201;
  public static final int _CUIK_LEVEL = 100;
  public static final int _CUIK_LOGINTIMEPERDAY = 206;
  public static final int _CUIK_MOOD = 211;
  public static final int _CUIK_MOSTFAVGAME = 207;
  public static final int _CUIK_NICKNAME = 1;
  public static final int _CUIK_ONLINESTATE = 210;
  public static final int _CUIK_PROVICE = 5;
  public static final int _CUIK_QQGAMELEVEL = 8;
  public static final int _CUIK_RANKINFRIENDS = 208;
  public static final int _CUIK_SCOREOFLEVEL = 103;
  public static final int _CUIK_SCOREREWARDTODAY = 200;
  public static final int _CUIK_TOTALLOGINDAYS = 204;
  public static final int _CUIK_TOTALLOGINTIME = 205;
  public static final int _CUIK_TOTALSCORE = 102;
  public static final int _CUIK_TOTOALPLAYGAMENUM = 209;
  public static final int _CUIK_UIN;
  private static COBRAUSERINFOKEY[] __values;
  private String __T = new String();
  private int __value;

  static
  {
    if (!COBRAUSERINFOKEY.class.desiredAssertionStatus());
    for (boolean bool = true; ; bool = false)
    {
      $assertionsDisabled = bool;
      __values = new COBRAUSERINFOKEY[33];
      CUIK_UIN = new COBRAUSERINFOKEY(0, 0, "CUIK_UIN");
      CUIK_NICKNAME = new COBRAUSERINFOKEY(1, 1, "CUIK_NICKNAME");
      CUIK_GENDER = new COBRAUSERINFOKEY(2, 2, "CUIK_GENDER");
      CUIK_AGE = new COBRAUSERINFOKEY(3, 3, "CUIK_AGE");
      CUIK_COUNTRY = new COBRAUSERINFOKEY(4, 4, "CUIK_COUNTRY");
      CUIK_PROVICE = new COBRAUSERINFOKEY(5, 5, "CUIK_PROVICE");
      CUIK_CITY = new COBRAUSERINFOKEY(6, 6, "CUIK_CITY");
      CUIK_FACE = new COBRAUSERINFOKEY(7, 7, "CUIK_FACE");
      CUIK_QQGAMELEVEL = new COBRAUSERINFOKEY(8, 8, "CUIK_QQGAMELEVEL");
      CUIK_DESC1 = new COBRAUSERINFOKEY(9, 9, "CUIK_DESC1");
      CUIK_BIRTHDAY = new COBRAUSERINFOKEY(10, 10, "CUIK_BIRTHDAY");
      CUIK_FACE_SMALL = new COBRAUSERINFOKEY(11, 11, "CUIK_FACE_SMALL");
      CUIK_FACE_MID = new COBRAUSERINFOKEY(12, 12, "CUIK_FACE_MID");
      CUIK_FACE_BIG = new COBRAUSERINFOKEY(13, 13, "CUIK_FACE_BIG");
      CUIK_LEVEL = new COBRAUSERINFOKEY(14, 100, "CUIK_LEVEL");
      CUIK_GETSCORETHISLEVEL = new COBRAUSERINFOKEY(15, 101, "CUIK_GETSCORETHISLEVEL");
      CUIK_TOTALSCORE = new COBRAUSERINFOKEY(16, 102, "CUIK_TOTALSCORE");
      CUIK_SCOREOFLEVEL = new COBRAUSERINFOKEY(17, 103, "CUIK_SCOREOFLEVEL");
      CUIK_SCOREREWARDTODAY = new COBRAUSERINFOKEY(18, 200, "CUIK_SCOREREWARDTODAY");
      CUIK_LASTLOGINTIME = new COBRAUSERINFOKEY(19, 201, "CUIK_LASTLOGINTIME");
      CUIK_CONTINUELOGINDAYS = new COBRAUSERINFOKEY(20, 202, "CUIK_CONTINUELOGINDAYS");
      CUIK_ISHALLUSER = new COBRAUSERINFOKEY(21, 203, "CUIK_ISHALLUSER");
      CUIK_TOTALLOGINDAYS = new COBRAUSERINFOKEY(22, 204, "CUIK_TOTALLOGINDAYS");
      CUIK_TOTALLOGINTIME = new COBRAUSERINFOKEY(23, 205, "CUIK_TOTALLOGINTIME");
      CUIK_LOGINTIMEPERDAY = new COBRAUSERINFOKEY(24, 206, "CUIK_LOGINTIMEPERDAY");
      CUIK_MOSTFAVGAME = new COBRAUSERINFOKEY(25, 207, "CUIK_MOSTFAVGAME");
      CUIK_RANKINFRIENDS = new COBRAUSERINFOKEY(26, 208, "CUIK_RANKINFRIENDS");
      CUIK_TOTOALPLAYGAMENUM = new COBRAUSERINFOKEY(27, 209, "CUIK_TOTOALPLAYGAMENUM");
      CUIK_ONLINESTATE = new COBRAUSERINFOKEY(28, 210, "CUIK_ONLINESTATE");
      CUIK_MOOD = new COBRAUSERINFOKEY(29, 211, "CUIK_MOOD");
      CUIK_BACKGROUNDPIC = new COBRAUSERINFOKEY(30, 212, "CUIK_BACKGROUNDPIC");
      CUIK_CONSTELLATION = new COBRAUSERINFOKEY(31, 213, "CUIK_CONSTELLATION");
      CUIK_GAMEVIP = new COBRAUSERINFOKEY(32, 300, "CUIK_GAMEVIP");
      return;
    }
  }

  private COBRAUSERINFOKEY(int paramInt1, int paramInt2, String paramString)
  {
    this.__T = paramString;
    this.__value = paramInt2;
    __values[paramInt1] = this;
  }

  public static COBRAUSERINFOKEY a(int paramInt)
  {
    for (int i = 0; i < __values.length; i++)
      if (__values[i].a() == paramInt)
        return __values[i];
    if (!$assertionsDisabled)
      throw new AssertionError();
    return null;
  }

  public static COBRAUSERINFOKEY a(String paramString)
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
 * Qualified Name:     CobraHallQmiProto.COBRAUSERINFOKEY
 * JD-Core Version:    0.6.0
 */