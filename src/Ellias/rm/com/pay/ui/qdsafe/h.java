package com.pay.ui.qdsafe;

import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import com.pay.tool.APPassWordTools;
import java.io.PrintStream;

final class h
  implements View.OnClickListener
{
  h(APSmmActivity paramAPSmmActivity)
  {
  }

  public final void onClick(View paramView)
  {
    APPassWordTools.sendTime = System.currentTimeMillis();
    System.out.println("click time=" + APPassWordTools.sendTime);
    APSmmActivity.a(this.a);
    APSmmActivity.b(this.a);
    this.a.a.setEnabled(false);
    this.a.b.post(this.a.d);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.ui.qdsafe.h
 * JD-Core Version:    0.6.0
 */