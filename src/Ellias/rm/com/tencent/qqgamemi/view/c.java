package com.tencent.qqgamemi.view;

import android.view.View;

class c
  implements Runnable
{
  c(PinnedSectionListView paramPinnedSectionListView)
  {
  }

  public void run()
  {
    if (this.a.getAdapter() == null);
    int i;
    int j;
    do
    {
      return;
      i = this.a.getFirstVisiblePosition();
      j = PinnedSectionListView.a(this.a, i);
    }
    while (j == -1);
    if (i == j)
    {
      PinnedSectionListView.b(this.a, i);
      View localView = this.a.getChildAt(i);
      PinnedSectionListView localPinnedSectionListView = this.a;
      if (localView == null);
      for (int k = 0; ; k = -localView.getTop())
      {
        localPinnedSectionListView.d = k;
        return;
      }
    }
    PinnedSectionListView.b(this.a, j);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.view.c
 * JD-Core Version:    0.6.0
 */