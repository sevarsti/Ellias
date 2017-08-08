package com.tenpay.tenpayplugin;

import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

class TenpayPluginActivity$27
  implements Runnable
{
  public void run()
  {
    ((InputMethodManager)this.a.getSystemService("input_method")).hideSoftInputFromWindow(TenpayPluginActivity.G(this.a).getWindowToken(), 0);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tenpay.tenpayplugin.TenpayPluginActivity.27
 * JD-Core Version:    0.6.0
 */