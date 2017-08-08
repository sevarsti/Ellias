package com.tencent.component.db.converter;

import android.database.Cursor;
import android.text.TextUtils;

public class DoubleColumnConverter
  implements ColumnConverter
{
  public Double a(Cursor paramCursor, int paramInt)
  {
    return Double.valueOf(paramCursor.getDouble(paramInt));
  }

  public Double a(Double paramDouble)
  {
    return paramDouble;
  }

  public Double a(Double paramDouble, ClassLoader paramClassLoader)
  {
    return paramDouble;
  }

  public Double a(String paramString)
  {
    if (TextUtils.isEmpty(paramString))
      return null;
    return Double.valueOf(paramString);
  }

  public String a()
  {
    return "REAL";
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.db.converter.DoubleColumnConverter
 * JD-Core Version:    0.6.0
 */