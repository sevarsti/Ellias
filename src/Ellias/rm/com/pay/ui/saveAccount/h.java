package com.pay.ui.saveAccount;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import com.pay.tool.APDataInterface;
import com.pay.tool.APDataReportManager;

final class h
  implements View.OnClickListener
{
  h(APSaveAccountListNumActivity paramAPSaveAccountListNumActivity)
  {
  }

  public final void onClick(View paramView)
  {
    APDataReportManager.getInstance().insertData("sdk.accountlist.else", APSaveAccountListNumActivity.a(this.a));
    APSaveAccountListNumActivity.b(this.a).saveNum = "";
    Intent localIntent = new Intent();
    Bundle localBundle = new Bundle();
    localBundle.putString("from", "sdk");
    localBundle.putInt("saveType", APSaveAccountListNumActivity.a(this.a));
    localIntent.setClass(this.a, APSaveAccountInputNumActivity.class);
    localIntent.putExtras(localBundle);
    APDataInterface.singleton().getOrderInfo().saveNum = "";
    this.a.startActivity(localIntent);
    this.a.finish();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.ui.saveAccount.h
 * JD-Core Version:    0.6.0
 */