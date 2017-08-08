package com.tencent.msdk.stat;

import android.app.Activity;
import com.tencent.msdk.WeGame;
import com.tencent.msdk.api.WGPlatform;
import com.tencent.msdk.tools.Logger;
import com.tencent.stat.StatConfig;
import com.tencent.stat.StatService;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

public class MtaHelper
{
  private Activity context;

  private String getCurrentLoginPlatform(int paramInt)
  {
    if (paramInt == WeGame.WXPLATID)
      return "Weixin";
    if (paramInt == WeGame.QQPLATID)
      return "QQ";
    return "";
  }

  public void enableCrashReport(boolean paramBoolean)
  {
    StatConfig.setAutoExceptionCaught(paramBoolean);
  }

  public void init(Activity paramActivity, String paramString1, String paramString2, boolean paramBoolean)
  {
    this.context = paramActivity;
    String str = "Aqc" + paramString1;
    StatConfig.setInstallChannel(WGPlatform.WGGetChannelId());
    StatConfig.setDebugEnable(paramBoolean);
    StatService.startStatService(this.context, str, "1.6.2");
  }

  public void onLogin(String paramString, int paramInt)
  {
    Logger.d("MtaHelper login");
    Properties localProperties = new Properties();
    localProperties.put("loginType", getCurrentLoginPlatform(paramInt));
    localProperties.put("msdkVersion", WGPlatform.WGGetVersion());
    localProperties.put("regChannel", WGPlatform.WGGetRegisterChannelId());
    localProperties.put("openId", paramString);
    StatService.trackCustomKVEvent(this.context, "wgLogin", localProperties);
    StatService.reportQQ(this.context, paramString);
  }

  public void reportEvent(String paramString1, String paramString2, int paramInt)
  {
    Logger.d("MtaHelper reportEvent  name:" + paramString1 + "; body:" + paramString2);
    Properties localProperties = new Properties();
    if (paramString2 == null)
      paramString2 = "";
    localProperties.put("loginType", getCurrentLoginPlatform(paramInt));
    localProperties.put("evtBody", paramString2);
    StatService.trackCustomKVEvent(this.context, paramString1, localProperties);
  }

  public void reportEvent(String paramString, HashMap<String, String> paramHashMap, int paramInt)
  {
    Properties localProperties = new Properties();
    Iterator localIterator = paramHashMap.entrySet().iterator();
    while (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      localProperties.put(((String)localEntry.getKey()).toString(), ((String)localEntry.getValue()).toString());
    }
    Logger.d("MtaHelper reportEvent  name:" + paramString + "; body:" + localProperties.toString());
    localProperties.put("loginType", getCurrentLoginPlatform(paramInt));
    StatService.trackCustomKVEvent(this.context, paramString, localProperties);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.msdk.stat.MtaHelper
 * JD-Core Version:    0.6.0
 */