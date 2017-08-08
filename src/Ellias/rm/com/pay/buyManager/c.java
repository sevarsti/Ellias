package com.pay.buyManager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import android.text.TextUtils;
import com.pay.AndroidPay;
import com.pay.api.APPayGameService;
import com.pay.api.APPayOpenService;
import com.pay.common.tool.APLog;
import com.pay.data.orderInfo.APOrderInfo;
import com.pay.http.APErrorCode;
import com.pay.tool.APCommMethod;
import com.pay.tool.APDataInterface;
import com.pay.tool.APDataReportManager;
import com.pay.ui.channel.APChannelActivity;
import com.pay.ui.common.APQCardSuccessActivity;
import com.pay.ui.common.APUICommonMethod;
import com.pay.ui.payWeb.APWebBuyActivity;
import com.pay.ui.payWeb.APWebProtocol;
import java.lang.ref.WeakReference;

final class c extends ResultReceiver
{
  c(APCallTenpay paramAPCallTenpay, Handler paramHandler)
  {
    super(paramHandler);
  }

  public final void onReceiveResult(int paramInt, Bundle paramBundle)
  {
    APLog.i("Call Tenpay", "TenpayCallback resultCode = " + paramInt);
    Intent localIntent = new Intent();
    String str1 = paramBundle.getString("trace");
    APLog.i("Call Tenpay", "TenpayCallback trace = " + str1);
    APCallTenpay.a(this.a, str1);
    APDataReportManager.getInstance().insertData("sdk.tenpaycallback", APDataInterface.singleton().getOrderInfo().saveType, APDataInterface.singleton().getOrderInfo().tokenId, String.valueOf(paramInt), null);
    APOrderInfo localAPOrderInfo = APDataInterface.singleton().getOrderInfo();
    switch (paramInt)
    {
    default:
    case 1:
    case 2:
      String str4;
      do
      {
        return;
        APDataInterface.singleton().getOrderInfo().succSaveNum = APCallTenpay.c(this.a);
        if (!TextUtils.isEmpty(localAPOrderInfo.expressChannel))
          try
          {
            int i1 = paramBundle.getInt("pay_type");
            n = i1;
            if (APCallTenpay.a(this.a) == null)
            {
              APLog.w("APCallTenpay", "TenpayCallback context is null");
              return;
            }
          }
          catch (Exception localException2)
          {
            int n;
            while (true)
            {
              APLog.i("tenpay pay_type", "-1");
              n = -1;
            }
            if (APCommMethod.isPayGameResultByWeb())
            {
              Bundle localBundle3 = new Bundle();
              localBundle3.putInt("channel", APCallTenpay.d(this.a));
              localBundle3.putInt("pay_type", n);
              localIntent.putExtras(localBundle3);
              APWebBuyActivity.loadWebPage = APWebProtocol.WEBPAGE_PAYGAME_RESULT;
              localIntent.setClass((Context)APCallTenpay.a(this.a).get(), APWebBuyActivity.class);
              ((Context)APCallTenpay.a(this.a).get()).startActivity(localIntent);
              ((Activity)APCallTenpay.a(this.a).get()).overridePendingTransition(APCommMethod.getAnimId((Context)APCallTenpay.a(this.a).get(), "unipay_anim_in_from_right"), APCommMethod.getAnimId((Context)APCallTenpay.a(this.a).get(), "unipay_anim_out_to_left"));
              return;
            }
            APUICommonMethod.popActivity();
            APCommMethod.paySuccCallBack(APCallTenpay.d(this.a), 0, -1);
            APDataReportManager.getInstance().insertData("sdk.tenpaysucctoast.show", APDataInterface.singleton().getOrderInfo().saveType, null, "pt" + String.valueOf(n), null);
            APUICommonMethod.successToast((Activity)APCallTenpay.a(this.a).get(), APDataInterface.singleton().getOrderInfo().saveType, APCallTenpay.c(this.a));
            return;
          }
        Bundle localBundle2 = new Bundle();
        String str5 = paramBundle.getString("total_fee");
        int j = paramBundle.getInt("pay_type");
        try
        {
          int m = Integer.valueOf(str5).intValue();
          k = m;
          if (APCallTenpay.a(this.a) == null)
          {
            APLog.w("APCallTenpay", "TenpayCallback context is null");
            return;
          }
        }
        catch (Exception localException1)
        {
          int k;
          while (true)
            k = 0;
          localBundle2.putFloat("total_fee", k / 100.0F);
          localBundle2.putInt("channel", APCallTenpay.d(this.a));
          localBundle2.putInt("pay_type", j);
          localIntent.putExtras(localBundle2);
          if (APDataInterface.singleton().getOrderInfo().saveType == 0)
            if (APCommMethod.isPayGameResultByWeb())
            {
              APWebBuyActivity.loadWebPage = APWebProtocol.WEBPAGE_PAYGAME_RESULT;
              localIntent.setClass((Context)APCallTenpay.a(this.a).get(), APWebBuyActivity.class);
            }
          while (true)
          {
            ((Context)APCallTenpay.a(this.a).get()).startActivity(localIntent);
            return;
            localIntent.setClass((Context)APCallTenpay.a(this.a).get(), APQCardSuccessActivity.class);
            continue;
            localIntent.setClass((Context)APCallTenpay.a(this.a).get(), APQCardSuccessActivity.class);
          }
        }
        localAPOrderInfo.expressChannel = null;
        int i = paramBundle.getInt("backfrom");
        APLog.i("APCallTenpay", "backfrom = " + i);
        if ((!localAPOrderInfo.isNumCanChange) && ((i == 0) || (i == 1)))
          APCommMethod.payErrorCallBack(2, "");
        str4 = APDataInterface.singleton().getPayAssignChannel();
      }
      while ((localAPOrderInfo.isNumCanChange) || (str4 == null) || (!str4.equals("bank")) || (AndroidPay.singleton().isValidPayChannelAndMarket()));
      APCommMethod.payErrorCallBack(2, "");
      return;
    case 3:
      localAPOrderInfo.expressChannel = null;
      if (APCallTenpay.a(this.a) == null)
      {
        APLog.w("APCallTenpay", "TenpayCallback 3 context is null");
        return;
      }
      Bundle localBundle1 = new Bundle();
      localBundle1.putBoolean("isPayExpress", true);
      localIntent.setClass((Context)APCallTenpay.a(this.a).get(), APChannelActivity.class);
      localIntent.putExtras(localBundle1);
      ((Context)APCallTenpay.a(this.a).get()).startActivity(localIntent);
      return;
    case 4:
      if (APCallTenpay.a(this.a) == null)
      {
        APLog.w("APCallTenpay", "TenpayCallback resultcode 4 context is null");
        return;
      }
      String str2 = paramBundle.getString("msg");
      if ((str2 != null) && (!str2.equals("")));
      for (String str3 = str2 + APErrorCode.getErrorCode(3004); ; str3 = "系统繁忙,请稍后再试" + APErrorCode.getErrorCode(3004))
      {
        APUICommonMethod.showToast((Context)APCallTenpay.a(this.a).get(), str3);
        if ((localAPOrderInfo.isNumCanChange) || ((APCallTenpay.b(this.a) != 0) && (APCallTenpay.b(this.a) != 1)))
          break;
        APCommMethod.payErrorCallBack(-1, "订单信息错误");
        return;
      }
    case 5:
    }
    if (APDataInterface.singleton().getOrderInfo().saveType == 4)
    {
      APPayOpenService.retLogin();
      return;
    }
    APPayGameService.retLogin();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.buyManager.c
 * JD-Core Version:    0.6.0
 */