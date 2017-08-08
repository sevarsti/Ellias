package com.tenpay.tenpayplugin.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

public class TenpayResizeLinearLayout extends LinearLayout
{
  private TenpayResizeLinearLayout.OnSizeChangedListener a;

  public TenpayResizeLinearLayout(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onLayout(paramBoolean, paramInt1, paramInt2, paramInt3, paramInt4);
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    super.onMeasure(paramInt1, paramInt2);
  }

  protected void onSizeChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onSizeChanged(paramInt1, paramInt2, paramInt3, paramInt4);
    int i = Math.abs(paramInt2 - paramInt4);
    if (i < 50)
      i = Math.abs(paramInt1 - paramInt3);
    if ((i > 49) && (this.a != null))
      this.a.onSizeChanged(paramInt1, paramInt2, paramInt3, paramInt4);
  }

  public void setOnSizeChangedListener(TenpayResizeLinearLayout.OnSizeChangedListener paramOnSizeChangedListener)
  {
    this.a = paramOnSizeChangedListener;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tenpay.tenpayplugin.view.TenpayResizeLinearLayout
 * JD-Core Version:    0.6.0
 */