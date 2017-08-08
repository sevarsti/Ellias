package com.tencent.component.db;

import android.database.Cursor;
import android.text.TextUtils;
import com.tencent.component.db.entity.TableEntity;
import com.tencent.component.utils.IOUtils;
import com.tencent.component.utils.log.LogUtil;

public class DefaultUpdateListener
  implements EntityManager.UpdateListener
{
  private static final String a = "DefaultUpdateListener";

  private static void a(ISQLiteDatabase paramISQLiteDatabase)
  {
    Cursor localCursor = null;
    if (paramISQLiteDatabase != null)
    {
      try
      {
        localCursor = paramISQLiteDatabase.rawQuery("SELECT name FROM sqlite_master WHERE type ='table'", null);
        if (localCursor != null)
          while (true)
          {
            boolean bool = localCursor.moveToNext();
            if (!bool)
              break;
            try
            {
              String str = localCursor.getString(0);
              paramISQLiteDatabase.execSQL("DROP TABLE IF EXISTS " + str);
              TableEntity.a(str);
            }
            catch (Throwable localThrowable)
            {
              LogUtil.e("DefaultUpdateListener", localThrowable.getMessage(), localThrowable);
            }
          }
      }
      finally
      {
        IOUtils.a(localCursor);
      }
      IOUtils.a(localCursor);
    }
  }

  private static void a(ISQLiteDatabase paramISQLiteDatabase, String paramString)
  {
    if ((paramISQLiteDatabase != null) && (!TextUtils.isEmpty(paramString)))
      paramISQLiteDatabase.execSQL("DROP TABLE IF EXISTS " + paramString);
  }

  public void onDatabaseDowngrade(ISQLiteDatabase paramISQLiteDatabase, int paramInt1, int paramInt2)
  {
    LogUtil.i("DefaultUpdateListener", "onDatabaseDowngrade(" + paramInt1 + " --> " + paramInt2 + ")");
    a(paramISQLiteDatabase);
  }

  public void onDatabaseUpgrade(ISQLiteDatabase paramISQLiteDatabase, int paramInt1, int paramInt2)
  {
    LogUtil.i("DefaultUpdateListener", "onDatabaseUpgrade(" + paramInt1 + " --> " + paramInt2 + ")");
    a(paramISQLiteDatabase);
  }

  public void onTableDowngrade(ISQLiteDatabase paramISQLiteDatabase, String paramString, int paramInt1, int paramInt2)
  {
    LogUtil.i("DefaultUpdateListener", "onTableDowngrade(" + paramInt1 + " --> " + paramInt2 + ",tableName:" + paramString + ")");
    a(paramISQLiteDatabase, paramString);
  }

  public void onTableUpgrade(ISQLiteDatabase paramISQLiteDatabase, String paramString, int paramInt1, int paramInt2)
  {
    LogUtil.i("DefaultUpdateListener", "onTableUpgrade(" + paramInt1 + " --> " + paramInt2 + ",tableName:" + paramString + ")");
    a(paramISQLiteDatabase, paramString);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.db.DefaultUpdateListener
 * JD-Core Version:    0.6.0
 */