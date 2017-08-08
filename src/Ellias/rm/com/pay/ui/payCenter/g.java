package com.pay.ui.payCenter;

import android.text.Editable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import com.pay.tool.APDataReportManager;

final class g
  implements View.OnClickListener
{
  g(APPayGameInputNumActivity paramAPPayGameInputNumActivity)
  {
  }

  public final void onClick(View paramView)
  {
    String str = APPayGameInputNumActivity.c(this.a).getText().toString().trim();
    APDataReportManager.getInstance().insertData("sdk.goods.sure", APPayGameInputNumActivity.h(this.a), null, "d" + str, null);
    if (APPayGameInputNumActivity.i(this.a))
      this.a.doPay();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.ui.payCenter.g
 * JD-Core Version:    0.6.0
 */