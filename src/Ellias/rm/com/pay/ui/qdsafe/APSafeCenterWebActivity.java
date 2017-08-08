package com.pay.ui.qdsafe;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;
import com.pay.common.tool.APLog;
import com.pay.data.orderInfo.APOrderInfo;
import com.pay.tool.APCommMethod;
import com.pay.tool.APDataInterface;
import com.pay.tool.APDataReportManager;
import com.pay.ui.common.APActivity;
import java.io.File;
import java.lang.reflect.Method;

public class APSafeCenterWebActivity extends APActivity
{
  private WebView a;

  private static void a(WebView paramWebView)
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

  public void SetBackBtn(String paramString)
  {
    runOnUiThread(new e(this, paramString));
  }

  public void SetTitle(String paramString)
  {
    ((TextView)findViewById(APCommMethod.getId(this, "unipay_id_WebTitle"))).setText(paramString);
  }

  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(APCommMethod.getLayoutId(this, "unipay_layout_common_web"));
    String str = getIntent().getExtras().getString("requesturl");
    if (str != null)
    {
      this.waitDialog.show();
      webHtml(str);
    }
    Button localButton = (Button)findViewById(APCommMethod.getId(this, "unipay_id_BackBtn"));
    localButton.setText("返回");
    localButton.setOnClickListener(new a(this));
    ((Button)findViewById(APCommMethod.getId(this, "unipay_id_RefreshBtn"))).setOnClickListener(new b(this));
  }

  public void onDestroy()
  {
    super.onDestroy();
  }

  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    APDataReportManager.getInstance().insertData("sdk.mb.keyback", APDataInterface.singleton().getOrderInfo().saveType);
    if ((paramInt == 4) && (this.a.canGoBack()))
    {
      this.a.goBack();
      return true;
    }
    return super.onKeyDown(paramInt, paramKeyEvent);
  }

  public void onResume()
  {
    super.onResume();
    APDataReportManager.getInstance().insertData("sdk.mb.show", APDataInterface.singleton().getOrderInfo().saveType);
  }

  public void webHtml(String paramString)
  {
    APLog.i("密保cgi", paramString);
    try
    {
      this.a = ((WebView)findViewById(APCommMethod.getId(this, "unipay_id_WebView")));
      WebSettings localWebSettings = this.a.getSettings();
      localWebSettings.setJavaScriptEnabled(true);
      localWebSettings.setSupportMultipleWindows(true);
      localWebSettings.setDomStorageEnabled(true);
      localWebSettings.setAppCacheMaxSize(2097152L);
      localWebSettings.setAppCachePath(getApplicationContext().getDir("cache", 0).getPath());
      localWebSettings.setAllowFileAccess(true);
      localWebSettings.setAppCacheEnabled(true);
      localWebSettings.setCacheMode(-1);
      c localc = new c(this);
      d locald = new d(this);
      this.a.setWebViewClient(localc);
      this.a.setWebChromeClient(locald);
      a(this.a);
      this.a.loadUrl(paramString);
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.ui.qdsafe.APSafeCenterWebActivity
 * JD-Core Version:    0.6.0
 */