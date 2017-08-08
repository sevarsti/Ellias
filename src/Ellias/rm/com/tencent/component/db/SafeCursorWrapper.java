package com.tencent.component.db;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.database.CharArrayBuffer;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.CursorWrapper;
import android.database.DataSetObserver;
import android.net.Uri;
import android.os.Bundle;
import com.tencent.component.db.exception.DbCacheExceptionHandler;
import com.tencent.component.utils.AssertUtil;

public class SafeCursorWrapper extends CursorWrapper
{
  private boolean a;

  private SafeCursorWrapper(Cursor paramCursor)
  {
    super(paramCursor);
    if (paramCursor != null);
    for (boolean bool = true; ; bool = false)
    {
      AssertUtil.a(bool);
      return;
    }
  }

  public static SafeCursorWrapper a(Cursor paramCursor)
  {
    if (paramCursor != null)
      return new SafeCursorWrapper(paramCursor);
    return null;
  }

  private Object a(Object paramObject, int paramInt)
  {
    return paramObject;
  }

  private void a(Throwable paramThrowable)
  {
    DbCacheExceptionHandler.a().a(paramThrowable);
  }

  public void close()
  {
    try
    {
      super.close();
      this.a = true;
      return;
    }
    catch (Throwable localThrowable)
    {
      a(localThrowable);
    }
  }

  public void copyStringToBuffer(int paramInt, CharArrayBuffer paramCharArrayBuffer)
  {
    try
    {
      super.copyStringToBuffer(paramInt, paramCharArrayBuffer);
      return;
    }
    catch (Throwable localThrowable)
    {
      a(localThrowable);
    }
  }

  public void deactivate()
  {
    try
    {
      super.deactivate();
      return;
    }
    catch (Throwable localThrowable)
    {
      a(localThrowable);
    }
  }

  public byte[] getBlob(int paramInt)
  {
    try
    {
      byte[] arrayOfByte = (byte[])a(super.getBlob(paramInt), paramInt);
      return arrayOfByte;
    }
    catch (Throwable localThrowable)
    {
      a(localThrowable);
    }
    return null;
  }

  public int getColumnCount()
  {
    try
    {
      int i = super.getColumnCount();
      return i;
    }
    catch (Throwable localThrowable)
    {
      a(localThrowable);
    }
    return 0;
  }

  public int getColumnIndex(String paramString)
  {
    try
    {
      int i = super.getColumnIndex(paramString);
      return i;
    }
    catch (Throwable localThrowable)
    {
      a(localThrowable);
    }
    return -1;
  }

  public int getColumnIndexOrThrow(String paramString)
  {
    try
    {
      int i = super.getColumnIndexOrThrow(paramString);
      return i;
    }
    catch (Throwable localThrowable)
    {
      a(localThrowable);
    }
    return -1;
  }

  public String getColumnName(int paramInt)
  {
    try
    {
      String str = super.getColumnName(paramInt);
      return str;
    }
    catch (Throwable localThrowable)
    {
      a(localThrowable);
    }
    return null;
  }

  public String[] getColumnNames()
  {
    try
    {
      String[] arrayOfString = super.getColumnNames();
      return arrayOfString;
    }
    catch (Throwable localThrowable)
    {
      a(localThrowable);
    }
    return null;
  }

  public int getCount()
  {
    try
    {
      int i = super.getCount();
      return i;
    }
    catch (Throwable localThrowable)
    {
      a(localThrowable);
    }
    return 0;
  }

  public double getDouble(int paramInt)
  {
    try
    {
      double d = ((Double)a(Double.valueOf(super.getDouble(paramInt)), paramInt)).doubleValue();
      return d;
    }
    catch (Throwable localThrowable)
    {
      a(localThrowable);
    }
    return 0.0D;
  }

  public Bundle getExtras()
  {
    try
    {
      Bundle localBundle = super.getExtras();
      return localBundle;
    }
    catch (Throwable localThrowable)
    {
      a(localThrowable);
    }
    return null;
  }

  public float getFloat(int paramInt)
  {
    try
    {
      float f = ((Float)a(Float.valueOf(super.getFloat(paramInt)), paramInt)).floatValue();
      return f;
    }
    catch (Throwable localThrowable)
    {
      a(localThrowable);
    }
    return 0.0F;
  }

  public int getInt(int paramInt)
  {
    try
    {
      int i = ((Integer)a(Integer.valueOf(super.getInt(paramInt)), paramInt)).intValue();
      return i;
    }
    catch (Throwable localThrowable)
    {
      a(localThrowable);
    }
    return 0;
  }

  public long getLong(int paramInt)
  {
    try
    {
      long l = ((Long)a(Long.valueOf(super.getLong(paramInt)), paramInt)).longValue();
      return l;
    }
    catch (Throwable localThrowable)
    {
      a(localThrowable);
    }
    return 0L;
  }

  public int getPosition()
  {
    try
    {
      int i = super.getPosition();
      return i;
    }
    catch (Throwable localThrowable)
    {
      a(localThrowable);
    }
    return -1;
  }

  public short getShort(int paramInt)
  {
    try
    {
      int i = ((Short)a(Short.valueOf(super.getShort(paramInt)), paramInt)).shortValue();
      return i;
    }
    catch (Throwable localThrowable)
    {
      a(localThrowable);
    }
    return 0;
  }

