package com.pay.ui.qdsafe;

import android.os.Handler;
import android.widget.Button;
import com.pay.tool.APCommMethod;

final class l
  implements Runnable
{
  private l(APSmmActivity paramAPSmmActivity)
  {
  }

  public final void run()
  {
    if (this.a.c == 0)
    {
      this.a.a.setTextSize(14.0F);
      this.a.a.setTextColor(APCommMethod.getColorId(this.a, "unipay_em1_color"));
      if ((this.a.f > 0) || (this.a.f < 0))
      {
        this.a.a.setText(APCommMethod.getStringId(this.a, "unipay_vercode_get"));
        this.a.a.setEnabled(true);
      }
      while (true)
      {
        this.a.c = 60;
        this.a.b.removeCallbacksAndMessages(null);
        return;
        this.a.a.setText(APCommMethod.getStringId(this.a, "unipay_vercode_over"));
        this.a.a.setEnabled(false);
      }
    }
    this.a.b.removeCallbacksAndMessages(null);
    this.a.a.setTextSize(12.0F);
    this.a.a.setTextColor(APCommMethod.getColorId(this.a, "unipay_thin2_color"));
    this.a.a.setText(String.valueOf(this.a.c) + APCommMethod.getStringId(this.a, "unipay_vercode_afterget"));
    APSmmActivity localAPSmmActivity = this.a;
    localAPSmmActivity.c = (-1 + localAPSmmActivity.c);
    this.a.b.postDelayed(this.a.d, 1000L);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.ui.qdsafe.l
 * JD-Core Version:    0.6.0
 */