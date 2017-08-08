package com.tenpay.tenpayplugin.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.Scroller;

public class FlowView extends ViewGroup
{
  public static final String TAG = "log";
  private int a = -1;
  private int b = 1;
  private Scroller c;
  private VelocityTracker d;
  private int e = 0;
  private float f;
  private boolean g;
  private boolean h;
  private FlowView.FlowViewListener i;
  protected Context mContext;
  protected int mCurrentScreen;
  protected FlowViewIndicator mFlowViewIndicator;
  protected int mTouchSlop;

  public FlowView(Context paramContext)
  {
    super(paramContext);
    a(paramContext);
  }

  public FlowView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    a(paramContext);
  }

  private void a()
  {
    int j = getWidth();
    a((getScrollX() + j / 2) / j);
  }

  private void a(int paramInt)
  {
    if (!this.c.isFinished())
      return;
    int j = Math.max(0, Math.min(paramInt, -1 + getChildCount()));
    this.a = j;
    int k = j * getWidth() - getScrollX();
    this.c.startScroll(getScrollX(), 0, k, 0, 2 * Math.abs(k));
    this.i.onChanged(j);
    invalidate();
  }

  private void a(Context paramContext)
  {
    this.mContext = paramContext;
    this.mCurrentScreen = 0;
    this.c = new Scroller(getContext());
    this.mTouchSlop = ViewConfiguration.get(paramContext).getScaledTouchSlop();
    if (this.mTouchSlop == 0)
      this.mTouchSlop = 16;
  }

  public void computeScroll()
  {
    if (this.c.computeScrollOffset())
    {
      scrollTo(this.c.getCurrX(), this.c.getCurrY());
      postInvalidate();
    }
    do
    {
      do
        return;
      while (this.a == -1);
      this.mCurrentScreen = Math.max(0, Math.min(this.a, -1 + getChildCount()));
      this.a = -1;
    }
    while (this.mFlowViewIndicator == null);
    this.mFlowViewIndicator.onSwitched(getDisplayedChild());
  }

  public int getDisplayedChild()
  {
    return this.mCurrentScreen;
  }

  public int getTotalChildViewsNum()
  {
    return this.b;
  }

  public boolean onInterceptTouchEvent(MotionEvent paramMotionEvent)
  {
    int j = 1;
    if (this.d == null)
      this.d = VelocityTracker.obtain();
    this.d.addMovement(paramMotionEvent);
    int k = paramMotionEvent.getAction();
    float f1 = paramMotionEvent.getX();
    switch (k)
    {
    default:
    case 0:
    case 2:
    case 1:
    case 3:
    }
    while (true)
    {
      j = 0;
      int i1;
      label178: label229: int i3;
      do
      {
        int i2;
        do
        {
          do
          {
            return j;
            this.f = f1;
            this.e = 0;
            break;
            if ((int)Math.abs(f1 - this.f) > this.mTouchSlop);
            for (int n = j; ; n = 0)
            {
              if (n != 0)
                this.e = j;
              if (this.e != j)
                break;
              i1 = (int)(this.f - f1);
              this.f = f1;
              i2 = getScrollX();
              if (i1 >= 0)
                break label229;
              if (i2 <= 0)
                break label178;
              scrollBy(Math.max(-i2, i1), 0);
              return j;
            }
          }
          while ((i2 + Math.max(-getWidth() / 3, i1) <= -getWidth() / 3) || (this.g));
          scrollBy(Math.max(-getWidth() / 3, i1), 0);
          return j;
        }
        while ((i1 <= 0) || ((this.h) && (i2 >= getChildAt(-1 + getChildCount()).getLeft())));
        i3 = getChildAt(-1 + getChildCount()).getRight() - i2 - 2 * getWidth() / 3;
      }
      while (i3 <= 0);
      scrollBy(Math.min(i3, i1), 0);
      return j;
      int m;
      if (this.e == j)
      {
        VelocityTracker localVelocityTracker = this.d;
        localVelocityTracker.computeCurrentVelocity(1000);
        m = (int)localVelocityTracker.getXVelocity();
        if (m <= 600)
          break label380;
        a(-1 + this.mCurrentScreen);
      }
      while (true)
      {
        if (this.d != null)
        {
          this.d.recycle();
          this.d = null;
        }
        this.e = 0;
        break;
        label380: if (m < -600)
        {
          a(1 + this.mCurrentScreen);
          continue;
        }
        a();
      }
      this.e = 0;
    }
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    int j = getChildCount();
    int k = 0;
    int m = 0;
    while (true)
    {
      if (k >= j)
        return;
      View localView = getChildAt(k);
      if (localView.getVisibility() != 8)
      {
        int n = localView.getMeasuredWidth();
        localView.layout(m, 0, m + n, localView.getMeasuredHeight());
        m += n;
      }
      k++;
    }
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    super.onMeasure(paramInt1, paramInt2);
    int j = getChildCount();
    for (int k = 0; ; k++)
    {
      if (k >= j)
        return;
      getChildAt(k).measure(paramInt1, paramInt2);
    }
  }

  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    if (this.d == null)
      this.d = VelocityTracker.obtain();
    this.d.addMovement(paramMotionEvent);
    int j = paramMotionEvent.getAction();
    float f1 = paramMotionEvent.getX();
    switch (j)
    {
    default:
    case 0:
    case 2:
      int n;
      int i2;
      do
      {
        int i1;
        do
          while (true)
          {
            return true;
            this.f = f1;
            this.e = 0;
            return true;
            if ((int)Math.abs(f1 - this.f) > this.mTouchSlop);
            for (int m = 1; ; m = 0)
            {
              if (m != 0)
                this.e = 1;
              if (this.e != 1)
                break;
              n = (int)(this.f - f1);
              this.f = f1;
              i1 = getScrollX();
              if (n >= 0)
                break label218;
              if (i1 <= 0)
                break label167;
              scrollBy(Math.max(-i1, n), 0);
              return true;
            }
            if ((i1 + Math.max(-getWidth() / 3, n) <= -getWidth() / 3) || (this.g))
              continue;
            scrollBy(Math.max(-getWidth() / 3, n), 0);
            return true;
          }
        while ((n <= 0) || ((this.h) && (i1 >= getChildAt(-1 + getChildCount()).getLeft())));
        i2 = getChildAt(-1 + getChildCount()).getRight() - i1 - 2 * getWidth() / 3;
      }
      while (i2 <= 0);
      scrollBy(Math.min(i2, n), 0);
      return true;
    case 1:
      label167: label218: int k;
      if (this.e == 1)
      {
        VelocityTracker localVelocityTracker = this.d;
        localVelocityTracker.computeCurrentVelocity(1000);
        k = (int)localVelocityTracker.getXVelocity();
        if ((k <= 600) || (this.mCurrentScreen <= 0))
          break label375;
        a(-1 + this.mCurrentScreen);
      }
      while (true)
      {
        if (this.d != null)
        {
          this.d.recycle();
          this.d = null;
        }
        this.e = 0;
        return true;
        label375: if (k < -600)
        {
          a(1 + this.mCurrentScreen);
          continue;
        }
        a();
      }
    case 3:
    }
    this.e = 0;
    return true;
  }

  public void setFirstScrollRihgt(boolean paramBoolean)
  {
    this.g = paramBoolean;
  }

  public void setFlowViewIndicator(FlowViewIndicator paramFlowViewIndicator)
  {
    this.mFlowViewIndicator = paramFlowViewIndicator;
  }

  public void setFlowViewListener(FlowView.FlowViewListener paramFlowViewListener)
  {
    this.i = paramFlowViewListener;
  }

  public void setLastScrollLeft(boolean paramBoolean)
  {
    this.h = paramBoolean;
  }

  public void setVisibleView(int paramInt, boolean paramBoolean)
  {
    this.mCurrentScreen = Math.max(0, Math.min(paramInt, -1 + getChildCount()));
    int j = this.mCurrentScreen * getWidth() - this.c.getCurrX();
    this.c.startScroll(this.c.getCurrX(), this.c.getCurrY(), j, 0, 0);
    if (paramBoolean)
    {
      invalidate();
      return;
    }
    postInvalidate();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tenpay.tenpayplugin.view.FlowView
 * JD-Core Version:    0.6.0
 */