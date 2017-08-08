package com.tencent.msdk.doctor;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Build.VERSION;
import com.tencent.msdk.WeGame;
import com.tencent.msdk.pf.WGPfManager;
import com.tencent.msdk.tools.Logger;
import com.tencent.msdk.tools.T;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;

public class MsdkCheckConfig
{
  private final byte OFFERID = 16;
  private final byte QQAPPID = 1;
  private final byte QQAPPKEY = 2;
  private final byte QQBASEINFO = 19;
  private final byte WXAPPID = 4;
  private final byte WXAPPKEY = 8;
  private final byte WXBASEINFO = 28;
  private String channelFileName = "channel.ini";
  private Activity mActivity = WeGame.getInstance().getActivity();
  private String msdkConfigFileName = "msdkconfig.ini";
  private boolean needCheck = isNeedCheck();

  private boolean containPermissions(List<String> paramList, String[] paramArrayOfString)
  {
    int i = 1;
    for (int j = 0; j < paramArrayOfString.length; j++)
    {
      if (paramList.contains(paramArrayOfString[j]))
        continue;
      Logger.e("Msdk: Missing Android Permission " + paramArrayOfString[j]);
      i = 0;
    }
    return i;
  }

  private String getValueFromAssetsFile(String paramString1, String paramString2)
  {
    Properties localProperties = new Properties();
    try
    {
      localProperties.load(this.mActivity.getResources().getAssets().open(paramString2));
      String str = localProperties.getProperty(paramString1, "");
      System.out.println(str);
      return str;
    }
    catch (IOException localIOException)
    {
      localIOException.printStackTrace();
      Logger.e("Msdk: read assets/" + paramString2 + " error");
    }
    return "error";
  }

  private boolean isNeedCheck()
  {
    String str = getValueFromAssetsFile("MSDK_URL", this.msdkConfigFileName);
    if (str.equals("error"));
    do
    {
      return false;
      if (T.ckIsEmpty(str))
      {
        Logger.e("Msdk: MSDK_URL is not set properly in assets/msdkconfig.ini");
        return false;
      }
      if (str.charAt(-1 + str.length()) == '/')
      {
        Logger.e("Msdk: MSDK_URL in msdkconfig.ini should not end with '/', maybe you should delete the '/' ");
        return false;
      }
      if ((str.contains("msdktest.qq.com")) || (str.contains("opensdktest.tencent.com")) || (str.contains("msdkdev.qq.com")))
        return true;
    }
    while ((str.contains("opensdk.tencent.com")) || (str.contains("msdk.qq.com")));
    Logger.w("Msdk: MSDK_URL may be illegal, are you sure to use it");
    return false;
  }

  private boolean queryBaseInfo(byte paramByte)
  {
    return true;
  }

  private boolean queryIntentFilter(Intent paramIntent, String paramString)
  {
    Iterator localIterator = this.mActivity.getPackageManager().queryIntentActivities(paramIntent, 65536).iterator();
    int i;
    while (true)
    {
      boolean bool = localIterator.hasNext();
      i = 0;
      if (!bool)
        break;
      if (!paramString.equals(((ResolveInfo)localIterator.next()).activityInfo.name))
        continue;
      i = 1;
    }
    if (i == 0)
      Logger.e("Msdk: the intent-filter of " + paramString + " has not be configured correctly");
    return i;
  }

  public boolean checkAllConfig()
  {
    if (!this.needCheck)
      Logger.d("MSDK:MsdkCheckConfig is closed");
    do
      return true;
    while ((checkBasicConfig()) && (checkQQConfig()) && (checkWXConfig()) && (checkPushConfig()));
    return false;
  }

