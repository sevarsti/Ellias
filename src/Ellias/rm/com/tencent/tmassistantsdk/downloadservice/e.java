package com.tencent.tmassistantsdk.downloadservice;

import java.util.ArrayList;
import java.util.Iterator;

public class e
{
  protected static e a = null;
  protected final ArrayList b = new ArrayList();

  public static e a()
  {
    if (a == null)
      a = new e();
    return a;
  }

  public void a(l paraml)
  {
    monitorenter;
    try
    {
      if (!this.b.contains(paraml))
        this.b.add(paraml);
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

  public void a(String paramString1, int paramInt1, int paramInt2, String paramString2)
  {
    monitorenter;
    try
    {
      Iterator localIterator = this.b.iterator();
      while (localIterator.hasNext())
        ((l)localIterator.next()).a(paramString1, paramInt1, paramInt2, paramString2);
    }
    finally
    {
      monitorexit;
    }
    monitorexit;
  }

  public void a(String paramString, long paramLong1, long paramLong2)
  {
    monitorenter;
    try
    {
      Iterator localIterator = this.b.iterator();
      while (localIterator.hasNext())
        ((l)localIterator.next()).a(paramString, paramLong1, paramLong2);
    }
    finally
    {
      monitorexit;
    }
    monitorexit;
  }

  public void b(l paraml)
  {
    monitorenter;
    try
    {
      this.b.remove(paraml);
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
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.tmassistantsdk.downloadservice.e
 * JD-Core Version:    0.6.0
 */