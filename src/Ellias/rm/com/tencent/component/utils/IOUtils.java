package com.tencent.component.utils;

import android.database.Cursor;
import java.io.Closeable;

public class IOUtils
{
  public static void a(Cursor paramCursor)
  {
    if (paramCursor != null);
    try
    {
      paramCursor.close();
      return;
    }
    catch (Throwable localThrowable)
    {
    }
  }

  public static boolean a(Closeable paramCloseable)
  {
    if (paramCloseable != null)
      try
      {
        paramCloseable.close();
        return true;
      }
      catch (Throwable localThrowable)
      {
      }
    return false;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.utils.IOUtils
 * JD-Core Version:    0.6.0
 */