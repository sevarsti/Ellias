package com.tenpay.tenpayplugin;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnKeyListener;
import android.os.Bundle;
import android.view.KeyEvent;

class TenpayPluginActivity$33
  implements DialogInterface.OnKeyListener
{
  public boolean onKey(DialogInterface paramDialogInterface, int paramInt, KeyEvent paramKeyEvent)
  {
    switch (paramInt)
    {
    default:
    case 4:
    }
    do
      return false;
    while ((TenpayPluginActivity.d(this.a) == null) || (!TenpayPluginActivity.d(this.a).isShowing()));
    TenpayPluginActivity.d(this.a).dismiss();
    TenpayPluginActivity.a(this.a, null);
    this.a.mFinished = true;
    Bundle localBundle = new Bundle();
    localBundle.putString("trace", this.a.e.toString());
    localBundle.putInt("pay_type", this.a.getPayType());
    localBundle.putInt("backfrom", TenpayPluginActivity.c(this.a));
    TenpayUtil.onCallback(this.a, 2, localBundle);
    this.a.finish();
    return true;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tenpay.tenpayplugin.TenpayPluginActivity.33
 * JD-Core Version:    0.6.0
 */