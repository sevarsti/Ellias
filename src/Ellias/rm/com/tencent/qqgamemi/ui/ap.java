package com.tencent.qqgamemi.ui;

import android.graphics.Point;
import android.view.View;
import com.tencent.component.utils.ResourceUtil;
import com.tencent.qqgamemi.plugin.ui.PluginListAdapter;
import com.tencent.qqgamemi.view.dragsortlist.DragSortController;
import com.tencent.qqgamemi.view.dragsortlist.DragSortListView;

class ap extends DragSortController
{
  DragSortListView a;
  private int i;
  private int j = 0;

  public ap(PluginManagerDialog paramPluginManagerDialog, DragSortListView paramDragSortListView, PluginListAdapter paramPluginListAdapter)
  {
    super(paramDragSortListView, ResourceUtil.f("qmi_drag_handle"), 0, 0);
    this.a = paramDragSortListView;
    this.j = 0;
  }

  public View a(int paramInt)
  {
    this.i = paramInt;
    return super.a(paramInt);
  }

  public void a(View paramView, Point paramPoint1, Point paramPoint2)
  {
    int k = this.a.getFirstVisiblePosition();
    int m = this.a.getDividerHeight();
    View localView = this.a.getChildAt(this.j - k);
    if ((localView != null) && (this.i > this.j))
    {
      int n = m + localView.getBottom();
      if (paramPoint1.y < n)
        paramPoint1.y = n;
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.ui.ap
 * JD-Core Version:    0.6.0
 */