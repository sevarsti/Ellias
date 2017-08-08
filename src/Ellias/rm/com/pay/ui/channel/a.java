package com.pay.ui.channel;

import android.view.View;
import android.view.View.OnClickListener;
import com.pay.tool.APDataReportManager;

final class a
  implements View.OnClickListener
{
  a(APChannelActivity paramAPChannelActivity)
  {
  }

  public final void onClick(View paramView)
  {
    APDataReportManager.getInstance().insertData("sdk.channellist.more", APChannelActivity.a(this.a));
    APChannelActivity.b(this.a);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.ui.channel.a
 * JD-Core Version:    0.6.0
 */