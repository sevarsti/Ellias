package com.tenpay.tenpayplugin;

import android.widget.Button;
import android.widget.ScrollView;

class TenpayPluginActivity$23
  implements Runnable
{
  public void run()
  {
    int[] arrayOfInt1 = new int[2];
    if (TenpayPluginActivity.w(this.a) != null)
      TenpayPluginActivity.w(this.a).getLocationInWindow(arrayOfInt1);
    int[] arrayOfInt2 = new int[2];
    TenpayPluginActivity.x(this.a).getLocationInWindow(arrayOfInt2);
    TenpayPluginActivity.x(this.a).smoothScrollBy(0, 5 + (arrayOfInt1[1] - arrayOfInt2[1] - TenpayPluginActivity.x(this.a).getHeight() + TenpayPluginActivity.w(this.a).getHeight()));
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tenpay.tenpayplugin.TenpayPluginActivity.23
 * JD-Core Version:    0.6.0
 */