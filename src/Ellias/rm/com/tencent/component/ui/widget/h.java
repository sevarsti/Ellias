package com.tencent.component.ui.widget;

import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import java.util.List;

class h
  implements AbsListView.OnScrollListener
{
  h(NotifyingListView paramNotifyingListView)
  {
  }

  public void onScroll(AbsListView paramAbsListView, int paramInt1, int paramInt2, int paramInt3)
  {
    for (int i = 0; i < NotifyingListView.a(this.a).size(); i++)
      ((AbsListView.OnScrollListener)NotifyingListView.a(this.a).get(i)).onScroll(paramAbsListView, paramInt1, paramInt2, paramInt3);
  }

  public void onScrollStateChanged(AbsListView paramAbsListView, int paramInt)
  {
    for (int i = 0; i < NotifyingListView.a(this.a).size(); i++)
      ((AbsListView.OnScrollListener)NotifyingListView.a(this.a).get(i)).onScrollStateChanged(paramAbsListView, paramInt);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.ui.widget.h
 * JD-Core Version:    0.6.0
 */