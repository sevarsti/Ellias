package com.tencent.qqgamemi.ui;

import android.widget.CheckBox;
import android.widget.PopupWindow.OnDismissListener;
import java.util.List;

class v
  implements PopupWindow.OnDismissListener
{
  v(u paramu)
  {
  }

  public void onDismiss()
  {
    if (LoginDialog.n(this.a.a).size() < 1)
      LoginDialog.j(this.a.a).setVisibility(8);
    LoginDialog.j(this.a.a).setChecked(false);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.ui.v
 * JD-Core Version:    0.6.0
 */