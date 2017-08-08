package com.tencent.qqgamemi.view;

import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public abstract class SectionListAdapter extends BaseAdapter
{
  private int a;
  private int b = 2;
  private SparseArray c;

  private void b()
  {
    if (this.c == null)
    {
      this.c = new SparseArray();
      c();
    }
  }

  private void c()
  {
    this.c.clear();
    int i = a();
    int j = 0;
    int i9;
    for (int k = 0; j < i; k = i9)
    {
      i9 = Math.max(b(j), k);
      j++;
    }
    int m = 0;
    int n = 0;
    int i5;
    for (int i1 = 0; m < i; i1 = i5)
    {
      int i2 = b(m);
      this.c.put(n, new f(this, m, -1, i2));
      int i3 = n + 1;
      int i4 = a(m);
      i5 = i1;
      int i6 = i3;
      for (int i7 = 0; i7 < i4; i7++)
      {
        int i8 = 1 + (k + b(m, i7));
        i5 = Math.max(i8, i5);
        this.c.put(i6, new f(this, m, i7, i8));
        i6++;
      }
      m++;
      n = i6;
    }
    this.a = n;
    this.b = (1 + (i1 + (k + 1)));
  }

  public abstract int a();

  public abstract int a(int paramInt);

  public abstract View a(int paramInt1, int paramInt2, View paramView, ViewGroup paramViewGroup);

  public abstract View a(int paramInt, View paramView, ViewGroup paramViewGroup);

  public abstract Object a(int paramInt1, int paramInt2);

  public int b(int paramInt)
  {
    return 0;
  }

  public int b(int paramInt1, int paramInt2)
  {
    return 0;
  }

  public int getCount()
  {
    b();
    return this.a;
  }

  public Object getItem(int paramInt)
  {
    b();
    f localf = (f)this.c.get(paramInt);
    if ((localf != null) && (localf.c != -1))
      return a(localf.b, localf.c);
    return null;
  }

  public long getItemId(int paramInt)
  {
    return paramInt;
  }

  public int getItemViewType(int paramInt)
  {
    b();
    f localf = (f)this.c.get(paramInt);
    if (localf == null)
      return 0;
    return localf.d;
  }

  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    b();
    if (this.c == null)
      return null;
    f localf = (f)this.c.get(paramInt);
    if (localf.c == -1)
      return a(localf.b, paramView, paramViewGroup);
    return a(localf.b, localf.c, paramView, paramViewGroup);
  }

  public int getViewTypeCount()
  {
    b();
    return this.b;
  }

  public void notifyDataSetChanged()
  {
    c();
    super.notifyDataSetChanged();
  }

  public void notifyDataSetInvalidated()
  {
    c();
    super.notifyDataSetInvalidated();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.view.SectionListAdapter
 * JD-Core Version:    0.6.0
 */