package com.tenpay.tenpayplugin;

import android.widget.Button;
import android.widget.ScrollView;

class TenpayPluginActivity$22
  implements Runnable
{
  public void run()
  {
    int[] arrayOfInt1 = new int[2];
    if (TenpayPluginActivity.r(this.a) != null)
      TenpayPluginActivity.r(this.a).getLocationInWindow(arrayOfInt1);
    int[] arrayOfInt2 = new int[2];
    TenpayPluginActivity.s(this.a).getLocationInWindow(arrayOfInt2);
    TenpayPluginActivity.s(this.a).smoothScrollBy(0, 5 + (arrayOfInt1[1] - arrayOfInt2[1] - TenpayPluginActivity.s(this.a).getHeight() + TenpayPluginActivity.r(this.a).getHeight()));
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tenpay.tenpayplugin.TenpayPluginActivity.22
 * JD-Core Version:    0.6.0
 */