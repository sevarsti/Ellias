package com.tencent.mid.b;

import android.content.Context;
import android.os.Environment;
import com.tencent.mid.util.Util;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class c extends f
{
  public c(Context paramContext)
  {
    super(paramContext);
  }

  protected void a(a parama)
  {
  }

  protected void a(String paramString)
  {
    monitorenter;
    try
    {
      Util.logInfo("write mid to InternalStorage");
      b.a(Environment.getExternalStorageDirectory() + "/" + e());
      File localFile = new File(Environment.getExternalStorageDirectory(), f());
      if (localFile != null);
      try
      {
        BufferedWriter localBufferedWriter = new BufferedWriter(new FileWriter(localFile));
        localBufferedWriter.write(k() + "," + paramString);
        localBufferedWriter.write("\n");
        localBufferedWriter.close();
        monitorexit;
        return;
      }
      catch (IOException localIOException)
      {
        while (true)
          Util.logWarn(localIOException);
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  protected boolean a()
  {
    return (Util.checkPermission(this.a, "android.permission.WRITE_EXTERNAL_STORAGE")) && (Environment.getExternalStorageState().equals("mounted"));
  }

  protected String b()
  {
    monitorenter;
    while (true)
    {
      try
      {
        Util.logInfo("read mid from InternalStorage");
        File localFile = new File(Environment.getExternalStorageDirectory(), f());
        Object localObject2 = null;
        if (localFile == null)
          continue;
        try
        {
          Iterator localIterator = b.a(localFile).iterator();
          if (localIterator.hasNext())
          {
            String[] arrayOfString = ((String)localIterator.next()).split(",");
            if ((arrayOfString.length != 2) || (!arrayOfString[0].equals(k())))
              continue;
            Util.logInfo("read mid from InternalStorage:" + arrayOfString[1]);
            str = arrayOfString[1];
            localObject2 = str;
            monitorexit;
            return localObject2;
          }
        }
        catch (IOException localIOException)
        {
          Util.logWarn(localIOException);
          localObject2 = null;
          continue;
        }
      }
      finally
      {
        monitorexit;
      }
      String str = null;
    }
  }

  protected void c()
  {
    monitorenter;
    try
    {
      b.a(Environment.getExternalStorageDirectory() + "/" + e());
      File localFile = new File(Environment.getExternalStorageDirectory(), f());
      if (localFile != null);
      try
      {
        BufferedWriter localBufferedWriter = new BufferedWriter(new FileWriter(localFile));
        localBufferedWriter.write("");
        localBufferedWriter.close();
        monitorexit;
        return;
      }
      catch (IOException localIOException)
      {
        while (true)
          Util.logWarn(localIOException);
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  protected a d()
  {
    return null;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.mid.b.c
 * JD-Core Version:    0.6.0
 */