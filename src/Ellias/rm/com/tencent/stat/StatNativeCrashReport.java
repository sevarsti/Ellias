package com.tencent.stat;

import android.content.Context;
import com.tencent.stat.common.StatLogger;
import com.tencent.stat.common.k;
import com.tencent.stat.common.p;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashSet;

public class StatNativeCrashReport
{
  public static final String PRE_TAG_TOMBSTONE_FNAME = "tombstone_";
  static StatNativeCrashReport a;
  private static StatLogger b = k.b();
  private static boolean d;
  private static boolean e;
  private static String f;
  private volatile boolean c = false;

  static
  {
    a = new StatNativeCrashReport();
    d = false;
    e = false;
    f = null;
    try
    {
      System.loadLibrary("MtaNativeCrash");
      return;
    }
    catch (Throwable localThrowable)
    {
      d = false;
      b.w(localThrowable);
    }
  }

  static String a(File paramFile)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    BufferedReader localBufferedReader;
    try
    {
      localBufferedReader = new BufferedReader(new FileReader(paramFile));
      while (true)
      {
        String str = localBufferedReader.readLine();
        if (str == null)
          break;
        localStringBuilder.append(str);
        localStringBuilder.append('\n');
      }
    }
    catch (IOException localIOException)
    {
      b.e(localIOException);
    }
    while (true)
    {
      return localStringBuilder.toString();
      localBufferedReader.close();
    }
  }

  static LinkedHashSet<File> a(Context paramContext)
  {
    LinkedHashSet localLinkedHashSet = new LinkedHashSet();
    String str = getTombstonesDir(paramContext);
    if (str != null)
    {
      File localFile1 = new File(str);
      if ((localFile1 != null) && (localFile1.isDirectory()))
      {
        File[] arrayOfFile = localFile1.listFiles();
        if (arrayOfFile != null)
        {
          int i = arrayOfFile.length;
          for (int j = 0; j < i; j++)
          {
            File localFile2 = arrayOfFile[j];
            if ((!localFile2.getName().startsWith("tombstone_")) || (!localFile2.isFile()))
              continue;
            b.d("get tombstone file:" + localFile2.getAbsolutePath().toString());
            localLinkedHashSet.add(localFile2.getAbsoluteFile());
          }
        }
      }
    }
    return localLinkedHashSet;
  }

  static long b(File paramFile)
  {
    String str = paramFile.getName().replace("tombstone_", "");
    try
    {
      long l = Long.valueOf(str).longValue();
      return l;
    }
    catch (NumberFormatException localNumberFormatException)
    {
      b.e(localNumberFormatException);
    }
    return 0L;
  }

  public static void doNativeCrashTest()
  {
    a.makeJniCrash();
  }

  public static String getTombstonesDir(Context paramContext)
  {
    if (f == null)
      f = p.a(paramContext, "__mta_tombstone__", "");
    return f;
  }

  public static void initNativeCrash(Context paramContext, String paramString)
  {
    if (a.c)
      return;
    if (paramString == null);
    try
    {
      paramString = paramContext.getDir("tombstones", 0).getAbsolutePath();
      if (paramString.length() > 128)
      {
        b.e("The length of tombstones dir: " + paramString + " can't exceeds 200 bytes.");
        return;
      }
    }
    catch (Throwable localThrowable)
    {
      b.w(localThrowable);
      return;
    }
    f = paramString;
    p.b(paramContext, "__mta_tombstone__", paramString);
    setNativeCrashEnable(true);
    a.initJNICrash(paramString);
    a.c = true;
    b.d("initNativeCrash success.");
  }

  public static boolean isNativeCrashDebugEnable()
  {
    return e;
  }

  public static boolean isNativeCrashEnable()
  {
    return d;
  }

  public static String onNativeCrashHappened()
  {
    try
    {
      new RuntimeException("MTA has caught a native crash, java stack:\n").printStackTrace();
      return "";
    }
    catch (RuntimeException localRuntimeException)
    {
    }
    return localRuntimeException.toString();
  }

  public static void setNativeCrashDebugEnable(boolean paramBoolean)
  {
    try
    {
      a.enableNativeCrashDebug(paramBoolean);
      e = paramBoolean;
      return;
    }
    catch (Throwable localThrowable)
    {
      b.w(localThrowable);
    }
  }

  public static void setNativeCrashEnable(boolean paramBoolean)
  {
    try
    {
      a.enableNativeCrash(paramBoolean);
      d = paramBoolean;
      return;
    }
    catch (Throwable localThrowable)
    {
      b.w(localThrowable);
    }
  }

  public native void enableNativeCrash(boolean paramBoolean);

  public native void enableNativeCrashDebug(boolean paramBoolean);

  public native boolean initJNICrash(String paramString);

  public native String makeJniCrash();

  public native String stringFromJNI();
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.stat.StatNativeCrashReport
 * JD-Core Version:    0.6.0
 */