package com.tencent.component.plugin.server;

import android.content.Context;
import com.tencent.component.plugin.PluginPlatformConfig;
import java.util.concurrent.ConcurrentHashMap;

class c
{
  private static ConcurrentHashMap h = new ConcurrentHashMap();
  private final String a;
  private Context b;
  private volatile BuiltinPluginLoader c;
  private volatile f d;
  private volatile e e;
  private volatile g f;
  private volatile PluginPlatformConfig g;

  private c(Context paramContext, String paramString)
  {
    this.a = paramString;
    this.b = paramContext.getApplicationContext();
  }

  public static c a(Context paramContext, String paramString)
  {
    c localc1 = (c)h.get(paramString);
    if (localc1 == null)
    {
      monitorenter;
      try
      {
        c localc2 = (c)h.get(paramString);
        if (localc2 == null)
        {
          localc2 = new c(paramContext, paramString);
          h.put(paramString, localc2);
        }
        return localc2;
      }
      finally
      {
        monitorexit;
      }
    }
    return localc1;
  }

  public Context a()
  {
    return this.b;
  }

  public void a(PluginPlatformConfig paramPluginPlatformConfig)
  {
    this.g = paramPluginPlatformConfig;
  }

  public String b()
  {
    return this.a;
  }

  public BuiltinPluginLoader c()
  {
    if (this.c == null)
      monitorenter;
    try
    {
      if (this.c == null)
        this.c = new BuiltinPluginLoader(this);
      return this.c;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public f d()
  {
    if (this.d == null)
      monitorenter;
    try
    {
      if (this.d == null)
        this.d = new f(this);
      return this.d;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public e e()
  {
    if (this.e == null)
      monitorenter;
    try
    {
      if (this.e == null)
        this.e = new e(this);
      return this.e;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public g f()
  {
    if (this.f == null)
      monitorenter;
    try
    {
      if (this.f == null)
        this.f = new g(this);
      return this.f;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public PluginPlatformConfig g()
  {
    return this.g;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.plugin.server.c
 * JD-Core Version:    0.6.0
 */