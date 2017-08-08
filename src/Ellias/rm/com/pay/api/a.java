package com.pay.api;

import com.pay.http.APBaseHttpAns;
import com.pay.http.IAPHttpAnsObserver;
import com.pay.tool.APDataReportManager;

final class a
  implements IAPHttpAnsObserver
{
  public final void onError(APBaseHttpAns paramAPBaseHttpAns)
  {
  }

  public final void onFinish(APBaseHttpAns paramAPBaseHttpAns)
  {
    APDataReportManager.getInstance().saveDataId();
  }

  public final void onStop(APBaseHttpAns paramAPBaseHttpAns)
  {
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.api.a
 * JD-Core Version:    0.6.0
 */