package com.tencent.qqgamemi.view.dragsortlist;

import android.os.SystemClock;
import android.view.View;

class f
  implements Runnable
{
  public static final int a = -1;
  public static final int b = 0;
  public static final int c = 1;
  private boolean e;
  private long f;
  private long g;
  private int h;
  private float i;
  private long j;
  private int k;
  private float l;
  private boolean m = false;
  private int n;
  private int o;

  public f(DragSortListView paramDragSortListView)
  {
  }

  public void a(int paramInt)
  {
    if (!this.m)
    {
      this.e = false;
      this.m = true;
      this.j = SystemClock.uptimeMillis();
      this.f = this.j;
      this.k = paramInt;
      this.d.post(this);
    }
  }

  public void a(boolean paramBoolean)
  {
    if (paramBoolean)
    {
      this.d.removeCallbacks(this);
      this.m = false;
      return;
    }
    this.e = true;
  }

  public boolean a()
  {
    return this.m;
  }

  public int b()
  {
    if (this.m)
      return this.k;
    return -1;
  }

  public void run()
  {
    if (this.e)
    {
      this.m = false;
      return;
    }
    int i1 = this.d.getFirstVisiblePosition();
    int i2 = this.d.getLastVisiblePosition();
    int i3 = this.d.getCount();
    int i4 = this.d.getPaddingTop();
    int i5 = this.d.getHeight() - i4 - this.d.getPaddingBottom();
    int i6 = Math.min(DragSortListView.f(this.d), DragSortListView.r(this.d) + DragSortListView.d(this.d));
    int i7 = Math.max(DragSortListView.f(this.d), DragSortListView.r(this.d) - DragSortListView.d(this.d));
    if (this.k == 0)
    {
      View localView3 = this.d.getChildAt(0);
      if (localView3 == null)
      {
        this.m = false;
        return;
      }
      if ((i1 == 0) && (localView3.getTop() == i4))
      {
        this.m = false;
        return;
      }
      this.l = DragSortListView.u(this.d).a((DragSortListView.s(this.d) - i7) / DragSortListView.t(this.d), this.f);
      this.g = SystemClock.uptimeMillis();
      this.i = (float)(this.g - this.f);
      this.h = Math.round(this.l * this.i);
      if (this.h < 0)
        break label471;
      this.h = Math.min(i5, this.h);
      i2 = i1;
    }
    while (true)
    {
      View localView2 = this.d.getChildAt(i2 - i1);
      int i8 = localView2.getTop() + this.h;
      if ((i2 == 0) && (i8 > i4))
        i8 = i4;
      DragSortListView.b(this.d, true);
      this.d.setSelectionFromTop(i2, i8 - i4);
      this.d.layoutChildren();
      this.d.invalidate();
      DragSortListView.b(this.d, false);
      DragSortListView.c(this.d, i2, localView2, false);
      this.f = this.g;
      this.d.post(this);
      return;
      View localView1 = this.d.getChildAt(i2 - i1);
      if (localView1 == null)
      {
        this.m = false;
        return;
      }
      if ((i2 == i3 - 1) && (localView1.getBottom() <= i5 + i4))
      {
        this.m = false;
        return;
      }
      this.l = (-DragSortListView.u(this.d).a((i6 - DragSortListView.v(this.d)) / DragSortListView.w(this.d), this.f));
      break;
      label471: this.h = Math.max(-i5, this.h);
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.view.dragsortlist.f
 * JD-Core Version:    0.6.0
 */