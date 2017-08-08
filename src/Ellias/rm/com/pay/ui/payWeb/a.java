package com.pay.ui.payWeb;

import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import com.pay.common.tool.APLog;

final class a
  implements DialogInterface.OnCancelListener
{
  a(APWebActivity paramAPWebActivity)
  {
  }

  public final void onCancel(DialogInterface paramDialogInterface)
  {
    APLog.i("WebViewLoadURL", "loading back");
    this.a.apWebData.webResponseCallBack.WebLoadingCancel();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.ui.payWeb.a
 * JD-Core Version:    0.6.0
 */