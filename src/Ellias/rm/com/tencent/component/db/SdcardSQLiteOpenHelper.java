package com.tencent.component.db;

import android.content.Context;
import android.text.TextUtils;
import java.util.concurrent.ConcurrentHashMap;

public class SdcardSQLiteOpenHelper
  implements ISQLiteOpenHelper
{
  private static ConcurrentHashMap c = new ConcurrentHashMap();
  private DefaultSQLiteOpenHelper.DBHelper a;
  private String b;

  private SdcardSQLiteOpenHelper(String paramString)
  {
    this.b = paramString;
  }

  public static SdcardSQLiteOpenHelper a(String paramString1, String paramString2)
  {
    if (!TextUtils.isEmpty(paramString2))
      paramString1 = paramString1 + "_" + paramString2.hashCode();
    SdcardSQLiteOpenHelper localSdcardSQLiteOpenHelper1 = (SdcardSQLiteOpenHelper)c.get(paramString1);
    if (localSdcardSQLiteOpenHelper1 == null)
    {
      monitorenter;
      try
      {
        SdcardSQLiteOpenHelper localSdcardSQLiteOpenHelper2 = (SdcardSQLiteOpenHelper)c.get(paramString1);
        if (localSdcardSQLiteOpenHelper2 == null)
          localSdcardSQLiteOpenHelper2 = new SdcardSQLiteOpenHelper(paramString2);
        return localSdcardSQLiteOpenHelper2;
      }
      finally
      {
        monitorexit;
      }
    }
    return localSdcardSQLiteOpenHelper1;
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
      this.a = new DefaultSQLiteOpenHelper.DBHelper(new SdcardSQLiteOpenHelper.SdcardDatabaseContext(paramContext, this.b), paramString, null, paramInt, paramUpdateListener);
  }

  public ISQLiteDatabase b()
  {
    return new DefaultSQLiteDatabase(this.a.getReadableDatabase());
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.db.SdcardSQLiteOpenHelper
 * JD-Core Version:    0.6.0
 */