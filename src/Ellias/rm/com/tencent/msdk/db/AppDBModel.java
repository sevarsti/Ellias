package com.tencent.msdk.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.tencent.msdk.Singleton;
import com.tencent.msdk.notice.AppInfo;
import com.tencent.msdk.tools.Logger;
import com.tencent.msdk.tools.T;

public class AppDBModel extends BaseDBModel
{
  public static final String TBL_NAME = "app_info";
  private static String col_app_id = "appid";
  private static String col_mat_id;
  private static String col_msdk_v;
  private static String col_open_id = "openid";
  private static String col_packageName;
  private static String col_updatetime;
  private DbManager helper = (DbManager)DbManager.gDefault.get();

  static
  {
    col_mat_id = "matid";
    col_msdk_v = "msdkVersion";
    col_packageName = "packageName";
    col_updatetime = "updateTime";
  }

  public static String getCreateTblSql()
  {
    String str1 = "" + "CREATE TABLE IF NOT EXISTS [app_info] (";
    String str2 = str1 + "[" + col_app_id + "] NVARCHAR(128)  PRIMARY KEY NOT NULL,";
    String str3 = str2 + "[" + col_open_id + "] VARCHAR(256)  NULL,";
    String str4 = str3 + "[" + col_mat_id + "] VARCHAR(256)   NULL,";
    String str5 = str4 + "[" + col_msdk_v + "] VARCHAR(64)  NULL,";
    String str6 = str5 + "[" + col_packageName + "] VARCHAR(256)  NULL,";
    String str7 = str6 + "[" + col_updatetime + "] TIMESTAMP  NULL";
    String str8 = str7 + ")";
    Logger.d(str8);
    return str8;
  }

  public static String getDropTblSql()
  {
    return "DROP TABLE IF EXISTS app_info";
  }

  private ContentValues getUsableContentValues(AppInfo paramAppInfo)
  {
    ContentValues localContentValues = new ContentValues();
    putValues(localContentValues, col_app_id, paramAppInfo.appid);
    putValues(localContentValues, col_open_id, paramAppInfo.openid);
    putValues(localContentValues, col_mat_id, paramAppInfo.matid);
    putValues(localContentValues, col_msdk_v, paramAppInfo.msdkVersion);
    putValues(localContentValues, col_packageName, paramAppInfo.packageName);
    return localContentValues;
  }

