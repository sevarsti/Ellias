package com.pay.ui.qdsafe;

import android.app.ProgressDialog;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import com.pay.data.orderInfo.APOrderInfo;
import com.pay.tool.APCommMethod;
import com.pay.tool.APDataInterface;
import com.pay.tool.APDataReportManager;

final class b
  implements View.OnClickListener
{
  b(APSafeCenterWebActivity paramAPSafeCenterWebActivity)
  {
  }

  public final void onClick(View paramView)
  {
    APDataReportManager.getInstance().insertData("sdk.mb.fresh", APDataInterface.singleton().getOrderInfo().saveType);
    APSafeCenterWebActivity.a(this.a).show();
    WebView localWebView = (WebView)this.a.findViewById(APCommMethod.getId(this.a, "unipay_id_WebView"));
    APSafeCenterWebActivity.a(this.a, localWebView);
    localWebView.reload();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.ui.qdsafe.b
 * JD-Core Version:    0.6.0
 */