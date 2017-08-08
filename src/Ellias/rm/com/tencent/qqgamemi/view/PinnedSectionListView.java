package com.tencent.qqgamemi.view;

import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Canvas;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.widget.AbsListView.LayoutParams;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SectionIndexer;

public class PinnedSectionListView extends ListView
{
  AbsListView.OnScrollListener a;
  d b;
  d c;
  int d;
  private boolean e = true;
  private final DataSetObserver f = new a(this);
  private final AbsListView.OnScrollListener g = new b(this);

  public PinnedSectionListView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    a();
  }

  public PinnedSectionListView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    a();
  }

  private int a(int paramInt1, int paramInt2)
  {
    PinnedSectionListView.PinnedSectionListAdapter localPinnedSectionListAdapter = (PinnedSectionListView.PinnedSectionListAdapter)getAdapter();
    for (int i = 0; i < paramInt2; i++)
    {
      int j = paramInt1 + i;
      if (localPinnedSectionListAdapter.a(localPinnedSectionListAdapter.getItemViewType(j)))
        return j;
    }
    return -1;
  }

  private void a()
  {
    setOnScrollListener(this.g);
  }

  private void a(int paramInt)
  {
    d locald1 = this.b;
    this.b = null;
    if (locald1 == null);
    for (d locald2 = new d(); ; locald2 = locald1)
    {
      View localView = getAdapter().getView(paramInt, locald2.a, this);
      AbsListView.LayoutParams localLayoutParams = (AbsListView.LayoutParams)localView.getLayoutParams();
      int k;
      int j;
      int m;
      if (localLayoutParams == null)
      {
        k = -2147483648;
        j = getHeight();
        if (k == 0)
          k = 1073741824;
        m = getHeight() - getListPaddingTop() - getListPaddingBottom();
        if (j <= m)
          break label193;
      }
      while (true)
      {
        localView.measure(View.MeasureSpec.makeMeasureSpec(getWidth() - getListPaddingLeft() - getListPaddingRight(), 1073741824), View.MeasureSpec.makeMeasureSpec(m, k));
        localView.layout(0, 0, localView.getMeasuredWidth(), localView.getMeasuredHeight());
        this.d = 0;
        locald2.b = paramInt;
        locald2.a = localView;
        this.c = locald2;
        return;
        int i = View.MeasureSpec.getMode(localLayoutParams.height);
        j = View.MeasureSpec.getSize(localLayoutParams.height);
        k = i;
        break;
        label193: m = j;
      }
    }
  }

  private int b(int paramInt)
  {
    PinnedSectionListView.PinnedSectionListAdapter localPinnedSectionListAdapter = (PinnedSectionListView.PinnedSectionListAdapter)getAdapter();
    if ((localPinnedSectionListAdapter instanceof SectionIndexer))
    {
      SectionIndexer localSectionIndexer = (SectionIndexer)localPinnedSectionListAdapter;
      int j = localSectionIndexer.getPositionForSection(localSectionIndexer.getSectionForPosition(paramInt));
      if (localPinnedSectionListAdapter.a(localPinnedSectionListAdapter.getItemViewType(j)))
        return j;
    }
    for (int i = paramInt; i >= 0; i--)
      if (localPinnedSectionListAdapter.a(localPinnedSectionListAdapter.getItemViewType(i)))
        return i;
    return -1;
  }

  private void b()
  {
    this.b = this.c;
    this.c = null;
  }

  protected void dispatchDraw(Canvas paramCanvas)
  {
    super.dispatchDraw(paramCanvas);
    if (this.c != null)
    {
      int i = getListPaddingLeft();
      int j = getListPaddingTop();
      View localView = this.c.a;
      paramCanvas.save();
      paramCanvas.clipRect(i, j, i + localView.getWidth(), j + localView.getHeight());
      paramCanvas.translate(i, j + this.d);
      drawChild(paramCanvas, this.c.a, getDrawingTime());
      paramCanvas.restore();
    }
  }

  public void onRestoreInstanceState(Parcelable paramParcelable)
  {
    super.onRestoreInstanceState(paramParcelable);
    post(new c(this));
  }

  public void setAdapter(ListAdapter paramListAdapter)
  {
    if ((this.e) && (paramListAdapter != null))
    {
      if (!(paramListAdapter instanceof PinnedSectionListView.PinnedSectionListAdapter))
        throw new IllegalArgumentException("Does your adapter implement PinnedSectionListAdapter?");
      if (paramListAdapter.getViewTypeCount() < 2)
        throw new IllegalArgumentException("Does your adapter handle at least two types of views - items and sections?");
    }
    ListAdapter localListAdapter = getAdapter();
    if (localListAdapter != null)
      localListAdapter.unregisterDataSetObserver(this.f);
    if (paramListAdapter != null)
      paramListAdapter.registerDataSetObserver(this.f);
    if (localListAdapter != paramListAdapter)
      b();
    super.setAdapter(paramListAdapter);
  }

  public void setOnScrollListener(AbsListView.OnScrollListener paramOnScrollListener)
  {
    if (paramOnScrollListener == this.g)
    {
      super.setOnScrollListener(paramOnScrollListener);
      return;
    }
    this.a = paramOnScrollListener;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.view.PinnedSectionListView
 * JD-Core Version:    0.6.0
 */