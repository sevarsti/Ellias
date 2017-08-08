package com.tencent.component.utils.clock;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

final class a extends Handler
{
  a(Looper paramLooper)
  {
    super(paramLooper);
  }

  public void handleMessage(Message paramMessage)
  {
    SimpleClock.a(paramMessage.what);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.utils.clock.a
 * JD-Core Version:    0.6.0
 */