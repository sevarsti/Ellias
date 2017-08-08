package com.pay.tool;

public class APAppDataInterface
{
  private static APAppDataInterface t = null;
  private String a = "cpay_1.3.5";
  private String b = "SAjUAcATIzxnw4v3SAjUAcATIzxnw4v3";
  private String c = "";
  private String d = "";
  private String e = "";
  private boolean f = false;
  private String g = "";
  private String h = "release";
  private String i = "";
  private String j = "";
  private boolean k = false;
  private boolean l = true;
  private String m = "";
  private int n = 1;
  private boolean o = true;
  private int p = 0;
  private String q = "";
  private boolean r = true;
  private String s = "r";

  public static void release()
  {
    t = null;
  }

  public static APAppDataInterface singleton()
  {
    if (t == null)
      t = new APAppDataInterface();
    return t;
  }

  public int getAppOrientation()
  {
    return t.n;
  }

  public String getBaseKey()
  {
    return t.b;
  }

  public boolean getChangeKey()
  {
    return t.f;
  }

  public String getCryptKeyTime()
  {
    return t.e;
  }

  public String getCryptoKey()
  {
    return t.d;
  }

  public String getCustomCgi()
  {
    return t.s;
  }

  public String getDeviceInfo()
  {
    return t.q;
  }

  public String getEnv()
  {
    return t.h;
  }

  public boolean getIsOwnResearch()
  {
    return t.l;
  }

  public boolean getIsShowSaveNum()
  {
    return t.o;
  }

  public String getMacAdress()
  {
    return t.i;
  }

  public int getNetworkState()
  {
    return t.p;
  }

  public String getOfferid()
  {
    return t.g;
  }

  public boolean getReloginInSDK()
  {
    return t.k;
  }

  public String getSecretKey()
  {
    return t.c;
  }

  public String getSysServerIP()
  {
    String str = singleton().getEnv();
    if (t.j.equals(""))
    {
      if (!str.equals("dev"))
        break label45;
      t.j = "183.60.36.92";
    }
    while (true)
    {
      return t.j;
      label45: if (str.equals("test"))
      {
        t.j = "sandbox.api.unipay.qq.com";
        continue;
      }
      t.j = "api.unipay.qq.com";
    }
  }

  public String getVid()
  {
    return t.a;
  }

  public String getWechatAppId()
  {
    return t.m;
  }

  public boolean isElseNumberVisible()
  {
    return t.r;
  }

  public void setAppOrientation(int paramInt)
  {
    t.n = paramInt;
  }

  public void setBaseKey(String paramString)
  {
    t.b = paramString;
  }

  public void setChangeKey(boolean paramBoolean)
  {
    t.f = paramBoolean;
  }

  public void setCryptKey(String paramString)
  {
    if (paramString == null)
    {
      t.d = "";
      return;
    }
    t.d = paramString;
  }

  public void setCryptKeyTime(String paramString)
  {
    if (paramString == null)
    {
      t.e = "";
      return;
    }
    t.e = paramString;
  }

  public void setCustomCgi(String paramString)
  {
    t.s = paramString;
  }

  public void setDeviceInfo(String paramString)
  {
    t.q = paramString;
  }

  public void setElseNumberVisible(boolean paramBoolean)
  {
    t.r = paramBoolean;
  }

  public void setEnv(String paramString)
  {
    t.h = paramString;
  }

  public void setIsOwnResearch(boolean paramBoolean)
  {
    t.l = paramBoolean;
  }

  public void setIsShowSaveNum(boolean paramBoolean)
  {
    t.o = paramBoolean;
  }

  public void setMacAdress(String paramString)
  {
    t.i = paramString;
  }

  public void setNetworkState(int paramInt)
  {
    t.p = paramInt;
  }

  public void setOfferid(String paramString)
  {
    t.g = paramString;
  }

  public void setReloginInSDK(boolean paramBoolean)
  {
    t.k = paramBoolean;
  }

  public void setSecretKey(String paramString)
  {
    if (paramString == null)
    {
      t.c = "";
      return;
    }
    t.c = paramString;
  }

  public void setSysServerIP(String paramString)
  {
    t.j = paramString;
  }

  public void setVid(String paramString)
  {
    t.a = paramString;
  }

  public void setWechatAppId(String paramString)
  {
    t.m = paramString;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.tool.APAppDataInterface
 * JD-Core Version:    0.6.0
 */