  // ERROR //
  public String getMatId(String paramString)
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 59	com/tencent/msdk/db/AppDBModel:helper	Lcom/tencent/msdk/db/DbManager;
    //   4: astore_2
    //   5: aload_2
    //   6: monitorenter
    //   7: ldc 66
    //   9: astore_3
    //   10: new 63	java/lang/StringBuilder
    //   13: dup
    //   14: invokespecial 64	java/lang/StringBuilder:<init>	()V
    //   17: ldc 125
    //   19: invokevirtual 70	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   22: getstatic 22	com/tencent/msdk/db/AppDBModel:col_app_id	Ljava/lang/String;
    //   25: invokevirtual 70	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   28: ldc 127
    //   30: invokevirtual 70	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   33: invokevirtual 75	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   36: astore 5
    //   38: iconst_1
    //   39: anewarray 129	java/lang/String
    //   42: dup
    //   43: iconst_0
    //   44: aload_1
    //   45: aastore
    //   46: astore 6
    //   48: aload_0
    //   49: getfield 59	com/tencent/msdk/db/AppDBModel:helper	Lcom/tencent/msdk/db/DbManager;
    //   52: invokevirtual 133	com/tencent/msdk/db/DbManager:getReadableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   55: ldc 8
    //   57: aconst_null
    //   58: aload 5
    //   60: aload 6
    //   62: aconst_null
    //   63: aconst_null
    //   64: aconst_null
    //   65: aconst_null
    //   66: invokevirtual 139	android/database/sqlite/SQLiteDatabase:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   69: astore 9
    //   71: aload 9
    //   73: invokeinterface 145 1 0
    //   78: ifle +21 -> 99
    //   81: aload 9
    //   83: invokeinterface 149 1 0
    //   88: pop
    //   89: aload_0
    //   90: aload 9
    //   92: getstatic 30	com/tencent/msdk/db/AppDBModel:col_mat_id	Ljava/lang/String;
    //   95: invokevirtual 153	com/tencent/msdk/db/AppDBModel:getStringByName	(Landroid/database/Cursor;Ljava/lang/String;)Ljava/lang/String;
    //   98: astore_3
    //   99: aload 9
    //   101: invokeinterface 156 1 0
    //   106: aload_0
    //   107: getfield 59	com/tencent/msdk/db/AppDBModel:helper	Lcom/tencent/msdk/db/DbManager;
    //   110: invokevirtual 157	com/tencent/msdk/db/DbManager:close	()V
    //   113: aload_2
    //   114: monitorexit
    //   115: aload_3
    //   116: areturn
    //   117: astore 8
    //   119: aload 8
    //   121: invokevirtual 160	java/lang/Exception:printStackTrace	()V
    //   124: aload_0
    //   125: getfield 59	com/tencent/msdk/db/AppDBModel:helper	Lcom/tencent/msdk/db/DbManager;
    //   128: invokevirtual 157	com/tencent/msdk/db/DbManager:close	()V
    //   131: goto -18 -> 113
    //   134: astore 4
    //   136: aload_2
    //   137: monitorexit
    //   138: aload 4
    //   140: athrow
    //   141: astore 7
    //   143: aload_0
    //   144: getfield 59	com/tencent/msdk/db/AppDBModel:helper	Lcom/tencent/msdk/db/DbManager;
    //   147: invokevirtual 157	com/tencent/msdk/db/DbManager:close	()V
    //   150: aload 7
    //   152: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   48	99	117	java/lang/Exception
    //   99	106	117	java/lang/Exception
    //   10	48	134	finally
    //   106	113	134	finally
    //   113	115	134	finally
    //   124	131	134	finally
    //   136	138	134	finally
    //   143	153	134	finally
    //   48	99	141	finally
    //   99	106	141	finally
    //   119	124	141	finally
  }

  public String getTableName()
  {
    return "app_info";
  }

  public String getUpdateTimeByAppId(String paramString)
  {
    String str1 = "";
    String str2;
    String[] arrayOfString;
    if (!T.ckIsEmpty(paramString))
      synchronized (this.helper)
      {
        str2 = " " + col_app_id + " = ? ";
        arrayOfString = new String[] { paramString };
      }
    try
    {
      Cursor localCursor = this.helper.getReadableDatabase().query("app_info", null, str2, arrayOfString, null, null, null, null);
      if (localCursor != null)
      {
        localCursor.moveToFirst();
        str1 = getStringByName(localCursor, col_updatetime);
        localCursor.close();
      }
      this.helper.close();
      monitorexit;
      return str1;
    }
    catch (Exception localException)
    {
      while (true)
      {
        Logger.w("getUpdateTimeByAppId cause exception");
        localException.printStackTrace();
        this.helper.close();
      }
      localObject1 = finally;
      monitorexit;
      throw localObject1;
    }
    finally
    {
      this.helper.close();
    }
    throw localObject2;
  }

  public boolean insert(AppInfo paramAppInfo)
  {
    synchronized (this.helper)
    {
      Logger.d("insert AppInfo db");
    }
    try
    {
      this.helper.getWritableDatabase().insert("app_info", null, getUsableContentValues(paramAppInfo));
      this.helper.close();
      monitorexit;
      return true;
    }
    catch (Exception localException)
    {
      Logger.d("Insert into AppInfo error");
      this.helper.close();
      monitorexit;
      return false;
      localObject1 = finally;
      monitorexit;
      throw localObject1;
    }
    finally
    {
      this.helper.close();
    }
    throw localObject2;
  }

  public boolean isExisted(AppInfo paramAppInfo)
  {
    String str;
    String[] arrayOfString;
    synchronized (this.helper)
    {
      str = " " + col_app_id + " = ? ";
      arrayOfString = new String[1];
      arrayOfString[0] = paramAppInfo.appid;
    }
    try
    {
      Cursor localCursor = this.helper.getReadableDatabase().query("app_info", null, str, arrayOfString, null, null, null, null);
      if (localCursor.getCount() > 0)
      {
        localCursor.close();
        this.helper.close();
        monitorexit;
        return true;
      }
      localCursor.close();
      this.helper.close();
      monitorexit;
      return false;
      localObject1 = finally;
      monitorexit;
      throw localObject1;
    }
    catch (Exception localException)
    {
      this.helper.close();
      monitorexit;
      return false;
    }
    finally
    {
      this.helper.close();
    }
    throw localObject2;
  }

  public boolean save(AppInfo paramAppInfo)
  {
    if (isExisted(paramAppInfo))
      return update(paramAppInfo) > 0;
    return insert(paramAppInfo);
  }

  // ERROR //
  public void setUpdateTimeByAppId(String paramString1, String paramString2)
  {
    // Byte code:
    //   0: aload_1
    //   1: invokestatic 168	com/tencent/msdk/tools/T:ckIsEmpty	(Ljava/lang/String;)Z
    //   4: ifne +139 -> 143
    //   7: aload_2
    //   8: invokestatic 168	com/tencent/msdk/tools/T:ckIsEmpty	(Ljava/lang/String;)Z
    //   11: ifne +132 -> 143
    //   14: aload_0
    //   15: getfield 59	com/tencent/msdk/db/AppDBModel:helper	Lcom/tencent/msdk/db/DbManager;
    //   18: astore_3
    //   19: aload_3
    //   20: monitorenter
    //   21: ldc 201
    //   23: invokestatic 95	com/tencent/msdk/tools/Logger:d	(Ljava/lang/String;)V
    //   26: new 102	android/content/ContentValues
    //   29: dup
    //   30: invokespecial 103	android/content/ContentValues:<init>	()V
    //   33: astore 5
    //   35: aload_0
    //   36: aload 5
    //   38: getstatic 42	com/tencent/msdk/db/AppDBModel:col_updatetime	Ljava/lang/String;
    //   41: aload_2
    //   42: invokevirtual 111	com/tencent/msdk/db/AppDBModel:putValues	(Landroid/content/ContentValues;Ljava/lang/String;Ljava/lang/String;)V
    //   45: new 63	java/lang/StringBuilder
    //   48: dup
    //   49: invokespecial 64	java/lang/StringBuilder:<init>	()V
    //   52: ldc 203
    //   54: invokevirtual 70	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   57: getstatic 22	com/tencent/msdk/db/AppDBModel:col_app_id	Ljava/lang/String;
    //   60: invokevirtual 70	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   63: ldc 205
    //   65: invokevirtual 70	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   68: invokevirtual 75	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   71: astore 8
    //   73: iconst_1
    //   74: anewarray 129	java/lang/String
    //   77: dup
    //   78: iconst_0
    //   79: aload_1
    //   80: aastore
    //   81: astore 9
    //   83: aload_0
    //   84: getfield 59	com/tencent/msdk/db/AppDBModel:helper	Lcom/tencent/msdk/db/DbManager;
    //   87: invokevirtual 180	com/tencent/msdk/db/DbManager:getWritableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   90: ldc 8
    //   92: aload 5
    //   94: aload 8
    //   96: aload 9
    //   98: invokevirtual 208	android/database/sqlite/SQLiteDatabase:update	(Ljava/lang/String;Landroid/content/ContentValues;Ljava/lang/String;[Ljava/lang/String;)I
    //   101: pop
    //   102: aload_0
    //   103: getfield 59	com/tencent/msdk/db/AppDBModel:helper	Lcom/tencent/msdk/db/DbManager;
    //   106: invokevirtual 157	com/tencent/msdk/db/DbManager:close	()V
    //   109: aload_3
    //   110: monitorexit
    //   111: return
    //   112: astore 7
    //   114: aload_0
    //   115: getfield 59	com/tencent/msdk/db/AppDBModel:helper	Lcom/tencent/msdk/db/DbManager;
    //   118: invokevirtual 157	com/tencent/msdk/db/DbManager:close	()V
    //   121: aload_3
    //   122: monitorexit
    //   123: return
    //   124: astore 4
    //   126: aload_3
    //   127: monitorexit
    //   128: aload 4
    //   130: athrow
    //   131: astore 6
    //   133: aload_0
    //   134: getfield 59	com/tencent/msdk/db/AppDBModel:helper	Lcom/tencent/msdk/db/DbManager;
    //   137: invokevirtual 157	com/tencent/msdk/db/DbManager:close	()V
    //   140: aload 6
    //   142: athrow
    //   143: ldc 210
    //   145: invokestatic 213	com/tencent/msdk/tools/Logger:e	(Ljava/lang/String;)V
    //   148: return
    //
    // Exception table:
    //   from	to	target	type
    //   45	102	112	java/lang/Exception
    //   21	45	124	finally
    //   102	111	124	finally
    //   114	123	124	finally
    //   126	128	124	finally
    //   133	143	124	finally
    //   45	102	131	finally
  }

  // ERROR //
  public int update(AppInfo paramAppInfo)
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 59	com/tencent/msdk/db/AppDBModel:helper	Lcom/tencent/msdk/db/DbManager;
    //   4: astore_2
    //   5: aload_2
    //   6: monitorenter
    //   7: ldc 201
    //   9: invokestatic 95	com/tencent/msdk/tools/Logger:d	(Ljava/lang/String;)V
    //   12: aload_0
    //   13: aload_1
    //   14: invokespecial 182	com/tencent/msdk/db/AppDBModel:getUsableContentValues	(Lcom/tencent/msdk/notice/AppInfo;)Landroid/content/ContentValues;
    //   17: astore 4
    //   19: new 63	java/lang/StringBuilder
    //   22: dup
    //   23: invokespecial 64	java/lang/StringBuilder:<init>	()V
    //   26: ldc 203
    //   28: invokevirtual 70	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   31: getstatic 22	com/tencent/msdk/db/AppDBModel:col_app_id	Ljava/lang/String;
    //   34: invokevirtual 70	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   37: ldc 215
    //   39: invokevirtual 70	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   42: invokevirtual 75	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   45: astore 7
    //   47: iconst_1
    //   48: anewarray 129	java/lang/String
    //   51: astore 8
    //   53: aload 8
    //   55: iconst_0
    //   56: aload_1
    //   57: getfield 107	com/tencent/msdk/notice/AppInfo:appid	Ljava/lang/String;
    //   60: aastore
    //   61: aload_0
    //   62: getfield 59	com/tencent/msdk/db/AppDBModel:helper	Lcom/tencent/msdk/db/DbManager;
    //   65: invokevirtual 180	com/tencent/msdk/db/DbManager:getWritableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   68: ldc 8
    //   70: aload 4
    //   72: aload 7
    //   74: aload 8
    //   76: invokevirtual 208	android/database/sqlite/SQLiteDatabase:update	(Ljava/lang/String;Landroid/content/ContentValues;Ljava/lang/String;[Ljava/lang/String;)I
    //   79: istore 9
    //   81: aload_0
    //   82: getfield 59	com/tencent/msdk/db/AppDBModel:helper	Lcom/tencent/msdk/db/DbManager;
    //   85: invokevirtual 157	com/tencent/msdk/db/DbManager:close	()V
    //   88: aload_2
    //   89: monitorexit
    //   90: iload 9
    //   92: ireturn
    //   93: astore 6
    //   95: aload_0
    //   96: getfield 59	com/tencent/msdk/db/AppDBModel:helper	Lcom/tencent/msdk/db/DbManager;
    //   99: invokevirtual 157	com/tencent/msdk/db/DbManager:close	()V
    //   102: aload_2
    //   103: monitorexit
    //   104: iconst_0
    //   105: ireturn
    //   106: astore_3
    //   107: aload_2
    //   108: monitorexit
    //   109: aload_3
    //   110: athrow
    //   111: astore 5
    //   113: aload_0
    //   114: getfield 59	com/tencent/msdk/db/AppDBModel:helper	Lcom/tencent/msdk/db/DbManager;
    //   117: invokevirtual 157	com/tencent/msdk/db/DbManager:close	()V
    //   120: aload 5
    //   122: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   19	81	93	java/lang/Exception
    //   7	19	106	finally
    //   81	90	106	finally
    //   95	104	106	finally
    //   107	109	106	finally
    //   113	123	106	finally
    //   19	81	111	finally
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.msdk.db.AppDBModel
 * JD-Core Version:    0.6.0
 */