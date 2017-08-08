package com.tencent.component.db.converter;

import android.database.Cursor;

public class ByteArrayColumnConverter
  implements ColumnConverter
{
  public String a()
  {
    return "BLOB";
  }

  public byte[] a(Cursor paramCursor, int paramInt)
  {
    return paramCursor.getBlob(paramInt);
  }

  public byte[] a(String paramString)
  {
    return null;
  }

  public byte[] a(byte[] paramArrayOfByte)
  {
    return paramArrayOfByte;
  }

  public byte[] a(byte[] paramArrayOfByte, ClassLoader paramClassLoader)
  {
    return paramArrayOfByte;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.db.converter.ByteArrayColumnConverter
 * JD-Core Version:    0.6.0
 */