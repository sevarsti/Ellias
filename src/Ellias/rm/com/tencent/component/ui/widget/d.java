package com.tencent.component.ui.widget;

import android.text.InputFilter;
import android.text.Spanned;

final class d
  implements InputFilter
{
  private final int b;

  public d(ExtendEditText paramExtendEditText, int paramInt)
  {
    this.b = paramInt;
  }

  private void a()
  {
    ExtendEditText.LimitListener localLimitListener = ExtendEditText.b(this.a);
    if (localLimitListener != null)
      localLimitListener.a(this.b);
  }

  public CharSequence filter(CharSequence paramCharSequence, int paramInt1, int paramInt2, Spanned paramSpanned, int paramInt3, int paramInt4)
  {
    ExtendEditText.LengthConverter localLengthConverter = ExtendEditText.a(this.a);
    int i;
    if (localLengthConverter == null)
    {
      i = paramSpanned.length() - (paramInt4 - paramInt3);
      if (localLengthConverter != null)
        break label96;
    }
    int k;
    label96: for (int j = paramInt2 - paramInt1; ; j = localLengthConverter.a(paramCharSequence, paramInt1, paramInt2))
    {
      k = this.b - i;
      if (k > 0)
        break label111;
      a();
      return "";
      i = localLengthConverter.a(paramSpanned, 0, paramInt3) + localLengthConverter.a(paramSpanned, paramInt4, paramSpanned.length());
      break;
    }
    label111: if (k >= j)
      return null;
    a();
    int m;
    if (localLengthConverter != null)
    {
      m = localLengthConverter.b(paramCharSequence, paramInt1, paramInt1 + k);
      if (m <= 0)
        return "";
    }
    else
    {
      m = k;
    }
    int n = m + paramInt1;
    if (Character.isHighSurrogate(paramCharSequence.charAt(n - 1)))
    {
      n--;
      if (n == paramInt1)
        return "";
    }
    return paramCharSequence.subSequence(paramInt1, n);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.ui.widget.d
 * JD-Core Version:    0.6.0
 */