package com.tenpay.tenpayplugin;

import android.app.Dialog;
import android.view.View;
import android.view.View.OnClickListener;
import org.json.JSONObject;

class TenpayPluginActivity$20
  implements View.OnClickListener
{
  public void onClick(View paramView)
  {
    TenpayPluginActivity.a(this.a, paramView);
    if (this.a.mPayGate.optInt("full_check", 1) == 1)
      TenpayPluginActivity.a(this.a, true, true);
    while (true)
    {
      TenpayPluginActivity.c(this.a, "cancel");
      TenpayPluginActivity.e(this.a).dismiss();
      this.a.g = this.a.f;
      this.a.f = null;
      return;
      TenpayPluginActivity.a(this.a, false, true);
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tenpay.tenpayplugin.TenpayPluginActivity.20
 * JD-Core Version:    0.6.0
 */