package com.pay.buyManager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.pay.AndroidPay;
import com.pay.api.APPayGameService;
import com.pay.api.APPayOpenService;
import com.pay.api.APPayResponseInfo;
import com.pay.common.tool.APLog;
import com.pay.data.mp.APMPSendInfo;
import com.pay.data.orderInfo.APOrderInfo;
import com.pay.tool.APAppDataInterface;
import com.pay.tool.APDataInterface;
import com.pay.tool.APDataReportManager;
import com.pay.ui.common.APQCardSuccessActivity;
import com.pay.ui.common.APUICommonMethod;
import com.pay.ui.marketing.APWebMarketActivity;
import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.modelpay.PayReq;
import com.tencent.mm.sdk.modelpay.PayReq.Options;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

public class APWechatPaySDK
{
  private Context context;
  IWXAPI payApi;
  IWXAPIEventHandler wxCallBack = new IWXAPIEventHandler()
  {
    public void onReq(BaseReq paramBaseReq)
    {
    }

    public void onResp(BaseResp paramBaseResp)
    {
      APLog.i("APWechatPaySDK", "wechat onResp, errCode = " + paramBaseResp.errCode + " msg=" + paramBaseResp.errStr);
      if (paramBaseResp.getType() == 5)
      {
        APDataReportManager.getInstance().insertData("sdk.wechatcallback", APDataInterface.singleton().getOrderInfo().saveType, APDataInterface.singleton().getOrderInfo().tokenId, String.valueOf(paramBaseResp.errCode), null);
        switch (paramBaseResp.errCode)
        {
        default:
        case 0:
        case -1:
        case -2:
        }
      }
      String str1;
      do
      {
        String str2;
        do
        {
          return;
          String str3 = APDataInterface.singleton().getPayAssignChannel();
          if ((AndroidPay.singleton().isValidPayChannelAndMarket()) && (str3.equals("wechat")))
          {
            APUICommonMethod.popActivity();
            Intent localIntent = new Intent();
            Bundle localBundle = new Bundle();
            localBundle.putString("loadUI", "showWechatResultActivity");
            localIntent.putExtras(localBundle);
            localIntent.setClass(APWechatPaySDK.this.context, APWebMarketActivity.class);
            APWechatPaySDK.this.context.startActivity(localIntent);
            return;
          }
          if (APMPSendInfo.getInstance().isHashSend())
          {
            APWechatPaySDK.this.toSuccActivity();
            return;
          }
          APUICommonMethod.popActivity();
          APWechatPaySDK.this.paySuccCallBack(8, -1);
          return;
          APUICommonMethod.showToast(APWechatPaySDK.this.context, "系统繁忙,请稍后再试(100-100--1)");
          str2 = APDataInterface.singleton().getPayAssignChannel();
        }
        while ((str2 == null) || (str2.equals("")) || (AndroidPay.singleton().isValidPayChannelAndMarket()));
        APUICommonMethod.popActivity();
        APWechatPaySDK.this.payErrorCallBack(-1, 2);
        return;
        APLog.i("APWechatPaySDK", "wechat onPayFinish user canceled");
        str1 = APDataInterface.singleton().getPayAssignChannel();
      }
      while ((str1 == null) || (str1.equals("")) || (AndroidPay.singleton().isValidPayChannelAndMarket()));
      APUICommonMethod.popActivity();
      APWechatPaySDK.this.payErrorCallBack(2, 1);
    }
  };

  public APWechatPaySDK(Context paramContext)
  {
    this.context = paramContext;
    try
    {
      Class.forName("com.tencent.mm.sdk.openapi.WXAPIFactory");
      this.payApi = WXAPIFactory.createWXAPI(paramContext, null);
      return;
    }
    catch (ClassNotFoundException localClassNotFoundException)
    {
      APLog.i("APWechatPaySDK", "libammsdk is lost");
      this.payApi = null;
    }
  }

  private void toSuccActivity()
  {
    try
    {
      Intent localIntent = new Intent();
      localIntent.setClass(this.context, APQCardSuccessActivity.class);
      localIntent.putExtra("channel", 8);
      this.context.startActivity(localIntent);
      return;
    }
    catch (Exception localException)
    {
      APUICommonMethod.popActivity();
      paySuccCallBack(8, -1);
    }
  }

  public void handleIntent(Intent paramIntent)
  {
    if (this.payApi == null)
      return;
    this.payApi.handleIntent(paramIntent, this.wxCallBack);
  }

