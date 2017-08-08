package com.tencent.msdk.doctor.checklist;

import android.app.Activity;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import com.tencent.msdk.config.ConfigManager;
import com.tencent.msdk.doctor.CheckBase;
import com.tencent.msdk.pf.WGPfManager;
import com.tencent.msdk.tools.Logger;
import com.tencent.msdk.tools.T;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Global extends CheckBase
{
  public Global(Activity paramActivity)
  {
    super(paramActivity);
  }

  private ArrayList<String> containPermissions(List<String> paramList, String[] paramArrayOfString)
  {
    ArrayList localArrayList = new ArrayList();
    for (int i = 0; i < paramArrayOfString.length; i++)
    {
      if (paramList.contains(paramArrayOfString[i]))
        continue;
      localArrayList.add("Missing Android Permission " + paramArrayOfString[i]);
    }
    return localArrayList;
  }

  public ArrayList<String> check()
  {
    ArrayList localArrayList = new ArrayList();
    String[] arrayOfString = { "android.permission.INTERNET", "android.permission.ACCESS_NETWORK_STATE", "android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.READ_PHONE_STATE", "android.permission.ACCESS_WIFI_STATE", "android.permission.CHANGE_WIFI_STATE", "android.permission.RESTART_PACKAGES", "android.permission.GET_TASKS", "android.permission.MOUNT_UNMOUNT_FILESYSTEMS", "android.permission.SYSTEM_ALERT_WINDOW", "android.permission.ACCESS_FINE_LOCATION" };
    String str1 = WGPfManager.getInstance().getChannelId();
    if ((str1.equals("00000000")) || (str1.equals(ConfigManager.readValueByKey(this.mContext, "CHANNEL"))))
      Logger.d("You are using a test channel");
    while (true)
    {
      PackageManager localPackageManager = this.mContext.getPackageManager();
      String str2 = this.mContext.getPackageName();
      try
      {
        PackageInfo localPackageInfo = localPackageManager.getPackageInfo(str2, 4096);
        return containPermissions(Arrays.asList(localPackageInfo.requestedPermissions), arrayOfString);
        if (!T.ckIsEmpty(str1))
          continue;
        Logger.e("channelID is empty");
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException)
      {
        localNameNotFoundException.printStackTrace();
        localArrayList.add("Give sdk a error Activity in Initialized");
      }
    }
    return localArrayList;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.msdk.doctor.checklist.Global
 * JD-Core Version:    0.6.0
 */