  public boolean checkBasicConfig()
  {
    if (!this.needCheck)
    {
      Logger.d("MSDK:MsdkCheckConfig is closed");
      return true;
    }
    String[] arrayOfString = { "android.permission.INTERNET", "android.permission.ACCESS_NETWORK_STATE", "android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.READ_PHONE_STATE", "android.permission.ACCESS_WIFI_STATE", "android.permission.CHANGE_WIFI_STATE", "android.permission.RESTART_PACKAGES", "android.permission.GET_TASKS", "android.permission.MOUNT_UNMOUNT_FILESYSTEMS", "android.permission.SYSTEM_ALERT_WINDOW", "android.permission.ACCESS_FINE_LOCATION" };
    String str1 = WGPfManager.getInstance().getChannelId();
    if ((str1.equals("00000000")) || (str1.equals(getValueFromAssetsFile("CHANNEL", this.channelFileName))))
      Logger.d("Msdk: You are using a test channel");
    while (true)
    {
      PackageManager localPackageManager = this.mActivity.getPackageManager();
      String str2 = this.mActivity.getPackageName();
      try
      {
        PackageInfo localPackageInfo = localPackageManager.getPackageInfo(str2, 4096);
        return containPermissions(Arrays.asList(localPackageInfo.requestedPermissions), arrayOfString);
        if (!T.ckIsEmpty(str1))
          continue;
        Logger.e("Msdk: channelID is empty");
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException)
      {
        localNameNotFoundException.printStackTrace();
      }
    }
    return false;
  }

  public boolean checkPushConfig()
  {
    int i;
    if (!this.needCheck)
    {
      Logger.d("MSDK:MsdkCheckConfig is closed");
      i = 1;
    }
    while (true)
    {
      return i;
      String str1 = getValueFromAssetsFile("PUSH", this.msdkConfigFileName);
      if (str1.equals("false"))
        return true;
      if (!str1.equals("true"))
      {
        Logger.e("Msdk: PUSH in assets/msdkconfig.ini is not set properly");
        return false;
      }
      String str2 = this.mActivity.getPackageName();
      String str3 = str2 + ".push.ForwardActivity";
      try
      {
        Class.forName(str3);
        ActivityInfo localActivityInfo1 = this.mActivity.getPackageManager().getActivityInfo(new ComponentName(str2, str3), 128);
        ServiceInfo localServiceInfo = this.mActivity.getPackageManager().getServiceInfo(new ComponentName(str2, "com.tencent.msdk.push.HttpPushService"), 128);
        ActivityInfo localActivityInfo2 = this.mActivity.getPackageManager().getReceiverInfo(new ComponentName(str2, "com.tencent.msdk.push.AlarmReveiver"), 128);
        i = 1;
        if ((0x20 & localActivityInfo1.flags) != 32)
        {
          Logger.e("Msdk: the excludeFromRecents of " + str3 + " must be true");
          i = 0;
        }
        if (localActivityInfo1.exported != true)
        {
          Logger.e("Msdk: the exported of " + str3 + " must be true");
          i = 0;
        }
        if (localActivityInfo1.launchMode != 1)
        {
          Logger.e("Msdk: the launchMose of " + str3 + " must be singleTop");
          i = 0;
        }
        if (localActivityInfo1.taskAffinity.equals(str2))
        {
          Logger.e("Msdk: the taskAffinity of " + str3 + " must be different from the PackageName of the game: " + str2);
          i = 0;
        }
        if (localServiceInfo.exported != true)
        {
          Logger.e("Msdk: the exported of " + "com.tencent.msdk.push.HttpPushService" + " must be true");
          i = 0;
        }
        if (!localServiceInfo.processName.equals(".msdk_push_v_1"))
        {
          Logger.e("Msdk: the process of " + "com.tencent.msdk.push.HttpPushService" + " must be .msdk_push_v_1");
          i = 0;
        }
        if (localActivityInfo2.processName.equals(".msdk_push_v_1"))
          continue;
        Logger.e("Msdk: the process of " + "com.tencent.msdk.push.AlarmReveiver" + " must be .msdk_push_v_1");
        return false;
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException)
      {
        localNameNotFoundException.printStackTrace();
        if (localNameNotFoundException.toString().contains(str3))
          Logger.e("Msdk: Lack of activity: " + str3);
        if (localNameNotFoundException.toString().contains("com.tencent.msdk.push.HttpPushService"))
          Logger.e("Msdk: Lack of service: " + "com.tencent.msdk.push.HttpPushService");
        boolean bool = localNameNotFoundException.toString().contains("com.tencent.msdk.push.AlarmReveiver");
        i = 0;
        if (!bool)
          continue;
        Logger.e("Msdk: Lack of recevice: " + "com.tencent.msdk.push.AlarmReveiver");
        return false;
      }
      catch (ClassNotFoundException localClassNotFoundException)
      {
        localClassNotFoundException.printStackTrace();
        Logger.e("Msdk: ForwardActivity.java must be put into package " + this.mActivity.getPackageName() + ".push");
      }
    }
    return false;
  }

