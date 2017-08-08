package com.pay.ui.payExpress;

import android.view.View;
import android.view.View.OnClickListener;
import com.pay.tool.APDataReportManager;

final class b
  implements View.OnClickListener
{
  b(APPayExpressActivity paramAPPayExpressActivity)
  {
  }

  public final void onClick(View paramView)
  {
    if (APPayExpressActivity.e(this.a) == 11);
    for (APPayExpressActivity.d(this.a).expressChannel = "qbqd"; ; APPayExpressActivity.d(this.a).expressChannel = "qdqb")
    {
      APDataReportManager.getInstance().insertData("sdk.payexpress.sure", APPayExpressActivity.c(this.a));
      APPayExpressActivity.a(this.a, APPayExpressActivity.c(this.a), APPayExpressActivity.e(this.a));
      return;
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.ui.payExpress.b
 * JD-Core Version:    0.6.0
 */