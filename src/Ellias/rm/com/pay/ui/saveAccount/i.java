package com.pay.ui.saveAccount;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import com.pay.tool.APDataReportManager;

final class i
  implements AdapterView.OnItemClickListener
{
  i(APSaveAccountListNumActivity paramAPSaveAccountListNumActivity)
  {
  }

  public final void onItemClick(AdapterView paramAdapterView, View paramView, int paramInt, long paramLong)
  {
    APDataReportManager.getInstance().insertData("sdk.accountlist.click", APSaveAccountListNumActivity.a(this.a), null, String.valueOf(paramInt), null);
    APSaveAccountListNumActivity.b(this.a).saveNum = String.valueOf(APSaveAccountListNumActivity.c(this.a)[paramInt]);
    this.a.dopay();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.ui.saveAccount.i
 * JD-Core Version:    0.6.0
 */