  public boolean checkQQConfig()
  {
    int i;
    if (!this.needCheck)
    {
      Logger.d("MSDK:MsdkCheckConfig is closed");
      i = 1;
    }
    String str1;
    int j;
    int k;
    label530: Intent localIntent2;
    Iterator localIterator;
    do
      while (!localIterator.hasNext())
      {
        Set localSet;
        do
        {
          do
          {
            return i;
            if (!queryBaseInfo(19))
              return false;
            str1 = this.mActivity.getPackageName();
            String str2 = Build.VERSION.SDK;
            Intent localIntent1;
            while (true)
            {
              try
              {
                PackageManager localPackageManager1 = this.mActivity.getPackageManager();
                ComponentName localComponentName1 = new ComponentName(str1, "com.tencent.tauth.AuthActivity");
                ActivityInfo localActivityInfo1 = localPackageManager1.getActivityInfo(localComponentName1, 128);
                PackageManager localPackageManager2 = this.mActivity.getPackageManager();
                ComponentName localComponentName2 = new ComponentName(str1, "com.tencent.connect.common.AssistActivity");
                ActivityInfo localActivityInfo2 = localPackageManager2.getActivityInfo(localComponentName2, 128);
                i = 1;
                if (localActivityInfo1.launchMode == 2)
                  continue;
                Logger.e("Msdk: the launchMose of " + "com.tencent.tauth.AuthActivity" + " be singleTask");
                i = 0;
                if ((0x80 & localActivityInfo1.flags) == 128)
                  continue;
                Logger.e("Msdk: the noHistory of " + "com.tencent.tauth.AuthActivity" + " must be true");
                i = 0;
                if (localActivityInfo2.screenOrientation == 1)
                  continue;
                Logger.e("Msdk: the screenOrientation of " + "com.tencent.connect.common.AssistActivity" + " must be portrait");
                i = 0;
                j = Integer.parseInt(str2);
                if (j < 13)
                {
                  k = 160;
                  if ((k & localActivityInfo2.configChanges) == k)
                    continue;
                  if (j >= 13)
                    break label530;
                  Logger.e("Msdk: the configChanges of " + "com.tencent.connect.common.AssistActivity" + " must be " + "orientation|keyboardHidden");
                  i = 0;
                  if (localActivityInfo2.theme == 16973840)
                    continue;
                  Logger.e("Msdk: the theme of " + "com.tencent.connect.common.AssistActivity" + " must be Theme.Translucent.NoTitleBar");
                  i = 0;
                  localIntent1 = new Intent();
                  localIntent1.setData(Uri.parse("tencent" + WeGame.getInstance().qq_appid + "://"));
                  if (queryIntentFilter(localIntent1, "com.tencent.tauth.AuthActivity"))
                    break;
                  Logger.e("Msdk: QQ AppID for Initialiezed must be the same as configed in AndroidMenifest.xml");
                  return false;
                }
              }
              catch (PackageManager.NameNotFoundException localNameNotFoundException1)
              {
                localNameNotFoundException1.printStackTrace();
                if (!localNameNotFoundException1.toString().contains("com.tencent.tauth.AuthActivity"))
                  continue;
                Logger.e("Msdk: Lack of activity: " + "com.tencent.tauth.AuthActivity");
                if (!localNameNotFoundException1.toString().contains("com.tencent.connect.common.AssistActivity"))
                  continue;
                Logger.e("Msdk: Lack of activity: " + "com.tencent.connect.common.AssistActivity");
                return false;
              }
              k = 1184;
              continue;
              Logger.e("Msdk: the configChanges of " + "com.tencent.connect.common.AssistActivity" + " must be " + "orientation|screenSize|keyboardHidden");
            }
            localIntent1.setAction("android.intent.action.VIEW");
            localIntent1.addCategory("android.intent.category.DEFAULT");
            localIntent1.addCategory("android.intent.category.BROWSABLE");
            if (!queryIntentFilter(localIntent1, "com.tencent.tauth.AuthActivity"))
              i = 0;
            localIntent2 = this.mActivity.getPackageManager().getLaunchIntentForPackage(str1);
          }
          while (!localIntent2.getComponent().getClassName().equals(this.mActivity.getClass().getName()));
          localSet = localIntent2.getCategories();
        }
        while (localSet == null);
        localIterator = localSet.iterator();
      }
    while (!((String)localIterator.next()).equals("android.intent.category.LAUNCHER"));
    while (true)
    {
      try
      {
        PackageManager localPackageManager3 = this.mActivity.getPackageManager();
        ComponentName localComponentName3 = new ComponentName(str1, localIntent2.getComponent().getClassName());
        ActivityInfo localActivityInfo3 = localPackageManager3.getActivityInfo(localComponentName3, 128);
        if ((k & localActivityInfo3.configChanges) == k)
          break;
        if (j < 13)
        {
          Logger.e("Msdk: if the game Activity is Launch Activity,the configChanges of " + localIntent2.getComponent().getClassName() + " must be orientation|keyboardHidden");
          i = 0;
        }
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException2)
      {
        localNameNotFoundException2.printStackTrace();
        return false;
      }
      Logger.e("Msdk: if the game Activity is Launch Activity,the configChanges of " + localIntent2.getComponent().getClassName() + " must be orientation|screenSize|keyboardHidden");
    }
  }

