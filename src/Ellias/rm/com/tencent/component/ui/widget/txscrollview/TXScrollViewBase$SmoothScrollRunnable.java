package com.tencent.component.ui.widget.txscrollview;

import android.os.Build;
import android.support.v4.view.ViewCompat;
import android.text.TextUtils;
import android.view.animation.Interpolator;

public class TXScrollViewBase$SmoothScrollRunnable
  implements Runnable
{
  private final Interpolator b;
  private final int c;
  private final int d;
  private final long e;
  private boolean f = true;
  private long g = -1L;
  private int h = -1;
  private TXScrollViewBase.ISmoothScrollRunnableListener i;

  public TXScrollViewBase$SmoothScrollRunnable(TXScrollViewBase paramTXScrollViewBase, int paramInt1, int paramInt2, long paramLong, TXScrollViewBase.ISmoothScrollRunnableListener paramISmoothScrollRunnableListener)
  {
    this.d = paramInt1;
    this.c = paramInt2;
    this.e = paramLong;
    this.i = paramISmoothScrollRunnableListener;
    this.b = TXScrollViewBase.a(paramTXScrollViewBase);
  }

  public void a()
  {
    this.f = false;
    this.a.removeCallbacks(this);
  }

  public void run()
  {
    if (this.g == -1L)
    {
      this.g = System.currentTimeMillis();
      if ((this.f != true) || (this.c == this.h))
        break label150;
      String str = Build.MODEL;
      if ((TextUtils.isEmpty(str)) || (!str.contains("OZZO138T")))
        ViewCompat.postOnAnimation(this.a, this);
    }
    label150: 
    do
    {
      return;
      long l = Math.max(Math.min(1000L * (System.currentTimeMillis() - this.g) / this.e, 1000L), 0L);
      int j = Math.round((this.d - this.c) * this.b.getInterpolation((float)l / 1000.0F));
      this.h = (this.d - j);
      this.a.a(this.h);
      break;
    }
    while (this.i == null);
    this.i.a();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.ui.widget.txscrollview.TXScrollViewBase.SmoothScrollRunnable
 * JD-Core Version:    0.6.0
 */