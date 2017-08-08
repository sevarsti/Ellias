package com.tencent.smtt.sdk;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import com.tencent.smtt.utils.ReflectionUtils;
import dalvik.system.DexClassLoader;
import java.io.File;
import java.lang.reflect.Constructor;
import java.util.Iterator;
import java.util.List;

public class QbSdk
{
  public static final String QB_PACKAGE_NAME = "com.tencent.mtt";
  private static final String SHELL_DEX_FILE = "sdk_shell.jar";
  private static final String SHELL_IMPL_CLASS = "com.tencent.mtt.sdk.shell.SdkShell";
  public static final int VERSION = 1;
  private static boolean mIsSysWebViewForced = false;
  private static int sQbVersion;
  private static Class<?> sShellClass;
  private static Object sShellObj;

  public static boolean canLoadX5(Context paramContext)
  {
    if (!init(paramContext));
    Object localObject;
    do
    {
      return false;
      localObject = ReflectionUtils.invokeInstance(sShellObj, "canLoadX5");
    }
    while (localObject == null);
    return ((Boolean)localObject).booleanValue();
  }

  public static boolean canOpenMimeFileType(Context paramContext, String paramString)
  {
    if (!init(paramContext));
    Object localObject;
    do
    {
      return false;
      localObject = ReflectionUtils.invokeStatic(sShellClass, "canOpenMimeFileType", new Class[] { String.class }, new Object[] { paramString });
    }
    while (localObject == null);
    return ((Boolean)localObject).booleanValue();
  }

  private static void deleteFiles(File paramFile)
  {
    if ((paramFile == null) || (!paramFile.exists()) || (!paramFile.isDirectory()));
    while (true)
    {
      return;
      for (File localFile : paramFile.listFiles())
      {
        if (!localFile.isFile())
          continue;
        localFile.delete();
      }
    }
  }

  public static void forceSysWebView(boolean paramBoolean)
  {
    mIsSysWebViewForced = paramBoolean;
  }

  public static String getX5CoreTimestamp()
  {
    Object localObject = ReflectionUtils.invokeStatic(sShellClass, "getX5CoreTimestamp", null, new Object[0]);
    if (localObject == null)
      return "";
    return (String)localObject;
  }

  private static boolean init(Context paramContext)
  {
    if (mIsSysWebViewForced)
      return false;
    try
    {
      File localFile1 = paramContext.getDir("x5core", 0);
      PackageManager localPackageManager = paramContext.getPackageManager();
      PackageInfo localPackageInfo = localPackageManager.getPackageInfo("com.tencent.mtt", 0);
      if ((sQbVersion != 0) && (sQbVersion != localPackageInfo.versionCode))
      {
        sShellClass = null;
        sShellObj = null;
        deleteFiles(localFile1);
      }
      sQbVersion = localPackageInfo.versionCode;
      if (sShellClass != null)
        return true;
      Context localContext = paramContext.createPackageContext("com.tencent.mtt", 2);
      File localFile2 = new File(localContext.getDir("x5_share", 0), "sdk_shell.jar");
      if (!localFile2.exists())
      {
        Intent localIntent = new Intent("com.tencent.mtt.ACTION_INSTALL_X5");
        localIntent.setPackage("com.tencent.mtt");
        List localList = localPackageManager.queryIntentServices(localIntent, 0);
        if ((localList != null) && (localList.size() > 0))
          paramContext.startService(localIntent);
      }
      else
      {
        sShellClass = new DexClassLoader(localFile2.getAbsolutePath(), localFile1.getAbsolutePath(), null, QbSdk.class.getClassLoader()).loadClass("com.tencent.mtt.sdk.shell.SdkShell");
        sShellObj = sShellClass.getConstructor(new Class[] { Context.class }).newInstance(new Object[] { localContext });
        Object localObject = sShellObj;
        Class[] arrayOfClass = new Class[1];
        arrayOfClass[0] = Integer.TYPE;
        Object[] arrayOfObject = new Object[1];
        arrayOfObject[0] = Integer.valueOf(1);
        ReflectionUtils.invokeInstance(localObject, "setClientVersion", arrayOfClass, arrayOfObject);
        return true;
      }
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
      return false;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      label278: break label278;
    }
    return false;
  }

  public static boolean isSdkVideoServiceFg(Context paramContext)
  {
    try
    {
      List localList = ((ActivityManager)paramContext.getApplicationContext().getSystemService("activity")).getRunningAppProcesses();
      if (localList != null)
      {
        if (localList.size() == 0)
          return false;
        Iterator localIterator = localList.iterator();
        while (localIterator.hasNext())
        {
          ActivityManager.RunningAppProcessInfo localRunningAppProcessInfo = (ActivityManager.RunningAppProcessInfo)localIterator.next();
          if ((localRunningAppProcessInfo.importance != 100) || (localRunningAppProcessInfo.processName == null))
            continue;
          boolean bool = localRunningAppProcessInfo.processName.contains("com.tencent.mtt:VideoService");
          if (bool)
            return true;
        }
      }
    }
    catch (Throwable localThrowable)
    {
      localThrowable.printStackTrace();
    }
    return false;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.smtt.sdk.QbSdk
 * JD-Core Version:    0.6.0
 */