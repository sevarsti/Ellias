package com.pay.http;

import com.pay.common.tool.APLog;

final class e
  implements IAPHttpAnsObserver
{
  e(APIPList paramAPIPList)
  {
  }

  public final void onError(APBaseHttpAns paramAPBaseHttpAns)
  {
  }

  public final void onFinish(APBaseHttpAns paramAPBaseHttpAns)
  {
    if (paramAPBaseHttpAns.getResultCode() == 0)
    {
      APLog.i("APIPList", "update ip list succ");
      APIPList.a(this.a);
    }
  }

  public final void onStop(APBaseHttpAns paramAPBaseHttpAns)
  {
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.http.e
 * JD-Core Version:    0.6.0
 */