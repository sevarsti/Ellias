package com.tencent.component.db.converter;

import android.database.Cursor;
import android.text.TextUtils;

public class BooleanColumnConverter
  implements ColumnConverter
{
  public Boolean a(Integer paramInteger, ClassLoader paramClassLoader)
  {
    int i = 1;
    if (paramInteger.intValue() == i);
    while (true)
    {
      return Boolean.valueOf(i);
      int j = 0;
    }
  }

  public Boolean a(String paramString)
  {
    if (TextUtils.isEmpty(paramString))
      return null;
    if (paramString.length() == 1);
    for (boolean bool = "1".equals(paramString); ; bool = Boolean.valueOf(paramString).booleanValue())
      return Boolean.valueOf(bool);
  }

  public Integer a(Cursor paramCursor, int paramInt)
  {
    return Integer.valueOf(paramCursor.getInt(paramInt));
  }

  public Integer a(Boolean paramBoolean)
  {
    if (paramBoolean == null)
      return null;
    if (paramBoolean.booleanValue());
    for (int i = 1; ; i = 0)
      return Integer.valueOf(i);
  }

  public String a()
  {
    return "INTEGER";
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.db.converter.BooleanColumnConverter
 * JD-Core Version:    0.6.0
 */