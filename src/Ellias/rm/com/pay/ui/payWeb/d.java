package com.pay.ui.payWeb;

import android.view.View;
import android.view.View.OnClickListener;
import com.pay.tool.APDataReportManager;

final class d
  implements View.OnClickListener
{
  d(APWebBuyActivity paramAPWebBuyActivity)
  {
  }

  public final void onClick(View paramView)
  {
    APDataReportManager.getInstance().insertData(this.a.apWebData.webRefreshClickFormat, APWebBuyActivity.b(this.a));
    this.a.webViewLoadURL();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.ui.payWeb.d
 * JD-Core Version:    0.6.0
 */