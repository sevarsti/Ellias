package com.tencent.component.ui.widget.pulltorefresh;

import com.tencent.component.annotation.PluginApi;

@PluginApi(a=6)
public abstract interface PullToRefreshBase$OnRefreshListener
{
  @PluginApi(a=6)
  public abstract void onRefresh(PullToRefreshBase paramPullToRefreshBase);

  @PluginApi(a=6)
  public abstract void onRefreshComplete(PullToRefreshBase paramPullToRefreshBase);
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.ui.widget.pulltorefresh.PullToRefreshBase.OnRefreshListener
 * JD-Core Version:    0.6.0
 */