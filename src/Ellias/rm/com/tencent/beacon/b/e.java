package com.tencent.beacon.b;

import com.tencent.beacon.c.e.c;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public final class e
  implements com.tencent.beacon.upload.f
{
  public static List<b> a(com.tencent.beacon.c.e.e parame)
  {
    if (parame == null)
    {
      com.tencent.beacon.d.a.b("SpeedMonitorStrategy sourcePackage is null", new Object[0]);
      return null;
    }
    ArrayList localArrayList1 = parame.a;
    ArrayList localArrayList2 = parame.b;
    ArrayList localArrayList3 = parame.c;
    ArrayList localArrayList4 = new ArrayList();
    if (localArrayList1 != null)
    {
      Object[] arrayOfObject3 = new Object[1];
      arrayOfObject3[0] = Integer.valueOf(localArrayList1.size());
      com.tencent.beacon.d.a.b("ipList size:%d", arrayOfObject3);
      Iterator localIterator3 = localArrayList1.iterator();
      while (localIterator3.hasNext())
      {
        c localc = (c)localIterator3.next();
        b localb3 = new b();
        localb3.b(localc.a + ":" + localc.b);
        localb3.a(new Date().getTime());
        localb3.a("IP");
        localb3.b(localc.c);
        localArrayList4.add(localb3);
      }
    }
    if (localArrayList2 != null)
    {
      Object[] arrayOfObject2 = new Object[1];
      arrayOfObject2[0] = Integer.valueOf(localArrayList2.size());
      com.tencent.beacon.d.a.b("dnsList size:%d", arrayOfObject2);
      Iterator localIterator2 = localArrayList2.iterator();
      while (localIterator2.hasNext())
      {
        com.tencent.beacon.c.e.b localb = (com.tencent.beacon.c.e.b)localIterator2.next();
        b localb2 = new b();
        localb2.b(localb.a);
        localb2.a(new Date().getTime());
        localb2.a("PG");
        localb2.b(localb.c);
        localb2.a(localb.b);
        localArrayList4.add(localb2);
      }
    }
    if (localArrayList3 != null)
    {
      Object[] arrayOfObject1 = new Object[1];
      arrayOfObject1[0] = Integer.valueOf(localArrayList3.size());
      com.tencent.beacon.d.a.b("hostList size:%d", arrayOfObject1);
      Iterator localIterator1 = localArrayList3.iterator();
      while (localIterator1.hasNext())
      {
        com.tencent.beacon.c.e.f localf = (com.tencent.beacon.c.e.f)localIterator1.next();
        b localb1 = new b();
        localb1.c(localf.d);
        localb1.d(localf.a + ":" + localf.b);
        localb1.b(localf.c);
        localb1.a(new Date().getTime());
        localb1.a("HOST");
        localArrayList4.add(localb1);
        com.tencent.beacon.d.a.a(" TxHostSource: " + localb1.toString(), new Object[0]);
      }
    }
    if (localArrayList4.size() > 0)
      return localArrayList4;
    return null;
  }

  public final void a(int paramInt, byte[] paramArrayOfByte, boolean paramBoolean)
  {
    d locald;
    if ((paramInt == 105) && (paramArrayOfByte != null))
    {
      locald = d.d();
      if ((locald == null) || (!d.e()));
    }
    try
    {
      com.tencent.beacon.c.e.e locale = new com.tencent.beacon.c.e.e();
      locale.a(new com.tencent.beacon.e.a(paramArrayOfByte));
      List localList = a(locale);
      if ((localList != null) && (localList.size() > 0))
        locald.a((b[])localList.toArray(new b[0]));
      return;
    }
    catch (Throwable localThrowable)
    {
      localThrowable.printStackTrace();
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = localThrowable.toString();
      com.tencent.beacon.d.a.d(" process sm strategy error: %s", arrayOfObject);
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.beacon.b.e
 * JD-Core Version:    0.6.0
 */