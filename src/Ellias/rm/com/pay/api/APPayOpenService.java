package com.pay.api;

import android.text.TextUtils;
import com.pay.AndroidPay;
import com.pay.AndroidPay.APLaunchRootViewOption;
import com.pay.common.tool.APLog;
import com.pay.http.APIPH5List;
import com.pay.http.APIPList;
import com.pay.http.APNetworkManager;
import com.pay.tool.APAppDataInterface;
import com.pay.tool.APDataReportManager;
import com.pay.tool.APMonthDataInterface;
import com.pay.ui.common.APUICommonMethod;

public class APPayOpenService
{
  private static IAPPayOpenServiceCallBack a = null;

  public static IAPPayOpenServiceCallBack GetDelegate()
  {
    return a;
  }

  public static void LaunchOpenServiceView(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7, String paramString8, String paramString9, int paramInt, String paramString10)
  {
    AndroidPay.singleton().payMonth(AndroidPay.APLaunchRootViewOption.APPayGameListNumView, paramString1, paramString2, paramString3, paramString4, paramString5, paramString6, paramString7, paramString8, paramString9, paramInt, null, "", true, paramString10);
  }

  public static void LaunchOpenServiceView(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7, String paramString8, String paramString9, int paramInt, String paramString10, String paramString11, boolean paramBoolean, String paramString12)
  {
    if (TextUtils.isEmpty(paramString10))
    {
      AndroidPay.singleton().payMonth(AndroidPay.APLaunchRootViewOption.APPayGameListNumView, paramString1, paramString2, paramString3, paramString4, paramString5, paramString6, paramString7, paramString8, paramString9, paramInt, null, null, paramBoolean, paramString12);
      return;
    }
    AndroidPay.singleton().payMonth(AndroidPay.APLaunchRootViewOption.APPayGameInputNumView, paramString1, paramString2, paramString3, paramString4, paramString5, paramString6, paramString7, paramString8, paramString9, paramInt, paramString10, paramString11, paramBoolean, paramString12);
  }

  public static void LaunchOpenServiceView(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7, String paramString8, String paramString9, int paramInt, String paramString10, boolean paramBoolean, String paramString11)
  {
    AndroidPay.singleton().payMonth(AndroidPay.APLaunchRootViewOption.APPayGameInputNumView, paramString1, paramString2, paramString3, paramString4, paramString5, paramString6, paramString7, paramString8, paramString9, paramInt, paramString10, "", paramBoolean, paramString11);
  }

  public static void PayOpenServiceCallBack(APPayResponseInfo paramAPPayResponseInfo)
  {
    APLog.i("APPayOpenService", "PayOpenServiceCallBack");
    try
    {
      APLog.i("getDeviceInfo==", APAppDataInterface.singleton().getDeviceInfo());
      APDataReportManager.getInstance().insertData("sdk.deviceinfo", -1, null, null, APAppDataInterface.singleton().getDeviceInfo());
      APNetworkManager.getInstance().dataReport(new b());
      if (a != null)
        a.PayOpenServiceCallBack(paramAPPayResponseInfo);
      APMonthDataInterface.singleton().setToUpGrade("0");
    }
    catch (Exception localException1)
    {
      try
      {
        APIPList.getInstance().close();
        APIPH5List.getInstance().close();
        label83: release();
        APUICommonMethod.releaseActivityStack();
        APUICommonMethod.releaseLoadingMap();
        return;
        localException1 = localException1;
        localException1.printStackTrace();
      }
      catch (Exception localException2)
      {
        break label83;
      }
    }
  }

  public static void SetDelegate(IAPPayOpenServiceCallBack paramIAPPayOpenServiceCallBack)
  {
    a = paramIAPPayOpenServiceCallBack;
  }

  public static void SetNeedReloginInSDK(boolean paramBoolean)
  {
    APAppDataInterface.singleton().setReloginInSDK(paramBoolean);
  }

  public static void release()
  {
    a = null;
  }

  public static void retLogin()
  {
    APLog.i("APPayOpenService", "retLogin");
    if (a != null)
      a.PayOpenServiceNeedLogin();
    release();
    APUICommonMethod.releaseActivityStack();
    APUICommonMethod.releaseLoadingMap();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.api.APPayOpenService
 * JD-Core Version:    0.6.0
 */