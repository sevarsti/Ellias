package com.tencent.qqgamemi;

import android.os.Handler;
import android.os.Message;

class ag extends Handler
{
  ag(SimpleGestureDetector paramSimpleGestureDetector)
  {
  }

  ag(SimpleGestureDetector paramSimpleGestureDetector, Handler paramHandler)
  {
    super(paramHandler.getLooper());
  }

  public void handleMessage(Message paramMessage)
  {
    switch (paramMessage.what)
    {
    default:
      throw new RuntimeException("Unknown message " + paramMessage);
    case 1:
      SimpleGestureDetector.b(this.a).e(SimpleGestureDetector.a(this.a));
    case 2:
    case 3:
    }
    do
    {
      return;
      SimpleGestureDetector.c(this.a);
      return;
    }
    while ((SimpleGestureDetector.d(this.a) == null) || (SimpleGestureDetector.e(this.a)));
    SimpleGestureDetector.d(this.a).b(SimpleGestureDetector.a(this.a));
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.ag
 * JD-Core Version:    0.6.0
 */