package com.tencent.qqgamemi.view.dragsortlist;

import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.Checkable;
import android.widget.ListAdapter;

class d extends BaseAdapter
{
  private ListAdapter b;

  public d(DragSortListView paramDragSortListView, ListAdapter paramListAdapter)
  {
    this.b = paramListAdapter;
    this.b.registerDataSetObserver(new e(this, paramDragSortListView));
  }

  public ListAdapter a()
  {
    return this.b;
  }

  public boolean areAllItemsEnabled()
  {
    return this.b.areAllItemsEnabled();
  }

  public int getCount()
  {
    return this.b.getCount();
  }

  public Object getItem(int paramInt)
  {
    return this.b.getItem(paramInt);
  }

  public long getItemId(int paramInt)
  {
    return this.b.getItemId(paramInt);
  }

  public int getItemViewType(int paramInt)
  {
    return this.b.getItemViewType(paramInt);
  }

  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    Object localObject2;
    if (paramView != null)
    {
      localObject2 = (DragSortItemView)paramView;
      View localView2 = ((DragSortItemView)localObject2).getChildAt(0);
      View localView3 = this.b.getView(paramInt, localView2, this.a);
      if (localView3 != localView2)
      {
        if (localView2 != null)
          ((DragSortItemView)localObject2).removeViewAt(0);
        ((DragSortItemView)localObject2).addView(localView3);
      }
      DragSortListView.a(this.a, paramInt + this.a.getHeaderViewsCount(), (View)localObject2, true);
      return localObject2;
    }
    View localView1 = this.b.getView(paramInt, null, this.a);
    if ((localView1 instanceof Checkable));
    for (Object localObject1 = new DragSortItemViewCheckable(this.a.getContext()); ; localObject1 = new DragSortItemView(this.a.getContext()))
    {
      ((DragSortItemView)localObject1).setLayoutParams(new AbsListView.LayoutParams(-1, -2));
      ((DragSortItemView)localObject1).addView(localView1);
      localObject2 = localObject1;
      break;
    }
  }

  public int getViewTypeCount()
  {
    return this.b.getViewTypeCount();
  }

  public boolean hasStableIds()
  {
    return this.b.hasStableIds();
  }

  public boolean isEmpty()
  {
    return this.b.isEmpty();
  }

  public boolean isEnabled(int paramInt)
  {
    return this.b.isEnabled(paramInt);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.view.dragsortlist.d
 * JD-Core Version:    0.6.0
 */