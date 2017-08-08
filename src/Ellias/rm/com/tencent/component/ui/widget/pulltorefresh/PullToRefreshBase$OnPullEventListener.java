package com.tencent.component.ui.widget.pulltorefresh;

import com.tencent.component.annotation.PluginApi;

@PluginApi(a=6)
public abstract interface PullToRefreshBase$OnPullEventListener
{
  @PluginApi(a=6)
  public abstract void onPullChanged(PullToRefreshBase paramPullToRefreshBase, PullToRefreshBase.Direction paramDirection, float paramFloat);

  @PluginApi(a=6)
  public abstract void onPullEnd(PullToRefreshBase paramPullToRefreshBase, PullToRefreshBase.Direction paramDirection);

  @PluginApi(a=6)
  public abstract void onPullStart(PullToRefreshBase paramPullToRefreshBase, PullToRefreshBase.Direction paramDirection);
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.ui.widget.pulltorefresh.PullToRefreshBase.OnPullEventListener
 * JD-Core Version:    0.6.0
 */