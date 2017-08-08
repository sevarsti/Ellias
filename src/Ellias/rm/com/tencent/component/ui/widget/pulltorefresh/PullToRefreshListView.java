package com.tencent.component.ui.widget.pulltorefresh;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.FrameLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.tencent.component.annotation.PluginApi;
import com.tencent.component.utils.PlatformUtil;

@PluginApi(a=6)
public class PullToRefreshListView extends PullToRefreshAdapterViewBase
{
  private LoadingLayout V;
  private LoadingLayout W;
  private FrameLayout Z;
  private PullToRefreshListView.OnListViewScrollChangeListener aa;

  @PluginApi(a=6)
  public PullToRefreshListView(Context paramContext)
  {
    super(paramContext);
    setDisableScrollingWhileRefreshing(false);
  }

  @PluginApi(a=6)
  public PullToRefreshListView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    setDisableScrollingWhileRefreshing(false);
  }

  @PluginApi(a=6)
  public PullToRefreshListView(Context paramContext, PullToRefreshBase.Mode paramMode)
  {
    super(paramContext, paramMode);
    setDisableScrollingWhileRefreshing(false);
  }

  @SuppressLint({"InlinedApi"})
  protected final ListView c(Context paramContext, AttributeSet paramAttributeSet)
  {
    g localg = new g(this, paramContext, paramAttributeSet);
    FrameLayout localFrameLayout1 = new FrameLayout(paramContext);
    this.V = new LoadingLayout(paramContext, PullToRefreshBase.Mode.PULL_DOWN_TO_REFRESH);
    LoadingLayout localLoadingLayout1 = this.V;
    if (PlatformUtil.version() >= 8);
    localFrameLayout1.addView(localLoadingLayout1, -1, -2);
    this.V.setVisibility(8);
    this.V.setPosition(0);
    localg.addHeaderView(localFrameLayout1, null, false);
    this.Z = new FrameLayout(paramContext);
    this.W = new LoadingLayout(paramContext, PullToRefreshBase.Mode.PULL_UP_TO_REFRESH);
    FrameLayout localFrameLayout2 = this.Z;
    LoadingLayout localLoadingLayout2 = this.W;
    if (PlatformUtil.version() >= 8);
    localFrameLayout2.addView(localLoadingLayout2, -1, -2);
    this.W.setVisibility(8);
    this.W.setPosition(1);
    ((g)localg).a(this.aa);
    localg.setId(16908298);
    return localg;
  }

  protected void e()
  {
    int i = 1;
    ListAdapter localListAdapter = ((ListView)this.A).getAdapter();
    if ((!getShowViewWhileRefreshing()) || (localListAdapter == null) || (localListAdapter.isEmpty()))
    {
      super.e();
      return;
    }
    int j = getHeaderHeight();
    LoadingLayout localLoadingLayout3;
    LoadingLayout localLoadingLayout4;
    int i1;
    switch (f.a[getCurrentMode().ordinal()])
    {
    default:
      localLoadingLayout3 = getHeaderLayout();
      localLoadingLayout4 = this.V;
      i1 = j * -1;
      if (((ListView)this.A).getFirstVisiblePosition() != 0)
        break;
    case 1:
    }
    while (true)
    {
      int m = i;
      Object localObject1 = localLoadingLayout4;
      int n = 0;
      Object localObject2 = localLoadingLayout3;
      ((LoadingLayout)localObject2).setVisibility(0);
      if (m != 0)
      {
        ((ListView)this.A).setSelection(n);
        setHeaderScroll(i1);
      }
      ((LoadingLayout)localObject1).setVisibility(8);
      super.e();
      return;
      LoadingLayout localLoadingLayout1 = getFooterLayout();
      LoadingLayout localLoadingLayout2 = this.W;
      int k = -1 + ((ListView)this.A).getCount();
      if (((ListView)this.A).getLastVisiblePosition() == k);
      for (m = i; ; m = 0)
      {
        n = k;
        i1 = j;
        localObject1 = localLoadingLayout2;
        localObject2 = localLoadingLayout1;
        break;
      }
      i = 0;
    }
  }

  public ContextMenu.ContextMenuInfo getContextMenuInfo()
  {
    return ((g)getRefreshableView()).getContextMenuInfo();
  }

  @PluginApi(a=6)
  public void setLastUpdatedLabel(CharSequence paramCharSequence)
  {
    super.setLastUpdatedLabel(paramCharSequence);
    if (this.V != null)
      this.V.setSubHeaderText(paramCharSequence);
    if (this.W != null)
      this.W.setSubHeaderText(paramCharSequence);
  }

  @PluginApi(a=6)
  public void setLoadingDrawable(Drawable paramDrawable, PullToRefreshBase.Mode paramMode)
  {
    super.setLoadingDrawable(paramDrawable, paramMode);
    if ((this.V != null) && (paramMode.canPullDown()))
      this.V.setLoadingDrawable(paramDrawable);
    if ((this.W != null) && (paramMode.canPullUp()))
      this.W.setLoadingDrawable(paramDrawable);
  }

  @PluginApi(a=6)
  public void setOnListViewScrollChangeListener(PullToRefreshListView.OnListViewScrollChangeListener paramOnListViewScrollChangeListener)
  {
    if ((ListView)getRefreshableView() != null)
      ((g)getRefreshableView()).a(paramOnListViewScrollChangeListener);
    this.aa = paramOnListViewScrollChangeListener;
  }

  @PluginApi(a=6)
  public void setPullBackground(Drawable paramDrawable, PullToRefreshBase.Mode paramMode)
  {
    super.setPullBackground(paramDrawable, paramMode);
    if ((this.V != null) && (paramMode.canPullDown()))
      this.V.setBackgroundDrawable(paramDrawable);
    if ((this.W != null) && (paramMode.canPullUp()))
      this.W.setBackgroundDrawable(paramDrawable);
  }

  @PluginApi(a=6)
  public void setPullLabel(String paramString, PullToRefreshBase.Mode paramMode)
  {
    super.setPullLabel(paramString, paramMode);
    if ((this.V != null) && (paramMode.canPullDown()))
      this.V.setPullLabel(paramString);
    if ((this.W != null) && (paramMode.canPullUp()))
      this.W.setPullLabel(paramString);
  }

  protected void setRefreshingInternal(boolean paramBoolean)
  {
    ListAdapter localListAdapter = ((ListView)this.A).getAdapter();
    if ((!getShowViewWhileRefreshing()) || (localListAdapter == null) || (localListAdapter.isEmpty()))
    {
      super.setRefreshingInternal(paramBoolean);
      return;
    }
    super.setRefreshingInternal(false);
    int j;
    LoadingLayout localLoadingLayout1;
    LoadingLayout localLoadingLayout2;
    int i;
    switch (f.a[getCurrentMode().ordinal()])
    {
    default:
      LoadingLayout localLoadingLayout3 = getHeaderLayout();
      LoadingLayout localLoadingLayout4 = this.V;
      j = getScrollY() + getHeaderHeight();
      localLoadingLayout1 = localLoadingLayout3;
      localLoadingLayout2 = localLoadingLayout4;
      i = 0;
    case 1:
    }
    while (true)
    {
      if (paramBoolean)
        setHeaderScroll(j);
      localLoadingLayout1.setVisibility(4);
      localLoadingLayout2.setVisibility(0);
      localLoadingLayout2.refreshing();
      if (!paramBoolean)
        break;
      ((ListView)this.A).setSelection(i);
      a(0);
      return;
      localLoadingLayout1 = getFooterLayout();
      localLoadingLayout2 = this.W;
      i = -1 + ((ListView)this.A).getCount();
      j = getScrollY() - getHeaderHeight();
    }
  }

  @PluginApi(a=6)
  public void setRefreshingLabel(String paramString, PullToRefreshBase.Mode paramMode)
  {
    super.setRefreshingLabel(paramString, paramMode);
    if ((this.V != null) && (paramMode.canPullDown()))
      this.V.setRefreshingLabel(paramString);
    if ((this.W != null) && (paramMode.canPullUp()))
      this.W.setRefreshingLabel(paramString);
  }

  @PluginApi(a=6)
  public void setReleaseLabel(String paramString, PullToRefreshBase.Mode paramMode)
  {
    super.setReleaseLabel(paramString, paramMode);
    if ((this.V != null) && (paramMode.canPullDown()))
      this.V.setReleaseLabel(paramString);
    if ((this.W != null) && (paramMode.canPullUp()))
      this.W.setReleaseLabel(paramString);
  }

  @PluginApi(a=6)
  public void setTextColor(ColorStateList paramColorStateList, PullToRefreshBase.TextType paramTextType, PullToRefreshBase.Mode paramMode)
  {
    super.setTextColor(paramColorStateList, paramTextType, paramMode);
    if ((this.V != null) && (paramMode.canPullDown()))
    {
      if (!paramTextType.isMain())
        break label66;
      this.V.setTextColor(paramColorStateList);
    }
    label66: 
    do
      while (true)
      {
        if ((this.W != null) && (paramMode.canPullUp()))
        {
          if (!paramTextType.isMain())
            break;
          this.W.setTextColor(paramColorStateList);
        }
        return;
        if (!paramTextType.isSub())
          continue;
        this.V.setSubTextColor(paramColorStateList);
      }
    while (!paramTextType.isSub());
    this.W.setSubTextColor(paramColorStateList);
  }

  @PluginApi(a=6)
  public void setTextSize(float paramFloat, PullToRefreshBase.TextType paramTextType, PullToRefreshBase.Mode paramMode)
  {
    super.setTextSize(paramFloat, paramTextType, paramMode);
    if ((this.V != null) && (paramMode.canPullDown()))
    {
      if (!paramTextType.isMain())
        break label66;
      this.V.setTextSize(paramFloat);
    }
    label66: 
    do
      while (true)
      {
        if ((this.W != null) && (paramMode.canPullUp()))
        {
          if (!paramTextType.isMain())
            break;
          this.W.setTextSize(paramFloat);
        }
        return;
        if (!paramTextType.isSub())
          continue;
        this.V.setSubTextSize(paramFloat);
      }
    while (!paramTextType.isSub());
    this.W.setSubTextSize(paramFloat);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.ui.widget.pulltorefresh.PullToRefreshListView
 * JD-Core Version:    0.6.0
 */