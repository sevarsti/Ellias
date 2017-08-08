package com.tencent.component.utils;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;

class j extends FrameLayout
{
  private final View a;

  public j(Context paramContext, View paramView)
  {
    super(paramContext);
    this.a = paramView;
  }

  public int getVisibility()
  {
    if (this.a != null)
      return this.a.getVisibility();
    return super.getVisibility();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.utils.j
 * JD-Core Version:    0.6.0
 */