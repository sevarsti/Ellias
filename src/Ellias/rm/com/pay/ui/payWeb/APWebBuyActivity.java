package com.pay.ui.payWeb;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.webkit.WebView;
import com.pay.AndroidPay;
import com.pay.data.orderInfo.APOrderInfo;
import com.pay.tool.APCommMethod;
import com.pay.tool.APDataInterface;
import com.pay.tool.APDataReportManager;
import com.pay.tool.APTools;
import com.pay.ui.common.APUICommonMethod;
import java.util.HashMap;

public class APWebBuyActivity extends APWebActivity
{
  public static String loadWebPage = APWebProtocol.WEBPAGE_PAYGAME_LIST;
  private int a = -1;
  public IAPWebResponse webResponseCallBack = new c(this);

  private void a(String paramString)
  {
    if (!TextUtils.isEmpty(paramString))
    {
      APDataInterface.singleton().getOrderInfo().saveNum = paramString;
      payAutoSelect();
      return;
    }
    APUICommonMethod.showToast(this, "充值数量不能为空");
  }

  protected String constructUrl()
  {
    TextUtils.isEmpty(this.apWebData.webUrlPath);
    StringBuilder localStringBuilder = new StringBuilder(String.valueOf(APWebProtocol.URLPRE)).append(this.apWebData.webUrlDomain).append(this.apWebData.webUrlPath);
    String str1 = APWebProtocol.URLSUFFIX_PORTRAIT;
    if (getResources().getConfiguration().orientation == 1)
      str1 = APWebProtocol.URLSUFFIX_PORTRAIT;
    while (true)
    {
      String str2 = str1;
      Object localObject;
      if (str2.contains("?"))
      {
        if (!str2.endsWith("?"))
          str2 = str2 + "&";
        localObject = "";
      }
      try
      {
        String str3 = "&random=" + String.valueOf(100.0D * Math.random());
        localObject = str3;
        label158: return str2 + APTools.map2UrlParams(this.apWebData.webViewUrlParamMap) + (String)localObject;
        if (getResources().getConfiguration().orientation != 2)
          continue;
        str1 = APWebProtocol.URLSUFFIX_LANDSCAPE;
        continue;
        str2 = str2 + "?";
      }
      catch (Exception localException)
      {
        break label158;
      }
    }
  }

  public void initParam()
  {
    APWebData localAPWebData = new APWebData();
    localAPWebData.webResponseCallBack = this.webResponseCallBack;
    if (loadWebPage.equals(APWebProtocol.WEBPAGE_PAYGAME_LIST))
    {
      localAPWebData.webUrlPath = APDataInterface.singleton().getMallUrl();
      localAPWebData.webViewUrlParamMap.put("page", APWebProtocol.WEBPAGE_PAYGAME_LIST);
      localAPWebData.webPageShowFormat = "sdk.gamelist.h5.show";
      localAPWebData.webBackFormat = "sdk.gamelist.h5.back";
      localAPWebData.webListClickFormat = "sdk.gamelist.h5.click";
      localAPWebData.webInputPayFormat = "sdk.gameinput.h5.pay";
      localAPWebData.webKeyBackFormat = "sdk.gamelist.h5.keyback";
      localAPWebData.webPageShowErrorFormat = "sdk.gamelist.h5.error";
    }
    for (localAPWebData.webRefreshClickFormat = "sdk.gamelist.h5.refresh"; ; localAPWebData.webRefreshClickFormat = "sdk.result.h5.refresh")
    {
      do
      {
        this.apWebData = localAPWebData;
        return;
      }
      while ((loadWebPage.equals(APWebProtocol.WEBPAGE_PAYGAME_INPUT)) || (!loadWebPage.equals(APWebProtocol.WEBPAGE_PAYGAME_RESULT)));
      localAPWebData.webUrlPath = APDataInterface.singleton().getResultUrl();
      localAPWebData.webViewUrlParamMap.put("page", APWebProtocol.WEBPAGE_PAYGAME_RESULT);
      localAPWebData.webViewUrlParamMap.put("saveNumber", APDataInterface.singleton().getOrderInfo().saveNum);
      localAPWebData.webResultPayFormat = "sdk.result.h5.pay";
      localAPWebData.webBackFormat = "sdk.result.h5.back";
      localAPWebData.webResultRepayFormat = "sdk.result.h5.repay";
      localAPWebData.webKeyBackFormat = "sdk.result.h5.keyback";
      localAPWebData.webPageShowFormat = "sdk.result.h5.show";
      localAPWebData.webPageShowErrorFormat = "sdk.result.h5.error";
    }
  }

  public void initUI()
  {
    setContentView(APCommMethod.getLayoutId(this, "unipay_layout_web"));
    this.mWebView = ((WebView)findViewById(APCommMethod.getId(this, "unipay_id_WebView")));
  }

  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    Bundle localBundle = getIntent().getExtras();
    if (localBundle != null)
      this.a = localBundle.getInt("channel");
    AndroidPay.singleton().isUILaunched = true;
    webViewLoadURL();
  }

  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    if (paramInt == 4)
    {
      APDataReportManager.getInstance().insertData(this.apWebData.webKeyBackFormat, this.saveType);
      if (!loadWebPage.equals(APWebProtocol.WEBPAGE_PAYGAME_LIST))
        break label50;
      APUICommonMethod.popActivity();
      APCommMethod.payErrorCallBack(2, "支付取消");
    }
    while (true)
    {
      return super.onKeyDown(paramInt, paramKeyEvent);
      label50: if ((loadWebPage.equals(APWebProtocol.WEBPAGE_PAYGAME_INPUT)) || (!loadWebPage.equals(APWebProtocol.WEBPAGE_PAYGAME_RESULT)))
        continue;
      APUICommonMethod.popActivity();
      APCommMethod.paySuccCallBack(APDataInterface.singleton().getOrderInfo().payChannel, 0, -1);
    }
  }

  public void onResume()
  {
    super.onResume();
    APDataReportManager.getInstance().insertData(this.apWebData.webPageShowFormat, this.saveType);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.ui.payWeb.APWebBuyActivity
 * JD-Core Version:    0.6.0
 */