package com.tencent.component.db.converter;

import android.database.Cursor;
import com.tencent.component.utils.ParcelUtil;
import java.util.List;

public class ListColumnConverter
  implements ColumnConverter
{
  public String a()
  {
    return "BLOB";
  }

  public List a(String paramString)
  {
    return null;
  }

  public List a(byte[] paramArrayOfByte, ClassLoader paramClassLoader)
  {
    return ParcelUtil.c(paramArrayOfByte, paramClassLoader);
  }

  public byte[] a(Cursor paramCursor, int paramInt)
  {
    return paramCursor.getBlob(paramInt);
  }

  public byte[] a(List paramList)
  {
    return ParcelUtil.a(paramList);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.db.converter.ListColumnConverter
 * JD-Core Version:    0.6.0
 */