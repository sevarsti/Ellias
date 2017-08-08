package com.tenpay.tenpayplugin;

import android.widget.Button;
import android.widget.ScrollView;

class TenpayNewCardActivity$19$2
  implements Runnable
{
  public void run()
  {
    int[] arrayOfInt1 = new int[2];
    if (TenpayNewCardActivity.M(TenpayNewCardActivity.19.a(this.a)) != null)
      TenpayNewCardActivity.M(TenpayNewCardActivity.19.a(this.a)).getLocationInWindow(arrayOfInt1);
    int[] arrayOfInt2 = new int[2];
    TenpayNewCardActivity.N(TenpayNewCardActivity.19.a(this.a)).getLocationInWindow(arrayOfInt2);
    TenpayNewCardActivity.N(TenpayNewCardActivity.19.a(this.a)).smoothScrollBy(0, 5 + (arrayOfInt1[1] - arrayOfInt2[1] - TenpayNewCardActivity.N(TenpayNewCardActivity.19.a(this.a)).getHeight() + TenpayNewCardActivity.M(TenpayNewCardActivity.19.a(this.a)).getHeight()));
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tenpay.tenpayplugin.TenpayNewCardActivity.19.2
 * JD-Core Version:    0.6.0
 */