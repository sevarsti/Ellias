package com.pay.ui.payExpress;

import android.view.View;
import android.view.View.OnClickListener;
import com.pay.tool.APCommMethod;
import com.pay.tool.APDataReportManager;
import com.pay.ui.common.APUICommonMethod;

final class a
  implements View.OnClickListener
{
  a(APPayExpressActivity paramAPPayExpressActivity)
  {
  }

  public final void onClick(View paramView)
  {
    APDataReportManager.getInstance().insertData("sdk.payexpress.close", APPayExpressActivity.c(this.a));
    APPayExpressActivity.d(this.a).expressChannel = null;
    APUICommonMethod.popActivity();
    APCommMethod.payErrorCallBack(2, "");
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.ui.payExpress.a
 * JD-Core Version:    0.6.0
 */