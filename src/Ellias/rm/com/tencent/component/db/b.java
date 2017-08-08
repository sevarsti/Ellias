package com.tencent.component.db;

import java.util.HashSet;
import java.util.Iterator;

class b
  implements EntityManager.UpdateListener
{
  b(EntityManagerFactory paramEntityManagerFactory)
  {
  }

  public void onDatabaseDowngrade(ISQLiteDatabase paramISQLiteDatabase, int paramInt1, int paramInt2)
  {
    Iterator localIterator = EntityManagerFactory.a(this.a).iterator();
    while (localIterator.hasNext())
    {
      EntityManager.UpdateListener localUpdateListener = (EntityManager.UpdateListener)localIterator.next();
      if (localUpdateListener == null)
        continue;
      localUpdateListener.onDatabaseDowngrade(paramISQLiteDatabase, paramInt1, paramInt2);
    }
  }

  public void onDatabaseUpgrade(ISQLiteDatabase paramISQLiteDatabase, int paramInt1, int paramInt2)
  {
    Iterator localIterator = EntityManagerFactory.a(this.a).iterator();
    while (localIterator.hasNext())
    {
      EntityManager.UpdateListener localUpdateListener = (EntityManager.UpdateListener)localIterator.next();
      if (localUpdateListener == null)
        continue;
      localUpdateListener.onDatabaseUpgrade(paramISQLiteDatabase, paramInt1, paramInt2);
    }
  }

  public void onTableDowngrade(ISQLiteDatabase paramISQLiteDatabase, String paramString, int paramInt1, int paramInt2)
  {
    Iterator localIterator = EntityManagerFactory.a(this.a).iterator();
    while (localIterator.hasNext())
    {
      EntityManager.UpdateListener localUpdateListener = (EntityManager.UpdateListener)localIterator.next();
      if (localUpdateListener == null)
        continue;
      localUpdateListener.onTableDowngrade(paramISQLiteDatabase, paramString, paramInt1, paramInt2);
    }
  }

  public void onTableUpgrade(ISQLiteDatabase paramISQLiteDatabase, String paramString, int paramInt1, int paramInt2)
  {
    Iterator localIterator = EntityManagerFactory.a(this.a).iterator();
    while (localIterator.hasNext())
    {
      EntityManager.UpdateListener localUpdateListener = (EntityManager.UpdateListener)localIterator.next();
      if (localUpdateListener == null)
        continue;
      localUpdateListener.onTableUpgrade(paramISQLiteDatabase, paramString, paramInt1, paramInt2);
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.db.b
 * JD-Core Version:    0.6.0
 */