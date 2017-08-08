package com.pay.http;

import android.content.Context;
import android.content.SharedPreferences;
import com.pay.AndroidPay;
import com.pay.common.tool.APLog;
import com.pay.tool.APAppDataInterface;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;
import org.apache.http.conn.util.InetAddressUtils;

public class APIPList
{
  private static APIPList c = null;
  private HashMap a = new HashMap();
  private HashMap b = new HashMap();
  private APIPDatabase d;
  private String e;
  private String[] f = { "183.60.36.92,'',''" };
  private String[] g = { "183.61.41.148,'',''" };
  private String[] h = { "183.61.38.211,'',''", "14.17.41.172,'',''", "183.232.88.171,'',''", "163.177.71.169,'',''", "112.90.77.191,'',''", "183.232.121.160,'',''", "112.90.143.146,'',''", "183.61.38.211,'',''" };

  private APIPList(Context paramContext)
  {
    HashMap localHashMap = new HashMap();
    this.e = APAppDataInterface.singleton().getEnv();
    this.d = new APIPDatabase(paramContext);
    Iterator localIterator;
    if (this.d.getCount(this.e, "TencentUnipayIPTable") == 0)
    {
      a(localHashMap);
      localIterator = localHashMap.entrySet().iterator();
    }
    while (true)
    {
      if (!localIterator.hasNext())
      {
        return;
        localHashMap.clear();
        this.d.getIPStateMap(localHashMap, this.e, "TencentUnipayIPTable");
        break;
      }
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      String str = (String)localEntry.getKey();
      APIPState localAPIPState = (APIPState)localEntry.getValue();
      if (localAPIPState.seqFailTimes >= 3)
      {
        this.b.put(str, localAPIPState);
        continue;
      }
      this.a.put(str, localAPIPState);
    }
  }

  private void a(HashMap paramHashMap)
  {
    paramHashMap.clear();
    int m;
    if (this.e.equals("dev"))
    {
      m = 0;
      if (m < this.f.length);
    }
    while (true)
    {
      return;
      APIPState localAPIPState4 = new APIPState();
      localAPIPState4.ip = this.f[m].split(",")[0];
      localAPIPState4.province = this.f[m].split(",")[1];
      localAPIPState4.city = this.f[m].split(",")[2];
      localAPIPState4.ipEnv = "dev";
      this.d.insertIP(localAPIPState4, "TencentUnipayIPTable");
      paramHashMap.put(localAPIPState4.ip, localAPIPState4);
      m++;
      break;
      if (this.e.equals("custom"))
      {
        for (int k = 0; k < this.h.length; k++)
        {
          APIPState localAPIPState3 = new APIPState();
          localAPIPState3.ip = this.h[k].split(",")[0];
          localAPIPState3.province = this.h[k].split(",")[1];
          localAPIPState3.city = this.h[k].split(",")[2];
          localAPIPState3.ipEnv = "custom";
          this.d.insertIP(localAPIPState3, "TencentUnipayIPTable");
          paramHashMap.put(localAPIPState3.ip, localAPIPState3);
        }
        continue;
      }
      if (this.e.equals("test"))
      {
        for (int j = 0; j < this.g.length; j++)
        {
          APIPState localAPIPState2 = new APIPState();
          localAPIPState2.ip = this.g[j].split(",")[0];
          localAPIPState2.province = this.g[j].split(",")[1];
          localAPIPState2.city = this.g[j].split(",")[2];
          localAPIPState2.ipEnv = "test";
          this.d.insertIP(localAPIPState2, "TencentUnipayIPTable");
          paramHashMap.put(localAPIPState2.ip, localAPIPState2);
        }
        continue;
      }
      for (int i = 0; i < this.h.length; i++)
      {
        APIPState localAPIPState1 = new APIPState();
        localAPIPState1.ip = this.h[i].split(",")[0];
        localAPIPState1.province = this.h[i].split(",")[1];
        localAPIPState1.city = this.h[i].split(",")[2];
        localAPIPState1.ipEnv = "release";
        this.d.insertIP(localAPIPState1, "TencentUnipayIPTable");
        paramHashMap.put(localAPIPState1.ip, localAPIPState1);
      }
    }
  }

  public static APIPList getInstance()
  {
    if (c != null)
      return c;
    return null;
  }

  public static APIPList getInstance(Context paramContext)
  {
    if (c == null)
      c = new APIPList(paramContext);
    return c;
  }

  public static void initIPList(Context paramContext)
  {
    c = null;
    c = new APIPList(paramContext);
  }

  public static void release()
  {
    c = null;
  }

