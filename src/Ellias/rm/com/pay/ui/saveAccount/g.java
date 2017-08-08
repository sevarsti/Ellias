package com.pay.ui.saveAccount;

import android.view.View;
import android.view.View.OnClickListener;
import com.pay.tool.APCommMethod;
import com.pay.tool.APDataReportManager;

final class g
  implements View.OnClickListener
{
  g(APSaveAccountListNumActivity paramAPSaveAccountListNumActivity)
  {
  }

  public final void onClick(View paramView)
  {
    APDataReportManager.getInstance().insertData("sdk.accountlist.close", APSaveAccountListNumActivity.a(this.a));
    this.a.finish();
    APCommMethod.payErrorCallBack(2, "");
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.ui.saveAccount.g
 * JD-Core Version:    0.6.0
 */