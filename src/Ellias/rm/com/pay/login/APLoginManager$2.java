package com.pay.login;

import android.content.DialogInterface;
import android.content.DialogInterface.OnKeyListener;
import android.view.KeyEvent;
import com.pay.tool.APCommMethod;
import com.pay.ui.common.APUICommonMethod;

class APLoginManager$2
  implements DialogInterface.OnKeyListener
{
  public boolean onKey(DialogInterface paramDialogInterface, int paramInt, KeyEvent paramKeyEvent)
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
 * Qualified Name:     com.pay.login.APLoginManager.2
 * JD-Core Version:    0.6.0
 */