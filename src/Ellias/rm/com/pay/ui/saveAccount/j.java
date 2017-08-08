package com.pay.ui.saveAccount;

import android.view.View;
import android.view.View.OnClickListener;
import com.pay.tool.APDataReportManager;

final class j
  implements View.OnClickListener
{
  j(APSaveAccountListNumActivity paramAPSaveAccountListNumActivity)
  {
  }

  public final void onClick(View paramView)
  {
    APDataReportManager.getInstance().insertData("sdk.gamelist.click", APSaveAccountListNumActivity.a(this.a), null, paramView.getTag().toString(), null);
    Integer localInteger = (Integer)paramView.getTag();
    APSaveAccountListNumActivity.b(this.a).saveNum = String.valueOf(APSaveAccountListNumActivity.c(this.a)[localInteger.intValue()]);
    this.a.dopay();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.ui.saveAccount.j
 * JD-Core Version:    0.6.0
 */