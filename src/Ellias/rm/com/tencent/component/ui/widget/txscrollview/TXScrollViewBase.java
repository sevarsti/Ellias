package com.tencent.component.ui.widget.txscrollview;

import android.content.Context;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

public abstract class TXScrollViewBase extends LinearLayout
{
  public static final int a = 200;
  public static final float b = 2.0F;
  protected boolean c = false;
  protected TXScrollViewBase.ScrollState d = TXScrollViewBase.ScrollState.ScrollState_Initial;
  protected TXScrollViewBase.ScrollMode e = TXScrollViewBase.ScrollMode.BOTH;
  protected TXScrollViewBase.ScrollDirection f = TXScrollViewBase.ScrollDirection.SCROLL_DIRECTION_VERTICAL;
  protected PointF g = new PointF(0.0F, 0.0F);
  protected PointF h = new PointF(0.0F, 0.0F);
  protected View i;
  protected FrameLayout j;
  private int k = 0;
  private Interpolator l = new DecelerateInterpolator();
  private TXScrollViewBase.SmoothScrollRunnable m = null;
  private View n = null;

  public TXScrollViewBase(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    this.e = TXScrollViewBase.ScrollMode.a(3);
    c(paramContext);
  }

  public TXScrollViewBase(Context paramContext, TXScrollViewBase.ScrollDirection paramScrollDirection, TXScrollViewBase.ScrollMode paramScrollMode)
  {
    super(paramContext);
    this.f = paramScrollDirection;
    this.e = paramScrollMode;
    c(paramContext);
  }

  private void a(Context paramContext, View paramView)
  {
    if (paramView == null)
      return;
    this.j = new FrameLayout(paramContext);
    this.j.addView(paramView, -1, -1);
    LinearLayout.LayoutParams localLayoutParams = new LinearLayout.LayoutParams(-1, -1);
    super.addView(this.j, -1, localLayoutParams);
  }

  protected void a(int paramInt)
  {
    int i1 = getMaximumScrollOffset();
    int i2 = Math.min(i1, Math.max(-i1, paramInt));
    if (this.f == TXScrollViewBase.ScrollDirection.SCROLL_DIRECTION_HORIZONTAL)
    {
      scrollTo(i2, 0);
      return;
    }
    scrollTo(0, i2);
  }

  protected void a(int paramInt1, int paramInt2)
  {
    b(paramInt1, paramInt2);
  }

  public final void a(int paramInt, long paramLong1, long paramLong2, TXScrollViewBase.ISmoothScrollRunnableListener paramISmoothScrollRunnableListener)
  {
    if (this.m != null)
      this.m.a();
    if (this.f == TXScrollViewBase.ScrollDirection.SCROLL_DIRECTION_HORIZONTAL);
    for (int i1 = getScrollX(); ; i1 = getScrollY())
    {
      if (i1 != paramInt)
      {
        this.m = new TXScrollViewBase.SmoothScrollRunnable(this, i1, paramInt, paramLong1, paramISmoothScrollRunnableListener);
        if (paramLong2 <= 0L)
          break;
        postDelayed(this.m, paramLong2);
      }
      return;
    }
    post(this.m);
  }

  public final void a(int paramInt, TXScrollViewBase.ISmoothScrollRunnableListener paramISmoothScrollRunnableListener)
  {
    a(paramInt, 200L, 0L, paramISmoothScrollRunnableListener);
  }

  protected void a(View paramView, int paramInt, ViewGroup.LayoutParams paramLayoutParams)
  {
    super.addView(paramView, paramInt, paramLayoutParams);
  }

  protected abstract boolean a();

  public void addView(View paramView, int paramInt, ViewGroup.LayoutParams paramLayoutParams)
  {
    if ((paramView != null) && (this.i != null) && ((this.i instanceof ViewGroup)))
      ((ViewGroup)this.i).addView(paramView, paramInt, paramLayoutParams);
  }

  protected abstract View b(Context paramContext);

  public final void b(int paramInt)
  {
    a(paramInt, 200L, 0L, null);
  }

  protected final void b(int paramInt1, int paramInt2)
  {
    if (this.j == null);
    LinearLayout.LayoutParams localLayoutParams;
    do
      while (true)
      {
        return;
        localLayoutParams = (LinearLayout.LayoutParams)this.j.getLayoutParams();
        if (this.f != TXScrollViewBase.ScrollDirection.SCROLL_DIRECTION_HORIZONTAL)
          break;
        if (localLayoutParams.width == paramInt1)
          continue;
        localLayoutParams.width = paramInt1;
        this.j.requestLayout();
        return;
      }
    while (localLayoutParams.height == paramInt2);
    localLayoutParams.height = paramInt2;
    this.j.requestLayout();
  }

  protected abstract boolean b();

