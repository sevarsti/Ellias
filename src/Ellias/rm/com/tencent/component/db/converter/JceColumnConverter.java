package com.tencent.component.db.converter;

import android.database.Cursor;
import com.qq.taf.jce.JceStruct;
import com.tencent.component.utils.ParcelUtil;

public class JceColumnConverter
  implements ColumnConverter
{
  public JceStruct a(String paramString)
  {
    return null;
  }

  public JceStruct a(byte[] paramArrayOfByte, ClassLoader paramClassLoader)
  {
    if (paramArrayOfByte != null)
      return ParcelUtil.a(paramArrayOfByte, paramClassLoader);
    return null;
  }

  public String a()
  {
    return "BLOB";
  }

  public byte[] a(Cursor paramCursor, int paramInt)
  {
    return paramCursor.getBlob(paramInt);
  }

  public byte[] a(JceStruct paramJceStruct)
  {
    return ParcelUtil.a(paramJceStruct);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.db.converter.JceColumnConverter
 * JD-Core Version:    0.6.0
 */