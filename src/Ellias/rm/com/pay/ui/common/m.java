package com.pay.ui.common;

import android.app.ProgressDialog;
import com.pay.http.APBaseHttpAns;
import com.pay.http.IAPHttpAnsObserver;
import com.pay.network.modle.APQueryMcardStatusAns;

final class m
  implements IAPHttpAnsObserver
{
  m(APMcardSuccessActivity paramAPMcardSuccessActivity)
  {
  }

  public final void onError(APBaseHttpAns paramAPBaseHttpAns)
  {
  }

  public final void onFinish(APBaseHttpAns paramAPBaseHttpAns)
  {
    this.a.waitDialog.dismiss();
    APQueryMcardStatusAns localAPQueryMcardStatusAns = (APQueryMcardStatusAns)paramAPBaseHttpAns;
    APMcardSuccessActivity.a(this.a, localAPQueryMcardStatusAns.getMcardStatus());
    APMcardSuccessActivity.b(this.a, APMcardSuccessActivity.b(this.a));
  }

  public final void onStop(APBaseHttpAns paramAPBaseHttpAns)
  {
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.ui.common.m
 * JD-Core Version:    0.6.0
 */