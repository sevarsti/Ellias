package com.tencent.component.db.converter;

import android.database.Cursor;

public class StringColumnConverter
  implements ColumnConverter
{
  public String a()
  {
    return "TEXT";
  }

  public String a(Cursor paramCursor, int paramInt)
  {
    return paramCursor.getString(paramInt);
  }

  public String a(String paramString)
  {
    return paramString;
  }

  public String a(String paramString, ClassLoader paramClassLoader)
  {
    return paramString;
  }

  public String c(String paramString)
  {
    return paramString;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.db.converter.StringColumnConverter
 * JD-Core Version:    0.6.0
 */