package com.tencent.msdk.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.tencent.msdk.Singleton;
import com.tencent.msdk.WeGame;
import com.tencent.msdk.api.LoginRet;
import com.tencent.msdk.api.TokenRet;
import com.tencent.msdk.tools.CommonUtil;
import java.util.ArrayList;
import java.util.Vector;

public class QQLoginModel extends BaseUserInfo
  implements ITbl
{
  public static final String TBL_NAME = "qq_login_info";
  private static String col_access_token;
  private static String col_access_token_expire;
  private static String col_age;
  private static String col_avatar;
  private static String col_create_at;
  private static String col_gender;
  private static String col_is_active;
  private static String col_nickname;
  private static String col_open_id = "open_id";
  private static String col_pay_token;
  private static String col_pay_token_expire;
  private static String col_pf;
  private static String col_pf_key;
  private static String col_qq;
  private static String col_update_at;
  private DbManager helper = (DbManager)DbManager.gDefault.get();
  public String pay_token = "";
  public long pay_token_expire = 0L;
  public String qq = "";

  static
  {
    col_access_token_expire = "access_token_expire";
    col_access_token = "access_token";
    col_pay_token = "pay_token";
    col_pay_token_expire = "pay_token_expire";
    col_qq = "qq";
    col_nickname = "nickname";
    col_age = "age";
    col_avatar = "avatar";
    col_gender = "gender";
    col_is_active = "is_active";
    col_create_at = "create_at";
    col_update_at = "update_at";
    col_pf = "pf";
    col_pf_key = "pf_key";
  }

  public QQLoginModel()
  {
  }

  public QQLoginModel(String paramString)
  {
    super(paramString);
  }

  public static String getCreateTblSql()
  {
    String str1 = "" + "CREATE TABLE IF NOT EXISTS [qq_login_info] (";
    String str2 = str1 + "[" + col_open_id + "] NVARCHAR(128)  UNIQUE NOT NULL,";
    String str3 = str2 + "[" + col_access_token_expire + "] REAL  NULL,";
    String str4 = str3 + "[" + col_access_token + "] VARCHAR(256)  NULL,";
    String str5 = str4 + "[" + col_pay_token + "] VARCHAR(256)  NULL,";
    String str6 = str5 + "[" + col_pay_token_expire + "] REAL  NULL,";
    String str7 = str6 + "[" + col_qq + "] REAL  NULL,";
    String str8 = str7 + "[" + col_nickname + "] NVARCHAR(64)  NULL,";
    String str9 = str8 + "[" + col_age + "] INTEGER  NULL,";
    String str10 = str9 + "[" + col_avatar + "] VARCHAR(256)  NULL,";
    String str11 = str10 + "[" + col_gender + "] INTEGER DEFAULT '''-1''' NULL,";
    String str12 = str11 + "[" + col_is_active + "] BOOLEAN  NULL,";
    String str13 = str12 + "[" + col_create_at + "] TIMESTAMP  NULL,";
    String str14 = str13 + "[" + col_update_at + "] TIMESTAMP DEFAULT CURRENT_TIMESTAMP NULL,";
    String str15 = str14 + "[" + col_pf + "] NVARCHAR(64)  NULL,";
    String str16 = str15 + "[" + col_pf_key + "] NVARCHAR(128)  NULL";
    return str16 + ")";
  }

  public static String getDropTblSql()
  {
    return "DROP TABLE IF EXISTS qq_login_info";
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
    if (!CommonUtil.ckIsEmpty(this.pay_token))
    {
      localContentValues.put(col_pay_token, this.pay_token);
      localContentValues.put(col_pay_token_expire, Long.valueOf(this.pay_token_expire));
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
    localLoginRet.platform = WeGame.QQPLATID;
    localLoginRet.token.add(new TokenRet(1, this.access_token, this.access_token_expire));
    localLoginRet.token.add(new TokenRet(2, this.pay_token, this.pay_token_expire));
    return localLoginRet;
  }

  public boolean create()
  {
    DbManager localDbManager = this.helper;
    monitorenter;
    try
    {
      this.helper.getWritableDatabase().insert("qq_login_info", null, getUsableContentValues());
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
    String str;
    String[] arrayOfString;
    synchronized (this.helper)
    {
      str = " `" + col_open_id + "` = ? ";
      arrayOfString = new String[1];
      arrayOfString[0] = this.open_id;
    }
    try
    {
      int i = this.helper.getWritableDatabase().delete("qq_login_info", str, arrayOfString);
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
    DbManager localDbManager = this.helper;
    monitorenter;
    try
    {
      i = this.helper.getWritableDatabase().delete("qq_login_info", null, null);
    }
    catch (Exception localException)
    {
    }
    finally
    {
      int i;
      this.helper.close();
    }
    throw localObject1;
  }

  public BaseUserInfo find()
  {
    return null;
  }

  public ArrayList<BaseUserInfo> findAll()
  {
    return null;
  }

  public QQLoginModel getLastLoginUserInfo()
  {
    synchronized (this.helper)
    {
      QQLoginModel localQQLoginModel = new QQLoginModel();
      String str = " `" + col_create_at + "` DESC ";
      Cursor localCursor = this.helper.getReadableDatabase().query("qq_login_info", null, null, null, null, null, str, " 1 ");
      if (localCursor.getCount() == 0)
      {
        localCursor.close();
        this.helper.close();
        return null;
      }
      localCursor.moveToFirst();
      localQQLoginModel.open_id = getStringByName(localCursor, col_open_id);
      localQQLoginModel.access_token = getStringByName(localCursor, col_access_token);
      localQQLoginModel.access_token_expire = getLongByName(localCursor, col_access_token_expire);
      localQQLoginModel.pay_token = getStringByName(localCursor, col_pay_token);
      localQQLoginModel.pay_token_expire = getLongByName(localCursor, col_pay_token_expire);
      localQQLoginModel.pf = getStringByName(localCursor, col_pf);
      localQQLoginModel.pf_key = getStringByName(localCursor, col_pf_key);
      localQQLoginModel.create_at = getLongByName(localCursor, col_create_at);
      localCursor.close();
      this.helper.close();
      return localQQLoginModel;
    }
  }

  public String getTableName()
  {
    return "qq_login_info";
  }

  public boolean isExisted()
  {
    synchronized (this.helper)
    {
      String str = " " + col_open_id + " = ? ";
      String[] arrayOfString = new String[1];
      arrayOfString[0] = this.open_id;
      try
      {
        Cursor localCursor = this.helper.getReadableDatabase().query("qq_login_info", null, str, arrayOfString, null, null, null, null);
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
    //   1: getfield 115	com/tencent/msdk/db/QQLoginModel:helper	Lcom/tencent/msdk/db/DbManager;
    //   4: astore_1
    //   5: aload_1
    //   6: monitorenter
    //   7: aload_0
    //   8: invokespecial 241	com/tencent/msdk/db/QQLoginModel:getUsableContentValues	()Landroid/content/ContentValues;
    //   11: astore_3
    //   12: new 122	java/lang/StringBuilder
    //   15: dup
    //   16: invokespecial 123	java/lang/StringBuilder:<init>	()V
    //   19: ldc_w 264
    //   22: invokevirtual 127	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   25: getstatic 37	com/tencent/msdk/db/QQLoginModel:col_open_id	Ljava/lang/String;
    //   28: invokevirtual 127	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   31: ldc_w 266
    //   34: invokevirtual 127	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   37: invokevirtual 132	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   40: astore 6
    //   42: iconst_1
    //   43: anewarray 268	java/lang/String
    //   46: astore 7
    //   48: aload 7
    //   50: iconst_0
    //   51: aload_0
    //   52: getfield 166	com/tencent/msdk/db/QQLoginModel:open_id	Ljava/lang/String;
    //   55: aastore
    //   56: aload_0
    //   57: getfield 115	com/tencent/msdk/db/QQLoginModel:helper	Lcom/tencent/msdk/db/DbManager;
    //   60: invokevirtual 239	com/tencent/msdk/db/DbManager:getWritableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   63: ldc 10
    //   65: aload_3
    //   66: aload 6
    //   68: aload 7
    //   70: invokevirtual 328	android/database/sqlite/SQLiteDatabase:update	(Ljava/lang/String;Landroid/content/ContentValues;Ljava/lang/String;[Ljava/lang/String;)I
    //   73: istore 8
    //   75: aload_0
    //   76: getfield 115	com/tencent/msdk/db/QQLoginModel:helper	Lcom/tencent/msdk/db/DbManager;
    //   79: invokevirtual 250	com/tencent/msdk/db/DbManager:close	()V
    //   82: aload_1
    //   83: monitorexit
    //   84: iload 8
    //   86: ireturn
    //   87: astore 5
    //   89: aload_0
    //   90: getfield 115	com/tencent/msdk/db/QQLoginModel:helper	Lcom/tencent/msdk/db/DbManager;
    //   93: invokevirtual 250	com/tencent/msdk/db/DbManager:close	()V
    //   96: aload_1
    //   97: monitorexit
    //   98: iconst_0
    //   99: ireturn
    //   100: astore_2
    //   101: aload_1
    //   102: monitorexit
    //   103: aload_2
    //   104: athrow
    //   105: astore 4
    //   107: aload_0
    //   108: getfield 115	com/tencent/msdk/db/QQLoginModel:helper	Lcom/tencent/msdk/db/DbManager;
    //   111: invokevirtual 250	com/tencent/msdk/db/DbManager:close	()V
    //   114: aload 4
    //   116: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   12	75	87	java/lang/Exception
    //   7	12	100	finally
    //   75	84	100	finally
    //   89	98	100	finally
    //   101	103	100	finally
    //   107	117	100	finally
    //   12	75	105	finally
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.msdk.db.QQLoginModel
 * JD-Core Version:    0.6.0
 */