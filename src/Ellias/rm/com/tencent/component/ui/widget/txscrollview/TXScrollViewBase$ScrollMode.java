package com.tencent.component.ui.widget.txscrollview;

public enum TXScrollViewBase$ScrollMode
{
  static
  {
    PULL_FROM_END = new ScrollMode("PULL_FROM_END", 1);
    BOTH = new ScrollMode("BOTH", 2);
    NONE = new ScrollMode("NONE", 3);
    ScrollMode[] arrayOfScrollMode = new ScrollMode[4];
    arrayOfScrollMode[0] = PULL_FROM_START;
    arrayOfScrollMode[1] = PULL_FROM_END;
    arrayOfScrollMode[2] = BOTH;
    arrayOfScrollMode[3] = NONE;
    $VALUES = arrayOfScrollMode;
  }

  static ScrollMode a(int paramInt)
  {
    switch (paramInt)
    {
    default:
      return BOTH;
    case 0:
      return PULL_FROM_START;
    case 1:
      return PULL_FROM_END;
    case 2:
      return BOTH;
    case 3:
    }
    return NONE;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.ui.widget.txscrollview.TXScrollViewBase.ScrollMode
 * JD-Core Version:    0.6.0
 */