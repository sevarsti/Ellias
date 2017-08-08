package com.tencent.qqgamemi.view.dragsortlist;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.util.SparseIntArray;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;

public abstract class DragSortCursorAdapter extends CursorAdapter
  implements DragSortListView.DragSortListener
{
  public static final int a = -1;
  private SparseIntArray b = new SparseIntArray();
  private ArrayList c = new ArrayList();

  public DragSortCursorAdapter(Context paramContext, Cursor paramCursor)
  {
    super(paramContext, paramCursor);
  }

  public DragSortCursorAdapter(Context paramContext, Cursor paramCursor, int paramInt)
  {
    super(paramContext, paramCursor, paramInt);
  }

  public DragSortCursorAdapter(Context paramContext, Cursor paramCursor, boolean paramBoolean)
  {
    super(paramContext, paramCursor, paramBoolean);
  }

  private void c()
  {
    this.b.clear();
    this.c.clear();
  }

  private void d()
  {
    ArrayList localArrayList = new ArrayList();
    int i = this.b.size();
    for (int j = 0; j < i; j++)
    {
      if (this.b.keyAt(j) != this.b.valueAt(j))
        continue;
      localArrayList.add(Integer.valueOf(this.b.keyAt(j)));
    }
    int k = localArrayList.size();
    for (int m = 0; m < k; m++)
      this.b.delete(((Integer)localArrayList.get(m)).intValue());
  }

  public void a()
  {
    c();
    notifyDataSetChanged();
  }

  public void a(int paramInt)
  {
    int i = this.b.get(paramInt, paramInt);
    if (!this.c.contains(Integer.valueOf(i)))
      this.c.add(Integer.valueOf(i));
    int j = getCount();
    while (paramInt < j)
    {
      this.b.put(paramInt, this.b.get(paramInt + 1, paramInt + 1));
      paramInt++;
    }
    this.b.delete(j);
    d();
    notifyDataSetChanged();
  }

  public void a(int paramInt1, int paramInt2)
  {
    if (paramInt1 != paramInt2)
    {
      int i = this.b.get(paramInt1, paramInt1);
      if (paramInt1 > paramInt2)
        while (paramInt1 > paramInt2)
        {
          this.b.put(paramInt1, this.b.get(paramInt1 - 1, paramInt1 - 1));
          paramInt1--;
        }
      while (paramInt1 < paramInt2)
      {
        this.b.put(paramInt1, this.b.get(paramInt1 + 1, paramInt1 + 1));
        paramInt1++;
      }
      this.b.put(paramInt2, i);
      d();
      notifyDataSetChanged();
    }
  }

  public int b(int paramInt)
  {
    return this.b.get(paramInt, paramInt);
  }

  public ArrayList b()
  {
    ArrayList localArrayList = new ArrayList();
    for (int i = 0; i < getCount(); i++)
      localArrayList.add(Integer.valueOf(this.b.get(i, i)));
    return localArrayList;
  }

  public void b(int paramInt1, int paramInt2)
  {
  }

  public int c(int paramInt)
  {
    if (this.c.contains(Integer.valueOf(paramInt)))
      paramInt = -1;
    int i;
    do
    {
      return paramInt;
      i = this.b.indexOfValue(paramInt);
    }
    while (i < 0);
    return this.b.keyAt(i);
  }

  public void changeCursor(Cursor paramCursor)
  {
    super.changeCursor(paramCursor);
    c();
  }

  public int getCount()
  {
    return super.getCount() - this.c.size();
  }

  public View getDropDownView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    return super.getDropDownView(this.b.get(paramInt, paramInt), paramView, paramViewGroup);
  }

  public Object getItem(int paramInt)
  {
    return super.getItem(this.b.get(paramInt, paramInt));
  }

  public long getItemId(int paramInt)
  {
    return super.getItemId(this.b.get(paramInt, paramInt));
  }

  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    return super.getView(this.b.get(paramInt, paramInt), paramView, paramViewGroup);
  }

  public Cursor swapCursor(Cursor paramCursor)
  {
    Cursor localCursor = super.swapCursor(paramCursor);
    c();
    return localCursor;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.view.dragsortlist.DragSortCursorAdapter
 * JD-Core Version:    0.6.0
 */