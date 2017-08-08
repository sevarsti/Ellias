package com.tencent.component.ui.widget;

import android.os.Handler;
import android.os.Message;

class e
{
  public static final int a = 16;
  private static final int b = 1000;
  private static final Handler c = new g(null);

  private e()
  {
    throw new UnsupportedOperationException();
  }

  public static void a(Runnable paramRunnable)
  {
    Message localMessage = new Message();
    localMessage.what = 1000;
    localMessage.obj = paramRunnable;
    c.sendMessageDelayed(localMessage, 16L);
  }

  public static void a(Runnable paramRunnable, long paramLong)
  {
    Message localMessage = new Message();
    localMessage.what = 1000;
    localMessage.obj = paramRunnable;
    c.sendMessageDelayed(localMessage, paramLong);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.ui.widget.e
 * JD-Core Version:    0.6.0
 */