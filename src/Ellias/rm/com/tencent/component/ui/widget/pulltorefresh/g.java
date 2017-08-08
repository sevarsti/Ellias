package com.tencent.component.ui.widget.pulltorefresh;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;

class g extends ListView
  implements EmptyViewMethodAccessor
{
  private boolean b = false;
  private PullToRefreshListView.OnListViewScrollChangeListener c;

  public g(PullToRefreshListView paramPullToRefreshListView, Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  public void a(View paramView)
  {
    super.setEmptyView(paramView);
  }

  public void a(PullToRefreshListView.OnListViewScrollChangeListener paramOnListViewScrollChangeListener)
  {
    this.c = paramOnListViewScrollChangeListener;
  }

  public void draw(Canvas paramCanvas)
  {
    try
    {
      super.draw(paramCanvas);
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }

  public ContextMenu.ContextMenuInfo getContextMenuInfo()
  {
    return super.getContextMenuInfo();
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    IllegalStateException localIllegalStateException2;
    try
    {
      super.onLayout(paramBoolean, paramInt1, paramInt2, paramInt3, paramInt4);
      return;
    }
    catch (IllegalStateException localIllegalStateException1)
    {
      localIllegalStateException2 = new IllegalStateException("exception occurs when layout, context:" + getContext() + ", adapter:" + getAdapter());
      localIllegalStateException2.initCause(localIllegalStateException1);
    }
    throw localIllegalStateException2;
  }

  protected void onScrollChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onScrollChanged(paramInt1, paramInt2, paramInt3, paramInt4);
    if (this.c != null)
      this.c.onScrollChanged(this, paramInt1, paramInt2, paramInt3, paramInt4);
  }

  public void setAdapter(ListAdapter paramListAdapter)
  {
    if (!this.b)
    {
      addFooterView(PullToRefreshListView.a(this.a), null, false);
      this.b = true;
    }
    super.setAdapter(paramListAdapter);
  }

  public void setEmptyView(View paramView)
  {
    this.a.setEmptyView(paramView);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.ui.widget.pulltorefresh.g
 * JD-Core Version:    0.6.0
 */