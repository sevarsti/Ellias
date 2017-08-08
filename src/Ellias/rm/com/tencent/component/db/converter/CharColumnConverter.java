package com.tencent.component.db.converter;

import android.database.Cursor;
import android.text.TextUtils;

public class CharColumnConverter
  implements ColumnConverter
{
  public Character a(Integer paramInteger, ClassLoader paramClassLoader)
  {
    return Character.valueOf((char)paramInteger.intValue());
  }

  public Character a(String paramString)
  {
    if (TextUtils.isEmpty(paramString))
      return null;
    return Character.valueOf(paramString.charAt(0));
  }

  public Integer a(Cursor paramCursor, int paramInt)
  {
    return Integer.valueOf(paramCursor.getInt(paramInt));
  }

  public Integer a(Character paramCharacter)
  {
    if (paramCharacter == null)
      return null;
    return Integer.valueOf(paramCharacter.charValue());
  }

  public String a()
  {
    return "INTEGER";
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.db.converter.CharColumnConverter
 * JD-Core Version:    0.6.0
 */