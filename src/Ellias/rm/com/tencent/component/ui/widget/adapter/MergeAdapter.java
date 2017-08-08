package com.tencent.component.ui.widget.adapter;

import android.database.DataSetObserver;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import com.tencent.component.annotation.PluginApi;
import java.util.ArrayList;

@PluginApi(a=4)
public class MergeAdapter extends BaseAdapter
{
  private final ArrayList a = new ArrayList();
  private Handler b = new Handler(Looper.getMainLooper());
  private DataSetObserver c = new e(this);

  public static int a(ArrayList paramArrayList)
  {
    int i = paramArrayList.size();
    int j = 0;
    int k = 0;
    while (j < i)
    {
      k += ((Adapter)paramArrayList.get(j)).getCount();
      j++;
    }
    return k;
  }

  private void a()
  {
    super.notifyDataSetChanged();
  }

  public static int b(ArrayList paramArrayList)
  {
    int i = paramArrayList.size();
    int j = 0;
    int k = 0;
    while (j < i)
    {
      k += ((Adapter)paramArrayList.get(j)).getViewTypeCount();
      j++;
    }
    return k;
  }

  @PluginApi(a=4)
  public void add(BaseAdapter paramBaseAdapter)
  {
    this.a.add(paramBaseAdapter);
    registerDataSetObserverForAdapter(paramBaseAdapter);
  }

  @PluginApi(a=4)
  public boolean areAllItemsEnabled()
  {
    return false;
  }

  @PluginApi(a=4)
  public int getCount()
  {
    return a(this.a);
  }

  @PluginApi(a=4)
  public Object getItem(int paramInt)
  {
    int i = this.a.size();
    for (int j = 0; j < i; j++)
    {
      Adapter localAdapter = (Adapter)this.a.get(j);
      int k = localAdapter.getCount();
      if (paramInt < k)
        return localAdapter.getItem(paramInt);
      paramInt -= k;
    }
    return null;
  }

  @PluginApi(a=4)
  public long getItemId(int paramInt)
  {
    return paramInt;
  }

  public int getItemViewType(int paramInt)
  {
    int i = this.a.size();
    int j = 0;
    int k = 0;
    while (j < i)
    {
      Adapter localAdapter = (Adapter)this.a.get(j);
      int m = localAdapter.getCount();
      if (paramInt < m)
        return k + localAdapter.getItemViewType(paramInt);
      paramInt -= m;
      k += localAdapter.getViewTypeCount();
      j++;
    }
    return -1;
  }

  @PluginApi(a=4)
  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    int i = this.a.size();
    for (int j = 0; j < i; j++)
    {
      Adapter localAdapter = (Adapter)this.a.get(j);
      int k = localAdapter.getCount();
      if (paramInt < k)
        return localAdapter.getView(paramInt, paramView, paramViewGroup);
      paramInt -= k;
    }
    return null;
  }

  public int getViewTypeCount()
  {
    return Math.max(b(this.a), 1);
  }

  @PluginApi(a=4)
  public boolean hasStableIds()
  {
    return false;
  }

  @PluginApi(a=4)
  public boolean isEmpty()
  {
    return getCount() == 0;
  }

  @PluginApi(a=4)
  public boolean isEnabled(int paramInt)
  {
    int i = this.a.size();
    for (int j = 0; j < i; j++)
    {
      BaseAdapter localBaseAdapter = (BaseAdapter)this.a.get(j);
      int k = localBaseAdapter.getCount();
      if (paramInt < k)
        return localBaseAdapter.isEnabled(paramInt);
      paramInt -= k;
    }
    return false;
  }

  @PluginApi(a=4)
  public void notifyDataSetChanged()
  {
    if (Looper.myLooper() == Looper.getMainLooper())
    {
      a();
      return;
    }
    this.b.post(new d(this));
  }

  @PluginApi(a=4)
  protected void registerDataSetObserverForAdapter(BaseAdapter paramBaseAdapter)
  {
    paramBaseAdapter.registerDataSetObserver(this.c);
  }

  @PluginApi(a=4)
  public void remove(BaseAdapter paramBaseAdapter)
  {
    if (Looper.myLooper() == Looper.getMainLooper())
    {
      this.a.remove(paramBaseAdapter);
      notifyDataSetChanged();
      return;
    }
    this.b.post(new c(this, paramBaseAdapter));
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.ui.widget.adapter.MergeAdapter
 * JD-Core Version:    0.6.0
 */