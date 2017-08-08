package com.pay.ui.channel;

import android.view.View;
import android.view.View.OnClickListener;
import com.pay.tool.APDataReportManager;

final class u
  implements View.OnClickListener
{
  u(APMCardPayActivity paramAPMCardPayActivity)
  {
  }

  public final void onClick(View paramView)
  {
    APDataReportManager.getInstance().insertData("sdk.pcard.pay", APMCardPayActivity.a(this.a));
    this.a.doPay();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.ui.channel.u
 * JD-Core Version:    0.6.0
 */