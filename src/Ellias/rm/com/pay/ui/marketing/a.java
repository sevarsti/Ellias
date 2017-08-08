package com.pay.ui.marketing;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.pay.APBuyPage;
import com.pay.AndroidPay;
import com.pay.common.tool.APLog;
import com.pay.data.buyInfo.APBuyGoodsInfo;
import com.pay.data.orderInfo.APOrderInfo;
import com.pay.tool.APCommMethod;
import com.pay.tool.APDataInterface;
import com.pay.tool.APDataReportManager;
import com.pay.ui.common.APUICommonMethod;

final class a extends WebViewClient
{
  a(APWebMarketActivity paramAPWebMarketActivity)
  {
  }

  public final void onPageFinished(WebView paramWebView, String paramString)
  {
    super.onPageFinished(paramWebView, paramString);
    APLog.i("webviewclient == ", "onPageFinished ");
    APWebMarketActivity.a(this.a, false);
    if (APWebMarketActivity.b(this.a) != null)
      APWebMarketActivity.b(this.a).dismiss();
    ((WebView)this.a.findViewById(APCommMethod.getId(this.a, "unipay_id_WebView"))).setVisibility(0);
  }

  public final void onPageStarted(WebView paramWebView, String paramString, Bitmap paramBitmap)
  {
    APWebMarketActivity.a(this.a, paramWebView, paramString);
    if (!APWebMarketActivity.c(this.a))
    {
      APLog.i("webviewclient == ", "onPageStarted ");
      paramWebView.goBack();
      if (!paramString.startsWith("http://unipay.android.mp.pay"))
        break label166;
      APDataReportManager.getInstance().insertData("sdk.market.sure", APDataInterface.singleton().getOrderInfo().saveType, null, APDataInterface.singleton().getPayAssignChannel(), APDataInterface.singleton().getDiscountExtras());
    }
    while (true)
    {
      try
      {
        APBuyPage localAPBuyPage = new APBuyPage(this.a, AndroidPay.singleton().launchOption);
        if (APDataInterface.singleton().getOrderInfo().saveType != 0)
          continue;
        localAPBuyPage.getBuyInfo(0, null);
        super.onPageStarted(paramWebView, paramString, paramBitmap);
        return;
        if (APDataInterface.singleton().getOrderInfo().saveType != 1)
          continue;
        localAPBuyPage.getBuyInfo(1, ((APBuyGoodsInfo)APDataInterface.singleton().getOrderInfo().buyInfo).tokenUrl);
        continue;
      }
      catch (Exception localException)
      {
        APUICommonMethod.showToast(this.a, "网络错误，请稍后重试");
        continue;
      }
      label166: if (paramString.startsWith("http://unipay.android.mp.back"))
      {
        APDataReportManager.getInstance().insertData("sdk.market.cancel", APDataInterface.singleton().getOrderInfo().saveType, null, APDataInterface.singleton().getPayAssignChannel(), APDataInterface.singleton().getDiscountExtras());
        paramWebView.stopLoading();
        APCommMethod.payErrorCallBack(2, "");
        this.a.finish();
        continue;
      }
      if (!paramString.startsWith("http://unipay.android.mp.retback"))
        continue;
      APDataReportManager.getInstance().insertData("sdk.market.result.cancel", APDataInterface.singleton().getOrderInfo().saveType, null, APDataInterface.singleton().getPayAssignChannel(), APDataInterface.singleton().getDiscountExtras());
      APUICommonMethod.popActivity();
      if (APWebMarketActivity.a(this.a).equals("showWechatResultActivity"))
      {
        APCommMethod.paySuccCallBack(8, 0, 0);
        continue;
      }
      if (!APWebMarketActivity.a(this.a).equals("showBankResultActivity"))
        continue;
      APCommMethod.paySuccCallBack(2, 0, 0);
    }
  }

  public final void onReceivedError(WebView paramWebView, int paramInt, String paramString1, String paramString2)
  {
    APLog.i("webviewclient == ", "onReceivedError ");
    if (paramString2.startsWith("http://unipay.android.mp.pay"))
      paramWebView.stopLoading();
    while (true)
    {
      super.onReceivedError(paramWebView, paramInt, paramString1, paramString2);
      return;
      if (!paramString2.startsWith("http://unipay.android.mp.back"))
        continue;
      paramWebView.stopLoading();
    }
  }

  public final boolean shouldOverrideUrlLoading(WebView paramWebView, String paramString)
  {
    APWebMarketActivity.a(this.a, paramWebView, paramString);
    paramWebView.stopLoading();
    if (paramString.startsWith("http://unipay.android.mp.pay"))
    {
      APLog.i("webviewclient == ", "shouldOverrideUrlLoading");
      APDataReportManager.getInstance().insertData("sdk.market.sure", APDataInterface.singleton().getOrderInfo().saveType, null, APDataInterface.singleton().getPayAssignChannel(), APDataInterface.singleton().getDiscountExtras());
    }
    while (true)
    {
      try
      {
        APBuyPage localAPBuyPage = new APBuyPage(this.a, AndroidPay.singleton().launchOption);
        if (APDataInterface.singleton().getOrderInfo().saveType != 0)
          continue;
        localAPBuyPage.getBuyInfo(0, null);
        APWebMarketActivity.a(this.a, true);
        return super.shouldOverrideUrlLoading(paramWebView, paramString);
        if (APDataInterface.singleton().getOrderInfo().saveType != 1)
          continue;
        localAPBuyPage.getBuyInfo(1, ((APBuyGoodsInfo)APDataInterface.singleton().getOrderInfo().buyInfo).tokenUrl);
        continue;
      }
      catch (Exception localException)
      {
        APUICommonMethod.showToast(this.a, "网络错误，请稍后重试");
        continue;
      }
      if (paramString.startsWith("http://unipay.android.mp.back"))
      {
        APDataReportManager.getInstance().insertData("sdk.market.cancel", APDataInterface.singleton().getOrderInfo().saveType, null, APDataInterface.singleton().getPayAssignChannel(), APDataInterface.singleton().getDiscountExtras());
        paramWebView.stopLoading();
        APCommMethod.payErrorCallBack(2, "");
        this.a.finish();
        continue;
      }
      if (!paramString.startsWith("http://unipay.android.mp.retback"))
        continue;
      APDataReportManager.getInstance().insertData("sdk.market.result.cancel", APDataInterface.singleton().getOrderInfo().saveType, null, APDataInterface.singleton().getPayAssignChannel(), APDataInterface.singleton().getDiscountExtras());
      APUICommonMethod.popActivity();
      if (APWebMarketActivity.a(this.a).equals("showWechatResultActivity"))
      {
        APCommMethod.paySuccCallBack(8, 0, 0);
        continue;
      }
      if (!APWebMarketActivity.a(this.a).equals("showBankResultActivity"))
        continue;
      APCommMethod.paySuccCallBack(2, 0, 0);
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.ui.marketing.a
 * JD-Core Version:    0.6.0
 */