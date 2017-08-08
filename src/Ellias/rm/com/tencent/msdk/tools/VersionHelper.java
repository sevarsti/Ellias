package com.tencent.msdk.tools;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;

public class VersionHelper
{
  private Context ctx;
  private String pkgName = "";

  public VersionHelper(Context paramContext, String paramString)
  {
    this.ctx = paramContext;
    this.pkgName = paramString;
  }

  public static int getAppVersionCode(Context paramContext, String paramString)
  {
    PackageManager localPackageManager = paramContext.getPackageManager();
    try
    {
      int i = localPackageManager.getPackageInfo(paramString, 0).versionCode;
      return i;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
    }
    return 0;
  }

  public static String getAppVersionName(Context paramContext, String paramString)
  {
    PackageManager localPackageManager = paramContext.getPackageManager();
    try
    {
      String str = localPackageManager.getPackageInfo(paramString, 0).versionName;
      return str;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
    }
    return "";
  }

  public int compareVersion(String paramString)
  {
    String str = getAppVersionName(this.ctx, this.pkgName);
    Logger.d("appVersion :" + str);
    return compareVersion(str, paramString);
  }

  public int compareVersion(String paramString1, String paramString2)
  {
    if ((paramString1 == null) && (paramString2 == null))
      return 0;
    if ((paramString1 != null) && (paramString2 == null))
      return 1;
    if ((paramString1 == null) && (paramString2 != null))
      return -1;
    String[] arrayOfString1 = paramString1.split("\\.");
    String[] arrayOfString2 = paramString2.split("\\.");
    for (int i = 0; ; i++)
    {
      int k;
      int m;
      try
      {
        if ((i < arrayOfString1.length) && (i < arrayOfString2.length))
        {
          k = Integer.parseInt(arrayOfString1[i]);
          m = Integer.parseInt(arrayOfString2[i]);
          if (k < m)
            return -1;
        }
        else
        {
          if (arrayOfString1.length > i)
            return 1;
          int j = arrayOfString2.length;
          if (j <= i)
            break;
          return -1;
        }
      }
      catch (NumberFormatException localNumberFormatException)
      {
        Logger.d("NumberFormatException ");
        return paramString1.compareTo(paramString2);
      }
      if (k > m)
        return 1;
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.msdk.tools.VersionHelper
 * JD-Core Version:    0.6.0
 */