  public String getString(int paramInt)
  {
    try
    {
      String str = (String)a(super.getString(paramInt), paramInt);
      return str;
    }
    catch (Throwable localThrowable)
    {
      a(localThrowable);
    }
    return null;
  }

  @SuppressLint({"NewApi"})
  public int getType(int paramInt)
  {
    try
    {
      int i = super.getType(paramInt);
      return i;
    }
    catch (Throwable localThrowable)
    {
      a(localThrowable);
    }
    return 0;
  }

  public boolean getWantsAllOnMoveCalls()
  {
    try
    {
      boolean bool = super.getWantsAllOnMoveCalls();
      return bool;
    }
    catch (Throwable localThrowable)
    {
      a(localThrowable);
    }
    return false;
  }

  public boolean isAfterLast()
  {
    try
    {
      boolean bool = super.isAfterLast();
      return bool;
    }
    catch (Throwable localThrowable)
    {
      a(localThrowable);
    }
    return false;
  }

  public boolean isBeforeFirst()
  {
    try
    {
      boolean bool = super.isBeforeFirst();
      return bool;
    }
    catch (Throwable localThrowable)
    {
      a(localThrowable);
    }
    return false;
  }

  public boolean isClosed()
  {
    try
    {
      boolean bool = super.isClosed();
      return bool;
    }
    catch (Throwable localThrowable)
    {
      a(localThrowable);
    }
    return this.a;
  }

  public boolean isFirst()
  {
    try
    {
      boolean bool = super.isFirst();
      return bool;
    }
    catch (Throwable localThrowable)
    {
      a(localThrowable);
    }
    return false;
  }

  public boolean isLast()
  {
    try
    {
      boolean bool = super.isLast();
      return bool;
    }
    catch (Throwable localThrowable)
    {
      a(localThrowable);
    }
    return false;
  }

  public boolean isNull(int paramInt)
  {
    try
    {
      boolean bool = super.isNull(paramInt);
      return bool;
    }
    catch (Throwable localThrowable)
    {
      a(localThrowable);
    }
    return true;
  }

  public boolean move(int paramInt)
  {
    try
    {
      boolean bool = super.move(paramInt);
      return bool;
    }
    catch (Throwable localThrowable)
    {
      a(localThrowable);
    }
    return false;
  }

  public boolean moveToFirst()
  {
    try
    {
      boolean bool = super.moveToFirst();
      return bool;
    }
    catch (Throwable localThrowable)
    {
      a(localThrowable);
    }
    return false;
  }

  public boolean moveToLast()
  {
    try
    {
      boolean bool = super.moveToLast();
      return bool;
    }
    catch (Throwable localThrowable)
    {
      a(localThrowable);
    }
    return false;
  }

  public boolean moveToNext()
  {
    try
    {
      boolean bool = super.moveToNext();
      return bool;
    }
    catch (Throwable localThrowable)
    {
      a(localThrowable);
    }
    return false;
  }

  public boolean moveToPosition(int paramInt)
  {
    try
    {
      boolean bool = super.moveToPosition(paramInt);
      return bool;
    }
    catch (Throwable localThrowable)
    {
      a(localThrowable);
    }
    return false;
  }

  public boolean moveToPrevious()
  {
    try
    {
      boolean bool = super.moveToPrevious();
      return bool;
    }
    catch (Throwable localThrowable)
    {
      a(localThrowable);
    }
    return false;
  }

  public void registerContentObserver(ContentObserver paramContentObserver)
  {
    try
    {
      super.registerContentObserver(paramContentObserver);
      return;
    }
    catch (Throwable localThrowable)
    {
      a(localThrowable);
    }
  }

  public void registerDataSetObserver(DataSetObserver paramDataSetObserver)
  {
    try
    {
      super.registerDataSetObserver(paramDataSetObserver);
      return;
    }
    catch (Throwable localThrowable)
    {
      a(localThrowable);
    }
  }

  public boolean requery()
  {
    try
    {
      boolean bool = super.requery();
      return bool;
    }
    catch (Throwable localThrowable)
    {
      a(localThrowable);
    }
    return false;
  }

  public Bundle respond(Bundle paramBundle)
  {
    try
    {
      Bundle localBundle = super.respond(paramBundle);
      return localBundle;
    }
    catch (Throwable localThrowable)
    {
      a(localThrowable);
    }
    return null;
  }

  public void setNotificationUri(ContentResolver paramContentResolver, Uri paramUri)
  {
    try
    {
      super.setNotificationUri(paramContentResolver, paramUri);
      return;
    }
    catch (Throwable localThrowable)
    {
      a(localThrowable);
    }
  }

  public void unregisterContentObserver(ContentObserver paramContentObserver)
  {
    try
    {
      super.unregisterContentObserver(paramContentObserver);
      return;
    }
    catch (Throwable localThrowable)
    {
      a(localThrowable);
    }
  }

  public void unregisterDataSetObserver(DataSetObserver paramDataSetObserver)
  {
    try
    {
      super.unregisterDataSetObserver(paramDataSetObserver);
      return;
    }
    catch (Throwable localThrowable)
    {
      a(localThrowable);
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.db.SafeCursorWrapper
 * JD-Core Version:    0.6.0
 */