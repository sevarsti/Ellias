package com.tenpay.tenpayplugin;

import android.os.Handler;

class TenpayPluginActivity$1
  implements Runnable
{
  public void run()
  {
    TenpayPluginActivity localTenpayPluginActivity = this.a;
    TenpayPluginActivity.a(localTenpayPluginActivity, -1 + TenpayPluginActivity.a(localTenpayPluginActivity));
    TenpayPluginActivity.b(this.a);
    if ((TenpayPluginActivity.a(this.a) > 0) && (this.a.mHandler != null))
      this.a.mHandler.postDelayed(this.a.mResendTimer, 1000L);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tenpay.tenpayplugin.TenpayPluginActivity.1
 * JD-Core Version:    0.6.0
 */