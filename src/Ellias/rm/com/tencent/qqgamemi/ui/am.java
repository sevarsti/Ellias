package com.tencent.qqgamemi.ui;

import android.view.View;
import android.view.View.OnClickListener;

class am
  implements View.OnClickListener
{
  am(MiuiNotifyDialog.Builder paramBuilder)
  {
  }

  public void onClick(View paramView)
  {
    if (paramView == MiuiNotifyDialog.Builder.a(this.a))
      MiuiNotifyDialog.Builder.b(this.a).dismiss();
    do
      return;
    while (paramView != MiuiNotifyDialog.Builder.c(this.a));
    MiuiNotifyDialog.Builder.b(this.a).dismiss();
    MiuiNotifyDialog.Builder.d(this.a);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.ui.am
 * JD-Core Version:    0.6.0
 */