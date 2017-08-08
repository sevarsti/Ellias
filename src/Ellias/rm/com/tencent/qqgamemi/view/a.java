package com.tencent.qqgamemi.view;

import android.database.DataSetObserver;

class a extends DataSetObserver
{
  a(PinnedSectionListView paramPinnedSectionListView)
  {
  }

  public void onChanged()
  {
    PinnedSectionListView.a(this.a);
  }

  public void onInvalidated()
  {
    PinnedSectionListView.a(this.a);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.view.a
 * JD-Core Version:    0.6.0
 */