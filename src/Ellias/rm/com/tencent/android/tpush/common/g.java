package com.tencent.android.tpush.common;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import com.tencent.android.tpush.logging.TLog;

public class g
{
  private static final String[] a = { "android.permission.READ_PHONE_STATE", "android.permission.ACCESS_WIFI_STATE", "android.permission.ACCESS_NETWORK_STATE", "android.permission.RECEIVE_BOOT_COMPLETED", "android.permission.RESTART_PACKAGES", "android.permission.WRITE_SETTINGS", "android.permission.RECEIVE_USER_PRESENT", "android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.WAKE_LOCK" };

  public static void a(Context paramContext)
  {
    if (paramContext == null)
      throw new IllegalArgumentException("The context parameter can not be null!");
    String[] arrayOfString1;
    try
    {
      PackageManager localPackageManager = paramContext.getPackageManager();
      if (localPackageManager != null)
      {
        arrayOfString1 = localPackageManager.getPackageInfo(paramContext.getPackageName(), 4096).requestedPermissions;
        if (arrayOfString1 != null)
          break label75;
        PermissionNotFoundException localPermissionNotFoundException1 = new PermissionNotFoundException("The required permissions do not found!");
        TLog.e("TPush", "check required permissins exception.", localPermissionNotFoundException1);
        throw localPermissionNotFoundException1;
      }
    }
    catch (Exception localException)
    {
      TLog.e("TPush", "check required permissins exception.", localException);
    }
    while (true)
    {
      return;
      label75: for (String str : a)
      {
        if (a(arrayOfString1, str))
          continue;
        PermissionNotFoundException localPermissionNotFoundException2 = new PermissionNotFoundException("The required permission of <" + str + "> does not found!");
        TLog.e("TPush", "check required permissins exception.", localPermissionNotFoundException2);
        throw localPermissionNotFoundException2;
      }
    }
  }

  private static boolean a(String[] paramArrayOfString, String paramString)
  {
    TLog.v("TPush", ">>> check permission of " + paramString);
    int i = paramArrayOfString.length;
    for (int j = 0; ; j++)
    {
      int k = 0;
      if (j < i)
      {
        if (!paramString.equals(paramArrayOfString[j]))
          continue;
        TLog.v("TPush", ">>> permission of " + paramString + " is found.");
        k = 1;
      }
      return k;
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.android.tpush.common.g
 * JD-Core Version:    0.6.0
 */