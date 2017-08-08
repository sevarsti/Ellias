package com.pay.ui.channel;

import com.pay.tool.APCommMethod;
import com.pay.ui.common.APScrollView;

final class CUpper
  implements Runnable
{
  CUpper(APQCardPayActivity paramAPQCardPayActivity)
  {
  }

  public final void run()
  {
    ((APScrollView)this.a.findViewById(APCommMethod.getId(this.a, "unipay_id_ScrollView"))).smoothScrollTo(0, 110);
  }
}