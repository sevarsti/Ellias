package com.tencent.component.db.entity;

import android.text.TextUtils;
import com.tencent.component.db.EntityContext;
import com.tencent.component.db.util.TableUtils;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class TableEntity
{
  private static final ConcurrentHashMap g = new ConcurrentHashMap();
  public final ConcurrentHashMap a;
  private String b;
  private int c;
  private boolean d;
  private IdEntity e;
  private Class f;

  private TableEntity(Class paramClass, EntityContext paramEntityContext)
  {
    this.b = TableUtils.a(paramClass, paramEntityContext.a());
    this.e = TableUtils.d(paramClass);
    this.c = TableUtils.b(paramClass);
    this.d = TableUtils.c(paramClass);
    this.a = TableUtils.a(paramClass, paramEntityContext);
    this.f = paramClass;
  }

  public static TableEntity a(Class paramClass, EntityContext paramEntityContext)
  {
    monitorenter;
    try
    {
      String str1 = TableUtils.a(paramClass, paramEntityContext.a());
      String str2 = paramClass.getCanonicalName() + "_" + str1;
      TableEntity localTableEntity = (TableEntity)g.get(str2);
      if ((localTableEntity != null) && (localTableEntity.f != paramClass))
        localTableEntity = null;
      if (localTableEntity == null)
      {
        localTableEntity = new TableEntity(paramClass, paramEntityContext);
        g.put(str2, localTableEntity);
      }
      return localTableEntity;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public static void a(Class paramClass)
  {
    monitorenter;
    if (paramClass != null);
    try
    {
      g.remove(paramClass.getCanonicalName());
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

  public static void a(String paramString)
  {
    monitorenter;
    while (true)
    {
      try
      {
        if (g.size() <= 0)
          continue;
        Iterator localIterator = g.entrySet().iterator();
        if (localIterator.hasNext())
        {
          Map.Entry localEntry = (Map.Entry)localIterator.next();
          TableEntity localTableEntity = (TableEntity)localEntry.getValue();
          if ((localTableEntity == null) || (!localTableEntity.a().equals(paramString)))
            continue;
          str = (String)localEntry.getKey();
          if (TextUtils.isEmpty(str))
            continue;
          g.remove(str);
          return;
        }
      }
      finally
      {
        monitorexit;
      }
      String str = null;
    }
  }

  public String a()
  {
    return this.b;
  }

  public ColumnEntity b(String paramString)
  {
    return (ColumnEntity)this.a.get(paramString);
  }

  public IdEntity b()
  {
    return this.e;
  }

  public int c()
  {
    return this.c;
  }

  public boolean d()
  {
    return this.d;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.db.entity.TableEntity
 * JD-Core Version:    0.6.0
 */