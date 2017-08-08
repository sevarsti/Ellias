package com.tencent.msdk.push.db;

import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import com.tencent.msdk.Singleton;
import com.tencent.msdk.push.PushConst;
import com.tencent.msdk.tools.Logger;
import java.io.File;

public class PushDBHelper
{
  private static final String DB_NAME = "msdk_push.db";
  private static final int DB_VERSION = 12;
  public static final Singleton<PushDBHelper> gDefault;
  private static String sDatabasePath = PushConst.PUSH_ROOT_DIR;
  private SQLiteDatabase mDatabase = null;

  static
  {
    gDefault = new Singleton()
    {
      protected PushDBHelper create()
      {
        return new PushDBHelper(null);
      }
    };
  }

  private PushDBHelper()
  {
    Logger.d("pushdb:" + sDatabasePath);
    createPath();
  }

  private static void createPath()
  {
    try
    {
      if ("mounted".equals(Environment.getExternalStorageState()))
      {
        File localFile = new File(sDatabasePath);
        if (!localFile.exists())
          localFile.mkdirs();
      }
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }

  private SQLiteDatabase getDatabaseLocked(boolean paramBoolean)
  {
    String str = sDatabasePath + "/" + "msdk_push.db";
    if (this.mDatabase == null)
    {
      try
      {
        this.mDatabase = SQLiteDatabase.openOrCreateDatabase(str, null);
        if (this.mDatabase != null)
          break label122;
        Logger.d("getDatabaseLocked failure!!!");
        return null;
      }
      catch (Exception localException)
      {
        while (true)
          localException.printStackTrace();
      }
    }
    else
    {
      if ((!paramBoolean) || (!this.mDatabase.isReadOnly()))
        return this.mDatabase;
      if ((paramBoolean) && (this.mDatabase.isReadOnly()))
      {
        this.mDatabase.close();
        this.mDatabase = null;
        this.mDatabase = SQLiteDatabase.openDatabase(str, null, 0);
      }
    }
    label122: int i = this.mDatabase.getVersion();
    this.mDatabase.beginTransaction();
    if (i == 0);
    while (true)
    {
      try
      {
        onCreate(this.mDatabase);
        this.mDatabase.setVersion(12);
        this.mDatabase.setTransactionSuccessful();
        return this.mDatabase;
        if (i > 12)
        {
          onDowngrade(this.mDatabase, i, 12);
          continue;
        }
      }
      finally
      {
        this.mDatabase.endTransaction();
        onOpen(this.mDatabase);
      }
      if (i >= 12)
        continue;
      onUpgrade(this.mDatabase, i, 12);
    }
  }

  private void onCreate(SQLiteDatabase paramSQLiteDatabase)
  {
    try
    {
      paramSQLiteDatabase.execSQL(PushClientDbModel.getCreateTblSql());
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }

  private void onDowngrade(SQLiteDatabase paramSQLiteDatabase, int paramInt1, int paramInt2)
  {
    onUpgrade(paramSQLiteDatabase, paramInt1, paramInt2);
  }

  private void onOpen(SQLiteDatabase paramSQLiteDatabase)
  {
  }

  private void onUpgrade(SQLiteDatabase paramSQLiteDatabase, int paramInt1, int paramInt2)
  {
    try
    {
      paramSQLiteDatabase.execSQL(PushClientDbModel.getDropTblSql());
      paramSQLiteDatabase.execSQL(PushClientDbModel.getCreateTblSql());
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }

  protected void close()
  {
  }

  public SQLiteDatabase getReadableDatabase()
  {
    monitorenter;
    try
    {
      SQLiteDatabase localSQLiteDatabase = getDatabaseLocked(false);
      return localSQLiteDatabase;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public SQLiteDatabase getWritableDatabase()
  {
    monitorenter;
    try
    {
      SQLiteDatabase localSQLiteDatabase = getDatabaseLocked(true);
      return localSQLiteDatabase;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.msdk.push.db.PushDBHelper
 * JD-Core Version:    0.6.0
 */