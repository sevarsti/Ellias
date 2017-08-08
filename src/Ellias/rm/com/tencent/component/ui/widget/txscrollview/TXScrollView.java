package com.tencent.component.ui.widget.txscrollview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ScrollView;

public class TXScrollView extends TXScrollViewBase
{
  public TXScrollView(Context paramContext)
  {
    super(paramContext, TXScrollViewBase.ScrollDirection.SCROLL_DIRECTION_VERTICAL, TXScrollViewBase.ScrollMode.NONE);
  }

  public TXScrollView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  protected ScrollView a(Context paramContext)
  {
    ScrollView localScrollView = new ScrollView(paramContext);
    localScrollView.setVerticalFadingEdgeEnabled(false);
    return localScrollView;
  }

  protected boolean a()
  {
    return (this.i != null) && (((ScrollView)this.i).getScrollY() == 0);
  }

  protected boolean b()
  {
    if (this.i != null)
    {
      View localView = ((ScrollView)this.i).getChildAt(0);
      if ((this.i != null) && (localView.getMeasuredHeight() <= ((ScrollView)this.i).getScrollY() + ((ScrollView)this.i).getHeight()))
        return true;
    }
    return false;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.ui.widget.txscrollview.TXScrollView
 * JD-Core Version:    0.6.0
 */