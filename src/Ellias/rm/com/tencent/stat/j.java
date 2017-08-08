package com.tencent.stat;

import android.content.Context;
import android.os.Handler;
import com.tencent.stat.a.i;
import com.tencent.stat.common.StatLogger;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.json.JSONArray;

class j
  implements Runnable
{
  private Context a = null;
  private Map<String, Integer> b = null;

  public j(Context paramContext, Map<String, Integer> paramMap)
  {
    this.a = paramContext;
    if (paramMap != null)
      this.b = paramMap;
  }

  private NetworkMonitor a(String paramString, int paramInt)
  {
    NetworkMonitor localNetworkMonitor = new NetworkMonitor();
    Socket localSocket = new Socket();
    try
    {
      localNetworkMonitor.setDomain(paramString);
      localNetworkMonitor.setPort(paramInt);
      long l = System.currentTimeMillis();
      InetSocketAddress localInetSocketAddress = new InetSocketAddress(paramString, paramInt);
      localSocket.connect(localInetSocketAddress, 30000);
      localNetworkMonitor.setMillisecondsConsume(System.currentTimeMillis() - l);
      localNetworkMonitor.setRemoteIp(localInetSocketAddress.getAddress().getHostAddress());
      if (localSocket != null)
        localSocket.close();
      i = 0;
      if (localSocket != null);
      try
      {
        localSocket.close();
        localNetworkMonitor.setStatusCode(i);
        return localNetworkMonitor;
      }
      catch (Throwable localThrowable3)
      {
        while (true)
        {
          StatService.b().e(localThrowable3);
          i = 0;
        }
      }
    }
    catch (IOException localIOException)
    {
      while (true)
      {
        int i = -1;
        StatService.b().e(localIOException);
        if (localSocket == null)
          continue;
        try
        {
          localSocket.close();
        }
        catch (Throwable localThrowable2)
        {
          StatService.b().e(localThrowable2);
        }
      }
    }
    finally
    {
      if (localSocket == null);
    }
    try
    {
      localSocket.close();
      throw localObject;
    }
    catch (Throwable localThrowable1)
    {
      while (true)
        StatService.b().e(localThrowable1);
    }
  }

  private Map<String, Integer> a()
  {
    HashMap localHashMap = new HashMap();
    String str1 = StatConfig.a("__MTA_TEST_SPEED__", null);
    if ((str1 == null) || (str1.trim().length() == 0))
      return localHashMap;
    String[] arrayOfString1 = str1.split(";");
    int i = arrayOfString1.length;
    int j = 0;
    label45: String[] arrayOfString2;
    if (j < i)
    {
      arrayOfString2 = arrayOfString1[j].split(",");
      if ((arrayOfString2 != null) && (arrayOfString2.length == 2))
        break label81;
    }
    while (true)
    {
      j++;
      break label45;
      break;
      label81: String str2 = arrayOfString2[0];
      if ((str2 == null) || (str2.trim().length() == 0))
        continue;
      try
      {
        int k = Integer.valueOf(arrayOfString2[1]).intValue();
        localHashMap.put(str2, Integer.valueOf(k));
      }
      catch (NumberFormatException localNumberFormatException)
      {
        StatService.b().e(localNumberFormatException);
      }
    }
  }

  public void run()
  {
    try
    {
      if (!com.tencent.stat.common.k.h(this.a))
        return;
      if (this.b == null)
        this.b = a();
      if ((this.b == null) || (this.b.size() == 0))
      {
        StatService.b().w("empty domain list.");
        return;
      }
    }
    catch (Throwable localThrowable)
    {
      StatService.b().e(localThrowable);
      return;
    }
    JSONArray localJSONArray = new JSONArray();
    Iterator localIterator = this.b.entrySet().iterator();
    while (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      String str = (String)localEntry.getKey();
      if ((str == null) || (str.length() == 0))
      {
        StatService.b().w("empty domain name.");
        continue;
      }
      if ((Integer)localEntry.getValue() == null)
      {
        StatService.b().w("port is null for " + str);
        continue;
      }
      localJSONArray.put(a((String)localEntry.getKey(), ((Integer)localEntry.getValue()).intValue()).toJSONObject());
    }
    if (localJSONArray.length() != 0)
    {
      i locali = new i(this.a, StatService.a(this.a, false));
      locali.a(localJSONArray.toString());
      if (StatService.c(this.a) != null)
        StatService.c(this.a).post(new k(locali));
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.stat.j
 * JD-Core Version:    0.6.0
 */