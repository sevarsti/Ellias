package com.tencent.msdk.doctor.checklist;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import com.tencent.msdk.WeGame;
import com.tencent.msdk.doctor.CheckBase;
import com.tencent.msdk.tools.T;
import java.util.ArrayList;

public class WX extends CheckBase
{
  public WX(Activity paramActivity)
  {
    super(paramActivity);
  }

  public ArrayList<String> check()
  {
    ArrayList localArrayList = new ArrayList();
    if (T.ckIsEmpty(WeGame.getInstance().wx_appid))
      localArrayList.add("Msdk: Missing WeiXin AppID");
    if (T.ckIsEmpty(WeGame.getInstance().wxAppKey))
      localArrayList.add("Msdk: Missing WX AppKey");
    String str1 = this.mContext.getPackageName();
    String str2 = str1 + ".wxapi.WXEntryActivity";
    try
    {
      Class.forName(str2);
      ActivityInfo localActivityInfo = this.mContext.getPackageManager().getActivityInfo(new ComponentName(str1, str2), 128);
      if ((0x20 & localActivityInfo.flags) != 32)
        localArrayList.add("Msdk: the excludeFromRecents of WXEntryActivity must be true");
      if (localActivityInfo.exported != true)
        localArrayList.add("Msdk: the exported of WXEntryActivity must be true");
      if (localActivityInfo.launchMode != 1)
        localArrayList.add("Msdk: the launchMose of WXEntryActivity must be singleTop");
      if (localActivityInfo.taskAffinity.equals(str1))
        localArrayList.add("Msdk: the taskAffinity of WXEntryActivity must be different from the PackageName of the game: " + str1);
      Intent localIntent = new Intent();
      localIntent.setData(Uri.parse(WeGame.getInstance().wx_appid + "://"));
      if (!queryIntentFilter(localIntent, str2))
        localArrayList.add("Msdk: WeiXin AppID for Initialiezed must be the same as configed in AndroidMenifest.xml");
      localIntent.setAction("android.intent.action.VIEW");
      localIntent.addCategory("android.intent.category.DEFAULT");
      if (!queryIntentFilter(localIntent, str2))
        localArrayList.add("Some error in WXEntryActivity defined in AndroidManifest.xml");
      return localArrayList;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      localNameNotFoundException.printStackTrace();
      localArrayList.add("Msdk:  Lack of activity: " + str2);
      return localArrayList;
    }
    catch (ClassNotFoundException localClassNotFoundException)
    {
      localClassNotFoundException.printStackTrace();
      localArrayList.add("Msdk: WXEntryActivity.java must be put into package " + this.mContext.getPackageName() + ".wxapi");
    }
    return localArrayList;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.msdk.doctor.checklist.WX
 * JD-Core Version:    0.6.0
 */