package com.pay.ui.channel;

import android.content.DialogInterface;
import android.content.DialogInterface.OnKeyListener;
import android.view.KeyEvent;
import com.pay.tool.APDataReportManager;

final class k
  implements DialogInterface.OnKeyListener
{
  k(APHFPayActivity paramAPHFPayActivity)
  {
  }

  public final boolean onKey(DialogInterface paramDialogInterface, int paramInt, KeyEvent paramKeyEvent)
  {
    if ((paramInt == 4) && (paramKeyEvent.getAction() == 1))
    {
      APDataReportManager.getInstance().insertData("sdk.ask.keyback", APHFPayActivity.f(this.a));
      APHFPayActivity.a(this.a, false);
    }
    return false;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.ui.channel.k
 * JD-Core Version:    0.6.0
 */