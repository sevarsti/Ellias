package com.tencent.msdk.stat;

import android.app.Activity;
import com.tencent.msdk.api.WGPlatform;
import com.tencent.msdk.tools.Logger;
import java.util.ArrayList;
import java.util.HashMap;

public class Stat
{
  private BeaconHelper beaconHelper = new BeaconHelper();
  private MtaHelper mtaHelper = new MtaHelper();
  private String openId = "";

  public void enableCrashReport(boolean paramBoolean1, boolean paramBoolean2)
  {
    this.beaconHelper.enableCrashReport(paramBoolean1);
    this.mtaHelper.enableCrashReport(paramBoolean2);
  }

  public void init(Activity paramActivity, String paramString, boolean paramBoolean)
  {
    String str = WGPlatform.WGGetChannelId();
    Logger.d("ChannelID: " + str);
    this.beaconHelper.init(paramActivity, paramString, str, paramBoolean);
    this.mtaHelper.init(paramActivity, paramString, str, paramBoolean);
  }

  public void reportEvent(String paramString1, String paramString2, int paramInt, boolean paramBoolean)
  {
    Logger.d("String called");
    this.beaconHelper.reportEvent(paramString1, paramString2, paramInt, paramBoolean);
    this.mtaHelper.reportEvent(paramString1, paramString2, paramInt);
  }

  public void reportEvent(String paramString, HashMap<String, String> paramHashMap, int paramInt, boolean paramBoolean)
  {
    Logger.d("HashMap called");
    this.beaconHelper.reportEvent(paramString, paramHashMap, paramInt, paramBoolean);
    this.mtaHelper.reportEvent(paramString, paramHashMap, paramInt);
  }

  public boolean reportLogin(String paramString, int paramInt)
  {
    if ((paramString == null) || ("".equals(paramString)) || (paramInt == 0))
    {
      Logger.d("did not report login: " + paramString + ";" + paramInt);
      return false;
    }
    if (("".equals(this.openId)) || (!this.openId.equals(paramString)))
    {
      Logger.d("report login: " + paramString + ";" + paramInt);
      this.beaconHelper.onLogin(paramString, paramInt);
      this.mtaHelper.onLogin(paramString, paramInt);
      this.openId = paramString;
    }
    Logger.d("called");
    return false;
  }

  public void resetOpenId()
  {
    this.openId = "";
  }

  public void speedTest(ArrayList<String> paramArrayList)
  {
    Logger.d("Stat speedTest");
    this.beaconHelper.speedTest(paramArrayList);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.msdk.stat.Stat
 * JD-Core Version:    0.6.0
 */