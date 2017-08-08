package com.tenpay.tenpayplugin.view;

import android.content.Context;
import android.view.KeyEvent;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

class ClearableEditText$1
  implements TextView.OnEditorActionListener
{
  public boolean onEditorAction(TextView paramTextView, int paramInt, KeyEvent paramKeyEvent)
  {
    if ((paramKeyEvent != null) && (paramKeyEvent.getKeyCode() == 66))
      paramInt = 6;
    if (paramInt == 6)
      ((InputMethodManager)this.a.getContext().getSystemService("input_method")).hideSoftInputFromWindow(this.a.getWindowToken(), 0);
    return false;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tenpay.tenpayplugin.view.ClearableEditText.1
 * JD-Core Version:    0.6.0
 */