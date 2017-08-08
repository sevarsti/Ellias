package com.pay.ui.channel;

import android.view.View;
import android.view.View.OnClickListener;
import com.pay.tool.APDataReportManager;
import com.pay.ui.common.APUICommonMethod;

final class n
  implements View.OnClickListener
{
  private n(APHFPayActivity paramAPHFPayActivity)
  {
  }

  public final void onClick(View paramView)
  {
    APDataReportManager.getInstance().insertData("sdk.hf.back", APHFPayActivity.f(this.a));
    APUICommonMethod.popActivity();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.ui.channel.n
 * JD-Core Version:    0.6.0
 */