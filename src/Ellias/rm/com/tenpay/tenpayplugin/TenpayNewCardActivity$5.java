package com.tenpay.tenpayplugin;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;

class TenpayNewCardActivity$5
  implements View.OnClickListener
{
  public void onClick(View paramView)
  {
    if (!TenpayNewCardActivity.p(this.a))
    {
      TenpayNewCardActivity.q(this.a).setVisibility(0);
      TenpayNewCardActivity.a(this.a, true);
      return;
    }
    TenpayNewCardActivity.q(this.a).setVisibility(8);
    TenpayNewCardActivity.a(this.a, false);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tenpay.tenpayplugin.TenpayNewCardActivity.5
 * JD-Core Version:    0.6.0
 */