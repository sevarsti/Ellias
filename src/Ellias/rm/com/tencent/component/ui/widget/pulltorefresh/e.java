package com.tencent.component.ui.widget.pulltorefresh;

import android.view.animation.Interpolator;
import android.view.animation.OvershootInterpolator;

final class e
  implements Runnable
{
  static final int a = 300;
  static final int b = 10;
  static final float c;
  private final Interpolator e;
  private final int f;
  private final int g;
  private boolean h = true;
  private long i = -1L;
  private int j = -1;
  private int k = 300;

  public e(PullToRefreshBase paramPullToRefreshBase, int paramInt1, int paramInt2)
  {
    this.g = paramInt1;
    this.f = paramInt2;
    this.e = new OvershootInterpolator(0.0F);
  }

  public e(PullToRefreshBase paramPullToRefreshBase, int paramInt1, int paramInt2, int paramInt3)
  {
    this(paramPullToRefreshBase, paramInt1, paramInt2);
    this.k = paramInt3;
  }

  public e(PullToRefreshBase paramPullToRefreshBase, int paramInt1, int paramInt2, int paramInt3, Interpolator paramInterpolator)
  {
    this.g = paramInt1;
    this.f = paramInt2;
    this.k = paramInt3;
    this.e = paramInterpolator;
  }

  public void a()
  {
    this.h = false;
    this.d.removeCallbacks(this);
  }

  public void run()
  {
    long l1 = System.currentTimeMillis();
    if (this.i == -1L)
      this.i = l1;
    while (true)
    {
      if ((this.h) && (l1 - this.i < this.k))
        this.d.postDelayed(this, 10L);
      return;
      long l2 = Math.max(Math.min(1000L * (l1 - this.i) / this.k, 1000L), 0L);
      int m = Math.round((this.g - this.f) * this.e.getInterpolation((float)l2 / 1000.0F));
      this.j = (this.g - m);
      this.d.setHeaderScroll(this.j);
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.ui.widget.pulltorefresh.e
 * JD-Core Version:    0.6.0
 */