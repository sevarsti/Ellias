package com.tenpay.tenpayplugin;

import android.view.inputmethod.InputMethodManager;
import android.widget.ListView;

class TenpayPluginSelectBankActivity$1
  implements Runnable
{
  public void run()
  {
    try
    {
      ((InputMethodManager)this.a.getSystemService("input_method")).hideSoftInputFromWindow(TenpayPluginSelectBankActivity.a(this.a).getWindowToken(), 1);
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tenpay.tenpayplugin.TenpayPluginSelectBankActivity.1
 * JD-Core Version:    0.6.0
 */