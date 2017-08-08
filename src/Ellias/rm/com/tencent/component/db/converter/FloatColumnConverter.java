package com.tencent.component.db.converter;

import android.database.Cursor;
import android.text.TextUtils;

public class FloatColumnConverter
  implements ColumnConverter
{
  public Float a(Cursor paramCursor, int paramInt)
  {
    return Float.valueOf(paramCursor.getFloat(paramInt));
  }

  public Float a(Float paramFloat)
  {
    return paramFloat;
  }

  public Float a(Float paramFloat, ClassLoader paramClassLoader)
  {
    return paramFloat;
  }

  public Float a(String paramString)
  {
    if (TextUtils.isEmpty(paramString))
      return null;
    return Float.valueOf(paramString);
  }

  public String a()
  {
    return "REAL";
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.db.converter.FloatColumnConverter
 * JD-Core Version:    0.6.0
 */