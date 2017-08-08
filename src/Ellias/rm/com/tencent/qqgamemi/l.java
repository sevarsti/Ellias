package com.tencent.qqgamemi;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import com.nineoldandroids.animation.AnimatorSet;

class l
  implements View.OnTouchListener
{
  l(QMiSpirit paramQMiSpirit)
  {
  }

  public boolean onTouch(View paramView, MotionEvent paramMotionEvent)
  {
    if ((QMiSpirit.e(this.a) != null) && (QMiSpirit.e(this.a).isRunning()));
    do
      return true;
    while ((QMiSpirit.g(this.a) != null) || (QMiSpirit.h(this.a).a(paramMotionEvent)));
    QMiSpirit.a(this.a, paramMotionEvent);
    this.a.a(paramMotionEvent);
    QMiSpirit.b(this.a, paramMotionEvent);
    return true;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.l
 * JD-Core Version:    0.6.0
 */