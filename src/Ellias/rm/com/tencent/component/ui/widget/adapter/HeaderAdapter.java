package com.tencent.component.ui.widget.adapter;

import android.database.DataSetObservable;
import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ListAdapter;
import android.widget.WrapperListAdapter;
import com.tencent.component.annotation.PluginApi;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

@PluginApi(a=4)
public class HeaderAdapter
  implements Filterable, WrapperListAdapter
{
  ArrayList a = new ArrayList();
  ArrayList b = new ArrayList();
  boolean c;
  private final ListAdapter d;
  private final DataSetObservable e = new DataSetObservable();
  private final boolean f;
  private boolean g = true;
  private Comparator h = new a(this);

  @PluginApi(a=4)
  public HeaderAdapter(ListAdapter paramListAdapter)
  {
    this.d = paramListAdapter;
    this.f = (paramListAdapter instanceof WrapperListAdapter);
  }

  private boolean a(ArrayList paramArrayList)
  {
    if (paramArrayList != null)
    {
      Iterator localIterator = paramArrayList.iterator();
      while (localIterator.hasNext())
        if (!((b)localIterator.next()).c)
          return false;
    }
    return true;
  }

  @PluginApi(a=4)
  public void addFooter(View paramView)
  {
    addFooter(paramView, null, true, true, 0);
  }

  @PluginApi(a=4)
  public void addFooter(View paramView, int paramInt)
  {
    addFooter(paramView, null, true, true, paramInt);
  }

  @PluginApi(a=4)
  public void addFooter(View paramView, Object paramObject, boolean paramBoolean1, boolean paramBoolean2, int paramInt)
  {
    b localb = new b(this, null);
    localb.a = paramView;
    localb.b = paramObject;
    localb.c = paramBoolean1;
    localb.d = paramBoolean2;
    localb.e = paramInt;
    this.b.add(localb);
    Collections.sort(this.b, this.h);
    notifyDataSetChanged();
  }

  @PluginApi(a=4)
  public void addFooter(View paramView, boolean paramBoolean, int paramInt)
  {
    addFooter(paramView, null, paramBoolean, true, paramInt);
  }

  @PluginApi(a=4)
  public void addFooter(View paramView, boolean paramBoolean1, boolean paramBoolean2, int paramInt)
  {
    addFooter(paramView, null, paramBoolean1, paramBoolean2, paramInt);
  }

  @PluginApi(a=4)
  public void addHeader(View paramView)
  {
    addHeader(paramView, null, true, true, 0);
  }

  @PluginApi(a=4)
  public void addHeader(View paramView, int paramInt)
  {
    addHeader(paramView, null, true, true, paramInt);
  }

  @PluginApi(a=4)
  public void addHeader(View paramView, Object paramObject, boolean paramBoolean, int paramInt)
  {
    addHeader(paramView, paramObject, paramBoolean, true, paramInt);
  }

  @PluginApi(a=4)
  public void addHeader(View paramView, Object paramObject, boolean paramBoolean1, boolean paramBoolean2, int paramInt)
  {
    b localb = new b(this, null);
    localb.a = paramView;
    localb.b = paramObject;
    localb.c = paramBoolean1;
    localb.d = paramBoolean2;
    localb.e = paramInt;
    this.a.add(localb);
    Collections.sort(this.a, this.h);
    notifyDataSetChanged();
  }

  @PluginApi(a=4)
  public void addHeader(View paramView, boolean paramBoolean, int paramInt)
  {
    addHeader(paramView, null, paramBoolean, true, paramInt);
  }

  @PluginApi(a=4)
  public void addHeader(View paramView, boolean paramBoolean1, boolean paramBoolean2, int paramInt)
  {
    addHeader(paramView, null, paramBoolean1, paramBoolean2, paramInt);
  }

  @PluginApi(a=4)
  public boolean areAllItemsEnabled()
  {
    return (this.d == null) || ((this.c) && (this.d.areAllItemsEnabled()));
  }

  @PluginApi(a=4)
  public int getCount()
  {
    if (this.d != null)
      return getFootersCount() + getHeadersCount() + this.d.getCount();
    return getFootersCount() + getHeadersCount();
  }

  public Filter getFilter()
  {
    if (this.f)
      return ((WrapperListAdapter)this.d).getFilter();
    return null;
  }

  @PluginApi(a=4)
  public int getFootersCount()
  {
    return this.b.size();
  }

  @PluginApi(a=4)
  public int getHeadersCount()
  {
    return this.a.size();
  }

  @PluginApi(a=4)
  public Object getItem(int paramInt)
  {
    int i = getHeadersCount();
    if (paramInt < i)
      return ((b)this.a.get(paramInt)).b;
    int j = paramInt - i;
    ListAdapter localListAdapter = this.d;
    int k = 0;
    if (localListAdapter != null)
    {
      k = this.d.getCount();
      if (j < k)
        return this.d.getItem(j);
    }
    return ((b)this.b.get(j - k)).b;
  }

  @PluginApi(a=4)
  public long getItemId(int paramInt)
  {
    int i = getHeadersCount();
    if ((this.d != null) && (paramInt >= i))
    {
      int j = paramInt - i;
      if (j < this.d.getCount())
        return this.d.getItemId(j);
    }
    return -1L;
  }

  public int getItemViewType(int paramInt)
  {
    int i = getHeadersCount();
    if ((this.d != null) && (paramInt >= i))
    {
      int i1 = paramInt - i;
      if (i1 < this.d.getCount())
        return this.d.getItemViewType(i1);
    }
    b localb;
    if (paramInt < i)
    {
      localb = (b)this.a.get(paramInt);
      if ((localb == null) || (localb.d))
        return -2;
    }
    else
    {
      int j = paramInt - i;
      if (this.d == null);
      for (int k = 0; ; k = this.d.getCount())
      {
        int m = j - k;
        localb = null;
        if (m < 0)
          break;
        int n = getFootersCount();
        localb = null;
        if (m >= n)
          break;
        localb = (b)this.b.get(m);
        break;
      }
    }
    return -1;
  }

  @PluginApi(a=4)
  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    int i = getHeadersCount();
    if (paramInt < i)
      return ((b)this.a.get(paramInt)).a;
    int j = paramInt - i;
    ListAdapter localListAdapter = this.d;
    int k = 0;
    if (localListAdapter != null)
    {
      k = this.d.getCount();
      if (j < k)
        return this.d.getView(j, paramView, paramViewGroup);
    }
    return ((b)this.b.get(j - k)).a;
  }

  public int getViewTypeCount()
  {
    if (this.d != null)
      return this.d.getViewTypeCount();
    return 1;
  }

  @PluginApi(a=4)
  public ListAdapter getWrappedAdapter()
  {
    return this.d;
  }

  @PluginApi(a=4)
  public boolean hasStableIds()
  {
    if (this.d != null)
      return this.d.hasStableIds();
    return false;
  }

  @PluginApi(a=4)
  public boolean isEmpty()
  {
    int i;
    if ((this.d == null) || (this.d.isEmpty()))
      i = 1;
    while (this.g)
    {
      if ((i != 0) && (getHeadersCount() + getFootersCount() == 0))
      {
        return true;
        i = 0;
        continue;
      }
      return false;
    }
    return i;
  }

  @PluginApi(a=4)
  public boolean isEnabled(int paramInt)
  {
    int i = getHeadersCount();
    if (paramInt < i)
      return ((b)this.a.get(paramInt)).c;
    int j = paramInt - i;
    ListAdapter localListAdapter = this.d;
    int k = 0;
    if (localListAdapter != null)
    {
      k = this.d.getCount();
      if (j < k)
        return this.d.isEnabled(j);
    }
    return ((b)this.b.get(j - k)).c;
  }

  @PluginApi(a=4)
  public boolean isFooterExists(View paramView)
  {
    for (int i = 0; ; i++)
    {
      int j = this.b.size();
      int k = 0;
      if (i < j)
      {
        if (((b)this.b.get(i)).a != paramView)
          continue;
        k = 1;
      }
      return k;
    }
  }

  @PluginApi(a=4)
  public boolean isHeaderExists(View paramView)
  {
    for (int i = 0; ; i++)
    {
      int j = this.a.size();
      int k = 0;
      if (i < j)
      {
        if (((b)this.a.get(i)).a != paramView)
          continue;
        k = 1;
      }
      return k;
    }
  }

  @PluginApi(a=4)
  public void notifyDataSetChanged()
  {
    if ((this.d != null) && ((this.d instanceof BaseAdapter)))
    {
      ((BaseAdapter)this.d).notifyDataSetChanged();
      return;
    }
    this.e.notifyChanged();
  }

  @PluginApi(a=4)
  public void notifyDataSetInvalidated()
  {
    if ((this.d != null) && ((this.d instanceof BaseAdapter)))
    {
      ((BaseAdapter)this.d).notifyDataSetInvalidated();
      return;
    }
    this.e.notifyInvalidated();
  }

  @PluginApi(a=4)
  public void registerDataSetObserver(DataSetObserver paramDataSetObserver)
  {
    this.e.registerObserver(paramDataSetObserver);
    if (this.d != null)
      this.d.registerDataSetObserver(paramDataSetObserver);
  }

  @PluginApi(a=4)
  public boolean removeFooter(View paramView)
  {
    for (int i = 0; i < this.b.size(); i++)
    {
      if (((b)this.b.get(i)).a != paramView)
        continue;
      this.b.remove(i);
      boolean bool1 = a(this.a);
      boolean bool2 = false;
      if (bool1)
      {
        boolean bool3 = a(this.b);
        bool2 = false;
        if (bool3)
          bool2 = true;
      }
      this.c = bool2;
      notifyDataSetChanged();
      return true;
    }
    return false;
  }

  @PluginApi(a=4)
  public boolean removeHeader(View paramView)
  {
    for (int i = 0; i < this.a.size(); i++)
    {
      if (((b)this.a.get(i)).a != paramView)
        continue;
      this.a.remove(i);
      boolean bool1 = a(this.a);
      boolean bool2 = false;
      if (bool1)
      {
        boolean bool3 = a(this.b);
        bool2 = false;
        if (bool3)
          bool2 = true;
      }
      this.c = bool2;
      notifyDataSetChanged();
      return true;
    }
    return false;
  }

  @PluginApi(a=4)
  public void setHeaderFooterVisibleWhenEmpty(boolean paramBoolean)
  {
    this.g = paramBoolean;
  }

  @PluginApi(a=4)
  public void unregisterDataSetObserver(DataSetObserver paramDataSetObserver)
  {
    this.e.unregisterObserver(paramDataSetObserver);
    if (this.d != null)
      this.d.unregisterDataSetObserver(paramDataSetObserver);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.ui.widget.adapter.HeaderAdapter
 * JD-Core Version:    0.6.0
 */