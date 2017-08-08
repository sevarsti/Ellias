package com.pay.ui.qdsafe;

import android.widget.Button;
import com.pay.tool.APCommMethod;

final class e
  implements Runnable
{
  e(APSafeCenterWebActivity paramAPSafeCenterWebActivity, String paramString)
  {
  }

  public final void run()
  {
    ((Button)this.a.findViewById(APCommMethod.getId(this.a, "unipay_id_BackBtn"))).setText(this.b);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.ui.qdsafe.e
 * JD-Core Version:    0.6.0
 */