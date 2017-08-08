package com.pay.buyManager;

import android.content.DialogInterface;
import android.content.DialogInterface.OnKeyListener;
import android.view.KeyEvent;
import com.pay.tool.APDataReportManager;

final class m
  implements DialogInterface.OnKeyListener
{
  m(APPayManager paramAPPayManager, int paramInt)
  {
  }

  public final boolean onKey(DialogInterface paramDialogInterface, int paramInt, KeyEvent paramKeyEvent)
  {
    if ((paramInt == 4) && (paramKeyEvent.getAction() == 1))
      APDataReportManager.getInstance().insertData("sdk.notenough.keyback", this.a.saveType, null, String.valueOf(this.b), null);
    return false;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.buyManager.m
 * JD-Core Version:    0.6.0
 */