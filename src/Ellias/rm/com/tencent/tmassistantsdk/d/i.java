package com.tencent.tmassistantsdk.d;

import com.tencent.tmassistantsdk.g.l;
import com.tencent.tmassistantsdk.protocol.jce.UpdateInfoLog;

public class i extends a
{
  protected static i d = null;

  public static i g()
  {
    monitorenter;
    try
    {
      if (d == null)
        d = new i();
      i locali = d;
      return locali;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public UpdateInfoLog a(String paramString, long paramLong)
  {
    l.b("UpdateInfoReportManager", "createUpdateInfoLog");
    UpdateInfoLog localUpdateInfoLog = new UpdateInfoLog();
    localUpdateInfoLog.packageName = paramString;
    localUpdateInfoLog.appid = paramLong;
    return localUpdateInfoLog;
  }

  protected com.tencent.tmassistantsdk.f.c.a e()
  {
    return com.tencent.tmassistantsdk.f.c.i.f();
  }

  protected byte f()
  {
    return 2;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.tmassistantsdk.d.i
 * JD-Core Version:    0.6.0
 */