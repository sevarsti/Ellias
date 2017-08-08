package com.tencent.mid.b;

import android.content.Context;
import com.tencent.mid.api.MidEntity;
import com.tencent.mid.util.Util;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class g
{
  private static g b = null;
  private Map<Integer, f> a = null;

  private g(Context paramContext)
  {
    this.a.put(Integer.valueOf(1), new e(paramContext));
    this.a.put(Integer.valueOf(2), new c(paramContext));
    this.a.put(Integer.valueOf(4), new d(paramContext));
  }

  public static g a(Context paramContext)
  {
    monitorenter;
    try
    {
      if (b == null)
        b = new g(paramContext);
      g localg = b;
      return localg;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public MidEntity a()
  {
    Integer[] arrayOfInteger = new Integer[3];
    arrayOfInteger[0] = Integer.valueOf(1);
    arrayOfInteger[1] = Integer.valueOf(2);
    arrayOfInteger[2] = Integer.valueOf(4);
    return a(new ArrayList(Arrays.asList(arrayOfInteger)));
  }

  public MidEntity a(List<Integer> paramList)
  {
    if ((paramList == null) || (paramList.size() == 0))
      return null;
    Iterator localIterator = paramList.iterator();
    while (localIterator.hasNext())
    {
      Integer localInteger = (Integer)localIterator.next();
      f localf = (f)this.a.get(localInteger);
      if (localf == null)
        continue;
      MidEntity localMidEntity = localf.h();
      if ((localMidEntity != null) && (localMidEntity.isMidValid()))
        return localMidEntity;
    }
    return null;
  }

  public void a(int paramInt1, int paramInt2)
  {
    a locala = b();
    if (paramInt1 > 0)
      locala.c(paramInt1);
    if (paramInt2 > 0)
      locala.a(paramInt2);
    locala.a(System.currentTimeMillis());
    locala.b(0);
    a(locala);
  }

  public void a(MidEntity paramMidEntity)
  {
    Iterator localIterator = this.a.entrySet().iterator();
    while (localIterator.hasNext())
      ((f)((Map.Entry)localIterator.next()).getValue()).a(paramMidEntity);
  }

  public void a(a parama)
  {
    Iterator localIterator = this.a.entrySet().iterator();
    while (localIterator.hasNext())
      ((f)((Map.Entry)localIterator.next()).getValue()).b(parama);
  }

  public a b()
  {
    Integer[] arrayOfInteger = new Integer[2];
    arrayOfInteger[0] = Integer.valueOf(1);
    arrayOfInteger[1] = Integer.valueOf(4);
    return b(new ArrayList(Arrays.asList(arrayOfInteger)));
  }

  public a b(List<Integer> paramList)
  {
    if ((paramList == null) || (paramList.size() == 0))
      return null;
    Iterator localIterator = paramList.iterator();
    while (localIterator.hasNext())
    {
      Integer localInteger = (Integer)localIterator.next();
      f localf = (f)this.a.get(localInteger);
      if (localf == null)
        continue;
      a locala = localf.j();
      if (locala != null)
        return locala;
    }
    return null;
  }

  public void c()
  {
    Util.logInfo("clear mid cache");
    Iterator localIterator = this.a.entrySet().iterator();
    while (localIterator.hasNext())
      ((f)((Map.Entry)localIterator.next()).getValue()).i();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.mid.b.g
 * JD-Core Version:    0.6.0
 */