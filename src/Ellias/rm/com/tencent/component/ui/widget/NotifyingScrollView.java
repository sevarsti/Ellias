package com.tencent.component.ui.widget;

import android.content.Context;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.widget.ScrollView;

public class NotifyingScrollView extends ScrollView
{
  private boolean a = true;
  private NotifyingScrollView.OnScrollChangedListener b;

  public NotifyingScrollView(Context paramContext)
  {
    super(paramContext);
  }

  public NotifyingScrollView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  public NotifyingScrollView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
  }

  protected float getBottomFadingEdgeStrength()
  {
    if ((this.a) && (Build.VERSION.SDK_INT < 11))
      return 0.0F;
    return super.getBottomFadingEdgeStrength();
  }

  protected float getTopFadingEdgeStrength()
  {
    if ((this.a) && (Build.VERSION.SDK_INT < 11))
      return 0.0F;
    return super.getTopFadingEdgeStrength();
  }

  protected void onScrollChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onScrollChanged(paramInt1, paramInt2, paramInt3, paramInt4);
    if (this.b != null)
      this.b.a(this, paramInt1, paramInt2, paramInt3, paramInt4);
  }

  public void setOnScrollChangedListener(NotifyingScrollView.OnScrollChangedListener paramOnScrollChangedListener)
  {
    this.b = paramOnScrollChangedListener;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.ui.widget.NotifyingScrollView
 * JD-Core Version:    0.6.0
 */