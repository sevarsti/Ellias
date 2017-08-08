package com.tencent.tmassistantsdk.downloadservice;

import android.text.TextUtils;
import java.util.HashMap;

public class f
{
  protected static f a = null;
  protected boolean b = true;
  protected boolean c = false;
  protected int d = 5;

  public f()
  {
    e();
  }

  static int a(String paramString)
  {
    if (paramString.equalsIgnoreCase("WIFI"))
      throw new UnsupportedOperationException("Split is not allowed in current version. netType: " + paramString);
    if (paramString.contains("net"));
    do
      return 921600;
    while (!paramString.contains("wap"));
    return 409600;
  }

  public static f a()
  {
    monitorenter;
    try
    {
      if (a == null)
        a = new f();
      f localf = a;
      return localf;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  private void e()
  {
    HashMap localHashMap = com.tencent.tmassistantsdk.f.c.f.c();
    String str1 = (String)localHashMap.get("isTaskAutoResume");
    boolean bool3;
    boolean bool2;
    if ((str1 != null) && (str1.length() > 0))
    {
      if (str1.equals("false"))
      {
        bool3 = false;
        this.b = bool3;
      }
    }
    else
    {
      String str2 = (String)localHashMap.get("isDownloadWifiOnly");
      if ((str2 != null) && (str2.length() > 0))
      {
        boolean bool1 = str2.equals("false");
        bool2 = false;
        if (!bool1)
          break label129;
      }
    }
    while (true)
    {
      this.c = bool2;
      String str3 = (String)localHashMap.get("maxTaskNum");
      if ((str3 != null) && (str3.length() > 0))
        this.d = Integer.valueOf(str3).intValue();
      return;
      bool3 = true;
      break;
      label129: bool2 = true;
    }
  }

  public void a(int paramInt)
  {
    if (a.d == paramInt)
      return;
    a.d = paramInt;
    com.tencent.tmassistantsdk.f.c.f.a("maxTaskNum", String.valueOf(paramInt), "Integer");
  }

  public void a(boolean paramBoolean)
  {
    if (a.b == paramBoolean)
      return;
    a.b = paramBoolean;
    com.tencent.tmassistantsdk.f.c.f.a("isTaskAutoResume", String.valueOf(paramBoolean), "boolean");
  }

  public boolean a(String paramString1, String paramString2)
  {
    if (TextUtils.isEmpty(paramString1));
    do
    {
      return false;
      if (paramString2.equalsIgnoreCase("WIFI"))
        return true;
    }
    while ((!paramString2.contains("net")) || (paramString1.equalsIgnoreCase("WIFI")));
    return true;
  }

  public void b(boolean paramBoolean)
  {
    if (a.c == paramBoolean)
      return;
    a.c = paramBoolean;
    com.tencent.tmassistantsdk.f.c.f.a("isDownloadWifiOnly", String.valueOf(paramBoolean), "boolean");
  }

  public boolean b()
  {
    return this.c;
  }

  public int c()
  {
    return this.d;
  }

  public boolean d()
  {
    if (this.b)
    {
      String str = c.b();
      if ((str.contains("wifi")) || (str.contains("net")))
        return true;
    }
    return false;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.tmassistantsdk.downloadservice.f
 * JD-Core Version:    0.6.0
 */