package com.tencent.component.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;

public class OverlapLayout extends ViewGroup
{
  public OverlapLayout(Context paramContext)
  {
    super(paramContext);
  }

  public OverlapLayout(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  public OverlapLayout(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
  }

  protected ViewGroup.LayoutParams generateDefaultLayoutParams()
  {
    return new ViewGroup.LayoutParams(-1, -1);
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    int i = getPaddingLeft();
    int j = paramInt3 - paramInt1 - getPaddingRight();
    int k = getPaddingTop();
    int m = paramInt4 - paramInt2 - getPaddingBottom();
    int n = getChildCount();
    for (int i1 = 0; i1 < n; i1++)
    {
      View localView = getChildAt(i1);
      if ((localView == null) || (localView.getVisibility() == 8))
        continue;
      localView.layout(i, k, j, m);
    }
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    int i = getChildCount();
    int j = 0;
    int k = 0;
    int m = 0;
    int i6;
    if (j < i)
    {
      View localView = getChildAt(j);
      if ((localView == null) || (localView.getVisibility() == 8))
        break label174;
      localView.measure(paramInt1, paramInt2);
      int i5 = localView.getMeasuredWidth();
      i6 = localView.getMeasuredHeight();
      if (m < i5)
        m = i5;
      if (k < i6)
        label82: k = i6;
    }
    label174: for (int i4 = m; ; i4 = m)
    {
      j++;
      m = i4;
      break;
      i6 = k;
      break label82;
      int n = m + (getPaddingLeft() + getPaddingRight());
      int i1 = k + (getPaddingTop() + getPaddingBottom());
      int i2 = Math.max(n, getSuggestedMinimumWidth());
      int i3 = Math.max(i1, getSuggestedMinimumHeight());
      setMeasuredDimension(resolveSize(i2, paramInt1), resolveSize(i3, paramInt2));
      return;
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.ui.widget.OverlapLayout
 * JD-Core Version:    0.6.0
 */