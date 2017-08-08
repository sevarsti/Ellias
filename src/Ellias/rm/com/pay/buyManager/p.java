package com.pay.buyManager;

import android.content.DialogInterface;
import android.content.DialogInterface.OnKeyListener;
import android.view.KeyEvent;
import com.pay.tool.APDataReportManager;

final class p
  implements DialogInterface.OnKeyListener
{
  p(APPayManager paramAPPayManager)
  {
  }

  public final boolean onKey(DialogInterface paramDialogInterface, int paramInt, KeyEvent paramKeyEvent)
  {
    if ((paramInt == 4) && (paramKeyEvent.getAction() == 1))
      APDataReportManager.getInstance().insertData("sdk.notenough.keyback", this.a.saveType, null, "15", null);
    return false;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.buyManager.p
 * JD-Core Version:    0.6.0
 */