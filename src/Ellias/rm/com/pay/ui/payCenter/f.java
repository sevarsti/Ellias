package com.pay.ui.payCenter;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import com.pay.tool.APCommMethod;
import com.pay.tool.APDataInterface;
import com.pay.tool.APDataReportManager;
import com.pay.ui.common.APUICommonMethod;

final class f
  implements View.OnClickListener
{
  f(APPayGameInputNumActivity paramAPPayGameInputNumActivity)
  {
  }

  public final void onClick(View paramView)
  {
    if (APDataInterface.singleton().getSourceActivity().equals("APPayGameListNumActivity"))
    {
      APDataReportManager.getInstance().insertData("sdk.gameinput.back", APPayGameInputNumActivity.h(this.a));
      Intent localIntent = new Intent();
      localIntent.setClass(this.a, APPayGameListNumActivity.class);
      this.a.startActivity(localIntent);
      this.a.finish();
      return;
    }
    ((InputMethodManager)this.a.getSystemService("input_method")).hideSoftInputFromWindow(APPayGameInputNumActivity.c(this.a).getWindowToken(), 0);
    APDataReportManager.getInstance().insertData("sdk.goods.close", APPayGameInputNumActivity.h(this.a));
    APUICommonMethod.popActivity();
    APCommMethod.payErrorCallBack(2, "");
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.ui.payCenter.f
 * JD-Core Version:    0.6.0
 */