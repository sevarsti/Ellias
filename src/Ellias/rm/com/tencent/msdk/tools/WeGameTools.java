package com.tencent.msdk.tools;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.tencent.msdk.WeGame;
import java.lang.reflect.Field;

public class WeGameTools
{
  public static String WXPAKAGENAME = "com.tencent.mm";

  public static boolean isInstalledApp(Context paramContext, String paramString)
  {
    try
    {
      PackageInfo localPackageInfo = paramContext.getPackageManager().getPackageInfo(paramString, 0);
      return localPackageInfo != null;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
    }
    return false;
  }

  public static boolean isPlatformInstalled(Context paramContext, int paramInt)
  {
    if (paramInt == WeGame.WXPLATID)
      return WXAPIFactory.createWXAPI(paramContext, WeGame.getInstance().wx_appid).isWXAppInstalled();
    if (paramInt == WeGame.QQPLATID)
      return isInstalledApp(paramContext, "com.tencent.mobileqq");
    return false;
  }

  public static boolean isPlatformSupportApi(Context paramContext, int paramInt)
  {
    if (paramInt == WeGame.WXPLATID)
      return WXAPIFactory.createWXAPI(paramContext, WeGame.getInstance().wx_appid).isWXAppSupportAPI();
    return paramInt == WeGame.QQPLATID;
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
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.msdk.tools.WeGameTools
 * JD-Core Version:    0.6.0
 */