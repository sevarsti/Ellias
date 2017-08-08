package com.tenpay.tenpayplugin;

import android.app.Dialog;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.LinearLayout;

class TenpayPluginActivity$13
  implements View.OnClickListener
{
  public void onClick(View paramView)
  {
    TenpayPluginActivity.c(this.a, "sure");
    if (this.a.a < 2);
    for (this.a.g = "tenpay.inputm."; ; this.a.g = "tenpay.inputmbig.")
    {
      this.a.f = null;
      TenpayPluginActivity.c(this.a, true);
      TenpayPluginActivity.c(this.a, "show");
      TenpayPluginActivity.i(this.a).requestFocus();
      if (TenpayPluginActivity.y(this.a) != null)
        TenpayPluginActivity.y(this.a).setVisibility(8);
      if (TenpayPluginActivity.y(this.a) != null)
        TenpayPluginActivity.z(this.a).setVisibility(8);
      TenpayPluginActivity.h(this.a).setVisibility(0);
      TenpayPluginActivity.e(this.a).dismiss();
      return;
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tenpay.tenpayplugin.TenpayPluginActivity.13
 * JD-Core Version:    0.6.0
 */