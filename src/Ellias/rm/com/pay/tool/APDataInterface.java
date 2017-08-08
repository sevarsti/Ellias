package com.pay.tool;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import com.pay.data.orderInfo.APOrderInfo;
import com.pay.data.userInfo.APUserInfo;

public class APDataInterface
{
  private static APDataInterface z = null;
  private boolean a = false;
  private String b = "";
  private String c = "";
  private String d = "";
  private String e = "";
  private String f = "";
  private String g = "";
  private boolean h = true;
  private byte[] i = null;
  private String j = "";
  private String k = "";
  private String l = "";
  private String m = "";
  private String n = "";
  private String o = "";
  private String p = "";
  private int q = -100;
  private String r = "";
  private String s = "";
  private String t = "";
  private String u = "";
  private String v = "";
  private int w = -1;
  private APUserInfo x;
  private APOrderInfo y;

  public static APDataInterface init()
  {
    APDataInterface localAPDataInterface = new APDataInterface();
    z = localAPDataInterface;
    return localAPDataInterface;
  }

  public static void release()
  {
    z = null;
  }

  public static APDataInterface singleton()
  {
    monitorenter;
    try
    {
      if (z == null)
      {
        APDataInterface localAPDataInterface1 = new APDataInterface();
        z = localAPDataInterface1;
        localAPDataInterface1.x = new APUserInfo();
      }
      APDataInterface localAPDataInterface2 = z;
      return localAPDataInterface2;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public Bitmap getAppResBitmap()
  {
    return BitmapFactory.decodeByteArray(z.i, 0, z.i.length);
  }

  public BitmapDrawable getAppResDrawable()
  {
    return new BitmapDrawable(BitmapFactory.decodeByteArray(z.i, 0, z.i.length));
  }

  public byte[] getAppResId()
  {
    return z.i;
  }

  public boolean getDataValid()
  {
    return z.a;
  }

  public String getDiscountExtras()
  {
    return z.n;
  }

  public String getDiscountType()
  {
    return z.l;
  }

  public String getDiscountUrl()
  {
    return z.m;
  }

  public String getEnvirnoment()
  {
    return z.g;
  }

  public String getGoods_extend()
  {
    return z.t;
  }

  public String getGoods_name()
  {
    return z.r;
  }

  public String getGoods_num()
  {
    return z.s;
  }

  public String getHfRealServiceCode()
  {
    return this.c;
  }

  public boolean getIsSendReport()
  {
    return z.h;
  }

  public String getMallUrl()
  {
    return z.u;
  }

  public String getMbSig()
  {
    return z.e;
  }

  public APOrderInfo getOrderInfo()
  {
    if (this.y == null)
      this.y = new APOrderInfo(0);
    return z.y;
  }

  public String getPayAssignChannel()
  {
    return z.k;
  }

  public String getPreSaveNumber()
  {
    return z.b;
  }

  public int getPresent_flag()
  {
    return z.q;
  }

  public String getResultUrl()
  {
    return z.v;
  }

  public int getScreenType()
  {
    return z.w;
  }

  public String getSmsInfo()
  {
    return z.f;
  }

  public String getSourceActivity()
  {
    return z.j;
  }

  public String getSuccess_url()
  {
    return z.o;
  }

  public APUserInfo getUserInfo()
  {
    return z.x;
  }

  public String getUuid()
  {
    return z.p;
  }

  public String getVCSession()
  {
    return z.d;
  }

  public void setAppResId(byte[] paramArrayOfByte)
  {
    z.i = paramArrayOfByte;
  }

  public void setDataValid(boolean paramBoolean)
  {
    z.a = paramBoolean;
  }

  public void setDiscountExtras(String paramString)
  {
    z.n = paramString;
  }

  public void setDiscountType(String paramString)
  {
    z.l = paramString;
  }

  public void setDiscountUrl(String paramString)
  {
    z.m = paramString;
  }

  public void setEnvirnoment(String paramString)
  {
    z.g = paramString;
  }

  public void setGoods_extend(String paramString)
  {
    z.t = paramString;
  }

  public void setGoods_name(String paramString)
  {
    z.r = paramString;
  }

  public void setGoods_num(String paramString)
  {
    z.s = paramString;
  }

  public void setHfRealServiceCode(String paramString)
  {
    this.c = paramString;
  }

  public void setIsSendReport(boolean paramBoolean)
  {
    z.h = paramBoolean;
  }

  public void setMallUrl(String paramString)
  {
    z.u = paramString;
  }

  public void setMbSig(String paramString)
  {
    z.e = paramString;
  }

  public void setOrderInfo(APOrderInfo paramAPOrderInfo)
  {
    z.y = paramAPOrderInfo;
  }

  public void setPayAssignChannel(String paramString)
  {
    z.k = paramString;
  }

  public void setPreSaveNumber(String paramString)
  {
    z.b = paramString;
  }

  public void setPresent_flag(int paramInt)
  {
    z.q = paramInt;
  }

  public void setResultUrl(String paramString)
  {
    z.v = paramString;
  }

  public void setScreenType(int paramInt)
  {
    z.w = paramInt;
  }

  public void setSmsInfo(String paramString)
  {
    z.f = paramString;
  }

  public void setSourceActivity(String paramString)
  {
    z.j = paramString;
  }

  public void setSuccess_url(String paramString)
  {
    z.o = paramString;
  }

  public void setUserInfo(APUserInfo paramAPUserInfo)
  {
    z.x = paramAPUserInfo;
  }

  public void setUuid(String paramString)
  {
    z.p = paramString;
  }

  public void setVCSession(String paramString)
  {
    z.d = paramString;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.tool.APDataInterface
 * JD-Core Version:    0.6.0
 */