package com.pay.ui.common;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

public class APResizeLayout extends LinearLayout
{
  public APResizeLayout(Context paramContext)
  {
    super(paramContext);
  }

  public APResizeLayout(Context paramContext, AttributeSet paramAttributeSet)
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
    if (paramInt2 > paramInt4)
    {
      com.pay.tool.APGlobalInfo.SCREENHEIGHT = paramInt4;
      return;
    }
    com.pay.tool.APGlobalInfo.SCREENHEIGHT = paramInt2;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.ui.common.APResizeLayout
 * JD-Core Version:    0.6.0
 */