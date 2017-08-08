package com.tenpay.tenpayplugin;

import android.text.method.DialerKeyListener;

class TenpayNewCardActivity$6 extends DialerKeyListener
{
  protected char[] getAcceptedChars()
  {
    return new char[] { 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 32 };
  }

  public int getInputType()
  {
    return 3;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tenpay.tenpayplugin.TenpayNewCardActivity.6
 * JD-Core Version:    0.6.0
 */