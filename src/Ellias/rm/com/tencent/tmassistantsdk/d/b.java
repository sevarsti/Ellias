package com.tencent.tmassistantsdk.d;

import android.net.Proxy;
import android.text.TextUtils;
import com.tencent.tmassistantsdk.f.c.e;
import com.tencent.tmassistantsdk.g.f;
import com.tencent.tmassistantsdk.g.l;
import com.tencent.tmassistantsdk.protocol.jce.DownloadChunkLogInfo;

public class b extends a
{
  protected static b d = null;

  public static b g()
  {
    monitorenter;
    try
    {
      if (d == null)
        d = new b();
      b localb = d;
      return localb;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public DownloadChunkLogInfo a(byte paramByte)
  {
    l.b("DownloadReportManager", "createNewChunkLogInfo");
    DownloadChunkLogInfo localDownloadChunkLogInfo = new DownloadChunkLogInfo();
    localDownloadChunkLogInfo.type = paramByte;
    localDownloadChunkLogInfo.networkOperator = f.a().d();
    localDownloadChunkLogInfo.networkType = f.a().e();
    if (!TextUtils.isEmpty(Proxy.getDefaultHost()));
    for (int i = 1; ; i = 0)
    {
      localDownloadChunkLogInfo.isWap = (byte)i;
      localDownloadChunkLogInfo.startTime = System.currentTimeMillis();
      return localDownloadChunkLogInfo;
    }
  }

  protected com.tencent.tmassistantsdk.f.c.a e()
  {
    return e.f();
  }

  protected byte f()
  {
    return 0;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.tmassistantsdk.d.b
 * JD-Core Version:    0.6.0
 */