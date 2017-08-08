package com.tencent.component.db.converter;

import android.database.Cursor;
import android.text.TextUtils;

public class LongColumnConverter
  implements ColumnConverter
{
  public Long a(Cursor paramCursor, int paramInt)
  {
    return Long.valueOf(paramCursor.getLong(paramInt));
  }

  public Long a(Long paramLong)
  {
    return paramLong;
  }

  public Long a(Long paramLong, ClassLoader paramClassLoader)
  {
    return paramLong;
  }

  public Long a(String paramString)
  {
    if (TextUtils.isEmpty(paramString))
      return null;
    return Long.valueOf(paramString);
  }

  public String a()
  {
    return "INTEGER";
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.db.converter.LongColumnConverter
 * JD-Core Version:    0.6.0
 */