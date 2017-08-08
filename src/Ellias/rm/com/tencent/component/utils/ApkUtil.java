package com.tencent.component.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;
import android.graphics.drawable.Drawable;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ApkUtil
{
  private static Class a;
  private static Method b;

  static
  {
    try
    {
      a = AssetManager.class;
      b = a.getDeclaredMethod("addAssetPath", new Class[] { String.class });
      return;
    }
    catch (NoSuchMethodException localNoSuchMethodException)
    {
      localNoSuchMethodException.printStackTrace();
      return;
    }
    catch (Throwable localThrowable)
    {
      localThrowable.printStackTrace();
    }
  }

  public static PackageInfo a(Context paramContext, String paramString, int paramInt)
  {
    if (!a(paramString));
    PackageInfo localPackageInfo;
    do
    {
      return null;
      localPackageInfo = paramContext.getPackageManager().getPackageArchiveInfo(paramString, paramInt);
    }
    while (localPackageInfo == null);
    if (((paramInt & 0x40) != 0) && (localPackageInfo.signatures == null))
      localPackageInfo.signatures = ApkUtil.Certificates.a(paramString);
    return localPackageInfo;
  }

  public static Resources a(Context paramContext, String paramString)
  {
    boolean bool = a(paramString);
    Object localObject = null;
    if (!bool);
    while (true)
    {
      return localObject;
      PackageInfo localPackageInfo = paramContext.getPackageManager().getPackageArchiveInfo(paramString, 0);
      ApplicationInfo localApplicationInfo;
      if (localPackageInfo == null)
      {
        localApplicationInfo = null;
        localObject = null;
        if (localApplicationInfo != null)
        {
          localApplicationInfo.sourceDir = paramString;
          localApplicationInfo.publicSourceDir = paramString;
        }
      }
      try
      {
        Resources localResources = paramContext.getPackageManager().getResourcesForApplication(localApplicationInfo);
        localObject = localResources;
        if (localObject != null)
          continue;
        return e(paramContext, paramString);
        localApplicationInfo = localPackageInfo.applicationInfo;
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException)
      {
        while (true)
        {
          localNameNotFoundException.printStackTrace();
          localObject = null;
        }
      }
      catch (Throwable localThrowable)
      {
        while (true)
        {
          localThrowable.printStackTrace();
          localObject = null;
        }
      }
    }
  }

  private static Drawable a(Resources paramResources, int paramInt)
  {
    try
    {
      Drawable localDrawable = paramResources.getDrawable(paramInt);
      return localDrawable;
    }
    catch (Resources.NotFoundException localNotFoundException)
    {
    }
    return null;
  }

  private static boolean a(String paramString)
  {
    if ((paramString == null) || (paramString.length() == 0));
    File localFile;
    do
    {
      return false;
      localFile = new File(paramString);
    }
    while ((!localFile.exists()) || (!localFile.isFile()));
    return true;
  }

  public static ApplicationInfo b(Context paramContext, String paramString, int paramInt)
  {
    boolean bool = a(paramString);
    ApplicationInfo localApplicationInfo = null;
    if (!bool);
    while (true)
    {
      return localApplicationInfo;
      PackageInfo localPackageInfo = paramContext.getPackageManager().getPackageArchiveInfo(paramString, paramInt);
      localApplicationInfo = null;
      if (localPackageInfo == null);
      while (localApplicationInfo != null)
      {
        localApplicationInfo.sourceDir = paramString;
        localApplicationInfo.publicSourceDir = paramString;
        return localApplicationInfo;
        localApplicationInfo = localPackageInfo.applicationInfo;
      }
    }
  }

  public static PackageInfo b(Context paramContext, String paramString)
  {
    return a(paramContext, paramString, 0);
  }

  private static String b(Resources paramResources, int paramInt)
  {
    try
    {
      String str = paramResources.getString(paramInt);
      return str;
    }
    catch (Resources.NotFoundException localNotFoundException)
    {
    }
    return null;
  }

  public static ApplicationInfo c(Context paramContext, String paramString)
  {
    return b(paramContext, paramString, 0);
  }

  public static ApkUtil.ApkInfo d(Context paramContext, String paramString)
  {
    if (!a(paramString))
      return null;
    try
    {
      localPackageInfo = paramContext.getPackageManager().getPackageArchiveInfo(paramString, 0);
      localResources = a(paramContext, paramString);
      if ((localPackageInfo != null) && (localResources != null))
      {
        localApplicationInfo = localPackageInfo.applicationInfo;
        if (localApplicationInfo == null)
        {
          str = null;
          break label188;
          if ((localObject1 != null) || (localApplicationInfo == null))
            break label176;
          localObject2 = paramContext.getPackageManager().getApplicationIcon(localApplicationInfo);
          localApkInfo = new ApkUtil.ApkInfo();
        }
      }
    }
    catch (Throwable localThrowable1)
    {
      while (true)
      {
        ApplicationInfo localApplicationInfo;
        try
        {
          PackageInfo localPackageInfo;
          Resources localResources;
          localApkInfo.a = localPackageInfo;
          localApkInfo.b = localPackageInfo.packageName;
          localApkInfo.c = str;
          localApkInfo.d = ((Drawable)localObject2);
          localApkInfo.e = localPackageInfo.versionCode;
          return localApkInfo;
          String str = b(localResources, localApplicationInfo.labelRes);
          break label188;
          Drawable localDrawable = a(localResources, localApplicationInfo.icon);
          localObject1 = localDrawable;
          continue;
          localThrowable1 = localThrowable1;
          localApkInfo = null;
          Throwable localThrowable2 = localThrowable1;
          localThrowable2.printStackTrace();
          continue;
        }
        catch (Throwable localThrowable3)
        {
          continue;
        }
        label176: Object localObject2 = localObject1;
        continue;
        ApkUtil.ApkInfo localApkInfo = null;
        continue;
        label188: if (localApplicationInfo != null)
          continue;
        Object localObject1 = null;
      }
    }
  }

  private static Resources e(Context paramContext, String paramString)
  {
    if ((a == null) || (b == null));
    do
      return null;
    while (!a(paramString));
    try
    {
      AssetManager localAssetManager = (AssetManager)a.newInstance();
      Object[] arrayOfObject = { paramString };
      b.invoke(localAssetManager, arrayOfObject);
      Resources localResources2 = new Resources(localAssetManager, paramContext.getResources().getDisplayMetrics(), paramContext.getResources().getConfiguration());
      localResources1 = localResources2;
      return localResources1;
    }
    catch (InvocationTargetException localInvocationTargetException)
    {
      while (true)
      {
        localInvocationTargetException.printStackTrace();
        localResources1 = null;
      }
    }
    catch (InstantiationException localInstantiationException)
    {
      while (true)
      {
        localInstantiationException.printStackTrace();
        localResources1 = null;
      }
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      while (true)
      {
        localIllegalAccessException.printStackTrace();
        localResources1 = null;
      }
    }
    catch (Throwable localThrowable)
    {
      while (true)
      {
        localThrowable.printStackTrace();
        Resources localResources1 = null;
      }
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.utils.ApkUtil
 * JD-Core Version:    0.6.0
 */