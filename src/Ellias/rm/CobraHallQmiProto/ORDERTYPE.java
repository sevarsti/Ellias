package CobraHallQmiProto;

import java.io.Serializable;

public final class ORDERTYPE
  implements Serializable
{
  public static final ORDERTYPE ORDERTYPE_APPRECOMM;
  public static final ORDERTYPE ORDERTYPE_ARKRECOMMEND;
  public static final ORDERTYPE ORDERTYPE_CATEGORY;
  public static final ORDERTYPE ORDERTYPE_CATEGORY2;
  public static final ORDERTYPE ORDERTYPE_HALLAPPRECOMM;
  public static final ORDERTYPE ORDERTYPE_HOTRANK;
  public static final ORDERTYPE ORDERTYPE_MINIGAME;
  public static final ORDERTYPE ORDERTYPE_NEWRELEASE;
  public static final ORDERTYPE ORDERTYPE_ONLINEGAME;
  public static final ORDERTYPE ORDERTYPE_PARTYGAME;
  public static final ORDERTYPE ORDERTYPE_PARTYPLUGIN;
  public static final ORDERTYPE ORDERTYPE_QMIPLUGIN;
  public static final ORDERTYPE ORDERTYPE_RECOMMEND;
  public static final ORDERTYPE ORDERTYPE_SDK_QMIPLUGIN;
  public static final ORDERTYPE ORDERTYPE_SINGLEGAME;
  public static final ORDERTYPE ORDERTYPE_SOCIALGAME;
  public static final ORDERTYPE ORDERTYPE_SPECIALZONE;
  public static final int _ORDERTYPE_APPRECOMM = 12;
  public static final int _ORDERTYPE_ARKRECOMMEND = 15;
  public static final int _ORDERTYPE_CATEGORY = 8;
  public static final int _ORDERTYPE_CATEGORY2 = 9;
  public static final int _ORDERTYPE_HALLAPPRECOMM = 13;
  public static final int _ORDERTYPE_HOTRANK = 2;
  public static final int _ORDERTYPE_MINIGAME = 4;
  public static final int _ORDERTYPE_NEWRELEASE = 3;
  public static final int _ORDERTYPE_ONLINEGAME = 6;
  public static final int _ORDERTYPE_PARTYGAME = 10;
  public static final int _ORDERTYPE_PARTYPLUGIN = 11;
  public static final int _ORDERTYPE_QMIPLUGIN = 16;
  public static final int _ORDERTYPE_RECOMMEND = 1;
  public static final int _ORDERTYPE_SDK_QMIPLUGIN = 17;
  public static final int _ORDERTYPE_SINGLEGAME = 5;
  public static final int _ORDERTYPE_SOCIALGAME = 7;
  public static final int _ORDERTYPE_SPECIALZONE = 14;
  private static ORDERTYPE[] __values;
  private String __T = new String();
  private int __value;

  static
  {
    if (!ORDERTYPE.class.desiredAssertionStatus());
    for (boolean bool = true; ; bool = false)
    {
      $assertionsDisabled = bool;
      __values = new ORDERTYPE[17];
      ORDERTYPE_RECOMMEND = new ORDERTYPE(0, 1, "ORDERTYPE_RECOMMEND");
      ORDERTYPE_HOTRANK = new ORDERTYPE(1, 2, "ORDERTYPE_HOTRANK");
      ORDERTYPE_NEWRELEASE = new ORDERTYPE(2, 3, "ORDERTYPE_NEWRELEASE");
      ORDERTYPE_MINIGAME = new ORDERTYPE(3, 4, "ORDERTYPE_MINIGAME");
      ORDERTYPE_SINGLEGAME = new ORDERTYPE(4, 5, "ORDERTYPE_SINGLEGAME");
      ORDERTYPE_ONLINEGAME = new ORDERTYPE(5, 6, "ORDERTYPE_ONLINEGAME");
      ORDERTYPE_SOCIALGAME = new ORDERTYPE(6, 7, "ORDERTYPE_SOCIALGAME");
      ORDERTYPE_CATEGORY = new ORDERTYPE(7, 8, "ORDERTYPE_CATEGORY");
      ORDERTYPE_CATEGORY2 = new ORDERTYPE(8, 9, "ORDERTYPE_CATEGORY2");
      ORDERTYPE_PARTYGAME = new ORDERTYPE(9, 10, "ORDERTYPE_PARTYGAME");
      ORDERTYPE_PARTYPLUGIN = new ORDERTYPE(10, 11, "ORDERTYPE_PARTYPLUGIN");
      ORDERTYPE_APPRECOMM = new ORDERTYPE(11, 12, "ORDERTYPE_APPRECOMM");
      ORDERTYPE_HALLAPPRECOMM = new ORDERTYPE(12, 13, "ORDERTYPE_HALLAPPRECOMM");
      ORDERTYPE_SPECIALZONE = new ORDERTYPE(13, 14, "ORDERTYPE_SPECIALZONE");
      ORDERTYPE_ARKRECOMMEND = new ORDERTYPE(14, 15, "ORDERTYPE_ARKRECOMMEND");
      ORDERTYPE_QMIPLUGIN = new ORDERTYPE(15, 16, "ORDERTYPE_QMIPLUGIN");
      ORDERTYPE_SDK_QMIPLUGIN = new ORDERTYPE(16, 17, "ORDERTYPE_SDK_QMIPLUGIN");
      return;
    }
  }

  private ORDERTYPE(int paramInt1, int paramInt2, String paramString)
  {
    this.__T = paramString;
    this.__value = paramInt2;
    __values[paramInt1] = this;
  }

  public static ORDERTYPE a(int paramInt)
  {
    for (int i = 0; i < __values.length; i++)
      if (__values[i].a() == paramInt)
        return __values[i];
    if (!$assertionsDisabled)
      throw new AssertionError();
    return null;
  }

  public static ORDERTYPE a(String paramString)
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
 * Qualified Name:     CobraHallQmiProto.ORDERTYPE
 * JD-Core Version:    0.6.0
 */