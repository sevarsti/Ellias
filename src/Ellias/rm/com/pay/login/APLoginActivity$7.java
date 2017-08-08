package com.pay.login;

import android.view.View;
import android.view.View.OnFocusChangeListener;
import com.pay.tool.APCommMethod;

class APLoginActivity$7
  implements View.OnFocusChangeListener
{
  public void onFocusChange(View paramView, boolean paramBoolean)
  {
    if (paramBoolean)
    {
      this.a.editLight(APCommMethod.getId(this.a, "unipay_id_LoginUinLayout"));
      return;
    }
    this.a.editNotLight(APCommMethod.getId(this.a, "unipay_id_LoginUinLayout"));
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.login.APLoginActivity.7
 * JD-Core Version:    0.6.0
 */