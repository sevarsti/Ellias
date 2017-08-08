package com.pay.ui.saveAccount;

import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import com.pay.tool.APCommMethod;
import com.pay.tool.APDataReportManager;

final class d
  implements View.OnClickListener
{
  d(APSaveAccountInputNumActivity paramAPSaveAccountInputNumActivity)
  {
  }

  public final void onClick(View paramView)
  {
    ((InputMethodManager)this.a.getSystemService("input_method")).hideSoftInputFromWindow(APSaveAccountInputNumActivity.a(this.a).getWindowToken(), 0);
    APDataReportManager.getInstance().insertData("sdk.accountinput.close", APSaveAccountInputNumActivity.c(this.a));
    this.a.finish();
    APCommMethod.payErrorCallBack(2, "");
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.ui.saveAccount.d
 * JD-Core Version:    0.6.0
 */