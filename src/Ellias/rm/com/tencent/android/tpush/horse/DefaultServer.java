package com.tencent.android.tpush.horse;

import android.text.TextUtils;
import com.tencent.android.tpush.horse.data.ServerItem;
import com.tencent.android.tpush.logging.TLog;
import com.tencent.android.tpush.service.c.c;
import com.tencent.android.tpush.service.cache.CacheManager;
import com.tencent.android.tpush.service.channel.exception.NullReturnException;
import com.tencent.android.tpush.service.channel.protocol.ApList;
import com.tencent.android.tpush.service.i;
import com.tencent.android.tpush.service.report.ReportItem;
import com.tencent.android.tpush.service.report.e;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class DefaultServer
{
  public static String a = "tpns.qq.com";
  public static ArrayList b;
  public static String[] c;
  public static String[] d;
  public static String[] e;
  public static final DefaultServer.ENV f;
  public static final ArrayList g;

  static
  {
    Integer[] arrayOfInteger = new Integer[4];
    arrayOfInteger[0] = Integer.valueOf(443);
    arrayOfInteger[1] = Integer.valueOf(8080);
    arrayOfInteger[2] = Integer.valueOf(80);
    arrayOfInteger[3] = Integer.valueOf(14000);
    b = new ArrayList(Arrays.asList(arrayOfInteger));
    c = new String[] { "183.232.98.178" };
    d = new String[] { "58.251.139.182" };
    e = new String[] { "183.61.46.193" };
    f = DefaultServer.ENV.RELEASE;
    Collections.shuffle(b);
    switch (e.a[f.ordinal()])
    {
    default:
      a = "test.tpns.qq.com";
      c = new String[] { "183.232.98.173" };
      d = new String[] { "58.251.139.186" };
      e = new String[] { "183.61.46.188" };
    case 1:
    }
    while (true)
    {
      g = new ArrayList();
      g.add(new ServerItem("183.61.46.193", 443, 0));
      return;
      a = "tpns.qq.com";
      c = new String[] { "183.232.98.178", "111.30.131.23" };
      d = new String[] { "58.251.139.182", "125.39.240.55" };
      e = new String[] { "183.61.46.193", "123.151.152.50" };
    }
  }

  public static ArrayList a()
  {
    TLog.v("XGService", "@@ getAllServerItems()");
    ArrayList localArrayList = new ArrayList();
    for (int i = 0; i < c.length; i++)
    {
      Iterator localIterator4 = b.iterator();
      while (localIterator4.hasNext())
      {
        int i1 = ((Integer)localIterator4.next()).intValue();
        localArrayList.add(new ServerItem(c[i], i1, 3));
      }
    }
    for (int j = 0; j < e.length; j++)
    {
      Iterator localIterator3 = b.iterator();
      while (localIterator3.hasNext())
      {
        int n = ((Integer)localIterator3.next()).intValue();
        localArrayList.add(new ServerItem(e[j], n, 1));
      }
    }
    for (int k = 0; k < d.length; k++)
    {
      Iterator localIterator2 = b.iterator();
      while (localIterator2.hasNext())
      {
        int m = ((Integer)localIterator2.next()).intValue();
        localArrayList.add(new ServerItem(d[k], m, 2));
      }
    }
    String str1 = null;
    String str2 = CacheManager.getDomain(i.e());
    if (TextUtils.isEmpty(str2));
    for (String str3 = a; ; str3 = str2)
    {
      long l = System.currentTimeMillis();
      try
      {
        str1 = InetAddress.getByName(str3).getHostAddress();
        Iterator localIterator1 = b.iterator();
        while (localIterator1.hasNext())
          localArrayList.add(new ServerItem(str1, ((Integer)localIterator1.next()).intValue(), 0));
      }
      catch (UnknownHostException localUnknownHostException)
      {
        TLog.e("XGService", ">> Dns resolve err : " + localUnknownHostException.toString());
        ReportItem localReportItem2 = new ReportItem(-1, 1, 202, 10, str1, System.currentTimeMillis() - l, "domain=" + str3 + "&ip=" + str1 + "&exception=" + localUnknownHostException.getMessage());
        e.a().a(localReportItem2);
        return localArrayList;
        ReportItem localReportItem3 = new ReportItem(-1, 0, 201, 10, str1, System.currentTimeMillis() - l, "domain=" + str3 + "&ip=" + str1);
        e.a().a(localReportItem3);
        return localArrayList;
      }
      catch (Exception localException)
      {
        TLog.e("XGService", ">> Dns resolve err : " + localException.toString());
        ReportItem localReportItem1 = new ReportItem(-1, 1, 203, 10, str1, System.currentTimeMillis() - l, "domain=" + str3 + "&ip=" + str1 + "&exception=" + localException.getMessage());
        e.a().a(localReportItem1);
        return localArrayList;
      }
    }
  }

  public static ArrayList a(String paramString)
  {
    TLog.v("XGService", "@@ createDefaultItems(" + paramString + ")");
    if (paramString == null)
      throw new NullReturnException("createDefaultItems return null,because key is null");
    ArrayList localArrayList = new ArrayList();
    if (paramString.equals(String.valueOf(3)))
      for (int n = 0; n < c.length; n++)
      {
        Iterator localIterator4 = b.iterator();
        while (localIterator4.hasNext())
        {
          int i1 = ((Integer)localIterator4.next()).intValue();
          localArrayList.add(new ServerItem(c[n], i1, 3));
        }
      }
    if (paramString.equals(String.valueOf(1)))
      for (int k = 0; k < e.length; k++)
      {
        Iterator localIterator3 = b.iterator();
        while (localIterator3.hasNext())
        {
          int m = ((Integer)localIterator3.next()).intValue();
          localArrayList.add(new ServerItem(e[k], m, 1));
        }
      }
    if (paramString.equals(String.valueOf(2)))
      for (int i = 0; i < d.length; i++)
      {
        Iterator localIterator2 = b.iterator();
        while (localIterator2.hasNext())
        {
          int j = ((Integer)localIterator2.next()).intValue();
          localArrayList.add(new ServerItem(d[i], j, 2));
        }
      }
    String str1 = CacheManager.getDomain(i.e());
    TLog.i("XGService", "cache domain=" + str1);
    if (TextUtils.isEmpty(str1))
    {
      str1 = a;
      TLog.i("XGService", "default domain=" + str1);
    }
    String str2 = str1;
    long l = System.currentTimeMillis();
    try
    {
      str3 = InetAddress.getByName(str2).getHostAddress();
      TLog.i("XGService", "LocalDns parse domain success,domain ip=" + str3);
      ReportItem localReportItem3 = new ReportItem(-1, 0, 201, 10, str3, System.currentTimeMillis() - l, "domain=" + str2 + "&ip=" + str3);
      e.a().a(localReportItem3);
      Iterator localIterator1 = b.iterator();
      while (localIterator1.hasNext())
        localArrayList.add(new ServerItem(str3, ((Integer)localIterator1.next()).intValue(), 0));
    }
    catch (UnknownHostException localUnknownHostException)
    {
      while (true)
      {
        TLog.e("XGService", localUnknownHostException.toString());
        str3 = c[0];
        ReportItem localReportItem2 = new ReportItem(-1, 1, 202, 10, str3, System.currentTimeMillis() - l, "domain=" + str2 + "&ip=" + str3 + "&exception=" + localUnknownHostException);
        e.a().a(localReportItem2);
      }
    }
    catch (Exception localException)
    {
      while (true)
      {
        TLog.e("XGService", localException.toString());
        String str3 = c[0];
        ReportItem localReportItem1 = new ReportItem(-1, 1, 203, 10, str3, System.currentTimeMillis() - l, "domain=" + str2 + "&ip=" + str3 + "&exception=" + localException);
        e.a().a(localReportItem1);
      }
    }
    return localArrayList;
  }

  public static void a(ApList paramApList)
  {
    TLog.v("XGService", "@@ saveDefaultServer(" + paramApList + ")");
    Map localMap1 = paramApList.primary;
    Map localMap2 = paramApList.secondary;
    ArrayList localArrayList1 = paramApList.portList;
    ArrayList localArrayList2 = new ArrayList();
    ArrayList localArrayList3 = new ArrayList();
    ArrayList localArrayList4 = new ArrayList();
    Iterator localIterator1 = localMap1.keySet().iterator();
    while (localIterator1.hasNext())
    {
      Byte localByte2 = (Byte)localIterator1.next();
      TLog.i("XGService", ">> ip : " + localMap1.get(localByte2));
      String str3 = c.a(((Long)localMap1.get(localByte2)).longValue());
      if (TextUtils.isEmpty(str3))
        continue;
      Iterator localIterator6 = localArrayList1.iterator();
      while (localIterator6.hasNext())
      {
        ServerItem localServerItem2 = new ServerItem(str3, ((Integer)localIterator6.next()).intValue(), localByte2.intValue());
        TLog.i("XGService", ">> serverItem : " + localServerItem2.toString() + ",key : " + localByte2);
        if (localByte2.byteValue() == 3)
          localArrayList2.add(localServerItem2);
        if (localByte2.byteValue() == 1)
          localArrayList3.add(localServerItem2);
        if (localByte2.byteValue() != 2)
          continue;
        localArrayList4.add(localServerItem2);
      }
    }
    Iterator localIterator2 = localMap2.keySet().iterator();
    while (localIterator2.hasNext())
    {
      Byte localByte1 = (Byte)localIterator2.next();
      TLog.i("XGService", ">> sec ip : " + localMap1.get(localByte1));
      String str2 = c.a(((Long)localMap2.get(localByte1)).longValue());
      if (TextUtils.isEmpty(str2))
        continue;
      Iterator localIterator5 = localArrayList1.iterator();
      while (localIterator5.hasNext())
      {
        ServerItem localServerItem1 = new ServerItem(str2, ((Integer)localIterator5.next()).intValue(), localByte1.intValue());
        TLog.i("XGService", ">> serverItem : " + localServerItem1.toString());
        if (localByte1.byteValue() == 3)
          localArrayList2.add(localServerItem1);
        if (localByte1.byteValue() == 1)
          localArrayList3.add(localServerItem1);
        if (localByte1.byteValue() != 2)
          continue;
        localArrayList4.add(localServerItem1);
      }
    }
    if (!localArrayList2.isEmpty())
      CacheManager.addServerItems(i.e(), "3", localArrayList2);
    if (!localArrayList3.isEmpty())
      CacheManager.addServerItems(i.e(), "1", localArrayList3);
    if (!localArrayList4.isEmpty())
      CacheManager.addServerItems(i.e(), "2", localArrayList4);
    ArrayList localArrayList5 = paramApList.speedTestIpList;
    ArrayList localArrayList6 = new ArrayList();
    Iterator localIterator3 = localArrayList5.iterator();
    while (localIterator3.hasNext())
    {
      Long localLong = (Long)localIterator3.next();
      Iterator localIterator4 = localArrayList1.iterator();
      while (localIterator4.hasNext())
      {
        Integer localInteger = (Integer)localIterator4.next();
        TLog.i("XGService", ">> serverItem : " + new ServerItem(localLong.longValue(), localInteger.intValue(), 0).toString());
        localArrayList6.add(new ServerItem(localLong.longValue(), localInteger.intValue(), 0));
      }
    }
    CacheManager.saveSpeedTestList(i.e(), localArrayList6);
    String str1 = paramApList.domain;
    TLog.i("XGService", ">> domain : " + str1);
    if ((!TextUtils.isEmpty(str1)) && (!str1.equals(CacheManager.getDomain(i.e()))))
    {
      CacheManager.clearDomainServerItem(i.e());
      CacheManager.saveDomain(i.e(), str1);
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.android.tpush.horse.DefaultServer
 * JD-Core Version:    0.6.0
 */