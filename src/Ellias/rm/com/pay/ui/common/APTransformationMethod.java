package com.pay.ui.common;

import android.text.method.ReplacementTransformationMethod;

public class APTransformationMethod extends ReplacementTransformationMethod
{
  protected char[] getOriginal()
  {
    return new char[] { '\n' };
  }

  protected char[] getReplacement()
  {
    return new char[] { ' ' };
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.ui.common.APTransformationMethod
 * JD-Core Version:    0.6.0
 */