package com.pay.ui.payCenter;

import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.EditText;
import com.pay.common.tool.APLog;
import com.pay.data.orderInfo.APOrderInfo;

final class c
  implements ViewTreeObserver.OnGlobalLayoutListener
{
  c(APPayGameInputNumActivity paramAPPayGameInputNumActivity)
  {
  }

  public final void onGlobalLayout()
  {
    APPayGameInputNumActivity.c(this.a).getViewTreeObserver().removeGlobalOnLayoutListener(this);
    APPayGameInputNumActivity.a(this.a, APPayGameInputNumActivity.c(this.a).getWidth() / 2);
    APLog.i("addOnGlobalLayoutListener", "numberEditx:" + APPayGameInputNumActivity.f(this.a));
    APPayGameInputNumActivity.c(this.a).setText(String.valueOf(APPayGameInputNumActivity.g(this.a).saveNum));
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.ui.payCenter.c
 * JD-Core Version:    0.6.0
 */