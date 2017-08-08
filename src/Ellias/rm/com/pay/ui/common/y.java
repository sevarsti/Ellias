package com.pay.ui.common;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

final class y
  implements View.OnTouchListener
{
  y(APPayVerifyCodeActivity paramAPPayVerifyCodeActivity)
  {
  }

  public final boolean onTouch(View paramView, MotionEvent paramMotionEvent)
  {
    if (this.a.getResources().getConfiguration().orientation == 2)
      APPayVerifyCodeActivity.a(this.a);
    return false;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.ui.common.y
 * JD-Core Version:    0.6.0
 */