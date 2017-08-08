package com.tencent.msdk.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.tencent.msdk.Singleton;
import com.tencent.msdk.notice.NoticeInfo;
import com.tencent.msdk.notice.eMSG_CONTENTTYPE;
import com.tencent.msdk.notice.eMSG_NOTICETYPE;
import com.tencent.msdk.tools.Logger;
import com.tencent.msdk.tools.T;
import java.util.Vector;

public class NoticeDBModel extends BaseDBModel
{
  private static String NOTICE_SQL_LIMIT = "20";
  public static final String TBL_NAME = "notice_info";
  public static String col_app_id;
  public static String col_content_type;
  public static String col_end_time;
  public static String col_horizontal_img_hash;
  public static String col_horizontal_img_url;
  public static String col_msg_content;
  public static String col_msg_id = "msg_id";
  public static String col_msg_scene;
  public static String col_msg_title;
  public static String col_msg_type;
  public static String col_msg_url;
  public static String col_open_id;
  public static String col_start_time;
  public static String col_vertical_img_hash;
  public static String col_vertical_img_url;
  public static String col_web_url;
  private DbManager helper = (DbManager)DbManager.gDefault.get();

  static
  {
    col_app_id = "app_id";
    col_open_id = "open_id";
    col_msg_url = "msg_url";
    col_msg_type = "msg_type";
    col_msg_scene = "msg_scene";
    col_start_time = "start_time";
    col_end_time = "end_time";
    col_content_type = "content_type";
    col_msg_content = "msg_content";
    col_msg_title = "msg_title";
    col_horizontal_img_url = "h_img_url";
    col_horizontal_img_hash = "h_img_hash";
    col_vertical_img_url = "v_img_url";
    col_vertical_img_hash = "v_img_hash";
    col_web_url = "web_url";
  }

  private NoticeInfo getColumnData(Cursor paramCursor)
  {
    NoticeInfo localNoticeInfo = new NoticeInfo();
    localNoticeInfo.mNoticeId = getStringByName(paramCursor, col_msg_id);
    localNoticeInfo.mAppId = getStringByName(paramCursor, col_app_id);
    localNoticeInfo.mOpenId = getStringByName(paramCursor, col_open_id);
    localNoticeInfo.mNoticeUrl = getStringByName(paramCursor, col_msg_url);
    localNoticeInfo.mNoticeType = eMSG_NOTICETYPE.getEnum(getIntByName(paramCursor, col_msg_type));
    localNoticeInfo.mNoticeScene = getStringByName(paramCursor, col_msg_scene);
    localNoticeInfo.mNoticeStartTime = getStringByName(paramCursor, col_start_time);
    localNoticeInfo.mNoticeEndTime = getStringByName(paramCursor, col_end_time);
    localNoticeInfo.mNoticeContentType = eMSG_CONTENTTYPE.getEnum(getIntByName(paramCursor, col_content_type));
    localNoticeInfo.mNoticeTitle = getStringByName(paramCursor, col_msg_title);
    localNoticeInfo.mNoticeContent = getStringByName(paramCursor, col_msg_content);
    localNoticeInfo.mNoticeHImgUrl = getStringByName(paramCursor, col_horizontal_img_url);
    localNoticeInfo.mNoticeHImgHash = getStringByName(paramCursor, col_horizontal_img_hash);
    localNoticeInfo.mNoticeVImgUrl = getStringByName(paramCursor, col_vertical_img_url);
    localNoticeInfo.mNoticeVImgHash = getStringByName(paramCursor, col_vertical_img_hash);
    localNoticeInfo.mNoticeContentWebUrl = getStringByName(paramCursor, col_web_url);
    return localNoticeInfo;
  }

