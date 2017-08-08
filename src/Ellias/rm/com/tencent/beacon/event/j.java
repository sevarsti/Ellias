package com.tencent.beacon.event;

import android.content.Context;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class j extends com.tencent.beacon.upload.a
{
  private com.tencent.beacon.c.a.b d;
  private List<h> e;

  public static com.tencent.beacon.c.a.b a(Context paramContext, int paramInt, List<h> paramList)
  {
    if ((paramList == null) || (paramList.size() <= 0));
    while (true)
    {
      return null;
      try
      {
        com.tencent.beacon.d.a.b(" current size:}" + paramList.size(), new Object[0]);
        com.tencent.beacon.c.b.b localb = b(paramList);
        if (localb == null)
          continue;
        byte[] arrayOfByte = localb.a();
        if (arrayOfByte == null)
          continue;
        com.tencent.beacon.c.a.b localb1 = a(paramContext, paramInt, arrayOfByte);
        return localb1;
      }
      catch (Throwable localThrowable)
      {
        localThrowable.printStackTrace();
        com.tencent.beacon.d.a.d(" RealTimeRecordUploadDatas.encode2EventRecordPackage error}", new Object[0]);
      }
    }
    return null;
  }

  private void a(com.tencent.beacon.c.a.b paramb)
  {
    monitorenter;
    try
    {
      this.d = paramb;
      monitorexit;
      return;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  private void a(List<h> paramList)
  {
    monitorenter;
    try
    {
      this.e = paramList;
      monitorexit;
      return;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  private static com.tencent.beacon.c.b.b b(List<h> paramList)
  {
    if ((paramList == null) || (paramList.size() <= 0))
      return null;
    com.tencent.beacon.c.b.b localb;
    ArrayList localArrayList;
    try
    {
      localb = new com.tencent.beacon.c.b.b();
      localArrayList = new ArrayList();
      Iterator localIterator = paramList.iterator();
      while (localIterator.hasNext())
      {
        com.tencent.beacon.c.b.a locala = a.e((h)localIterator.next());
        if (locala == null)
          continue;
        localArrayList.add(locala);
      }
    }
    catch (Throwable localThrowable)
    {
      localThrowable.printStackTrace();
      return null;
    }
    localb.a = localArrayList;
    com.tencent.beacon.d.a.b(" RealTimeRecordUploadDatas.encode2EventRecordPackage() end}", new Object[0]);
    return localb;
  }

  private List<h> e()
  {
    monitorenter;
    try
    {
      List localList = this.e;
      monitorexit;
      return localList;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public final com.tencent.beacon.c.a.b a()
  {
    monitorenter;
    monitorexit;
    return null;
  }

  public final void a(boolean paramBoolean)
  {
    a(null);
    List localList = e();
    a(null);
    if (localList != null)
    {
      if (!paramBoolean)
      {
        com.tencent.beacon.d.a.b(" isHandled false , record to db}", new Object[0]);
        com.tencent.beacon.applog.a.c(null, localList);
      }
      localList.clear();
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.beacon.event.j
 * JD-Core Version:    0.6.0
 */