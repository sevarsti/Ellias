package com.tencent.component.db.converter;

import android.database.Cursor;
import android.os.Parcelable;
import com.tencent.component.utils.ParcelUtil;

public class ParcelColumnConverter
  implements ColumnConverter
{
  public Parcelable a(String paramString)
  {
    return null;
  }

  public Parcelable a(byte[] paramArrayOfByte, ClassLoader paramClassLoader)
  {
    return ParcelUtil.b(paramArrayOfByte, paramClassLoader);
  }

  public String a()
  {
    return "BLOB";
  }

  public byte[] a(Cursor paramCursor, int paramInt)
  {
    return paramCursor.getBlob(paramInt);
  }

  public byte[] a(Parcelable paramParcelable)
  {
    return ParcelUtil.a(paramParcelable);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.db.converter.ParcelColumnConverter
 * JD-Core Version:    0.6.0
 */