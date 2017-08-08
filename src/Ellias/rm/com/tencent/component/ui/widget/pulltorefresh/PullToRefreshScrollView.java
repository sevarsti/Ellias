package com.tencent.component.ui.widget.pulltorefresh;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ScrollView;
import com.tencent.component.utils.ResourceUtil;

public class PullToRefreshScrollView extends PullToRefreshBase
{
  public PullToRefreshScrollView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  protected boolean a()
  {
    return ((ScrollView)this.A).getScrollY() == 0;
  }

  protected boolean b()
  {
    View localView = ((ScrollView)this.A).getChildAt(0);
    if (localView != null)
      return ((ScrollView)this.A).getScrollY() >= localView.getHeight() - getHeight();
    return false;
  }

  protected ScrollView c(Context paramContext, AttributeSet paramAttributeSet)
  {
    ScrollView localScrollView = new ScrollView(paramContext, paramAttributeSet);
    localScrollView.setId(ResourceUtil.f("appfw_scrollview"));
    return localScrollView;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.ui.widget.pulltorefresh.PullToRefreshScrollView
 * JD-Core Version:    0.6.0
 */