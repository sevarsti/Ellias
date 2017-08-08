package com.pay.ui.payWeb;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import com.pay.common.tool.APLog;
import com.pay.data.buyInfo.APBaseBuyInfo;
import com.pay.data.mp.APMPSendInfo;
import com.pay.data.orderInfo.APOrderInfo;
import com.pay.data.userInfo.APUserInfo;
import com.pay.tool.APAppDataInterface;
import com.pay.tool.APDataInterface;
import com.pay.tool.APTestDataInterface;
import com.pay.tool.APTools;
import com.pay.ui.channel.APRecoChannelActivity;
import com.pay.ui.payCenter.APSaveValueList;
import java.io.File;
import java.lang.reflect.Method;
import java.util.HashMap;

public class APWebActivity extends APRecoChannelActivity
{
  private boolean a = false;
  protected APWebData apWebData = null;
  protected WebView mWebView = null;

  protected void addCommParam()
  {
    this.apWebData.webViewUrlParamMap.put("reqFrom", APWebProtocol.WEB_REQFROM);
    this.apWebData.webViewUrlParamMap.put("openId", APDataInterface.singleton().getUserInfo().openId);
    this.apWebData.webViewUrlParamMap.put("openKey", APDataInterface.singleton().getUserInfo().openKey);
    this.apWebData.webViewUrlParamMap.put("sessionId", APDataInterface.singleton().getUserInfo().sessionId);
    this.apWebData.webViewUrlParamMap.put("sessionType", APDataInterface.singleton().getUserInfo().sessionType);
    this.apWebData.webViewUrlParamMap.put("pf", APDataInterface.singleton().getUserInfo().pf);
    this.apWebData.webViewUrlParamMap.put("pfKey", APDataInterface.singleton().getUserInfo().pfKey);
    this.apWebData.webViewUrlParamMap.put("zoneId", APDataInterface.singleton().getUserInfo().zoneId);
    this.apWebData.webViewUrlParamMap.put("offerId", APAppDataInterface.singleton().getOfferid());
    this.apWebData.webViewUrlParamMap.put("saveType", String.valueOf(APDataInterface.singleton().getOrderInfo().saveType));
    this.apWebData.webViewUrlParamMap.put("gameCoinName", APTools.urlEncode(APDataInterface.singleton().getOrderInfo().buyInfo.name, 1));
    this.apWebData.webViewUrlParamMap.put("gameCoinRate", APDataInterface.singleton().getOrderInfo().buyInfo.price);
    this.apWebData.webViewUrlParamMap.put("recommendList", APTools.array2String(APSaveValueList.singleton().getSaveNumber(), ","));
    this.apWebData.webViewUrlParamMap.put("minNumber", String.valueOf(APDataInterface.singleton().getOrderInfo().buyInfo.minNum));
    this.apWebData.webViewUrlParamMap.put("sdkVersion", "1.3.7b");
    this.apWebData.webViewUrlParamMap.put("sessionToken", APDataInterface.singleton().getOrderInfo().sessionToken);
    this.apWebData.webViewUrlParamMap.put("mpJson", APTools.urlEncode(APMPSendInfo.getInstance().getMPJson(), 1));
    this.apWebData.webViewUrlParamMap.put("gameCoinUnit", APTools.urlEncode(APDataInterface.singleton().getOrderInfo().buyInfo.unit, 1));
    if (APDataInterface.singleton().getUserInfo().isFirstCharge)
    {
      this.apWebData.webViewUrlParamMap.put("firstCharge", "1");
      return;
    }
    this.apWebData.webViewUrlParamMap.put("firstCharge", "0");
  }

  public void addTestParam()
  {
    try
    {
      if ((APAppDataInterface.singleton().getEnv().equals("test")) && (APWebBuyActivity.loadWebPage.equals(APWebProtocol.WEBPAGE_PAYGAME_RESULT)))
      {
        this.apWebData.webViewUrlParamMap.put("saveType", APTestDataInterface.TestData_ResultPageSaveType);
        this.apWebData.webViewUrlParamMap.put("isToken", APTestDataInterface.TestData_ResultPageIsToken);
        this.apWebData.webViewUrlParamMap.put("canChange", APTestDataInterface.TestData_ResultPageCanChanage);
        this.apWebData.webViewUrlParamMap.put("offerId", APTestDataInterface.TestData_ResultPageOfferId);
        this.apWebData.webViewUrlParamMap.put("productId", APTestDataInterface.TestData_ResultPageProductId);
        this.apWebData.webViewUrlParamMap.put("tokenUrl", APTools.urlEncode(APTestDataInterface.TestData_ResultPageToken, 1));
      }
      return;
    }
    catch (Exception localException)
    {
    }
  }

  protected String constructUrl()
  {
    return null;
  }

  public void initParam()
  {
  }

  protected void initUI()
  {
  }

  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this.waitDialog.setOnCancelListener(new a(this));
  }

  public void onDestroy()
  {
    super.onDestroy();
  }

  protected void removeJsInterface(WebView paramWebView)
  {
    try
    {
      Method localMethod = paramWebView.getClass().getMethod("removeJavascriptInterface", new Class[] { String.class });
      if (localMethod != null)
        localMethod.invoke(paramWebView, new Object[] { "searchBoxJavaBridge_" });
      return;
    }
    catch (Exception localException)
    {
      APLog.i("removeJavascriptInterface", localException.toString());
    }
  }

  protected void setWebViewSetting()
  {
    WebSettings localWebSettings = this.mWebView.getSettings();
    localWebSettings.setJavaScriptEnabled(true);
    localWebSettings.setSupportMultipleWindows(true);
    localWebSettings.setDomStorageEnabled(true);
    localWebSettings.setAppCacheMaxSize(2097152L);
    localWebSettings.setAppCachePath(getApplicationContext().getDir("cache", 0).getPath());
    localWebSettings.setAllowFileAccess(true);
    localWebSettings.setAppCacheEnabled(true);
    localWebSettings.setCacheMode(-1);
  }

  protected void webViewLoadURL()
  {
    this.waitDialog.show();
    initParam();
    addCommParam();
    addTestParam();
    String str = APAppDataInterface.singleton().getEnv();
    if ((!str.equals("release")) && ((str.equals("test")) || (str.equals("dev"))));
    for (this.apWebData.webUrlDomain = APWebProtocol.URL_DOMAIN_SANDBOX; ; this.apWebData.webUrlDomain = APWebProtocol.URL_DOMAIN_RELEASE)
    {
      initUI();
      removeJsInterface(this.mWebView);
      setWebViewSetting();
      b localb = new b(this);
      this.mWebView.setWebViewClient(localb);
      APLog.i("apWebData.webUrl == ", constructUrl());
      this.mWebView.loadUrl(constructUrl());
      return;
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.ui.payWeb.APWebActivity
 * JD-Core Version:    0.6.0
 */