package com.tencent.component.ui.widget.txscrollview;

public enum TXScrollViewBase$ScrollState
{
  static
  {
    ScrollState_FromStart = new ScrollState("ScrollState_FromStart", 1);
    ScrollState_FromEnd = new ScrollState("ScrollState_FromEnd", 2);
    ScrollState[] arrayOfScrollState = new ScrollState[3];
    arrayOfScrollState[0] = ScrollState_Initial;
    arrayOfScrollState[1] = ScrollState_FromStart;
    arrayOfScrollState[2] = ScrollState_FromEnd;
    $VALUES = arrayOfScrollState;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.ui.widget.txscrollview.TXScrollViewBase.ScrollState
 * JD-Core Version:    0.6.0
 */