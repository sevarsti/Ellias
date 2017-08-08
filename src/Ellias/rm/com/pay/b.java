package com.pay;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.pay.tool.APCommMethod;
import com.pay.ui.common.APUICommonMethod;

final class b
  implements DialogInterface.OnClickListener
{
  b(APBuyPage paramAPBuyPage)
  {
  }

  public final void onClick(DialogInterface paramDialogInterface, int paramInt)
  {
    paramDialogInterface.cancel();
    APUICommonMethod.popActivity();
    APCommMethod.retLoginCallBack();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.b
 * JD-Core Version:    0.6.0
 */