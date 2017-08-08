package com.tencent.component.ui.widget;

import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListView;

public class ListViewScrollObserver
  implements AbsListView.OnScrollListener
{
  private ListViewScrollObserver.OnListViewScrollListener a;
  private int b;
  private int c;
  private int d;
  private int e;

  public ListViewScrollObserver(ListView paramListView)
  {
    paramListView.setOnScrollListener(this);
  }

  public void a(ListViewScrollObserver.OnListViewScrollListener paramOnListViewScrollListener)
  {
    this.a = paramOnListViewScrollListener;
  }

  public void onScroll(AbsListView paramAbsListView, int paramInt1, int paramInt2, int paramInt3)
  {
    View localView = paramAbsListView.getChildAt(0);
    if (localView == null)
      return;
    int i = localView.getTop();
    int j = localView.getHeight();
    int m;
    int k;
    if (this.b == paramInt1)
    {
      m = this.c - i;
      k = 0;
    }
    while (true)
    {
      boolean bool = false;
      if (k == 0)
        bool = true;
      this.d += -m;
      if (this.a != null)
        this.a.a(-m, this.d, bool);
      this.b = paramInt1;
      this.c = i;
      this.e = localView.getHeight();
      return;
      if (paramInt1 > this.b)
      {
        k = -1 + (paramInt1 - this.b);
        m = j * k + this.e + this.c - i;
        continue;
      }
      k = -1 + (this.b - paramInt1);
      m = k * -j + this.c - (j + i);
    }
  }

  public void onScrollStateChanged(AbsListView paramAbsListView, int paramInt)
  {
    if ((this.a != null) && (paramInt == 0))
      this.a.a();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.ui.widget.ListViewScrollObserver
 * JD-Core Version:    0.6.0
 */