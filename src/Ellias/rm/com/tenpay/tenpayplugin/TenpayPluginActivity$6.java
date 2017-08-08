package com.tenpay.tenpayplugin;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;

class TenpayPluginActivity$6
  implements View.OnClickListener
{
  public void onClick(View paramView)
  {
    if (!TenpayPluginActivity.p(this.a))
    {
      TenpayPluginActivity.q(this.a).setVisibility(0);
      TenpayPluginActivity.b(this.a, true);
      return;
    }
    TenpayPluginActivity.q(this.a).setVisibility(8);
    TenpayPluginActivity.b(this.a, false);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tenpay.tenpayplugin.TenpayPluginActivity.6
 * JD-Core Version:    0.6.0
 */