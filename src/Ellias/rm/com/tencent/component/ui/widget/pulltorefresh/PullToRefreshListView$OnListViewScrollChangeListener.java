package com.tencent.component.ui.widget.pulltorefresh;

import android.widget.ListView;
import com.tencent.component.annotation.PluginApi;

@PluginApi(a=6)
public abstract interface PullToRefreshListView$OnListViewScrollChangeListener
{
  @PluginApi(a=6)
  public abstract void onScrollChanged(ListView paramListView, int paramInt1, int paramInt2, int paramInt3, int paramInt4);
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.ui.widget.pulltorefresh.PullToRefreshListView.OnListViewScrollChangeListener
 * JD-Core Version:    0.6.0
 */