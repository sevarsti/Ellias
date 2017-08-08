package com.pay.ui.common;

import android.os.CountDownTimer;

final class t extends CountDownTimer
{
  public t(APMcardSuccessActivity paramAPMcardSuccessActivity, long paramLong1, long paramLong2)
  {
    super(60000L, 1000L);
  }

  public final void onFinish()
  {
    APMcardSuccessActivity.a(this.a, false);
  }

  public final void onTick(long paramLong)
  {
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.ui.common.t
 * JD-Core Version:    0.6.0
 */