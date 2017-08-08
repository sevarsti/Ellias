package com.tencent.component.db;

import android.content.Context;
import java.util.concurrent.ConcurrentHashMap;

public class DefaultSQLiteOpenHelper
  implements ISQLiteOpenHelper
{
  private static ConcurrentHashMap b = new ConcurrentHashMap();
  private DefaultSQLiteOpenHelper.DBHelper a;

  public static DefaultSQLiteOpenHelper a(String paramString)
  {
    DefaultSQLiteOpenHelper localDefaultSQLiteOpenHelper1 = (DefaultSQLiteOpenHelper)b.get(paramString);
    if (localDefaultSQLiteOpenHelper1 == null)
    {
      monitorenter;
      try
      {
        DefaultSQLiteOpenHelper localDefaultSQLiteOpenHelper2 = (DefaultSQLiteOpenHelper)b.get(paramString);
        if (localDefaultSQLiteOpenHelper2 == null)
          localDefaultSQLiteOpenHelper2 = new DefaultSQLiteOpenHelper();
        return localDefaultSQLiteOpenHelper2;
      }
      finally
      {
        monitorexit;
      }
    }
    return localDefaultSQLiteOpenHelper1;
  }

  public ISQLiteDatabase a()
  {
    return new DefaultSQLiteDatabase(this.a.getWritableDatabase());
  }

  public void a(Context paramContext, String paramString, int paramInt)
  {
    a(paramContext, paramString, paramInt, null);
  }

  public void a(Context paramContext, String paramString, int paramInt, EntityManager.UpdateListener paramUpdateListener)
  {
    if (this.a == null)
      this.a = new DefaultSQLiteOpenHelper.DBHelper(paramContext, paramString, null, paramInt, paramUpdateListener);
  }

  public ISQLiteDatabase b()
  {
    return new DefaultSQLiteDatabase(this.a.getReadableDatabase());
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.db.DefaultSQLiteOpenHelper
 * JD-Core Version:    0.6.0
 */