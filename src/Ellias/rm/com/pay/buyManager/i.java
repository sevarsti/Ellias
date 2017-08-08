package com.pay.buyManager;

import com.pay.http.APBaseHttpAns;
import com.pay.http.IAPHttpAnsObserver;
import com.pay.network.modle.APQueryMcardStatusAns;
import com.pay.ui.common.APUICommonMethod;

final class i
  implements IAPHttpAnsObserver
{
  i(APPayManager paramAPPayManager)
  {
  }

  public final void onError(APBaseHttpAns paramAPBaseHttpAns)
  {
    APUICommonMethod.dismissWaitDialog();
    APPayManager.b(this.a, "");
  }

  public final void onFinish(APBaseHttpAns paramAPBaseHttpAns)
  {
    APUICommonMethod.dismissWaitDialog();
    APQueryMcardStatusAns localAPQueryMcardStatusAns = (APQueryMcardStatusAns)paramAPBaseHttpAns;
    if (localAPQueryMcardStatusAns.getResultCode() == 0);
    for (String str = localAPQueryMcardStatusAns.getMcardStatus(); ; str = "-1")
    {
      APPayManager.b(this.a, str);
      return;
    }
  }

  public final void onStop(APBaseHttpAns paramAPBaseHttpAns)
  {
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.buyManager.i
 * JD-Core Version:    0.6.0
 */