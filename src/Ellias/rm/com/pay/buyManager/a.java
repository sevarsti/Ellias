package com.pay.buyManager;

import android.app.Activity;
import com.pay.common.tool.APLog;
import com.pay.ui.common.APUICommonMethod;
import com.tenpay.tenpayplugin.TenpayCallback;
import java.lang.ref.WeakReference;

final class a
  implements TenpayCallback
{
  a(APCallTenpay paramAPCallTenpay)
  {
  }

  public final void onTenpayInited(Activity paramActivity)
  {
    if (APCallTenpay.a(this.a) == null)
      APLog.w("APCallTenpay", "onTenpayInited context is null");
    while (true)
    {
      return;
      APUICommonMethod.pushActivity(paramActivity);
      try
      {
        Activity localActivity = (Activity)APCallTenpay.a(this.a).get();
        if (!localActivity.toString().contains("com.pay"))
          continue;
        localActivity.runOnUiThread(new b(this, localActivity));
        return;
      }
      catch (Exception localException)
      {
      }
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.buyManager.a
 * JD-Core Version:    0.6.0
 */