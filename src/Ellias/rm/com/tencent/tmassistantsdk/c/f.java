package com.tencent.tmassistantsdk.c;

import android.content.Context;
import com.tencent.tmassistantsdk.g.l;
import java.util.ArrayList;
import java.util.Iterator;

public class f
{
  protected static f a = null;
  protected static ArrayList c = new ArrayList();
  protected static h d = null;
  protected static ArrayList e = new ArrayList();
  protected Context b = null;

  protected f(Context paramContext)
  {
    this.b = paramContext;
  }

  public static f a(Context paramContext)
  {
    monitorenter;
    try
    {
      if (a == null)
        a = new f(paramContext);
      f localf = a;
      return localf;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public static void b(Context paramContext)
  {
    monitorenter;
    while (true)
    {
      try
      {
        l.b("TMAssistantDownloadSDKManager", "closeAllService method!");
        if (a != null)
          continue;
        l.b("TMAssistantDownloadSDKManager", "manager minstance == null");
        return;
        if ((c == null) || (c.size() <= 0))
          break label91;
        Iterator localIterator = c.iterator();
        if (localIterator.hasNext())
        {
          c localc = (c)localIterator.next();
          if (localc == null)
            continue;
          localc.f();
          continue;
        }
      }
      finally
      {
        monitorexit;
      }
      c.clear();
      label91: if (d != null)
      {
        d.f();
        d = null;
      }
      a = null;
    }
  }

  public c a(String paramString)
  {
    monitorenter;
    if (paramString != null);
    try
    {
      int i = paramString.length();
      c localc;
      if (i <= 0)
        localc = null;
      while (true)
      {
        return localc;
        Iterator localIterator = c.iterator();
        while (true)
          if (localIterator.hasNext())
          {
            localc = (c)localIterator.next();
            if (localc.d.equals(paramString) != true)
              continue;
            break;
          }
        localc = new c(this.b, paramString);
        localc.e();
        c.add(localc);
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
 * Qualified Name:     com.tencent.tmassistantsdk.c.f
 * JD-Core Version:    0.6.0
 */