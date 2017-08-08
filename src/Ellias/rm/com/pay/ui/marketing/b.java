package com.pay.ui.marketing;

import android.app.ProgressDialog;
import com.pay.http.APBaseHttpAns;
import com.pay.http.IAPHttpAnsObserver;
import com.pay.tool.APCommMethod;
import com.pay.ui.common.APUICommonMethod;

final class b
  implements IAPHttpAnsObserver
{
  b(APWebMarketActivity paramAPWebMarketActivity)
  {
  }

  public final void onError(APBaseHttpAns paramAPBaseHttpAns)
  {
    APWebMarketActivity.b(this.a).dismiss();
    APUICommonMethod.popActivity();
    APCommMethod.paySuccCallBack(2, 0, -1);
  }

  public final void onFinish(APBaseHttpAns paramAPBaseHttpAns)
  {
    if (paramAPBaseHttpAns.getResultCode() == 0)
    {
      String str = APWebMarketActivity.d(this.a);
      this.a.webHtml(str);
      return;
    }
    APWebMarketActivity.b(this.a).dismiss();
    APUICommonMethod.popActivity();
    APCommMethod.paySuccCallBack(2, 0, -1);
  }

  public final void onStop(APBaseHttpAns paramAPBaseHttpAns)
  {
    APWebMarketActivity.b(this.a).dismiss();
    APUICommonMethod.popActivity();
    APCommMethod.paySuccCallBack(2, 0, -1);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.ui.marketing.b
 * JD-Core Version:    0.6.0
 */