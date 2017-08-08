package com.tencent.msdk.db;

import android.content.ContentValues;
import android.database.Cursor;
import com.tencent.msdk.tools.T;

public class BaseDBModel
{
  protected int getIntByName(Cursor paramCursor, String paramString)
  {
    return paramCursor.getInt(paramCursor.getColumnIndex(paramString));
  }

  protected long getLongByName(Cursor paramCursor, String paramString)
  {
    return paramCursor.getLong(paramCursor.getColumnIndex(paramString));
  }

  public String getStringByName(Cursor paramCursor, String paramString)
  {
    return paramCursor.getString(paramCursor.getColumnIndex(paramString));
  }

  public void putValues(ContentValues paramContentValues, String paramString, int paramInt)
  {
    paramContentValues.put(paramString, Integer.valueOf(paramInt));
  }

  public void putValues(ContentValues paramContentValues, String paramString1, String paramString2)
  {
    if (!T.ckIsEmpty(paramString2))
    {
      paramContentValues.put(paramString1, paramString2);
      return;
    }
    paramContentValues.put(paramString1, "");
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.msdk.db.BaseDBModel
 * JD-Core Version:    0.6.0
 */