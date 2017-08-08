package com.pay.ui.payExpress;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import com.pay.tool.APDataReportManager;
import com.pay.ui.channel.APChannelActivity;

final class c
  implements View.OnClickListener
{
  c(APPayExpressActivity paramAPPayExpressActivity)
  {
  }

  public final void onClick(View paramView)
  {
    APDataReportManager.getInstance().insertData("sdk.payexpress.change", APPayExpressActivity.c(this.a));
    APPayExpressActivity.d(this.a).expressChannel = null;
    Bundle localBundle = new Bundle();
    localBundle.putBoolean("isPayExpress", true);
    Intent localIntent = new Intent(this.a, APChannelActivity.class);
    localIntent.putExtras(localBundle);
    this.a.startActivity(localIntent);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.ui.payExpress.c
 * JD-Core Version:    0.6.0
 */