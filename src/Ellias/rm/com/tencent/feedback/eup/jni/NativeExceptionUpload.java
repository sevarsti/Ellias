package com.tencent.feedback.eup.jni;

import com.tencent.feedback.common.e;
import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class NativeExceptionUpload
{
  public static final int ANDROID_LOG_DEBUG = 3;
  public static final int ANDROID_LOG_ERROR = 6;
  public static final int ANDROID_LOG_INFO = 4;
  public static final int ANDROID_LOG_WARN = 5;
  private static AtomicBoolean a = new AtomicBoolean(false);
  private static NativeExceptionHandler b = null;

  protected static native void doNativeCrashForTest();

  protected static native void enableHandler(boolean paramBoolean);

  public static void enableNativeEUP(boolean paramBoolean)
  {
    if (!a.get())
    {
      e.c("rqdp{  n enable disable!!}", new Object[0]);
      return;
    }
    enableHandler(paramBoolean);
  }

  public static NativeExceptionHandler getmHandler()
  {
    monitorenter;
    try
    {
      NativeExceptionHandler localNativeExceptionHandler = b;
      monitorexit;
      return localNativeExceptionHandler;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public static boolean loadRQDNativeLib()
  {
    int i = 1;
    monitorenter;
    try
    {
      System.loadLibrary("NativeRQD");
      a.set(true);
      return i;
    }
    catch (Throwable localThrowable)
    {
      while (true)
      {
        localThrowable.printStackTrace();
        e.d("rqdp{  load library fail! see detail ,will turn off native eup function!}", new Object[0]);
        i = 0;
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public static boolean loadRQDNativeLib(File paramFile)
  {
    int i = 1;
    monitorenter;
    if (paramFile != null);
    try
    {
      if (paramFile.exists())
      {
        boolean bool = paramFile.canRead();
        if (!bool);
      }
      while (true)
      {
        try
        {
          Object[] arrayOfObject = new Object[1];
          arrayOfObject[0] = paramFile.getAbsolutePath();
          e.b("load %s", arrayOfObject);
          System.load(paramFile.getAbsolutePath());
          a.set(true);
          monitorexit;
          return i;
        }
        catch (Throwable localThrowable)
        {
          localThrowable.printStackTrace();
          e.d("rqdp{  load library fail! see detail ,will turn off native eup function!}", new Object[0]);
          i = 0;
          continue;
        }
        i = 0;
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public static boolean loadRQDNativeLib(List<File> paramList)
  {
    monitorenter;
    if (paramList != null);
    while (true)
    {
      try
      {
        Iterator localIterator = paramList.iterator();
        if (localIterator.hasNext())
        {
          localFile = (File)localIterator.next();
          if ((!localFile.exists()) || (!localFile.isFile()) || (!localFile.getName().equals("libNativeRQD.so")))
            continue;
          if (localFile == null)
            continue;
          boolean bool1 = loadRQDNativeLib(localFile);
          boolean bool2 = bool1;
          return bool2;
          bool2 = false;
          continue;
        }
      }
      finally
      {
        monitorexit;
      }
      File localFile = null;
    }
  }

  public static boolean registEUP(String paramString1, String paramString2, int paramInt)
  {
    if (!a.get())
    {
      e.c("rqdp{  nreg disable!}", new Object[0]);
      return false;
    }
    if ((paramString1 == null) || (paramString1.trim().length() <= 0))
    {
      e.c("rqdp{  nreg param!}", new Object[0]);
      return false;
    }
    return registNativeExceptionHandler(paramString1, paramString2, paramInt);
  }

  protected static native boolean registNativeExceptionHandler(String paramString1, String paramString2, int paramInt);

  protected static native void setLogMode(int paramInt);

  public static void setNativeLogMode(int paramInt)
  {
    if (!a.get())
    {
      e.c("rqdp{  n sNL disable!!}", new Object[0]);
      return;
    }
    setLogMode(paramInt);
  }

  public static void setmHandler(NativeExceptionHandler paramNativeExceptionHandler)
  {
    monitorenter;
    try
    {
      b = paramNativeExceptionHandler;
      monitorexit;
      return;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public static void testNativeCrash()
  {
    if (!a.get())
    {
      e.c("rqdp{  n testNC disable!!}", new Object[0]);
      return;
    }
    doNativeCrashForTest();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.feedback.eup.jni.NativeExceptionUpload
 * JD-Core Version:    0.6.0
 */