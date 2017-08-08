package com.tencent.msdk.pf;

import android.app.Activity;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.util.Log;
import com.tencent.msdk.WeGame;
import com.tencent.msdk.api.LoginRet;
import com.tencent.msdk.db.LoginInfoManager;
import com.tencent.msdk.handle.MsdkThreadManager;
import com.tencent.msdk.tools.CommonUtil;
import com.tencent.msdk.tools.Logger;
import com.tencent.msdk.tools.T;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class WGPfManager
{
  private static final String DEFAULT_CHANNEL = "00000000";
  public static final String WG_3366_PLATFORM_ID = "3366_m";
  public static final String WG_DEFAULT_PLATFORM_ID = "desktop_m";
  public static final String WG_MOBILE_PLATFORM_ID = "mobile";
  public static final String WG_MYAPP_PLATFORM_ID = "myapp_m";
  public static final String WG_QB_PLATFORM_ID = "qqbrowser_m";
  public static final String WG_QQ_PLATFORM_ID = "qq_m";
  public static final String WG_QZONE_PLATFORM_ID = "qzone_m";
  public static final String WG_WX_PLATFORM_ID = "wechat";
  private static volatile WGPfManager instance = null;
  private String channel = "";
  private String pf = "";
  private String pfKey = "";
  private String platformId = "desktop_m";
  private String regChannelId = "";

  private WGPfManager()
  {
    LoginRet localLoginRet = LoginInfoManager.getInstance().getLastLoginUserInfo();
    if ((localLoginRet.pf != null) && (!localLoginRet.pf.equals("openmobile_android")))
    {
      this.pf = localLoginRet.pf;
      this.pfKey = localLoginRet.pf_key;
    }
    Logger.d("init: pf = " + this.pf + "pfKey = " + this.pfKey);
  }

  private String getConfigChannelId()
  {
    String str1 = WeGame.getInstance().getActivity().getPackageCodePath();
    try
    {
      String str3 = ApkExternalInfoTool.readChannelId(new File(str1));
      Logger.d("Comment: " + str3);
      boolean bool = CommonUtil.ckIsEmpty(str3);
      if (!bool)
        return str3;
    }
    catch (IOException localIOException)
    {
      localIOException.printStackTrace();
      Logger.d("Read apk file for channelId Error");
      String str2 = readChannelFromIni();
      if (!CommonUtil.ckIsEmpty(str2))
        return str2;
    }
    return "00000000";
  }

  public static WGPfManager getInstance()
  {
    if (instance == null)
      monitorenter;
    try
    {
      if (instance == null)
        instance = new WGPfManager();
      return instance;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  private String readChannelFromIni()
  {
    Activity localActivity = WeGame.getInstance().getActivity();
    try
    {
      InputStream localInputStream = localActivity.getResources().getAssets().open("channel.ini");
      Properties localProperties = new Properties();
      localProperties.load(localInputStream);
      String str = localProperties.getProperty("CHANNEL", "");
      return str;
    }
    catch (IOException localIOException)
    {
      localIOException.printStackTrace();
      Log.e("WeGame", "CHANNEL ID ERROR");
    }
    return "";
  }

  public void clearPfAndPfKey()
  {
    this.pf = "";
    this.pfKey = "";
  }

  public String getChannelId()
  {
    Logger.d("getChannelId:  = " + this.channel);
    return this.channel;
  }

  public String getPf(String paramString)
  {
    LoginRet localLoginRet = LoginInfoManager.getInstance().getLastLoginUserInfo();
    Logger.d("getPf: = " + localLoginRet.pf);
    if (T.ckIsEmpty(paramString))
      return localLoginRet.pf;
    if (paramString.startsWith("-"))
      Logger.w("gameCustomInfo should not start with '-'");
    return localLoginRet.pf + "-" + paramString;
  }

  public String getPfKey()
  {
    LoginRet localLoginRet = LoginInfoManager.getInstance().getLastLoginUserInfo();
    Logger.d("getPfKey:  = " + localLoginRet.pf_key);
    return localLoginRet.pf_key;
  }

  public String getPlatformId()
  {
    Logger.d("getPlatformId:  = " + this.platformId);
    return this.platformId;
  }

  public String getRegChannelId()
  {
    String str = getInstance().getPf("");
    if (T.ckIsEmpty(str))
      return "00000000";
    String[] arrayOfString = str.split("-");
    if (arrayOfString.length < 2)
      return "00000000";
    if (T.ckIsEmpty(arrayOfString[1]))
      return "00000000";
    Logger.d("RegChannel: " + arrayOfString[1]);
    return arrayOfString[1];
  }

  public void setChannelId(String paramString)
  {
    if (paramString == null);
    do
      return;
    while (paramString.length() >= 64);
    Logger.d("setChannelId:  = " + paramString);
    this.channel = paramString;
  }

  public void setPlatformId(String paramString)
  {
    if (paramString == null);
    do
    {
      return;
      Logger.d("setPlatformId: = " + paramString);
      if (!this.platformId.equals("desktop_m"))
        continue;
      this.platformId = paramString;
    }
    while (!this.platformId.equals("qzone_m"));
    Logger.d("PfKeyRequestMng.getInstance().getPfKeyReq PlatformId = " + paramString);
    MsdkThreadManager.getInstance().getPfKeyReq(0);
  }

  public void setRegChannelId(String paramString)
  {
    if (paramString == null)
      return;
    Logger.d("setRegChannelId:  = " + paramString);
    this.regChannelId = paramString;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.msdk.pf.WGPfManager
 * JD-Core Version:    0.6.0
 */