package com.tencent.qqgamemi.view;

import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;

class b
  implements AbsListView.OnScrollListener
{
  b(PinnedSectionListView paramPinnedSectionListView)
  {
  }

  public void onScroll(AbsListView paramAbsListView, int paramInt1, int paramInt2, int paramInt3)
  {
    if (this.a.a != null)
      this.a.a.onScroll(paramAbsListView, paramInt1, paramInt2, paramInt3);
    if (((PinnedSectionListView.PinnedSectionListAdapter)paramAbsListView.getAdapter() == null) || (paramInt2 == 0));
    int i;
    int j;
    int k;
    while (true)
    {
      return;
      i = PinnedSectionListView.a(this.a, paramInt1, paramInt2);
      if (i == -1)
      {
        int i2 = PinnedSectionListView.a(this.a, paramInt1);
        if (i2 == -1)
          continue;
        if (this.a.c != null)
        {
          if (this.a.c.b == i2)
          {
            this.a.d = 0;
            return;
          }
          PinnedSectionListView.a(this.a);
        }
        PinnedSectionListView.b(this.a, i2);
        return;
      }
      j = paramAbsListView.getChildAt(i - paramInt1).getTop();
      k = this.a.getListPaddingTop();
      if (this.a.c == null)
      {
        if (j >= k)
          continue;
        PinnedSectionListView.b(this.a, i);
        return;
      }
      if (i != this.a.c.b)
        break;
      if (j <= k)
        continue;
      PinnedSectionListView.a(this.a);
      int n = PinnedSectionListView.a(this.a, i - 1);
      if (n <= -1)
        continue;
      PinnedSectionListView.b(this.a, n);
      int i1 = j - k - this.a.c.a.getHeight();
      if (i1 > 0)
        i1 = 0;
      this.a.d = i1;
      return;
    }
    int m = k + this.a.c.a.getHeight();
    if (j < m)
    {
      if (j < k)
      {
        PinnedSectionListView.a(this.a);
        PinnedSectionListView.b(this.a, i);
        return;
      }
      this.a.d = (j - m);
      return;
    }
    this.a.d = 0;
  }

  public void onScrollStateChanged(AbsListView paramAbsListView, int paramInt)
  {
    if (this.a.a != null)
      this.a.a.onScrollStateChanged(paramAbsListView, paramInt);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.view.b
 * JD-Core Version:    0.6.0
 */