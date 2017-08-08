package com.tencent.android.tpush.logging.c;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.io.Reader;
import java.io.Writer;

public class a
{
  public static boolean a(Object paramObject)
  {
    if (paramObject == null);
    while (true)
    {
      return false;
      try
      {
        if ((paramObject instanceof InputStream))
        {
          ((InputStream)paramObject).close();
        }
        else if ((paramObject instanceof OutputStream))
        {
          ((OutputStream)paramObject).close();
        }
        else if ((paramObject instanceof Reader))
        {
          ((Reader)paramObject).close();
        }
        else if ((paramObject instanceof Writer))
        {
          ((Writer)paramObject).close();
        }
        else
        {
          if (!(paramObject instanceof RandomAccessFile))
            continue;
          ((RandomAccessFile)paramObject).close();
        }
        return true;
      }
      catch (IOException localIOException)
      {
      }
    }
    return false;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.android.tpush.logging.c.a
 * JD-Core Version:    0.6.0
 */