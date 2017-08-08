package com.tenpay.tenpayplugin;

import android.widget.Button;
import android.widget.ScrollView;

class TenpayPluginActivity$24
  implements Runnable
{
  public void run()
  {
    int[] arrayOfInt1 = new int[2];
    if (TenpayPluginActivity.o(this.a) != null)
      TenpayPluginActivity.o(this.a).getLocationInWindow(arrayOfInt1);
    int[] arrayOfInt2 = new int[2];
    this.a.d.getLocationInWindow(arrayOfInt2);
    this.a.d.smoothScrollBy(0, 5 + (arrayOfInt1[1] - arrayOfInt2[1] - this.a.d.getHeight() + TenpayPluginActivity.o(this.a).getHeight()));
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tenpay.tenpayplugin.TenpayPluginActivity.24
 * JD-Core Version:    0.6.0
 */