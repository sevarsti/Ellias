package com.tencent.component.db.converter;

import android.database.Cursor;
import com.tencent.component.utils.ParcelUtil;
import java.io.Serializable;

public class SerializableColumnConverter
  implements ColumnConverter
{
  public Serializable a(String paramString)
  {
    return null;
  }

  public Serializable a(byte[] paramArrayOfByte, ClassLoader paramClassLoader)
  {
    return ParcelUtil.a(paramArrayOfByte);
  }

  public String a()
  {
    return "BLOB";
  }

  public byte[] a(Cursor paramCursor, int paramInt)
  {
    return paramCursor.getBlob(paramInt);
  }

  public byte[] a(Serializable paramSerializable)
  {
    return ParcelUtil.a(paramSerializable);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.db.converter.SerializableColumnConverter
 * JD-Core Version:    0.6.0
 */