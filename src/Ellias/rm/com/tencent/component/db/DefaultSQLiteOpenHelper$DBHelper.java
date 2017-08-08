package com.tencent.component.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import com.tencent.component.db.entity.TableEntity;
import com.tencent.component.db.exception.DbCacheExceptionHandler;
import com.tencent.component.utils.IOUtils;
import com.tencent.component.utils.log.LogUtil;

public class DefaultSQLiteOpenHelper$DBHelper extends SQLiteOpenHelper
{
  private final String a;
  private final Context b;
  private EntityManager.UpdateListener c;

  public DefaultSQLiteOpenHelper$DBHelper(Context paramContext, String paramString, SQLiteDatabase.CursorFactory paramCursorFactory, int paramInt, EntityManager.UpdateListener paramUpdateListener)
  {
    super(paramContext, paramString, paramCursorFactory, paramInt);
    this.a = paramString;
    this.b = paramContext;
    this.c = paramUpdateListener;
  }

  private static void a(SQLiteDatabase paramSQLiteDatabase)
  {
    Cursor localCursor = null;
    if (paramSQLiteDatabase != null)
    {
      try
      {
        localCursor = paramSQLiteDatabase.rawQuery("SELECT name FROM sqlite_master WHERE type ='table'", null);
        if (localCursor != null)
          while (true)
          {
            boolean bool = localCursor.moveToNext();
            if (!bool)
              break;
            try
            {
              String str = localCursor.getString(0);
              paramSQLiteDatabase.execSQL("DROP TABLE IF EXISTS " + str);
              TableEntity.a(str);
            }
            catch (Throwable localThrowable)
            {
              LogUtil.e("DBHelper", localThrowable.getMessage(), localThrowable);
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

  private static void a(Throwable paramThrowable)
  {
    try
    {
      DbCacheExceptionHandler.a().a(paramThrowable);
      return;
    }
    catch (Throwable localThrowable)
    {
    }
  }

  public void a()
  {
    this.b.deleteDatabase(this.a);
  }

  public SQLiteDatabase getWritableDatabase()
  {
    monitorenter;
    try
    {
      SQLiteDatabase localSQLiteDatabase2 = super.getWritableDatabase();
      localObject2 = localSQLiteDatabase2;
      return localObject2;
    }
    catch (Throwable localThrowable1)
    {
      while (true)
      {
        Object localObject2;
        a();
        try
        {
          SQLiteDatabase localSQLiteDatabase1 = super.getWritableDatabase();
          localObject2 = localSQLiteDatabase1;
        }
        catch (Throwable localThrowable2)
        {
          a(localThrowable2);
          localObject2 = null;
        }
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject1;
  }

  public void onCreate(SQLiteDatabase paramSQLiteDatabase)
  {
  }

  public void onDowngrade(SQLiteDatabase paramSQLiteDatabase, int paramInt1, int paramInt2)
  {
    if (this.c != null)
    {
      this.c.onDatabaseDowngrade(new DefaultSQLiteDatabase(paramSQLiteDatabase), paramInt1, paramInt2);
      return;
    }
    a(paramSQLiteDatabase);
  }

  public void onUpgrade(SQLiteDatabase paramSQLiteDatabase, int paramInt1, int paramInt2)
  {
    if (this.c != null)
    {
      this.c.onDatabaseUpgrade(new DefaultSQLiteDatabase(paramSQLiteDatabase), paramInt1, paramInt2);
      return;
    }
    a(paramSQLiteDatabase);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.db.DefaultSQLiteOpenHelper.DBHelper
 * JD-Core Version:    0.6.0
 */