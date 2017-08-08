package com.tencent.component.db;

import android.content.Context;
import android.content.ContextWrapper;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import com.tencent.component.cache.CacheManager;
import java.io.File;
import java.io.IOException;

public class SdcardSQLiteOpenHelper$SdcardDatabaseContext extends ContextWrapper
{
  private String a;

  public SdcardSQLiteOpenHelper$SdcardDatabaseContext(Context paramContext, String paramString)
  {
    super(paramContext);
    this.a = paramString;
  }

  public File getDatabasePath(String paramString)
  {
    if (!"mounted".equals(Environment.getExternalStorageState()))
    {
      Log.e("SD卡管理：", "SD卡不存在，请加载SD卡");
      localFile2 = null;
      return localFile2;
    }
    String str1 = this.a;
    if (TextUtils.isEmpty(str1))
      str1 = CacheManager.b(getBaseContext(), "databases");
    if (TextUtils.isEmpty(str1))
      str1 = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "Tecent" + File.separator + getBaseContext().getPackageName() + File.separator + "databases";
    String str2 = str1 + "/" + paramString;
    File localFile1 = new File(str1);
    if (!localFile1.exists())
      localFile1.mkdirs();
    File localFile2 = new File(str2);
    if (!localFile2.exists());
    while (true)
    {
      try
      {
        boolean bool2 = localFile2.createNewFile();
        bool1 = bool2;
        if (bool1)
          break;
        return null;
      }
      catch (IOException localIOException)
      {
        localIOException.printStackTrace();
        bool1 = false;
        continue;
      }
      boolean bool1 = true;
    }
  }

  public SQLiteDatabase openOrCreateDatabase(String paramString, int paramInt, SQLiteDatabase.CursorFactory paramCursorFactory)
  {
    return SQLiteDatabase.openOrCreateDatabase(getDatabasePath(paramString), null);
  }

  public SQLiteDatabase openOrCreateDatabase(String paramString, int paramInt, SQLiteDatabase.CursorFactory paramCursorFactory, DatabaseErrorHandler paramDatabaseErrorHandler)
  {
    return SQLiteDatabase.openOrCreateDatabase(getDatabasePath(paramString), null);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.db.SdcardSQLiteOpenHelper.SdcardDatabaseContext
 * JD-Core Version:    0.6.0
 */