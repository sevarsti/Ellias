package com.tencent.qqgamemi.view.dragsortlist;

import android.graphics.Point;
import android.os.SystemClock;
import android.view.View;
import android.view.ViewGroup.LayoutParams;

class k extends l
{
  private float d;
  private float e;
  private float f;
  private int g = -1;
  private int h = -1;
  private int i;
  private int j;
  private int k;

  public k(DragSortListView paramDragSortListView, float paramFloat, int paramInt)
  {
    super(paramDragSortListView, paramFloat, paramInt);
  }

  public void a()
  {
    int m = -1;
    this.g = m;
    this.h = m;
    this.i = DragSortListView.l(this.a);
    this.j = DragSortListView.m(this.a);
    this.k = DragSortListView.h(this.a);
    DragSortListView.b(this.a, 1);
    this.d = DragSortListView.e(this.a).x;
    if (DragSortListView.n(this.a))
    {
      float f1 = 2.0F * this.a.getWidth();
      if (DragSortListView.o(this.a) == 0.0F)
      {
        DragSortListView localDragSortListView = this.a;
        if (this.d < 0.0F)
          DragSortListView.a(localDragSortListView, f1 * m);
      }
      float f2;
      do
      {
        return;
        m = 1;
        break;
        f2 = f1 * 2.0F;
        if ((DragSortListView.o(this.a) >= 0.0F) || (DragSortListView.o(this.a) <= -f2))
          continue;
        DragSortListView.a(this.a, -f2);
        return;
      }
      while ((DragSortListView.o(this.a) <= 0.0F) || (DragSortListView.o(this.a) >= f2));
      DragSortListView.a(this.a, f2);
      return;
    }
    DragSortListView.p(this.a);
  }

  public void a(float paramFloat1, float paramFloat2)
  {
    float f1 = 1.0F - paramFloat2;
    int m = this.a.getFirstVisiblePosition();
    View localView1 = this.a.getChildAt(this.i - m);
    float f2;
    if (DragSortListView.n(this.a))
    {
      f2 = (float)(SystemClock.uptimeMillis() - this.b) / 1000.0F;
      if (f2 != 0.0F);
    }
    View localView2;
    do
    {
      do
      {
        return;
        float f3 = f2 * DragSortListView.o(this.a);
        int i2 = this.a.getWidth();
        DragSortListView localDragSortListView = this.a;
        if (DragSortListView.o(this.a) > 0.0F);
        for (int i3 = 1; ; i3 = -1)
        {
          DragSortListView.b(localDragSortListView, f2 * i3 * i2);
          this.d = (f3 + this.d);
          DragSortListView.e(this.a).x = (int)this.d;
          if ((this.d >= i2) || (this.d <= -i2))
            break;
          this.b = SystemClock.uptimeMillis();
          DragSortListView.a(this.a, true);
          return;
        }
        if (localView1 == null)
          continue;
        if (this.g == -1)
        {
          this.g = DragSortListView.b(this.a, this.i, localView1, false);
          this.e = (localView1.getHeight() - this.g);
        }
        int i1 = Math.max((int)(f1 * this.e), 1);
        ViewGroup.LayoutParams localLayoutParams2 = localView1.getLayoutParams();
        localLayoutParams2.height = (i1 + this.g);
        localView1.setLayoutParams(localLayoutParams2);
      }
      while (this.j == this.i);
      localView2 = this.a.getChildAt(this.j - m);
    }
    while (localView2 == null);
    if (this.h == -1)
    {
      this.h = DragSortListView.b(this.a, this.j, localView2, false);
      this.f = (localView2.getHeight() - this.h);
    }
    int n = Math.max((int)(f1 * this.f), 1);
    ViewGroup.LayoutParams localLayoutParams1 = localView2.getLayoutParams();
    localLayoutParams1.height = (n + this.h);
    localView2.setLayoutParams(localLayoutParams1);
  }

  public void b()
  {
    DragSortListView.q(this.a);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.view.dragsortlist.k
 * JD-Core Version:    0.6.0
 */