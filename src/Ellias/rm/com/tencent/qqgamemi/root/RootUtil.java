package com.tencent.qqgamemi.root;

import android.os.Build;
import com.tencent.component.utils.log.LogUtil;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;

public class RootUtil
{
  protected static RootUtil b;
  private static final String c = RootUtil.class.getSimpleName();
  protected boolean a = false;

  static
  {
    b = null;
  }

  protected RootUtil()
  {
    if ((!c()) && (!d()) && (!e()))
      this.a = false;
  }

  public static RootUtil a()
  {
    monitorenter;
    try
    {
      if (b == null)
        b = new RootUtil();
      RootUtil localRootUtil = b;
      return localRootUtil;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  private static ArrayList a(String[] paramArrayOfString)
  {
    try
    {
      localProcess = Runtime.getRuntime().exec(paramArrayOfString);
      localBufferedReader = new BufferedReader(new InputStreamReader(localProcess.getInputStream()));
      char[] arrayOfChar = new char[4096];
      localArrayList = new ArrayList();
      while (localBufferedReader.read(arrayOfChar) > 0)
        localArrayList.add(arrayOfChar.toString());
    }
    catch (IOException localIOException)
    {
      Process localProcess;
      BufferedReader localBufferedReader;
      ArrayList localArrayList;
      throw new RuntimeException(localIOException);
      localBufferedReader.close();
      localProcess.waitFor();
      return localArrayList;
    }
    catch (InterruptedException localInterruptedException)
    {
    }
    throw new RuntimeException(localInterruptedException);
  }

  public static void a(RootUtil paramRootUtil)
  {
    monitorenter;
    try
    {
      b = paramRootUtil;
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

  public static boolean c()
  {
    String str = Build.TAGS;
    if ((str != null) && (str.contains("test-keys")))
    {
      LogUtil.d(c, "rqdp{  test-keys}");
      return true;
    }
    return false;
  }

  public static boolean d()
  {
    try
    {
      if (new File("/system/app/Superuser.apk").exists())
      {
        LogUtil.d(c, "rqdp{  super_apk}");
        return true;
      }
    }
    catch (Exception localException)
    {
    }
    return false;
  }

  public static boolean e()
  {
    ArrayList localArrayList = a(new String[] { "/system/bin/sh", "-c", "type su" });
    if ((localArrayList != null) && (localArrayList.size() > 0))
    {
      Iterator localIterator = localArrayList.iterator();
      while (localIterator.hasNext())
      {
        String str = (String)localIterator.next();
        LogUtil.d(c, str);
        if (str.contains("not found"))
          return false;
      }
      LogUtil.d(c, "rqdp{  sufile}");
      return true;
    }
    LogUtil.d(c, "rqdp{  no response}");
    return false;
  }

  public boolean b()
  {
    monitorenter;
    try
    {
      LogUtil.d(c, "isRooted:" + this.a);
      boolean bool = this.a;
      monitorexit;
      return bool;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.root.RootUtil
 * JD-Core Version:    0.6.0
 */