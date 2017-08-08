package com.pay.ui.qdsafe;

import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import com.pay.tool.APCommMethod;
import com.pay.ui.common.APUICommonMethod;

final class j
  implements View.OnClickListener
{
  j(APSmmActivity paramAPSmmActivity)
  {
  }

  public final void onClick(View paramView)
  {
    String str = this.a.vercodeEdit.getText().toString();
    if (TextUtils.isEmpty(str))
    {
      APUICommonMethod.showToast(this.a, APCommMethod.getStringId(this.a, "unipay_vercode_error"), "", false);
      return;
    }
    APSmmActivity.a(this.a, str);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.ui.qdsafe.j
 * JD-Core Version:    0.6.0
 */