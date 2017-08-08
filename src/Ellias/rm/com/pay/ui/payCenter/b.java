package com.pay.ui.payCenter;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.EditText;

final class b
  implements View.OnTouchListener
{
  b(APPayGameInputNumActivity paramAPPayGameInputNumActivity)
  {
  }

  public final boolean onTouch(View paramView, MotionEvent paramMotionEvent)
  {
    APPayGameInputNumActivity.c(this.a).setCursorVisible(true);
    return false;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.ui.payCenter.b
 * JD-Core Version:    0.6.0
 */