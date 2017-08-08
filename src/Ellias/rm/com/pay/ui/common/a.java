package com.pay.ui.common;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;

final class a
  implements View.OnClickListener
{
  a(APActivity paramAPActivity, LinearLayout paramLinearLayout)
  {
  }

  public final void onClick(View paramView)
  {
    if (!APActivity.a(this.a))
    {
      APActivity.a(this.a, true);
      this.b.setVisibility(0);
      return;
    }
    APActivity.a(this.a, false);
    this.b.setVisibility(8);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.ui.common.a
 * JD-Core Version:    0.6.0
 */