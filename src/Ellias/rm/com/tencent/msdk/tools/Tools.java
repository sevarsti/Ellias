package com.tencent.msdk.tools;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.widget.Toast;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class Tools
{
  public static String WXPAKAGENAME = "com.tencent.mm";

  public static boolean isInstalledApp(Context paramContext, String paramString)
  {
    if (paramString == null);
    ArrayList localArrayList;
    do
    {
      return false;
      List localList = paramContext.getPackageManager().getInstalledPackages(0);
      localArrayList = null;
      if (0 != 0)
        continue;
      localArrayList = new ArrayList();
      for (int i = 0; i < localList.size(); i++)
      {
        PackageInfo localPackageInfo = (PackageInfo)localList.get(i);
        if ((0x1 & localPackageInfo.applicationInfo.flags) != 0)
          continue;
        localArrayList.add(localPackageInfo.applicationInfo.packageName);
      }
    }
    while (!localArrayList.contains(paramString));
    return true;
  }

  public static int reflectResouce(String paramString1, String paramString2, String paramString3)
  {
    if ((paramString1 == null) || (paramString2 == null) || (paramString3 == null))
      return 0;
    try
    {
      Class localClass = Class.forName(paramString1 + "$" + paramString2);
      int i = Integer.parseInt(localClass.getField(paramString3).get(localClass.newInstance()).toString());
      return i;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return -1;
  }

  public static void toast(Activity paramActivity, String paramString)
  {
    Toast.makeText(paramActivity, paramString, 0).show();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.msdk.tools.Tools
 * JD-Core Version:    0.6.0
 */