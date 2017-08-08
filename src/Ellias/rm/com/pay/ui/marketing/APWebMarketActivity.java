package com.pay.ui.marketing;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.LinearLayout.LayoutParams;
import com.pay.AndroidPay;
import com.pay.common.tool.APLog;
import com.pay.data.orderInfo.APOrderInfo;
import com.pay.http.APNetworkManager;
import com.pay.tool.APCommMethod;
import com.pay.tool.APDataInterface;
import com.pay.tool.APDataReportManager;
import com.pay.tool.APTools;
import com.pay.ui.common.APActivity;
import com.pay.ui.common.APUICommonMethod;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.net.URLEncoder;

public class APWebMarketActivity extends APActivity
{
  public static final String LOADBANKRESULTUI = "showBankResultActivity";
  public static final String LOADSTARTUI = "showStartActivity";
  public static final String LOADWECHATRESULTUI = "showWechatResultActivity";
  private String a = "";
  private boolean b = false;

  private String a()
  {
    int i = APDataInterface.singleton().getPresent_flag();
    String str1 = APDataInterface.singleton().getGoods_name();
    String str2 = APDataInterface.singleton().getGoods_num();
    String str3 = APDataInterface.singleton().getGoods_extend();
    APDataReportManager.getInstance().insertData("sdk.market.send" + i, APDataInterface.singleton().getOrderInfo().saveType, null, APDataInterface.singleton().getPayAssignChannel(), APDataInterface.singleton().getDiscountExtras());
    APLog.i("reResultURL", "send flag == sdk.market.send" + i);
    String str4 = "";
    Object localObject1 = "";
    try
    {
      str4 = URLEncoder.encode(str1, "UTF-8");
      if (!TextUtils.isEmpty(str3))
      {
        String str8 = URLEncoder.encode(str3, "UTF-8");
        localObject1 = str8;
      }
      str5 = APDataInterface.singleton().getSuccess_url();
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
      try
      {
        String str5;
        String str7;
        if (str5.contains("?"))
          str7 = str5 + "&present_flag=" + i + "&goods_name=" + str4 + "&goods_num=" + str2 + "&extend=" + (String)localObject1;
        String str6;
        for (localObject2 = str7; ; localObject2 = str6)
        {
          APLog.i("result url == ", (String)localObject2);
          return localObject2;
          localUnsupportedEncodingException = localUnsupportedEncodingException;
          APLog.i("goods_name_des == ", str4);
          break;
          str6 = str5 + "?present_flag=" + i + "&goods_name=" + str4 + "&goods_num=" + str2 + "&extend=" + (String)localObject1;
        }
      }
      catch (Exception localException)
      {
        while (true)
        {
          APUICommonMethod.popActivity();
          if (this.a.equals("showBankResultActivity"))
          {
            APCommMethod.paySuccCallBack(2, 0, -1);
            localObject2 = "";
            continue;
          }
          if (this.a.equals("showWechatResultActivity"))
            APCommMethod.paySuccCallBack(8, 0, -1);
          Object localObject2 = "";
        }
      }
    }
  }

