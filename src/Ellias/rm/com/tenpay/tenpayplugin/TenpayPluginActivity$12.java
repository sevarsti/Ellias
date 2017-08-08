package com.tenpay.tenpayplugin;

import android.app.Dialog;
import android.view.View;
import android.view.View.OnClickListener;

class TenpayPluginActivity$12
  implements View.OnClickListener
{
  public void onClick(View paramView)
  {
    TenpayPluginActivity.c(this.a, "cancel");
    TenpayPluginActivity.e(this.a).dismiss();
    this.a.g = this.a.f;
    this.a.f = null;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tenpay.tenpayplugin.TenpayPluginActivity.12
 * JD-Core Version:    0.6.0
 */