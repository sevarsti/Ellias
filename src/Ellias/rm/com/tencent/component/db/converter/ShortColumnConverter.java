package com.tencent.component.db.converter;

import android.database.Cursor;
import android.text.TextUtils;

public class ShortColumnConverter
  implements ColumnConverter
{
  public Short a(Cursor paramCursor, int paramInt)
  {
    return Short.valueOf(paramCursor.getShort(paramInt));
  }

  public Short a(Short paramShort)
  {
    return paramShort;
  }

  public Short a(Short paramShort, ClassLoader paramClassLoader)
  {
    return paramShort;
  }

  public Short a(String paramString)
  {
    if (TextUtils.isEmpty(paramString))
      return null;
    return Short.valueOf(paramString);
  }

  public String a()
  {
    return "INTEGER";
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.db.converter.ShortColumnConverter
 * JD-Core Version:    0.6.0
 */