package com.tencent.component.ui.widget.adapter;

import java.util.Comparator;

class a
  implements Comparator
{
  a(HeaderAdapter paramHeaderAdapter)
  {
  }

  public int a(b paramb1, b paramb2)
  {
    if (paramb1.e > paramb2.e)
      return 1;
    if (paramb1.e < paramb2.e)
      return -1;
    return 0;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.ui.widget.adapter.a
 * JD-Core Version:    0.6.0
 */