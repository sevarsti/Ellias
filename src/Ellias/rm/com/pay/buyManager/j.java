package com.pay.buyManager;

import android.content.DialogInterface;
import android.content.DialogInterface.OnKeyListener;
import android.view.KeyEvent;
import com.pay.tool.APDataReportManager;

final class j
  implements DialogInterface.OnKeyListener
{
  j(APPayManager paramAPPayManager, int paramInt)
  {
  }

  public final boolean onKey(DialogInterface paramDialogInterface, int paramInt, KeyEvent paramKeyEvent)
  {
    if ((paramInt == 4) && (paramKeyEvent.getAction() == 1))
    {
      if (this.b != 4)
        break label42;
      APDataReportManager.getInstance().insertData("sdk.notenough.keyback", this.a.saveType, null, "16", null);
    }
    while (true)
    {
      return false;
      label42: APDataReportManager.getInstance().insertData("sdk.notenough.keyback", this.a.saveType, null, String.valueOf(this.b), null);
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.buyManager.j
 * JD-Core Version:    0.6.0
 */