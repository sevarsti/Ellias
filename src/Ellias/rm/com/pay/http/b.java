package com.pay.http;

import com.pay.ui.common.APUICommonMethod;

final class b
  implements IAPHttpAnsObserver
{
  b(APBaseHttpAns paramAPBaseHttpAns, boolean paramBoolean)
  {
  }

  public final void onError(APBaseHttpAns paramAPBaseHttpAns)
  {
    APUICommonMethod.dismissWaitDialog();
  }

  public final void onFinish(APBaseHttpAns paramAPBaseHttpAns)
  {
    APBaseHttpAns.a(this.a, paramAPBaseHttpAns, this.b);
  }

  public final void onStop(APBaseHttpAns paramAPBaseHttpAns)
  {
    APUICommonMethod.dismissWaitDialog();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.http.b
 * JD-Core Version:    0.6.0
 */