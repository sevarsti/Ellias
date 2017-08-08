package com.tencent.qqgamemi.protocol;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import com.tencent.qqgamemi.common.TLog;

public class ServerType
{
  private static final String F = ServerType.class.getSimpleName();
  private static String G = "public";
  public static final int a = 1;
  public static final String b = "test";
  public static final String c = "http://gamehalltest.cs0309.3g.qq.com";
  public static final String d = "http://gamehalltest.cs0309.3g.qq.com/cobrahall/m";
  public static final String e = "1213621173";
  public static final String f = "http://gamehalltest.cs0309.3g.qq.com/gamejoy/m";
  public static final int g = 2;
  public static final String h = "prepublic";
  public static final String i = "http://gamehallpre.cs0309.3g.qq.com";
  public static final String j = "http://gamehallpre.cs0309.3g.qq.com/cobrahall/m";
  public static final String k = "1213621180";
  public static final String l = "http://gamehallpre.cs0309.3g.qq.com/gamejoy/m";
  public static final int m = 3;
  public static final String n = "public";
  public static final String o = "http://gamehall.3g.qq.com";
  public static final String p = "http://gamehall.3g.qq.com/cobrahall/m";
  public static final String q = "1213621180";
  public static final String r = "http://gamehall.3g.qq.com/gamejoy/m";
  public static final int s = 4;
  public static final String t = "develop";
  public static final String u = "http://gamehalldev.cs0309.3g.qq.com";
  public static final String v = "http://gamehalldev.cs0309.3g.qq.com/cobrahall/m";
  public static final String w = "1213621173";
  public static final String x = "http://gamehalldev.cs0309.3g.qq.com/gamejoy/m";
  public static ServerType[] y;
  public String A;
  public String B;
  public String C;
  public String D;
  public String E;
  public int z;

  static
  {
    ServerType[] arrayOfServerType = new ServerType[4];
    arrayOfServerType[0] = new ServerType(1, "test", "http://gamehalltest.cs0309.3g.qq.com", "http://gamehalltest.cs0309.3g.qq.com/cobrahall/m", "1213621173", "http://gamehalltest.cs0309.3g.qq.com/gamejoy/m");
    arrayOfServerType[1] = new ServerType(2, "prepublic", "http://gamehallpre.cs0309.3g.qq.com", "http://gamehallpre.cs0309.3g.qq.com/cobrahall/m", "1213621180", "http://gamehallpre.cs0309.3g.qq.com/gamejoy/m");
    arrayOfServerType[2] = new ServerType(3, "public", "http://gamehall.3g.qq.com", "http://gamehall.3g.qq.com/cobrahall/m", "1213621180", "http://gamehall.3g.qq.com/gamejoy/m");
    arrayOfServerType[3] = new ServerType(4, "develop", "http://gamehalldev.cs0309.3g.qq.com", "http://gamehalldev.cs0309.3g.qq.com/cobrahall/m", "1213621173", "http://gamehalldev.cs0309.3g.qq.com/gamejoy/m");
    y = arrayOfServerType;
  }

  public ServerType(int paramInt, String paramString1, String paramString2, String paramString3, String paramString4, String paramString5)
  {
    this.z = paramInt;
    this.A = paramString1;
    this.B = paramString2;
    this.C = paramString3;
    this.D = paramString4;
    this.E = paramString5;
  }

  public static ServerType a(int paramInt)
  {
    switch (paramInt)
    {
    default:
      return null;
    case 1:
      return y[0];
    case 2:
      return y[1];
    case 3:
      return y[2];
    case 4:
    }
    return y[3];
  }

  public static ServerType a(String paramString)
  {
    for (ServerType localServerType : y)
      if (localServerType.A.equals(paramString))
        return localServerType;
    return null;
  }

  public static String a(Context paramContext)
  {
    String str = paramContext.getSharedPreferences("ServerType", 0).getString("type", "public");
    TLog.c(F, "get type:" + str);
    return str;
  }

  public static void a(Context paramContext, String paramString)
  {
    TLog.c(F, "set type:" + paramString);
    SharedPreferences.Editor localEditor = paramContext.getSharedPreferences("ServerType", 2).edit();
    localEditor.putString("type", paramString);
    localEditor.commit();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.protocol.ServerType
 * JD-Core Version:    0.6.0
 */