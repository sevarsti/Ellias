package com.pay.ui.common;

import com.pay.http.APBaseHttpAns;
import com.pay.http.IAPHttpAnsObserver;
import com.pay.network.modle.APhfPayAns;

final class i
  implements IAPHttpAnsObserver
{
  i(APHFPaySuccessActivity paramAPHFPaySuccessActivity)
  {
  }

  public final void onError(APBaseHttpAns paramAPBaseHttpAns)
  {
  }

  public final void onFinish(APBaseHttpAns paramAPBaseHttpAns)
  {
    APhfPayAns localAPhfPayAns = (APhfPayAns)paramAPBaseHttpAns;
    APHFPaySuccessActivity.a(this.a, localAPhfPayAns.getHfStatus());
    APHFPaySuccessActivity.a(this.a).sendEmptyMessage(0);
  }

  public final void onStop(APBaseHttpAns paramAPBaseHttpAns)
  {
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.ui.common.i
 * JD-Core Version:    0.6.0
 */