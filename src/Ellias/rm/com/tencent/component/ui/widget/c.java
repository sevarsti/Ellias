package com.tencent.component.ui.widget;

final class c
  implements ExtendEditText.LengthConverter
{
  private boolean a(char paramChar)
  {
    Character.UnicodeBlock localUnicodeBlock = Character.UnicodeBlock.of(paramChar);
    return (localUnicodeBlock == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS) || (localUnicodeBlock == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS) || (localUnicodeBlock == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A) || (localUnicodeBlock == Character.UnicodeBlock.GENERAL_PUNCTUATION) || (localUnicodeBlock == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION) || (localUnicodeBlock == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS);
  }

  public int a(CharSequence paramCharSequence, int paramInt1, int paramInt2)
  {
    int i;
    if (paramInt1 <= paramInt2)
    {
      i = paramInt1;
      if (paramInt1 > paramInt2)
        break label67;
    }
    int j;
    while (true)
    {
      j = 0;
      for (int k = i; (k < paramInt2) && (k < paramCharSequence.length()); k++)
      {
        if (!a(paramCharSequence.charAt(k)))
          continue;
        j++;
      }
      i = paramInt2;
      break;
      label67: paramInt2 = paramInt1;
    }
    return j + (paramInt2 - i);
  }

  public int b(CharSequence paramCharSequence, int paramInt1, int paramInt2)
  {
    int i;
    label13: int j;
    int k;
    if (paramInt1 <= paramInt2)
    {
      i = paramInt1;
      if (paramInt1 > paramInt2)
        break label81;
      j = i;
      k = 0;
    }
    while (true)
    {
      int m = paramCharSequence.length();
      int n = 0;
      if (j < m)
      {
        if (a(paramCharSequence.charAt(j)))
          k++;
        if (j + k >= paramInt2)
          n = j - i;
      }
      else
      {
        return n;
        i = paramInt2;
        break;
        label81: paramInt2 = paramInt1;
        break label13;
      }
      j++;
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.ui.widget.c
 * JD-Core Version:    0.6.0
 */