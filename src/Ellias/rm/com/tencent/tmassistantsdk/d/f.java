package com.tencent.tmassistantsdk.d;

import com.tencent.tmassistantsdk.downloadservice.NetworkMonitorReceiver;
import com.tencent.tmassistantsdk.downloadservice.n;

public class f
{
  private static f a = null;
  private static final Class[] b = { b.class, h.class, i.class };
  private final n c = new g(this);

  private f()
  {
    NetworkMonitorReceiver.a().a(this.c);
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

  public void b()
  {
    Class[] arrayOfClass = b;
    int i = arrayOfClass.length;
    for (int j = 0; ; j++)
      if (j < i)
      {
        Class localClass = arrayOfClass[j];
        try
        {
          if (localClass.equals(b.class))
          {
            b.g().a();
            continue;
          }
          if (!localClass.equals(h.class))
            continue;
          h.g().a();
        }
        catch (Exception localException)
        {
          localException.printStackTrace();
        }
      }
      else
      {
        NetworkMonitorReceiver.a().b(this.c);
        return;
      }
  }

  public void c()
  {
    Class[] arrayOfClass = b;
    int i = arrayOfClass.length;
    for (int j = 0; ; j++)
      if (j < i)
      {
        Class localClass = arrayOfClass[j];
        try
        {
          if (localClass.equals(b.class))
          {
            b.g().c();
            continue;
          }
          if (localClass.equals(h.class))
            h.g().c();
        }
        catch (Exception localException)
        {
          localException.printStackTrace();
        }
        if (!localClass.equals(i.class))
          continue;
        i.g().c();
      }
      else
      {
        return;
      }
  }

  public void d()
  {
    Class[] arrayOfClass = b;
    int i = arrayOfClass.length;
    for (int j = 0; ; j++)
      if (j < i)
      {
        Class localClass = arrayOfClass[j];
        try
        {
          if (localClass.equals(b.class))
          {
            b.g().b();
            continue;
          }
          if (!localClass.equals(h.class))
            continue;
          h.g().b();
        }
        catch (Exception localException)
        {
          localException.printStackTrace();
        }
      }
      else
      {
        return;
      }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.tmassistantsdk.d.f
 * JD-Core Version:    0.6.0
 */