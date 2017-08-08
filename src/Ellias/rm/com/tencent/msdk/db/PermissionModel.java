package com.tencent.msdk.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.tencent.msdk.Singleton;
import com.tencent.msdk.tools.CommonUtil;
import java.util.ArrayList;

public class PermissionModel
  implements ITbl
{
  public static final String TBL_NAME = "msdk_permission";
  private static final String col_permission = "PERMISSIONSTR";
  private static final String col_qq_appid = "QQ_APPID";
  private static final String col_wx_appid = "WX_APPID";
  private DbManager helper = (DbManager)DbManager.gDefault.get();
  public String permission = "";
  public String qqAppId = "";
  public String wxAppId = "";

  public static String getCreateTblSql()
  {
    return "CREATE TABLE IF NOT EXISTS msdk_permission ( QQ_APPID STRING UNIQUE NOT NULL, WX_APPID STRING, PERMISSIONSTR STRING)";
  }

  public static String getDropTblSql()
  {
    return "DROP TABLE IF EXISTS msdk_permission";
  }

  private ContentValues getUsableContentValues()
  {
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("QQ_APPID", this.qqAppId);
    if (!CommonUtil.ckIsEmpty(this.wxAppId))
      localContentValues.put("WX_APPID", this.wxAppId);
    if (!CommonUtil.ckIsEmpty(this.permission))
      localContentValues.put("PERMISSIONSTR", this.permission);
    return localContentValues;
  }

  public boolean create()
  {
    DbManager localDbManager = this.helper;
    monitorenter;
    try
    {
      this.helper.getWritableDatabase().insert("msdk_permission", null, getUsableContentValues());
    }
    catch (Exception localException)
    {
    }
    finally
    {
      this.helper.close();
    }
    throw localObject1;
  }

  public int delete()
  {
    String[] arrayOfString;
    synchronized (this.helper)
    {
      arrayOfString = new String[1];
      arrayOfString[0] = this.qqAppId;
    }
    try
    {
      int i = this.helper.getWritableDatabase().delete("msdk_permission", " `QQ_APPID` = ? ", arrayOfString);
      this.helper.close();
      monitorexit;
      return i;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
      this.helper.close();
      monitorexit;
      return 0;
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

  public int deleteAll()
  {
    return 0;
  }

  public BaseUserInfo find()
  {
    return null;
  }

  public ArrayList<BaseUserInfo> findAll()
  {
    return null;
  }

  public boolean firstTimeSave()
  {
    if (!isExisted())
      return create();
    return true;
  }

  public void getRecord()
  {
    DbManager localDbManager = this.helper;
    monitorenter;
    Cursor localCursor = null;
    try
    {
      localCursor = this.helper.getReadableDatabase().query("msdk_permission", null, null, null, null, null, null);
      if (localCursor.getCount() > 0)
      {
        localCursor.moveToFirst();
        this.qqAppId = localCursor.getString(localCursor.getColumnIndex("QQ_APPID"));
        this.wxAppId = localCursor.getString(localCursor.getColumnIndex("WX_APPID"));
        this.permission = localCursor.getString(localCursor.getColumnIndex("PERMISSIONSTR"));
      }
      if (localCursor == null);
    }
    catch (Exception localException)
    {
    }
    finally
    {
      if (localCursor != null)
        localCursor.close();
      this.helper.close();
    }
    throw localObject1;
  }

  public String getTableName()
  {
    return "msdk_permission";
  }

  public boolean isExisted()
  {
    synchronized (this.helper)
    {
      String[] arrayOfString = new String[1];
      arrayOfString[0] = this.qqAppId;
      try
      {
        Cursor localCursor = this.helper.getReadableDatabase().query("msdk_permission", null, " QQ_APPID = ? ", arrayOfString, null, null, null, null);
        if (localCursor.getCount() > 0)
        {
          localCursor.close();
          this.helper.close();
          return true;
        }
        localCursor.close();
        this.helper.close();
        return false;
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
        this.helper.close();
        return true;
      }
      finally
      {
        this.helper.close();
      }
    }
  }

  public boolean save()
  {
    if (isExisted())
      return update() > 0;
    return create();
  }

  // ERROR //
  public int update()
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 50	com/tencent/msdk/db/PermissionModel:helper	Lcom/tencent/msdk/db/DbManager;
    //   4: astore_1
    //   5: aload_1
    //   6: monitorenter
    //   7: aload_0
    //   8: invokespecial 82	com/tencent/msdk/db/PermissionModel:getUsableContentValues	()Landroid/content/ContentValues;
    //   11: astore_3
    //   12: iconst_1
    //   13: anewarray 106	java/lang/String
    //   16: astore 6
    //   18: aload 6
    //   20: iconst_0
    //   21: aload_0
    //   22: getfield 32	com/tencent/msdk/db/PermissionModel:qqAppId	Ljava/lang/String;
    //   25: aastore
    //   26: aload_0
    //   27: getfield 50	com/tencent/msdk/db/PermissionModel:helper	Lcom/tencent/msdk/db/DbManager;
    //   30: invokevirtual 80	com/tencent/msdk/db/DbManager:getWritableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   33: ldc 10
    //   35: aload_3
    //   36: ldc 108
    //   38: aload 6
    //   40: invokevirtual 162	android/database/sqlite/SQLiteDatabase:update	(Ljava/lang/String;Landroid/content/ContentValues;Ljava/lang/String;[Ljava/lang/String;)I
    //   43: istore 7
    //   45: aload_0
    //   46: getfield 50	com/tencent/msdk/db/PermissionModel:helper	Lcom/tencent/msdk/db/DbManager;
    //   49: invokevirtual 91	com/tencent/msdk/db/DbManager:close	()V
    //   52: aload_1
    //   53: monitorexit
    //   54: iload 7
    //   56: ireturn
    //   57: astore 5
    //   59: aload_0
    //   60: getfield 50	com/tencent/msdk/db/PermissionModel:helper	Lcom/tencent/msdk/db/DbManager;
    //   63: invokevirtual 91	com/tencent/msdk/db/DbManager:close	()V
    //   66: aload_1
    //   67: monitorexit
    //   68: iconst_0
    //   69: ireturn
    //   70: astore_2
    //   71: aload_1
    //   72: monitorexit
    //   73: aload_2
    //   74: athrow
    //   75: astore 4
    //   77: aload_0
    //   78: getfield 50	com/tencent/msdk/db/PermissionModel:helper	Lcom/tencent/msdk/db/DbManager;
    //   81: invokevirtual 91	com/tencent/msdk/db/DbManager:close	()V
    //   84: aload 4
    //   86: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   12	45	57	java/lang/Exception
    //   7	12	70	finally
    //   45	54	70	finally
    //   59	68	70	finally
    //   71	73	70	finally
    //   77	87	70	finally
    //   12	45	75	finally
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.msdk.db.PermissionModel
 * JD-Core Version:    0.6.0
 */