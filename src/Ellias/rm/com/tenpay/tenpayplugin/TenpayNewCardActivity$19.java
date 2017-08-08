package com.tenpay.tenpayplugin;

import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TableLayout;
import com.tenpay.tenpayplugin.view.ClearableEditText;

class TenpayNewCardActivity$19
  implements View.OnClickListener
{
  public void onClick(View paramView)
  {
    if (TenpayNewCardActivity.K(this.a).getVisibility() == 8)
    {
      TenpayNewCardActivity.f(this.a).postDelayed(new TenpayNewCardActivity.19.1(this), 200L);
      this.a.mHandler.postDelayed(new TenpayNewCardActivity.19.2(this), 300L);
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tenpay.tenpayplugin.TenpayNewCardActivity.19
 * JD-Core Version:    0.6.0
 */