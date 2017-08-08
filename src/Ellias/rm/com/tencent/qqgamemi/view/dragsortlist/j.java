package com.tencent.qqgamemi.view.dragsortlist;

class j extends l
{
  private float d;
  private float e;

  public j(DragSortListView paramDragSortListView, float paramFloat, int paramInt)
  {
    super(paramDragSortListView, paramFloat, paramInt);
  }

  public void a()
  {
    this.d = DragSortListView.c(this.a);
    this.e = DragSortListView.d(this.a);
  }

  public void a(float paramFloat1, float paramFloat2)
  {
    if (DragSortListView.b(this.a) != 4)
    {
      d();
      return;
    }
    DragSortListView.a(this.a, (int)(paramFloat2 * this.e + (1.0F - paramFloat2) * this.d));
    DragSortListView.e(this.a).y = (DragSortListView.f(this.a) - DragSortListView.c(this.a));
    DragSortListView.a(this.a, true);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.view.dragsortlist.j
 * JD-Core Version:    0.6.0
 */