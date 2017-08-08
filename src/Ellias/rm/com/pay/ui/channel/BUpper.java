package com.pay.ui.channel;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;

final class BUpper
  implements View.OnClickListener
{
  BUpper(APQCardPayActivity paramAPQCardPayActivity)
  {
  }

  public final void onClick(View paramView)
  {
    APQCardPayActivity.a(this.a).setText("");
  }
}