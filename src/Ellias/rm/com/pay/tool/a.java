package com.pay.tool;

import com.pay.http.APBaseHttpAns;
import com.pay.http.IAPHttpAnsObserver;

final class a
  implements IAPHttpAnsObserver
{
  a(APDataReportManager paramAPDataReportManager)
  {
  }

  public final void onError(APBaseHttpAns paramAPBaseHttpAns)
  {
  }

  public final void onFinish(APBaseHttpAns paramAPBaseHttpAns)
  {
    this.a.saveDataId();
  }

  public final void onStop(APBaseHttpAns paramAPBaseHttpAns)
  {
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.tool.a
 * JD-Core Version:    0.6.0
 */