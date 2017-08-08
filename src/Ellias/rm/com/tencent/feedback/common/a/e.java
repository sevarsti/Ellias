package com.tencent.feedback.common.a;

import java.io.Serializable;
import java.util.Locale;

public class e
  implements Serializable
{
  public final int a;
  public final long b;
  public final long c;
  public final long d;
  public final long e;
  public final long f;
  public final long g;
  private long h = -1L;

  public e(int paramInt, long paramLong1, long paramLong2, long paramLong3, long paramLong4, long paramLong5, long paramLong6)
  {
    this.a = paramInt;
    this.b = paramLong1;
    this.c = paramLong2;
    this.d = paramLong3;
    this.e = paramLong4;
    this.f = paramLong5;
    this.g = paramLong6;
  }

  public final long a()
  {
    monitorenter;
    try
    {
      long l = this.h;
      monitorexit;
      return l;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public final void a(long paramLong)
  {
    monitorenter;
    try
    {
      this.h = paramLong;
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
    try
    {
      Locale localLocale = Locale.US;
      Object[] arrayOfObject = new Object[7];
      arrayOfObject[0] = Integer.valueOf(this.a);
      arrayOfObject[1] = Long.valueOf(this.b);
      arrayOfObject[2] = Long.valueOf(this.c);
      arrayOfObject[3] = Long.valueOf(this.d);
      arrayOfObject[4] = Long.valueOf(this.e);
      arrayOfObject[5] = Long.valueOf(this.f);
      arrayOfObject[6] = Long.valueOf(this.g);
      String str = String.format(localLocale, "[tp:%d , st:%d ,counts:%d, wifi:%d , notWifi:%d , up:%d , dn:%d]", arrayOfObject);
      return str;
    }
    catch (Throwable localThrowable)
    {
      localThrowable.printStackTrace();
    }
    return null;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.feedback.common.a.e
 * JD-Core Version:    0.6.0
 */