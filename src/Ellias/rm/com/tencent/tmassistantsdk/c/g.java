package com.tencent.tmassistantsdk.c;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import com.tencent.tmassistantsdk.g.h;
import com.tencent.tmassistantsdk.g.l;
import java.util.ArrayList;
import java.util.Iterator;

public class g extends Handler
{
  private static g a = null;
  private static HandlerThread b = null;

  private g(Looper paramLooper)
  {
    super(paramLooper);
  }

  public static g a()
  {
    monitorenter;
    try
    {
      if (a == null)
      {
        b = new HandlerThread("TMAssistantDownloadSDKMessageThread");
        b.start();
        a = new g(b.getLooper());
      }
      g localg = a;
      return localg;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public void a(c paramc, b paramb)
  {
    if ((paramb == null) || (paramc == null))
      return;
    Message localMessage = a().obtainMessage();
    localMessage.what = 3;
    localMessage.obj = new h(paramc, paramb);
    localMessage.sendToTarget();
  }

  public void a(c paramc, b paramb, String paramString1, int paramInt1, int paramInt2, String paramString2)
  {
    if ((paramb == null) || (paramc == null))
      return;
    Message localMessage = a().obtainMessage();
    localMessage.what = 1;
    localMessage.obj = new h(paramc, paramb);
    Bundle localBundle = new Bundle();
    localBundle.putString("url", paramString1);
    localBundle.putInt("state", paramInt1);
    localBundle.putInt("errorCode", paramInt2);
    localBundle.putString("errorMsg", paramString2);
    localMessage.setData(localBundle);
    localMessage.sendToTarget();
  }

  public void a(c paramc, b paramb, String paramString, long paramLong1, long paramLong2)
  {
    if ((paramb == null) || (paramc == null))
    {
      l.b("TMAssistantDownloadSDKMessageThread", "listenr:" + paramb + " === sdkClient" + paramc);
      return;
    }
    Message localMessage = a().obtainMessage();
    localMessage.what = 2;
    localMessage.obj = new h(paramc, paramb);
    Bundle localBundle = new Bundle();
    localBundle.putString("url", paramString);
    localBundle.putLong("receiveDataLen", paramLong1);
    localBundle.putLong("totalDataLen", paramLong2);
    localMessage.setData(localBundle);
    localMessage.sendToTarget();
  }

  public void handleMessage(Message paramMessage)
  {
    super.handleMessage(paramMessage);
    switch (paramMessage.what)
    {
    default:
    case 1:
    case 2:
    case 3:
    case 4:
    }
    while (true)
    {
      return;
      h localh4 = (h)paramMessage.obj;
      c localc3 = (c)localh4.a;
      b localb3 = (b)localh4.b;
      Bundle localBundle2 = paramMessage.getData();
      String str2 = localBundle2.getString("url");
      int i = localBundle2.getInt("state");
      int j = localBundle2.getInt("errorCode");
      String str3 = localBundle2.getString("errorMsg");
      if (localb3 == null)
        continue;
      localb3.a(localc3, str2, i, j, str3);
      return;
      h localh3 = (h)paramMessage.obj;
      c localc2 = (c)localh3.a;
      b localb2 = (b)localh3.b;
      Bundle localBundle1 = paramMessage.getData();
      String str1 = localBundle1.getString("url");
      long l1 = localBundle1.getLong("receiveDataLen");
      long l2 = localBundle1.getLong("totalDataLen");
      if (localb2 == null)
        continue;
      localb2.a(localc2, str1, l1, l2);
      return;
      h localh2 = (h)paramMessage.obj;
      c localc1 = (c)localh2.a;
      b localb1 = (b)localh2.b;
      if (localb1 == null)
        continue;
      localb1.a(localc1);
      return;
      h localh1 = (h)paramMessage.obj;
      byte[] arrayOfByte = (byte[])(byte[])localh1.a;
      ArrayList localArrayList = (ArrayList)localh1.b;
      if (localArrayList == null)
        continue;
      Iterator localIterator = localArrayList.iterator();
      while (localIterator.hasNext())
      {
        a locala = (a)localIterator.next();
        if (locala == null)
          continue;
        locala.a(arrayOfByte);
      }
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.tmassistantsdk.c.g
 * JD-Core Version:    0.6.0
 */