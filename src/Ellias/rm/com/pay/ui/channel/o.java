package com.pay.ui.channel;

import com.pay.tool.APCommMethod;
import com.pay.ui.common.APScrollView;

final class o
  implements Runnable
{
  o(APMCardPayActivity paramAPMCardPayActivity)
  {
  }

  public final void run()
  {
    try
    {
      ((APScrollView)this.a.findViewById(APCommMethod.getId(this.a, "unipay_id_ScrollView"))).smoothScrollTo(0, 110);
      return;
    }
    catch (Exception localException)
    {
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.ui.channel.o
 * JD-Core Version:    0.6.0
 */