package com.tencent.msdk.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.tencent.msdk.Singleton;
import com.tencent.msdk.WeGame;
import com.tencent.msdk.api.LoginRet;
import com.tencent.msdk.api.TokenRet;
import com.tencent.msdk.tools.CommonUtil;
import com.tencent.msdk.tools.Logger;
import java.util.ArrayList;
import java.util.Vector;

public class WxLoginModel extends BaseUserInfo
  implements ITbl
{
  static final String TBL_NAME = "wx_login_info";
  private static String col_access_token;
  private static String col_access_token_expire;
  private static String col_age;
  private static String col_avatar;
  private static String col_create_at;
  private static String col_gender;
  private static String col_is_active;
  private static String col_nickname;
  private static String col_open_id = "open_id";
  private static String col_pf;
  private static String col_pf_key;
  private static String col_refresh_token;
  private static String col_refresh_token_expire;
  private static String col_update_at;
  private static String col_wechat_uin;
  public static volatile WxLoginModel instance;
  private DbManager helper = (DbManager)DbManager.gDefault.get();
  public String refresh_token = "";
  public long refresh_token_expire = 0L;
  public String wechat_uin = "";

  static
  {
    col_access_token_expire = "access_token_expire";
    col_access_token = "access_token";
    col_refresh_token = "refresh_token";
    col_refresh_token_expire = "refresh_token_expire";
    col_pf = "pf";
    col_pf_key = "pf_key";
    col_wechat_uin = "wechat_uin";
    col_nickname = "nickname";
    col_age = "age";
    col_avatar = "avatar";
    col_gender = "gender";
    col_is_active = "is_active";
    col_create_at = "create_at";
    col_update_at = "update_at";
  }

  public WxLoginModel()
  {
  }

  public WxLoginModel(String paramString)
  {
    super(paramString);
  }

  public static String getCreateTblSql()
  {
    String str1 = "" + "CREATE TABLE IF NOT EXISTS [wx_login_info] (";
    String str2 = str1 + "[" + col_open_id + "] NVARCHAR(128)  UNIQUE NOT NULL,";
    String str3 = str2 + "[" + col_access_token_expire + "] REAL  NULL,";
    String str4 = str3 + "[" + col_access_token + "] VARCHAR(256)  NULL,";
    String str5 = str4 + "[" + col_refresh_token + "] VARCHAR(256)  NULL,";
    String str6 = str5 + "[" + col_refresh_token_expire + "] REAL  NULL,";
    String str7 = str6 + "[" + col_pf + "] NVARCHAR(64)  NULL,";
    String str8 = str7 + "[" + col_pf_key + "] NVARCHAR(128)  NULL,";
    String str9 = str8 + "[" + col_wechat_uin + "] NVARCHAR(64)  NULL,";
    String str10 = str9 + "[" + col_nickname + "] NVARCHAR(64)  NULL,";
    String str11 = str10 + "[" + col_age + "] INTEGER  NULL,";
    String str12 = str11 + "[" + col_avatar + "] VARCHAR(256)  NULL,";
    String str13 = str12 + "[" + col_gender + "] INTEGER DEFAULT -1 NULL,";
    String str14 = str13 + "[" + col_is_active + "] BOOLEAN  NULL,";
    String str15 = str14 + "[" + col_create_at + "] TIMESTAMP  NULL,";
    String str16 = str15 + "[" + col_update_at + "] TIMESTAMP DEFAULT CURRENT_TIMESTAMP NULL";
    return str16 + ")";
  }

  public static String getDropTblSql()
  {
    return "DROP TABLE IF EXISTS wx_login_info";
  }

  private ContentValues getUsableContentValues()
  {
    ContentValues localContentValues = new ContentValues();
    localContentValues.put(col_open_id, this.open_id);
    if (!CommonUtil.ckIsEmpty(this.access_token))
    {
      localContentValues.put(col_access_token, this.access_token);
      localContentValues.put(col_access_token_expire, Long.valueOf(this.access_token_expire));
    }
    if (!CommonUtil.ckIsEmpty(this.refresh_token))
    {
      localContentValues.put(col_refresh_token, this.refresh_token);
      localContentValues.put(col_refresh_token_expire, Long.valueOf(this.refresh_token_expire));
    }
    if (!CommonUtil.ckIsEmpty(this.pf))
      localContentValues.put(col_pf, this.pf);
    if (!CommonUtil.ckIsEmpty(this.pf_key))
      localContentValues.put(col_pf_key, this.pf_key);
    long l = System.currentTimeMillis();
    localContentValues.put(col_create_at, Long.valueOf(l));
    return localContentValues;
  }

  public LoginRet convertToLoginRet()
  {
    LoginRet localLoginRet = new LoginRet();
    localLoginRet.open_id = this.open_id;
    localLoginRet.pf = this.pf;
    localLoginRet.pf_key = this.pf_key;
    localLoginRet.platform = WeGame.WXPLATID;
    localLoginRet.token.add(new TokenRet(3, this.access_token, this.access_token_expire));
    localLoginRet.token.add(new TokenRet(5, this.refresh_token, this.refresh_token_expire));
    return localLoginRet;
  }

  public boolean create()
  {
    SQLiteDatabase localSQLiteDatabase;
    synchronized (this.helper)
    {
      localSQLiteDatabase = this.helper.getWritableDatabase();
    }
    try
    {
      long l = localSQLiteDatabase.insert("wx_login_info", null, getUsableContentValues());
      Logger.d("" + l);
      this.helper.close();
      monitorexit;
      return true;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
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

  public int delete()
  {
    SQLiteDatabase localSQLiteDatabase;
    String str;
    String[] arrayOfString;
    synchronized (this.helper)
    {
      localSQLiteDatabase = this.helper.getWritableDatabase();
      str = " `" + col_open_id + "` = ? ";
      arrayOfString = new String[1];
      arrayOfString[0] = this.open_id;
    }
    try
    {
      int i = localSQLiteDatabase.delete("wx_login_info", str, arrayOfString);
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
    SQLiteDatabase localSQLiteDatabase;
    synchronized (this.helper)
    {
      localSQLiteDatabase = this.helper.getWritableDatabase();
    }
    try
    {
      int i = localSQLiteDatabase.delete("wx_login_info", null, null);
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

  public BaseUserInfo find()
  {
    return null;
  }

  public ArrayList<BaseUserInfo> findAll()
  {
    return null;
  }

  public WxLoginModel getLastLoginUserInfo()
  {
    synchronized (this.helper)
    {
      WxLoginModel localWxLoginModel = new WxLoginModel();
      String str = " `" + col_create_at + "` DESC ";
      Cursor localCursor = this.helper.getReadableDatabase().query("wx_login_info", null, null, null, null, null, str, "1");
      if (localCursor.getCount() == 0)
      {
        localCursor.close();
        this.helper.close();
        return null;
      }
      localCursor.moveToFirst();
      localWxLoginModel.open_id = getStringByName(localCursor, col_open_id);
      localWxLoginModel.access_token = getStringByName(localCursor, col_access_token);
      localWxLoginModel.access_token_expire = getLongByName(localCursor, col_access_token_expire);
      localWxLoginModel.refresh_token = getStringByName(localCursor, col_refresh_token);
      localWxLoginModel.refresh_token_expire = getLongByName(localCursor, col_refresh_token_expire);
      localWxLoginModel.pf = getStringByName(localCursor, col_pf);
      localWxLoginModel.pf_key = getStringByName(localCursor, col_pf_key);
      localWxLoginModel.create_at = getLongByName(localCursor, col_create_at);
      localCursor.close();
      this.helper.close();
      return localWxLoginModel;
    }
  }

  public String getTableName()
  {
    return "wx_login_info";
  }

  public boolean isExisted()
  {
    synchronized (this.helper)
    {
      SQLiteDatabase localSQLiteDatabase = this.helper.getReadableDatabase();
      String str = " " + col_open_id + " = ? ";
      String[] arrayOfString = new String[1];
      arrayOfString[0] = this.open_id;
      try
      {
        Cursor localCursor = localSQLiteDatabase.query("wx_login_info", null, str, arrayOfString, null, null, null, null);
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
    deleteAll();
    if (isExisted())
      return update() > 0;
    return create();
  }

  // ERROR //
  public int update()
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 117	com/tencent/msdk/db/WxLoginModel:helper	Lcom/tencent/msdk/db/DbManager;
    //   4: astore_1
    //   5: aload_1
    //   6: monitorenter
    //   7: aload_0
    //   8: getfield 117	com/tencent/msdk/db/WxLoginModel:helper	Lcom/tencent/msdk/db/DbManager;
    //   11: invokevirtual 241	com/tencent/msdk/db/DbManager:getWritableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   14: astore_3
    //   15: aload_0
    //   16: invokespecial 243	com/tencent/msdk/db/WxLoginModel:getUsableContentValues	()Landroid/content/ContentValues;
    //   19: astore 4
    //   21: new 124	java/lang/StringBuilder
    //   24: dup
    //   25: invokespecial 125	java/lang/StringBuilder:<init>	()V
    //   28: ldc_w 267
    //   31: invokevirtual 129	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   34: getstatic 39	com/tencent/msdk/db/WxLoginModel:col_open_id	Ljava/lang/String;
    //   37: invokevirtual 129	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   40: ldc_w 269
    //   43: invokevirtual 129	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   46: invokevirtual 134	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   49: astore 7
    //   51: iconst_1
    //   52: anewarray 271	java/lang/String
    //   55: astore 8
    //   57: aload 8
    //   59: iconst_0
    //   60: aload_0
    //   61: getfield 168	com/tencent/msdk/db/WxLoginModel:open_id	Ljava/lang/String;
    //   64: aastore
    //   65: aload_3
    //   66: ldc 10
    //   68: aload 4
    //   70: aload 7
    //   72: aload 8
    //   74: invokevirtual 331	android/database/sqlite/SQLiteDatabase:update	(Ljava/lang/String;Landroid/content/ContentValues;Ljava/lang/String;[Ljava/lang/String;)I
    //   77: istore 9
    //   79: aload_0
    //   80: getfield 117	com/tencent/msdk/db/WxLoginModel:helper	Lcom/tencent/msdk/db/DbManager;
    //   83: invokevirtual 260	com/tencent/msdk/db/DbManager:close	()V
    //   86: aload_1
    //   87: monitorexit
    //   88: iload 9
    //   90: ireturn
    //   91: astore 6
    //   93: aload_0
    //   94: getfield 117	com/tencent/msdk/db/WxLoginModel:helper	Lcom/tencent/msdk/db/DbManager;
    //   97: invokevirtual 260	com/tencent/msdk/db/DbManager:close	()V
    //   100: aload_1
    //   101: monitorexit
    //   102: iconst_0
    //   103: ireturn
    //   104: astore_2
    //   105: aload_1
    //   106: monitorexit
    //   107: aload_2
    //   108: athrow
    //   109: astore 5
    //   111: aload_0
    //   112: getfield 117	com/tencent/msdk/db/WxLoginModel:helper	Lcom/tencent/msdk/db/DbManager;
    //   115: invokevirtual 260	com/tencent/msdk/db/DbManager:close	()V
    //   118: aload 5
    //   120: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   21	79	91	java/lang/Exception
    //   7	21	104	finally
    //   79	88	104	finally
    //   93	102	104	finally
    //   105	107	104	finally
    //   111	121	104	finally
    //   21	79	109	finally
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.msdk.db.WxLoginModel
 * JD-Core Version:    0.6.0
 */