  public boolean checkIpInList(String paramString)
  {
    Iterator localIterator1 = this.b.entrySet().iterator();
    Iterator localIterator2;
    if (!localIterator1.hasNext())
      localIterator2 = this.a.entrySet().iterator();
    do
      if (!localIterator2.hasNext())
      {
        return false;
        if (!((APIPState)((Map.Entry)localIterator1.next()).getValue()).ip.equals(paramString))
          break;
        return true;
      }
    while (!((APIPState)((Map.Entry)localIterator2.next()).getValue()).ip.equals(paramString));
    return true;
  }

  public void close()
  {
    if (this.d != null)
      this.d.closeDB();
  }

  public String getRandomIP(String paramString)
  {
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = this.a.entrySet().iterator();
    Object localObject1 = "";
    int j;
    if (!localIterator.hasNext())
    {
      int i = localArrayList.size();
      j = (int)(Math.random() * i);
      APLog.i("APIPList", "random = " + j + " ipArrayList.size = " + localArrayList.size());
      if (localArrayList.size() <= 0)
        break label211;
    }
    label211: for (Object localObject2 = (String)localArrayList.get(j); ; localObject2 = localObject1)
    {
      if ((localObject2 == null) || (((String)localObject2).equals("")))
      {
        if (this.e.equals("dev"))
          localObject2 = "183.60.36.92";
      }
      else
      {
        return localObject2;
        Map.Entry localEntry = (Map.Entry)localIterator.next();
        if (((String)localEntry.getKey()).equals(paramString))
          break;
        String str = (String)localEntry.getKey();
        localArrayList.add(str);
        localObject1 = str;
        break;
      }
      if (this.e.equals("test"))
        return "sandbox.api.unipay.qq.com";
      return "api.unipay.qq.com";
    }
  }

  public void init()
  {
    String str = getRandomIP(null);
    APAppDataInterface.singleton().setSysServerIP(str);
    if (this.b.size() < this.a.size())
    {
      Context localContext = AndroidPay.singleton().applicationContext;
      int i = 0;
      if (localContext != null)
      {
        long l = localContext.getSharedPreferences("TencentUnipay", 0).getLong("updateIPPreTime", 0L);
        boolean bool = System.currentTimeMillis() - l < 86400000L;
        i = 0;
        if (bool)
          i = 1;
      }
      if (i == 0);
    }
    else
    {
      APNetworkManager.getInstance().getIpList(new e(this));
    }
  }

  public boolean isIPAddress(String paramString)
  {
    return (paramString != null) && ((InetAddressUtils.isIPv4Address(paramString)) || (InetAddressUtils.isIPv6Address(paramString)));
  }

  public void setIPState(String paramString, boolean paramBoolean)
  {
    APIPState localAPIPState = (APIPState)this.a.get(paramString);
    if (localAPIPState != null)
    {
      localAPIPState.accessTimes = (1 + localAPIPState.accessTimes);
      if (!paramBoolean)
        break label50;
      localAPIPState.succTimes = (1 + localAPIPState.succTimes);
      localAPIPState.seqFailTimes = 0;
    }
    while (true)
    {
      updateToDB();
      return;
      label50: localAPIPState.failTimes = (1 + localAPIPState.failTimes);
      localAPIPState.seqFailTimes = (1 + localAPIPState.seqFailTimes);
      if (localAPIPState.seqFailTimes < 3)
        continue;
      this.a.remove(paramString);
      this.b.put(paramString, localAPIPState);
    }
  }

  public void updateIPList(HashMap paramHashMap)
  {
    this.a.clear();
    this.b.clear();
    this.d.clearAll("TencentUnipayIPTable");
    Iterator localIterator = paramHashMap.entrySet().iterator();
    while (true)
    {
      if (!localIterator.hasNext())
        return;
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      this.a.put((String)localEntry.getKey(), (APIPState)localEntry.getValue());
      APIPState localAPIPState = (APIPState)localEntry.getValue();
      localAPIPState.ipEnv = this.e;
      this.d.insertIP(localAPIPState, "TencentUnipayIPTable");
    }
  }

  public void updateToDB()
  {
    Iterator localIterator1 = this.a.entrySet().iterator();
    Iterator localIterator2;
    if (!localIterator1.hasNext())
      localIterator2 = this.b.entrySet().iterator();
    while (true)
    {
      if (!localIterator2.hasNext())
      {
        return;
        APIPState localAPIPState1 = (APIPState)((Map.Entry)localIterator1.next()).getValue();
        this.d.updateIP(localAPIPState1, "TencentUnipayIPTable");
        break;
      }
      APIPState localAPIPState2 = (APIPState)((Map.Entry)localIterator2.next()).getValue();
      this.d.updateIP(localAPIPState2, "TencentUnipayIPTable");
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.http.APIPList
 * JD-Core Version:    0.6.0
 */