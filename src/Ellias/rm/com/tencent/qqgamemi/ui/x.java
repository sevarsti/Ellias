package com.tencent.qqgamemi.ui;

import android.view.View;
import android.view.View.OnClickListener;

class x
  implements View.OnClickListener
{
  x(w paramw)
  {
  }

  public void onClick(View paramView)
  {
    int i = ((Integer)paramView.getTag()).intValue();
    LoginDialog.a(this.a.c, i);
    this.a.notifyDataSetChanged();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.ui.x
 * JD-Core Version:    0.6.0
 */