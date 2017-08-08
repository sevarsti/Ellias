package com.tencent.component.db.converter;

import android.database.Cursor;

public abstract interface ColumnConverter
{
  public abstract Object a(Object paramObject);

  public abstract Object a(Object paramObject, ClassLoader paramClassLoader);

  public abstract String a();

  public abstract Object b(Cursor paramCursor, int paramInt);

  public abstract Object b(String paramString);
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.db.converter.ColumnConverter
 * JD-Core Version:    0.6.0
 */