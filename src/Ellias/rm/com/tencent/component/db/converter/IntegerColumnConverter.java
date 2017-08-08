package com.tencent.component.db.converter;

import android.database.Cursor;
import android.text.TextUtils;

public class IntegerColumnConverter
  implements ColumnConverter
{
  public Integer a(Cursor paramCursor, int paramInt)
  {
    return Integer.valueOf(paramCursor.getInt(paramInt));
  }

  public Integer a(Integer paramInteger)
  {
    return paramInteger;
  }

  public Integer a(Integer paramInteger, ClassLoader paramClassLoader)
  {
    return paramInteger;
  }

  public Integer a(String paramString)
  {
    if (TextUtils.isEmpty(paramString))
      return null;
    return Integer.valueOf(paramString);
  }

  public String a()
  {
    return "INTEGER";
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.db.converter.IntegerColumnConverter
 * JD-Core Version:    0.6.0
 */