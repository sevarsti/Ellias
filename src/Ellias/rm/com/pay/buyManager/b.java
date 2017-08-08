package com.pay.buyManager;

import android.app.Activity;
import android.view.View;
import android.view.Window;

final class b
  implements Runnable
{
  b(a parama, Activity paramActivity)
  {
  }

  public final void run()
  {
    if ((APCallTenpay.b(a.a(this.a)) == 0) || (APCallTenpay.b(a.a(this.a)) == 1))
      this.b.getWindow().getDecorView().setVisibility(8);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.buyManager.b
 * JD-Core Version:    0.6.0
 */