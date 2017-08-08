package com.tencent.qqgamemi.ui;

import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;

class u
  implements CompoundButton.OnCheckedChangeListener
{
  u(LoginDialog paramLoginDialog)
  {
  }

  public void onCheckedChanged(CompoundButton paramCompoundButton, boolean paramBoolean)
  {
    if ((paramCompoundButton == LoginDialog.j(this.a)) && (paramBoolean))
    {
      LoginDialog.e(this.a).clearFocus();
      LoginDialog.d(this.a).clearFocus();
      this.a.a();
      if (LoginDialog.k(this.a) != null)
      {
        if (LoginDialog.l(this.a) == null)
        {
          LoginDialog.a(this.a, new PopupWindow(LoginDialog.k(this.a), LoginDialog.m(this.a).getWidth(), -2, true));
          LoginDialog.l(this.a).setOutsideTouchable(false);
          LoginDialog.l(this.a).setBackgroundDrawable(new ColorDrawable(-1));
          LoginDialog.l(this.a).setOnDismissListener(new v(this));
        }
        LoginDialog.l(this.a).showAsDropDown(LoginDialog.m(this.a));
      }
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.ui.u
 * JD-Core Version:    0.6.0
 */