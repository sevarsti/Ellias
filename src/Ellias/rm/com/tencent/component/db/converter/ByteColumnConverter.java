package com.tencent.component.db.converter;

import android.database.Cursor;
import android.text.TextUtils;

public class ByteColumnConverter
  implements ColumnConverter
{
  public Byte a(Cursor paramCursor, int paramInt)
  {
    return Byte.valueOf((byte)paramCursor.getInt(paramInt));
  }

  public Byte a(Byte paramByte)
  {
    return paramByte;
  }

  public Byte a(Byte paramByte, ClassLoader paramClassLoader)
  {
    return paramByte;
  }

  public Byte a(String paramString)
  {
    if (TextUtils.isEmpty(paramString))
      return null;
    return Byte.valueOf(paramString);
  }

  public String a()
  {
    return "INTEGER";
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.db.converter.ByteColumnConverter
 * JD-Core Version:    0.6.0
 */