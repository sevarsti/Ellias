package com.tencent.component.ui.widget.pulltorefresh;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.Adapter;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.LinearLayout.LayoutParams;
import com.tencent.component.annotation.PluginApi;
import com.tencent.component.plugin.PluginContextWrapper;
import com.tencent.component.utils.PlatformUtil;
import com.tencent.component.utils.ResourceUtil;

@PluginApi(a=6)
public abstract class PullToRefreshAdapterViewBase extends PullToRefreshBase
  implements AbsListView.OnScrollListener
{
  static final boolean a = true;
  private int V = -1;
  private AbsListView.OnScrollListener W;
  private PullToRefreshBase.OnLastItemVisibleListener Z;
  private View aa;
  private FrameLayout ab;
  private IndicatorLayout ac;
  private IndicatorLayout ad;
  private boolean ae;
  private boolean af = true;
  private int ag = 0;
  private Context ah;

  @PluginApi(a=6)
  public PullToRefreshAdapterViewBase(Context paramContext)
  {
    super(paramContext);
    this.ah = PluginContextWrapper.a(paramContext);
    ((AbsListView)this.A).setOnScrollListener(this);
  }

  @PluginApi(a=6)
  public PullToRefreshAdapterViewBase(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    this.ah = PluginContextWrapper.a(paramContext);
    ((AbsListView)this.A).setOnScrollListener(this);
  }

  @PluginApi(a=6)
  public PullToRefreshAdapterViewBase(Context paramContext, PullToRefreshBase.Mode paramMode)
  {
    super(paramContext, paramMode);
    this.ah = PluginContextWrapper.a(paramContext);
    ((AbsListView)this.A).setOnScrollListener(this);
  }

  private boolean getShowIndicatorInternal()
  {
    return (this.ae) && (isPullToRefreshEnabled());
  }

  private void q()
  {
    PullToRefreshBase.Mode localMode = getMode();
    if ((localMode.canPullDown()) && (this.ac == null))
    {
      this.ac = new IndicatorLayout(getContext(), PullToRefreshBase.Mode.PULL_DOWN_TO_REFRESH);
      FrameLayout.LayoutParams localLayoutParams2 = new FrameLayout.LayoutParams(-2, -2);
      localLayoutParams2.rightMargin = getPlatformContext().getResources().getDimensionPixelSize(ResourceUtil.j("appfw_indicator_right_padding"));
      localLayoutParams2.gravity = 53;
      this.ab.addView(this.ac, localLayoutParams2);
    }
    do
      while ((localMode.canPullUp()) && (this.ad == null))
      {
        this.ad = new IndicatorLayout(getContext(), PullToRefreshBase.Mode.PULL_UP_TO_REFRESH);
        FrameLayout.LayoutParams localLayoutParams1 = new FrameLayout.LayoutParams(-2, -2);
        localLayoutParams1.rightMargin = getPlatformContext().getResources().getDimensionPixelSize(ResourceUtil.j("appfw_indicator_right_padding"));
        localLayoutParams1.gravity = 85;
        this.ab.addView(this.ad, localLayoutParams1);
        return;
        if ((localMode.canPullDown()) || (this.ac == null))
          continue;
        this.ab.removeView(this.ac);
        this.ac = null;
      }
    while ((localMode.canPullUp()) || (this.ad == null));
    this.ab.removeView(this.ad);
    this.ad = null;
  }

  private boolean r()
  {
    Adapter localAdapter = ((AbsListView)this.A).getAdapter();
    if ((localAdapter == null) || (localAdapter.isEmpty()))
    {
      View localView1 = ((AbsListView)this.A).getEmptyView();
      if ((localView1 == null) || (localView1.getVisibility() != 0));
    }
    while (true)
    {
      return true;
      if (((AbsListView)this.A).getChildCount() <= 0)
        continue;
      if (((AbsListView)this.A).getFirstVisiblePosition() == 0)
      {
        View localView2 = ((AbsListView)this.A).getChildAt(0);
        if (localView2 == null)
          continue;
        if (localView2.getTop() - ((AbsListView)this.A).getPaddingTop() >= ((AbsListView)this.A).getTop());
        for (int i = 1; ; i = 0)
          return i;
      }
      return false;
      if (((AbsListView)this.A).getFirstVisiblePosition() != 0)
        break;
      View localView3 = ((AbsListView)this.A).getChildAt(0);
      if (localView3 == null)
        break;
      if (localView3.getTop() - ((AbsListView)this.A).getPaddingTop() < ((AbsListView)this.A).getTop())
        return false;
    }
    return false;
  }

  private boolean s()
  {
    Adapter localAdapter = ((AbsListView)this.A).getAdapter();
    if ((localAdapter == null) || (localAdapter.isEmpty()))
      return true;
    int i = ((AbsListView)this.A).getCount();
    int j = ((AbsListView)this.A).getLastVisiblePosition();
    if (j == i - 1)
    {
      int k = j - ((AbsListView)this.A).getFirstVisiblePosition();
      View localView = ((AbsListView)this.A).getChildAt(k);
      if (localView != null)
        return localView.getBottom() - ((AbsListView)this.A).getPaddingBottom() <= ((AbsListView)this.A).getBottom();
    }
    return false;
  }

  private void t()
  {
    if (this.ac != null)
    {
      this.ab.removeView(this.ac);
      this.ac = null;
    }
    if (this.ad != null)
    {
      this.ab.removeView(this.ad);
      this.ad = null;
    }
  }

  private void u()
  {
    if (this.ac != null)
    {
      if ((isRefreshing()) || (!a()))
        break label77;
      if (!this.ac.a())
        this.ac.c();
    }
    label77: 
    do
      while (true)
      {
        if (this.ad != null)
        {
          if ((isRefreshing()) || (!b()))
            break;
          if (!this.ad.a())
            this.ad.c();
        }
        return;
        if (!this.ac.a())
          continue;
        this.ac.b();
      }
    while (!this.ad.a());
    this.ad.b();
  }

  protected void a(Context paramContext, AbsListView paramAbsListView)
  {
    this.ab = new FrameLayout(paramContext);
    this.ab.addView(paramAbsListView, -1, -1);
    a(this.ab, new LinearLayout.LayoutParams(-1, 0, 1.0F));
  }

  protected void a(AbsListView paramAbsListView, int paramInt)
  {
  }

  protected boolean a()
  {
    return r();
  }

  protected boolean b()
  {
    return s();
  }

  protected void c()
  {
    super.c();
    if (getShowIndicatorInternal());
    switch (c.a[getCurrentMode().ordinal()])
    {
    default:
      return;
    case 1:
      this.ad.e();
      return;
    case 2:
    }
    this.ac.e();
  }

  protected void d()
  {
    super.d();
    if (getShowIndicatorInternal());
    switch (c.a[getCurrentMode().ordinal()])
    {
    default:
      return;
    case 1:
      this.ad.d();
      return;
    case 2:
    }
    this.ac.d();
  }

  protected void e()
  {
    super.e();
    if (getShowIndicatorInternal())
      u();
  }

  protected void f()
  {
    super.f();
    if (getShowIndicatorInternal())
    {
      q();
      return;
    }
    t();
  }

  protected int g()
  {
    return -getHeadSpaceHeight();
  }

  public abstract ContextMenu.ContextMenuInfo getContextMenuInfo();

  public int getHeadSpaceHeight()
  {
    return this.ag;
  }

  public Context getPlatformContext()
  {
    if (this.ah == null)
      this.ah = PluginContextWrapper.a(getContext());
    return this.ah;
  }

  public boolean getShowIndicator()
  {
    return this.ae;
  }

  public final void onScroll(AbsListView paramAbsListView, int paramInt1, int paramInt2, int paramInt3)
  {
    if (this.Z != null)
    {
      int i = paramInt1 + paramInt2;
      if ((paramInt2 > 0) && (i + 1 == paramInt3) && (i != this.V))
      {
        this.V = i;
        this.Z.onLastItemVisible();
      }
    }
    if (getShowIndicatorInternal())
      u();
    if (this.W != null)
      this.W.onScroll(paramAbsListView, paramInt1, paramInt2, paramInt3);
  }

  protected void onScrollChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onScrollChanged(paramInt1, paramInt2, paramInt3, paramInt4);
    if ((this.aa != null) && (!this.af))
      this.aa.scrollTo(-paramInt1, -paramInt2);
  }

  public final void onScrollStateChanged(AbsListView paramAbsListView, int paramInt)
  {
    if (this.W != null)
      this.W.onScrollStateChanged(paramAbsListView, paramInt);
    a(paramAbsListView, paramInt);
  }

  @SuppressLint({"NewApi"})
  @PluginApi(a=6)
  public final void scrollToTop()
  {
    AbsListView localAbsListView = (AbsListView)getRefreshableView();
    if (localAbsListView == null)
      return;
    localAbsListView.setSelection(0);
    if (PlatformUtil.version() >= 8)
      localAbsListView.smoothScrollBy(0, 10);
    setHeaderScroll(getScrollY() - getHeadSpaceHeight());
    a(-getHeadSpaceHeight());
  }

  @PluginApi(a=6)
  public final void setEmptyView(View paramView)
  {
    if (this.aa != null)
      this.ab.removeView(this.aa);
    if (paramView != null)
    {
      paramView.setClickable(true);
      ViewParent localViewParent = paramView.getParent();
      if ((localViewParent != null) && ((localViewParent instanceof ViewGroup)))
        ((ViewGroup)localViewParent).removeView(paramView);
      this.ab.addView(paramView, -1, -1);
      if (!(this.A instanceof EmptyViewMethodAccessor))
        break label90;
      ((EmptyViewMethodAccessor)this.A).a(paramView);
    }
    while (true)
    {
      this.aa = paramView;
      return;
      label90: ((AbsListView)this.A).setEmptyView(paramView);
    }
  }

  public void setHeadSpaceHeight(int paramInt)
  {
    this.ag = paramInt;
  }

  @PluginApi(a=6)
  public final void setOnLastItemVisibleListener(PullToRefreshBase.OnLastItemVisibleListener paramOnLastItemVisibleListener)
  {
    this.Z = paramOnLastItemVisibleListener;
  }

  @PluginApi(a=6)
  public final void setOnScrollListener(AbsListView.OnScrollListener paramOnScrollListener)
  {
    this.W = paramOnScrollListener;
  }

  protected void setRefreshingInternal(boolean paramBoolean)
  {
    super.setRefreshingInternal(paramBoolean);
    if (getShowIndicatorInternal())
      u();
  }

  @PluginApi(a=6)
  public final void setScrollEmptyView(boolean paramBoolean)
  {
    this.af = paramBoolean;
  }

  @PluginApi(a=6)
  public void setShowIndicator(boolean paramBoolean)
  {
    this.ae = paramBoolean;
    if (getShowIndicatorInternal())
    {
      q();
      return;
    }
    t();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.ui.widget.pulltorefresh.PullToRefreshAdapterViewBase
 * JD-Core Version:    0.6.0
 */