package com.tenpay.tenpayplugin;

import android.app.Dialog;

class TenpayPluginActivity$34
  implements Runnable
{
  public void run()
  {
    if (TenpayPluginActivity.K(this.a))
    {
      if (TenpayPluginActivity.d(this.a) != null)
      {
        TenpayPluginActivity.d(this.a).dismiss();
        TenpayPluginActivity.a(this.a, null);
      }
      if (!this.a.mFinished)
        TenpayPluginActivity.g(this.a);
      return;
    }
    TenpayPluginActivity.e(this.a, true);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tenpay.tenpayplugin.TenpayPluginActivity.34
 * JD-Core Version:    0.6.0
 */