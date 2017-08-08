package com.pay.login;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.pay.tool.APCommMethod;
import com.pay.ui.common.APUICommonMethod;

class APLoginManager$1
  implements DialogInterface.OnClickListener
{
  public void onClick(DialogInterface paramDialogInterface, int paramInt)
  {
    paramDialogInterface.cancel();
    APUICommonMethod.popActivity();
    APCommMethod.retLoginCallBack();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.login.APLoginManager.1
 * JD-Core Version:    0.6.0
 */