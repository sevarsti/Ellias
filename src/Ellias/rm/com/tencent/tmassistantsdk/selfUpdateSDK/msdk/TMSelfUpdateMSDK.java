package com.tencent.tmassistantsdk.selfUpdateSDK.msdk;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import com.tencent.tmassistantsdk.g.l;
import com.tencent.tmassistantsdk.openSDK.g;
import com.tencent.tmassistantsdk.selfUpdateSDK.ITMSelfUpdateSDKListener;
import com.tencent.tmassistantsdk.selfUpdateSDK.TMSelfUpdateSDK;

public class TMSelfUpdateMSDK extends TMSelfUpdateSDK
{
  private static TMSelfUpdateMSDK mInstance;

  public static String about()
  {
    return "TMAssistantSDK_MSDK_SelfUpdateSDK_20140521121338_release_36092";
  }

  public static TMSelfUpdateMSDK getInstance()
  {
    monitorenter;
    try
    {
      if (mInstance == null)
        mInstance = new TMSelfUpdateMSDK();
      TMSelfUpdateMSDK localTMSelfUpdateMSDK = mInstance;
      return localTMSelfUpdateMSDK;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public void afterPatchNewApk(String paramString)
  {
    try
    {
      PackageInfo localPackageInfo = this.mContext.getPackageManager().getPackageInfo(this.hostPackageName, 0);
      if (localPackageInfo != null)
      {
        String str = localPackageInfo.applicationInfo.sourceDir;
        boolean bool = a.a(str, paramString);
        l.b("SelfUpdateSDK", "writeOldCommentToNewFile; result=" + bool + "; packageName=" + this.hostPackageName + "; oldApk=" + str + "; newGenApkPath=" + paramString);
      }
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }

  public void initTMSelfUpdateSDK(Context paramContext, ITMSelfUpdateSDKListener paramITMSelfUpdateSDKListener)
  {
    super.initTMSelfUpdateSDK(paramContext, "992183", null, "ANDROIDGAME.YYB.UPDATE", "ANDROIDGAME.NEWYYB.UPDATE", paramITMSelfUpdateSDKListener);
  }

  public void onResume(Context paramContext)
  {
    g localg = new g();
    localg.k = "4";
    onResume(paramContext, localg, true, true);
  }

  public int startSaveUpdate(Context paramContext)
  {
    g localg = new g();
    localg.k = "4";
    return startSaveUpdate(paramContext, localg, true, true);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.tmassistantsdk.selfUpdateSDK.msdk.TMSelfUpdateMSDK
 * JD-Core Version:    0.6.0
 */