  public static String getCreateTableSql()
  {
    String str1 = "" + "CREATE TABLE IF NOT EXISTS [notice_info] (";
    String str2 = str1 + "[" + col_msg_id + "] NVARCHAR(32)  PRIMARY KEY NOT NULL,";
    String str3 = str2 + "[" + col_app_id + "] VARCHAR(256)  NULL,";
    String str4 = str3 + "[" + col_open_id + "] VARCHAR(256)  NULL,";
    String str5 = str4 + "[" + col_msg_url + "] TEXT  NULL,";
    String str6 = str5 + "[" + col_msg_type + "] VARCHAR(16)  NULL,";
    String str7 = str6 + "[" + col_msg_scene + "] VARCHAR(16)  NULL,";
    String str8 = str7 + "[" + col_start_time + "] TIMESTAMP  NULL,";
    String str9 = str8 + "[" + col_end_time + "] TIMESTAMP  NULL,";
    String str10 = str9 + "[" + col_content_type + "] VARCHAR(16)  NULL,";
    String str11 = str10 + "[" + col_msg_content + "] TEXT  NULL,";
    String str12 = str11 + "[" + col_msg_title + "] VARCHAR(256)  NULL,";
    String str13 = str12 + "[" + col_horizontal_img_url + "] VARCHAR(256)  NULL,";
    String str14 = str13 + "[" + col_horizontal_img_hash + "] VARCHAR(64)  NULL,";
    String str15 = str14 + "[" + col_vertical_img_url + "] VARCHAR(256)  NULL,";
    String str16 = str15 + "[" + col_vertical_img_hash + "] VARCHAR(64)  NULL,";
    String str17 = str16 + "[" + col_web_url + "] VARCHAR(256)  NULL";
    return str17 + ")";
  }

  public static String getDropTableSql()
  {
    return "DROP TABLE IF EXISTS notice_info";
  }

  public int deleteNoticeByMsgId(String paramString)
  {
    if (T.ckIsEmpty(paramString))
    {
      Logger.w("msg_id is null");
      return 0;
    }
    synchronized (this.helper)
    {
      Logger.d("deleteNoticeByMsgId, msg_id= " + paramString);
      String str = " `" + col_msg_id + "` = ? ";
      String[] arrayOfString = { paramString };
      int i = this.helper.getWritableDatabase().delete("notice_info", str, arrayOfString);
      return i;
    }
  }

  public int deleteNoticeByTime(String paramString)
  {
    if (T.ckIsEmpty(paramString))
    {
      Logger.w("currentTime is null");
      return 0;
    }
    synchronized (this.helper)
    {
      Logger.d("deleteNoticeByTime, currentTime= " + paramString);
      String str = " " + col_end_time + " < ? ";
      String[] arrayOfString = { paramString };
      int i = this.helper.getWritableDatabase().delete("notice_info", str, arrayOfString);
      return i;
    }
  }

  public int deleteNoticeInDBByMsgList(String paramString)
  {
    if (T.ckIsEmpty(paramString))
    {
      Logger.d("msgList is null");
      return 0;
    }
    String str1 = paramString.replaceAll(" ", "");
    if (str1.endsWith(","))
    {
      Logger.d("sql para is end with ,msgList:" + str1);
      str1 = str1.substring(0, -1 + str1.length());
      Logger.d("sql para after check ,msgList:" + str1);
    }
    while (true)
    {
      synchronized (this.helper)
      {
        String str2 = " " + col_msg_id + " in (" + str1 + ") ";
        Logger.d("whereClause: " + str2);
        int i = this.helper.getWritableDatabase().delete("notice_info", str2, null);
        return i;
      }
      Logger.d("msgList:" + str1);
    }
  }

  public Vector<NoticeInfo> getNoticeRecordBySceneAndType(String paramString1, String paramString2, eMSG_NOTICETYPE parameMSG_NOTICETYPE, String paramString3)
  {
    String str1 = String.valueOf(System.currentTimeMillis() / 1000L);
    Vector localVector = new Vector();
    if ((T.ckIsEmpty(paramString1)) || (T.ckIsEmpty(paramString3)) || (T.ckIsEmpty(str1)))
    {
      Logger.w("appId,scene,currentTime maybe null");
      return localVector;
    }
    if (!eMSG_NOTICETYPE.checkIsValidType(parameMSG_NOTICETYPE))
    {
      Logger.w("bad noticeType:" + parameMSG_NOTICETYPE);
      return localVector;
    }
    Cursor localCursor;
    while (true)
    {
      String str2;
      synchronized (this.helper)
      {
        str2 = " " + col_app_id + " like ? and " + col_open_id + " in('', ? )and " + col_msg_scene + " = ? and " + col_start_time + " < ? and " + col_end_time + " > ? and ";
        if (eMSG_NOTICETYPE.eMSG_NOTICETYPE_ALL == parameMSG_NOTICETYPE)
        {
          str3 = str2 + col_msg_type + " in (0,1,?)";
          String[] arrayOfString = new String[6];
          arrayOfString[0] = ("%" + paramString1 + "%");
          arrayOfString[1] = paramString2;
          arrayOfString[2] = paramString3;
          arrayOfString[3] = str1;
          arrayOfString[4] = str1;
          arrayOfString[5] = String.valueOf(parameMSG_NOTICETYPE.val());
          Logger.d("appId:" + paramString1 + ",openid:" + paramString2 + ",scene:" + paramString3 + ",noticetype:" + parameMSG_NOTICETYPE + ",currentTimeStamp:" + str1);
          localCursor = this.helper.getReadableDatabase().query("notice_info", null, str3, arrayOfString, null, null, col_msg_id, NOTICE_SQL_LIMIT);
          Logger.d("query result:" + localCursor.getCount());
          localCursor.moveToFirst();
          if (localCursor.isAfterLast())
            break;
          NoticeInfo localNoticeInfo = getColumnData(localCursor);
          localVector.add(localNoticeInfo);
          Logger.d("query result info:" + localNoticeInfo.mNoticeId);
          localCursor.moveToNext();
        }
      }
      String str3 = str2 + col_msg_type + " = ?";
    }
    localCursor.close();
    monitorexit;
    return localVector;
  }

