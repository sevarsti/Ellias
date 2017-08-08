package com.tencent.qqgamemi;

import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

class v
  implements View.OnTouchListener
{
  v(QMiViewManager paramQMiViewManager)
  {
  }

  public boolean onTouch(View paramView, MotionEvent paramMotionEvent)
  {
    QMiViewManager.a(this.a).onTouchEvent(paramMotionEvent);
    return true;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.v
 * JD-Core Version:    0.6.0
 */