package com.tencent.msdk.stat;

import android.app.Activity;
import com.tencent.beacon.event.UserAction;
import com.tencent.feedback.eup.CrashHandleListener;
import com.tencent.feedback.eup.CrashReport;
import com.tencent.feedback.eup.CrashStrategyBean;
import com.tencent.msdk.WeGame;
import com.tencent.msdk.WeGameNotifyGame;
import com.tencent.msdk.api.WGPlatform;
import com.tencent.msdk.tools.Logger;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class BeaconHelper
{
  private static final long MAX_SAVE_BUSINESS_LOG_LEN = 9216L;
  private static final String MSDK_EVENT_HEADER = "MSDK_";

  private String getCurrentLoginPlatform(int paramInt)
  {
    if (paramInt == WeGame.WXPLATID)
      return "Weixin";
    if (paramInt == WeGame.QQPLATID)
      return "QQ";
    return "";
  }

  public static void reportMSDKEvent(String paramString, long paramLong, boolean paramBoolean1, Map<String, String> paramMap, boolean paramBoolean2)
  {
    if ((paramString == null) || (paramString.trim().equals("")))
      return;
    String str = "MSDK_" + paramString;
    long l = System.currentTimeMillis() - paramLong;
    if (paramMap == null)
      paramMap = new HashMap();
    paramMap.put("msdkVersion", WGPlatform.WGGetVersion());
    if (paramBoolean1)
      Logger.d(">>>event:" + str + ",wattingTime:" + l + ",flag:" + paramBoolean1);
    while (true)
    {
      UserAction.onUserAction(str, paramBoolean1, l, -1L, paramMap, paramBoolean2);
      return;
      Logger.d(">>>event:" + str + ",wattingTime:" + l + ",flag:" + paramBoolean1 + ",logic_error:" + (String)paramMap.get("msdk_logic_error") + ",errcode:" + (String)paramMap.get("param_FailCode"));
    }
  }

  public void enableCrashReport(boolean paramBoolean)
  {
    Logger.d("flag: " + paramBoolean);
    CrashReport.setCrashReportAble(paramBoolean);
    CrashReport.setNativeCrashReportAble(paramBoolean);
  }

  protected CrashHandleListener getCrashHandleListener()
  {
    return new CrashHandleListener()
    {
      public byte[] getCrashExtraData()
      {
        return null;
      }

      public String getCrashExtraMessage()
      {
        String str = WeGameNotifyGame.getInstance().OnCrashExtMessageNotify(0, "upload extra crash message");
        Logger.d("getCrashExtraMessage, extMsg:" + str);
        if (str == null)
          return null;
        byte[] arrayOfByte = str.getBytes();
        long l = arrayOfByte.length;
        if (l > 9216L)
          str = new String(arrayOfByte, (int)(l - 9216L), 9216);
        return str;
      }

      public boolean onCrashFinished(Thread paramThread, Throwable paramThrowable)
      {
        return true;
      }

      public void onCrashHappen(Thread paramThread, Throwable paramThrowable)
      {
      }

      public void onNativeCrash(int paramInt1, int paramInt2, String paramString)
      {
      }
    };
  }

  public void init(Activity paramActivity, String paramString1, String paramString2, boolean paramBoolean)
  {
    try
    {
      UserAction.setAppKey(paramActivity, paramString1);
      UserAction.setLogAble(paramBoolean, false);
      UserAction.initUserAction(paramActivity);
      UserAction.setAutoLaunchEventUsable(true);
      UserAction.setChannelID(paramString2);
      CrashStrategyBean localCrashStrategyBean = new CrashStrategyBean();
      localCrashStrategyBean.setMerged(true);
      localCrashStrategyBean.setMaxStoredNum(10);
      localCrashStrategyBean.setMaxUploadNum_GPRS(1);
      localCrashStrategyBean.setMaxUploadNum_Wifi(3);
      localCrashStrategyBean.setMaxLogRow(100);
      CrashReport.initCrashReport(paramActivity, getCrashHandleListener(), null, true, localCrashStrategyBean);
      CrashReport.initNativeCrashReport(paramActivity, paramActivity.getDir("tomb", 0).getAbsolutePath(), paramBoolean);
      return;
    }
    catch (Exception localException)
    {
      while (true)
        localException.printStackTrace();
    }
  }

  public void onLogin(String paramString, int paramInt)
  {
    long l1 = System.currentTimeMillis();
    HashMap localHashMap = new HashMap();
    localHashMap.put("loginType", getCurrentLoginPlatform(paramInt));
    localHashMap.put("msdkVersion", WGPlatform.WGGetVersion());
    localHashMap.put("regChannel", WGPlatform.WGGetRegisterChannelId());
    localHashMap.put("openId", paramString);
    long l2 = System.currentTimeMillis() - l1;
    Logger.d(paramString);
    UserAction.setUserID(paramString);
    UserAction.onUserAction("wgLogin", true, l2, 1000L, localHashMap, true);
  }

  public void reportEvent(String paramString, long paramLong, boolean paramBoolean1, Map<String, String> paramMap, boolean paramBoolean2)
  {
    long l = System.currentTimeMillis() - paramLong;
    Logger.d(">>>event:" + paramString + ",wattingTime:" + l + ", flag:" + paramBoolean1);
    UserAction.onUserAction(paramString, paramBoolean1, l, -1L, paramMap, paramBoolean2);
  }

  public void reportEvent(String paramString1, String paramString2, int paramInt, boolean paramBoolean)
  {
    HashMap localHashMap = new HashMap();
    if (paramString2 == null)
      paramString2 = "";
    localHashMap.put("evtBody", paramString2);
    localHashMap.put("loginType", getCurrentLoginPlatform(paramInt));
    Logger.d("Stat reportEvent name:" + paramString1 + "; body: " + paramString2);
    UserAction.onUserAction(paramString1, true, 0L, -1L, localHashMap, paramBoolean);
  }

  public void reportEvent(String paramString, HashMap<String, String> paramHashMap, int paramInt, boolean paramBoolean)
  {
    Logger.d("Stat reportEvent name:" + paramString + "; body: " + paramHashMap.toString());
    paramHashMap.put("loginType", getCurrentLoginPlatform(paramInt));
    UserAction.onUserAction(paramString, true, 0L, -1L, paramHashMap, paramBoolean);
  }

  public void speedTest(ArrayList<String> paramArrayList)
  {
    ArrayList localArrayList1 = new ArrayList();
    ArrayList localArrayList2 = new ArrayList();
    Iterator localIterator = paramArrayList.iterator();
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      if ((str != null) && (str.matches("(\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}):(\\d{1,5})")))
      {
        Logger.d("IP: " + str);
        localArrayList1.add(str);
        continue;
      }
      Logger.d("Domain: " + str);
      localArrayList2.add(str);
    }
    if (localArrayList1.size() > 0)
      UserAction.testSpeedIp(localArrayList1);
    do
      return;
    while (localArrayList2.size() <= 0);
    UserAction.testSpeedDomain(localArrayList2);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.msdk.stat.BeaconHelper
 * JD-Core Version:    0.6.0
 */