  private void a(WebView paramWebView, String paramString)
  {
    LinearLayout.LayoutParams localLayoutParams = (LinearLayout.LayoutParams)paramWebView.getLayoutParams();
    APLog.i("webviewclient == ", "updateWebViewSize ");
    String str1 = APTools.getUrlParamsValue(paramString, "mpwidth");
    if (!TextUtils.isEmpty(str1));
    int j;
    for (int i = Integer.valueOf(str1).intValue(); ; j = 0)
    {
      String str2 = APTools.getUrlParamsValue(paramString, "mpheight");
      boolean bool = TextUtils.isEmpty(str2);
      int k = 0;
      if (!bool)
        k = Integer.valueOf(str2).intValue();
      if ((k != 0) && (i != 0))
      {
        localLayoutParams.width = APUICommonMethod.dip2px(this, i);
        localLayoutParams.height = APUICommonMethod.dip2px(this, k);
        paramWebView.setLayoutParams(localLayoutParams);
      }
      return;
    }
  }

  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    if (this.waitDialog != null)
      this.waitDialog.show();
    AndroidPay.singleton().isUILaunched = true;
    Bundle localBundle = getIntent().getExtras();
    try
    {
      this.a = localBundle.getString("loadUI");
      label44: APDataInterface.singleton().getDiscountUrl();
      if (this.a.equals("showStartActivity"))
      {
        setContentView(APCommMethod.getLayoutId(this, "unipay_layout_market_web"));
        webHtml(APDataInterface.singleton().getDiscountUrl());
        return;
      }
      if (this.a.equals("showBankResultActivity"))
      {
        setContentView(APCommMethod.getLayoutId(this, "unipay_layout_marketresult_web"));
        if (this.waitDialog != null)
          this.waitDialog.show();
        APNetworkManager.getInstance().queryMarketResult(new b(this));
        return;
      }
      if (this.a.equals("showWechatResultActivity"))
      {
        setContentView(APCommMethod.getLayoutId(this, "unipay_layout_marketresult_web"));
        if (this.waitDialog != null)
          this.waitDialog.show();
        APNetworkManager.getInstance().queryMarketResult(new c(this));
        return;
      }
      APCommMethod.payErrorCallBack(-1, "活动url为空");
      return;
    }
    catch (Exception localException)
    {
      break label44;
    }
  }

  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    if (paramInt == 4)
    {
      if (this.waitDialog != null)
        this.waitDialog.dismiss();
      if (!this.a.equals("showStartActivity"))
        break label78;
      APDataReportManager.getInstance().insertData("sdk.market.keyback", APDataInterface.singleton().getOrderInfo().saveType, null, APDataInterface.singleton().getPayAssignChannel(), APDataInterface.singleton().getDiscountExtras());
      APUICommonMethod.popActivity();
      APCommMethod.payErrorCallBack(2, "");
    }
    while (true)
    {
      return super.onKeyDown(paramInt, paramKeyEvent);
      label78: if (this.a.equals("showBankResultActivity"))
      {
        APDataReportManager.getInstance().insertData("sdk.market.result.keyback", APDataInterface.singleton().getOrderInfo().saveType, null, APDataInterface.singleton().getPayAssignChannel(), APDataInterface.singleton().getDiscountExtras());
        APUICommonMethod.popActivity();
        APCommMethod.paySuccCallBack(2, 0, -1);
        continue;
      }
      if (!this.a.equals("showWechatResultActivity"))
        continue;
      APDataReportManager.getInstance().insertData("sdk.market.result.keyback", APDataInterface.singleton().getOrderInfo().saveType, null, APDataInterface.singleton().getPayAssignChannel(), APDataInterface.singleton().getDiscountExtras());
      APUICommonMethod.popActivity();
      APCommMethod.paySuccCallBack(8, 0, -1);
    }
  }

  public void onResume()
  {
    super.onResume();
    if (this.a.equals("showStartActivity"))
      APDataReportManager.getInstance().insertData("sdk.market.show", APDataInterface.singleton().getOrderInfo().saveType, null, APDataInterface.singleton().getPayAssignChannel(), APDataInterface.singleton().getDiscountExtras());
    do
    {
      return;
      if (!this.a.equals("showBankResultActivity"))
        continue;
      APDataReportManager.getInstance().insertData("sdk.market.result.show", APDataInterface.singleton().getOrderInfo().saveType, null, APDataInterface.singleton().getPayAssignChannel(), APDataInterface.singleton().getDiscountExtras());
      APLog.i("onResume", "结果result show");
      return;
    }
    while (!this.a.equals("showWechatResultActivity"));
    APDataReportManager.getInstance().insertData("sdk.market.result.show", APDataInterface.singleton().getOrderInfo().saveType, null, APDataInterface.singleton().getPayAssignChannel(), APDataInterface.singleton().getDiscountExtras());
  }

  public void webHtml(String paramString)
  {
    APLog.i("APWebMarketActivity url == ", paramString);
    try
    {
      WebView localWebView = (WebView)findViewById(APCommMethod.getId(this, "unipay_id_WebView"));
      localWebView.setScrollBarStyle(0);
      try
      {
        Method localMethod = localWebView.getClass().getMethod("removeJavascriptInterface", new Class[] { String.class });
        if (localMethod != null)
          localMethod.invoke(localWebView, new Object[] { "searchBoxJavaBridge_" });
        WebSettings localWebSettings = localWebView.getSettings();
        localWebSettings.setUseWideViewPort(true);
        localWebSettings.setLoadWithOverviewMode(true);
        localWebSettings.setJavaScriptEnabled(true);
        localWebSettings.setSupportMultipleWindows(true);
        localWebSettings.setDomStorageEnabled(false);
        localWebSettings.setAppCacheMaxSize(2097152L);
        localWebSettings.setAppCachePath(getApplicationContext().getDir("cache", 0).getPath());
        localWebSettings.setAllowFileAccess(true);
        localWebSettings.setAppCacheEnabled(true);
        localWebSettings.setCacheMode(-1);
        localWebView.setWebViewClient(new a(this));
        a(localWebView, paramString);
        localWebView.loadUrl(paramString);
        return;
      }
      catch (Exception localException2)
      {
        while (true)
          APLog.i("removeJavascriptInterface", localException2.toString());
      }
    }
    catch (Exception localException1)
    {
      localException1.printStackTrace();
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.ui.marketing.APWebMarketActivity
 * JD-Core Version:    0.6.0
 */