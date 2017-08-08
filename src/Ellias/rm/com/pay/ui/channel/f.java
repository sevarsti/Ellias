package com.pay.ui.channel;

import android.text.Editable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import com.pay.tool.APDataReportManager;

final class f
  implements View.OnClickListener
{
  f(APHFPayActivity paramAPHFPayActivity)
  {
  }

  public final void onClick(View paramView)
  {
    if (!APHFPayActivity.b(this.a))
    {
      APHFPayActivity.a(this.a, APHFPayActivity.a(this.a).getText().toString().trim());
      this.a.searchPhoneNumInfo(APHFPayActivity.c(this.a));
      return;
    }
    APHFPayActivity.a(this.a, APHFPayActivity.d(this.a), APHFPayActivity.e(this.a));
    APHFPayActivity.a(this.a, true);
    APDataReportManager.getInstance().insertData("sdk.hf.sure", APHFPayActivity.f(this.a));
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.ui.channel.f
 * JD-Core Version:    0.6.0
 */