package com.pay.ui.saveAccount;

import android.text.Editable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import com.pay.tool.APDataReportManager;

final class e
  implements View.OnClickListener
{
  e(APSaveAccountInputNumActivity paramAPSaveAccountInputNumActivity)
  {
  }

  public final void onClick(View paramView)
  {
    String str = APSaveAccountInputNumActivity.a(this.a).getText().toString().trim();
    APDataReportManager.getInstance().insertData("sdk.goods.sure", APSaveAccountInputNumActivity.c(this.a), null, "d" + str, null);
    if (APSaveAccountInputNumActivity.e(this.a))
      this.a.doPay();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.ui.saveAccount.e
 * JD-Core Version:    0.6.0
 */