package com.tencent.msdk.db;

import android.database.Cursor;
import com.tencent.msdk.api.LoginRet;

public abstract class BaseUserInfo
{
  public String access_token = "";
  public long access_token_expire = 0L;
  public int age = 0;
  public String avatar = "";
  public long create_at = 0L;
  public int gender = 0;
  public String is_active = "";
  public String nickname = "";
  public String open_id = "";
  public String pf = "";
  public String pf_key = "";
  public long update_at = 0L;

  public BaseUserInfo()
  {
  }

  public BaseUserInfo(String paramString)
  {
    this.open_id = paramString;
  }

  public BaseUserInfo(String paramString1, String paramString2, int paramInt, String paramString3, String paramString4)
  {
    this.open_id = paramString1;
    this.access_token = paramString2;
    this.access_token_expire = paramInt;
    this.pf = paramString3;
    this.pf_key = paramString4;
  }

  public abstract LoginRet convertToLoginRet();

  protected int getIntByName(Cursor paramCursor, String paramString)
  {
    return paramCursor.getInt(paramCursor.getColumnIndex(paramString));
  }

  protected long getLongByName(Cursor paramCursor, String paramString)
  {
    return paramCursor.getLong(paramCursor.getColumnIndex(paramString));
  }

  protected String getStringByName(Cursor paramCursor, String paramString)
  {
    return paramCursor.getString(paramCursor.getColumnIndex(paramString));
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.msdk.db.BaseUserInfo
 * JD-Core Version:    0.6.0
 */