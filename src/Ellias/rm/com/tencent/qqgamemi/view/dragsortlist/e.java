package com.tencent.qqgamemi.view.dragsortlist;

import android.database.DataSetObserver;

class e extends DataSetObserver
{
  e(d paramd, DragSortListView paramDragSortListView)
  {
  }

  public void onChanged()
  {
    this.b.notifyDataSetChanged();
  }

  public void onInvalidated()
  {
    this.b.notifyDataSetInvalidated();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.view.dragsortlist.e
 * JD-Core Version:    0.6.0
 */