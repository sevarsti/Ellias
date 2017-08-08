package com.pay.buyManager;

import com.pay.http.APBaseHttpAns;
import com.pay.http.IAPHttpAnsObserver;
import com.pay.ui.common.APUICommonMethod;

final class d
  implements IAPHttpAnsObserver
{
  d(APGetKeyManager paramAPGetKeyManager)
  {
  }

  public final void onError(APBaseHttpAns paramAPBaseHttpAns)
  {
    APUICommonMethod.dismissWaitDialog();
    if (this.a.getKeyCallBack != null)
      this.a.getKeyCallBack.onGetKeyFail(paramAPBaseHttpAns.getResultCode(), paramAPBaseHttpAns.getErrorMessage());
  }

  public final void onFinish(APBaseHttpAns paramAPBaseHttpAns)
  {
    APGetKeyManager.a(this.a, paramAPBaseHttpAns);
  }

  public final void onStop(APBaseHttpAns paramAPBaseHttpAns)
  {
    APUICommonMethod.dismissWaitDialog();
    if (this.a.getKeyCallBack != null)
      this.a.getKeyCallBack.onGetKeyCancel();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.buyManager.d
 * JD-Core Version:    0.6.0
 */