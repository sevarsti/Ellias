package com.tencent.qqgamemi;

import android.view.MotionEvent;
import com.tencent.qqgamemi.common.TLog;

class k extends SimpleGestureDetector.SimpleOnGestureListener
{
  k(QMiSpirit paramQMiSpirit)
  {
  }

  public boolean a(MotionEvent paramMotionEvent)
  {
    TLog.c("OnTouch", "onDoubleTap");
    QMiSpirit.b(this.a).k();
    return true;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.k
 * JD-Core Version:    0.6.0
 */