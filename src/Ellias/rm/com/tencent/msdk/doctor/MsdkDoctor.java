package com.tencent.msdk.doctor;

import android.app.Activity;
import android.app.Application;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.net.Uri;
import com.tencent.msdk.WeGame;
import com.tencent.msdk.api.WGPlatform;
import com.tencent.msdk.config.ConfigManager;
import com.tencent.msdk.doctor.checklist.Global;
import com.tencent.msdk.doctor.checklist.Push;
import com.tencent.msdk.doctor.checklist.QQ;
import com.tencent.msdk.doctor.checklist.WX;
import com.tencent.msdk.tools.Logger;
import com.tencent.msdk.tools.SharedPreferencesTool;
import com.tencent.msdk.tools.T;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class MsdkDoctor
{
  private ArrayList<CheckBase> checkList = new ArrayList();
  private String checkedKey = "configChecked";
  private Activity ctx;

  public MsdkDoctor(Activity paramActivity)
  {
    this.ctx = paramActivity;
    this.checkList.add(new Global(paramActivity));
    this.checkList.add(new QQ(paramActivity));
    this.checkList.add(new WX(paramActivity));
    this.checkList.add(new Push(paramActivity));
  }

  public ArrayList<String> checkAll()
  {
    ArrayList localArrayList = new ArrayList();
    for (int i = 0; i < this.checkList.size(); i++)
    {
      if (this.checkList.get(i) == null)
        continue;
      localArrayList.addAll(((CheckBase)this.checkList.get(i)).check());
    }
    Logger.w("Check Result: " + localArrayList.size());
    return localArrayList;
  }

  public boolean checkConfig()
  {
    if (SharedPreferencesTool.getBoolean(this.ctx, this.checkedKey, false))
      return true;
    WGPlatform.WGLogPlatformSDKVersion();
    boolean bool1 = checkWxConfig();
    boolean bool2 = checkQQConfig();
    boolean bool3 = checkMsdkConfig();
    boolean bool4 = checkOtherConfig();
    if ((bool1) && (bool2) && (bool3) && (bool4))
    {
      SharedPreferencesTool.putBoolean(this.ctx, this.checkedKey, true);
      return true;
    }
    Logger.e("Config Error, Please correct all config error before go on");
    return false;
  }

  public boolean checkMsdkConfig()
  {
    int i = 1;
    try
    {
      List localList = Arrays.asList(this.ctx.getResources().getAssets().list(""));
      if ((localList != null) && (localList.contains("msdkconfig.ini")))
      {
        j = 1;
        String str = ConfigManager.getApiDomain(this.ctx);
        if (T.ckIsEmpty(str))
        {
          i = 0;
          Logger.e("Msdk: MSDK_URL is not set properly in assets/msdkconfig.ini");
          i = 0;
          break label126;
        }
        if (str.charAt(-1 + str.length()) != '/')
          break label126;
        i = 0;
        Logger.e("Msdk: MSDK_URL in msdkconfig.ini should not end with '/', maybe you should delete the '/' ");
        i = 0;
      }
    }
    catch (IOException localIOException)
    {
      Logger.e("Msdk: msdkconfig.ini file must be put into assets dir");
      localIOException.printStackTrace();
      j = 0;
    }
    Logger.e("Msdk: msdkconfig.ini must be put into assets dir");
    int j = 0;
    label126: 
    while ((j == 0) || (i == 0))
      return false;
    return true;
  }

  public boolean checkOtherConfig()
  {
    return true;
  }

  public boolean checkQQConfig()
  {
    if (T.ckIsEmpty(WeGame.getInstance().qq_appid));
    int i;
    int j;
    do
    {
      return true;
      i = 1;
      j = 1;
      String str = this.ctx.getPackageName();
      try
      {
        if (this.ctx.getPackageManager().getActivityInfo(new ComponentName(str, "com.tencent.tauth.AuthActivity"), 128) == null)
        {
          i = 0;
        }
        else
        {
          Intent localIntent = new Intent();
          localIntent.setData(Uri.parse("tencent" + WeGame.getInstance().qq_appid + "://"));
          List localList = this.ctx.getPackageManager().queryIntentActivities(localIntent, 65536);
          j = 0;
          Iterator localIterator = localList.iterator();
          while (true)
          {
            boolean bool = localIterator.hasNext();
            j = 0;
            if (!bool)
              break;
            if (!"com.tencent.tauth.AuthActivity".equals(((ResolveInfo)localIterator.next()).activityInfo.name))
              continue;
            j = 1;
          }
          if (j != 0)
            continue;
          Logger.e("QQ AppID for Initialiezed must be the same as configed in AndroidMenifest.xml ");
        }
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException)
      {
        localNameNotFoundException.printStackTrace();
      }
      catch (SecurityException localSecurityException)
      {
        localSecurityException.printStackTrace();
      }
    }
    while ((i != 0) && (j != 0));
    return false;
  }

  public boolean checkWxConfig()
  {
    if (T.ckIsEmpty(WeGame.getInstance().wx_appid))
      return true;
    int i = 1;
    int j = 1;
    int k = 1;
    int m = 1;
    String str1 = this.ctx.getApplication().getPackageName();
    String str2 = str1 + ".wxapi.WXEntryActivity";
    try
    {
      Class.forName(str2);
      i = 1;
      ActivityInfo localActivityInfo = this.ctx.getPackageManager().getActivityInfo(new ComponentName(str1, str2), 128);
      if (localActivityInfo == null)
      {
        i = 0;
      }
      else
      {
        i = 1;
        if (localActivityInfo.launchMode != 1)
        {
          j = 0;
          Logger.e("LauchMode of WXEntryActivity should be SingleTop");
        }
        if (str1.equals(localActivityInfo.taskAffinity))
        {
          k = 0;
          Logger.e("taskAffinity of WXEntryActivity must different from you app packageName");
        }
        Intent localIntent = new Intent();
        localIntent.setData(Uri.parse(WeGame.getInstance().wx_appid + "://"));
        List localList = this.ctx.getPackageManager().queryIntentActivities(localIntent, 65536);
        m = 0;
        Iterator localIterator = localList.iterator();
        while (true)
        {
          boolean bool = localIterator.hasNext();
          m = 0;
          if (!bool)
            break;
          if (!str2.equals(((ResolveInfo)localIterator.next()).activityInfo.name))
            continue;
          m = 1;
        }
        if (m == 0)
          Logger.e("Weixin AppID for Initialiezed must be the same as configed in AndroidMenifest.xml ");
      }
    }
    catch (ClassNotFoundException localClassNotFoundException)
    {
      Logger.e("Weixin: WXEntryActivity.java must be put into package " + this.ctx.getApplication().getPackageName() + ".wxapi");
      localClassNotFoundException.printStackTrace();
      i = 0;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      Logger.e("Weixin: WXEntryActivity.java must be put into package " + this.ctx.getApplication().getPackageName() + ".wxapi");
      localNameNotFoundException.printStackTrace();
    }
    do
      return false;
    while ((i == 0) || (j == 0) || (k == 0) || (m == 0));
    return true;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.msdk.doctor.MsdkDoctor
 * JD-Core Version:    0.6.0
 */