  protected void c(Context paramContext)
  {
    if (this.f == TXScrollViewBase.ScrollDirection.SCROLL_DIRECTION_HORIZONTAL)
      setOrientation(0);
    while (true)
    {
      setGravity(17);
      this.k = ViewConfiguration.get(paramContext).getScaledTouchSlop();
      this.i = b(paramContext);
      a(paramContext, this.i);
      return;
      setOrientation(1);
    }
  }

  protected boolean c()
  {
    if (this.c == true)
    {
      this.c = false;
      b(0);
      return true;
    }
    return false;
  }

  protected boolean d()
  {
    if (a() == true);
    do
      return true;
    while (b() == true);
    return false;
  }

  protected int e()
  {
    float f1;
    float f2;
    if (this.f == TXScrollViewBase.ScrollDirection.SCROLL_DIRECTION_HORIZONTAL)
    {
      f1 = this.g.x;
      f2 = this.h.x;
      if (this.d != TXScrollViewBase.ScrollState.ScrollState_FromStart)
        break label75;
    }
    label75: for (int i1 = Math.round(Math.min(f1 - f2, 0.0F) / 2.0F); ; i1 = Math.round(Math.max(f1 - f2, 0.0F) / 2.0F))
    {
      a(i1);
      return i1;
      f1 = this.g.y;
      f2 = this.h.y;
      break;
    }
  }

  public void f()
  {
  }

  protected int getMaximumScrollOffset()
  {
    if (this.f == TXScrollViewBase.ScrollDirection.SCROLL_DIRECTION_HORIZONTAL)
      return Math.round(getWidth() / 2.0F);
    return Math.round(getHeight() / 2.0F);
  }

  public final boolean onInterceptTouchEvent(MotionEvent paramMotionEvent)
  {
    int i1 = paramMotionEvent.getAction();
    if ((i1 == 3) || (i1 == 1))
    {
      this.c = false;
      return false;
    }
    if ((this.c == true) && (i1 != 0))
      return true;
    switch (i1)
    {
    case 1:
    default:
    case 2:
    case 0:
    }
    while (true)
    {
      return this.c;
      if (d() != true)
        continue;
      float f3 = paramMotionEvent.getX();
      float f4 = paramMotionEvent.getY();
      float f5;
      float f6;
      if (this.f == TXScrollViewBase.ScrollDirection.SCROLL_DIRECTION_HORIZONTAL)
      {
        f5 = f3 - this.h.x;
        f6 = f4 - this.h.y;
      }
      while (true)
      {
        float f7 = Math.abs(f5);
        if ((f7 <= this.k) || (f7 <= Math.abs(f6)))
          break;
        if ((f5 >= 1.0F) && (a()))
        {
          this.h.x = f3;
          this.h.y = f4;
          this.c = true;
          this.d = TXScrollViewBase.ScrollState.ScrollState_FromStart;
        }
        if ((f5 > -1.0F) || (!b()))
          break;
        this.h.x = f3;
        this.h.y = f4;
        this.c = true;
        this.d = TXScrollViewBase.ScrollState.ScrollState_FromEnd;
        break;
        f5 = f4 - this.h.y;
        f6 = f3 - this.h.x;
      }
      if (d() != true)
        continue;
      PointF localPointF1 = this.h;
      PointF localPointF2 = this.g;
      float f1 = paramMotionEvent.getX();
      localPointF2.x = f1;
      localPointF1.x = f1;
      PointF localPointF3 = this.h;
      PointF localPointF4 = this.g;
      float f2 = paramMotionEvent.getY();
      localPointF4.y = f2;
      localPointF3.y = f2;
      this.c = false;
    }
  }

  protected void onSizeChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onSizeChanged(paramInt1, paramInt2, paramInt3, paramInt4);
    a(paramInt1, paramInt2);
    post(new a(this));
  }

  public final boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    if ((paramMotionEvent.getAction() == 0) && (paramMotionEvent.getEdgeFlags() != 0));
    do
    {
      do
      {
        return false;
        switch (paramMotionEvent.getAction())
        {
        default:
          return false;
        case 0:
        case 1:
        case 3:
        case 2:
        }
      }
      while (d() != true);
      PointF localPointF1 = this.h;
      PointF localPointF2 = this.g;
      float f1 = paramMotionEvent.getX();
      localPointF2.x = f1;
      localPointF1.x = f1;
      PointF localPointF3 = this.h;
      PointF localPointF4 = this.g;
      float f2 = paramMotionEvent.getY();
      localPointF4.y = f2;
      localPointF3.y = f2;
      return true;
      return c();
    }
    while (this.c != true);
    this.h.x = paramMotionEvent.getX();
    this.h.y = paramMotionEvent.getY();
    e();
    return true;
  }

  public void setTipsView(View paramView)
  {
    if (this.j == null);
    do
    {
      return;
      if (this.n != null)
        this.j.removeView(this.n);
      this.n = paramView;
    }
    while (this.n == null);
    this.j.addView(this.n, -1, -1);
    this.n.bringToFront();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.ui.widget.txscrollview.TXScrollViewBase
 * JD-Core Version:    0.6.0
 */