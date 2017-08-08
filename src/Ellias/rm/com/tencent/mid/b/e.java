package com.tencent.mid.b;

import android.content.Context;
import android.provider.Settings.System;
import com.tencent.mid.util.Util;

public class e extends f
{
  public e(Context paramContext)
  {
    super(paramContext);
  }

  protected void a(a parama)
  {
    monitorenter;
    try
    {
      Util.logInfo("write CheckEntity to Settings.System:" + parama.toString());
      Settings.System.putString(this.a.getContentResolver(), g(), parama.toString());
      return;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  protected void a(String paramString)
  {
    monitorenter;
    try
    {
      Util.logInfo("write mid to Settings.System");
      Settings.System.putString(this.a.getContentResolver(), k(), paramString);
      return;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  protected boolean a()
  {
    return Util.checkPermission(this.a, "android.permission.WRITE_SETTINGS");
  }

  protected String b()
  {
    monitorenter;
    try
    {
      Util.logInfo("read mid from Settings.System");
      String str = Settings.System.getString(this.a.getContentResolver(), k());
      return str;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  protected void c()
  {
    monitorenter;
    try
    {
      Settings.System.putString(this.a.getContentResolver(), k(), "");
      Settings.System.putString(this.a.getContentResolver(), g(), "");
      return;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  protected a d()
  {
    monitorenter;
    try
    {
      a locala = new a(Settings.System.getString(this.a.getContentResolver(), g()));
      Util.logInfo("read readCheckEntity from Settings.System:" + locala.toString());
      return locala;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.mid.b.e
 * JD-Core Version:    0.6.0
 */