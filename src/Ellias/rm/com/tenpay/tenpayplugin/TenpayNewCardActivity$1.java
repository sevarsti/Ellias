package com.tenpay.tenpayplugin;

import android.os.Handler;

class TenpayNewCardActivity$1
  implements Runnable
{
  public void run()
  {
    TenpayNewCardActivity localTenpayNewCardActivity = this.a;
    TenpayNewCardActivity.a(localTenpayNewCardActivity, -1 + TenpayNewCardActivity.a(localTenpayNewCardActivity));
    TenpayNewCardActivity.b(this.a);
    if ((TenpayNewCardActivity.a(this.a) > 0) && (this.a.mHandler != null))
      this.a.mHandler.postDelayed(this.a.mResendTimer, 1000L);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tenpay.tenpayplugin.TenpayNewCardActivity.1
 * JD-Core Version:    0.6.0
 */