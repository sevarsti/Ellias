package com.tencent.msdk.push.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.tencent.msdk.Singleton;
import com.tencent.msdk.tools.Logger;

public class PushClientDbModel
{
  private static final String TBL_NAME = "push_client";
  private static final PushDBHelper helper = (PushDBHelper)PushDBHelper.gDefault.get();
  public Entry mData = new Entry();

  public static String getCreateTblSql()
  {
    String str1 = "" + "CREATE TABLE IF NOT EXISTS [push_client] (";
    String str2 = str1 + "[mat_id] NVARCHAR(128)  NOT NULL,";
    String str3 = str2 + "[mat_key] NVARCHAR(128) NOT NULL,";
    String str4 = str3 + "[mat_key_version] NVARCHAR(128) NOT NULL,";
    String str5 = str4 + "[mat_key_create_at] TIMESTAMP NULL,";
    String str6 = str5 + "[resolution] TIMESTAMP NULL,";
    String str7 = str6 + "[last_msg_id] NVARCHAR(128) NULL ";
    return str7 + ")";
  }

  public static String getDropTblSql()
  {
    return "DROP TABLE IF EXISTS push_client";
  }

  public static String getLastMatKeyVersion()
  {
    return getStringByCol("mat_key_version");
  }

  public static String getLastMsgId()
  {
    return getStringByCol("last_msg_id");
  }

  public static String getMatId()
  {
    return getStringByCol("mat_id");
  }

  public static String getMatKey()
  {
    return getStringByCol("mat_key");
  }

  private static String getStringByCol(String paramString)
  {
    try
    {
      synchronized (helper)
      {
        Cursor localCursor = helper.getReadableDatabase().query("push_client", new String[] { paramString }, null, null, null, null, null);
        if (localCursor.getCount() > 0)
        {
          localCursor.moveToFirst();
          String str = localCursor.getString(localCursor.getColumnIndex(paramString));
          Logger.d(paramString + " in Db: " + str);
          localCursor.close();
          return str;
        }
        Logger.w(paramString + " not found");
        localCursor.close();
        return "";
      }
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
      monitorexit;
    }
    return "";
  }

  public boolean create()
  {
    synchronized (helper)
    {
      try
      {
        ContentValues localContentValues = this.mData.GetContentCalues();
        long l = helper.getWritableDatabase().insert("push_client", null, localContentValues);
        helper.close();
        boolean bool = l < -1L;
        int i = 0;
        if (bool)
          i = 1;
        return i;
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
        return false;
      }
    }
  }

  public int deleteAll()
  {
    PushDBHelper localPushDBHelper = helper;
    monitorenter;
    try
    {
      i = helper.getWritableDatabase().delete("push_client", null, null);
    }
    catch (Exception localException)
    {
    }
    finally
    {
      int i;
      helper.close();
    }
    throw localObject1;
  }

  public String getTableName()
  {
    return "push_client";
  }

  public boolean isExisted()
  {
    return false;
  }

  public int update(ContentValues paramContentValues, String paramString, String[] paramArrayOfString)
  {
    try
    {
      int i = helper.getWritableDatabase().update("push_client", paramContentValues, paramString, paramArrayOfString);
      return i;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return 0;
  }

  public class Entry
  {
    public static final String sColLastMsgId = "last_msg_id";
    public static final String sColMatId = "mat_id";
    public static final String sColMatKey = "mat_key";
    public static final String sColMatKeyCreateAt = "mat_key_create_at";
    public static final String sColMatKeyVersion = "mat_key_version";
    public static final String sColResolution = "resolution";
    private String mLastMsgId = "";
    private String mMatId = "";
    private String mMatKey = "";
    private long mMatKeyCreateAt = 0L;
    private String mMatKeyVersion = "";
    private String mResolution = "";

    public Entry()
    {
    }

    public ContentValues GetContentCalues()
    {
      ContentValues localContentValues = new ContentValues();
      localContentValues.put("mat_id", this.mMatId);
      localContentValues.put("mat_key", this.mMatKey);
      localContentValues.put("mat_key_version", this.mMatKeyVersion);
      localContentValues.put("mat_key_create_at", Long.valueOf(this.mMatKeyCreateAt));
      localContentValues.put("last_msg_id", this.mLastMsgId);
      localContentValues.put("resolution", this.mResolution);
      return localContentValues;
    }

    public void setmLastMsgId(String paramString)
    {
      this.mLastMsgId = paramString;
    }

    public void setmMatId(String paramString)
    {
      this.mMatId = paramString;
    }

    public void setmMatKey(String paramString)
    {
      this.mMatKey = paramString;
    }

    public void setmMatKeyCreateAt(long paramLong)
    {
      this.mMatKeyCreateAt = paramLong;
    }

    public void setmMatKeyVersion(String paramString)
    {
      this.mMatKeyVersion = paramString;
    }

    public void setmResolution(String paramString)
    {
      this.mResolution = paramString;
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.msdk.push.db.PushClientDbModel
 * JD-Core Version:    0.6.0
 */