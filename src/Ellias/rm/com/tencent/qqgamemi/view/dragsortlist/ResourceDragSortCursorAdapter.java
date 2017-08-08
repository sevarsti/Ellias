package com.tencent.qqgamemi.view.dragsortlist;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class ResourceDragSortCursorAdapter extends DragSortCursorAdapter
{
  private int b;
  private int c;
  private LayoutInflater d;

  @Deprecated
  public ResourceDragSortCursorAdapter(Context paramContext, int paramInt, Cursor paramCursor)
  {
    super(paramContext, paramCursor);
    this.c = paramInt;
    this.b = paramInt;
    this.d = ((LayoutInflater)paramContext.getSystemService("layout_inflater"));
  }

  public ResourceDragSortCursorAdapter(Context paramContext, int paramInt1, Cursor paramCursor, int paramInt2)
  {
    super(paramContext, paramCursor, paramInt2);
    this.c = paramInt1;
    this.b = paramInt1;
    this.d = ((LayoutInflater)paramContext.getSystemService("layout_inflater"));
  }

  public ResourceDragSortCursorAdapter(Context paramContext, int paramInt, Cursor paramCursor, boolean paramBoolean)
  {
    super(paramContext, paramCursor, paramBoolean);
    this.c = paramInt;
    this.b = paramInt;
    this.d = ((LayoutInflater)paramContext.getSystemService("layout_inflater"));
  }

  public void d(int paramInt)
  {
    this.b = paramInt;
  }

  public void e(int paramInt)
  {
    this.c = paramInt;
  }

  public View newDropDownView(Context paramContext, Cursor paramCursor, ViewGroup paramViewGroup)
  {
    return this.d.inflate(this.c, paramViewGroup, false);
  }

  public View newView(Context paramContext, Cursor paramCursor, ViewGroup paramViewGroup)
  {
    return this.d.inflate(this.b, paramViewGroup, false);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.view.dragsortlist.ResourceDragSortCursorAdapter
 * JD-Core Version:    0.6.0
 */