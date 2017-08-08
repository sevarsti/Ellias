package com.tencent.feedback.eup;

import com.tencent.feedback.common.e;
import java.util.Locale;

public class CrashStrategyBean
  implements Cloneable
{
  public static final String format = "[MSNum:%d ,Wifi:%d,GPRS:%d,ODay:%d,isMerged:%b,AfQ:%b,Silent:%b,mLog:%d,tag:%s,assert:%s, interval:%s, limit:%s]";
  private int a = 10;
  private int b = 10;
  private int c = 3;
  private int d = 10;
  private boolean e = false;
  private boolean f = false;
  private boolean g = true;
  private int h = 100;
  private String i = null;
  private boolean j = false;
  private String k = null;
  private int l = 5000;
  private int m = 3;
  private int n = 100;
  private boolean o = false;
  private int p = 60;
  private int q = 50;

  public CrashStrategyBean clone()
    throws CloneNotSupportedException
  {
    monitorenter;
    try
    {
      CrashStrategyBean localCrashStrategyBean = new CrashStrategyBean();
      localCrashStrategyBean.setEnableAfterQuery(this.f);
      localCrashStrategyBean.setMaxStoredNum(this.a);
      localCrashStrategyBean.setMaxUploadNum_GPRS(this.c);
      localCrashStrategyBean.setMaxUploadNum_Wifi(this.b);
      localCrashStrategyBean.setMerged(this.e);
      localCrashStrategyBean.setRecordOverDays(this.d);
      localCrashStrategyBean.setSilentUpload(this.g);
      localCrashStrategyBean.setMaxLogRow(this.h);
      localCrashStrategyBean.setOnlyLogTag(this.i);
      localCrashStrategyBean.setAssertEnable(this.o);
      localCrashStrategyBean.setAssertTaskInterval(this.p);
      localCrashStrategyBean.setAssertLimitCount(this.q);
      monitorexit;
      return localCrashStrategyBean;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public int getAssertLimitCount()
  {
    monitorenter;
    try
    {
      int i1 = this.q;
      monitorexit;
      return i1;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public int getAssertTaskInterval()
  {
    monitorenter;
    try
    {
      int i1 = this.p;
      monitorexit;
      return i1;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public int getCrashSdcardMaxSize()
  {
    monitorenter;
    try
    {
      int i1 = this.l;
      monitorexit;
      return i1;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public int getMaxLogRow()
  {
    monitorenter;
    try
    {
      int i1 = this.h;
      monitorexit;
      return i1;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public int getMaxStackFrame()
  {
    monitorenter;
    try
    {
      int i1 = this.m;
      monitorexit;
      return i1;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public int getMaxStackLine()
  {
    monitorenter;
    try
    {
      int i1 = this.n;
      monitorexit;
      return i1;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public int getMaxStoredNum()
  {
    monitorenter;
    try
    {
      int i1 = this.a;
      monitorexit;
      return i1;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public int getMaxUploadNum_GPRS()
  {
    monitorenter;
    try
    {
      int i1 = this.c;
      monitorexit;
      return i1;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public int getMaxUploadNum_Wifi()
  {
    monitorenter;
    try
    {
      int i1 = this.b;
      monitorexit;
      return i1;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public String getOnlyLogTag()
  {
    monitorenter;
    try
    {
      String str = this.i;
      monitorexit;
      return str;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public int getRecordOverDays()
  {
    monitorenter;
    try
    {
      int i1 = this.d;
      monitorexit;
      return i1;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public String getStoreDirectoryPath()
  {
    monitorenter;
    try
    {
      String str = this.k;
      monitorexit;
      return str;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public boolean isAssertOn()
  {
    monitorenter;
    try
    {
      boolean bool = this.o;
      monitorexit;
      return bool;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public boolean isEnableAfterQuery()
  {
    monitorenter;
    try
    {
      boolean bool = this.f;
      monitorexit;
      return bool;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public boolean isMerged()
  {
    monitorenter;
    try
    {
      boolean bool = this.e;
      monitorexit;
      return bool;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public boolean isSilentUpload()
  {
    monitorenter;
    try
    {
      boolean bool = this.g;
      monitorexit;
      return bool;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public boolean isStoreCrashSdcard()
  {
    monitorenter;
    try
    {
      boolean bool = this.j;
      monitorexit;
      return bool;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public void setAssertEnable(boolean paramBoolean)
  {
    monitorenter;
    try
    {
      this.o = paramBoolean;
      monitorexit;
      return;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public void setAssertLimitCount(int paramInt)
  {
    monitorenter;
    if (paramInt < 50);
    while (true)
    {
      try
      {
        Object[] arrayOfObject = new Object[1];
        arrayOfObject[0] = Integer.valueOf(paramInt);
        e.a("rqdp{The trigger count of the assert store is smaller than the default count.} [%s]", arrayOfObject);
        break label48;
        this.q = paramInt;
        return;
        paramInt = 50;
        continue;
      }
      finally
      {
        monitorexit;
      }
      label48: if (paramInt <= 0)
        continue;
    }
  }

  public void setAssertTaskInterval(int paramInt)
  {
    monitorenter;
    if (paramInt < 60);
    while (true)
    {
      try
      {
        Object[] arrayOfObject = new Object[1];
        arrayOfObject[0] = Integer.valueOf(paramInt);
        e.a("rqdp{The interval of assert check task is smaller than the default time.} [%s s]", arrayOfObject);
        break label48;
        this.p = paramInt;
        return;
        paramInt = 60;
        continue;
      }
      finally
      {
        monitorexit;
      }
      label48: if (paramInt <= 0)
        continue;
    }
  }

  public void setCrashSdcardMaxSize(int paramInt)
  {
    monitorenter;
    if (paramInt > 0);
    try
    {
      this.l = paramInt;
      monitorexit;
      return;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public void setEnableAfterQuery(boolean paramBoolean)
  {
    monitorenter;
    try
    {
      this.f = paramBoolean;
      monitorexit;
      return;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public void setMaxLogRow(int paramInt)
  {
    monitorenter;
    if (paramInt > 0);
    try
    {
      this.h = paramInt;
      monitorexit;
      return;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public void setMaxStackFrame(int paramInt)
  {
    monitorenter;
    try
    {
      this.m = paramInt;
      monitorexit;
      return;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public void setMaxStackLine(int paramInt)
  {
    monitorenter;
    try
    {
      this.n = paramInt;
      monitorexit;
      return;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public void setMaxStoredNum(int paramInt)
  {
    monitorenter;
    if ((paramInt > 0) && (paramInt <= 20));
    try
    {
      this.a = paramInt;
      monitorexit;
      return;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public void setMaxUploadNum_GPRS(int paramInt)
  {
    monitorenter;
    if (paramInt > 0);
    try
    {
      this.c = paramInt;
      monitorexit;
      return;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public void setMaxUploadNum_Wifi(int paramInt)
  {
    monitorenter;
    if (paramInt > 0);
    try
    {
      this.b = paramInt;
      monitorexit;
      return;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public void setMerged(boolean paramBoolean)
  {
    monitorenter;
    try
    {
      this.e = paramBoolean;
      monitorexit;
      return;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public void setOnlyLogTag(String paramString)
  {
    monitorenter;
    try
    {
      this.i = paramString;
      monitorexit;
      return;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public void setRecordOverDays(int paramInt)
  {
    monitorenter;
    if (paramInt > 0);
    try
    {
      this.d = paramInt;
      monitorexit;
      return;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public void setSilentUpload(boolean paramBoolean)
  {
    monitorenter;
    try
    {
      this.g = paramBoolean;
      monitorexit;
      return;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public void setStoreCrashSdcard(boolean paramBoolean)
  {
    monitorenter;
    try
    {
      this.j = paramBoolean;
      monitorexit;
      return;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public void setStoreDirectoryPath(String paramString)
  {
    monitorenter;
    try
    {
      this.k = paramString;
      monitorexit;
      return;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public String toString()
  {
    monitorenter;
    try
    {
      Locale localLocale = Locale.US;
      Object[] arrayOfObject = new Object[12];
      arrayOfObject[0] = Integer.valueOf(this.a);
      arrayOfObject[1] = Integer.valueOf(this.b);
      arrayOfObject[2] = Integer.valueOf(this.c);
      arrayOfObject[3] = Integer.valueOf(this.d);
      arrayOfObject[4] = Boolean.valueOf(this.e);
      arrayOfObject[5] = Boolean.valueOf(this.f);
      arrayOfObject[6] = Boolean.valueOf(this.g);
      arrayOfObject[7] = Integer.valueOf(this.h);
      arrayOfObject[8] = this.i;
      arrayOfObject[9] = Boolean.valueOf(this.o);
      arrayOfObject[10] = Integer.valueOf(this.q);
      arrayOfObject[11] = Integer.valueOf(this.p);
      String str2 = String.format(localLocale, "[MSNum:%d ,Wifi:%d,GPRS:%d,ODay:%d,isMerged:%b,AfQ:%b,Silent:%b,mLog:%d,tag:%s,assert:%s, interval:%s, limit:%s]", arrayOfObject);
      str1 = str2;
      return str1;
    }
    catch (Throwable localThrowable)
    {
      while (true)
      {
        localThrowable.printStackTrace();
        String str1 = "error";
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.feedback.eup.CrashStrategyBean
 * JD-Core Version:    0.6.0
 */