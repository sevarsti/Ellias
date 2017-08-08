package com.tencent.qqgamemi.view.dragsortlist;

import android.graphics.Point;
import android.view.View;

class h extends l
{
  private int d;
  private int e;
  private float f;
  private float g;

  public h(DragSortListView paramDragSortListView, float paramFloat, int paramInt)
  {
    super(paramDragSortListView, paramFloat, paramInt);
  }

  private int e()
  {
    int i = this.a.getFirstVisiblePosition();
    int j = (DragSortListView.i(this.a) + this.a.getDividerHeight()) / 2;
    View localView = this.a.getChildAt(this.d - i);
    if (localView != null)
    {
      if (this.d == this.e)
        return localView.getTop();
      if (this.d < this.e)
        return localView.getTop() - j;
      return j + localView.getBottom() - DragSortListView.j(this.a);
    }
    d();
    return -1;
  }

  public void a()
  {
    this.d = DragSortListView.g(this.a);
    this.e = DragSortListView.h(this.a);
    DragSortListView.b(this.a, 2);
    this.f = (DragSortListView.e(this.a).y - e());
    this.g = (DragSortListView.e(this.a).x - this.a.getPaddingLeft());
  }

  public void a(float paramFloat1, float paramFloat2)
  {
    int i = e();
    int j = this.a.getPaddingLeft();
    float f1 = DragSortListView.e(this.a).y - i;
    float f2 = DragSortListView.e(this.a).x - j;
    float f3 = 1.0F - paramFloat2;
    if ((f3 < Math.abs(f1 / this.f)) || (f3 < Math.abs(f2 / this.g)))
    {
      DragSortListView.e(this.a).y = (i + (int)(f3 * this.f));
      DragSortListView.e(this.a).x = (this.a.getPaddingLeft() + (int)(f3 * this.g));
      DragSortListView.a(this.a, true);
    }
  }

  public void b()
  {
    DragSortListView.k(this.a);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.view.dragsortlist.h
 * JD-Core Version:    0.6.0
 */