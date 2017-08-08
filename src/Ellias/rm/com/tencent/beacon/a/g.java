package com.tencent.beacon.a;

import android.content.Context;
import com.tencent.beacon.d.a;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.util.HashMap;
import java.util.Map;

public final class g
{
  private static g c = null;
  private Context a = null;
  private Map<String, FileChannel> b = null;

  private g(Context paramContext)
  {
    this.a = paramContext.getApplicationContext();
    this.b = new HashMap(5);
  }

  public static g a(Context paramContext)
  {
    monitorenter;
    try
    {
      if (c == null)
        c = new g(paramContext);
      g localg = c;
      return localg;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  private File b(String paramString)
  {
    monitorenter;
    try
    {
      File localFile = new File(this.a.getFilesDir(), "RQD_" + paramString + ".lock");
      boolean bool = localFile.exists();
      if (!bool);
      try
      {
        Object[] arrayOfObject = new Object[1];
        arrayOfObject[0] = localFile.getAbsolutePath();
        a.b(" create lock file: %s", arrayOfObject);
        localFile.createNewFile();
        monitorexit;
        return localFile;
      }
      catch (IOException localIOException)
      {
        while (true)
          localIOException.printStackTrace();
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public final boolean a(String paramString)
  {
    monitorenter;
    if (paramString != null);
    try
    {
      int j = paramString.trim().length();
      int i;
      if (j <= 0)
        i = 0;
      while (true)
      {
        return i;
        File localFile = b(paramString);
        try
        {
          FileChannel localFileChannel = (FileChannel)this.b.get(paramString);
          if ((localFileChannel == null) || (!localFileChannel.isOpen()))
          {
            a.b(" create channel %s", new Object[] { paramString });
            localFileChannel = new FileOutputStream(localFile).getChannel();
            this.b.put(paramString, localFileChannel);
          }
          FileLock localFileLock = localFileChannel.tryLock();
          if (localFileLock != null)
          {
            boolean bool = localFileLock.isValid();
            if (bool)
              i = 1;
          }
        }
        catch (Throwable localThrowable)
        {
          localThrowable.printStackTrace();
          i = 0;
        }
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.beacon.a.g
 * JD-Core Version:    0.6.0
 */