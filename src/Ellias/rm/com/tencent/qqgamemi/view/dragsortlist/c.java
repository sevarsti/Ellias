package com.tencent.qqgamemi.view.dragsortlist;

import android.database.DataSetObserver;

class c extends DataSetObserver
{
  c(DragSortListView paramDragSortListView)
  {
  }

  private void a()
  {
    if (DragSortListView.b(this.a) == 4)
      this.a.a();
  }

  public void onChanged()
  {
    a();
  }

  public void onInvalidated()
  {
    a();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.view.dragsortlist.c
 * JD-Core Version:    0.6.0
 */