  public String getRedundancyNoticeListByTime(String paramString)
  {
    String str1 = "";
    if (T.ckIsEmpty(paramString))
    {
      Logger.w("currentTime maybe null");
      return "";
    }
    Cursor localCursor;
    synchronized (this.helper)
    {
      String str2 = " " + col_end_time + " < ? ";
      String[] arrayOfString = { paramString };
      Logger.d("currentTimeStamp:" + paramString);
      localCursor = this.helper.getReadableDatabase().query("notice_info", null, str2, arrayOfString, col_msg_id, null, null, NOTICE_SQL_LIMIT);
      Logger.d("query result:" + localCursor.getCount());
      localCursor.moveToFirst();
      if (!localCursor.isAfterLast())
      {
        NoticeInfo localNoticeInfo = getColumnData(localCursor);
        str1 = str1 + " " + localNoticeInfo.mNoticeId + " ,";
        localCursor.moveToNext();
      }
    }
    localCursor.close();
    monitorexit;
    return str1;
  }

  public String getTableName()
  {
    return "notice_info";
  }

  public boolean insert(NoticeInfo paramNoticeInfo)
  {
    if (paramNoticeInfo == null)
    {
      Logger.w("noticeInfo is null");
      return false;
    }
    DbManager localDbManager = this.helper;
    monitorenter;
    try
    {
      ContentValues localContentValues = paramNoticeInfo.getUsableContentValues(this);
      Logger.d("insert, cv = " + localContentValues);
      this.helper.getWritableDatabase().insert("notice_info", null, localContentValues);
      try
      {
        this.helper.close();
        return true;
      }
      finally
      {
        monitorexit;
      }
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
      Logger.d("Insert into notice_info error");
      this.helper.close();
      monitorexit;
      return false;
    }
    finally
    {
      this.helper.close();
    }
    throw localObject1;
  }

  public boolean isExisted(NoticeInfo paramNoticeInfo)
  {
    if (paramNoticeInfo == null)
    {
      Logger.w("noticeInfo is null");
      return false;
    }
    synchronized (this.helper)
    {
      String str = " " + col_msg_id + " = ? ";
      String[] arrayOfString = new String[1];
      arrayOfString[0] = String.valueOf(paramNoticeInfo.mNoticeId);
      Cursor localCursor = this.helper.getReadableDatabase().query("notice_info", null, str, arrayOfString, null, null, null, null);
      if (localCursor.getCount() > 0)
      {
        localCursor.close();
        return true;
      }
      localCursor.close();
      return false;
    }
  }

  public boolean save(NoticeInfo paramNoticeInfo)
  {
    if (paramNoticeInfo == null)
    {
      Logger.w("noticeInfo is null");
      return false;
    }
    synchronized (this.helper)
    {
      if (isExisted(paramNoticeInfo))
      {
        Logger.d("notice has exit!");
        String str = " " + col_msg_id + " = ? ";
        String[] arrayOfString = new String[1];
        arrayOfString[0] = String.valueOf(paramNoticeInfo.mNoticeId);
        ContentValues localContentValues = paramNoticeInfo.getUsableContentValues(this);
        Logger.d("update, cv = " + localContentValues);
        this.helper.getWritableDatabase().update("notice_info", localContentValues, str, arrayOfString);
        return true;
      }
      boolean bool = insert(paramNoticeInfo);
      return bool;
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.msdk.db.NoticeDBModel
 * JD-Core Version:    0.6.0
 */