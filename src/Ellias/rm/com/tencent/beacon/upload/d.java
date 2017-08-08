package com.tencent.beacon.upload;

import com.tencent.beacon.d.a;

public class d
{
  private long a = 0L;
  private long b = 0L;
  private int c = 0;

  public long a()
  {
    monitorenter;
    try
    {
      long l = this.a;
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

  public void a(long paramLong)
  {
    monitorenter;
    try
    {
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = Long.valueOf(paramLong);
      a.h("recevied: %d", arrayOfObject);
      this.b = (paramLong + this.b);
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

  public void a(String paramString1, long paramLong, String paramString2)
  {
    monitorenter;
    try
    {
      Object[] arrayOfObject = new Object[3];
      arrayOfObject[0] = paramString1;
      arrayOfObject[1] = Long.valueOf(paramLong);
      arrayOfObject[2] = paramString2;
      a.h("send: %s sz: %d apn: %s", arrayOfObject);
      this.c = (1 + this.c);
      this.a = (paramLong + this.a);
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

  public long b()
  {
    monitorenter;
    try
    {
      long l = this.b;
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
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.beacon.upload.d
 * JD-Core Version:    0.6.0
 */