  public boolean isInstallWechat()
  {
    if (this.payApi == null);
    do
      return true;
    while (this.payApi.isWXAppInstalled());
    APLog.i("APWechatPaySDK", "wechat is unInstalled");
    return false;
  }

  public boolean isSupportWechatAPI()
  {
    if (this.payApi == null);
    int i;
    do
    {
      return true;
      i = this.payApi.getWXAppSupportAPI();
    }
    while (i >= 570425345);
    APLog.i("APWechatPaySDK", "wechat version = " + i + "您的微信版本过低,请更新");
    return false;
  }

  protected void payErrorCallBack(int paramInt1, int paramInt2)
  {
    APPayResponseInfo localAPPayResponseInfo = new APPayResponseInfo();
    localAPPayResponseInfo.resultCode = paramInt1;
    localAPPayResponseInfo.payState = paramInt2;
    localAPPayResponseInfo.payChannel = 8;
    if ((APDataInterface.singleton().getOrderInfo().saveType == 0) || (APDataInterface.singleton().getOrderInfo().saveType == 1))
      APPayGameService.PayGameServiceCallBack(localAPPayResponseInfo);
    do
    {
      return;
      if (APDataInterface.singleton().getOrderInfo().saveType != 4)
        continue;
      APPayOpenService.PayOpenServiceCallBack(localAPPayResponseInfo);
      return;
    }
    while ((APDataInterface.singleton().getOrderInfo().saveType != 2) && (APDataInterface.singleton().getOrderInfo().saveType != 3));
    localAPPayResponseInfo.resultCode = paramInt1;
    localAPPayResponseInfo.payState = paramInt2;
    localAPPayResponseInfo.provideState = -1;
    if (AndroidPay.singleton().originalSaveType == 4)
    {
      APPayOpenService.PayOpenServiceCallBack(localAPPayResponseInfo);
      return;
    }
    APPayGameService.PayGameServiceCallBack(localAPPayResponseInfo);
  }

  protected void paySuccCallBack(int paramInt1, int paramInt2)
  {
    APPayResponseInfo localAPPayResponseInfo = new APPayResponseInfo();
    localAPPayResponseInfo.resultCode = 0;
    try
    {
      localAPPayResponseInfo.realSaveNum = Integer.valueOf(APDataInterface.singleton().getOrderInfo().succSaveNum).intValue();
      label32: localAPPayResponseInfo.payState = 0;
      localAPPayResponseInfo.provideState = paramInt2;
      localAPPayResponseInfo.payChannel = paramInt1;
      if ((APDataInterface.singleton().getOrderInfo().saveType == 0) || (APDataInterface.singleton().getOrderInfo().saveType == 1))
        APPayGameService.PayGameServiceCallBack(localAPPayResponseInfo);
      do
      {
        return;
        if (APDataInterface.singleton().getOrderInfo().saveType != 4)
          continue;
        APPayOpenService.PayOpenServiceCallBack(localAPPayResponseInfo);
        return;
      }
      while ((APDataInterface.singleton().getOrderInfo().saveType != 2) && (APDataInterface.singleton().getOrderInfo().saveType != 3));
      localAPPayResponseInfo.resultCode = 2;
      localAPPayResponseInfo.realSaveNum = 0;
      localAPPayResponseInfo.payState = 1;
      localAPPayResponseInfo.provideState = -1;
      if (AndroidPay.singleton().originalSaveType == 4)
      {
        APPayOpenService.PayOpenServiceCallBack(localAPPayResponseInfo);
        return;
      }
      APPayGameService.PayGameServiceCallBack(localAPPayResponseInfo);
      return;
    }
    catch (Exception localException)
    {
      break label32;
    }
  }

  public void toWeChat(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7)
  {
    if (this.payApi == null)
      return;
    APAppDataInterface.singleton().setWechatAppId(paramString1);
    PayReq localPayReq = new PayReq();
    localPayReq.appId = paramString1;
    localPayReq.partnerId = paramString2;
    localPayReq.prepayId = paramString3;
    localPayReq.nonceStr = paramString4;
    localPayReq.timeStamp = paramString5;
    localPayReq.packageValue = paramString6;
    localPayReq.sign = paramString7;
    localPayReq.openId = "";
    localPayReq.extData = "app data";
    PayReq.Options localOptions = new PayReq.Options();
    localOptions.callbackClassName = "com.pay.ui.channel.APWechatActivity";
    localPayReq.options = localOptions;
    this.payApi.registerApp(paramString1);
    this.payApi.sendReq(localPayReq);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.buyManager.APWechatPaySDK
 * JD-Core Version:    0.6.0
 */