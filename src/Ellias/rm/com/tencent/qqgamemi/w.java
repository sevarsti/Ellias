package com.tencent.qqgamemi;

import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;

class w extends GestureDetector.SimpleOnGestureListener
{
  w(QMiViewManager paramQMiViewManager)
  {
  }

  public boolean onDown(MotionEvent paramMotionEvent)
  {
    if (this.a.l())
      this.a.h();
    return true;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.w
 * JD-Core Version:    0.6.0
 */