  public boolean checkWXConfig()
  {
    int i;
    if (!this.needCheck)
    {
      Logger.d("MSDK:MsdkCheckConfig is closed");
      i = 1;
    }
    String str2;
    Intent localIntent;
    do
    {
      boolean bool;
      do
      {
        return i;
        bool = queryBaseInfo(28);
        i = 0;
      }
      while (!bool);
      String str1 = this.mActivity.getPackageName();
      str2 = str1 + ".wxapi.WXEntryActivity";
      try
      {
        Class.forName(str2);
        ActivityInfo localActivityInfo = this.mActivity.getPackageManager().getActivityInfo(new ComponentName(str1, str2), 128);
        i = 1;
        if ((0x20 & localActivityInfo.flags) != 32)
        {
          Logger.e("Msdk: the excludeFromRecents of WXEntryActivity must be true");
          i = 0;
        }
        if (localActivityInfo.exported != true)
        {
          Logger.e("Msdk: the exported of WXEntryActivity must be true");
          i = 0;
        }
        if (localActivityInfo.launchMode != 1)
        {
          Logger.e("Msdk: the launchMose of WXEntryActivity must be singleTop");
          i = 0;
        }
        if (localActivityInfo.taskAffinity.equals(str1))
        {
          Logger.e("Msdk: the taskAffinity of WXEntryActivity must be different from the PackageName of the game: " + str1);
          i = 0;
        }
        localIntent = new Intent();
        localIntent.setData(Uri.parse(WeGame.getInstance().wx_appid + "://"));
        if (!queryIntentFilter(localIntent, str2))
        {
          Logger.e("Msdk: WeiXin AppID for Initialiezed must be the same as configed in AndroidMenifest.xml");
          return false;
        }
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException)
      {
        localNameNotFoundException.printStackTrace();
        Logger.e("Msdk:  Lack of activity: " + str2);
        return false;
      }
      catch (ClassNotFoundException localClassNotFoundException)
      {
        localClassNotFoundException.printStackTrace();
        Logger.e("Msdk: WXEntryActivity.java must be put into package " + this.mActivity.getPackageName() + ".wxapi");
        return false;
      }
      localIntent.setAction("android.intent.action.VIEW");
      localIntent.addCategory("android.intent.category.DEFAULT");
    }
    while (queryIntentFilter(localIntent, str2));
    return false;
  }

  public void closeCheck()
  {
    this.needCheck = false;
  }

  public void openCheck()
  {
    this.needCheck = true;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.msdk.doctor.MsdkCheckConfig
 * JD-Core Version:    0.6.0
 */