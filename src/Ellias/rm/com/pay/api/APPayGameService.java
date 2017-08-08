package com.pay.api;

import com.pay.AndroidPay;
import com.pay.AndroidPay.APLaunchRootViewOption;
import com.pay.common.tool.APLog;
import com.pay.http.APHttpHandle;
import com.pay.http.APIPH5List;
import com.pay.http.APIPList;
import com.pay.http.APNetworkManager;
import com.pay.http.IAPHttpAnsObserver;
import com.pay.network.modle.APMpAns;
import com.pay.network.modle.APMpReq;
import com.pay.tool.APAppDataInterface;
import com.pay.tool.APDataReportManager;
import com.pay.tool.ApCrashReport;
import com.pay.ui.common.APUICommonMethod;
import java.util.HashMap;

public class APPayGameService
{
  public static final String ACCOUNT_TYPE_COMMON = "common";
  public static final String ACCOUNT_TYPE_SECURITY = "secrety";
  public static final int LOGINPLATFORM_MOBILEQQ = 2;
  public static final int LOGINPLATFORM_WECHAT = 1;
  public static final String PAY_CHANNEL_BANK = "bank";
  public static final String PAY_CHANNEL_WECHAT = "wechat";
  private static IAPPayGameServiceCallBack a = null;

  public static IAPPayGameServiceCallBack GetDelegate()
  {
    return a;
  }

  public static void LaunchMPSaveCurrencyView(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7, String paramString8, String paramString9, int paramInt, String paramString10, String paramString11, String paramString12, String paramString13)
  {
    AndroidPay.singleton().payGameInputNumberAndMarket(AndroidPay.APLaunchRootViewOption.APPayGameInputNumView, paramString1, paramString2, paramString3, paramString4, paramString5, paramString6, paramString7, paramString8, paramString9, false, paramInt, paramString10, paramString11, paramString12, paramString13);
  }

  public static void LaunchMPSaveGoodsView(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7, String paramString8, int paramInt, String paramString9, String paramString10, String paramString11, String paramString12)
  {
    AndroidPay.singleton().buyGoodsWithMarket(AndroidPay.APLaunchRootViewOption.APPayGameInputNumView, paramString1, paramString2, paramString3, paramString4, paramString5, paramString6, paramString7, paramString8, paramInt, paramString9, paramString10, paramString11, paramString12);
  }

  public static void LaunchMp(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7, IAPHttpAnsObserver paramIAPHttpAnsObserver)
  {
    APMpReq localAPMpReq = new APMpReq(paramString1, paramString2, paramString3, paramString4, paramString5, paramString6, paramString7);
    localAPMpReq.setHttpAns(new APMpAns(APHttpHandle.getIntanceHandel(), paramIAPHttpAnsObserver, new HashMap(), "mp"));
    localAPMpReq.starService();
  }

  public static void LaunchSaveCurrencyView(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7, String paramString8, int paramInt)
  {
    AndroidPay.singleton().payGameListNumber(AndroidPay.APLaunchRootViewOption.APPayGameListNumView, paramString1, paramString2, paramString3, paramString4, paramString5, paramString6, paramString7, paramString8, paramInt);
  }

  public static void LaunchSaveCurrencyView(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7, String paramString8, String paramString9, boolean paramBoolean, int paramInt)
  {
    AndroidPay.singleton().payGameInputNumberWithoutMarket(AndroidPay.APLaunchRootViewOption.APPayGameInputNumView, paramString1, paramString2, paramString3, paramString4, paramString5, paramString6, paramString7, paramString8, paramString9, paramBoolean, paramInt);
  }

  public static void LaunchSaveGoodsView(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7, String paramString8, int paramInt)
  {
    AndroidPay.singleton().buyGoodsWithoutMarket(AndroidPay.APLaunchRootViewOption.APPayGameInputNumView, paramString1, paramString2, paramString3, paramString4, paramString5, paramString6, paramString7, paramString8, paramInt);
  }

  public static void LaunchSaveGoodsViewWithoutToken(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7, String paramString8, String paramString9, boolean paramBoolean, String paramString10)
  {
    AndroidPay.singleton().buyGoods(AndroidPay.APLaunchRootViewOption.APPayGameInputNumView, paramString1, paramString2, paramString3, paramString4, paramString5, paramString6, paramString7, paramString8, paramString9, paramBoolean, paramString10);
  }

  public static void PayGameServiceCallBack(APPayResponseInfo paramAPPayResponseInfo)
  {
    APLog.i("APPayGameService", "PayGameServiceCallBack");
    try
    {
      APLog.i("getDeviceInfo==", APAppDataInterface.singleton().getDeviceInfo());
      APDataReportManager.getInstance().insertData("sdk.deviceinfo", -1, null, null, APAppDataInterface.singleton().getDeviceInfo());
      APNetworkManager.getInstance().dataReport(new a());
      if (a != null)
        a.PayGameServiceCallBack(paramAPPayResponseInfo);
    }
    catch (Exception localException1)
    {
      try
      {
        APIPList.getInstance().close();
        APIPH5List.getInstance().close();
        label75: release();
        APUICommonMethod.releaseActivityStack();
        APUICommonMethod.releaseLoadingMap();
        return;
        localException1 = localException1;
        localException1.printStackTrace();
      }
      catch (Exception localException2)
      {
        break label75;
      }
    }
  }

  public static void SetDelegate(IAPPayGameServiceCallBack paramIAPPayGameServiceCallBack)
  {
    a = paramIAPPayGameServiceCallBack;
  }

  public static void SetNeedReloginInSDK(boolean paramBoolean)
  {
    APAppDataInterface.singleton().setReloginInSDK(false);
  }

  public static void release()
  {
    a = null;
  }

  public static void reportCrashApLog(Throwable paramThrowable)
  {
    ApCrashReport.reportCrashApLog(paramThrowable);
  }

  public static void retLogin()
  {
    APLog.i("APPayGameService", "retLogin");
    if (a != null)
      a.PayGameNeedLogin();
    release();
    APUICommonMethod.releaseActivityStack();
    APUICommonMethod.releaseLoadingMap();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.api.APPayGameService
 * JD-Core Version:    0.6.0
 */