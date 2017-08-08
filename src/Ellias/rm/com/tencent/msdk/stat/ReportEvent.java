package com.tencent.msdk.stat;

import com.tencent.beacon.event.UserAction;
import com.tencent.msdk.api.LoginRet;
import com.tencent.msdk.db.LoginInfoManager;
import com.tencent.msdk.tools.Logger;
import java.util.HashMap;

public class ReportEvent
{
  public static String sEventHead;
  public static long sGameStart;
  public static boolean sReportEvent = true;

  static
  {
    sEventHead = "MSDK";
    sGameStart = 0L;
  }

  public static void BaseReportEvent(String paramString, eEVENT_TYPE parameEVENT_TYPE, HashMap<String, String> paramHashMap)
  {
    if (paramHashMap == null)
      paramHashMap = new HashMap();
    String str = "MSDK_" + paramString + "_" + parameEVENT_TYPE.val();
    Logger.d("Start reportEvent name:" + str);
    UserAction.onUserAction(str, true, 0L, -1L, paramHashMap, true);
  }

  public static void ReportBasicClickEvent(eEVENT_TYPE parameEVENT_TYPE)
  {
    if (sReportEvent)
      BaseReportEvent("BASIC", parameEVENT_TYPE, null);
  }

  public static void ReportBasicValue(eEVENT_TYPE parameEVENT_TYPE, String paramString)
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("key", paramString);
    BaseReportEvent("BASIC", parameEVENT_TYPE, localHashMap);
  }

  public static void ReportGameFinished()
  {
    if (sReportEvent)
    {
      long l = (System.currentTimeMillis() / 1000L - sGameStart) / 60L;
      if (l > 200L)
        l = 200L;
      ReportBasicValue(eEVENT_TYPE.eEVENT_BASIC_GAMETIME, String.valueOf(l));
    }
  }

  public static void ReportNoticeEvent(eEVENT_TYPE parameEVENT_TYPE, String paramString)
  {
    HashMap localHashMap = new HashMap();
    LoginRet localLoginRet = LoginInfoManager.getInstance().getLastLoginUserInfo();
    localHashMap.put("msgid", String.valueOf(paramString));
    localHashMap.put("openid", localLoginRet.open_id);
    localHashMap.put("accountType", String.valueOf(localLoginRet.platform));
    BaseReportEvent("NOTICE", parameEVENT_TYPE, localHashMap);
  }

  public static void ReportPicLength(long paramLong)
  {
    if (sReportEvent)
    {
      long l = paramLong / 51200L;
      if (l > 61L)
        l = 62L;
      ReportBasicValue(eEVENT_TYPE.eEVENT_BASIC_PICLENGTH, String.valueOf(l));
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.msdk.stat.ReportEvent
 * JD-Core Version:    0.6.0
 */