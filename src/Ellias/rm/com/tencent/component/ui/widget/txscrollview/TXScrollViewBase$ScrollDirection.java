package com.tencent.component.ui.widget.txscrollview;

public enum TXScrollViewBase$ScrollDirection
{
  static
  {
    SCROLL_DIRECTION_HORIZONTAL = new ScrollDirection("SCROLL_DIRECTION_HORIZONTAL", 1);
    ScrollDirection[] arrayOfScrollDirection = new ScrollDirection[2];
    arrayOfScrollDirection[0] = SCROLL_DIRECTION_VERTICAL;
    arrayOfScrollDirection[1] = SCROLL_DIRECTION_HORIZONTAL;
    $VALUES = arrayOfScrollDirection;
  }

  static ScrollDirection a(int paramInt)
  {
    switch (paramInt)
    {
    default:
      return SCROLL_DIRECTION_VERTICAL;
    case 0:
      return SCROLL_DIRECTION_VERTICAL;
    case 1:
    }
    return SCROLL_DIRECTION_HORIZONTAL;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.ui.widget.txscrollview.TXScrollViewBase.ScrollDirection
 * JD-Core Version:    0.6.0
 */