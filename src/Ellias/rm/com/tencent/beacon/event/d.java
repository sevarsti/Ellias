package com.tencent.beacon.event;

import java.util.Locale;
import java.util.Map;
import java.util.Set;

public final class d
  implements Cloneable
{
  private int a = 12;
  private int b = 60;
  private int c = 12;
  private int d = 60;
  private int e = 20;
  private int f = 102400;
  private int g = 10240;
  private int h = 2097152;
  private int i = 60;
  private int j = 600;
  private boolean k = true;
  private int l = 30;
  private boolean m = true;
  private boolean n = true;
  private boolean o = true;
  private int p = 524288;
  private int q = 10;
  private int r = 50;
  private Set<String> s = null;

  private void a(int paramInt)
  {
    monitorenter;
    if (paramInt > 0);
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

  private void b(int paramInt)
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

  private void c(int paramInt)
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

  private void d(int paramInt)
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

  private void e(int paramInt)
  {
    monitorenter;
    if (paramInt > 0);
    try
    {
      this.e = paramInt;
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

  private void f(int paramInt)
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

  private void g(int paramInt)
  {
    monitorenter;
    if (paramInt > 0);
    try
    {
      this.f = paramInt;
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

  private void h(int paramInt)
  {
    monitorenter;
    if (paramInt > 0);
    try
    {
      this.g = paramInt;
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

  private d s()
  {
    monitorenter;
    try
    {
      d locald = new d();
      locald.d(this.d);
      locald.c(this.c);
      locald.e(this.e);
      locald.h(this.g);
      locald.g(this.f);
      locald.f(this.h);
      locald.b(this.b);
      locald.a(this.a);
      monitorexit;
      return locald;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public final int a()
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

  public final void a(Map<String, String> paramMap)
  {
    monitorenter;
    if (paramMap != null);
    while (true)
    {
      String str13;
      String str14;
      String str15;
      try
      {
        String str1 = (String)paramMap.get("realNumUp");
        if (str1 == null)
          continue;
        int i1 = Integer.valueOf(str1).intValue();
        if ((i1 <= 0) || (i1 > 50))
          continue;
        this.a = i1;
        String str2 = (String)paramMap.get("realDelayUp");
        if (str2 == null)
          continue;
        int i2 = Integer.valueOf(str2).intValue();
        if ((i2 < 10) || (i2 > 600))
          continue;
        this.b = i2;
        String str3 = (String)paramMap.get("comNumDB");
        if (str3 == null)
          continue;
        int i3 = Integer.valueOf(str3).intValue();
        if ((i3 <= 0) || (i3 > 50))
          continue;
        this.c = i3;
        String str4 = (String)paramMap.get("comDelayDB");
        if (str4 == null)
          continue;
        int i4 = Integer.valueOf(str4).intValue();
        if ((i4 < 60) || (i4 > 600))
          continue;
        this.d = i4;
        String str5 = (String)paramMap.get("comNumUp");
        if (str5 == null)
          continue;
        int i5 = Integer.valueOf(str5).intValue();
        if ((i5 <= 0) || (i5 > 100))
          continue;
        this.e = i5;
        String str6 = (String)paramMap.get("sizeUpWifi");
        if (str6 == null)
          continue;
        int i6 = Integer.valueOf(str6).intValue();
        if ((i6 < 10240) || (i6 > 102400))
          continue;
        this.f = i6;
        String str7 = (String)paramMap.get("sizeUpNoWifi");
        if (str7 == null)
          continue;
        int i7 = Integer.valueOf(str7).intValue();
        if ((i7 < 5120) || (i7 > 51200))
          continue;
        this.g = i7;
        String str8 = (String)paramMap.get("dailyNetFlowLimit");
        if (str8 == null)
          continue;
        int i8 = Integer.valueOf(str8).intValue();
        if ((i8 < 204800) || (i8 > 10485760))
          continue;
        this.h = i8;
        String str9 = (String)paramMap.get("runInfoPeriod");
        if (str9 == null)
          continue;
        int i9 = Integer.valueOf(str9).intValue();
        if ((i9 < 30) || (i9 > 300))
          continue;
        this.i = i9;
        String str10 = (String)paramMap.get("useTimeUpPeriod");
        if (str10 == null)
          continue;
        int i10 = Integer.valueOf(str10).intValue();
        if ((i10 < 300) || (i10 > 1800))
          continue;
        this.j = i10;
        String str11 = (String)paramMap.get("useTimeOnOff");
        if (str11 == null)
          continue;
        if (!str11.toLowerCase().equals("y"))
          continue;
        this.k = true;
        String str12 = (String)paramMap.get("proChangePeriod");
        if (str12 == null)
          continue;
        int i11 = Integer.valueOf(str12).intValue();
        if ((i11 < 10) || (i11 > 300))
          continue;
        this.l = i11;
        str13 = (String)paramMap.get("proChangeOnOff");
        if (str13 == null)
          continue;
        if (str13.toLowerCase().equals("y"))
        {
          this.m = true;
          str14 = (String)paramMap.get("heartOnOff");
          if (str14 == null)
            continue;
          if (!str14.toLowerCase().equals("y"))
            break label866;
          this.n = true;
          str15 = (String)paramMap.get("appLogUploadOnOff");
          if (str15 == null)
            continue;
          if (!str15.toLowerCase().equals("y"))
            break label887;
          this.o = true;
          String str16 = (String)paramMap.get("appLogOutTime");
          if (str2 == null)
            continue;
          Long.valueOf(str16).longValue();
          String str17 = (String)paramMap.get("appLogSizeLimit");
          if (str2 == null)
            continue;
          Long.valueOf(str17).longValue();
          String str18 = (String)paramMap.get("appLogRecordMax");
          if (str18 == null)
            continue;
          int i12 = Integer.valueOf(str18).intValue();
          if ((i12 < 5) || (i12 > 100))
            continue;
          this.r = i12;
          String str19 = (String)paramMap.get("appLogFileTotalMaxSize");
          if (str19 == null)
            continue;
          Integer.valueOf(str19).intValue();
          return;
          if (!str11.toLowerCase().equals("n"))
            continue;
          this.k = false;
          continue;
        }
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
        continue;
      }
      finally
      {
        monitorexit;
      }
      if (!str13.toLowerCase().equals("n"))
        continue;
      this.m = false;
      continue;
      label866: if (!str14.toLowerCase().equals("n"))
        continue;
      this.n = false;
      continue;
      label887: if (!str15.toLowerCase().equals("n"))
        continue;
      this.o = false;
    }
  }

  public final void a(Set<String> paramSet)
  {
    monitorenter;
    try
    {
      this.s = paramSet;
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

  public final void a(boolean paramBoolean)
  {
    this.k = false;
  }

  public final boolean a(String paramString)
  {
    monitorenter;
    try
    {
      Set localSet = this.s;
      int i1 = 0;
      if (localSet != null)
      {
        int i2 = this.s.size();
        i1 = 0;
        if (i2 > 0)
        {
          boolean bool = this.s.contains(paramString);
          i1 = bool;
        }
      }
      return i1;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public final int b()
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

  public final int c()
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

  public final int d()
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

  public final int e()
  {
    monitorenter;
    try
    {
      int i1 = this.e;
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

  public final int f()
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

  public final int g()
  {
    monitorenter;
    try
    {
      int i1 = this.f;
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

  public final int h()
  {
    monitorenter;
    try
    {
      int i1 = this.g;
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

  public final int i()
  {
    monitorenter;
    try
    {
      int i1 = this.j;
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

  public final int j()
  {
    return this.i;
  }

  public final boolean k()
  {
    return this.k;
  }

  public final int l()
  {
    return this.l;
  }

  public final boolean m()
  {
    return this.m;
  }

  public final boolean n()
  {
    return this.n;
  }

  public final boolean o()
  {
    return this.o;
  }

  public final int p()
  {
    return this.p;
  }

  public final int q()
  {
    return this.r;
  }

  public final int r()
  {
    return this.q;
  }

  public final String toString()
  {
    monitorenter;
    try
    {
      Locale localLocale = Locale.US;
      Object[] arrayOfObject = new Object[9];
      arrayOfObject[0] = Integer.valueOf(this.a);
      arrayOfObject[1] = Integer.valueOf(this.b);
      arrayOfObject[2] = Integer.valueOf(this.c);
      arrayOfObject[3] = Integer.valueOf(this.d);
      arrayOfObject[4] = Integer.valueOf(this.e);
      arrayOfObject[5] = Integer.valueOf(this.f);
      arrayOfObject[6] = Integer.valueOf(this.g);
      arrayOfObject[7] = Integer.valueOf(this.g);
      arrayOfObject[8] = Integer.valueOf(this.h);
      String str2 = String.format(localLocale, "[rnum:%d,rdelay:%d,cndb:%d,cdelay:%d,csWifi:%d,csGPRS:%d,cnum:%d,dLimit:%d]", arrayOfObject);
      str1 = str2;
      return str1;
    }
    catch (Throwable localThrowable)
    {
      while (true)
      {
        localThrowable.printStackTrace();
        String str1 = null;
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
 * Qualified Name:     com.tencent.beacon.event.d
 * JD-Core Version:    0.6.0
 */