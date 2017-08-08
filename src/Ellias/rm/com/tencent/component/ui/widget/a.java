package com.tencent.component.ui.widget;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.PopupWindow;

class a
  implements View.OnTouchListener
{
  a(BetterPopupWindow paramBetterPopupWindow)
  {
  }

  public boolean onTouch(View paramView, MotionEvent paramMotionEvent)
  {
    if (paramMotionEvent.getAction() == 4)
    {
      BetterPopupWindow.a(this.a).dismiss();
      return true;
    }
    return false;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.ui.widget.a
 * JD-Core Version:    0.6.0
 */