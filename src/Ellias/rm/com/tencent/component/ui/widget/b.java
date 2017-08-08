package com.tencent.component.ui.widget;

final class b
  implements ExtendEditText.LengthConverter
{
  public int a(CharSequence paramCharSequence, int paramInt1, int paramInt2)
  {
    int i = paramInt2 - paramInt1;
    if (i >= 0)
      return i;
    return -i;
  }

  public int b(CharSequence paramCharSequence, int paramInt1, int paramInt2)
  {
    int i = paramInt2 - paramInt1;
    if (i >= 0)
      return i;
    return -i;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.ui.widget.b
 * JD-Core Version:    0.6.0
 */