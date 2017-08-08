package com.tencent.qqgamemi.ui;

import android.app.Dialog;
import android.view.View;
import android.view.View.OnClickListener;

class ai
  implements View.OnClickListener
{
  ai(MeDialog.Builder paramBuilder)
  {
  }

  public void onClick(View paramView)
  {
    MeDialog.Builder.b(this.a).dismiss();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.ui.ai
 * JD-Core Version:    0.6.0
 */