package com.tencent.component.db.converter;

import android.database.Cursor;
import android.text.TextUtils;
import java.sql.Date;

public class SqlDateColumnConverter
  implements ColumnConverter
{
  public Long a(Cursor paramCursor, int paramInt)
  {
    return Long.valueOf(paramCursor.getLong(paramInt));
  }

  public Long a(Date paramDate)
  {
    if (paramDate == null)
      return null;
    return Long.valueOf(paramDate.getTime());
  }

  public String a()
  {
    return "INTEGER";
  }

  public Date a(Long paramLong, ClassLoader paramClassLoader)
  {
    return new Date(paramLong.longValue());
  }

  public Date a(String paramString)
  {
    if (TextUtils.isEmpty(paramString))
      return null;
    return new Date(Long.valueOf(paramString).longValue());
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.db.converter.SqlDateColumnConverter
 * JD-Core Version:    0.6.0
 */