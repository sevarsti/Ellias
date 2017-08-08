package com.pay;

import android.content.DialogInterface;
import android.content.DialogInterface.OnKeyListener;
import android.view.KeyEvent;
import com.pay.tool.APCommMethod;
import com.pay.ui.common.APUICommonMethod;

final class c
  implements DialogInterface.OnKeyListener
{
  c(APBuyPage paramAPBuyPage)
  {
  }

  public final boolean onKey(DialogInterface paramDialogInterface, int paramInt, KeyEvent paramKeyEvent)
  {
    if ((paramInt == 4) && (paramKeyEvent.getAction() == 1))
    {
      APUICommonMethod.popActivity();
      APCommMethod.retLoginCallBack();
    }
    return false;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.c
 * JD-Core Version:    0.6.0
 */