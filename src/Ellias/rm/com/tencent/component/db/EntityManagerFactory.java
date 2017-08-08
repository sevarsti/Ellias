package com.tencent.component.db;

import android.content.Context;
import android.text.TextUtils;
import com.tencent.component.db.exception.DBException;
import com.tencent.component.db.util.TableUtils;
import com.tencent.component.utils.DebugUtil;
import com.tencent.component.utils.SecurityUtil;
import com.tencent.component.utils.log.LogUtil;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

public class EntityManagerFactory
{
  private static final String a = "EntityManagerFactory";
  private static final String b = String.valueOf(-2147483648);
  private static volatile HashMap n = new HashMap();
  private final Context c;
  private final HashMap d = new HashMap();
  private final HashMap e = new HashMap();
  private String f;
  private String g;
  private int h;
  private ISQLiteOpenHelper i;
  private final HashSet j = new HashSet();
  private final DefaultUpdateListener k = new DefaultUpdateListener();
  private EntityManager.UpdateListener l = new b(this);
  private a m = new c(this);

  private EntityManagerFactory(Context paramContext, int paramInt, String paramString, ISQLiteOpenHelper paramISQLiteOpenHelper)
  {
    this.c = paramContext.getApplicationContext();
    this.f = paramString;
    if (DebugUtil.a());
    for (this.g = ("db_" + paramString); ; this.g = SecurityUtil.a(paramString))
    {
      this.h = paramInt;
      if (paramISQLiteOpenHelper == null)
        paramISQLiteOpenHelper = DefaultSQLiteOpenHelper.a(paramString);
      paramISQLiteOpenHelper.a(this.c, this.g, this.h, this.l);
      this.i = paramISQLiteOpenHelper;
      return;
    }
  }

  public static EntityManagerFactory a(Context paramContext, int paramInt, String paramString, ISQLiteOpenHelper paramISQLiteOpenHelper, EntityManager.UpdateListener paramUpdateListener)
  {
    if (TextUtils.isEmpty(paramString))
      paramString = b;
    EntityManagerFactory localEntityManagerFactory = (EntityManagerFactory)n.get(paramString);
    if (localEntityManagerFactory == null);
    synchronized (n)
    {
      localEntityManagerFactory = (EntityManagerFactory)n.get(paramString);
      if (localEntityManagerFactory == null)
      {
        localEntityManagerFactory = new EntityManagerFactory(paramContext, paramInt, paramString, paramISQLiteOpenHelper);
        n.put(paramString, localEntityManagerFactory);
      }
      localEntityManagerFactory.a(paramUpdateListener);
      return localEntityManagerFactory;
    }
  }

  private static String a(String paramString1, String paramString2, boolean paramBoolean)
  {
    return paramString1 + "_" + paramString2 + "_" + paramBoolean;
  }

  public EntityManager a(Class paramClass, String paramString)
  {
    return a(paramClass, paramString, null, false);
  }

  public EntityManager a(Class paramClass, String paramString, ClassLoader paramClassLoader, boolean paramBoolean)
  {
    if (paramClass == null)
      throw new RuntimeException("invalid Entity class: null");
    String str1 = TableUtils.a(paramClass, paramString);
    if (TextUtils.isEmpty(str1))
      throw new RuntimeException("invalid table name: " + str1);
    while (true)
    {
      synchronized (this.d)
      {
        String str2 = a(this.f, str1, paramBoolean);
        Object localObject2 = (d)this.d.get(str2);
        if ((localObject2 == null) || (((d)localObject2).a == null) || (((d)localObject2).a.a() == paramClass))
          continue;
        localObject2 = null;
        if ((localObject2 != null) && (((d)localObject2).a != null) && (!((d)localObject2).a.d()))
          continue;
        if (paramClassLoader == null)
        {
          localClassLoader = getClass().getClassLoader();
          EntityManager localEntityManager1 = new EntityManager(this.c, paramClass, this.l, this.g, str1, localClassLoader, this.i);
          localEntityManager1.a(this.m);
          d locald = new d(localEntityManager1, this.f, paramBoolean);
          this.d.put(str2, locald);
          this.e.put(localEntityManager1, str2);
          localObject2 = locald;
          EntityManager localEntityManager2 = ((d)localObject2).a;
          return localEntityManager2;
        }
      }
      ClassLoader localClassLoader = paramClassLoader;
    }
  }

  public void a()
  {
    a(b);
  }

  public void a(EntityManager.UpdateListener paramUpdateListener)
  {
    if (paramUpdateListener != null)
    {
      this.j.add(paramUpdateListener);
      return;
    }
    this.j.add(this.k);
  }

  public void a(String paramString)
  {
    while (true)
    {
      Iterator localIterator;
      d locald;
      synchronized (this.d)
      {
        localIterator = this.d.values().iterator();
        if (!localIterator.hasNext())
          break;
        locald = (d)localIterator.next();
        if (locald == null)
          localIterator.remove();
      }
      if ((!b.equals(paramString)) && (locald.b != paramString))
        continue;
      locald.a.a(null);
      locald.a.close();
      localIterator.remove();
      this.e.remove(locald.a);
    }
    monitorexit;
  }

  public void b()
  {
    b(b);
  }

  public void b(String paramString)
  {
    synchronized (this.d)
    {
      Iterator localIterator = this.d.values().iterator();
      while (true)
        if (localIterator.hasNext())
        {
          d locald = (d)localIterator.next();
          if ((locald == null) || (locald.c))
            continue;
          if (!b.equals(paramString))
          {
            String str = locald.b;
            if (str != paramString)
              continue;
          }
          try
          {
            locald.a.delete(null);
          }
          catch (DBException localDBException)
          {
            LogUtil.e("EntityManagerFactory", localDBException.getMessage(), localDBException);
          }
        }
    }
    monitorexit;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.db.EntityManagerFactory
 * JD-Core Version:    0.6.0
 */