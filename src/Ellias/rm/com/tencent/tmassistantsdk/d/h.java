package com.tencent.tmassistantsdk.d;

import com.tencent.tmassistantsdk.g.l;
import com.tencent.tmassistantsdk.openSDK.opensdktomsdk.TMOpenSDKAuthorizedInfo;
import com.tencent.tmassistantsdk.protocol.jce.TipsInfoLog;

public class h extends a
{
  protected static h d = null;

  public static h g()
  {
    monitorenter;
    try
    {
      if (d == null)
        d = new h();
      h localh = d;
      return localh;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public TipsInfoLog a(TMOpenSDKAuthorizedInfo paramTMOpenSDKAuthorizedInfo)
  {
    l.b("TipsInfoReportManager", "createTipsInfoLog");
    if (paramTMOpenSDKAuthorizedInfo == null)
      return null;
    TipsInfoLog localTipsInfoLog = new TipsInfoLog();
    localTipsInfoLog.userId = paramTMOpenSDKAuthorizedInfo.userId;
    localTipsInfoLog.userIdType = paramTMOpenSDKAuthorizedInfo.userIdType;
    localTipsInfoLog.gamePackageName = paramTMOpenSDKAuthorizedInfo.gamePackageName;
    localTipsInfoLog.gameVersionCode = Integer.parseInt(paramTMOpenSDKAuthorizedInfo.gameVersionCode);
    localTipsInfoLog.gameChannelId = paramTMOpenSDKAuthorizedInfo.gameChannelId;
    return localTipsInfoLog;
  }

  protected com.tencent.tmassistantsdk.f.c.a e()
  {
    return com.tencent.tmassistantsdk.f.c.h.f();
  }

  protected byte f()
  {
    return 1;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.tmassistantsdk.d.h
 * JD-Core Version:    0.6.0
 */