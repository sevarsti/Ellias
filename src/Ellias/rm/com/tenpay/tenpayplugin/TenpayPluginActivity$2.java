package com.tenpay.tenpayplugin;

import android.content.Context;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

class TenpayPluginActivity$2
  implements Runnable
{
  public void run()
  {
    ((InputMethodManager)TenpayPluginActivity.m(this.a).getContext().getSystemService("input_method")).showSoftInput(TenpayPluginActivity.m(this.a), 0);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tenpay.tenpayplugin.TenpayPluginActivity.2
 * JD-Core Version:    0.6.0
 */